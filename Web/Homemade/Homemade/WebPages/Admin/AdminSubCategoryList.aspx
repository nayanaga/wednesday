<%@ Page Title="" Language="C#" MasterPageFile="~/WebPages/Admin/Admin.Master" AutoEventWireup="true" CodeBehind="AdminSubCategoryList.aspx.cs" Inherits="Homemade.WebPages.Admin.AdminSubCategoryList" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">

</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">



<h1>Sub Category List</h1>

 
<table class="minitable">
<tr>
<td>Category</td>
<td> 
    <asp:DropDownList ID="ddlCategory" runat="server">
    </asp:DropDownList>
</td>
</tr>
<tr>
<td style="width:35%;">
   Sub Category
</td>
<td>
  <asp:TextBox ID="txtSubCategory" runat="server" MaxLength="100"></asp:TextBox>
             <br />
            <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" ErrorMessage="Enter Sub Category" ControlToValidate="txtSubCategory" ValidationGroup="test" Display="Dynamic" CssClass="error"></asp:RequiredFieldValidator>
</td>
</tr>

  <tr>
        <td colspan="2" style="text-align:center;">
             <asp:Button ID="btnAdd" runat="server" Text="Add" ValidationGroup="test" 
                        ToolTip="Click here to Add" onclick="btnAdd_Click"   />
                 
        </td>
        </tr>

</table>

<table class="minitable">
<tr>
<td>Category</td>
<td>
 <asp:DropDownList ID="ddlSearchCategory" runat="server" AutoPostBack="true" 
        onselectedindexchanged="ddlSearchCategory_SelectedIndexChanged">
    </asp:DropDownList>
</td>
</tr>
</table>

    <asp:Label ID="lblerror" runat="server" Text="" CssClass="error"></asp:Label>

<asp:GridView ID="grdSubCategory" runat="server" 
                     AutoGenerateColumns="False" Caption="Sub Category List"  
        DataKeyNames="ID" CssClass="minitable" 
    onrowdatabound="grdSubCategory_RowDataBound" 
    onrowdeleting="grdSubCategory_RowDeleting">
             <Columns>
                <asp:BoundField DataField="SubCategory" HeaderText="Sub Category">
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
