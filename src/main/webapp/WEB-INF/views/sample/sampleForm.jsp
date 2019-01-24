<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 
 
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
                <!-- PAGE HEADING TAG - START --><h1 class="title">Site Mail Info</h1><!-- PAGE HEADING TAG - END -->                            </div>
			<div class="pull-right hidden-xs">
                    <ol class="breadcrumb">
                        <li>
                            <a href="http://www.webtreeindia.com/projectdemo/bppatilcollege/webadmin/index.php/dashboard"><i class="fa fa-home"></i>Home</a>
                        </li>
                       
                        <li class="active">
                            <strong>Site Mail Info</strong>
                        </li>
                    </ol>
                </div>
                                
        </div>
    </div>
    <div class="clearfix"></div>
    <!-- MAIN CONTENT AREA STARTS -->
 
	<div class="col-lg-12">
    	                                                            
    </div>
	
    <div class="col-lg-12">
        <section class="box ">
                <header class="panel_header">
                    <h2 class="title pull-left">Mail Info</h2>
                     
                </header>
                <div class="content-body">
                    <div class="row">
                        <div class="col-xs-12">
                              <form class="form-horizontal" action="#" method="post" enctype="multipart/form-data">  
                              
           				 
												  
                               <div class="form-group">
                                <label class="control-label col-sm-2" for="config_mail_protocol">Mail Engine:<!--<span    data-original-title="Only choose 'Mail' unless your host has disabled the php mail function." rel="tooltip" data-color-class = "primary" data-animate=" animated fadeIn" data-toggle="tooltip"   data-placement="top">Help</span>--></label>
                                <div class="col-sm-10">
                                   
                                  <select name="config_mail_protocol" id="config_mail_protocol" class="form-control">
                                    <option value="mail" selected="">Mail</option>
                                    <option value="smtp">SMTP</option>
                                    </select>
                                </div>
                              </div>
                            
                          	 
                              <div class="form-group">
                                <label class="control-label col-sm-2" for="config_smtp_host">SMTP Hostname:</label>
                                <div class="col-sm-10">
                                 <input type="text" class="form-control" name="formfield1" aria-required="true" aria-describedby="formfield1-error" aria-invalid="true">
                                </div>
                              </div>
                               <div class="form-group">
                                <label class="control-label col-sm-2" for="config_smtp_username">SMTP Username:</label>
                                <div class="col-sm-10">
                                   <select class="" id="s2example-1">
                    <option></option>
                    <optgroup label="North America">
                        <option>Alabama</option>
                        <option>Alaska</option>
                        <option>Arizona</option>
                        <option>Arkansas</option>
                        <option>California</option>
                        <option>Colorado</option>
                        <option>Connecticut</option>
                        <option>Delaware</option>
                        <option>Florida</option>
                        <option>Georgia</option>
                        <option>Hawaii</option>
                        <option>Idaho</option>
                        <option>Illinois</option>
                        <option>Indiana</option>
                        <option>Iowa</option>
                        <option>Kansas</option>
                        <option>Kentucky[C]</option>
                        <option>Louisiana</option>
                        <option>Maine</option>
                        <option>Maryland</option>
                        <option>Massachusetts[D]</option>
                        <option>Michigan</option>
                        <option>Minnesota</option>
                        <option>Mississippi</option>
                        <option>Missouri</option>
                        <option>Montana</option>
                        <option>Nebraska</option>
                        <option>Nevada</option>
                        <option>New Hampshire</option>
                        <option>New Jersey</option>
                        <option>New Mexico</option>
                        <option>New York</option>
                        <option>North Carolina</option>
                        <option>North Dakota</option>
                        <option>Ohio</option>
                        <option>Oklahoma</option>
                        <option>Oregon</option>
                        <option>Pennsylvania[E]</option>
                        <option>Rhode Island[F]</option>
                        <option>South Carolina</option>
                        <option>South Dakota</option>
                        <option>Tennessee</option>
                        <option>Texas</option>
                        <option>Utah</option>
                        <option>Vermont</option>
                        <option>Virginia[G]</option>
                        <option>Washington</option>
                        <option>West Virginia</option>
                        <option>Wisconsin</option>
                        <option>Wyoming</option>
                    </optgroup>
                    <optgroup label="Europe">
                        <option>Albania</option>
                        <option>Andorra</option>
                        <option>Armenia</option>
                        <option>Austria</option>
                        <option>Azerbaijan</option>
                        <option>Belarus</option>
                        <option>Belgium</option>
                        <option>Bosnia & Herzegovina</option>
                        <option>Bulgaria</option>
                        <option>Croatia</option>
                        <option>Cyprus</option>
                        <option>Czech Republic</option>
                        <option>Denmark</option>
                        <option>Estonia</option>
                        <option>Finland</option>
                        <option>France</option>
                        <option>Georgia</option>
                        <option>Germany</option>
                        <option>Greece</option>
                        <option>Hungary</option>
                        <option>Iceland</option>
                        <option>Ireland</option>
                        <option>Italy</option>
                        <option>Kosovo</option>
                        <option>Latvia</option>
                        <option>Liechtenstein</option>
                        <option>Lithuania</option>
                        <option>Luxembourg</option>
                        <option>Macedonia</option>
                        <option>Malta</option>
                        <option>Moldova</option>
                        <option>Monaco</option>
                        <option>Montenegro</option>
                        <option>The Netherlands</option>
                        <option>Norway</option>
                        <option>Poland</option>
                        <option>Portugal</option>
                        <option>Romania</option>
                        <option>Russia</option>
                        <option>San Marino</option>
                        <option>Serbia</option>
                        <option>Slovakia</option>
                        <option>Slovenia</option>
                        <option>Spain</option>
                        <option>Sweden</option>
                        <option>Switzerland</option>
                        <option>Turkey</option>
                        <option>Ukraine</option>
                        <option>United Kingdom</option>
                        <option>Vatican City (Holy See)</option>
                    </optgroup>
                    <optgroup label="Asia">
                        <option>Afghanistan</option>
                        <option>Bahrain</option>
                        <option>Bangladesh</option>
                        <option>Bhutan</option>
                        <option>Brunei</option>
                        <option>Cambodia</option>
                        <option>China</option>
                        <option>East Timor</option>
                        <option>India</option>
                        <option>Indonesia</option>
                        <option>Iran</option>
                        <option>Iraq</option>
                        <option>Israel</option>
                        <option>Japan</option>
                        <option>Jordan</option>
                        <option>Kazakhstan</option>
                        <option>Korea North</option>
                        <option>Korea South</option>
                        <option>Kuwait</option>
                        <option>Kyrgyzstan</option>
                        <option>Laos</option>
                        <option>Lebanon</option>
                        <option>Malaysia</option>
                        <option>Maldives</option>
                        <option>Mongolia</option>
                        <option>Myanmar (Burma)</option>
                        <option>Nepal</option>
                        <option>Oman</option>
                        <option>Pakistan</option>
                        <option>The Philippines</option>
                        <option>Qatar</option>
                        <option>Russia</option>
                        <option>Saudi Arabia</option>
                        <option>Singapore</option>
                        <option>Sri Lanka</option>
                        <option>Syria</option>
                        <option>Taiwan</option>
                        <option>Tajikistan</option>
                        <option>Thailand</option>
                        <option>Turkey</option>
                        <option>Turkmenistan</option>
                        <option>United Arab Emirates</option>
                        <option>Uzbekistan</option>
                        <option>Vietnam</option>
                        <option>Yemen</option>
                    </optgroup>
                </select>

                                </div>
                              </div>
                               <div class="form-group">
                                <label class="control-label col-sm-2" for="config_smtp_password">SMTP Password:</label>
                                <div class="col-sm-10">
                                  <input type="text" class="form-control" id="config_smtp_password" name="config_smtp_password" placeholder="SMTP Password" value="" required>
                                </div>
                              </div>
                               <div class="form-group">
                                <label class="control-label col-sm-2" for="config_smtp_port">SMTP Port:</label>
                                <div class="col-sm-10">
                                  <input type="text" class="form-control" id="config_smtp_port" name="config_smtp_port" placeholder="SMTP Port:" value="25">
                                </div>
                              </div>
                               <div class="form-group">
                                <label class="control-label col-sm-2" for="config_smtp_timeout">SMTP Timeout:</label>
                                <div class="col-sm-10">
                                  <input type="text" class="form-control" id="config_smtp_timeout" name="config_smtp_timeout" placeholder="SMTP Timeout" value="5">
                                </div>
                              </div>
                                <div class="form-group">
                                <label class="control-label col-sm-2" for="config_alert_mail">Alert Mail:</label>
                                <div class="col-sm-10">
                                 <select name="config_alert_mail" id="config_alert_mail" class="form-control">
                                    <option value="1" selected="">Yes</option>
                                    <option value="0">No</option>
                                    </select>
                                </div>
                              </div>
                                <div class="form-group">
                                <label class="control-label col-sm-2" for="config_alert_emails">Alert emails:</label>
                                <div class="col-sm-10">
                                  <input type="text" class="form-control" id="config_alert_emails" name="config_alert_emails" placeholder="Alert emails" value="">
                                  <small>Comma sperated</small>
                                </div>
                              </div>
                                 
                              <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                  <button type="submit" class="btn btn-primary">Submit</button>
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
</body>
</html>


 