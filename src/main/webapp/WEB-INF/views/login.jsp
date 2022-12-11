<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>RentFlix :: Login</title>
	<link rel="preconnect" href="https://fonts.googleapis.com" />
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
	<link href="https://fonts.googleapis.com/css2?family=Fuzzy+Bubbles:wght@400;700&display=swap" rel="stylesheet" />
	<link rel="stylesheet" href="./resources/css/main.css" />
</head>
<body>
	<div class="container">
		<header>
			<nav>
				<a href="./home">RentFlix</a>
				<ul>
					<li><a href="#">Browse</a></li>
					<li><a href="./login" class="selected">Login</a></li>
				</ul>
			</nav>
		</header>
		<main>
			<ul class="customers">
				<li>
					<a href="./loginCustomer?customerId=-1" class="customerPic astronaught"></a>
					<a href="./loginCustomer?customerId=-1" class="customerName">Admin</a>
				</li>
				<c:forEach var="customer" items="${customers}">
					<li>
						<c:set var="customerLoginLink" value="./loginCustomer?customerId=${customer.id}" />
						<a href="${customerLoginLink}" class="customerPic ${customer.profileImg}"></a>
						<a href="${customerLoginLink}" class="customerName">${customer.firstName}</a>
					</li>
				</c:forEach>
				<li>
					<a href="#" class="customerPic addCustomer"></a>
					<a href="#" class="customerName">Add Customer</a>
				</li>
			</ul>
		</main>
		<footer>
			<p>Created by: Sean Polidori</p>
		</footer>
	</div>
</body>
</html>