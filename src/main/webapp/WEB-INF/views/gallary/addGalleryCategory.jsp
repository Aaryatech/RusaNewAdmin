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
                <!-- PAGE HEADING TAG - START --><h1 class="title"> Category</h1><!-- PAGE HEADING TAG - END -->                            </div>
			 
                                
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
                	      <a href="${pageContext.request.contextPath}/galleryCategoryList"><button type="button" class="btn btn-info"><< Back</button></a>
                	       <a class="box_toggle fa fa-chevron-down"></a>
                </div>
                     
                </header>
                
                   <form class="form-horizontal" id="addSupplier" action="${pageContext.request.contextPath}/insertGalleryCategory" 
                    method="post">
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
                   
                <div class="content-body"> 
                    <div class="row">
                   <span class="text-danger">*</span> indicates mandatory fields
                    
                    <c:forEach items="${languagesList}" var="languagesList" >
                     <h5 class="title pull-left">${languagesList.name}</h5>
                     <c:set var="find" value="0"></c:set>
                     <c:choose>
                     	<c:when test="${isEdit==1}">
                     	 
                     		<c:forEach items="${editGallaryCategory.gallaryCategoryDescriptioinList}" var="gallaryCategoryDescriptioinList" >
                     		 
                     		 
                     			<c:choose>
                     				<c:when test="${gallaryCategoryDescriptioinList.languageId==languagesList.languagesId}">
                     					 
                     					<div class="col-xs-12">
                                	  
				                               <div class="form-group">
				                                <label class="control-label col-sm-2" for="config_mail_protocol">Category Name : <span class="text-danger">*</span> </label>
				                                <div class="col-sm-10"> 
												<input id="catName${languagesList.languagesId}" class="form-control"
								placeholder="Category Name" value="${gallaryCategoryDescriptioinList.cateName}"  style="text-align: left;" name="catName${languagesList.languagesId}" type="text" required>
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
												<input id="catName${languagesList.languagesId}" class="form-control"  onchange="trim(this)"
								placeholder="Category Name" value="${categoryDescriptionList.catName}"  style="text-align: left;" name="catName${languagesList.languagesId}" type="text" required>
				                                </div>
				                              </div>
				                             
				                        </div>
                     	</c:otherwise>
                     </c:choose>
                        
                        </c:forEach>
                        
                        <div class="col-xs-12"> 
                        <input id="catId" value="${editGallaryCategory.galleryCatId}" name="catId" type="hidden"  >
                         
                              
                         <div class="form-group">
                                <label class="control-label col-sm-2" for="config_mail_protocol">Sort No : <span class="text-danger">*</span> </label>
                                <div class="col-sm-10"> 
                                  <input id="seqNo" class="form-control"  onchange="trim(this)"
								placeholder="Sequence No" value="${editGallaryCategory.sortNo}"  style="text-align: left;" name="seqNo" type="number" required>
                                </div>
                              </div>
                            
                            <div class="form-group">
                                <label class="control-label col-sm-2" for="config_mail_protocol">Is Active : <span class="text-danger">*</span> </label>
                                <div class="col-sm-10">
                                   
                                  <select name="isActive" id="isActive" class="form-control">
                                    <c:choose>
                                  		<c:when test="${editGallaryCategory.isActive==0}">
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
    <script>
			function trim(el) {
				el.value = el.value.replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
				replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space 
				replace(/\n +/, "\n"); // Removes spaces after newlines
				return;
			}
			</script>
</body>
</html>