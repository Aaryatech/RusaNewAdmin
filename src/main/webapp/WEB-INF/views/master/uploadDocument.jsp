<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="java.util.UUID"%>
<%@ page import="java.security.MessageDigest"%>
<%@ page import="java.math.BigInteger"%>

<!DOCTYPE html>
<html class=" ">
<head>

<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<!-- CORE CSS TEMPLATE - END -->
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
							<h1 class="title">Upload Documents</h1>
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
									<c:when test="${isEdit==1}">Edit Documents</c:when>
									<c:otherwise>Upload Documents</c:otherwise>
								</c:choose>
							</h2>

							<div class="actions panel_actions pull-right">
								<a href="${pageContext.request.contextPath}/documentUploadList"><button
										type="button" class="btn btn-info"><< Back</button></a> <a
									class="box_toggle fa fa-chevron-down"></a>
							</div>

						</header>
<%
		UUID uuid = UUID.randomUUID();
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] messageDigest = md.digest(String.valueOf(uuid).getBytes());
		BigInteger number = new BigInteger(1, messageDigest);
		String hashtext = number.toString(16);
		session = request.getSession();
		session.setAttribute("generatedKey", hashtext);
	%>
						<form class="form-horizontal" id="addSupplier"
							action="${pageContext.request.contextPath}/insertUploadDoc"
							method="post" enctype="multipart/form-data">

	<input type="hidden" value="<%out.println(hashtext);%>"
				name="token" id="token">
							<div class="content-body">
								<div class="row">

									<c:choose>
										<c:when test="${isEdit==0}">
											<input type="hidden" name="pageId" value="${page.pageId}">

										</c:when>
										<c:otherwise>
											<input type="hidden" name="pageId"
												value="${editupload.pageId}">
										</c:otherwise>
									</c:choose>
									<input id="docId" value="${editupload.docId}" name="docId"
										type="hidden"> <input type="hidden" name="isEdit"
										value="${isEdit}">
									<div class="col-xs-12">
										<div class="form-group">
											<label class="control-label col-sm-2"
												for="config_mail_protocol">Category : <span
												class="text-danger">*</span>
											</label>
											<div class="col-sm-10">

												<select id="cateId" name="cateId"
													class="form-control chosen" required>

													<c:forEach items="${categoryList}" var="catList">
														<c:choose>
															<c:when
																test="${catList.galleryCatId==editupload.cateType}">
																<option value="${catList.galleryCatId}" selected>${catList.cateName}</option>
															</c:when>
														</c:choose>
													</c:forEach>

													<c:forEach items="${categoryList}" var="catList"
														varStatus="count">
														<option value="${catList.galleryCatId}">${catList.cateName}</option>
													</c:forEach>
												</select>


											</div>
										</div>





										<c:choose>
											<c:when test="${isEdit==0}">
												<div class="form-group">
													<label class="control-label col-sm-2"
														for="config_mail_protocol">Document Name :<span
														class="text-danger">*</span>
													</label>
													<div class="col-sm-10">
														<input id="docName" class="form-control"
															placeholder="Document Name" value="${editupload.exVar1}"
															style="text-align: left;" name="docName" type="text"
															required>
													</div>
												</div>
												<div class="form-group row">
													<label class="control-label col-sm-2" for="page_pdf">Document
														File :<span class="text-danger">*</span>
													</label>
													<div class="col-sm-10">
														<input type="file" name="pagePdf" id="pagePdf" 
															value="${editupload.fileName}" class="form-control"
															data-parsley-minlength="2"
															accept=".xlsx,.xls,.doc, .docx,.ppt, .pptx,.txt,.pdf,.zip,image/png, image/jpeg, image/gif"
															required />

													</div>
												</div>
											</c:when>
											<c:otherwise>
												<div class="form-group">
													<label class="control-label col-sm-2"
														for="config_mail_protocol">Document Name : </label>
													<div class="col-sm-10">
														<input id="docName" class="form-control" onchange="trim(this)"
															placeholder="Document Name" value="${editupload.exVar1}"
															style="text-align: left;" name="docName" type="text">
													</div>
												</div>
												<c:if test="${not empty editupload.fileName}">
													<div class="form-group">
														<label class="col-md-2 control-label">Current PDF
															:</label>
														<div class="col-sm-10">
															<a href="${url}${editupload.fileName}" target="_blank">${editupload.exVar1}
																- ${editupload.fileSize}</a>
														</div>

													</div>
												</c:if>
												<div class="form-group row">
													<label class="control-label col-sm-2" for="page_pdf">Document
														File :</label>
													<div class="col-sm-10">
														<input type="file" name="pagePdf" id="pagePdf"
															value="${editupload.fileName}" class="form-control"
															data-parsley-minlength="2"
															accept=".xlsx,.xls,.doc, .docx,.ppt, .pptx,.txt,.pdf,.zip,image/png, image/jpeg, image/gif" />

													</div>
												</div>
											</c:otherwise>
										</c:choose>





										<div class="form-group">
											<label class="control-label col-sm-2"
												for="config_mail_protocol">Sort Order : <span
												class="text-danger">*</span></label>
											<div class="col-sm-10">
												<input id="sortNo" class="form-control" onchange="trim(this)"
													placeholder="Sequence No" value="${editupload.sortNo}"
													style="text-align: left;" name="sortNo" type="number"
													required>
											</div>
										</div>

										<div class="form-group">
											<label class="control-label col-sm-2"
												for="config_mail_protocol">Is Active : <span
												class="text-danger">*</span>
											</label>
											<div class="col-sm-10">

												<select name="isActive" id="isActive" class="form-control"
													required>
													<c:choose>
														<c:when test="${editupload.isActive==0}">
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