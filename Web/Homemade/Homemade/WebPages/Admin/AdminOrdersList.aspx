<%@ Page Title="" Language="C#" MasterPageFile="~/WebPages/Admin/Admin.Master" AutoEventWireup="true" CodeBehind="AdminOrdersList.aspx.cs" Inherits="Homemade.WebPages.Admin.AdminOrdersList" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">


<h1>Orders List</h1>
<table class="minitable">
<tr>
<td>
From Date
</td>
<td>
  <asp:TextBox ID="txtStartDate" runat="server" MaxLength="12"></asp:TextBox>
                     <br />
                    <asp:RequiredFieldValidator ID="RequiredFieldValidator5" runat="server" ErrorMessage="Enter Start Date" ControlToValidate="txtStartDate" Display="Dynamic" ValidationGroup="test2" CssClass="error"></asp:RequiredFieldValidator>
                        <asp:RegularExpressionValidator ID="RegularExpressionValidator1" Display="Dynamic" runat="server" ControlToValidate="txtstartdate"  ValidationGroup="test2" ErrorMessage="Enter Valid date" 
ValidationExpression="^(((0?[1-9]|1[012])/(0?[1-9]|1\d|2[0-8])|(0?[13456789]|1[012])/(29|30)|(0?[13578]|1[02])/31)/(19|[2-9]\d)\d{2}|0?2/29/((19|[2-9]\d)(0[48]|[2468][048]|[13579][26])|(([2468][048]|[3579][26])00)))$" CssClass="error"></asp:RegularExpressionValidator>

</td>
</tr>

<tr>

<td>
To Date
</td>
<td>
  <asp:TextBox ID="txtEndDate" runat="server" MaxLength="12"></asp:TextBox>
                     <br />
                    <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" ErrorMessage="Enter End" ControlToValidate="txtEndDate" Display="Dynamic" ValidationGroup="test2" CssClass="error"></asp:RequiredFieldValidator>
                        <asp:RegularExpressionValidator ID="RegularExpressionValidator2" Display="Dynamic" runat="server" ControlToValidate="txtenddate"  ValidationGroup="test2" ErrorMessage="Enter Valid date" 
ValidationExpression="^(((0?[1-9]|1[012])/(0?[1-9]|1\d|2[0-8])|(0?[13456789]|1[012])/(29|30)|(0?[13578]|1[02])/31)/(19|[2-9]\d)\d{2}|0?2/29/((19|[2-9]\d)(0[48]|[2468][048]|[13579][26])|(([2468][048]|[3579][26])00)))$" CssClass="error"></asp:RegularExpressionValidator>

</td>

</tr>

<tr>
<td>Status</td>
<td>
    <asp:DropDownList ID="ddlStatus" runat="server">
    </asp:DropDownList>
</td>
</tr>

<tr>
<td colspan="2" style="text-align:center;">
    <asp:Button ID="btnView" runat="server" Text="View" onclick="btnView_Click" />
</td>
</tr>
</table>


<asp:Label ID="lblerror" runat="server" Text="" Visible="false"></asp:Label>

<asp:GridView ID="grdReport" runat="server" 
                     AutoGenerateColumns="False" Caption="Orders List"  DataKeyNames="OrderID" CssClass="gridview">
             <Columns>
                <asp:BoundField DataField="ODate" HeaderText="Order Date" DataFormatString="{0:MM/dd/yyyy}">
                    <HeaderStyle HorizontalAlign="Center" />
                    <ItemStyle HorizontalAlign="Center" VerticalAlign="Middle" />
                </asp:BoundField>
                  <asp:BoundField DataField="BillAmount" HeaderText="Bill Amount">
                    <HeaderStyle HorizontalAlign="Center" />
                    <ItemStyle HorizontalAlign="Center" VerticalAlign="Middle" />
                </asp:BoundField>
              
                  <asp:HyperLinkField DataNavigateUrlFields="OrderID" DataNavigateUrlFormatString="AdminOrdersDetails.aspx?OrderID={0}" Text="More Info" HeaderText="More Info">
                    <HeaderStyle HorizontalAlign="Center" />
                    <ItemStyle HorizontalAlign="Center" VerticalAlign="Middle" />
                </asp:HyperLinkField>
            </Columns>
             <PagerStyle CssClass="footerstyle" HorizontalAlign="Center" />
            <SelectedRowStyle BackColor="#330000" Font-Bold="True" ForeColor="White" />
            <HeaderStyle CssClass="headerstyle" />
</asp:GridView>

<table class="minitable">
<tr>
<td>Amount Collected</td>
<td> 
    <asp:Label ID="lblAmountCollected" runat="server" Text="----"></asp:Label>
</td>
</tr>
</table>


</asp:Content>
