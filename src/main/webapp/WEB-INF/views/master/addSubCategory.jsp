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
    
    	<c:url var="getCategoryBySectionId" value="/getCategoryBySectionId"></c:url>
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
                <!-- PAGE HEADING TAG - START --><h1 class="title">Add Sub-Category</h1><!-- PAGE HEADING TAG - END -->                            </div>
			 
                                
        </div>
    </div>
    <div class="clearfix"></div>
    <!-- MAIN CONTENT AREA STARTS -->
 
	<div class="col-lg-12">
    	                                                            
    </div>
	
    <div class="col-lg-12">
        <section class="box ">
       
                <header class="panel_header">
                    <h2 class="title pull-left"><c:choose><c:when test="${isEdit==1}">Edit Sub-Category</c:when><c:otherwise>Add Sub-Category</c:otherwise></c:choose></h2>
                   
                    <div class="actions panel_actions pull-right">
                	      <a href="${pageContext.request.contextPath}/sectionList"><button type="button" class="btn btn-info">< Back</button></a>
                	       <a class="box_toggle fa fa-chevron-down"></a>
                </div>
                     
                </header>
                
                   <form class="form-horizontal" id="addSupplier" action="${pageContext.request.contextPath}/insertSubCategory" 
                   onsubmit="return confirm('Do you really want to submit the form?');" method="post">
                   
                <div class="content-body"> 
                  
                      <div class="form-group">
                                <label class="control-label col-sm-2" for="config_mail_protocol">Section : <span class="text-danger">*</span> </label>
                                <div class="col-sm-10">
                                   
                                 <select name="sectionId" id="sectionId" class="form-control chosen" placeholder="Section" onchange="getCategoryList()"
													required>
													<option value="">Select Section</option>
													<c:forEach items="${sectionList}" var="secList" varStatus="count">
														<c:choose>
															<c:when test="${secList.sectionId==editSecList.sectionId}">
															<option value="${secList.sectionId}" selected><c:out value="${secList.sectionName}"/></option>
															</c:when>
															<c:otherwise>
														<option value="${secList.sectionId}"><c:out value="${secList.sectionName}"/></option>
															</c:otherwise>
														</c:choose>
														
													</c:forEach>
												</select>
                                </div>
                              </div>
                              
                                <div class="form-group">
                                <label class="control-label col-sm-2" for="config_mail_protocol">Category : <span class="text-danger">*</span> </label>
                                <div class="col-sm-10">
                                   
                                 <select id="catId" name="catId" class="form-control chosen" placeholder="Category"
								required>
								<option value="${editSecList.catName}"></option>
							</select>
                                </div>
                              </div>
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
				                                <label class="control-label col-sm-2" for="config_mail_protocol">Sub-Category Name : <span class="text-danger">*</span> </label>
				                                <div class="col-sm-10">
				                                   <input id="sectionName${languagesList.languagesId}" class="form-control"
												placeholder="Sub-Category Name"  value="${sectionDescriptionList.catName}"  style="text-align: left;" name="subCategoryName${languagesList.languagesId}" type="text" required>
				                                </div>
				                              </div>
				                            
				                           
				                              <div class="form-group">
				                                <label class="control-label col-sm-2" for="config_smtp_host">Sub-Category Description:</label>
				                                <div class="col-sm-10">
				                                <input id="sectionDesc${languagesList.languagesId}" class="form-control"
												placeholder="Sub-Category Description"  value="${sectionDescriptionList.catDesc}"  style="text-align: left;" name="subCategoryDesc${languagesList.languagesId}" type="text"  >
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
                                <label class="control-label col-sm-2" for="config_mail_protocol">Sub-Category Name : <span class="text-danger">*</span> </label>
                                <div class="col-sm-10">
                                   <input id="sectionName${languagesList.languagesId}" class="form-control"
								placeholder="Sub-Category Name" style="text-align: left;" name="subCategoryName${languagesList.languagesId}" type="text" required>
                                </div>
                              </div>
                            
                           
                              <div class="form-group">
                                <label class="control-label col-sm-2" for="config_smtp_host">Sub-Category Description:</label>
                                <div class="col-sm-10">
                                <input id="sectionDesc${languagesList.languagesId}" class="form-control"
								placeholder="Sub-Category Description" style="text-align: left;" name="subCategoryDesc${languagesList.languagesId}" type="text"  >
                                </div>
                              </div>
                               
                        </div>
                     	</c:otherwise>
                     </c:choose>
                        
                        </c:forEach>
                        
                        <div class="col-xs-12"> 
                        <input id="sectionId" value="${editSection.sectionId}" name="sectionId" type="hidden"  >
                        
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


 <script type="text/javascript">
		function getCategoryList() {

			var sectionId = document.getElementById("sectionId").value;
alert("Section id: "+sectionId);
			$.getJSON('${getCategoryBySectionId}', {

				sectionId : sectionId,
				ajax : 'true'
			}, function(data) {
			//alert(data);

				var html = '<option value="">Select Category</option>';

				var len = data.length;
				for (var i = 0; i < len; i++) {
					html += '<option value="' + data[i].catId + '">'
							+ data[i].catName + ' &nbsp;&nbsp; </option>';
				}
				html += '</option>';
				$('#catId').html(html);
				$("#catId").trigger("chosen:updated");
			});
		}
	</script>


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