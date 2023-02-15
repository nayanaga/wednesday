<%@ Page Title="" Language="C#" MasterPageFile="~/WebPages/Admin/Admin.Master" AutoEventWireup="true" CodeBehind="AdminCategoryList.aspx.cs" Inherits="Homemade.WebPages.Admin.AdminCategoryList" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">

</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">

 <h1>Category List</h1>

<table class="minitable">
<tr>
<td style="width:35%;">
  Category
</td>
<td>
  <asp:TextBox ID="txtCategory" runat="server" MaxLength="100"></asp:TextBox>
             <br />
            <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" ErrorMessage="Enter Category" ControlToValidate="txtCategory" ValidationGroup="test" Display="Dynamic" CssClass="error"></asp:RequiredFieldValidator>
</td>
</tr>

  <tr>
        <td colspan="2" style="text-align:center;">
             <asp:Button ID="btnAdd" runat="server" Text="Add" ValidationGroup="test" 
                        ToolTip="Click here to Add" onclick="btnAddNew_Click"  />

        </td>
        </tr>

</table>
    <asp:Label ID="lblerror" runat="server" Text="" CssClass="error"></asp:Label>

<asp:GridView ID="grdCategory" runat="server" 
                     AutoGenerateColumns="False" Caption="Category List"  
        DataKeyNames="ID" CssClass="minitable" 
        onrowdatabound="grdCategory_RowDataBound" 
        onrowdeleting="grdCategory_RowDeleting">
             <Columns>
                <asp:BoundField DataField="Category" HeaderText="Category">
                    <HeaderStyle HorizontalAlign="Center" />
                    <ItemStyle HorizontalAlign="Center" VerticalAlign="Middle" />
                </asp:BoundField>
                 <asp:CommandField ShowDeleteButton="True" />  
            </Columns>
             <PagerStyle CssClass="footerstyle" HorizontalAlign="Center" />
            <SelectedRowStyle BackColor="#330000" Font-Bold="True" ForeColor="White" />
            <HeaderStyle CssClass="headerstyle" />
</asp:GridView>


</asp:Content>
