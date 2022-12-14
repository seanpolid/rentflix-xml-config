<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>RentFlix :: Checkout</title>
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
					<li><a href="./browse">Browse</a></li>
					<c:choose>
						<c:when test="${customer.id != null}">
							<c:if test="${customer.id != -1}">
								<li><a href="./checkout" class="checkout selected">Checkout</a></li>
								<li><a href="#" class="customer ${customer.profileImg}"></a>
							</c:if>				
							<c:if test="${customer.id == -1}">
								<li><a href="#" class="customer ${customer.profileImg} admin"></a>
							</c:if>
						</c:when>
					</c:choose>
				</ul>
			</nav>
		</header>
		<main class="checkout">
			<section class="cart">
				<h1>Cart:</h1>
				
				<table class="movieContainer">
					<tr>
						<th class="imageCol"></th>
						<th class="itemCol">Item</th>
						<th class="priceCol">Price</th>
					</tr>
					<c:set var="totalCost" value="0" />
					<c:forEach var="movie" items="${movies}">
						<tr>
							<td><a href="#" data-name="${movie.name}" class="movieCover"></a></td>
							<td><a href="#" data-name="${movie.name}" class="movieName">${movie.name}</a></td>
							<td class="priceCol">${movie.cost}</td>
						</tr>
						<c:set var="totalCost" value="${totalCost + movie.cost}" />
					</c:forEach>
					<c:if test="${totalCost != 0}">
						<tr>
							<td class="totalRow"></td>
							<td class="totalRow"></td>
							<td class="priceCol totalRow">${totalCost}</td>
						</tr>
					</c:if>
				</table>
				
				<form method="post" action="./checkout">
					<input type="submit" value="Checkout" />
				</form>
			</section>
		</main>
		<footer>
			<p>Created by: Sean Polidori</p>
		</footer>
	</div>
	<script src="./resources/js/user-nav.js"></script>
	<script src="./resources/js/movie-info.js"></script>
</body>
</html>