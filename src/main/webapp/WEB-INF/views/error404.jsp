<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html lang="en">


<head>

<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/resources/assets/images/favicon.png"
	type="image/x-icon" />
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<script src="https://www.google.com/recaptcha/api.js"></script>
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
 
</head>
<body>

	<div class="inner-slider" id="slider">
		<div class="container"></div>
	</div>
	<div class="bridcrumb">
		<div class="container"></div>
	</div>

	<div class="container" id="main-content">
		<div class="row">
			<div class="col-12 col-sm-12 col-lg-12 " style="text-align: center;">

				<h2 style="text-align: center;">${errorMsg}</h2>

				<a href="${pageContext.request.contextPath}/dashboard"> Click
					Here to Home </a>
			</div>

		</div>

	</div>



</body>
</html>