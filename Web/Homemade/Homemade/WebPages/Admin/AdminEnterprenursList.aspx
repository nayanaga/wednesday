<%@ Page Title="" Language="C#" MasterPageFile="~/WebPages/Admin/Admin.Master" AutoEventWireup="true" CodeBehind="AdminEnterprenursList.aspx.cs" Inherits="Homemade.WebPages.Admin.AdminEnterprenursList" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">

</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">

<h1>Strores List</h1>



<table class="minitable">
<tr>
<td>Status</td>
<td>
    <asp:DropDownList ID="ddlStatus" runat="server" AutoPostBack="true" 
        onselectedindexchanged="ddlStatus_SelectedIndexChanged" >
    </asp:DropDownList>

</td>
</tr>

</table>

    <asp:Label ID="lblerror" runat="server" Text="" Visible="false"></asp:Label>

<asp:GridView ID="grdEnterprenurs" runat="server" 
                     AutoGenerateColumns="False" Caption="Enterprenurs List"  DataKeyNames="ID" CssClass="gridview">
             <Columns>
                <asp:BoundField DataField="Entrepreneur" HeaderText="Entrepreneur">
                    <HeaderStyle HorizontalAlign="Center" />
                    <ItemStyle HorizontalAlign="Center" VerticalAlign="Middle" />
                </asp:BoundField>
                  <asp:BoundField DataField="OwnerName" HeaderText="Owner Person">
                    <HeaderStyle HorizontalAlign="Center" />
                    <ItemStyle HorizontalAlign="Center" VerticalAlign="Middle" />
                </asp:BoundField>
              
                 <asp:BoundField DataField="Mobile" HeaderText="Mobile">
                    <HeaderStyle HorizontalAlign="Center" />
                    <ItemStyle HorizontalAlign="Center" VerticalAlign="Middle" />
                </asp:BoundField>
                  <asp:HyperLinkField DataNavigateUrlFields="ID" DataNavigateUrlFormatString="AdminEnterprenursDetails.aspx?ID={0}" Text="More Info" HeaderText="More Info">
                    <HeaderStyle HorizontalAlign="Center" />
                    <ItemStyle HorizontalAlign="Center" VerticalAlign="Middle" />
                </asp:HyperLinkField>
            </Columns>
             <PagerStyle CssClass="footerstyle" HorizontalAlign="Center" />
            <SelectedRowStyle BackColor="#330000" Font-Bold="True" ForeColor="White" />
            <HeaderStyle CssClass="headerstyle" />
</asp:GridView>


</asp:Content>
