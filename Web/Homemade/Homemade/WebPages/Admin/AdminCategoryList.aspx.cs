using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Homemade.WebPages.Admin
{
    public partial class AdminCategoryList : System.Web.UI.Page
    {
        readyclass obj = new readyclass();
        string str;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                fill();
            }
        }

        private void fill()
        {
            str = "select * from tblCategory";
            obj.fill(grdCategory, str, lblerror);
        }

        protected void btnAddNew_Click(object sender, EventArgs e)
        {
            string sql = "select * from tblCategory where Category='" + txtCategory.Text.Trim() + "'";
            if (obj.checkduplicate(sql))
            {
                readyclass.errormessage(lblerror, "Category Already exist");
            }
            else
            {
                lblerror.Text = "";

                int id = obj.autoid("tblCategory", "ID");

                sql = "insert into tblCategory (ID,Category) ";
                sql = sql + "Values(" + id + ", '" + txtCategory.Text.Trim() + "')";
                Database.executeQuery(sql);

                txtCategory.Text = "";

                fill();
            }
        }

        protected void grdCategory_RowDeleting(object sender, GridViewDeleteEventArgs e)
        {
            string release;
            release = grdCategory.DataKeys[e.RowIndex].Values[0].ToString();

            string sql = "delete from tblCategory where ID = " + release + "";
            Database.executeQuery(sql);

            fill();
        }

        protected void grdCategory_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                // reference the Delete LinkButtonc
                LinkButton db = (LinkButton)e.Row.Cells[1].Controls[0];

                db.OnClientClick = "return confirm('Are you certain you want to delete this?');";
            }
        }

    }
}