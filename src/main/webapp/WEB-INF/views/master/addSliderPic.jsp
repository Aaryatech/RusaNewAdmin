<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*"
	import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.UUID"%>
<%@ page import="java.security.MessageDigest"%>
<%@ page import="java.math.BigInteger"%>
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
							<h1 class="title">HEADER SLIDER MANAGEMENT</h1>
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
									<c:otherwise>Add Slider Image</c:otherwise>
								</c:choose>
							</h2>

							<div class="actions panel_actions pull-right">
								<a href="${pageContext.request.contextPath}/sliderPicList"><button
										type="button" class="btn btn-info"><< Back</button></a> <a
									class="box_toggle fa fa-chevron-down"></a>
							</div>

						</header>

						<form class="form-horizontal" id="addSupplier"
							action="${pageContext.request.contextPath}/insertBannerImage"
							method="post" enctype="multipart/form-data">
							<%
								UUID uuid = UUID.randomUUID();
								MessageDigest md = MessageDigest.getInstance("MD5");
								byte[] messageDigest = md.digest(String.valueOf(uuid).getBytes());
								BigInteger number = new BigInteger(1, messageDigest);
								String hashtext = number.toString(16);
								session = request.getSession();
								session.setAttribute("generatedKey", hashtext);
							%>
							<input type="hidden" value="<%out.println(hashtext);%>"
								name="token" id="token">
							<div class="content-body">
								<div class="row">


									<c:forEach items="${languagesList}" var="languagesList">
										<h5 class="title pull-left">${languagesList.name}</h5>
										<c:choose>
											<c:when test="${isEdit==1}">
												<c:forEach items="${editbanner.detillist}" var="detillist">

													<c:choose>
														<c:when
															test="${detillist.sortOrder==languagesList.languagesId}">
															<div class="col-xs-12">
																<div class="form-group">
																	<label class="control-label col-sm-2"
																		for="config_mail_protocol">Banner Name : <span
																		class="text-danger">*</span>
																	</label>
																	<div class="col-sm-10">
																		<input id="sliderName" class="form-control"
																			onchange="trim(this)" placeholder="Slider Name"
																			value="${detillist.sliderName}"
																			style="text-align: left;"
																			name="sliderName${languagesList.languagesId}"
																			type="text" required>
																	</div>
																</div>

																<div class="form-group">
																	<label class="control-label col-sm-2"
																		for="config_mail_protocol">Text 1 : </label>
																	<div class="col-sm-10">
																		<input id="text1" class="form-control"
																			onchange="trim(this)" placeholder="Text 1"
																			value="${detillist.text1}" style="text-align: left;"
																			name="text1${languagesList.languagesId}" type="text">
																	</div>
																</div>


																<div class="form-group">
																	<label class="control-label col-sm-2"
																		for="config_smtp_host">Text 2:</label>
																	<div class="col-sm-10">

																		<input id="text2" class="form-control"
																			onchange="trim(this)" placeholder="Text 2"
																			value="${detillist.text2}" style="text-align: left;"
																			name="text2${languagesList.languagesId}" type="text">

																	</div>
																</div>



																<div class="form-group">
																	<label class="control-label col-sm-2"
																		for="config_mail_protocol">Link Name : </label>
																	<div class="col-sm-10">
																		<input id="linkName" class="form-control"
																			onchange="trim(this)" placeholder="Link Name "
																			value="${detillist.linkName}"
																			style="text-align: left;"
																			name="linkName${languagesList.languagesId}"
																			type="text">
																	</div>
																</div>
															</div>
														</c:when>
													</c:choose>
												</c:forEach>
											</c:when>
											<c:otherwise>

												<div class="col-xs-12">
													<div class="form-group">
														<label class="control-label col-sm-2"
															for="config_mail_protocol">Banner Name : <span
															class="text-danger">*</span>
														</label>
														<div class="col-sm-10">
															<input id="sliderName" class="form-control"
																onchange="trim(this)" placeholder="Slider Name"
																style="text-align: left;"
																name="sliderName${languagesList.languagesId}"
																type="text" required>
														</div>
													</div>

													<div class="form-group">
														<label class="control-label col-sm-2"
															for="config_mail_protocol">Text 1 : </label>
														<div class="col-sm-10">
															<input id="text1" class="form-control"
																onchange="trim(this)" placeholder="Text 1"
																style="text-align: left;"
																name="text1${languagesList.languagesId}" type="text">
														</div>
													</div>


													<div class="form-group">
														<label class="control-label col-sm-2"
															for="config_smtp_host">Text 2:</label>
														<div class="col-sm-10">

															<input id="text2" class="form-control"
																onchange="trim(this)" placeholder="Text 2"
																style="text-align: left;"
																name="text2${languagesList.languagesId}" type="text">

														</div>
													</div>



													<div class="form-group">
														<label class="control-label col-sm-2"
															for="config_mail_protocol">Link Name : </label>
														<div class="col-sm-10">
															<input id="linkName" class="form-control"
																onchange="trim(this)" placeholder="Link Name "
																style="text-align: left;"
																name="linkName${languagesList.languagesId}" type="text">
														</div>
													</div>
												</div>

											</c:otherwise>
										</c:choose>
									</c:forEach>

									<div class="col-xs-12">

										<c:if test="${isEdit==1}">
											<div class="form-group">
												<label class="control-label col-sm-2" for="banner_image">
													Current Slider Image:</label>
												<div class="col-sm-10">
													<img src="${url}${editbanner.sliderImage}"
														style="width: 250px; height: auto"> <input
														id="imageName" value="${editbanner.sliderImage}"
														name="imageName" type="hidden">
												</div>
											</div>
										</c:if>
										<input id="id" value="${editbanner.id}" name="id"
											type="hidden">
										<div class="form-group">
											<label class="col-md-2 control-label">New Slider
												Image:</label>
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
																class="btn btn-default image-preview-clear1"
																style="display: none;">
																<span class="glyphicon glyphicon-remove"></span> Clear
															</button> <!-- image-preview-input -->
															<div class="btn btn-default image-preview-input">
																<span class="glyphicon glyphicon-folder-open"></span> <span
																	class="image-preview-input-title image-preview-input-title1">Browse</span>
																<input type="file"
																	accept="image/png, image/jpeg, image/gif"
																	class="browseimage1" id="1" name="docfile">
																<!-- rename it -->

															</div>
														</span>
													</div>
													<span class="help-block">* Only jpg,gif,png * Best
														image size is 1920px × 700px</span>

												</div>
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-sm-2"
												for="config_mail_protocol">URL/Link : </label>
											<div class="col-sm-10">
												<input id="urlLink" class="form-control"
													onchange="trim(this)" placeholder="URL/Link "
													value="${editbanner.urlLink}" style="text-align: left;"
													name="urlLink" type="text">
											</div>
										</div>


										<div class="form-group">
											<label class="control-label col-sm-2"
												for="config_mail_protocol">Is Active : <span
												class="text-danger">*</span>
											</label>
											<div class="col-sm-10">

												<select name="isActive" id="isActive" class="form-control">
													<c:choose>
														<c:when test="${editbanner.isActive==0}">
															<option value="1">YES</option>
															<option value="0" selected>NO</option>
														</c:when>
														<c:otherwise>
															<option value="1">YES</option>
															<option value="0">NO</option>
														</c:otherwise>
													</c:choose>
												</select>
											</div>
										</div>

										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<button type="submit" class="btn btn-primary">Submit</button>
												<button type="reset" class="btn btn-default">Reset</button>

											</div>
										</div>
									</div>
								</div>
							</div>


						</form>
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
		jQuery(document)
				.ready(
						function($) {

							$(document).on(
									'click',
									'#close-preview1',
									function() {
										$('.image-preview1').popover('hide');
										// Hover befor close the preview
										$('.image-preview1').hover(
												function() {
													$('.image-preview1')
															.popover('show');
												},
												function() {
													$('.image-preview1')
															.popover('hide');
												});
									});

							$(function() {

								//image 1
								// Create the close button
								var closebtn1 = $('<button/>', {
									type : "button",
									text : 'x',
									id : 'close-preview1',
									style : 'font-size: initial;',
								});
								closebtn1.attr("class", "close pull-right");
								// Set the popover default content
								$('.image-preview1')
										.popover(
												{
													trigger : 'manual',
													html : true,
													title : "<strong>Preview</strong>"
															+ $(closebtn1)[0].outerHTML,
													content : "There's no image",
													placement : 'bottom'
												});
								// Clear event
								$('.image-preview-clear1').click(
										function() {
											$('.image-preview1').attr(
													"data-content", "")
													.popover('hide');
											$('.image-preview-filename1').val(
													"");
											$('.image-preview-clear1').hide();
											//$('.image-preview-input input:file').val("");
											$(".image-preview-input-title1")
													.text("Browse");
											$(".temppreviewimageki1").attr(
													"src", '');
											$(".temppreviewimageki1").hide();
										});
								// Create the preview image
								$(".browseimage1")
										.change(
												function() {
													var img = $('<img/>', {
														id : 'dynamic',
														width : 250,
														height : 200,
													});
													var imgid = $(this).attr(
															'id');
													var file = this.files[0];
													var reader = new FileReader();
													// Set preview image into the popover data-content
													reader.onload = function(e) {

														$(
																".image-preview-input-title1")
																.text("Change");
														$(
																".image-preview-clear1")
																.show();
														$(
																".image-preview-filename1")
																.val(file.name);
														img
																.attr(
																		'src',
																		e.target.result);

														$(
																".temppreviewimageki"
																		+ imgid)
																.attr(
																		"src",
																		$(img)[0].src);
														$(
																".temppreviewimageki"
																		+ imgid)
																.show();
														//img.attr('src', e.target.result);
														//alert(e.target.result);
														///alert($(img)[0].outerHTML);
														//$(".image-preview1").attr("data-content",$(img)[0].outerHTML).popover("show");
													}
													reader.readAsDataURL(file);
												});
								//end  
							});
						});

		// TableManageButtons.init();
	</script>
</body>
</html>