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
							<h1 class="title">Contact List</h1>
							PAGE HEADING TAG - END
						</div>


					</div>
				</div> -->
				<div class="clearfix"></div>
				<!-- MAIN CONTENT AREA STARTS -->



				<div class="col-lg-12">
					<section class="box ">
						<header class="panel_header">
							<h2 class="title pull-left">Deleted Contact List</h2>
							<div class="actions panel_actions pull-right">
								<%--  <a href="${pageContext.request.contextPath}/addUser"><button type="button" class="btn btn-success">Add User</button></a>
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
										<a href="${pageContext.request.contextPath}/ContactList"><button
												type="button" class="btn btn-primary">Back</button></a>

									</div>
									<br>
									<form
										action="${pageContext.request.contextPath}/multipleContactDelete/<%out.println(hashtext);%>"
										method="get" id="multipleDelete">
										<table id="example-1"
											class="table table-striped dt-responsive display">
											<thead>
												<tr>
													<th width="5%">Sr No</th>
													<th>Contact Name</th>
													<th>Email</th>
													<th>Mobile No</th>
													<th>Message</th>
													<th>Feedback Type</th>
													<th>Action</th>
												</tr>
											</thead>


											<tbody>
												<c:forEach items="${contactList}" var="contactList"
													varStatus="count">
													<tr>
														<td>${count.index+1}</td>
														<td>${contactList.contactName}</td>
														<td>${contactList.emailId}</td>
														<td style="text-align: center;">${contactList.mobileNo}</td>
														<td>${contactList.message}</td>

														<td>${contactList.exVar1}</td>


														<td style="text-align: center;"><a
															href="${pageContext.request.contextPath}/retriveContact/${contactList.id}/<%out.println(hashtext);%>"
															rel="tooltip" data-color-class="danger"
															data-animate=" animated fadeIn " data-toggle="tooltip"
															data-original-title="Retrieve  record" title="Retrieve Record"><span
																class="glyphicon glyphicon-repeat"></span></a> <br /></td>

													</tr>
												</c:forEach>
											</tbody>


										</table>

										<span class="validation-invalid-label" id="error_table1"
											style="display: none;">Please select one record.</span>
										 
									</form>
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

			});

		}
		$(document).ready(function($) {

			$("#multipleDelete").submit(function(e) {
				var isError = false;
				var errMsg = "";

				var checkedVals = $('.chk:checkbox:checked').map(function() {
					return this.value;
				}).get();
				checkedVals = checkedVals.join(',');

				if (checkedVals == '') {
					$("#error_table1").show();
					return false;
				}

				if (!isError) {

					var x = true;
					if (x == true) {

						$('#modal_scrollable').modal('show');
					}
					//end ajax send this to php page
				}
				return false;
			});
		});
	</script>
	<script>
		function submitForm() {
			$('#modal_scrollable').modal('hide');
			document.getElementById("multipleDelete").submit();

		}
		function singleDelete(id) {

			$('#modal_scrollable_single').modal('show');
			document.getElementById("conid").value = id;
		}
		function submitFormSingle() {
			$('#modal_scrollable_single').modal('hide');
			var id = document.getElementById("conid").value;
			location.href = "${pageContext.request.contextPath}/deleteContact/"
					+ id;
			//document.getElementById("multipleDelete").submit();

		}
	</script>
	<div id="modal_scrollable" class="modal fade" data-backdrop="false"
		tabindex="-1">
		<div class="modal-dialog modal-dialog-scrollable">
			<div class="modal-content">
				<div class="modal-header pb-3">

					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<div class="modal-body py-0">
					<h5>Do your want to delete record?</h5>

				</div>

				<div class="modal-footer pt-3">
					<button type="button" class="btn bg-primary" data-dismiss="modal">No</button>
					<button type="button" class="btn bg-primary" onclick="submitForm()">Yes</button>
				</div>
			</div>
		</div>
	</div>

	<div id="modal_scrollable_single" class="modal fade"
		data-backdrop="false" tabindex="-1">
		<div class="modal-dialog modal-dialog-scrollable">
			<div class="modal-content">
				<div class="modal-header pb-3">

					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<div class="modal-body py-0">
					<h5>Do your want to delete record?</h5>

				</div>

				<div class="modal-footer pt-3">
					<input type="hidden" id="conid" name="conid">
					<button type="button" class="btn btn btn-primary"
						data-dismiss="modal">No</button>
					<button type="button" class="btn btn btn-primary"
						onclick="submitFormSingle()">Yes</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
