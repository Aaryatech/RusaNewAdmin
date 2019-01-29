<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

  
 
<!DOCTYPE html>
<html class=" ">
    <head>
      
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
                <!-- PAGE HEADING TAG - START --><h1 class="title">Add Category</h1><!-- PAGE HEADING TAG - END -->                            </div>
			 
                                
        </div>
    </div>
    <div class="clearfix"></div>
    <!-- MAIN CONTENT AREA STARTS -->
 
	<div class="col-lg-12">
    	                                                            
    </div>
	
    <div class="col-lg-12">
        <section class="box ">
       
                <header class="panel_header">
                    <h2 class="title pull-left"><c:choose><c:when test="${isEdit==1}">Edit Category</c:when><c:otherwise>Add Category</c:otherwise></c:choose></h2>
                   
                    <div class="actions panel_actions pull-right">
                	      <a href="${pageContext.request.contextPath}/sectionList"><button type="button" class="btn btn-info"><< Back</button></a>
                	       <a class="box_toggle fa fa-chevron-down"></a>
                </div>
                     
                </header>
                
                   <form class="form-horizontal" id="addSupplier" action="${pageContext.request.contextPath}/insertCategory" 
                   onsubmit="return confirm('Do you really want to submit the form?');" method="post">
                   
                <div class="content-body"> 
                    <div class="row">
                    
                    <c:forEach items="${languagesList}" var="languagesList" >
                     <h5 class="title pull-left">${languagesList.name}</h5>
                     
                     <c:choose>
                     	<c:when test="${isEdit==1}">
                     	 
                     		<c:forEach items="${editSection.sectionDescriptionList}" var="sectionDescriptionList" >
                     		 
                     			<c:choose>
                     				<c:when test="${sectionDescriptionList.languageId==languagesList.languagesId}">
                     					<div class="col-xs-12">
                                	  
				                               <div class="form-group">
				                                <label class="control-label col-sm-2" for="config_mail_protocol">Category Name : <span class="text-danger">*</span> </label>
				                                <div class="col-sm-10"> 
												<input id="sectionName${languagesList.languagesId}" class="form-control"
								placeholder="Category Name" value="${editCategory.catName}"  style="text-align: left;" name="sectionName${languagesList.languagesId}" type="text" required>
				                                </div>
				                              </div>
				                            
				                           
				                              <div class="form-group">
				                                <label class="control-label col-sm-2" for="config_smtp_host">Category Description:</label>
				                                <div class="col-sm-10">
				                                
				                                <input id="sectionDesc${languagesList.languagesId}" class="form-control"
								placeholder="Category Description" value="${editCategory.catDesc}"  style="text-align: left;" name="sectionDesc${languagesList.languagesId}" type="text"  >
				                                 
				                                </div>
				                              </div>
				                               
				                        </div>
                     				</c:when>
                     			</c:choose>
                     		
                     		</c:forEach>
                     	</c:when>
                     	<c:otherwise>
                     		 <div class="col-xs-12">
                                	  
				                               <div class="form-group">
				                                <label class="control-label col-sm-2" for="config_mail_protocol">Category Name : <span class="text-danger">*</span> </label>
				                                <div class="col-sm-10"> 
												<input id="sectionName${languagesList.languagesId}" class="form-control"
								placeholder="Category Name" value="${editCategory.catName}"  style="text-align: left;" name="sectionName${languagesList.languagesId}" type="text" required>
				                                </div>
				                              </div>
				                            
				                           
				                              <div class="form-group">
				                                <label class="control-label col-sm-2" for="config_smtp_host">Category Description:</label>
				                                <div class="col-sm-10">
				                                
				                                <input id="sectionDesc${languagesList.languagesId}" class="form-control"
								placeholder="Category Description" value="${editCategory.catDesc}"  style="text-align: left;" name="sectionDesc${languagesList.languagesId}" type="text"  >
				                                 
				                                </div>
				                              </div>
				                               
				                        </div>
                     	</c:otherwise>
                     </c:choose>
                        
                        </c:forEach>
                        
                        <div class="col-xs-12"> 
                        <input id="catId" value="${editSection.sectionId}" name="catId" type="hidden"  >
                        
                        <div class="form-group">
                                <label class="control-label col-sm-2" for="config_mail_protocol">Select Section : <span class="text-danger">*</span> </label>
                                <div class="col-sm-10">
                                   
                                 <select class="form-control"   name="sectionId"  id="sectionId"  required>
                                    <option value="" >Select</option>
                                    <c:forEach items="${sectionList}" var="sectionList" >
                                    <option value="${sectionList.sectionId}">${sectionList.sectionName}</option>
                                    </c:forEach>
                                    </select>
                                </div>
                              </div>
                              
                         <div class="form-group">
                                <label class="control-label col-sm-2" for="config_mail_protocol">Sort No : <span class="text-danger">*</span> </label>
                                <div class="col-sm-10"> 
                                  <input id="seqNo" class="form-control"
								placeholder="Sequence No" value="${editSection.sectionSortNo}"  style="text-align: left;" name="seqNo" type="number" required>
                                </div>
                              </div>
                            
                            <div class="form-group">
                                <label class="control-label col-sm-2" for="config_mail_protocol">Is Active : <span class="text-danger">*</span> </label>
                                <div class="col-sm-10">
                                   
                                  <select name="isActive" id="isActive" class="form-control">
                                    <c:choose>
                                  		<c:when test="${editSection.isActive==0}">
                                  			<option value="1" >YES</option>
                                   			<option value="0" selected>NO</option>
                                  		</c:when>
                                  		<c:otherwise>
                                  			<option value="1" >YES</option>
                                    		<option value="0">NO</option>
                                  		</c:otherwise>
                                  	</c:choose>
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