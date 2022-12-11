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
					<li><a href="./login" class="selected">Login</a></li>
					<li><a href="#">Browse</a></li>
					<li><a href="#">Checkout</a></li>
					<!--<li><a href="#" class="user"></a>-->
				</ul>
			</nav>
		</header>
		<main>
			<ul class="users">
				<li>
					<a href="#" class="userPic pic1"></a>
					<a href="#" class="userName">Admin</a>
				</li>
				<li>
					<a href="#" class="userPic pic2"></a>
					<a href="#" class="userName">SeanP</a>
				</li>
				<li>
					<a href="#" class="userPic addUser"></a>
					<a href="#" class="userName">Add user</a>
				</li>
			</ul>
		</main>
		<footer>
			<p>Created by: Sean Polidori</p>
		</footer>
	</div>
</body>
</html>