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
                <!-- PAGE HEADING TAG - START --><h1 class="title">Add FAQ</h1><!-- PAGE HEADING TAG - END -->                            </div>
			 
                                
        </div>
    </div>
    <div class="clearfix"></div>
    <!-- MAIN CONTENT AREA STARTS -->
 
	<div class="col-lg-12">
    	                                                            
    </div>
    
    
	
    <div class="col-lg-12">
        <section class="box ">
       
                <header class="panel_header">
                    <h2 class="title pull-left">Add FAQ</h2>
                   
                    <div class="actions panel_actions pull-right">
                	      <a href="${pageContext.request.contextPath}/faqList"><button type="button" class="btn btn-info"><< Back</button></a>
                	       <a class="box_toggle fa fa-chevron-down"></a>
                </div>
                     
                </header>
                
                    
                <div class="content-body"> 
                    <div class="row">
                    <div class="col-md-12">
                         <form class="form-horizontal" action="${pageContext.request.contextPath}/insertFaqForm" method="post" name="form_sample_2" id="form_sample_2">               
                    
                    <ul class="nav nav-tabs">
                        <li class="active">
                            <a href="#home" data-toggle="tab">
                                <i class="fa fa-home"></i> Home
                            </a>
                        </li>
                       
                        
                    </ul>

                    <div class="tab-content">
                        <div class="tab-pane fade in active" id="home">

                            <div>

                               
                      
                      <div class="col-xs-12"> 
                    		 <div class="form-group">
                                <label class="control-label col-sm-2" for="page_name">Page/Menu Title :<span class="text-danger">*</span></label>
                                <div class="col-sm-10">
                                  <input type="text" class="form-control" id="page_name" name="page_name" placeholder="Page/Menu Title" value="${page.pageName}"  readonly>
                                </div>
                              </div>
                        </div>
                        
                      <c:forEach items="${languagesList}" var="languagesList" >
                    	 <h5 class="title pull-left">${languagesList.name}</h5>
                      
                       <div class="col-xs-12"> 
                    		  
                         <div class="form-group">
                                <label class="control-label col-sm-2" for="heading1">Question : </label>
                                <div class="col-sm-10">
                                  <input type="text" class="form-control" id="question${languagesList.languagesId}" name="question${languagesList.languagesId}" placeholder="Question" value="" >
                                </div>
                            </div>
                             
                              <div class="form-group">
                                <label class="control-label col-sm-2" for="page_description1">Answer :</label>
                                <div class="col-sm-10">
                                    <textarea  class="ckeditor" style="width: 100%; height: 250px; font-size: 14px; line-height: 23px;padding:15px;" name="ans${languagesList.languagesId}" id="ans${languagesList.languagesId}"  ></textarea>
                                </div>
                              </div> 
                              
                             </div>
                     </c:forEach>
                     
                     
                     
                        <div class="col-xs-12"> 
                        <hr>
                             	 
                                <div class="form-group">
                                <label class="control-label col-sm-2" for="page_order">Sort Order :<span class="text-danger">*</span></label>
                                <div class="col-sm-10">
                                  <input type="number" class="form-control" id="sortNo" name="sortNo" placeholder="Sort Order" value="" required>
                                </div>
                              </div>
                                    
                               <div class="form-group">
                                <label class="control-label col-sm-2" for="status">Status  :<span class="text-danger">*</span></label>
                                <div class="col-sm-10">
                                <select id="status" name="status" class="form-control" required >
                                                                    <option value="1" >Active</option>
                    							<option value="0" >Inactive</option>
                     
                 
                                  </select>
                                </div>
                              </div>
                              <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                  <button type="submit" class="btn btn-primary">Submit</button>  <button type="reset" class="btn btn-default">Reset</button> 
                                </div>
                              </div>
                            
                            
                        </div>
                        
                        <div class="clearfix"></div>

                            </div>

                        </div>
                    <!--     <div class="tab-pane fade" id="metatage">

                            <p>That said, in some situations it may be desirable to turn this functionality off. Therefore, we also provide the ability to disable the data attribute API by unbinding all events on the document namespaced with data-api. </p>

                        </div>
                          -->
                         
                    </div>
 				</form>
                </div>
                    
                    </div> 
                </div>
                 
        </section>
        
    </div> 
    
    
    <div class="col-lg-12">
    <section class="box "> 
             <header class="panel_header">
                <h2 class="title pull-left">FAQ List</h2>
                <div class="actions panel_actions pull-right">
                 <a href="${pageContext.request.contextPath}/sectionTreeList"><button type="button" class="btn btn-success">Add FAQ</button></a>
                	<a class="box_toggle fa fa-chevron-down"></a>
                   <!--  <a class="box_setting fa fa-cog" data-toggle="modal" href="#section-settings"></a>
                    <a class="box_close fa fa-times"></a> -->
                     
                </div>
                 
            </header> 
            <div class="content-body">    <div class="row"> 
            
        <div class="col-xs-12">


            <table id="example-1" class="table table-striped dt-responsive display">
                <thead>
                    <tr>
                   		<th width="5%">Sr No</th>
                        <th>Page Name</th> 
                        <th>Desc</th> 
                        <th width="10%">Action</th> 
                    </tr>
                </thead>

                 
                <tbody>
                        <c:forEach items="${getPagesModuleList}" var="getPagesModuleList" varStatus="count">
									<tr  >
										<td>${count.index+1}</td>
										<td>${getPagesModuleList.pageName} (${getPagesModuleList.secctionName})</td> 
										<td>${getPagesModuleList.content}</td>  
										<td><a
											href="${pageContext.request.contextPath}/editFaqContent/${getPagesModuleList.primaryKeyId}"><span
												class="glyphicon glyphicon-edit" data-animate=" animated fadeIn "
												rel="tooltip" ></span></a> | <a
											href="${pageContext.request.contextPath}/deleteFaqContent/${getPagesModuleList.primaryKeyId}"
											onClick="return confirm('Are you sure want to delete this record');" rel="tooltip" data-color-class = "danger" data-animate=" animated fadeIn " data-toggle="tooltip" data-original-title="Delete  record"><span
												class="glyphicon glyphicon-remove"></span></a></td>
									</tr>
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
   
   <script type="text/javascript">
            jQuery(document).ready(function($) {
            
			// sub_menu
			 $('#sub_menu1').click( function(e) {
			//ajax send this to php page
				var def=1;
						 if($("#sub_menu").prop('checked')==true){
						   $('#main_menu').prop('checked', true);
						   
						} else {
							 
						}
			 });
			
			 //Example 2
      
	 
			
				$(document).on('click', '#close-preview', function(){ 
					$('.image-preview').popover('hide');
					// Hover befor close the preview
					$('.image-preview').hover(
						function () {
						   $('.image-preview').popover('show');
						}, 
						 function () {
						   $('.image-preview').popover('hide');
						}
					);    
				});

				$(function() {
					// Create the close button
					var closebtn = $('<button/>', {
						type:"button",
						text: 'x',
						id: 'close-preview',
						style: 'font-size: initial;',
					});
					closebtn.attr("class","close pull-right");
					// Set the popover default content
					$('.image-preview').popover({
						trigger:'manual',
						html:true,
						title: "<strong>Preview</strong>"+$(closebtn)[0].outerHTML,
						content: "There's no image",
						placement:'bottom'
					});
					// Clear event
					$('.image-preview-clear').click(function(){
						//$('.image-preview').attr("data-content","").popover('hide');
						$('.image-preview-filename').val("");
						$('.image-preview-clear').hide();
						$('#header_image input:file').val("");
						$(".image-preview-input-title").text("Browse"); 
						$("#thumbkishore").attr("src",'');
						$(".thumbkishorediv").hide();
					}); 
					// Create the preview image
					$("#header_image").change(function (){     
						var img = $('<img/>', {
							id: 'dynamic',
							width:250,
							height:200,
						});      
						
						var file = this.files[0];
						var reader = new FileReader();
						// Set preview image into the popover data-content
						reader.onload = function (e) {

							$(".image-preview-input-title").text("Change");
							$(".image-preview-clear").show();
							$(".image-preview-filename").val(file.name);            
							img.attr('src', e.target.result);
							//alert(e.target.result);
							///alert($(img)[0].outerHTML);
							//$(".image-preview").attr("data-content",$(img)[0].outerHTML).popover("show");
							//
							//alert(e.target.result);
							$("#thumbkishore").attr("src",e.target.result);
							 
							$(".thumbkishorediv").show();
						}        
						reader.readAsDataURL(file);
					});  
					
					
					
						// Clear event
					$('.image-preview-clear2').click(function(){
						//$('.image-preview').attr("data-content","").popover('hide');
						$('.image-preview-filename2').val("");
						$('.image-preview-clear2').hide();
						$('.image-preview-input2 input:file').val("");
						$(".image-preview-input-title2").text("Browse"); 
						$("#thumbkishor2e").attr("src",'');
						$(".thumbkishore2div").hide();
					}); 
					// Create the preview image
					$("#images").change(function (){     
						var img = $('<img/>', {
							id: 'dynamic',
							width:250,
							height:200,
						});      
						
						var file = this.files[0];
						var reader = new FileReader();
						// Set preview image into the popover data-content
						reader.onload = function (e) {

							$(".image-preview-input-title2").text("Change");
							$(".image-preview-clear2").show();
							$(".image-preview-filename2").val(file.name);            
							img.attr('src', e.target.result);
							//alert(e.target.result);
							///alert($(img)[0].outerHTML);
							//$(".image-preview").attr("data-content",$(img)[0].outerHTML).popover("show");
							//
							//alert(e.target.result);
							$("#thumbkishore2").attr("src",e.target.result);
							 
							$(".thumbkishore2div").show();
						}        
						reader.readAsDataURL(file);
					}); 
					
					
				});
			
            });
            </script>
            
</body>
</html>