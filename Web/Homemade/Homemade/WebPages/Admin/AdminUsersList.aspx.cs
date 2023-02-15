using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Homemade.WebPages.Admin
{
    public partial class AdminUsersList : System.Web.UI.Page
    {
        readyclass obj = new readyclass();

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                string sql = "Select * from tblUsers";
                obj.fill(grdUsers, sql, lblerror);
            }

        }
    }
}