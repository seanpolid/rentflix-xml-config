<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>RentFlix :: Library</title>
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
					<c:if test="${customer.id != -1}">
						<li><a href="#">Checkout</a></li>
					</c:if>				
					<li><a href="#" class="customer ${customer.profileImg}"></a>
				</ul>
			</nav>
		</header>
		<main>

		</main>
		<footer>
			<p>Created by: Sean Polidori</p>
		</footer>
	</div>
</body>
</html>