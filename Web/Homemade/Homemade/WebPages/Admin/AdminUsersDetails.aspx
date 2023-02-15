<%@ Page Title="" Language="C#" MasterPageFile="~/WebPages/Admin/Admin.Master" AutoEventWireup="true" CodeBehind="AdminUsersDetails.aspx.cs" Inherits="Homemade.WebPages.Admin.AdminUsersDetails" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">

<h1>User Details</h1>

<table class="minitable">

 <tr>
                <td style="width:35%">
                   User Name
                </td>
                <td style="width:65%">
                        <asp:Label ID="lblUserName" runat="server" Text="---"></asp:Label>
                </td>
            </tr>

          
            <tr>
                <td >
                   Address Line1
                </td>
                <td>
                  <asp:Label ID="lblAddressLine1" runat="server" Text="---"></asp:Label>
                </td>
            </tr>

               <tr>
                <td >
                   Address Line2
                </td>
                <td>
                  <asp:Label ID="lblAddressLine2" runat="server" Text="---"></asp:Label>
                  
                </td>
            </tr>


             <tr>
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
</tr>



             <tr>
                <td >
                   Mobile
                </td>
                <td>
                    <asp:Label ID="lblMobile" runat="server" Text="---"></asp:Label>

                </td>
            </tr>
            <tr>
                <td >
                   Email ID
                </td>
                <td>
                      <asp:Label ID="lblEmailID" runat="server" Text="---"></asp:Label>
                 
                </td>
            </tr>
       
                  
            <tr>
                <td colspan="2" style="text-align:center;"> 
                                 <asp:Button ID="btnCancel" runat="server" Text="Cancel" 
                        onclick="btnCancel_Click"/>

                    <asp:Button ID="btnDelete" runat="server" Text="Delete"  
                       OnClientClick="javascript:return confirm('Are you sure you want Delete?');" 
                        onclick="btnDelete_Click"/>
                    <br /> 
                    <asp:Label ID="lblerror" runat="server" Text="Label" Visible="False"  ></asp:Label>
                </td>
           </tr>
       </table>

       <asp:Label ID="lblID" runat="server" Text="" Visible="False"  ></asp:Label>
</asp:Content>
