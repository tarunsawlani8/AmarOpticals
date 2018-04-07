<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width intial-scale=1.0">
<title>Amar Opticals | Eyeglasses, Sunglasses & Contact Lens</title>
<link rel="stylesheet" href="style.css">
<link rel="icon" href="./images/titleimage.png" type="image/gif" sizes="16x16"/>
</head>
<body>
<header>
<div class="container"></div>
<div id="branding">
<h1><a href="login.jsp">AmarOpticals<span class="higlight">.in</span></a></h1>
</div>
<nav><ul>
<li><a class="current"href="#">Orders</a></li>
<li><a href="#">Sales</a></li>
<li><a href="#">Reports</a></li>
<% 
if (session != null) {
String userName =(String)session.getAttribute("userName");
if (StringUtils.isNotBlank(userName))
{%>
<li ><a href="#">Hello, <%= userName %></a></li>
<li ><a href="logout.jsp">Logout</a></li>
<% }} else {%>
<li ><a href="loginApp.jsp">Login</a></li>
</ul>
<%} %>

</nav>
</header>

<section id="showcase2">
<div>
<% 
String error =  (String)session.getAttribute("actionStatus");


if ( session.getAttribute("message") != null) {

	%>
	<p class="alert"><span style="font-style: italic;"><%=(String)session.getAttribute("message")%></span> 
	</p>
	
<% } %>
<form action="recordController" method="POST">
<input type="hidden" name="pageAction" value="insertOrders" />
Order Id :<input type="text" name="orderId" maxlength="20" />
Customer Name :<input type="text" name="customerName" maxlength="20"/>
Customer Contact :<input type="text" name="customerContact" maxlength="10" />
Delivery Status :<select name="deliveryStatus" maxlength="20" >
<option selected="selected" value="NOT STARTED">NOT STARTED</option>
<option  value="IN PROGRESS">IN PROGRESS</option>
<option  value="READY FOR PICKUP">READY FOR PICKUP</option>
<option  value="DELIVERED">DELIVERED</option>
</select>
Delivery Date :<input type="text" name="deliveryDate"  maxlength="10"/>
Pending Amount : <input type="text" name="pendingAmount"  maxlength="10"/>

<input type="submit" value="Submit"></input>
</form>
</div>

<footer>Amar Opticals Copyright &copy; 2018</footer>
</body>
</html>
<body>

</body>
</html>