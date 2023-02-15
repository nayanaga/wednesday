using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

using System.Data.SqlClient;

namespace Homemade.WebPages.Admin
{
    public partial class AdminOrdersList : System.Web.UI.Page
    {
        string sql;
        readyclass obj = new readyclass();

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                txtEndDate.Text = DateTime.Now.ToShortDateString();
                txtStartDate.Text = DateTime.Now.ToShortDateString();

                sql = "Select distinct(Status) from tblOrders";
                obj.filllist(ddlStatus, sql);

            }
        }

        protected void btnView_Click(object sender, EventArgs e)
        {
            sql = "select * from tblOrders where ODate >='" + txtStartDate.Text.Trim() + "' and ODate<='" + txtEndDate.Text.Trim() + "'";
            obj.fill(grdReport, sql, lblerror);

            sql = "select Sum(BillAmount) from tblOrders where ODate >='" + txtStartDate.Text.Trim() + "' and ODate<='" + txtEndDate.Text.Trim() + "'";
            SqlDataReader dr = Database.getDataReader(sql);
            if (dr.Read())
            {
                lblAmountCollected.Text = dr[0].ToString().Trim();
            }
            dr.Close();
        }
    }
}