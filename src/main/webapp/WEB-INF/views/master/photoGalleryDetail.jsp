<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

 
  <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
  <style>
input[type="file"] {
  display: block;
}
.imageThumb {
  max-height: 75px;
  border: 2px solid;
  padding: 1px;
  cursor: pointer;
}
.pip {
  display: inline-block;
  margin: 10px 10px 0 0;
}
.remove {
  display: block;
  background: #444;
  border: 1px solid black;
  color: white;
  text-align: center;
  cursor: pointer;
}
.remove:hover {
  background: white;
  color: black;
}
</style>
<body  >
 
	<c:url var="getSubCatListByCatId" value="/getSubCatListByCatId"></c:url>
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
								<i class="fa fa-table"></i>Add Title - ${editGalleryheader.galleryTitle}
							</h3>
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/addPhotoGallary">
									Add Title</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>

						</div>

						<div class=" box-content">
							<form id="addSupplier"
								action="${pageContext.request.contextPath}/insertGalleryDetail"
								onsubmit="return confirm('Do you really want to submit the form?');" method="post" enctype="multipart/form-data">
								
								 
								
								<div class="box-content">

									<div class="col-md-2">Image Description*</div>
									<div class="col-md-10">
										<input id="imgDesc" class="form-control"
								placeholder="Image Description"  style="text-align: left;" name="imgDesc" type="text" required>
								 
								  
									</div>
									 
									 
									 
								</div>
								<br>
								
								<div class="box-content">

									<div class="col-md-2">Image Description*</div>
									<div class="col-md-10">
										<div class="field" align="left">
  
  <input type="file" id="files" name="files" multiple data-max-file-size="1MB"
        required/>
</div>
<div id="preview"></div><br>
									</div>
									 
									 
									 
								</div>
								<br>
						 
								 
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
									<th class="col-md-2">Photo</th>
									 <th >Photo Name</th> 
									<th class="col-md-1">Action</th>
								</tr>
							</thead>
							<tbody>
							 <c:forEach items="${editGalleryheader.detailList}" var="detailList" varStatus="count">
									<tr  >
										<td>${count.index+1}</td>
										
										 <td align="left">
																			 <img src="file://${url}${detailList.photoName}" width="70" height="70" 	
																			  /> 
																	file://${url}${detailList.photoName}</td>
										<td>${detailList.photoDesc}</td>
										
										 
										<td>
										 <a
											href="${pageContext.request.contextPath}/deletePhotoGallaryDetail/${detailList.galleryDetailId}"
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

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	<script type="text/javascript">
	
	function getSubCatListByCatId() {

		var catId = document.getElementById("catId").value;
		 
		$.getJSON('${getSubCatListByCatId}', {

			catId : catId,
			ajax : 'true'
		}, function(data) {

			 
			  var html = '<option value="">Select Sub-Category</option>';

			var len = data.length;
			for (var i = 0; i < len; i++) {
				html += '<option value="' + data[i].subCatId + '">'
						+ data[i].subCatName + '</option>';
			}
			html += '</option>';
			$('#subCatId').html(html);
			$("#subCatId").trigger("chosen:updated"); 
		});
	}
	function validation() {

		var catId = document.getElementById("catId").value;
		var subCatId = document.getElementById("subCatId").value;

		if (catId == "" || catId == null) {
			alert("Select Category ");

		}else if(subCatId == "" || subCatId == null){
			alert("Select Sub Category ");
		}
	}
	$(document).ready(function() {
		  if (window.File && window.FileList && window.FileReader) {
		    $("#files").on("change", function(e) {
		      var files = e.target.files,
		        filesLength = files.length;
		      for (var i = 0; i < filesLength; i++) {
		        var f = files[i]
		        var fileReader = new FileReader();
		        fileReader.onload = (function(e) {
		          var file = e.target;
		          $("<span class=\"pip\">" +
		            "<img class=\"imageThumb\" src=\"" + e.target.result + "\" title=\"" + file.name + "\"/>" +
		            "<br/><span class=\"remove\">Remove image</span>" +
		            "</span>").insertAfter("#files");
		          $(".remove").click(function(){
		            $(this).parent(".pip").remove();
		            $('#files').val("");
		          });
		          
		          // Old code here
		          /*$("<img></img>", {
		            class: "imageThumb",
		            src: e.target.result,
		            title: file.name + " | Click to remove"
		          }).insertAfter("#files").click(function(){$(this).remove();});*/
		          
		        });
		        fileReader.readAsDataURL(f);
		      }
		    });
		  } else {
		    alert("Your browser doesn't support to File API")
		  }
		});
	 
	</script>

</body>
</html>