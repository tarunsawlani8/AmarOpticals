<%@page import="com.opticals.model.Orders"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width">
<title>Amar Opticals | Eyeglasses, Sunglasses & Contact Lens</title>
<link rel="stylesheet" href="style.css">
<link rel="icon" href="./images/titleimage.jpg"  type="image/gif" size="16x16"/>
</head>
<body>
<header>
<div class="container"></div>
<div id="branding">
<h1><a href="login.jsp">AmarOpticals<span class="higlight">.in</span></a></h1>
</div>
<nav><ul>
<li><a href="#">Men</a></li>
<li><a href="#">Women</a></li>
<li><a href="#">Categories</a></li>
<li align="right"><a href="loginApp.jsp">Login</a></li>
</ul>


</nav>
</header>

<!-- <section id="showcase">
<div class="container">
<h1>Affordable Spectales & Goggles</h1>
<p>Wide variety of spectales, lenses and goggles</p>
</div>
</section> -->
<section id="showcase" class="row">
<div id="cf" class="col">
  <img class="top" src="./images/800.jpg" alt="offer1" />
  <img class="bottom" src="./images/nosepick.jpg" alt="offer2" />
</div>
<div class="col container">
<h1>Track Your Order Here</h1>
<%if ("success".equalsIgnoreCase((String) request.getAttribute("actionStatus"))) {
Orders orderInfo =(Orders)request.getAttribute("orderObj");
	%>
	<p class="alert">Your order id <span style="font-style: italic;"><%=orderInfo.getOrderId()%></span> 
	with status <span style="font-style: italic;"><%=orderInfo.getDeliveryStatus()%>
	 with expected Delivery Date <span style="font-style: italic;"><%=orderInfo.getDeliveryDate()%>
	 holding Pending Amount
	<span style="font-style: italic;"><%=orderInfo.getPendingAmount()%>
	</p>
	
<% }else if ("failure".equalsIgnoreCase((String) request.getAttribute("actionStatus"))) { %>
<p class="alert">No Records Found for given Order Id</p>
<%} if ( request.getAttribute("message") != null) {

	%>
	<p class="alert"> <span style="font-style: italic;"><%=(String)request.getAttribute("message")%></span> 
	</p>
	
<% } %>


<form method="POST" action="recordController">
<input type="hidden" name="pageAction" value="getOrderDetails">
<input  type="text" class="boxes" name="orderId" placeholder="Enter your Order Id" maxlength="20"/>
<input class="button" type="submit" value="Submit"/>
</form>
</div>
</section>
<section id="showcase2" class="row">
<div >
 <img class="col2" src="./images/crizal_big.png" alt="offer1" />
<div  class="col2">
<br/>
<p>We provide huge variety of branded spectales, aviators, wayfarers and contact lenses</p>
</div>
  <img  class="col2"  src="./images/contact_lens.jpg" alt="offer2" />
  </div>
</section>
<footer>Amar Opticals Copyright &copy; 2018</footer>
</body>
</html>