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
								<i class="fa fa-table"></i>Add Category
							</h3>
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/addCategory">
									Add Category</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>

						</div>

						<div class=" box-content">
							<form id="addSupplier"
								action="${pageContext.request.contextPath}/insertCategory"
								onsubmit="return confirm('Do you really want to submit the form?');" method="post">
								
								<div class="box-content">

									<div class="col-md-2">Category Code*</div>
									<div class="col-md-3">
										<input id="catCode" class="form-control"
								placeholder="Category Code" value="${editCategory.catCode}"  style="text-align: left;" name="catCode" type="text" required>
								 
								  <input id="catId" value="${editCategory.catId}" name="catId" type="hidden"  >
								  
									</div>
									<div class="col-md-1"></div>
									<div class="col-md-2">Category Name*</div>
									<div class="col-md-3">
										<input id="catName" class="form-control"
								placeholder="Category Name" value="${editCategory.catName}"  style="text-align: left;" name="catName" type="text" required>
								 
								  
									</div>
									 
								</div>
								<br>
								
								<div class="box-content">

									<div class="col-md-2">Category Description*</div>
									<div class="col-md-3">
										<input id="catDesc" class="form-control"
								placeholder="Category Description" value="${editCategory.catDesc}"  style="text-align: left;" name="catDesc" type="text" required>
								 
								  
									</div>
									<div class="col-md-1"></div>
									
									<div class="col-md-2" >Section No</div>
									<div class="col-md-3">
										<select   class="form-control chosen"   name="sectionId"  id="sectionId"  required>
										  
										 <option value="">select</option>
											<c:forEach items="${sectionList}" var="sectionList">
												<c:choose>
													<c:when test="${sectionList.sectionId==editCategory.sectionId}">
														<option value="${sectionList.sectionId}" selected>${sectionList.sectionNo}</option>
													</c:when>
													<c:otherwise>
														<option value="${sectionList.sectionId}">${sectionList.sectionNo}</option>
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
								placeholder="Sequence No" value="${editCategory.catSortNo}"  style="text-align: left;" name="seqNo" type="number" required>
								  
									</div>
									 
									 
								</div>
								<br> <br>
								 
					<br>
								<div class=" box-content">
									<div class="col-md-12" style="text-align: center">
										<input type="submit" onclick="validation()" class="btn btn-info" value="Submit"
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
									<th >Category Code</th>
									<th>Category Name</th>
									<th>Description</th>
									<th>Section</th>
									<th>Seq. No</th> 
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
								  <c:forEach items="${categoryList}" var="categoryList" varStatus="count">
									<tr  >
										<td>${count.index+1}</td>
										<td>${categoryList.catCode}</td>
										<td>${categoryList.catName}</td>
										<td>${categoryList.catDesc}</td> 
										<td>${categoryList.sectionName}</td>
										<td>${categoryList.catSortNo}</td> 
										<td><a
											href="${pageContext.request.contextPath}/editCategory/${categoryList.catId}"><span
												class="glyphicon glyphicon-edit"></span></a> <a
											href="${pageContext.request.contextPath}/deleteCategory/${categoryList.catId}"
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
		function validation() {

			var sectionId = document.getElementById("sectionId").value;
			 

			if (sectionId == "" || sectionId == null) {
				alert("Select Section ");

			}
		}
		function FocusOnInput()
		 {
		 document.getElementById("catCode").focus();
		 }
		 
	</script>

</body>
</html>