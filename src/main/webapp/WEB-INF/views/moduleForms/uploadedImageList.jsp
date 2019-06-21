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
							<h1 class="title">Photo Gallery List</h1>
							<!-- PAGE HEADING TAG - END -->
						</div>


					</div>
				</div>
				<div class="clearfix"></div>
				<!-- MAIN CONTENT AREA STARTS -->



				<div class="col-lg-12">
					<section class="box ">
						<header class="panel_header">
							<h2 class="title pull-left">Photo Gallery List</h2>
							<div class="actions panel_actions pull-right">
								<a href="${pageContext.request.contextPath}/sectionTreeList"><button
										type="button" class="btn btn-success">Add Photo
										Gallery</button></a> <a class="box_toggle fa fa-chevron-down"></a>
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
<br>
									<form
										action="${pageContext.request.contextPath}/multipleGalleryImageDelete"
										method="get" id="multipleDelete">
								<div class="col-xs-12">


									<table id="example-1"
										class="table table-striped dt-responsive display">
										<thead>
											<tr>
												<th width="5%">Sr No</th>
												<th width="5%">Delete</th>
												<th>Title</th>
												<th>Image</th>
												<th  width="10%">Sort No</th>
												<th width="10%">Action</th>
											</tr>
										</thead>

										<tbody>
											<c:forEach items="${gallaryDetailList}"
												var="gallaryDetailList" varStatus="count">
												<tr>
													<td>${count.index+1}</td>
													<td style="text-align: center;"><input
																type="checkbox" class="chk" name="ids"
																id="contact${gallaryDetailList.galleryDetailsId}" value="${gallaryDetailList.galleryDetailsId}" /></td>
													<td><input value="${gallaryDetailList.title}"
														class="col-md-10" type="text"
														id="title${gallaryDetailList.galleryDetailsId}"></td>

													<td><img
														src="${imageUrl}thumbnail${gallaryDetailList.fileName}"
														style="width: 150px; height: auto"></td>
													<%-- <c:choose>
														<c:when test="${gallaryDetailList.isActive==1}">
															<td>Active</td>
														</c:when>
														<c:otherwise>
															<td>Inactive</td>
														</c:otherwise>
													</c:choose> --%>
													<td><input value="${gallaryDetailList.sortNo}"
														class="alloNo" type="text"
														id="sortNo${gallaryDetailList.galleryDetailsId}" style="text-align: right;"></td>
													<td><a
														href="javascript:updateTitleName(${gallaryDetailList.galleryDetailsId});"><span
															class="glyphicon glyphicon-edit"
															data-animate=" animated fadeIn " rel="tooltip"></span></a> <a
														href="${pageContext.request.contextPath}/deleteImage?pageId=${pageId}&moduleId=${moduleId}&catId=${catId}&id=${gallaryDetailList.galleryDetailsId}
														&file=${gallaryDetailList.fileName}"
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
	<script type="text/javascript">
		function updateTitleName(id) {

			var title = $("#title" + id).val();
			var sortNo = $("#sortNo" + id).val();
			//alert(sortNo);
			if(sortNo==""){
				sortNo=0;
			}
			//alert(title); 
			location.href = 'updateTitleName/' + id + "/" + title +"/"+ sortNo;

		}
	</script>
	
	
	<script>
		function clearSessionAttribute() {

			$.getJSON('${clearSessionAttribute}', {

				ajax : 'true',

			}, function(data) {

			});

		}
		
		$('.alloNo').on('input', function() {
			 this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');
			});
	</script>
	
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
