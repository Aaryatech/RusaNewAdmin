<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

 
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<body onload="FocusOnInput()">
 
	<c:url var="getMixingListWithDate" value="/getMixingListWithDate"></c:url>
	<c:url var="getMixingAllListWithDate" value="/getMixingAllListWithDate"></c:url>


	<div class="container" id="main-container">

		<!-- BEGIN Sidebar -->
		<div id="sidebar" class="navbar-collapse collapse">

			<jsp:include page="/WEB-INF/views/include/left.jsp"></jsp:include>

			<div id="sidebar-collapse" class="visible-lg">
				<i class="fa fa-angle-double-left"></i>
			</div>
			<!-- END Sidebar Collapse Button -->
		</div>
		<!-- END Sidebar -->

		<!-- BEGIN Content -->
		<div id="main-content">
			<!-- BEGIN Page Title -->
			<!-- <div class="page-title">
				<div>
					<h1>

						<i class="fa fa-file-o"></i>Add Category

					</h1>
				</div>
			</div> --><br>
			<!-- END Page Title -->

			<div class="row">
				<div class="col-md-12">

					<div class="box" id="todayslist">
						<div class="box-title">
							<h3>
								<i class="fa fa-table"></i>Add Sub-Category
							</h3>
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/addSubCategory">
									Add Sub-Category</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>

						</div>

						<div class=" box-content">
							<form id="addSupplier"
								action="${pageContext.request.contextPath}/insertSubCategory"
								onsubmit="return confirm('Do you really want to submit the form?');" method="post">
								
								<div class="box-content">

									<div class="col-md-2">Sub-Category Code*</div>
									<div class="col-md-3">
										<input id="subCatCode" class="form-control"
								placeholder="Sub-Category Code" value="${editSubCategory.subCatCode}"  style="text-align: left;" name="subCatCode" type="text" required>
								 
								  <input id="subCatId" value="${editSubCategory.subCatId}" name="subCatId" type="hidden"  >
									</div>
									<div class="col-md-1"></div>
									<div class="col-md-2">Sub-Category Name*</div>
									<div class="col-md-3">
										<input id="subCatName" class="form-control"
								placeholder="Sub-Category Name" value="${editSubCategory.subCatName}"  style="text-align: left;" name="subCatName" type="text" required>
								 
								  
									</div>
									 
								</div>
								<br>
								
								<div class="box-content">

									<div class="col-md-2">Sub-Category Description*</div>
									<div class="col-md-3">
										<input id="subCatDesc" class="form-control"
								placeholder="Sub-Category Description" value="${editSubCategory.subCatDesc}"  style="text-align: left;" name="subCatDesc" type="text" required>
								  
									</div>
									<div class="col-md-1"></div>
									
									<div class="col-md-2" >Select Category</div>
									<div class="col-md-3">
										<select   class="form-control chosen"   name="catId"  id="catId"  required>
										 	<option value="">select</option>
											<c:forEach items="${categoryList}" var="categoryList">
												<c:choose>
													<c:when test="${categoryList.catId==editSubCategory.catId}">
														<option value="${categoryList.catId}" selected>${categoryList.catName}</option>
													</c:when>
													<c:otherwise>
														<option value="${categoryList.catId}">${categoryList.catName}</option>
													</c:otherwise>
												</c:choose>


											</c:forEach>
											</select>
									</div> 
									 
								</div>
								<br>
								<div class="box-content">

									<div class="col-md-2">Sequence No*</div>
									<div class="col-md-3">
										<input id="seqNo" class="form-control"
								placeholder="Sequence No" value="${editSubCategory.subCatSortNo}"  style="text-align: left;" name="seqNo" type="number" required>
								  
									</div>
									 
									 
								</div>
								<br> <br>
								 
					<br>
								<div class=" box-content">
									<div class="col-md-12" style="text-align: center">
										<input type="submit" class="btn btn-info" value="Submit"
											id="submit">



									</div>
								</div>
								
								 <div class="box-content">

					<br /> <br />
					<div class="clearfix"></div>
					<div class="table-responsive" style="border: 0">
						<table class="table table-advance"   id="table1">
							<thead>
								<tr>
									<th style="width: 18px">Sr No</th>
									<th >Sub-Category Code</th>
									<th>Sub-Category Name</th>
									<th>Sub-Description</th>
									<th>Category</th>
									<th>Seq. No</th> 
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
								  <c:forEach items="${subCategoryList}" var="subCategoryList" varStatus="count">
									<tr >
										<td>${count.index+1}</td>
										<td>${subCategoryList.subCatCode}</td>
										<td>${subCategoryList.subCatName}</td>
										<td>${subCategoryList.subCatDesc}</td> 
										<td>${subCategoryList.catName}</td>
										<td>${subCategoryList.subCatSortNo}</td> 
										<td><a
											href="${pageContext.request.contextPath}/editSubCategory/${subCategoryList.subCatId}"><span
												class="glyphicon glyphicon-edit"></span></a> <a
											href="${pageContext.request.contextPath}/deleteSubCategory/${subCategoryList.subCatId}"
											onClick="return confirm('Are you sure want to delete this record');"><span
												class="glyphicon glyphicon-remove"></span></a></td>
									</tr>
								</c:forEach>
								 
								 

							</tbody>
						</table>
					</div>
				</div>
							</form>


						</div>
					</div>


				</div>
			</div>


			<div class=" box-content">

				

			</div>

			<!-- END Main Content -->
			<footer>
				<p>2019 © RUSA</p>
			</footer>

			<a id="btn-scrollup" class="btn btn-circle btn-lg" href="#"><i
				class="fa fa-chevron-up"></i></a>
		</div>
		<!-- END Content -->
	</div>
	<!-- END Container -->

	<!--basic scripts-->
	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="${pageContext.request.contextPath}/resources/assets/jquery/jquery-2.0.3.min.js"><\/script>')
	</script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/jquery-cookie/jquery.cookie.js"></script>

	<!--page specific plugin scripts-->
	<script
		src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.resize.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.pie.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.stack.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.crosshair.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.tooltip.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/sparkline/jquery.sparkline.min.js"></script>


	<!--page specific plugin scripts-->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/jquery.validate.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/additional-methods.min.js"></script>





	<!--flaty scripts-->
	<script src="${pageContext.request.contextPath}/resources/js/flaty.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/flaty-demo-codes.js"></script>
	<!--page specific plugin scripts-->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-fileupload/bootstrap-fileupload.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/chosen-bootstrap/chosen.jquery.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/clockface/js/clockface.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-daterangepicker/date.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-daterangepicker/daterangepicker.js"></script>
		
		 
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/data-tables/jquery.dataTables.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/data-tables/bootstrap3/dataTables.bootstrap.js"></script>


	<script type="text/javascript">
		function passwordValidation() {

			var pass = document.getElementById("password").value;
			var pass1 = document.getElementById("rePassword").value;

			if (pass != "" && pass1 != "") {
				if (pass != pass1) {
					alert("Password Not Matched ");
					document.getElementById("submit").disabled = true;
				} else {
					document.getElementById("submit").disabled = false;

				}

			}
		}
		function FocusOnInput()
		 {
		 document.getElementById("catDesc").focus();
		 }
		 
	</script>

</body>
</html>