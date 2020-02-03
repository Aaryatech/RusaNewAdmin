<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%@ page import="java.util.UUID"%>
<%@ page import="java.security.MessageDigest"%>
<%@ page import="java.math.BigInteger"%>



<%
												UUID uuid = UUID.randomUUID();
													MessageDigest md = MessageDigest.getInstance("MD5");
													byte[] messageDigest = md.digest(String.valueOf(uuid).getBytes());
													BigInteger number = new BigInteger(1, messageDigest);
													String hashtext = number.toString(16);
													session = request.getSession();
													session.setAttribute("generatedKey", hashtext);
											%>

<!DOCTYPE html>
<html class=" ">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<!-- CORE CSS TEMPLATE - END -->

</head>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body class=" ">
	<!-- START TOPBAR -->
	<jsp:include page="/WEB-INF/views/include/topbar.jsp"></jsp:include>
	<!-- END TOPBAR -->
	<!-- START CONTAINER -->
	<div class="page-container row-fluid container-fluid">

		<!-- SIDEBAR - START -->

		<jsp:include page="/WEB-INF/views/include/left.jsp"></jsp:include>
		<!--  SIDEBAR - END -->
		<!-- START CONTENT -->
		<section id="main-content" class=" ">
			<section class="wrapper main-wrapper row" style="">

				<div class="col-xs-12">
					<div class="page-title">

						<div class="pull-left">
							<!-- PAGE HEADING TAG - START -->
							<h1 class="title">Activate User</h1>
							<!-- PAGE HEADING TAG - END -->
						</div>


					</div>
				</div>
				<div class="clearfix"></div>
				<!-- MAIN CONTENT AREA STARTS -->

				<div class="col-lg-12"></div>

				<div class="col-lg-12">
					<section class="box ">

						<header class="panel_header">
							<h2 class="title pull-left">
								<c:choose>
									<c:when test="${isEdit==1}">  User Info</c:when>
									<c:otherwise>  User Info</c:otherwise>
								</c:choose>
							</h2>

							<div class="actions panel_actions pull-right">
								<a href="${pageContext.request.contextPath}/activeUserList"><button
										type="button" class="btn btn-info">Back</button></a> <a
									class="box_toggle fa fa-chevron-down"></a>
							</div>

						</header>
						<%--  <div class="content-body">    <div class="row">
            <c:if test="${sessionScope.successMsg!=null}">
            <div class="col-lg-12">
    		          <div class="alert alert-success alert-dismissible fade in">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
                
                <strong>Success : </strong> ${sessionScope.successMsg}</div>
        	          </c:if>  --%>

						<form class="form-horizontal" id="addSupplier"
							action="${pageContext.request.contextPath}/activateUser"
							method="post">
							
							<input type="hidden" value="<%out.println(hashtext);%>"
												name="token" id="token">
												
							<input type="hidden" name="smsVerified"
								value="${editUser.smsVerified}"> <input type="hidden"
								name="emailVerified" value="${editUser.emailVerified}">
							<input type="hidden" name="date" value="${editUser.addDate}">
							<input type="hidden" name="userPass"
								value="${editUser.userPassword}">
							<div class="content-body">
								<div class="row">
									<div class="col-xs-12">


										<input id="id" value="${editUser.regId}" name="regId"
											type="hidden"> <input id="id"
											value="${editUser.userType}" name="type" type="hidden">


										<c:choose>
											<c:when test="${isEdit==1}">

												<div class="form-group">
													<label class="col-md-2" for="config_mail_protocol">User
														Type : </label>
													<c:if test="${editUser.userType==1}">
														<label class="col-sm-10"> Individual </label>

													</c:if>

													<c:if test="${editUser.userType==2}">
														<label class="col-sm-10"> Institute </label>
													</c:if>
													<c:if test="${editUser.userType==3}">
														<label class="col-sm-10"> University </label>
													</c:if>

												</div>

												<div class="form-group">
													<label class="col-md-2" for="config_mail_protocol">User
														Name : </label> <label class="col-md-10"
														for="config_mail_protocol"> ${editUser.name} </label>
												</div>

												<div class="form-group">
													<label class="col-md-2" for="config_mail_protocol">AISHE
														Code : </label> <label class="col-sm-10">
														${editUser.aisheCode} </label>
												</div>

												<c:if test="${editUser.userType==1}">
													<div class="form-group">
														<label class="col-md-2" for="config_mail_protocol">Institute
															Name : </label> <label class="col-sm-10">
															${editUser.instName} </label>
													</div>
												</c:if>


												<div class="form-group">
													<label class="col-md-2" for="config_mail_protocol">University
														Name : </label> <label class="col-sm-10">
														${editUser.uniName} </label>
												</div>

												<c:if test="${editUser.userType==2 || editUser.userType==3}">
													<div class="form-group">
														<label class="col-md-2" for="config_mail_protocol">Authorized
															Person Name : </label> <label class="col-sm-10">
															${editUser.authorizedPerson} </label>
													</div>
												</c:if>

												<div class="form-group">
													<label class="col-md-2" for="config_mail_protocol">Designation
														of Person : </label> <label class="col-sm-10">
														${editUser.designationName} </label>
												</div>
												<div class="form-group">
													<label class="col-md-2" for="departmentName"> Name
														of Department : </label> <label class="col-sm-10">
														${editUser.departmentName} </label>
												</div>

												<div class="form-group">
													<label class=" col-md-2" for="config_mail_protocol">Mobile
														No. : </label> <label class="col-sm-10">
														${editUser.mobileNumber} </label>
												</div>

												<div class="form-group">
													<label class="col-md-2" for="config_mail_protocol">
														Email-ID : </label> <label class="col-sm-10">
														${editUser.emails} </label>
												</div>

												<div class="form-group">
													<label class="col-md-2" for="config_mail_protocol">Alternate
														Email-ID : </label> <label class="col-sm-10"> <c:choose>
															<c:when test="${not empty editUser.alternateEmail}"> 
															${editUser.alternateEmail}
															</c:when>
															<c:otherwise>-</c:otherwise>
														</c:choose>
													</label>
												</div>


												<div class="form-group">
													<label class="col-md-2" for="departmentName">Current
														Status : </label> <label class="col-sm-10"> <c:if
															test="${editUser.isActive==0}">
													New User
													</c:if> <c:if test="${editUser.isActive==1}">
													Active
													</c:if> <c:if test="${editUser.isActive==2}">
													Deactivate
													</c:if>


													</label>
												</div>


												<div class="form-group">
													<label class="col-md-2" for="config_mail_protocol">Account
														Status : <span class="text-danger">*</span>
													</label>
													<div class="col-sm-10">

														<select name="status" id="status" class="form-control">
															<c:choose>
																<c:when test="${editUser.isActive==1}">
																	<option value="0">New User</option>
																	<option value="1" selected>Activate</option>
																	<option value="2">Deactivate</option>
																</c:when>
																<c:when test="${editUser.isActive==2}">
																	<option value="0">New User</option>
																	<option value="1">Activate</option>
																	<option value="2" selected>Deactivate</option>
																</c:when>
																<c:otherwise>
																	<option value="0" selected>New User</option>
																	<option value="1">Activate</option>
																	<option value="2">Deactivate</option>
																</c:otherwise>
															</c:choose>

														</select>
													</div>
												</div>
											</c:when>

										</c:choose>

										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">

												<input type="submit" name="btnsubmit" id="btnsubmit"
													value="Update" class="btn btn-primary">
												<c:choose>
													<c:when test="${editUser.isActive==0}">

													</c:when>
													<c:when test="${editUser.isActive==1}">
														<input type="submit" name="btnsendmail" id="btnsendmail"
															value="Send Password" class="btn btn-primary">

													</c:when>

												</c:choose>

												<button type="reset" class="btn btn-default">Reset</button>
											</div>


										</div>
									</div>
								</div>
							</div>
						</form>
					</section>
				</div>
			</section>
		</section>

	</div>



	<script type="text/javascript">
		function RestrictSpace() {
			if (event.keyCode == 32) {
				alert("Space not allowed");
				return false;
			}
		}
	</script>


	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->

	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
</body>
</html>