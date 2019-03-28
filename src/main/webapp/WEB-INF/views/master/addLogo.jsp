<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" 
  import = "java.text.SimpleDateFormat"%> 
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
<body class=" " onload="clearSessionAttribute()">
	<!-- START TOPBAR -->
	<jsp:include page="/WEB-INF/views/include/topbar.jsp"></jsp:include>
	<!-- END TOPBAR -->
	<!-- START CONTAINER -->
	<div class="page-container row-fluid container-fluid">
		<c:url var="clearSessionAttribute" value="/clearSessionAttribute" />
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
							<h1 class="title">logo</h1>
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
									<c:when test="${isEdit==1}">Edit Slider Image</c:when>
									<c:otherwise>Add Logo</c:otherwise>
								</c:choose>
							</h2>
							<div class="actions panel_actions pull-right">
								<a class="box_toggle fa fa-chevron-down"></a>
							</div>
						</header>
						<div class="content-body">
							<div class="row">
								<form class="form-horizontal" id="addSupplier"
									action="${pageContext.request.contextPath}/updateOrInsertLogo"
									onsubmit="return confirm('Do you really want to submit the form?');"
									method="post" enctype="multipart/form-data">

									<c:if
										test="${sessionScope.errorMsg!=null and sessionScope.errorMsg==false}">
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
									<c:if
										test="${sessionScope.errorMsg!=null and sessionScope.errorMsg==true}">
										<div class="col-lg-12">
											<div class="alert alert-danger alert-dismissible fade in">
												<button type="button" class="close" data-dismiss="alert"
													aria-label="Close">
													<span aria-hidden="true">×</span>
												</button>
												<strong>Error : </strong> ${sessionScope.successMsg}
											</div>
										</div>
									</c:if>
									<h5 class="title pull-left">${languagesList.name}</h5>
									<div class="col-xs-12">
										<div class="form-group">
											<label class="col-md-2 control-label">Current Logo</label>
											<div class="col-md-10">
												<%
													Date date = new Date();
													SimpleDateFormat sf = new SimpleDateFormat("HHmmss", Locale.ENGLISH);
													 
												%>
												<c:set var="time" value="<%= sf.format(date) %>"></c:set>
												<img src="${url}${logo.logoMain}?id=${time}>"
													style="width: 150px; height: auto">
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">Main Logo:</label>
											<div class="col-md-10">
												<div class="row col-md-5">
													<img src="" id="temppreviewimageki1"
														class="temppreviewimageki1"
														style="width: 200px; height: auto; display: none">
												</div>
												<div class="row col-md-10">
													<div class="input-group image-preview1"
														data-original-title="" title="">
														<input type="text"
															class="form-control image-preview-filename1"
															disabled="disabled">
														<!-- don't give a name === doesn't send on POST/GET -->
														<span class="input-group-btn"> <!-- image-preview-clear button -->
															<button type="button"
																class="btn btn-default image-preview-clear image-preview-clear1"
																id="1" style="display: none;">
																<span class="glyphicon glyphicon-remove"></span> Clear
															</button> <!-- image-preview-input -->
															<div class="btn btn-default image-preview-input">
																<span class="glyphicon glyphicon-folder-open"></span> <span
																	class="image-preview-input-title image-preview-input-title1">Browse</span>
																<input type="file"
																	accept="image/png, image/jpeg, image/gif"
																	class="browseimage browseimage1" id="1" name="mainLogo">
																<!-- rename it -->
															</div>
														</span>
													</div>
													 <span class="help-block">* Only jpg,gif,png * Best image size is 369px × 64px</span>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">Current Logo2</label>
											<div class="col-md-10">
												<img src="${url}${logo.logo2}"
													style="width: 150px; height: auto">
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label"> Logo2:</label>
											<div class="col-md-10">
												<div class="row col-md-5">
													<img src="" id="temppreviewimageki2"
														class="temppreviewimageki2"
														style="width: 200px; height: auto; display: none">
												</div>
												<div class="row col-md-10">
													<div class="input-group image-preview2"
														data-original-title="" title="">
														<input type="text"
															class="form-control image-preview-filename2"
															disabled="disabled">
														<!-- don't give a name === doesn't send on POST/GET -->
														<span class="input-group-btn"> <!-- image-preview-clear button -->
															<button type="button"
																class="btn btn-default image-preview-clear image-preview-clear2"
																id="2" style="display: none;">
																<span class="glyphicon glyphicon-remove"></span> Clear
															</button> <!-- image-preview-input -->
															<div class="btn btn-default image-preview-input">
																<span class="glyphicon glyphicon-folder-open"></span> <span
																	class="image-preview-input-title image-preview-input-title2">Browse</span>
																<input type="file"
																	accept="image/png, image/jpeg, image/gif"
																	class="browseimage browseimage2" id="2" name="Logo2">
																<!-- rename it -->
															</div>
														</span>
													</div>
													 <span class="help-block">* Only jpg,gif,png * Best image size is 369px × 64px</span>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">Current Logo3</label>
											<div class="col-md-10">
												<img src="${url}${logo.logo3}"
													style="width: 150px; height: auto">
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">Logo3:</label>
											<div class="col-md-10">
												<div class="row col-md-5">
													<img src="" id="temppreviewimageki3"
														class="temppreviewimageki3"
														style="width: 200px; height: auto; display: none">
												</div>
												<div class="row col-md-10">
													<div class="input-group image-preview3"
														data-original-title="" title="">
														<input type="text"
															class="form-control image-preview-filename3"
															disabled="disabled">
														<!-- don't give a name === doesn't send on POST/GET -->
														<span class="input-group-btn"> <!-- image-preview-clear button -->
															<button type="button"
																class="btn btn-default image-preview-clear image-preview-clear3"
																id="3" style="display: none;">
																<span class="glyphicon glyphicon-remove"></span> Clear
															</button> <!-- image-preview-input -->
															<div class="btn btn-default image-preview-input">
																<span class="glyphicon glyphicon-folder-open"></span> <span
																	class="image-preview-input-title image-preview-input-title3">Browse</span>
																<input type="file"
																	accept="image/png, image/jpeg, image/gif"
																	class="browseimage browseimage3" id="3" name="Logo3">
																<!-- rename it -->
															</div>
														</span>
													</div>
													 <span class="help-block">* Only jpg,gif,png * Best image size is 369px × 64px</span>
												</div>
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<button type="submit" class="btn btn-primary">Submit</button>
											</div>
										</div>
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
	<script type="text/javascript">
		jQuery(document).ready(function($) {
			$(document).on('click', '#close-preview1', function() {
				$('.image-preview1').popover('hide');
				// Hover befor close the preview
				$('.image-preview1').hover(function() {
					$('.image-preview1').popover('show');
				}, function() {
					$('.image-preview1').popover('hide');
				});
			});
		});
		$(function() {

			//image 1

			// Create the preview image
			$(".browseimage").change(
					function() {
						var img = $('<img/>', {
							id : 'dynamic',
							width : 250,
							height : 200,
						});
						var imgid = $(this).attr('id');
						//alert(imgid);
						var file = this.files[0];
						var reader = new FileReader();
						// Set preview image into the popover data-content
						reader.onload = function(e) {

							$(".image-preview-filename" + imgid)
									.html(file.name);
							img.attr('src', e.target.result);

							$(".temppreviewimageki" + imgid).attr("src",
									$(img)[0].src);
							$(".temppreviewimageki" + imgid).show();

							$('.image-preview-clear' + imgid).show();
							//alert(e.target.result);
							///alert($(img)[0].outerHTML);
							//$(".image-preview1").attr("data-content",$(img)[0].outerHTML).popover("show");
						}
						reader.readAsDataURL(file);
					});

			// Clear event
			$('.image-preview-clear').click(function() {
				var imgid = $(this).attr('id');

				$('.image-preview-filename' + imgid).html("No file selected");
				$('.image-preview-clear' + imgid).val("");
				$('.image-preview-clear' + imgid).hide();
				$(".image-preview-input-title" + imgid).text("Browse");
				$(".temppreviewimageki" + imgid).attr("src", '');
				$(".temppreviewimageki" + imgid).hide();
			});
			//end

		});

		// TableManageButtons.init();
	</script>
	<script>
		function clearSessionAttribute() {

			$.getJSON('${clearSessionAttribute}', {

				ajax : 'true',

			}, function(data) {

			});

		}
	</script>
</body>
</html>