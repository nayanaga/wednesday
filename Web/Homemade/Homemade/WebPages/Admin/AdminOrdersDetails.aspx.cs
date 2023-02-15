using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

using System.Data.SqlClient;

namespace Homemade.WebPages.Admin
{
    public partial class AdminOrdersDetails : System.Web.UI.Page
    {
        readyclass obj = new readyclass();

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                lblOrderID.Text = Request.QueryString["OrderID"].ToString();

                fillOrderDetails();

                fillUserDetails();

                fillBillItems();

                fillStoreDetails();
            }

        }

        private void fillOrderDetails()
        {
            string sql = "Select * from tblOrders where OrderID='" + lblOrderID.Text.Trim() + "'";
            SqlDataReader dr = Database.getDataReader(sql);
            if (dr.Read())
            {
                lblOrderID.Text = dr[0].ToString().Trim();
                lblODate.Text = Convert.ToDateTime(dr[1]).ToString("dd/MM/yyyy");
                lblBillAmount.Text = dr[2].ToString().Trim();
                lblStatus.Text = dr[3].ToString().Trim();
                lblMobile.Text = dr[4].ToString().Trim();
                lblSMobile.Text = dr[5].ToString().Trim();
            }
            dr.Close();
        }

        private void fillUserDetails()
        {
            string sql = "Select * from tblUsers where Mobile='" + lblMobile.Text.Trim() + "'";
            SqlDataReader dr = Database.getDataReader(sql);
            if (dr.Read())
            {
                lblUserName.Text = dr[1].ToString().Trim();
                lblAddressLine1.Text = dr[2].ToString().Trim();
                lblAddressLine2.Text = dr[3].ToString().Trim();
                lblTaluk.Text = dr[4].ToString().Trim();
                lblDistrict.Text = dr[5].ToString().Trim();
                lblMobile.Text = dr[6].ToString().Trim();
                lblEmailID.Text = dr[7].ToString().Trim();
            }
            dr.Close();
        }

        private void fillStoreDetails()
        {
            string sql = "Select * from tblEntrepreneur where Mobile='" + lblSMobile.Text.Trim() + "'";
            SqlDataReader dr = Database.getDataReader(sql);
            if (dr.Read())
            {
                lblStoreName.Text = dr[1].ToString().Trim();
                lblContactPerson.Text = dr[2].ToString().Trim();
                lblSAddressLine1.Text = dr[3].ToString().Trim();
                lblSAddressLine2.Text = dr[4].ToString().Trim();
                lblSTaluk.Text = dr[5].ToString().Trim();
                lblSDistrict.Text = dr[6].ToString().Trim();
                lblSMobile.Text = dr[7].ToString().Trim();
                lblSEmailID.Text = dr[8].ToString().Trim();
            }
            dr.Close();
        }

        private void fillBillItems()
        {
            string sql = "Select * from tblOrdersItems where OrderID='" + lblOrderID.Text + "'";
            obj.fill(grdOrderItems, sql, lblerror);
        }

        protected void btnBack_Click(object sender, EventArgs e)
        {
            Response.Redirect("AdminOrdersList.aspx");
        }

        protected void btnDelete_Click(object sender, EventArgs e)
        {
            string sql = "delete from tblOrders where OrderID='" + lblOrderID.Text.Trim() + "'";
            Database.executeQuery(sql);

            sql = "delete from tblOrdersItems where OrderID='" + lblOrderID.Text.Trim() + "'";
            Database.executeQuery(sql);

            obj.Show("Deleted Successfully", "AdminOrdersList.aspx");
        }

    }
}