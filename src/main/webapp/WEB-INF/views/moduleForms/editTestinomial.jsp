
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
<body class=" " onload="check(${editTestImonial.pageId})">
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
							<h1 class="title">
								<c:choose>
									<c:when test="${isEdit==1}">Edit</c:when>
									<c:otherwise> Add</c:otherwise>
								</c:choose>
								Content for
								<c:choose>
									<c:when test="${moduleId==8}">Success Story</c:when>
									<c:when test="${moduleId==13}">Team</c:when>
									<c:otherwise>Testimonial</c:otherwise>
								</c:choose>

							</h1>
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
									<c:when test="${isEdit==1}">Edit</c:when>
									<c:otherwise> Add</c:otherwise>
								</c:choose>
								Content for
								<c:choose>
									<c:when test="${moduleId==8}">Success Story</c:when>
									<c:when test="${moduleId==13}">Team</c:when>
									<c:otherwise>Testimonial</c:otherwise>
								</c:choose>
							</h2>

							<div class="actions panel_actions pull-right">
								<a href="${pageContext.request.contextPath}/testImonialList"><button
										type="button" class="btn btn-info"><< Back</button></a> <a
									class="box_toggle fa fa-chevron-down"></a>
							</div>

						</header>

						<div class="content-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal"
										action="${pageContext.request.contextPath}/insertTestimonialEditForm"
										method="post" enctype="multipart/form-data"
										name="form_sample_2" id="form_sample_2">

										<ul class="nav nav-tabs">
											<li class="active"><a href="#home" data-toggle="tab">
													<i class="fa fa-home"></i> Home
											</a></li>

										</ul>


										<c:choose>
											<c:when test="${isEdit==0}">
												<input type="hidden" name="pageId" value="${page.pageId}">
												<input type="hidden" name="moduleId" value="${moduleId}">
												<input type="hidden" name="id" value="0">

											</c:when>
											<c:otherwise>
												<input type="hidden" name="id" value="${editTestImonial.id}">
												<input type="hidden" name="pageId"
													value="${editTestImonial.pageId}">
												<input type="hidden" name="moduleId"
													value="${editTestImonial.sectionId}">
											</c:otherwise>
										</c:choose>

										<input type="hidden" name="isEdit" value="${isEdit}">


										<div class="tab-content">
											<div class="tab-pane fade in active" id="home">



												<div class="col-xs-12">
													<c:if test="${not empty editTestImonial.imageName}">
														<div class="form-group">
															<label class="col-md-2 control-label">Current
																Image</label>
															<div class="col-md-10">
																<img src="${url}${editTestImonial.imageName}"
																	style="width: 200px; height: auto"> &nbsp &nbsp <input
																	type="checkbox" name="removeImg" value="1">
																Remove
															</div>
														</div>
													</c:if>
													<input type="hidden" name="removeImg" value="0">

												</div>
												<div class="col-xs-12">
													<div class="form-group row">
														<label class="control-label col-sm-2" for="category_id">
															Image :</label>
														<div class="col-sm-7">
															<div class="fileinput-new thumbkishore2div thumbnail"
																style="width: 250px; display: none; overflow: hidden">
																<img
																	src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image"
																	id="thumbkishore2" alt=""
																	style="width: 250px; height: auto">
															</div>

															<div class="input-group image-preview2">

																<input type="text"
																	class="form-control image-preview-filename2"
																	disabled="disabled">
																<!-- don't give a name === doesn't send on POST/GET -->
																<span class="input-group-btn"> <!-- image-preview-clear button -->
																	<button type="button"
																		class="btn btn-default image-preview-clear2"
																		style="display: none;">
																		<span class="glyphicon glyphicon-remove2"></span>
																		Clear
																	</button> <!-- image-preview-input -->

																	<div class="btn btn-default image-preview-input">
																		<span class="glyphicon glyphicon-folder-open"></span>
																		<span class="image-preview-input-title2">Browse</span>

																		<input type="file"
																			accept="image/png, image/jpeg, image/gif" id="images"
																			name="images" />
																		<!-- rename it -->
																	</div>
																</span>
															</div>

															Note: Image size should be width: 500px and height:
															500px.
														</div>
													</div>
												</div>

												<c:choose>
													<c:when test="${moduleId==8}">

														<input type="hidden" name="formType" value="1">
													</c:when>

													<c:when test="${moduleId==13}">

														<input type="hidden" name="formType" value="1">


													</c:when>
													<c:otherwise>
														<div class="form-group" onchange="showExtraField()">
															<label class="control-label col-sm-2" for="page_order">Type
																:<span class="text-danger">*</span>
															</label>
															<div class="col-sm-10">
																<div class="form-check-inline">
																	<label class="form-check-label"> <c:choose>
																			<c:when test="${editTestImonial.exInt1==1}">
																				<input type="radio" class="form-check-input"
																					name="formType" value="1" checked>
																		Text
																	</c:when>
																			<c:when test="${isEdit==0}">
																				<input type="radio" class="form-check-input"
																					name="formType" value="1" checked>
																		Text
																	</c:when>
																			<c:otherwise>
																				<input type="radio" class="form-check-input"
																					name="formType" value="1">
																		Text 
																	</c:otherwise>
																		</c:choose>


																	</label>
																</div>


																<div class="form-check-inline">
																	<label class="form-check-label"> <c:choose>
																			<c:when test="${editTestImonial.exInt1==2}">
																				<input type="radio" class="form-check-input"
																					name="formType" id="formType" value="2" checked>
																		Video
																		</c:when>
																			<c:otherwise>
																				<input type="radio" class="form-check-input"
																					name="formType" id="formType" value="2">
																		Video
																	</c:otherwise>
																		</c:choose>
																	</label>
																</div>
															</div>
														</div>


													</c:otherwise>
												</c:choose>
												<c:forEach items="${languagesList}" var="languagesList">
													<c:forEach items="${editTestImonial.detailList}"
														var="detailList">

														<c:if
															test="${detailList.langId==languagesList.languagesId}">
															<h5 class="title pull-left">${languagesList.name}</h5>

															<div class="col-xs-12">
																<div class="form-group">
																	<label class="control-label col-sm-2" for="page_name">
																		Person Name :<span class="text-danger">*</span>
																	</label>
																	<div class="col-sm-10">
																		<input type="text" class="form-control"
																			id="form_name${detailList.langId}"
																			name="form_name${detailList.langId}"
																			onchange="trim(this)" placeholder="  Name"
																			value="${detailList.fromName}" required>
																	</div>
																</div>
															</div>

															<div class="col-xs-12">
																<div class="form-group">
																	<label class="control-label col-sm-2" for="page_name">Designation
																		:</label>
																	<div class="col-sm-10">
																		<input type="text" class="form-control"
																			id="designation${detailList.langId}"
																			name="designation${detailList.langId}"
																			placeholder="Designation" onchange="trim(this)"
																			value="${detailList.designation}">
																	</div>
																</div>
															</div>
															<div class="col-xs-12">
																<div class="form-group">
																	<label class="control-label col-sm-2" for="page_name">Location
																		:</label>
																	<div class="col-sm-10">
																		<input type="text" class="form-control"
																			id="location${detailList.langId}"
																			onchange="trim(this)"
																			name="location${detailList.langId}"
																			placeholder="Location" value="${detailList.location}">
																	</div>
																</div>
															</div>

															<c:choose>
																<c:when test="${moduleId==8}">



																	<div class="col-xs-12" id="msg1">
																		<div class="form-group">
																			<label class="control-label col-sm-2"
																				for="page_description1">Message : </label>
																			<div class="col-sm-10">

																				<textarea
																					style="width: 100%; height: 150px; font-size: 14px; line-height: 23px; padding: 15px;"
																					name="message${detailList.langId}"
																					onchange="trim(this)"
																					id="message${detailList.langId}">
															
															 ${detailList.message}
																
															</textarea>
																			</div>
																		</div>
																	</div>



																</c:when>
																<c:when test="${moduleId==13}">

																	<input type="hidden" name="message${detailList.langId}">
																	<input type="hidden" name="video1${detailList.langId}">

																</c:when>
																<c:otherwise>


																	<div class="col-xs-12" id="msg1">
																		<div class="form-group">
																			<label class="control-label col-sm-2"
																				for="page_description1">Message/Video Link :
																			</label>
																			<div class="col-sm-10">
																				<textarea rows="3" cols="3" class="form-control"
																					style="width: 100%; height: 150px; font-size: 14px; line-height: 23px; padding: 15px;"
																					name="message${detailList.langId}"
																					id="message${detailList.langId}">${detailList.message}</textarea>



																			</div>
																		</div>
																	</div>


																</c:otherwise>
															</c:choose>
														</c:if>
													</c:forEach>

												</c:forEach>

												<div class="col-xs-12">
													<hr>

													<div class="form-group">
														<label class="control-label col-sm-2" for="page_order">Sort
															No. :<span class="text-danger">*</span>
														</label>
														<div class="col-sm-10">
															<input type="number" class="form-control" id="sortNo"
																name="sortNo" placeholder="Sort Order"
																onchange="trim(this)" value="${editTestImonial.sortNo}"
																required>
														</div>
													</div>


													<div class="form-group">
														<label class="control-label col-sm-2" for="status">Status
															:<span class="text-danger">*</span>
														</label>
														<div class="col-sm-10">
															<select id="status" name="status" class="form-control"
																required>

																<c:choose>
																	<c:when test="${editTestImonial.isActive==0}">
																		<option value="1">Active</option>
																		<option value="0" selected>Inactive</option>
																	</c:when>
																	<c:otherwise>
																		<option value="1" selected>Active</option>
																		<option value="0">Inactive</option>
																	</c:otherwise>
																</c:choose>



															</select>
														</div>
													</div>
												</div>
												<div class="form-group">
													<div class="col-sm-offset-2 col-sm-10">
														<button type="submit" class="btn btn-primary">Submit</button>
														<button type="reset" class="btn btn-default">Reset</button>
													</div>
												</div>




												<div class="clearfix"></div>
											</div>

										</div>
										<div class="tab-pane fade" id="metatage">

											<p>That said, in some situations it may be desirable to
												turn this functionality off. Therefore, we also provide the
												ability to disable the data attribute API by unbinding all
												events on the document namespaced with data-api.</p>

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
	<script>
			function trim(el) {
				el.value = el.value.replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
				replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space 
				replace(/\n +/, "\n"); // Removes spaces after newlines
				return;
			}
			</script>
	<script type="text/javascript">
	function check(temp) {
		//alert("hii");
		//alert(temp);
		if (temp == 2) {
			document.getElementById("msg1").style = "display:none"
			document.getElementById("video1").style = "visible"
		} else {
			document.getElementById("video1").style = "display:none"
			document.getElementById("msg1").style = "visible"

		}

	}
	</script>


	<script type="text/javascript">
		jQuery(document).ready(
				function($) {

					// sub_menu
					$('#sub_menu1').click(function(e) {
						//ajax send this to php page
						var def = 1;
						if ($("#sub_menu").prop('checked') == true) {
							$('#main_menu').prop('checked', true);

						} else {

						}
					});

					//Example 2

					$(document).on('click', '#close-preview', function() {
						$('.image-preview').popover('hide');
						// Hover befor close the preview
						$('.image-preview').hover(function() {
							$('.image-preview').popover('show');
						}, function() {
							$('.image-preview').popover('hide');
						});
					});

					$(function() {
						// Create the close button
						var closebtn = $('<button/>', {
							type : "button",
							text : 'x',
							id : 'close-preview',
							style : 'font-size: initial;',
						});
						closebtn.attr("class", "close pull-right");
						// Set the popover default content
						$('.image-preview').popover(
								{
									trigger : 'manual',
									html : true,
									title : "<strong>Preview</strong>"
											+ $(closebtn)[0].outerHTML,
									content : "There's no image",
									placement : 'bottom'
								});
						// Clear event
						$('.image-preview-clear').click(function() {
							//$('.image-preview').attr("data-content","").popover('hide');
							$('.image-preview-filename').val("");
							$('.image-preview-clear').hide();
							$('#header_image input:file').val("");
							$(".image-preview-input-title").text("Browse");
							$("#thumbkishore").attr("src", '');
							$(".thumbkishorediv").hide();
						});
						// Create the preview image
						$("#header_image").change(
								function() {
									var img = $('<img/>', {
										id : 'dynamic',
										width : 250,
										height : 200,
									});

									var file = this.files[0];
									var reader = new FileReader();
									// Set preview image into the popover data-content
									reader.onload = function(e) {

										$(".image-preview-input-title").text(
												"Change");
										$(".image-preview-clear").show();
										$(".image-preview-filename").val(
												file.name);
										img.attr('src', e.target.result);
										//alert(e.target.result);
										///alert($(img)[0].outerHTML);
										//$(".image-preview").attr("data-content",$(img)[0].outerHTML).popover("show");
										//
										//alert(e.target.result);
										$("#thumbkishore").attr("src",
												e.target.result);

										$(".thumbkishorediv").show();
									}
									reader.readAsDataURL(file);
								});

						// Clear event
						$('.image-preview-clear2').click(function() {
							//$('.image-preview').attr("data-content","").popover('hide');
							$('.image-preview-filename2').val("");
							$('.image-preview-clear2').hide();
							$('.image-preview-input2 input:file').val("");
							$(".image-preview-input-title2").text("Browse");
							$("#thumbkishor2e").attr("src", '');
							$(".thumbkishore2div").hide();
						});
						// Create the preview image
						$("#images").change(
								function() {
									var img = $('<img/>', {
										id : 'dynamic',
										width : 250,
										height : 200,
									});

									var file = this.files[0];
									var reader = new FileReader();
									// Set preview image into the popover data-content
									reader.onload = function(e) {

										$(".image-preview-input-title2").text(
												"Change");
										$(".image-preview-clear2").show();
										$(".image-preview-filename2").val(
												file.name);
										img.attr('src', e.target.result);
										//alert(e.target.result);
										///alert($(img)[0].outerHTML);
										//$(".image-preview").attr("data-content",$(img)[0].outerHTML).popover("show");
										//
										//alert(e.target.result);
										$("#thumbkishore2").attr("src",
												e.target.result);

										$(".thumbkishore2div").show();
									}
									reader.readAsDataURL(file);
								});

					});

				});
	</script>

</body>
</html>