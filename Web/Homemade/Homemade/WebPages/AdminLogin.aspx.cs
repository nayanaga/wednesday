using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

using System.Data.SqlClient;

namespace Homemade.WebPages
{
    public partial class AdminLogin : System.Web.UI.Page
    {
        readyclass obj = new readyclass();

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
                txtUserID.Focus();
        }

        protected void btnLogin_Click(object sender, EventArgs e)
        {
            string sql = "select * from tblLogin where UserID= '" + txtUserID.Text.Trim() + "' and Password='" + txtPassword.Text.Trim() + "'";

            SqlDataReader dr = Database.getDataReader(sql);

            if (dr.Read())
            {
                string str = dr[2].ToString();

                dr.Close();

                if (str.Trim() == "Admin")
                {
                    Response.Redirect("~/WebPages/Admin/AdminHome.aspx");
                }
            
                else
                {
                    readyclass.errormessage(lblerror, "Invalid User ID or Password");
                    dr.Close();
                }
            }
            else
            {
                readyclass.errormessage(lblerror, "Invalid User ID or Password");
                dr.Close();
            }
        }
    }
}