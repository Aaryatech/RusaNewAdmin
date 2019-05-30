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
<body class=" " onload="clearSessionAttribute()">
	<!-- START TOPBAR -->
	<jsp:include page="/WEB-INF/views/include/topbar.jsp"></jsp:include>
	<c:url var="clearSessionAttribute" value="/clearSessionAttribute" />
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
							<h1 class="title"> Site Maintenance</h1>
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
									<c:when test="${isEdit==1}">Edit Site Maintenance</c:when>
									<c:otherwise>Site Maintenance</c:otherwise>
								</c:choose>
							</h2>

   								<div class="actions panel_actions pull-right">
                                    <a class="box_toggle fa fa-chevron-down"></a>
                                    </div>

						</header>
<div class="content-body">
								<div class="row">
						<form class="form-horizontal" id="addSupplier"
							action="${pageContext.request.contextPath}/siteMaintenance" 
							method="post">
							
							
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
							

						
                    
                     		<div class="col-xs-12">
                                	  
                               <div class="form-group">
                                <label class="control-label col-sm-2" for="config_maintenance">Site Maintenance Status: <span class="text-danger">*</span> </label>
                                <div class="col-sm-10">
                                    <select name="status" id="status" class="form-control">
                                  	  <c:choose>
                                  		<c:when test="${editsiteMain.maintenanceStatus==0}">
                                  			<option value="1" >YES</option>
                                   			<option value="0" selected>NO</option>
                                  		</c:when>
                                  		<c:otherwise>  
                                  			<option value="1" selected>YES</option>
                                    		<option value="0">NO</option>
                                  	  	</c:otherwise>
                                  	</c:choose>  
                                    
                                    </select> </div>
                              </div>
                            
                           
                              <div class="form-group">
                                <label class="control-label col-sm-2" for="config_site_maintenance_message">Message:  <span class="text-danger">*</span> </label>
                                <div class="col-sm-10">
                                <input id="message" class="form-control"
								placeholder="Message"  style="text-align: left;" name="message" type="text" value="${editsiteMain.message}" required>
                                </div>
                              </div>
                               
                              
                        </div>
                     

									<div class="col-xs-12">
										<%--   <input id="id" value="${editMetaData.id}" name="id" type="hidden"  > --%>



										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<button type="submit" class="btn btn-primary">Submit</button>
												<button type="reset" class="btn btn-default">Reset</button>

											</div>
											<!--  -->
										</div>

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