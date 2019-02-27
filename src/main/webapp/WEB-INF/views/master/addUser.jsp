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
							<h1 class="title">Admin </h1>
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
								
									<c:when test="${editUser.userId==null}">Add Admin</c:when>
									<c:otherwise>Edit Admin</c:otherwise>
								</c:choose>
							</h2>

							<div class="actions panel_actions pull-right">
								<a href="${pageContext.request.contextPath}/userList"><button
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
							action="${pageContext.request.contextPath}/insertUser"
							onsubmit="return confirm('Do you really want to submit the form?');"
							method="post" enctype="multipart/form-data">

							<div class="content-body">
								<div class="row">
									<div class="col-xs-12">
										<%-- <div class="form-group">
											<label class="control-label col-sm-2"
												for="config_mail_protocol">Roles : <span
												class="text-danger">*</span>
											</label>
											<div class="col-sm-10">

												<select name="roles" id="roles" class="form-control"
													placeholder="User Role">
													<c:choose>
														<c:when test="${editUser.roles=='SA'}">
															<option value="SA">SA</option>
															<option value="A" selected>A</option>
														</c:when>
														<c:otherwise>
															<option value="SA">SA</option>
															<option value="A">A</option>
														</c:otherwise>
													</c:choose>

												</select>
											</div>
										</div>
										 --%>
										<input id="roles" value="SA" name="roles" type="hidden">
										<input id="roles" value="SA" name="roles" type="hidden">


										<input id="userId" value="${editUser.userId}" name="userId"
											type="hidden">

										<div class="form-group">
											<label class="control-label col-sm-2"
												for="config_mail_protocol">First Name : <span
												class="text-danger">*</span>
											</label>
											<div class="col-sm-10">
												<input id="firstname" class="form-control"
													placeholder="First Name" value="${editUser.firstname}"
													style="text-align: left;" name="firstname" type="text"
													required>
											</div>
										</div>

										<div class="form-group">
											<label class="control-label col-sm-2"
												for="config_mail_protocol">Middle Name : </label>
											<div class="col-sm-10">
												<input id="middlename" class="form-control"
													placeholder="Middle Name" value="${editUser.middlename}"
													style="text-align: left;" name="middlename" type="text">
											</div>
										</div>

										<div class="form-group">
											<label class="control-label col-sm-2"
												for="config_mail_protocol">Last Name : <span
												class="text-danger">*</span>
											</label>
											<div class="col-sm-10">
												<input id="lastname" class="form-control"
													placeholder="Last Name" value="${editUser.lastname}"
													style="text-align: left;" name="lastname" type="text"
													required>
											</div>
										</div>

										<div class="form-group">
											<label class="control-label col-sm-2"
												for="config_mail_protocol">Email : <span
												class="text-danger">*</span>
											</label>
											<div class="col-sm-10">
												<input id="userEmail" class="form-control"
													placeholder="Email" value="${editUser.userEmail}"
													style="text-align: left;" name="userEmail" type="email"
													required>
											</div>
										</div>


 									<input type="hidden" name="remove" value="0"> 

										<c:choose>
											<c:when test="${isEdit==1}">
												<div class="form-group">
													<label class="col-md-2 control-label">Current
														Profile Photo</label>
													<div class="col-md-10">
														<img src="${imageUrl}${editUser.fileName}"
															style="width: 150px; height: auto">
													</div>
												</div>
											</c:when>


										</c:choose>


										<div class="form-group">
											<label class="col-md-2 control-label">Profile Photo:</label>
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
																	class="browseimage browseimage1" id="1" name="docfile">
																<!-- rename it -->
															</div>
														</span>
													</div>
													<span class="help-block">* Only jpg,gif,png * Best
														image size is 100px × 100px </span>
												</div>
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
														<c:when test="${editUser.isActive==0}">
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
										<hr>
										<div class="row">
											<div class="col-xs-12">
												<h3 class="title pull-left">LOGIN DETAIL</h3>
											</div>
										</div>
										<c:choose>
											<c:when test="${isEdit=='1'}">

												<div class="form-group">
													<label class="control-label col-sm-2"
														for="config_mail_protocol">User Name : <span
														class="text-danger">*</span>
													</label>
													<div class="col-sm-10">
														<input id="userName" class="form-control"
															placeholder="User Name" value="${editUser.userName}"
															style="text-align: left;" name="userName" type="text"
															onKeyDown="if(event.keyCode === 32) return false;"
															readonly required>
													</div>
												</div>


											</c:when>

											<c:otherwise>
												<div class="form-group">
													<label class="control-label col-sm-2"
														for="config_mail_protocol">User Name : <span
														class="text-danger">*</span>
													</label>
													<div class="col-sm-10">
														<input id="userName" class="form-control"
															placeholder="User Name" value="${editUser.userName}"
															style="text-align: left;" name="userName" type="text"
															onKeyDown="if(event.keyCode === 32) return false;"
															required>
													</div>
												</div>

											</c:otherwise>
										</c:choose>

										<div class="form-group">
											<label class="control-label col-sm-2"
												for="config_mail_protocol">Password : <span
												class="text-danger">*</span>
											</label>
											<div class="col-sm-10">
												<input id="userPass" class="form-control"
													placeholder="Password" value="${editUser.userPass}"
													style="text-align: left;" name="userPass" type="text"
													required>
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

	<!-- MAIN CONTENT AREA ENDS -->

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