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
											<input name="file" type="file" multiple />


										</div>


									</form>
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


								<div class="col-xs-12">


									<table id="example-1"
										class="table table-striped dt-responsive display">
										<thead>
											<tr>
												<th width="5%">Sr No</th>
												<th>Image</th>
												<th>name</th>
												<th width="10%">Action</th>
											</tr>
										</thead>



										<tbody>
											<c:forEach items="${list}" var="list" varStatus="count">
												<tr>
													<td>${count.index+1}</td>
													<td><c:choose>
															<c:when
																test="${list.size!='jpeg' and list.size!='jpg' and list.size!='png' and list.size!='gif'}">
																<a
																	href="${list.thumb}" target="_blank">
																	<i class="fa fa-file" style="font-size: 45px" ></i></a>
															</c:when>
															<c:otherwise>
																<img src="${list.thumb}"
																	style="width: 150px; height: 120px">
															</c:otherwise>
														</c:choose></td>

													<td><input value="${list.thumb}" class="col-md-10"
														type="text" id="${count.index+1}">
														<!-- <a href="javascript:void(0);" class="btna" data-clipboard-action="copy" data-clipboard-target="#${count.index+1}">Copy URL</a> --></td>
													<td><a
														href="${pageContext.request.contextPath}/deleteOtherMediaFile/${list.image}/${list.size}"
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


</body>
</html>