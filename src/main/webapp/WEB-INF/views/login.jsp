<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html class=" ">
<head>
<!-- 
         * @Package: Complete Admin - Responsive Theme
         * @Subpackage: Bootstrap
         * @Version: 2.2
         * This file is part of Complete Admin Theme.
        -->
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<meta charset="utf-8" />
<title>RUSA Admin : Login Page</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta content="" name="description" />
<meta content="" name="author" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/resources/assets/images/favicon.png"
	type="image/x-icon" />
<!-- Favicon -->
<link rel="apple-touch-icon-precomposed"
	href="${pageContext.request.contextPath}/resources/assets/images/apple-touch-icon-57-precomposed.png">
<!-- For iPhone -->
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="${pageContext.request.contextPath}/resources/assets/images/apple-touch-icon-114-precomposed.png">
<!-- For iPhone 4 Retina display -->
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="${pageContext.request.contextPath}/resources/assets/images/apple-touch-icon-72-precomposed.png">
<!-- For iPad -->
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="${pageContext.request.contextPath}/resources/assets/images/apple-touch-icon-144-precomposed.png">
<!-- For iPad Retina display -->




<!-- CORE CSS FRAMEWORK - START -->
<link
	href="${pageContext.request.contextPath}/resources/assets/plugins/pace/pace-theme-flash.css"
	rel="stylesheet" type="text/css" media="screen" />
<link
	href="${pageContext.request.contextPath}/resources/assets/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/resources/assets/plugins/bootstrap/css/bootstrap-theme.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/resources/assets/fonts/font-awesome/css/font-awesome.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/resources/assets/css/animate.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/resources/assets/plugins/perfect-scrollbar/perfect-scrollbar.css"
	rel="stylesheet" type="text/css" />
<!-- CORE CSS FRAMEWORK - END -->

<!-- HEADER SCRIPTS INCLUDED ON THIS PAGE - START -->


<link
	href="${pageContext.request.contextPath}/resources/assets/plugins/icheck/skins/all.css"
	rel="stylesheet" type="text/css" media="screen" />

<!-- HEADER SCRIPTS INCLUDED ON THIS PAGE - END -->


<!-- CORE CSS TEMPLATE - START -->
<link
	href="${pageContext.request.contextPath}/resources/assets/css/style.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/resources/assets/css/responsive.css"
	rel="stylesheet" type="text/css" />
<!-- CORE CSS TEMPLATE - END -->

</head>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body class=" login_page">

	<div class="container-fluid">
		<div class="login-wrapper row">
			<div id="login"
				class="login loginpage col-lg-offset-4 col-md-offset-3 col-sm-offset-3 col-xs-offset-0 col-xs-12 col-sm-6 col-lg-4">
				<h1>
					<a href="#" title="Login Page" tabindex="-1">Complete Admin</a>
				</h1>

				<form name="loginform" id="loginform"
					action="${pageContext.request.contextPath}/loginProcess"
					method="post">

					<c:choose>
						<c:when test="${msg!=null}">
							<div class="alert alert-error alert-dismissible fade in">
								<button type="button" class="close" data-dismiss="alert"
									aria-label="Close">
									<span aria-hidden="true">×</span>
								</button>
								<strong>${msg}</strong>
							</div>
						</c:when>
					</c:choose>

					<p>
						<label for="user_login">Username<br /> <input type="text"
							name="username" id="username" placeholder="Username"
							class="input" autocomplete="off" size="20" required /></label>
					</p>
					<p>
						<label for="user_pass">Password<br /> <input
							type="password" name="password" placeholder="Password"
							id="password" class="input" autocomplete="off" size="20" required /></label>
					</p>

					<div class="capcha-img">
						<img src="${pageContext.request.contextPath }/captcha"
							id='captchaImage'>
						<button id="captchaRef" type="button" class="repeat-btn">
							<div class="repeat">
								<img
									src="${pageContext.request.contextPath}/resources/assets/images/repeat.png"
									alt="Repeat" class="img-responsive" title="Repeat"
									height="10px" width="10px">
							</div>
						</button>
					</div>

					<br>
					<p>
						<label for="user_pass">Enter Text : <br /> <input
							type="text" class="form-control capcha-input" name="captcha"
							id="captcha" placeholder="Enter Text" autocomplete="off" required></label>
					</p>
					<!--  <p class="forgetmenot">
                    <label class="icheck-label form-label" for="rememberme"><input name="rememberme" type="checkbox" id="rememberme" value="forever" class="icheck-minimal-aero" checked> Remember me</label>
                </p>
 -->


					<p class="submit">
						<input type="submit" name="wp-submit" id="wp-submit"
							class="btn btn-accent btn-block" value="Sign In" />

					</p>
				</form>

				<!--  <p id="nav">
                <a class="pull-left" href="#" title="Password Lost and Found">Forgot password?</a>
                <a class="pull-right" href="ui-register.html" title="Sign Up">Sign Up</a>
            </p> -->


			</div>
		</div>
	</div>




	<!-- MAIN CONTENT AREA ENDS -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->


	<!-- CORE JS FRAMEWORK - START -->
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.min.js" type="text/javascript"></script> 

	<script
		src="${pageContext.request.contextPath}/resources/assets/js/jquery.easing.min.js"
		type="text/javascript"></script>
 <script src="${pageContext.request.contextPath}/resources/assets/js/popper.min.js" type="text/javascript"></script> 
 
	  <script src="${pageContext.request.contextPath}/resources/assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script> 
	<script
		src="${pageContext.request.contextPath}/resources/assets/plugins/pace/pace.min.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/plugins/perfect-scrollbar/perfect-scrollbar.min.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/plugins/viewport/viewportchecker.js"
		type="text/javascript"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="../assets/js/jquery-1.11.2.min.js"><\/script>');
	</script>
	<!-- CORE JS FRAMEWORK - END -->


	<!-- OTHER SCRIPTS INCLUDED ON THIS PAGE - START -->

	<script
		src="${pageContext.request.contextPath}/resources/assets/plugins/icheck/icheck.min.js"
		type="text/javascript"></script>
	<!-- OTHER SCRIPTS INCLUDED ON THIS PAGE - END -->


	<!-- CORE TEMPLATE JS - START -->
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/scripts.js"
		type="text/javascript"></script>
	<!-- END CORE TEMPLATE JS - END -->


	<!-- General section box modal start -->
	<div class="modal" id="section-settings" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog animated bounceInDown">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Section Settings</h4>
				</div>
				<div class="modal-body">Body goes here...</div>
				<div class="modal-footer">
					<button data-dismiss="modal" class="btn btn-default" type="button">Close</button>
					<button class="btn btn-success" type="button">Save changes</button>
				</div>
			</div>
		</div>
	</div>
	<!-- modal end -->
	<script>
		$(document).ready(function() {

			$.ajaxSetup({
				cache : false
			});

			var timestamp = (new Date()).getTime();

			$("#captchaRef").click(function() {
				document.getElementById('captcha').value = "";
				var timestamp = (new Date()).getTime();
				var newSrc = $("#captchaImage").attr("src").split("?");
				//  $('#captchaImage').attr('src', '').attr('src', 'Captcha.jpg');
				newSrc = newSrc[0] + "?" + timestamp;
				$("#captchaImage").attr("src", newSrc);
				$("#captchaImage").slideDown("fast");

			});
		});
	</script>
</body>
</html>


