using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

using System.Data.SqlClient;

namespace Homemade.WebPages.Admin
{
    public partial class AdminEnterprenursDetails : System.Web.UI.Page
    {
        readyclass obj = new readyclass();

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                lblID.Text = Request.QueryString["ID"];

                fillDetails();

                btnDelete.Attributes.Add("onclick", "return confirm();");
            }
        }

        private void fillDetails()
        {
            string sql = "Select * from tblEntrepreneur where ID='" + lblID.Text.Trim() + "'";
            SqlDataReader dr = Database.getDataReader(sql);
            if (dr.Read())
            {
                lblID.Text = dr[0].ToString().Trim();
                lblEnterprenur.Text = dr[1].ToString().Trim();
                lblOwnerPerson.Text = dr[2].ToString().Trim();
                lblAddressLine1.Text = dr[3].ToString().Trim();
                lblAddressLine2.Text = dr[4].ToString().Trim();
                lblTaluk.Text = dr[5].ToString().Trim();
                lblDistrict.Text = dr[6].ToString().Trim();
                lblMobile.Text = dr[7].ToString().Trim();
                lblEmailID.Text = dr[8].ToString().Trim();
                lblStatus.Text = dr[9].ToString().Trim();
            }
            dr.Close();

            if (lblStatus.Text == "New")
            {
                btnStatus.Visible = true;
            }
            else
                btnStatus.Visible = false;
        }

        protected void btnStatus_Click(object sender, EventArgs e)
        {
            string sql = "Update tblEntrepreneur SET ";
            sql = sql + "Status = 'Approved' Where ID = '" + lblID.Text.Trim() + "'";

            Database.executeQuery(sql);

            string pwd = obj.generateOTP();

            string sql1 = "insert into tblLogin (UserID,Password,UserType,UserName) ";
            sql1 = sql1 + "Values('" + lblMobile.Text + "', '" + pwd + "','Entrepreneur','" + lblEnterprenur.Text + "')";
            Database.executeQuery(sql1);

            string msg = "Your Login Code " + lblMobile.Text.Trim() + " Password is " + pwd;
            string mobile = lblMobile.Text.Trim();

            obj.Show("Approved Successfully"+msg, "AdminEnterprenursList.aspx");
        }

        protected void btnCancel_Click(object sender, EventArgs e)
        {
            Response.Redirect("AdminEnterprenursList.aspx");
        }

        protected void btnDelete_Click(object sender, EventArgs e)
        {
            if (lblID.Text.Trim() == "")
                readyclass.errormessage(lblerror, "Select Entrepreneur Details to delete");
            else
            {
                string sql = "delete from tblEntrepreneur where ID = '" + lblID.Text.Trim() + "'";
                Database.executeQuery(sql);

                sql = "delete from tblLogin where UserID = '" + lblMobile.Text + "'";
                Database.executeQuery(sql);

                obj.Show("Deleted Successfully", "AdminEnterprenursList.aspx");
            }
        }
    }
}