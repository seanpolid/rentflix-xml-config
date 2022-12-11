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
					<li><a href="./browse">Browse</a></li>
					<c:choose>
						<c:when test="${customer.id != null}">
							<c:if test="${customer.id != -1}">
								<li><a href="#">Checkout</a></li>
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
		<main>
			<form:form action="./addMovie" method="post" modelAttribute="movie" class="movieForm">
				<div class="movieCover"></div>
				<form:label path="name">Name:</form:label>
				<form:input path="name" />
				
				<form:label path="yearMade">Year made:</form:label>
				<form:input path="yearMade" />
				
				<form:label path="releaseDate">Release date:</form:label>
				<form:input path="releaseDate" type="date" />
				
				<form:label path="length">Length:</form:label>
				<form:input path="length" />
				
				<form:label path="rating">Rating:</form:label>
				<form:select path="rating.name">
					<c:forEach var="rating" items="${ratings}">
						<form:option value="${rating.name}"></form:option>
					</c:forEach>
				</form:select>
				
				<form:label path="cost">Cost:</form:label>
				<form:input path="cost" />
				
				<form:label path="totalCopies">Total copies:</form:label>
				<form:input path="totalCopies" />
				
				<form:label path="genre">Genre:</form:label>
				<form:select path="genre.name">
					<c:forEach var="genre" items="${genres}">
						<form:option value="${genre.name}"></form:option>
					</c:forEach>
				</form:select>
				
				<form:label path="description">Description:</form:label>
				<form:textarea path="description" rows="7"/>
				
				<input type="submit" value="Save movie" />
			</form:form>
		</main>
		<footer>
			<p>Created by: Sean Polidori</p>
		</footer>
	</div>
	<script src="./resources/js/user-nav.js"></script>
</body>
</html>