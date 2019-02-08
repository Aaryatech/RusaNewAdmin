<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"  %><%@ taglib
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
    <body class=" "><!-- START TOPBAR -->
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
                <!-- PAGE HEADING TAG - START --><h1 class="title">Upload Media</h1><!-- PAGE HEADING TAG - END -->                            </div>
			 
                                
        </div>
    </div>
    <div class="clearfix"></div>
    <!-- MAIN CONTENT AREA STARTS -->
 
	<div class="col-lg-12">
    	                                                            
    </div>
	
    <div class="col-lg-12">
        <section class="box ">
       
                <header class="panel_header">
                    <h2 class="title pull-left"> Upload Media </h2>
                   
                    <div class="actions panel_actions pull-right">
                	       <a href="${pageContext.request.contextPath}/addGalleryCategory"><button type="button" class="btn btn-success">Add Gallery Category</button></a>
                	       <a class="box_toggle fa fa-chevron-down"></a>
                </div>
                     
                </header>
                
                   <form class="form-horizontal" id="addSupplier" action="${pageContext.request.contextPath}/uploadMediaForm" 
                     method="get">
                   
                <div class="content-body"> 
                    <div class="row">
                  
                        <div class="col-xs-12"> 
                        <input id="pageId" value="${pageId}" name="pageId" type="hidden"  >
                        <input id="moduleId" value="${moduleId}" name="moduleId" type="hidden"  >
                         
                                <div class="form-group">
                                <label class="control-label col-sm-2" for="config_mail_protocol">Select Gallery Category : <span class="text-danger">*</span> </label>
                                <div class="col-sm-10">
                                   
                                  <select name="catId" id="catId" class="form-control" required>
                                  
                                  <option value="">Select Category</option>
                                  	<c:forEach items="${catList}" var="catList" >
                                  		 
                                   			<option value="${catList.galleryCatId}">${catList.cateName}</option>
                                  		 
                                  	</c:forEach>
                                    
                                    </select>
                                </div>
                              </div>
                              
                               
                              <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                  <button type="submit" class="btn btn-primary">Submit</button> <button type="reset" class="btn btn-default">Reset</button> 
                                  
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
</body>
</html>