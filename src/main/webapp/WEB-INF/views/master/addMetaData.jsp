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
                <!-- PAGE HEADING TAG - START --><h1 class="title">Add Meta Data</h1><!-- PAGE HEADING TAG - END -->                            </div>
			 
                                
        </div>
    </div>
    <div class="clearfix"></div>
    <!-- MAIN CONTENT AREA STARTS -->
 
	<div class="col-lg-12">
    	                                                            
    </div>
	
    <div class="col-lg-12">
        <section class="box ">
       
                <header class="panel_header">
                    <h2 class="title pull-left"><c:choose><c:when test="${isEdit==1}">Edit Meta Data</c:when><c:otherwise>Add Meta Data</c:otherwise></c:choose></h2>
                   
                  
                     
                </header>
                
                   <form class="form-horizontal" id="addSupplier" action="${pageContext.request.contextPath}/insertMetaData" 
                   onsubmit="return confirm('Do you really want to submit the form?');" method="post">
                   
                <div class="content-body"> 
                    <div class="row">
                    
                    <c:forEach items="${languagesList}" var="languagesList" >
                     <h5 class="title pull-left">${languagesList.name}</h5>
                     
                     <%-- <c:choose>
                     	<c:when test="${isEdit==1}"> --%>
                     	 
                     		<c:forEach items="${editMetaData}" var="metaList" >
                     		 
                     		 
                     			<c:choose>
                     				<c:when test="${metaList.languageId==languagesList.languagesId}">
                     					<div class="col-xs-12">
                                	  
				                               <div class="form-group">
				                                <label class="control-label col-sm-2" for="config_mail_protocol">Meta Site Title : <span class="text-danger">*</span> </label>
				                                <div class="col-sm-10">
				                                   <input id="siteName${languagesList.languagesId}" class="form-control"
												placeholder="Meta Site Name"  value="${metaList.siteTitle}"  style="text-align: left;" name="siteName${languagesList.languagesId}" type="text" required>
				                                </div>
				                              </div>
				                            
				                           
				                              <div class="form-group">
				                                <label class="control-label col-sm-2" for="config_smtp_host">Meta Description: <span class="text-danger">*</span> </label>
				                                <div class="col-sm-10">
				                                <input id="metaDescription${languagesList.languagesId}" class="form-control"
												placeholder="Meta Description"  value="${metaList.metaDescription}"  style="text-align: left;" name="metaDescription${languagesList.languagesId}" type="text"  required>
				                                </div>
				                              </div>
				                                                          
				                               <div class="form-group">
				                                <label class="control-label col-sm-2" for="config_smtp_host">Meta Keywords :<span class="text-danger">*</span> </label>
				                                <div class="col-sm-10">
				                                <input id="metaKeywords${languagesList.languagesId}" class="form-control"
												placeholder="Meta Keyword"  value="${metaList.metaKeywords}"  style="text-align: left;" name="metaKeywords${languagesList.languagesId}" type="text"  required>
				                                </div>
				                              </div>
				                               
				                                <div class="form-group">
				                                <label class="control-label col-sm-2" for="config_smtp_host">Meta Author :<span class="text-danger">*</span> </label>
				                                <div class="col-sm-10">
				                                <input id="metaAuthor${languagesList.languagesId}" class="form-control"
												placeholder="Meta Author"  value="${metaList.metaAuthor}"  style="text-align: left;" name="metaAuthor${languagesList.languagesId}" type="text"  required>
				                                </div>
				                              </div>
				                        </div>
                     				</c:when>
                     			</c:choose>
                     		
                     		</c:forEach>
                     	<%-- </c:when>
                     	<c:otherwise>
                     		<div class="col-xs-12">
                                	  
                               <div class="form-group">
                                <label class="control-label col-sm-2" for="config_mail_protocol">Meta Site Title: <span class="text-danger">*</span> </label>
                                <div class="col-sm-10">
                                   <input id="siteName${languagesList.languagesId}" class="form-control"
								placeholder="Meta Site Name"    style="text-align: left;" name="siteName${languagesList.languagesId}" type="text" required>
                                </div>
                              </div>
                            
                           
                              <div class="form-group">
                                <label class="control-label col-sm-2" for="config_smtp_host">Meta Description:  <span class="text-danger">*</span> </label>
                                <div class="col-sm-10">
                                <input id="metaDescription${languagesList.languagesId}" class="form-control"
								placeholder="Meta Description"    style="text-align: left;" name="metaDescription${languagesList.languagesId}" type="text" required>
                                </div>
                              </div>
                               
                                 <div class="form-group">
                                <label class="control-label col-sm-2" for="config_smtp_host">Meta Author : <span class="text-danger">*</span> </label>
                                <div class="col-sm-10">
                                <input id="metaAuthor${languagesList.languagesId}" class="form-control"
								placeholder="Meta Author"  style="text-align: left;" name="metaAuthor${languagesList.languagesId}" type="text"  required>
                                </div>
                              </div>
                              
                        </div>
                     	</c:otherwise>
                     </c:choose> --%>
                        
                        </c:forEach>
                        
                        <div class="col-xs-12"> 
                      <%--   <input id="id" value="${editMetaData.id}" name="id" type="hidden"  > --%>
                        
                      
                               
                              <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                  <button type="submit" class="btn btn-primary">Submit</button> <button type="reset" class="btn btn-default">Reset</button> 
                                  
                                </div><!--  -->
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