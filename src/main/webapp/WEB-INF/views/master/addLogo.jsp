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
<style>
                    
            .image-preview-input {
                position: relative;
                overflow: hidden;
                margin: 0px;    
                color: #333;
                background-color: #fff;
                border-color: #ccc;    
            }
            .image-preview-input input[type=file] {
                position: absolute;
                top: 0;
                right: 0;
                margin: 0;
                padding: 0;
                font-size: 20px;
                cursor: pointer;
                opacity: 0;
                filter: alpha(opacity=0);
            }
            .image-preview-input-title {
                margin-left:2px;
            }
        </style>

    <!-- BEGIN BODY -->
    <body class=" " onload="clearSessionAttribute()"><!-- START TOPBAR -->
<jsp:include page="/WEB-INF/views/include/topbar.jsp"></jsp:include>
<!-- END TOPBAR -->
<!-- START CONTAINER -->
<div class="page-container row-fluid container-fluid">
<c:url var="clearSessionAttribute" value="/clearSessionAttribute" />
    <!-- SIDEBAR - START -->

<jsp:include page="/WEB-INF/views/include/left.jsp"></jsp:include>
<!--  SIDEBAR - END -->
    <!-- START CONTENT -->
<section id="main-content" class=" ">
     <section class="wrapper main-wrapper row" style="">

    <div class="col-xs-12">
        <div class="page-title">

            <div class="pull-left">
                <!-- PAGE HEADING TAG - START --><h1 class="title">logo</h1><!-- PAGE HEADING TAG - END -->                            </div>
			 
                                
        </div>
    </div>
    <div class="clearfix"></div>
    <!-- MAIN CONTENT AREA STARTS -->
 
	<div class="col-lg-12">
    	                                                            
    </div>
	
    <div class="col-lg-12">
        <section class="box ">
       
                <header class="panel_header">
                    <h2 class="title pull-left"><c:choose><c:when test="${isEdit==1}">Edit Slider Image</c:when><c:otherwise>Add Logo</c:otherwise></c:choose></h2>
                   
                    <div class="actions panel_actions pull-right">
                	      <a href="${pageContext.request.contextPath}/sliderPicList"><button type="button" class="btn btn-info"><< Back</button></a>
                	       <a class="box_toggle fa fa-chevron-down"></a>
                </div>
                     
                </header>
                
                   <form class="form-horizontal" id="addSupplier" action="${pageContext.request.contextPath}/updateOrInsertLogo" 
                   onsubmit="return confirm('Do you really want to submit the form?');" method="post" enctype="multipart/form-data">
                   
                <div class="content-body"> 
                    <div class="row">
                    
                    
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
                    
                     <h5 class="title pull-left">${languagesList.name}</h5>
                       
                        <div class="col-xs-12"> 
                        
                         <div class="form-group">  
                                 <label class="col-md-2 control-label">Current Logo</label>
                                          <div class="col-md-10">
                                                  <img src="${url}${logo.logoMain}" style="width:150px; height:auto">
                                                  
                                          </div>
 								</div>
 								
                           
                       		<div class="form-group">
                                    <label class="col-md-2 control-label">Main Logo:</label>
                                     <div class="col-md-10">
                                        <div class="row col-md-5">
                                     <img src="" id="temppreviewimageki1" class="temppreviewimageki1" style="width:200px; height:auto;display:none">								 </div>
                                        <div class="row col-md-10">
                                
                                        <div class="input-group image-preview1" data-original-title="" title="">
                                    
                                     <input type="text" class="form-control image-preview-filename1" disabled="disabled"> <!-- don't give a name === doesn't send on POST/GET -->
                                            <span class="input-group-btn">
                                                <!-- image-preview-clear button -->
                                                <button type="button" class="btn btn-default image-preview-clear1" style="display:none;">
                                                    <span class="glyphicon glyphicon-remove"></span> Clear
                                                </button>
                                            <!-- image-preview-input -->
                                                <div class="btn btn-default image-preview-input">
                                                    <span class="glyphicon glyphicon-folder-open"></span>
                                                    <span class="image-preview-input-title image-preview-input-title1">Browse</span>
                                                    <input type="file" accept="image/png, image/jpeg, image/gif" class="browseimage1" id="1" name="mainLogo"> <!-- rename it -->
                                                   
                                                </div>
                                            </span>
                                        </div>
                                         <span class="help-block">* Only jpg,gif,png</span>
                                          
                                    </div>
                                      </div>
                                 </div>
                                 
                                 <div class="form-group">  
                                 <label class="col-md-2 control-label">Current Logo2</label>
                                          <div class="col-md-10">
                                                  <img src="${url}${logo.logo2}" style="width:150px; height:auto">
                                                         
                                          </div>
 								</div>
                                 
                                  <div class="form-group">
                                    <label class="col-md-2 control-label"> Logo2:</label>
                                     <div class="col-md-10">
                                        <div class="row col-md-5">
                                     <img src="" id="temppreviewimageki2" class="temppreviewimageki2" style="width:200px; height:auto;display:none">								 </div>
                                        <div class="row col-md-10">
                                
                                        <div class="input-group image-preview2" data-original-title="" title="">
                                    
                                     <input type="text" class="form-control image-preview-filename2" disabled="disabled"> <!-- don't give a name === doesn't send on POST/GET -->
                                            <span class="input-group-btn">
                                                <!-- image-preview-clear button -->
                                                <button type="button" class="btn btn-default image-preview-clear2" style="display:none;">
                                                    <span class="glyphicon glyphicon-remove"></span> Clear
                                                </button>
                                            <!-- image-preview-input -->
                                                <div class="btn btn-default image-preview-input">
                                                    <span class="glyphicon glyphicon-folder-open"></span>
                                                    <span class="image-preview-input-title image-preview-input-title2">Browse</span>
                                                    <input type="file" accept="image/png, image/jpeg, image/gif" class="browseimage2" id="2" name="Logo2"> <!-- rename it -->
                                                   
                                                </div>
                                            </span>
                                        </div>
                                         <span class="help-block">* Only jpg,gif,png</span>
                                          
                                    </div>
                                      </div>
                                 </div>
                                 
                                 <div class="form-group">  
                                 <label class="col-md-2 control-label">Current Logo3</label>
                                          <div class="col-md-10">
                                                  <img src="${url}${logo.logo2}" style="width:150px; height:auto">
                                                         
                                          </div>
 								</div>
 								
                                 <div class="form-group">
                                    <label class="col-md-2 control-label">Logo3:</label>
                                     <div class="col-md-10">
                                        <div class="row col-md-5">
                                     <img src="" id="temppreviewimageki3" class="temppreviewimageki3" style="width:200px; height:auto;display:none">								 </div>
                                        <div class="row col-md-10">
                                
                                        <div class="input-group image-preview3" data-original-title="" title="">
                                    
                                     <input type="text" class="form-control image-preview-filename3" disabled="disabled"> <!-- don't give a name === doesn't send on POST/GET -->
                                            <span class="input-group-btn">
                                                <!-- image-preview-clear button -->
                                                <button type="button" class="btn btn-default image-preview-clear3" style="display:none;">
                                                    <span class="glyphicon glyphicon-remove"></span> Clear
                                                </button>
                                            <!-- image-preview-input -->
                                                <div class="btn btn-default image-preview-input">
                                                    <span class="glyphicon glyphicon-folder-open"></span>
                                                    <span class="image-preview-input-title image-preview-input-title3">Browse</span>
                                                    <input type="file" accept="image/png, image/jpeg, image/gif" class="browseimage3" id="3" name="Logo3"> <!-- rename it -->
                                                   
                                                </div>
                                            </span>
                                        </div>
                                         <span class="help-block">* Only jpg,gif,png</span>
                                          
                                    </div>
                                      </div>
                                 </div>
				                               
                              <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                  <button type="submit" class="btn btn-primary">Submit</button> 
                                  
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

				$(function() {
					
					  
				//image 1
					// Create the close button
					var closebtn1 = $('<button/>', {
						type:"button",
						text: 'x',
						id: 'close-preview1',
						style: 'font-size: initial;',
					});
					closebtn1.attr("class","close pull-right");
					// Set the popover default content
					$('.image-preview1').popover({
						trigger:'manual',
						html:true,
						title: "<strong>Preview</strong>"+$(closebtn1)[0].outerHTML,
						content: "There's no image",
						placement:'bottom'
					});
					// Clear event
					$('.image-preview-clear1').click(function(){
						$('.image-preview1').attr("data-content","").popover('hide');
						$('.image-preview-filename1').val("");
						$('.image-preview-clear1').hide();
						//$('.image-preview-input input:file').val("");
						$(".image-preview-input-title1").text("Browse"); 
						$(".temppreviewimageki1").attr("src",'');
						$(".temppreviewimageki1").hide();
					}); 
					// Create the preview image
					$(".browseimage1").change(function (){     
						var img = $('<img/>', {
							id: 'dynamic',
							width:250,
							height:200,
						});      
					var imgid =	$(this).attr('id');
						var file = this.files[0];
						var reader = new FileReader();
						// Set preview image into the popover data-content
						reader.onload = function (e) {

							$(".image-preview-input-title1").text("Change");
							$(".image-preview-clear1").show();
							$(".image-preview-filename1").val(file.name);   
							img.attr('src', e.target.result);
							
							$(".temppreviewimageki"+imgid).attr("src",$(img)[0].src);         
							$(".temppreviewimageki"+imgid).show();
							//img.attr('src', e.target.result);
							//alert(e.target.result);
							///alert($(img)[0].outerHTML);
							//$(".image-preview1").attr("data-content",$(img)[0].outerHTML).popover("show");
						}        
						reader.readAsDataURL(file);
					});  
					//end  
				});  
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
 </script>
</body>
</html>