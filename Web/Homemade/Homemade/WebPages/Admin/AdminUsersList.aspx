<%@ Page Title="" Language="C#" MasterPageFile="~/WebPages/Admin/Admin.Master" AutoEventWireup="true" CodeBehind="AdminUsersList.aspx.cs" Inherits="Homemade.WebPages.Admin.AdminUsersList" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">

    <asp:Label ID="lblerror" runat="server" Text="" Visible="false"></asp:Label>

<asp:GridView ID="grdUsers" runat="server" 
                     AutoGenerateColumns="False" Caption="User List"  DataKeyNames="ID" CssClass="gridview">
             <Columns>
                <asp:BoundField DataField="UserName" HeaderText="User Name">
                    <HeaderStyle HorizontalAlign="Center" />
                    <ItemStyle HorizontalAlign="Center" VerticalAlign="Middle" />
                </asp:BoundField>
               
                
                 <asp:BoundField DataField="Mobile" HeaderText="Mobile">
                    <HeaderStyle HorizontalAlign="Center" />
                    <ItemStyle HorizontalAlign="Center" VerticalAlign="Middle" />
                </asp:BoundField>
                  <asp:HyperLinkField DataNavigateUrlFields="ID" DataNavigateUrlFormatString="AdminUsersDetails.aspx?ID={0}" Text="More Info" HeaderText="More Info">
                    <HeaderStyle HorizontalAlign="Center" />
                    <ItemStyle HorizontalAlign="Center" VerticalAlign="Middle" />
                </asp:HyperLinkField>
            </Columns>
             <PagerStyle CssClass="footerstyle" HorizontalAlign="Center" />
            <SelectedRowStyle BackColor="#330000" Font-Bold="True" ForeColor="White" />
            <HeaderStyle CssClass="headerstyle" />
</asp:GridView>


</asp:Content>
