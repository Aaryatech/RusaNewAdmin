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
                <!-- PAGE HEADING TAG - START --><h1 class="title">Edit Social Channels</h1><!-- PAGE HEADING TAG - END -->                            </div>
			 
                                
        </div>
    </div>
    <div class="clearfix"></div>
    <!-- MAIN CONTENT AREA STARTS -->
 
	<div class="col-lg-12">
    	                                                            
    </div>
	
    <div class="col-lg-12">
        <section class="box ">
       
                <header class="panel_header">
                    <h2 class="title pull-left"><c:choose><c:when test="${isEdit==1}">Social Channels</c:when><c:otherwise>Edit Social Channels</c:otherwise></c:choose></h2>
                   
                    <div class="actions panel_actions pull-right">
                	      <a href="${pageContext.request.contextPath}/socialChannelList"><button type="button" class="btn btn-info"><< Back</button></a>
                	       <a class="box_toggle fa fa-chevron-down"></a>
                </div>
                     
                </header>
                 <%-- 
            <c:if test="${sessionScope.successMsg!=null}">
            <div class="col-lg-12">
    		          <div class="alert alert-success alert-dismissible fade in">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
                
                <strong>Success : </strong> ${sessionScope.successMsg}</div>
        	          </c:if>  --%>                          
           
            <div class="content-body">   
             <div class="row">
                   <form class="form-horizontal" id="addSupplier" action="${pageContext.request.contextPath}/editChannel" 
                   method="post">
             
                    <div class="col-xs-12"> 
                    
                                      
                        <input id="id" value="${editChannel.id}" name="id" type="hidden"  >
                        
                        <c:choose>
    					<c:when test="${isEdit=='1'}">
      
                         <div class="form-group">
                                <label class="control-label col-sm-2" for="config_mail_protocol">Title : <span class="text-danger">*</span> </label>
                                <div class="col-sm-10"> 
                                  <input id="contactName" class="form-control" onchange="trim(this)"
								placeholder="Channel Title" value="${editChannel.title}"  style="text-align: left;" name="title" type="text" readonly  required>
                                </div>
                              </div>
                              
                              <br />
   						
					
                              <div class="form-group">
                                <label class="control-label col-sm-2" for="config_mail_protocol">URL Links : <span class="text-danger">*</span> </label>
                                <div class="col-sm-10"> 
                                  <input id="firstname" class="form-control" onchange="trim(this)"
								placeholder="URL Links" value="${editChannel.urllinks}"  style="text-align: left;" name="urlLink" type="text"  required>
                                </div>
                              </div>
                                                                          
                     		    <div class="form-group">
                                <label class="control-label col-sm-2" for="config_mail_protocol">Sort No : <span class="text-danger">*</span> </label>
                                <div class="col-sm-10"> 
                                  <input id="seqNo" class="form-control" onchange="trim(this)"
								placeholder="Sort No" value="${editChannel.sortNo}"  style="text-align: left;" name="seqNo" type="number" required>
                                </div>
                              </div>
                              
                                <div class="form-group">
                                <label class="control-label col-sm-2" for="config_mail_protocol">Is Active : <span class="text-danger">*</span> </label>
                                <div class="col-sm-10">
                                   
                                  <select name="isActive" id="isActive" class="form-control">
                                  	<c:choose>
                                  		<c:when test="${editChannel.isActive==0}">
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
                               </c:when>    
   					
					</c:choose>
                               
                              <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                  <button type="submit" class="btn btn-primary">Submit</button> <button type="reset" class="btn btn-default">Reset</button> 
                                  
                                </div>
                              </div> 
                            
                        </div>
                  </form>
                  </div>
                  </div>
        </section>
        
    </div> 

<!-- MAIN CONTENT AREA ENDS -->
  
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
       <script type="text/javascript">
            jQuery(document).ready(function($) {
                $(document).on('click', '#close-preview1', function(){ 
                    $('.image-preview1').popover('hide');
                    // Hover befor close the preview
                    $('.image-preview1').hover(
                    function () {
                        $('.image-preview1').popover('show');
                        }, 
                    function () {
                        $('.image-preview1').popover('hide');
                        }
                    );    
                });
            });
            $(function() {
            
                
                				//image 1
                
                
                					// Create the preview image
                					$(".browseimage").change(function (){
                						var img = $('<img/>', {
                							id: 'dynamic',
                							width:250,
                							height:200,
                						});
                					var imgid =	$(this).attr('id');
                					//alert(imgid);
                						var file = this.files[0];
                						var reader = new FileReader();
                						// Set preview image into the popover data-content
                						reader.onload = function (e) {
                
                
                
                							$(".image-preview-filename"+imgid).html(file.name);
                							img.attr('src', e.target.result);
                
                							$(".temppreviewimageki"+imgid).attr("src",$(img)[0].src);
                							$(".temppreviewimageki"+imgid).show();
                						
                                            $('.image-preview-clear'+imgid).show();
                							//alert(e.target.result);
                							///alert($(img)[0].outerHTML);
                							//$(".image-preview1").attr("data-content",$(img)[0].outerHTML).popover("show");
                						}
                						reader.readAsDataURL(file);
                					});
                
                
                                    // Clear event
                            $('.image-preview-clear').click(function(){
                                var imgid =	$(this).attr('id');
                              
                                $('.image-preview-filename'+imgid).html("No file selected");
                                $('.image-preview-clear'+imgid).val("");
                                $('.image-preview-clear'+imgid).hide();
                                $(".image-preview-input-title"+imgid).text("Browse"); 
                                $(".temppreviewimageki"+imgid).attr("src",'');
                                $(".temppreviewimageki"+imgid).hide();
                            }); 
                					//end
             
            });
            
            // TableManageButtons.init();
            
        </script>
        <script>
            function clearSessionAttribute() {
            	 
            	 
             
            	$.getJSON('${clearSessionAttribute}', {
              
            		ajax : 'true',
            
            	}, function(data) { 
            		 
            	
            	});
            
            }
             
   
</body>
</html>