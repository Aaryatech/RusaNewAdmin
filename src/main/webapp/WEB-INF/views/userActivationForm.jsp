<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
									<c:when test="${isEdit==1}">Edit User</c:when>
									<c:otherwise>Edit Contact</c:otherwise>
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
							onsubmit="return confirm('Do you really want to submit the form?');"
							method="post">
							<input type="hidden" name="smsVerified"
													value="${editUser.smsVerified}">
												<input type="hidden" name="emailVerified"
													value="${editUser.emailVerified}">
												<input type="hidden" name="date" value="${editUser.addDate}">
	<input type="hidden" name="userPass"
													value="${editUser.userPassword}">
							<div class="content-body">
								<div class="row">
									<div class="col-xs-12">


										<input id="id" value="${editUser.regId}" name="regId"
											type="hidden">
											
										<input id="id" value="${editUser.userType}" name="type"
											type="hidden">
											

										<c:choose>
											<c:when test="${isEdit==1}">

												<div class="form-group">
													<label class="control-label col-sm-2"
														for="config_mail_protocol">User Name : <span
														class="text-danger">*</span>
													</label>
													<div class="col-sm-10">
														<input id="contactName" class="form-control"
															placeholder="User Name" value="${editUser.name}"
															style="text-align: left;" name="name" type="text"
															required>
													</div>
												</div>


												<div class="form-group">
													<label class="control-label col-sm-2"
														for="config_mail_protocol">User Type : <span
														class="text-danger">*</span>
													</label>
													<c:if test="${editUser.userType==1}">
													<div class="col-sm-10">
														<input id="contactName" class="form-control"
															placeholder="User Type" value="Individual"
															style="text-align: left;"  type="text"
															readonly required>
													</div>
										   		
												</c:if>
										
												<c:if test="${editUser.userType==2}">
										   		<div class="col-sm-10">
														<input id="contactName" class="form-control"
															placeholder="User Type" value="College"
															style="text-align: left;" type="text"
															readonly required>
													</div>
													</c:if>
													<c:if test="${editUser.userType==3}">										
													<div class="col-sm-10">
															<input id="contactName" class="form-control"
															placeholder="User Type" value="University"
															style="text-align: left;"  type="text"
															readonly required>
															</div>										   		
													</c:if>
													
												</div>
												<div class="form-group">
													<label class="control-label col-sm-2"
														for="config_mail_protocol"> Email-ID : <span
														class="text-danger">*</span>
													</label>
													<div class="col-sm-10">
														<input id="userEmail" class="form-control"
															placeholder="Email" value="${editUser.emails}"
															style="text-align: left;" name="userEmail" type="email"
															required>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-sm-2"
														for="config_mail_protocol">Mobile No. : <span
														class="text-danger">*</span>
													</label>
													<div class="col-sm-10">
														<input id="firstname" class="form-control"
															pattern="[7-9]{1}[0-9]{9}" maxlength="10"
															placeholder="Mobile Number"
															value="${editUser.mobileNumber}"
															style="text-align: left;" name="phone" type="text"
															required>
													</div>
												</div>
														<c:if test="${editUser.userType==2 || editUser.userType==3}">
												<div class="form-group">
													<label class="control-label col-sm-2"
														for="config_mail_protocol">Authorized Person Name : <span
														class="text-danger">*</span>
													</label>
													<div class="col-sm-10">
														<input id="middlename" class="form-control"
															placeholder="Authorized Name"
															value="${editUser.authorizedPerson}"
															style="text-align: left;" name="authN" type="text" required
															>
													</div>
												</div></c:if>
												<div class="form-group">
													<label class="control-label col-sm-2"
														for="config_mail_protocol">Alternate Email-ID : </label>
													<div class="col-sm-10">
														<input id="userEmail" class="form-control"
															placeholder="Alternate Email"
															value="${editUser.alternateEmail}"
															style="text-align: left;" name="email" type="email" >
													</div>
												</div>

											<c:if test="${editUser.userType==2 || editUser.userType==3}">
												<div class="form-group">
													<label class="control-label col-sm-2"
														for="config_mail_protocol">AISHE Code : <span
														class="text-danger">*</span></label>
													<div class="col-sm-10">
														<input id="middlename" class="form-control"
															placeholder="AISHE Code" value="${editUser.aisheCode}"
															style="text-align: left;" name="aishe" type="text" >
													</div>
												</div></c:if>
												<c:if test="${editUser.userType==1}">
												<div class="form-group">
													<label class="control-label col-sm-2"
														for="config_mail_protocol">College Name : <span
														class="text-danger">*</span> </label>
													<div class="col-sm-10">
														<input id="middlename" class="form-control"
															placeholder="College Name"
															value="${editUser.collegeName}" style="text-align: left;"
															name="collegeN" type="text" required>
													</div>
												</div>
												</c:if>
												<c:if test="${editUser.userType==1 || editUser.userType==2}">
												<div class="form-group">
													<label class="control-label col-sm-2"
														for="config_mail_protocol">University Name :<span
														class="text-danger">*</span> </label>
													<div class="col-sm-10">
														<input id="middlename" class="form-control"
															placeholder="University Name"
															value="${editUser.unversityName}"
															style="text-align: left;" name="uniName" type="text" required>
													</div>
												</div>
												</c:if>
												<div class="form-group">
													<label class="control-label col-sm-2"
														for="config_mail_protocol">Designation of Person :<span
														class="text-danger">*</span> </label>
													<div class="col-sm-10">
														<input id="middlename" class="form-control"
															placeholder="Designation"
															value="${editUser.designationName}"
															style="text-align: left;" name="designation" type="text">
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-sm-2"
														for="departmentName"> Name of Department :<span
														class="text-danger">*</span> </label>
													<div class="col-sm-10">
														<input id="departmentName" class="form-control"
															placeholder="Department Name"
															value="${editUser.departmentName}"
															style="text-align: left;" name="deptN" type="text">
													</div>
												</div>


												
												
											<div class="form-group">
													<label class="control-label col-sm-2"
														for="departmentName">Current Status : </label>
													<div class="col-sm-10">
													<c:if test="${editUser.isActive==0}">
													New User
													</c:if>
													<c:if test="${editUser.isActive==1}">
													Active
													</c:if>
													<c:if test="${editUser.isActive==2}">
													Deactive
													</c:if>
														
														 
													</div>
												</div>


												<div class="form-group">
													<label class="control-label col-sm-2"
														for="config_mail_protocol">Account Status : <span
														class="text-danger">*</span>
													</label>
													<div class="col-sm-10">

														<select name="status" id="status" class="form-control">
															<c:choose>
																<c:when test="${editUser.isActive==1}">
																	<option value="0" >New User</option>
																	<option value="1" selected>Activate</option>
																	<option value="2" >Deactivate</option>
																</c:when>
																<c:when test="${editUser.isActive==2}">
																	<option value="0" >New User</option>
																	<option value="1" >Activate</option>
																	<option value="2" selected>Deactivate</option>
																</c:when>
																<c:otherwise>
																	<option value="0" selected>New User</option>
																	<option value="1">Activate</option>
																	<option value="2" >Deactivate</option>
																</c:otherwise>
															</c:choose>

														</select>
													</div>
												</div>
											</c:when>

										</c:choose>

										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												 
												<input type="submit" name="btnsubmit" id="btnsubmit" value="Update"   class="btn btn-primary">
												<c:choose>
																<c:when test="${editUser.isActive==0}">
																	 
																</c:when>
																<c:when test="${editUser.isActive==1}">
																	 <input type="submit" name="btnsendmail" id="btnsendmail" value="Send Password"   class="btn btn-primary">
												 
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