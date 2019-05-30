<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html class=" ">
<head>

<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<!-- CORE CSS TEMPLATE - END -->
<c:url var="clearSessionAttribute" value="/clearSessionAttribute" />
<c:url var="clearUserSessionAttribute"
	value="/clearUserSessionAttribute" />
<!-- <link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/buttons/1.5.6/css/buttons.dataTables.min.css">
<style>
.btn1 {
	background-color: #ffffff; /* Blue background */
	border: none; /* Remove borders */
	color: white; /* White text */
	padding: 12px 16px; /* Some padding */
	font-size: 16px; /* Set a font size */
	cursor: pointer; /* Mouse pointer on hover */
}

/* Darker background on mouse-over */
.btn:hover {
	background-color: blue;
}
</style> -->
<!-- CORE CSS TEMPLATE - END -->
</head>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body class=" " onload="clearSessionAttribute()">
	<!-- START TOPBAR -->
	<jsp:include page="/WEB-INF/views/include/topbar.jsp"></jsp:include>
	<!-- END TOPBAR -->
	<!-- START CONTAINER -->
	<div class="page-container row-fluid container-fluid">

		<!-- SIDEBAR - START -->

		<jsp:include page="/WEB-INF/views/include/left.jsp"></jsp:include>
		<!--  SIDEBAR - END -->
		<!-- START CONTENT -->
		<!-- START CONTENT -->
		<section id="main-content" class=" ">
			<section class="wrapper main-wrapper row" style=''>

				<div class='col-xs-12'>
					<div class="page-title">

						<div class="pull-left">
							<!-- PAGE HEADING TAG - START -->
							<h1 class="title">User List</h1>
							<!-- PAGE HEADING TAG - END -->
						</div>


					</div>
				</div>
				<div class="clearfix"></div>
				<!-- MAIN CONTENT AREA STARTS -->



				<div class="col-lg-12">
					<section class="box ">
						<header class="panel_header">
							<h2 class="title pull-left">User Registered List</h2>
							<div class="actions panel_actions pull-right">
								<%--     <a href="${pageContext.request.contextPath}/addUser"><button type="button" class="btn btn-success">Add Admin</button></a>
                --%>
								<a class="box_toggle fa fa-chevron-down"></a>
								<!--  <a class="box_setting fa fa-cog" data-toggle="modal" href="#section-settings"></a>
                    <a class="box_close fa fa-times"></a> -->

							</div>

						</header>
						<div class="content-body">
							<div class="row">
								<c:if test="${sessionScope.successMsg!=null}">
									<div class="col-lg-12">
										<div class="alert alert-success alert-dismissible fade in">
											<button type="button" class="close" data-dismiss="alert"
												aria-label="Close">
												<span aria-hidden="true">×</span>
											</button>

											<strong>Success : </strong> ${sessionScope.successMsg}
										</div>
									</div>
								</c:if>

								<div class="col-xs-12">

									<div style="text-align: right;">
										<input type="button" class="btn btn-primary"
											onclick="tableToExcel('example-1', 'name', 'userlist.xls')"
											value="Export to Excel">&nbsp;<a
											href="${pageContext.request.contextPath}/activeUserListPdf"
											target="_blank"><button type="button"
												class="btn btn-primary">PDF</button></a>

									</div>
									<br>

									<table id="example-1"
										class="table table-striped dt-responsive display">
										<thead>
											<tr>
												<th width="5%">Sr No</th>
												<th>User Name</th>
												<th>User Registration Type</th>
												<th>Email</th>
												<th>Mobile Number</th>
												<th>Register Via</th>
												<th>SMS Verified Status</th>
												<th>Email Verified Status</th>
												<th>Status</th>
												<th>Activate</th>
											</tr>
										</thead>

										<tbody>
											<c:forEach items="${regList}" var="userList"
												varStatus="count">
												<tr>
													<td>${count.index+1}</td>
													<td>${userList.name}</td>
													<c:if test="${userList.userType=='1'}">
														<td>Individual</td>
													</c:if>
													<c:if test="${userList.userType=='2'}">
														<td>College</td>
													</c:if>
													<c:if test="${userList.userType=='3'}">
														<td>University</td>
													</c:if>

													<td>${userList.emails}</td>
													<td>${userList.mobileNumber}</td>
													<td>${userList.registerVia}</td>
													<c:choose>
														<c:when test="${userList.smsVerified==1}">
															<td>Verified</td>
														</c:when>
														<c:otherwise>
															<td>Not Verified</td>
														</c:otherwise>
													</c:choose>


													<c:choose>
														<c:when test="${userList.emailVerified==1}">
															<td>Verified</td>
														</c:when>
														<c:otherwise>
															<td>Not Verified</td>
														</c:otherwise>
													</c:choose>


													<c:choose>
														<c:when test="${userList.isActive==0}">
															<td>New User</td>
														</c:when>
														<c:when test="${userList.isActive==1}">
															<td>Activate</td>
														</c:when>
														<c:otherwise>
															<td>Deactivate</td>
														</c:otherwise>
													</c:choose>
													<td><a
														href="${pageContext.request.contextPath}/editActivateUser/${userList.regId}"><span
															class="glyphicon glyphicon-edit"
															data-animate=" animated fadeIn " rel="tooltip"></span></a></td>

												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</section>
				</div>






				<!-- MAIN CONTENT AREA ENDS -->
			</section>
		</section>
		<!-- END CONTENT -->





	</div>
	<!-- END CONTAINER -->
	<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->

	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
	<script>
		function clearSessionAttribute() {

			$.getJSON('${clearSessionAttribute}', {

				ajax : 'true',

			}, function(data) {

				clearUserSessionAttribute();
			});

		}
		function clearUserSessionAttribute() {

			$.getJSON('${clearUserSessionAttribute}', {

				ajax : 'true',

			}, function(data) {

			});

		}
	</script>
	<script type="text/javascript">
		function tableToExcel(table, name, filename) {
			let uri = 'data:application/vnd.ms-excel;base64,', template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><title></title><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--><meta http-equiv="content-type" content="text/plain; charset=UTF-8"/></head><body><table>{table}</table></body></html>', base64 = function(
					s) {
				return window.btoa(decodeURIComponent(encodeURIComponent(s)))
			}, format = function(s, c) {
				return s.replace(/{(\w+)}/g, function(m, p) {
					return c[p];
				})
			}

			if (!table.nodeType)
				table = document.getElementById(table)
			var ctx = {
				worksheet : name || 'Worksheet',
				table : table.innerHTML
			}

			var link = document.createElement('a');
			link.download = filename;
			link.href = uri + base64(format(template, ctx));
			link.click();
		}
	</script>
</body>
</html>
