<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<!DOCTYPE html>
<html class=" ">
<head>

<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<!-- CORE CSS TEMPLATE - END -->
</head>
<!-- END HEAD -->

<style>
.image-preview-input {
	position: relative;
	overflow: hidden;
	margin: 0px;
	color: #333;
	background-color: #fff;
	border-color: #ccc;
}

.image-preview-input input[type=file] {
	position: absolute;
	top: 0;
	right: 0;
	margin: 0;
	padding: 0;
	font-size: 20px;
	cursor: pointer;
	opacity: 0;
	filter: alpha(opacity = 0);
}

.image-preview-input-title {
	margin-left: 2px;
}
</style>


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
							<h1 class="title">${title}</h1>
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
							<h2 class="title pull-left">${title}</h2>

							<div class="actions panel_actions pull-right">
								<%-- <a href="${pageContext.request.contextPath}/publicationList"><button
										type="button" class="btn btn-info">Back</button></a> <a
									class="box_toggle fa fa-chevron-down"></a> --%>
							</div>

						</header>


						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertPublicationDetail"
										method="post" enctype="multipart/form-data"
										name="form_sample_2" id="form_sample_2"
										onsubmit="return confirm('Do you really want to submit the form?');">

										<ul class="nav nav-tabs">
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> ${title}
											</a></li>

										</ul>

										<div class="tab-content">
											<div class="tab-pane fade in active" id="home">

												<div>

													<div class="col-xs-12">


														<div class="form-group">
															<label class="control-label col-sm-2" for="isReform"
																style="text-align: left;"> Safety/Security : <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="radio" onclick="showReforms(this.value)"
																	id="isSecure" name="isSecure" value="1" checked>Yes
																<input type="radio" id="isSecure"
																	onclick="showReforms(this.value)" name="isSecure"
																	value="0">No
															</div>
														</div>

														<div class="form-group" id="ex1">
															<label class="control-label col-sm-2" for="page_name">Please
																Specify :<span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="iqacName"
																	name="iqacName" value="${page.pageName}" required>
															</div>
														</div>

													</div>


													<div class="col-xs-12">


														<div class="form-group">
															<label class="control-label col-sm-2" for="isReform"
																style="text-align: left;"> Counseling : <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="radio" onclick="showReforms2(this.value)"
																	id="counseling" name="counseling" value="1" checked>Yes
																<input type="radio" id="counseling"
																	onclick="showReforms2(this.value)" name="counseling"
																	value="0">No
															</div>


														</div>




														<div class="form-group" id="ex2">
															<label class="control-label col-sm-2" for="page_name">Please
																Specify :<span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="iqacName"
																	name="iqacName" value="${page.pageName}" required>
															</div>
														</div>

													</div>


													<div class="col-xs-12">


														<div class="form-group">
															<label class="control-label col-sm-2" for="isReform"
																style="text-align: left;"> Common Room : <span
																class="text-danger">*</span>
															</label>
															<div class="col-sm-3">
																<input type="radio" onclick="showReforms3(this.value)"
																	id="istransparent" name="istransparent" value="1"
																	checked>Yes <input type="radio"
																	id="istransparent" onclick="showReforms3(this.value)"
																	name="istransparent" value="0">No
															</div>


														</div>




														<div class="form-group" id="ex3">
															<label class="control-label col-sm-2" for="page_name">Please
																Specify Location :<span class="text-danger">*</span>
															</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="iqacName"
																	name="iqacName" value="${page.pageName}" required>
															</div>
														</div>

													</div>



													<div class="form-group">
														<div class="col-sm-offset-2 col-sm-10">
															<input type="submit" class="btn btn-primary"
																onclick="submit_f(1)" value="Save"> <input
																type="submit" class="btn btn-primary"
																onclick="submit_f(0)"
																value="Save &
																		Next">
															<button type="reset" class="btn btn-default">Reset</button>
														</div>
													</div>
												</div>


												<div class="clearfix"></div>

											</div>

										</div>
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




	<div class="modal fade col-xs-12" id="myModal1" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog" style="width: 65%">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Mechanism of Examination related
						Grievances</h4>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label class="control-label col-sm-3" for="academicYear">Academic
							Year</label> <select id="academicYear" name="academicYear"
							class="form-control" required>
							<option value="2018-2019">2018-2019</option>
							<option value="2017-2018">2017-2018</option>
							<option value="2016-2017">2016-2017</option>

						</select>
					</div>


					<div class="form-group">

						<label class="control-label col-sm-3" for="Grievances">Grievances
						</label> <input type="text" class="form-control" id="grievances"
							name="grievances" placeholder="Grievances"
							value="${page.pageName}" required>
					</div>



					<div class="form-group">

						<label class="control-label col-sm-3" for="remark">Remark
						</label> <input type="text" class="form-control" id="remark" name="remark"
							placeholder="Remark" value="${page.pageName}" required>
					</div>





					<!-- Link on Website for Activity Report -->

					<div class="form-group" style="text-align: center;">

						<button type="submit" class="btn btn-primary" onclick="getData()">Submit</button>
					</div>


				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
					<input type="hidden" id="index" name="index" value="0">
				</div>
			</div>
		</div>
	</div>


	<script type="text/javascript">
		function showReforms(temp) {
			//alert("hii");
			//var remark = document.getElementById("isReform").value;
			//alert(temp);
			if (temp == 1) {
				document.getElementById("ex1").style = "visible"

			} else {

				document.getElementById("ex1").style = "display:none"

			}
		}
	</script>

	<script type="text/javascript">
		function showReforms2(a) {
			//alert("hii");
			//var remark = document.getElementById("isReform").value;
			//alert(a);
			if (a == 1) {
				document.getElementById("ex2").style = "visible"

			} else {

				document.getElementById("ex2").style = "display:none"

			}
		}
	</script>

	<script type="text/javascript">
		function showReforms3(b) {
			//alert("hii");
			//var remark = document.getElementById("isReform").value;
			//alert(temp);
			if (b == 1) {
				document.getElementById("ex3").style = "visible"

			} else {

				document.getElementById("ex3").style = "display:none"

			}
		}
	</script>






</body>
</html>