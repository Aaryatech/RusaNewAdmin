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


<!-- CORE CSS TEMPLATE - END -->
<c:url var="clearSessionAttribute" value="/clearSessionAttribute" />
<c:url var="clearUserSessionAttribute"
	value="/clearUserSessionAttribute" />
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

				<!-- <div class='col-xs-12'>
					<div class="page-title">

						<div class="pull-left">
							PAGE HEADING TAG - START
							<h1 class="title">User List</h1>
							PAGE HEADING TAG - END
						</div>


					</div>
				</div> -->
				<div class="clearfix"></div>
				<!-- MAIN CONTENT AREA STARTS -->



				<div class="col-lg-12">
					<section class="box ">
						<header class="panel_header">
							<h2 class="title pull-left">Event Applied User List</h2>
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
										<a href="${pageContext.request.contextPath}/showEventDetailListPdf"
											target="_blank"><button type="button"
												class="btn btn-primary">PDF</button></a>

									</div><br>
									<table id="example-1"
										class="table table-striped dt-responsive display">
										<thead>
											<tr>
												<th width="5%">Sr No</th>
												<!-- <th>Event Name</th> -->
												<th>User Name</th>
												<th>Type</th>
												<th>Mobile Number</th>
												<th>Apply Date</th>
												<th>Document</th>
												<th>Approval Status</th>
												<th>Approval Date</th>
												<th>Feedback</th>
												<th>Action</th>
											</tr>
										</thead>

										<tbody>
											<c:forEach items="${editUser}" var="userList"
												varStatus="count">
												<tr>
													<td>${count.index+1}</td>
													<%-- <td>${userList.heading}</td> --%>
													<td>${userList.name}</td>
													<td><c:choose>
															<c:when test="${userList.userType==2}">
																 Institute
															</c:when>
															<c:when test="${userList.userType==3}">
																University
															</c:when>
															<c:otherwise>
															Individuals
															</c:otherwise>
														</c:choose></td>
													<td>${userList.mobileNumber}</td>
													<td>${userList.regDate}</td>
													<td><c:choose>
															<c:when test="${not empty userList.doc1}">
																<a href="${documentUrl}${userList.doc1}" target="_blank"><span
																	class="icon-download-2"></span> Download</a>
															</c:when>
															<c:otherwise>
															-
															</c:otherwise>
														</c:choose></td>
													<c:if test="${userList.statusApproval==0}">
														<td>Applied</td>
													</c:if>
													<c:if test="${userList.statusApproval==1}">
														<td>Approve</td>
													</c:if>
													<c:if test="${userList.statusApproval==2}">
														<td>Not Approve</td>
													</c:if>
													<c:choose>
														<c:when test="${not empty userList.approvalDate}">
															<td>${userList.approvalDate}</td>
														</c:when>
														<c:otherwise>
															<td>--</td>
														</c:otherwise>
													</c:choose>
													<c:choose>
														<c:when test="${not empty userList.exVar1}">
															<td>${userList.exVar1}</td>
														</c:when>
														<c:otherwise>
															<td>-</td>
														</c:otherwise>
													</c:choose>



													<td><a
														href="${pageContext.request.contextPath}/approveUser?userId=${userList.userId}&regId=${userList.eventRegId}&newsId=${newsblogsId}&status=1"
														onclick="return confirm('Are you want to approve ?')">
															<span class="glyphicon glyphicon-ok-circle"
															data-animate="  animated fadeIn " rel="tooltip"
															title="Approve"></span>
													</a></td>
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
	<!-- <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>

<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.5.6/js/dataTables.buttons.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.5.6/js/buttons.flash.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
<script src="https://cdn.datatables.net/buttons/1.5.6/js/buttons.html5.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.5.6/js/buttons.print.min.js"></script>

<script>
$(document).ready(function() {
    $('#example-1').DataTable( {
        dom: 'Bfrtip',
        buttons: [
             'excel', 'pdf'
        ]
    } );
} );
</script> -->

</body>
</html>
