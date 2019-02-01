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
    <body class=" " onload="clearSessionAttribute()"><!-- START TOPBAR -->
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
                <!-- PAGE HEADING TAG - START --><h1 class="title">Section List</h1><!-- PAGE HEADING TAG - END -->                            </div>
			 
                                
        </div>
    </div>
    <div class="clearfix"></div>
    <!-- MAIN CONTENT AREA STARTS -->
 
	 
     
<div class="col-lg-12">
    <section class="box "> 
             <header class="panel_header">
                <h2 class="title pull-left">Section List</h2>
                <div class="actions panel_actions pull-right">
                 <a href="${pageContext.request.contextPath}/addSection"><button type="button" class="btn btn-success">Add Section</button></a>
                	<a class="box_toggle fa fa-chevron-down"></a>
                   <!--  <a class="box_setting fa fa-cog" data-toggle="modal" href="#section-settings"></a>
                    <a class="box_close fa fa-times"></a> -->
                     
                </div>
                 
            </header> 
            <div class="content-body">    <div class="row">
            <c:if test="${sessionScope.errorMsg!=null and sessionScope.errorMsg==false}">
            <div class="col-lg-12">
    		          <div class="alert alert-success alert-dismissible fade in">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
                <strong>Success : </strong> ${sessionScope.successMsg}</div>
        	                                       </div> 
            </c:if>
            
            <c:if test="${sessionScope.errorMsg!=null and sessionScope.errorMsg==true}">
            <div class="col-lg-12">
    		          <div class="alert alert-danger alert-dismissible fade in">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
                <strong>Error : </strong> ${sessionScope.successMsg}</div>
        	                                       </div> 
            </c:if>
            
        <div class="col-xs-12">


            <table id="example-11" class="table table-striped dt-responsive display">
                <thead>
                    <tr>
                   		<th width="5%">#</th>
                        <th>Name</th>
                       
                        <th width="10%">Action</th> 
                    </tr>
                </thead>
 
                <tbody>
                        <c:forEach items="${list}" var="list" varStatus="count">
									<tr>
										<td>${count.index+1}</td>
										<td>${list.sectionName}</td> 
										<td>   
										<a
											href="#myModal" data-toggle="modal" onclick="getPageId(${list.pageId})" ><button type="button" class="btn btn-info"> Add Content</button></a></td>
									</tr> 
									
									 <c:forEach items="${list.catList}" var="catList">
										<tr>
											<td> </td>
											<td><span style="padding-right: 20px;"></span>-${catList.catName}</td> 
											<td> <a
											href="#myModal" data-toggle="modal" onclick="getPageId(${catList.pageId})" ><button type="button" class="btn btn-info"> Add Content</button></a> </td>
										</tr>
										  <c:forEach items="${catList.subCatList}" var="subCatList" >
												<tr>
													<td> </td>
													<td><span style="padding-right: 40px;"></span>--${subCatList.subCatName}</td> 
													<td>  <a
											href="#myModal" data-toggle="modal" onclick="getPageId(${subCatList.pageId})" ><button type="button" class="btn btn-info"> Add Content</button></a></td>
												</tr>
											</c:forEach>   
									</c:forEach>  
								</c:forEach>  
                </tbody>
            </table>




        </div>
    </div>
    </div>
        </section></div>



 


<!-- MAIN CONTENT AREA ENDS -->
    </section>
    </section>
    <!-- END CONTENT -->
     

     


     </div>
    <!-- END CONTAINER -->
<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->

   <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
   
   <div aria-hidden="true"  role="dialog" tabindex="-1" id="myModal" class="modal fade" style="display: none;">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                <h4 class="modal-title">Select Module</h4>
                            </div>
                            <div class="modal-body">
                                <form role="form" action="${pageContext.request.contextPath}/showModuleForm" method="get">
								<input type="hidden" class="form-control" id="pageId" name="pageId">
                                    <div class="form-group">
                                        <label for="modalname1" class="form-label">Select Module</label>
                                        
		                                     <select name="moduleId" id="moduleId" class="form-control" required>
		                                     <option value="" >Select Module</option>
				                                   <c:forEach items="${mdulesList}" var="mdulesList" >
				                                  			<option value="${mdulesList.id}" >${mdulesList.name}</option>
				                                   		 
				                                  	</c:forEach>
		                                    </select>
                                    </div>
                                      
                                   
                                    <button type="submit" class="btn btn-primary">Submit</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
   
   <script>
   
   function getPageId(pageId) {
		 
	   document.getElementById("pageId").value = pageId;

	}
   
function clearSessionAttribute() {
	 
	 

	$.getJSON('${clearSessionAttribute}', {
  
		ajax : 'true',

	}, function(data) { 
		 
	
	});

}
 </script>
</body>
</html>
