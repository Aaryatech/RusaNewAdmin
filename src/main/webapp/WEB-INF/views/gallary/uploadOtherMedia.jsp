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
							<h1 class="title">Other Media Upload</h1>
							<!-- PAGE HEADING TAG - END -->
						</div>


					</div>
				</div>
				<div class="clearfix"></div>
				<!-- MAIN CONTENT AREA STARTS -->





				<div class="col-lg-12">
					<section class="box ">
						<header class="panel_header">
							<h2 class="title pull-left">Add File</h2>
							<div class="actions panel_actions pull-right">
								<a href="${pageContext.request.contextPath}/uploadOtherMedia"><button
										type="button" class="btn btn-success">Add New</button></a> <a
									href="${pageContext.request.contextPath}/uploadedImageList"><button
										type="button" class="btn btn-info"><< Back</button></a>
							</div>
						</header>
						<div class="content-body">
							<div class="row">
								<div class="col-xs-12">
									<form class="dropzone"
										action="${pageContext.request.contextPath}/uploadOtherMediaProccess"
										method="post" enctype="multipart/form-data">
										<input name="isImage" value="1" type="hidden" />

										<div class="fallback">
											<input name="file" type="file" multiple /> <input
												type="hidden" value="<%out.println(hashtext);%>"
												name="token" id="token">


										</div>


									</form>
									<br>

								</div>
							</div>
						</div>
					</section>

				</div>

				<div class="col-lg-12">
					<section class="box ">
						<header class="panel_header">
							<h2 class="title pull-left">Media List</h2>
							<div class="actions panel_actions pull-right">
								<a href="${pageContext.request.contextPath}/uploadOtherMedia"><button
										type="button" class="btn btn-success">Other Media
										Upload</button></a> <a class="box_toggle fa fa-chevron-down"></a>
								<!--  <a class="box_setting fa fa-cog" data-toggle="modal" href="#section-settings"></a>
                    <a class="box_close fa fa-times"></a> -->

							</div>

						</header>
						<div class="content-body">
							<div class="row">

								<form
									action="${pageContext.request.contextPath}/multipleUploadMediaDelete/<%out.println(hashtext);%>"
									method="get" id="multipleDelete">
									<div class="col-xs-12">


										<table id="example-1"
											class="table table-striped dt-responsive display">
											<thead>
												<tr>
													<th width="5%">Sr No</th>
													<th width="2%">Delete</th>
													<th>Image</th>
													<th>name</th>
													<th width="10%">Action</th>
												</tr>
											</thead>



											<tbody>
												<c:forEach items="${list}" var="list" varStatus="count">
													<tr>
														<td>${count.index+1}</td>
														<td style="text-align: center;"><input
															type="checkbox" class="chk" name="ids"
															id="contact${list.image}" value="${list.image}" /></td>
														<td><c:choose>
																<c:when
																	test="${list.size!='jpeg' and list.size!='jpg' and list.size!='png' and list.size!='gif'}">
																	<a href="${list.thumb}" target="_blank"> <i
																		class="fa fa-file" style="font-size: 45px"></i></a>
																</c:when>
																<c:otherwise>
																	<img src="${list.thumb}"
																		style="width: 150px; height: 120px">
																</c:otherwise>
															</c:choose></td>

														<td><input value="${list.thumb}" class="col-md-10"
															type="text" id="${count.index+1}"> <!-- <a href="javascript:void(0);" class="btna" data-clipboard-action="copy" data-clipboard-target="#${count.index+1}">Copy URL</a> --></td>
														<td><a
															href="${pageContext.request.contextPath}/deleteOtherMediaFile/${list.image}/${list.size}/<%out.println(hashtext);%>"
															onClick="return confirm('Are you sure want to delete this record');"
															rel="tooltip" data-color-class="danger"
															data-animate=" animated fadeIn " data-toggle="tooltip"
															data-original-title="Delete  record"><span
																class="glyphicon glyphicon-remove"></span></a></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>

									</div>
									<span class="validation-invalid-label" id="error_table1"
										style="display: none;">Please select one record.</span>
									<div class="form-group" style="text-align: center;">
										<button type="submit" class="btn btn-primary" id="submtbtn">Multiple
											Delete</button>
									</div>
								</form>
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
					<button type="button" class="btn btn-primary" data-dismiss="modal">No</button>
					<button type="button" class="btn btn-primary"
						onclick="submitForm()">Yes</button>
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