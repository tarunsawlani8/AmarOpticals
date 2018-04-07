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
<h1><a href="login.html">AmarOpticals<span class="higlight">.in</span></a></h1>
</div>
<nav><ul>
<li><a href="#">Men</a></li>
<li><a href="#">Women</a></li>
<li><a href="#">Categories</a></li>
<li ><a href="loginApp.jsp">Login</a></li>
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
  <img class="top" src="./images/99.jpg" alt="offer1" />
  <img class="bottom" src="./images/framefree.jpg" alt="offer2" />
</div>
<div class="col container">
<h1>Track Your Order Here</h1>
<form method="POST" action="getSatus">
<input  type="text" class="boxes" name="orderId" placeholder="Enter your Order Id" maxlength="20"/>
<input class="button" type="submit" value="Submit"/>
</form>
</div>
</section>
<section id="showcase2">
<div>
<% 
String error = (String) request.getAttribute("message");


if (error != null) {%>
<div class="alert"><%= error%></div>
<%} else%>
<form action="loginApp" method="POST">
User Name :<input class="text" name="userName" />
Password :<input type="password" name="password" />
<input type="submit" value="Submit"></input>
</form>
</div>

<footer>Amar Opticals Copyright &copy; 2018</footer>
</body>
</html>
<body>

</body>
</html>