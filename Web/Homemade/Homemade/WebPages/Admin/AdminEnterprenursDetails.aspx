<%@ Page Title="" Language="C#" MasterPageFile="~/WebPages/Admin/Admin.Master" AutoEventWireup="true" CodeBehind="AdminEnterprenursDetails.aspx.cs" Inherits="Homemade.WebPages.Admin.AdminEnterprenursDetails" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">

</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">



<h1>Enterprenur Details</h1>

<table class="minitable">

 <tr>
                <td style="width:35%">
                  Enterprenur
                </td>
                <td style="width:65%">
                        <asp:Label ID="lblEnterprenur" runat="server" Text="---"></asp:Label>
                </td>
            </tr>

            <tr>
                <td style="width:35%">
                   Owner Person
                </td>
                <td style="width:65%">
                    <asp:Label ID="lblOwnerPerson" runat="server" Text="---"></asp:Label>
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
                <td >
                  Status
                </td>
                <td>
                     <asp:Label ID="lblStatus" runat="server" Text="---"></asp:Label>
                </td>
            </tr>

                  
            <tr>
                <td colspan="2" style="text-align:center;"> 
                   <asp:Button ID="btnStatus" runat="server" Text="Approved" 
                        onclick="btnStatus_Click"/>

              
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
