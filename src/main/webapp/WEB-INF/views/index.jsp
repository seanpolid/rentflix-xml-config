<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
	<title>RentFlix :: Home</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width" />
	<link rel="preconnect" href="https://fonts.googleapis.com" />
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
	<link href="https://fonts.googleapis.com/css2?family=Fuzzy+Bubbles:wght@400;700&display=swap" rel="stylesheet" />
	<link rel="stylesheet" href="./resources/css/main.css" />
</head>
<body>
	<div class="container">
		<header>
			<nav class="homeNav">
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
			<h1>RentFlix</h1>
			<form method="post" action="#" class="signUp">
				<input type="text" placeholder="Enter your email" name="email" />
				<input type="submit" name="submit" value="Sign Up"/>
			</form>
		</main>
		<footer>
			<p>Created by: Sean Polidori</p>
		</footer>
	</div>
	<script src="./resources/js/user-nav.js"></script>
</body>
</html>