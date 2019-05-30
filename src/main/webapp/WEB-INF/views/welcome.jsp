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
    <section class="wrapper main-wrapper row" style=''>

    <div class='col-xs-12'>
        <div class="page-title">

            <div class="pull-left">
                <!-- PAGE HEADING TAG - START --><h1 class="title">Dashboard</h1><!-- PAGE HEADING TAG - END -->                            </div>

                                
        </div>
    </div>
    <div class="clearfix"></div>
    <!-- MAIN CONTENT AREA STARTS -->
    

<div class="col-lg-12">
    <section class="box nobox marginBottom0">
                <div class="content-body">    <div class="row">
        <div class="col-lg-3 col-sm-6 col-xs-12">
            <div class="r4_counter db_box">
                <i class="pull-left fa fa-envelope icon-md icon-rounded icon-primary"></i>
                <div class="stats">
                     
                    <h4><strong>${count.contactCount}</strong></h4>
                    <span>New Message</span>
                </div>
            </div>
        </div>
        <div class="col-lg-3 col-sm-6 col-xs-12">
            <div class="r4_counter db_box">
                <i class="pull-left fa fa fa-male icon-md icon-rounded icon-accent"></i>
                <div class="stats">
                    <h4><strong>${count.newUserCount}</strong></h4>
                    <span>New User</span>
                </div>
            </div>
        </div>
        <div class="col-lg-3 col-sm-6 col-xs-12">
            <div class="r4_counter db_box">
                <i class="pull-left fa fa-th-large  icon-md icon-rounded icon-purple"></i>
                <div class="stats">
                    <h4><strong>${count.collageUserCount}</strong></h4>
                    <span>Institute Registration</span>
                </div>
            </div>
        </div>
      <div class="col-lg-3 col-sm-6 col-xs-12">
            <div class="r4_counter db_box">
                <i class="pull-left fa fa-university  icon-md icon-rounded icon-warning"></i>
                <div class="stats">
                    <h4><strong>${count.univercityUserCount}</strong></h4>
                    <span>University Registration</span>
                </div>
            </div>
        </div>
    </div> <!-- End .row -->    
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
</body>
</html>


 
 