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
								<li><a href="./checkout" class="checkout">Checkout</a></li>
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
		<main class="library">
			<section>
				<div class="options">
					<c:choose>
						<c:when test="${libraryType == 'current'}">
							<a href="./library?libraryType=current" class="selected">Current</a>
							<a href="./library?libraryType=previous">Previous</a>
						</c:when>
						<c:otherwise>
							<a href="./library?libraryType=current">Current</a>
							<a href="./library?libraryType=previous" class="selected">Previous</a>
						</c:otherwise>
					</c:choose>
				</div>
				
				
				<table class="movieContainer">
					<tr>
						<th class="imageCol"></th>
						<th class="itemCol">Item</th>
						<c:choose>
							<c:when test="${libraryType == 'current'}">
								<th class="returnCol">Return</th>
							</c:when>
							<c:otherwise>
								<th class="returnCol">Returned</th>
							</c:otherwise>
						</c:choose>
						
					</tr>
					<c:forEach var="invoice" items="${invoices}">
						<c:forEach var="invoiceMovie" items="${invoice.invoiceMovies}">
							<c:choose>
								<c:when test="${libraryType == 'current'}">
									<c:if test="${invoiceMovie.returnDate == null}">
										<c:set var="movie" value="${invoiceMovie.movie}" />
										<tr>
											<td><a href="#" data-name="${movie.name}" class="movieCover"></a></td>
											<td><a href="#" data-name="${movie.name}" class="movieName">${movie.name}</a></td>
											<td class="returnCol">
												<input type="checkbox" name="return" class="checkbox" data-movieId="${movie.id}" data-invoiceMovieId="${invoiceMovie.id}" />
											</td>
										</tr>
									</c:if>
								</c:when>
								<c:otherwise>
									<c:if test="${invoiceMovie.returnDate != null}">
										<c:set var="movie" value="${invoiceMovie.movie}" />
										<tr>
											<td><a href="#" data-name="${movie.name}" class="movieCover"></a></td>
											<td><a href="#" data-name="${movie.name}" class="movieName">${movie.name}</a></td>
											<td class="returnCol">${invoiceMovie.returnDate}</td>
										</tr>
									</c:if>
								</c:otherwise>
							</c:choose>
						</c:forEach> 
					</c:forEach>
				</table>
			</section>
			<c:if test="${libraryType == 'current'}">
				<input type="submit" value="Save changes" />
			</c:if>
		</main>
		<footer>
			<p>Created by: Sean Polidori</p>
		</footer>
	</div>
	<script src="./resources/js/user-nav.js"></script>
	<script src="./resources/js/movie-info.js"></script>
	<script src="./resources/js/return-movies.js"></script>
</body>
</html>