<%@ Page Title="" Language="C#" MasterPageFile="~/WebPages/Admin/Admin.Master" AutoEventWireup="true" CodeBehind="AdminOrdersDetails.aspx.cs" Inherits="Homemade.WebPages.Admin.AdminOrdersDetails" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">


<h1>Order Details</h1>

<table class="bigtable">
<tr>
<td colspan="4" style="text-align:center;">
Order Details
</td>
</tr>

<tr>
<td>
Order Date
</td>
<td>
    <asp:Label ID="lblODate" runat="server" Text="Label"></asp:Label>
</td>

<td>
Bill Amount
</td>
<td>
    <asp:Label ID="lblBillAmount" runat="server" Text="Label"></asp:Label>
</td>
</tr>

<tr>
<td>
Status
</td>
<td>
    <asp:Label ID="lblStatus" runat="server" Text="Label"></asp:Label>
</td>
<td>
Order ID
</td>
<td>
    <asp:Label ID="lblOrderID" runat="server" Text="Label"></asp:Label>
</td>
</tr>


<tr>
<td colspan="4" style="text-align:center;">
    <asp:Button ID="btnBack" runat="server" Text="Back" onclick="btnBack_Click" />

    <asp:Button ID="btnDelete" runat="server" Text="Delete" 
        onclick="btnDelete_Click"  />
</td>
</tr>
</table>


<asp:Label ID="lblerror" runat="server" Text="" Visible="false"></asp:Label>

<asp:GridView ID="grdOrderItems" runat="server" 
                     AutoGenerateColumns="False" Caption="Items List"  DataKeyNames="ID" CssClass="gridview">
             <Columns>
                <asp:BoundField DataField="Item" HeaderText="Item">
                    <HeaderStyle HorizontalAlign="Center" />
                    <ItemStyle HorizontalAlign="Center" VerticalAlign="Middle" />
                </asp:BoundField>
                  <asp:BoundField DataField="Price" HeaderText="Price">
                    <HeaderStyle HorizontalAlign="Center" />
                    <ItemStyle HorizontalAlign="Center" VerticalAlign="Middle" />
                </asp:BoundField>
                    <asp:BoundField DataField="Qty" HeaderText="Qty">
                    <HeaderStyle HorizontalAlign="Center" />
                    <ItemStyle HorizontalAlign="Center" VerticalAlign="Middle" />
                </asp:BoundField>
                    <asp:BoundField DataField="Amount" HeaderText="Amount">
                    <HeaderStyle HorizontalAlign="Center" />
                    <ItemStyle HorizontalAlign="Center" VerticalAlign="Middle" />
                </asp:BoundField>
            </Columns>
             <PagerStyle CssClass="footerstyle" HorizontalAlign="Center" />
            <SelectedRowStyle BackColor="#330000" Font-Bold="True" ForeColor="White" />
            <HeaderStyle CssClass="headerstyle" />
</asp:GridView>


<table class="bigtable">
<tr>
<td colspan="4" style="text-align:center;">
User Details
</td>
</tr>

<tr>
<td>
User Name
</td>
<td>
    <asp:Label ID="lblUserName" runat="server" Text="---"></asp:Label>
</td>

<td>
AddressLine1
</td>
<td>
    <asp:Label ID="lblAddressLine1" runat="server" Text="---"></asp:Label>
</td>
</tr>

<tr>
<td>
AddressLine2
</td>
<td>
    <asp:Label ID="lblAddressLine2" runat="server" Text="---"></asp:Label>
</td>
<td>
Taluk
</td>
<td>
    <asp:Label ID="lblTaluk" runat="server" Text="---"></asp:Label>
</td>
</tr>


<tr>
<td>
District
</td>
<td>
    <asp:Label ID="lblDistrict" runat="server" Text="---"></asp:Label>
</td>
<td>
Mobile
</td>
<td>
    <asp:Label ID="lblMobile" runat="server" Text="---"></asp:Label>
</td>
</tr>

<tr>
<td>
Email ID
</td>
<td>
    <asp:Label ID="lblEmailID" runat="server" Text="---"></asp:Label>
</td>
</tr>



<tr>
<td colspan="4" style="text-align:center;">
Store Details
</td>
</tr>

<tr>
<td>
Store Name
</td>
<td>
    <asp:Label ID="lblStoreName" runat="server" Text="---"></asp:Label>
</td>

<td>
Contact Person
</td>
<td>
    <asp:Label ID="lblContactPerson" runat="server" Text="---"></asp:Label>
</td>
</tr>

<tr>
<td>
AddressLine1
</td>
<td>
    <asp:Label ID="lblSAddressLine1" runat="server" Text="---"></asp:Label>
</td>
<td>
AddressLine2
</td>
<td>
    <asp:Label ID="lblSAddressLine2" runat="server" Text="---"></asp:Label>
</td>
</tr>


<tr>
<td>
Taluk
</td>
<td>
    <asp:Label ID="lblSTaluk" runat="server" Text="---"></asp:Label>
</td>
<td>
District
</td>
<td>
    <asp:Label ID="lblSDistrict" runat="server" Text="---"></asp:Label>
</td>
</tr>

<tr>
<td>
Mobile
</td>
<td>
    <asp:Label ID="lblSMobile" runat="server" Text="---"></asp:Label>
</td>

<td>
Email ID
</td>
<td>
    <asp:Label ID="lblSEmailID" runat="server" Text="---"></asp:Label>
</td>

</tr>
</table>



</asp:Content>
