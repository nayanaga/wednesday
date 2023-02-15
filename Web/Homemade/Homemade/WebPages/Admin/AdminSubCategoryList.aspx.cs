using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Homemade.WebPages.Admin
{
    public partial class AdminSubCategoryList : System.Web.UI.Page
    {
        readyclass obj = new readyclass();
        string str;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                str = "Select Category from tblCategory";
                obj.filllist(ddlCategory, str);

                str = "Select distinct(Category) from tblSubCategory";
                obj.filllist(ddlSearchCategory, str);

            }
        }

        private void fill()
        {
            str = "Select * from tblSubCategory where Category='" + ddlSearchCategory.SelectedValue + "'";
            obj.fill(grdSubCategory, str, lblerror);
        }

        protected void btnAdd_Click(object sender, EventArgs e)
        {
            string sql = "select * from tblSubCategory where Category='" + ddlCategory.SelectedValue + "' and SubCategory='" + txtSubCategory.Text.Trim() + "'";
            if (obj.checkduplicate(sql))
            {
                readyclass.errormessage(lblerror, "Sub Category Already exist");
            }
            else
            {
                lblerror.Text = "";

                int id = obj.autoid("tblSubCategory", "ID");

                sql = "insert into tblSubCategory (ID,Category,SubCategory) ";
                sql = sql + "Values(" + id + ", '" + ddlCategory.SelectedValue + "','" + txtSubCategory.Text.Trim() + "')";
                Database.executeQuery(sql);

                txtSubCategory.Text = "";

                str = "Select distinct(Category) from tblSubCategory";
                obj.filllist(ddlSearchCategory, str);

                ddlSearchCategory.SelectedValue = ddlCategory.SelectedValue;
                ddlCategory.SelectedValue = "Select";

                fill();

            }
        }

        protected void grdSubCategory_RowDeleting(object sender, GridViewDeleteEventArgs e)
        {
            string release;
            release = grdSubCategory.DataKeys[e.RowIndex].Values[0].ToString();

            string sql = "delete from tblSubCategory where ID = " + release + "";
            Database.executeQuery(sql);

            fill();
        }

        protected void grdSubCategory_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                // reference the Delete LinkButton
                LinkButton db = (LinkButton)e.Row.Cells[1].Controls[0];

                db.OnClientClick = "return confirm('Are you certain you want to delete this?');";
            }
        }

        protected void ddlSearchCategory_SelectedIndexChanged(object sender, EventArgs e)
        {
            fill();
        }

    }
}