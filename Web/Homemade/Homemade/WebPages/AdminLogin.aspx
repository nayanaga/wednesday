<%@ Page Title="" Language="C#" MasterPageFile="~/WebPages/User.Master" AutoEventWireup="true" CodeBehind="AdminLogin.aspx.cs" Inherits="Homemade.WebPages.AdminLogin" %>


<asp:Content ID="Content1" ContentPlaceHolderID="menucontent" runat="server">

 <ul class="lavaLampWithImage" id="lava_menu">
          <li class="current"><a href="Index.aspx">Home</a></li>
          <li><a href="Profile.aspx">Our Profile</a></li>
          <li><a href="AdminLogin.aspx">Admin</a></li>
        </ul>

</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">

<h1>Admin Login</h1>

<table class="minitable">
                    <tr>
                        <td style="width:30%">
                            User ID
                        </td>
                        <td>
                            <asp:TextBox ID="txtUserID" runat="server"></asp:TextBox>
                       <br />
                            <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" ErrorMessage="Enter User ID" ControlToValidate="txtUserID"  Display="Dynamic" ValidationGroup="test"></asp:RequiredFieldValidator></td>
                    </tr>
                        
                    <tr>
                        <td>
                           Password
                        </td>
                        <td>
                            <asp:TextBox ID="txtPassword" runat="server" TextMode="Password"></asp:TextBox>
                      <br />
                            <asp:RequiredFieldValidator ID="RequiredFieldValidator2" runat="server" ErrorMessage="Enter Password" ControlToValidate="txtPassword"  Display="Dynamic" ValidationGroup="test"></asp:RequiredFieldValidator>
                        </td>
                    </tr>
                   
                    <tr>
                      <td colspan="2" style="text-align:center;">
                          <asp:Button ID="btnLogin" runat="server" Text="Login" 
                              ValidationGroup="test" onclick="btnLogin_Click"/></td>
                    </tr>
                    
                       <tr>
                        <td colspan="2" style="text-align:center;">
                                                 
                           <asp:Label ID="lblerror" runat="server" Text="---" Visible="false" ></asp:Label>
                        </td>
                    </tr>


            </table>

</asp:Content>