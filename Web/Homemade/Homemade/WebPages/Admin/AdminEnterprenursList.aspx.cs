using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Homemade.WebPages.Admin
{
    public partial class AdminEnterprenursList : System.Web.UI.Page
    {
        readyclass obj = new readyclass();
        string str;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                string sql = "Select distinct(Status) from tblEntrepreneur";
                obj.filllist(ddlStatus, sql);
            }
        }

        private void fill()
        {
            str = "select * from tblEntrepreneur where Status='" + ddlStatus.SelectedValue + "'";
            obj.fill(grdEnterprenurs, str, lblerror);
        }

        protected void ddlStatus_SelectedIndexChanged(object sender, EventArgs e)
        {
            fill();
        }

    }
}