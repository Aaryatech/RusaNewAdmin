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
							<h1 class="title">Menu List</h1>
							<!-- PAGE HEADING TAG - END -->
						</div>


					</div>
				</div>
				<div class="clearfix"></div>
				<!-- MAIN CONTENT AREA STARTS -->



				<div class="col-lg-12">
					<section class="box ">
						<header class="panel_header">
							<h2 class="title pull-left">Menu List</h2>
							<div class="actions panel_actions pull-right">
								<a href="${pageContext.request.contextPath}/addCategory"><button
										type="button" class="btn btn-success">Add Menu</button></a> <a
									class="box_toggle fa fa-chevron-down"></a>
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
											${sessionScope.successMsg}
										</div>
									</div>
								</c:if>

								<div class="col-xs-12">
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

									<table id="example-1"
										class="table table-striped dt-responsive display">
										<thead>
											<tr>
												<th width="5%">Sr No</th>
												<th>Menu Name</th>
												<th>Menu Desc</th>
												<th>Section Name</th>
												<th>Menu Sort No</th>
												<th>Status</th>
												<th>Action</th>
											</tr>
										</thead>

										<tbody>
											<c:forEach items="${categoryList}" var="categoryList"
												varStatus="count">
												<tr>
													<td>${count.index+1}</td>
													<td>${categoryList.catName}</td>
													<td>${categoryList.catDesc}</td>
													<td>${categoryList.sectionName}</td>
													<td>${categoryList.catSortNo}</td>
													<c:choose>
														<c:when test="${categoryList.isActive==1}">
															<td>Active</td>
														</c:when>
														<c:otherwise>
															<td>Inactive</td>
														</c:otherwise>
													</c:choose>
													<td><a
														href="${pageContext.request.contextPath}/editCategory/${categoryList.catId}"><span
															class="glyphicon glyphicon-edit"
															data-animate=" animated fadeIn " rel="tooltip"></span></a> <c:if
															test="${categoryList.externalUrl==0}">| <a
																href="#" onClick="singleDelete(${categoryList.catId});"
																rel="tooltip" data-color-class="danger"
																data-animate=" animated fadeIn " data-toggle="tooltip"
																data-original-title="Delete  record"><span
																class="glyphicon glyphicon-remove"></span></a>
														</c:if></td>
													<!-- ${pageContext.request.contextPath}/deleteCategory/${categoryList.catId} -->
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
	<script type="text/javascript">
   function singleDelete(id) {
		 
		$('#modal_scrollable_single').modal('show');
		document.getElementById("conid").value = id;
	}
	function submitFormSingle() {
		 $('#modal_scrollable_single').modal('hide'); 
		 var id = document.getElementById("conid").value;
		 var token = document.getElementById("token").value;
		 location.href = "${pageContext.request.contextPath}/deleteCategory/"+id+"/"+token;
		//document.getElementById("multipleDelete").submit();
		 
	}
   </script>
	<script>
function clearSessionAttribute() {
	 
	 
 
	$.getJSON('${clearSessionAttribute}', {
  
		ajax : 'true',

	}, function(data) { 
		 
	
	});

}
 </script>
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
					<button type="button" class="btn bg-primary" data-dismiss="modal">No</button>
					<button type="button" class="btn bg-primary"
						onclick="submitFormSingle()">Yes</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
