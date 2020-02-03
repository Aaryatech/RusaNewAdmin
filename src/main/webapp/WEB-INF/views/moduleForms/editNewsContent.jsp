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
                <!-- PAGE HEADING TAG - START --><h1 class="title">Edit News Blog Form</h1><!-- PAGE HEADING TAG - END -->                            </div>
			 
                                
        </div>
    </div>
    <div class="clearfix"></div>
    <!-- MAIN CONTENT AREA STARTS -->
 
	<div class="col-lg-12">
    	                                                            
    </div>
    
    
	
    <div class="col-lg-12">
        <section class="box ">
       
                <header class="panel_header">
                    <h2 class="title pull-left">Edit News Blog Form</h2>
                   
                    <div class="actions panel_actions pull-right">
                	      <a href="${pageContext.request.contextPath}/NewsBlogList"><button type="button" class="btn btn-info"><< Back</button></a>
                	       <a class="box_toggle fa fa-chevron-down"></a>
                </div>
                     
                </header>
                
                    
                <div class="content-body"> 
                    <div class="row">
                    <div class="col-md-12">
                         <form class="form-horizontal" action="${pageContext.request.contextPath}/submtEditNewsBlogForm"
                          method="post" enctype="multipart/form-data" name="form_sample_2" id="form_sample_2">               
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
                                  <input type="text" class="form-control" id="page_name" onchange="trim(this)" name="page_name" placeholder="Page/Menu Title" value="${page.pageName}"  readonly>
                                </div>
                              </div>
                        </div>
                        <div class="col-xs-12"> 
                    		 <div class="form-group">
                                <label class="control-label col-sm-2" for="page_name">News Source URL :<span class="text-danger">*</span></label>
                                <div class="col-sm-10">
                                  <input type="text" class="form-control" id="url_name" onchange="trim(this)" name="url_name" placeholder="News Source URL" value="${editNewsBlog.newsSourceUrlName}" required >
                                </div>
                              </div>
                        </div>
                        
                      <c:forEach items="${languagesList}" var="languagesList" >
                      	<c:forEach items="${editNewsBlog.detailList}" var="detailList" >
                      	
                      	<c:if test="${detailList.languageId==languagesList.languagesId}"> 
                      	
                    	 <h5 class="title pull-left">${languagesList.name}</h5>
                      
                       <div class="col-xs-12"> 
                    		  
                         <div class="form-group">
                                <label class="control-label col-sm-2" for="heading1">Heading :<span class="text-danger">*</span></label>
                                <div class="col-sm-10">
                                  <input type="text" class="form-control" onchange="trim(this)"  id="heading1${detailList.languageId}" name="heading1${detailList.languageId}" placeholder="Heading" value="${detailList.heading}" required>
                                </div>
                            </div>
                              <div class="form-group">
                                <label class="control-label col-sm-2" for="page_description1">Content :</label>
                                <div class="col-sm-10">
                                    <textarea  class="ckeditor"   style="width: 100%; height: 250px; font-size: 14px; line-height: 23px;padding:15px;" name="page_description1${languagesList.languagesId}" id="page_description1${languagesList.languagesId}" >
                                    ${detailList.descriptions}
                                    </textarea>
                                </div>
                              </div> 
                                 <div class="form-group">
                                <label class="control-label col-sm-2" for="meta_title1">Meta Title :<span class="text-danger">*</span></label>
                                <div class="col-sm-10">
                                       <input type="text" class="form-control" onchange="trim(this)" id="meta_title1${languagesList.languagesId}" name="meta_title1${languagesList.languagesId}" placeholder="Meta Title" value="${detailList.pageMetaTitle}"  required>
                                
                                </div>
                              </div> 
                                 <div class="form-group">
                                <label class="control-label col-sm-2" for="meta_description1">Meta Description :</label>
                                <div class="col-sm-10">
                                    <textarea  class="form-control" onchange="trim(this)" style="width: 100%; height: 50px; font-size: 14px; line-height: 23px;padding:15px;" name="meta_description1${languagesList.languagesId}" id="meta_description1${languagesList.languagesId}"   > 
                                     ${detailList.pageMetaDescription}</textarea>
                                </div>
                              </div> 
                                 <div class="form-group">
                                <label class="control-label col-sm-2" for="meta_keyword1">KeyWord :</label>
                                <div class="col-sm-10">
                                     <input type="text" class="form-control"  onchange="trim(this)" id="meta_keyword1${languagesList.languagesId}" name="meta_keyword1${languagesList.languagesId}" placeholder="Meta Keywords" value="${detailList.pageMetaKeyword}"  >
                                
                                </div>
                              </div> 
                             </div>
                             
                             </c:if>
                             </c:forEach>
                             
                     </c:forEach>
                     
                     
                     
                        <div class="col-xs-12"> 
                        <hr>
                            
                              
           					 <input type="hidden" name="mode" id="mode" value="edit_content">
                               			  <input type="hidden" name="pageId" id="pageId" value="${editNewsBlog.pageId}">
                                <div class="form-group">
                                <label class="control-label col-sm-2" for="page_order">Sort Order :<span class="text-danger">*</span></label>
                                <div class="col-sm-10">
                                  <input type="text" class="form-control" id="page_order" name="page_order" placeholder="Sort Order" value="${editNewsBlog.pageOrder}" required>
                                </div>
                              </div>
                              
                               <c:if test="${not empty editNewsBlog.featuredImage}">
                               	<div class="form-group">  
                                 <label class="col-md-2 control-label">Current Image :</label>
                                          <div class="col-md-10">
                                                  <img src="${url}${editNewsBlog.featuredImage}" style="width:150px; height:auto">
                                              	&nbsp 	&nbsp  <input type="checkbox" name="removeImg" value="1"> Remove 
																		</div>
																	</div>
																</c:if>
																<input type="hidden" name="removeImg" value="0"> 
                                 
                                  
                           <div class="form-group row">
                             <label class="control-label col-sm-2" for="category_id">Featured Image :</label>
                              <div class="col-sm-7">
                               								<div class="fileinput-new thumbkishore2div thumbnail" style="width: 250px;   display:none; overflow:hidden">
                                                                <img src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image" id="thumbkishore2" alt=""  style="width:250px; height:auto" >
                                                          </div>
                                                            
                                                           <div class="input-group image-preview2">
                                            
											<input type="text" class="form-control image-preview-filename2" disabled="disabled"> <!-- don't give a name === doesn't send on POST/GET -->
											<span class="input-group-btn">
												<!-- image-preview-clear button -->
												<button type="button" class="btn btn-default image-preview-clear2" style="display:none;">
													<span class="glyphicon glyphicon-remove2"></span> Clear
												</button>
												<!-- image-preview-input -->
                                                
												<div class="btn btn-default image-preview-input">
													<span class="glyphicon glyphicon-folder-open"></span>
													<span class="image-preview-input-title2">Browse</span>
                                                    
													<input type="file" accept="image/png, image/jpeg, image/gif" id="images"   name="images"/> <!-- rename it -->
												</div>
											</span>
										</div>
                                  
                                 Note: Image size should be  width: 960px   and height: 600px. 
                              </div>
                           </div>
                            
                            <div class="form-group">
                                <label class="control-label col-sm-2" for="status">Featured Image Aligned :</label>
                                <div class="col-sm-10">
                                <select id="header_top_alignment" name="header_top_alignment" class="form-control"  >
                                
                                <c:choose>
                                <c:when test="${editNewsBlog.featuredImageAlignment eq 'none'}">
                                	<option value="none"  selected>None</option>
                                    <option value="center"  >Center</option>
                    				<option value="left"  >Left</option>
                                    <option value="right" >Right</option>
                                </c:when>
                                 <c:when test="${editNewsBlog.featuredImageAlignment eq 'center'}">
                                	<option value="none"  >None</option>
                                    <option value="center" selected>Center</option>
                    				<option value="left"  >Left</option>
                                    <option value="right" >Right</option>
                                </c:when>
                                 <c:when test="${editNewsBlog.featuredImageAlignment eq 'left'}">
                                	<option value="none"  >None</option>
                                    <option value="center"  >Center</option>
                    				<option value="left"  selected>Left</option>
                                    <option value="right" >Right</option>
                                </c:when>
                                 <c:when test="${editNewsBlog.featuredImageAlignment eq 'right'}">
                                	<option value="none"   >None</option>
                                    <option value="center"  >Center</option>
                    				<option value="left"  >Left</option>
                                    <option value="right" selected>Right</option>
                                </c:when>
                                	</c:choose>
                                    
                     
                 
                                  </select>
                                </div>
                              </div>
                          
														<c:if test="${not empty editNewsBlog.downloadPdf}">
															<div class="form-group">
																<label class="col-md-2 control-label">Current
																	PDF :</label>
																<div class="col-md-10">
																	<a href="${pdfUrl}${editNewsBlog.downloadPdf}"
																		target="_blank">${editNewsBlog.downloadPdf}</a> 
																		&nbsp	&nbsp	&nbsp
																		<input type="checkbox" name="removePdf" value="1"> Remove <br>
																</div>
																
															</div>
														</c:if>
																<input type="hidden" name="removePdf" value="0"> 
											
 						 <%-- <c:if test="${not empty editNewsBlog.downloadPdf}">
                       	<div class="form-group">  
                                 <label class="col-md-2 control-label">Current PDF :</label>
                  				  <div class="col-sm-10">
							<a href="${url}${editNewsBlog.downloadPdf}" target="_blank">${editNewsBlog.downloadPdf} - ${documentUploadList.fileSize}</a>
						</div>
						</div>
						</c:if> --%>
 										 <div class="form-group row">
                                         <label class="control-label col-sm-2" for="page_pdf">PDF File :</label>
                                          <div class="col-sm-7">
                                             <input type="file" name="pagePdf" id="pagePdf"   class="form-control"  data-parsley-minlength="2" accept=".xlsx,.xls,.doc, .docx,.ppt, .pptx,.txt,.pdf,.zip"  />
                                              
                                          </div>
                                        </div>
                               
                                     <div class="form-group">
                                <label class="control-label col-sm-2" for="config_mail_protocol">Select Section : <span class="text-danger">*</span> </label>
                                <div class="col-sm-10">
                                   
                                  <select name="sectionId" class="form-control" id="s2example-2" multiple="multiple" required>
                                  
                                  	<c:forEach items="${nonSelectedSection}" var="sectionList" >
                                  		 
                                   			<option value="${sectionList.sectionId}">${sectionList.sectionName}</option>
                                  		 
                                  		 
                                  	</c:forEach>
                                    <c:forEach items="${selectedSection}" var="sectionList" >
                                  		 
                                   			<option value="${sectionList.sectionId}" selected>${sectionList.sectionName}</option>
                                  		 
                                  		 
                                  	</c:forEach>
                                    </select>
                                </div>
                              </div>
                                 
                                                	<div class="form-group row">
															<label class="control-label col-sm-2" for="page_pdf">On
																Home Page :</label>
															<div class="col-sm-7">
																<c:choose>
																	<c:when test="${editNewsBlog.detailList[0].exInt1==1}">
																		<input type="radio" name="onHomePage" id="onHomePage" value="0" /> No
                                          							    <input type="radio" name="onHomePage" id="onHomePage" value="1" checked> Yes
                                          							</c:when>
                                          							
																	<c:otherwise>
																		<input type="radio" name="onHomePage" id="onHomePage" value="0" checked> No
                                            							 <input type="radio" name="onHomePage" id="onHomePage" value="1"> Yes                                          	
                                        						  	</c:otherwise>
																</c:choose>

															</div>
														</div>
                                 
                               <div class="form-group">
                                <label class="control-label col-sm-2" for="status">Status  :<span class="text-danger">*</span></label>
                                <div class="col-sm-10">
                                <select id="status" name="status" class="form-control" required >
                                <c:choose>
                                	<c:when test="${editNewsBlog.isActive==0}">
                                				<option value="1" >Active</option>
                   								 <option value="0" selected>Inactive</option>
                                	</c:when>
                                	<c:otherwise>
                                				<option value="1" selected>Active</option>
                   								 <option value="0" >Inactive</option>
                                	</c:otherwise>
                                </c:choose>
                                    
                                                                    
                     
                 
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
                    
                         
                         
                    </div>
 				</form>
                </div>
                    
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
			function trim(el) {
				el.value = el.value.replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
				replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space 
				replace(/\n +/, "\n"); // Removes spaces after newlines
				return;
			}
			</script>
   <script type="text/javascript">
   
   $("#s2example-2").select2({
       placeholder: 'Choose your favorite US Countries',
       allowClear: true
   }).on('select2-open', function() {
       // Adding Custom Scrollbar
       $(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
   });

   
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