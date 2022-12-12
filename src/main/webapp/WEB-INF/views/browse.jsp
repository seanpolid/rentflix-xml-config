<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>RentFlix :: Browse</title>
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
					<li><a href="./browse" class="selected">Browse</a></li>
					<c:choose>
						<c:when test="${customer.id != null}">
							<c:if test="${customer.id != -1}">
								<li><a href="./checkout" class="checkout">Checkout</a></li>
								<li><a href="#" class="customer ${customer.profileImg}"></a>
							</c:if>				
							<c:if test="${customer.id == -1}">
								<li><a href="#" class="customer ${customer.profileImg} admin"></a>
							</c:if>
						</c:when>
						<c:otherwise>
							<li><a href="./login">Login</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</nav>
		</header>
		<main class="browse">
			<div class="search">
				<input type="search" name="search" /> 
				<input type="submit" value="Search" />
			</div>
			<ul class="movieContainer">
				<c:forEach var="movie" items="${movies}">
				<li>
					<a href="#" data-name="${movie.name}" class="movieCover"></a>
					<a href="#" data-name="${movie.name}" class="movieName">${movie.name}</a>
				</li>
			</c:forEach>
			</ul>
		</main>
		<footer>
			<p>Created by: Sean Polidori</p>
		</footer>
	</div>
	<script src="./resources/js/user-nav.js"></script>
	<script src="./resources/js/movie-info.js"></script>
	<script src="./resources/js/search.js"></script>
</body>
</html>