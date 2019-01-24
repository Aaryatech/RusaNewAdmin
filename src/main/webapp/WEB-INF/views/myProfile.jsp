<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 
  <!DOCTYPE html>
<html class=" ">
    <head>
        <!-- 
         * @Package: Complete Admin - Responsive Theme
         * @Subpackage: Bootstrap
         * @Version: 2.2
         * This file is part of Complete Admin Theme.
        -->
        <meta http-equiv="content-type" content="text/html;charset=UTF-8" />
        <meta charset="utf-8" />
        <title>Complete Admin : Form Elements - Basic Fields</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/assets/images/favicon.png" type="image/x-icon" />    <!-- Favicon -->
        <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath}/resources/assets/images/apple-touch-icon-57-precomposed.png">	<!-- For iPhone -->
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="${pageContext.request.contextPath}/resources/assets/images/apple-touch-icon-114-precomposed.png">    <!-- For iPhone 4 Retina display -->
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="${pageContext.request.contextPath}/resources/assets/images/apple-touch-icon-72-precomposed.png">    <!-- For iPad -->
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="${pageContext.request.contextPath}/resources/assets/images/apple-touch-icon-144-precomposed.png">    <!-- For iPad Retina display -->




        <!-- CORE CSS FRAMEWORK - START -->
        <link href="${pageContext.request.contextPath}/resources/assets/plugins/pace/pace-theme-flash.css" rel="stylesheet" type="text/css" media="screen"/>
        <link href="${pageContext.request.contextPath}/resources/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/resources/assets/plugins/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/resources/assets/fonts/font-awesome/css/font-awesome.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/resources/assets/css/animate.min.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/resources/assets/plugins/perfect-scrollbar/perfect-scrollbar.css" rel="stylesheet" type="text/css"/>
        <!-- CORE CSS FRAMEWORK - END -->

        <!-- HEADER SCRIPTS INCLUDED ON THIS PAGE - START --> 
        
        
<link href="${pageContext.request.contextPath}/resources/assets/plugins/icheck/skins/all.css" rel="stylesheet" type="text/css" media="screen"/>

        <!-- HEADER SCRIPTS INCLUDED ON THIS PAGE - END --> 


        <!-- CORE CSS TEMPLATE - START -->
        <link href="${pageContext.request.contextPath}/resources/assets/css/style.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/resources/assets/css/responsive.css" rel="stylesheet" type="text/css"/>
        <!-- CORE CSS TEMPLATE - END -->

    </head>
    <!-- END HEAD -->

    <!-- BEGIN BODY -->
    <body class=" ">
<!-- START TOPBAR -->
<div class='page-topbar '>
    <div class='logo-area'>

    </div>
    <div class='quick-area'>
        <div class='pull-left'>
            <ul class="info-menu left-links list-inline list-unstyled">
                <li class="sidebar-toggle-wrap">
                    <a href="#" data-toggle="sidebar" class="sidebar_toggle">
                        <i class="fa fa-bars"></i>
                    </a>
                </li>
                <li class="message-toggle-wrapper">
                    <a href="#" data-toggle="dropdown" class="toggle">
                        <i class="fa fa-envelope"></i>
                        <span class="badge badge-accent">7</span>
                    </a>
                    <ul class="dropdown-menu messages animated fadeIn">

                        <li class="list">

                            <ul class="dropdown-menu-list list-unstyled ps-scrollbar">
                                                                    <li class="unread status-available">
                                        <a href="javascript:;">
                                            <div class="user-img">
                                                <img src="${pageContext.request.contextPath}/resources/data/profile/avatar-1.png" alt="user-image" class="img-circle img-inline">
                                            </div>
                                            <div>
                                                <span class="name">
                                                    <strong>Clarine Vassar</strong>
                                                    <span class="time small">- 15 mins ago</span>
                                                    <span class="profile-status available pull-right"></span>
                                                </span>
                                                <span class="desc small">
                                                    Sometimes it takes a lifetime to win a battle.
                                                </span>
                                            </div>
                                        </a>
                                    </li>
                                    <li class=" status-away">
                                        <a href="javascript:;">
                                            <div class="user-img">
                                                <img src="${pageContext.request.contextPath}/resources/data/profile/avatar-2.png" alt="user-image" class="img-circle img-inline">
                                            </div>
                                            <div>
                                                <span class="name">
                                                    <strong>Brooks Latshaw</strong>
                                                    <span class="time small">- 45 mins ago</span>
                                                    <span class="profile-status away pull-right"></span>
                                                </span>
                                                <span class="desc small">
                                                    Sometimes it takes a lifetime to win a battle.
                                                </span>
                                            </div>
                                        </a>
                                    </li>
                                    <li class=" status-busy">
                                        <a href="javascript:;">
                                            <div class="user-img">
                                                <img src="${pageContext.request.contextPath}/resources/data/profile/avatar-3.png" alt="user-image" class="img-circle img-inline">
                                            </div>
                                            <div>
                                                <span class="name">
                                                    <strong>Clementina Brodeur</strong>
                                                    <span class="time small">- 1 hour ago</span>
                                                    <span class="profile-status busy pull-right"></span>
                                                </span>
                                                <span class="desc small">
                                                    Sometimes it takes a lifetime to win a battle.
                                                </span>
                                            </div>
                                        </a>
                                    </li>
                                    <li class=" status-offline">
                                        <a href="javascript:;">
                                            <div class="user-img">
                                                <img src="${pageContext.request.contextPath}/resources/data/profile/avatar-4.png" alt="user-image" class="img-circle img-inline">
                                            </div>
                                            <div>
                                                <span class="name">
                                                    <strong>Carri Busey</strong>
                                                    <span class="time small">- 5 hours ago</span>
                                                    <span class="profile-status offline pull-right"></span>
                                                </span>
                                                <span class="desc small">
                                                    Sometimes it takes a lifetime to win a battle.
                                                </span>
                                            </div>
                                        </a>
                                    </li>
                                    <li class=" status-offline">
                                        <a href="javascript:;">
                                            <div class="user-img">
                                                <img src="${pageContext.request.contextPath}/resources/data/profile/avatar-5.png" alt="user-image" class="img-circle img-inline">
                                            </div>
                                            <div>
                                                <span class="name">
                                                    <strong>Melissa Dock</strong>
                                                    <span class="time small">- Yesterday</span>
                                                    <span class="profile-status offline pull-right"></span>
                                                </span>
                                                <span class="desc small">
                                                    Sometimes it takes a lifetime to win a battle.
                                                </span>
                                            </div>
                                        </a>
                                    </li>
                                    <li class=" status-available">
                                        <a href="javascript:;">
                                            <div class="user-img">
                                                <img src="${pageContext.request.contextPath}/resources/data/profile/avatar-1.png" alt="user-image" class="img-circle img-inline">
                                            </div>
                                            <div>
                                                <span class="name">
                                                    <strong>Verdell Rea</strong>
                                                    <span class="time small">- 14th Mar</span>
                                                    <span class="profile-status available pull-right"></span>
                                                </span>
                                                <span class="desc small">
                                                    Sometimes it takes a lifetime to win a battle.
                                                </span>
                                            </div>
                                        </a>
                                    </li>
                                    <li class=" status-busy">
                                        <a href="javascript:;">
                                            <div class="user-img">
                                                <img src="${pageContext.request.contextPath}/resources/data/profile/avatar-2.png" alt="user-image" class="img-circle img-inline">
                                            </div>
                                            <div>
                                                <span class="name">
                                                    <strong>Linette Lheureux</strong>
                                                    <span class="time small">- 16th Mar</span>
                                                    <span class="profile-status busy pull-right"></span>
                                                </span>
                                                <span class="desc small">
                                                    Sometimes it takes a lifetime to win a battle.
                                                </span>
                                            </div>
                                        </a>
                                    </li>
                                    <li class=" status-away">
                                        <a href="javascript:;">
                                            <div class="user-img">
                                                <img src="${pageContext.request.contextPath}/resources/data/profile/avatar-3.png" alt="user-image" class="img-circle img-inline">
                                            </div>
                                            <div>
                                                <span class="name">
                                                    <strong>Araceli Boatright</strong>
                                                    <span class="time small">- 16th Mar</span>
                                                    <span class="profile-status away pull-right"></span>
                                                </span>
                                                <span class="desc small">
                                                    Sometimes it takes a lifetime to win a battle.
                                                </span>
                                            </div>
                                        </a>
                                    </li>

                            </ul>

                        </li>

                        <li class="external">
                            <a href="javascript:;">
                                <span>Read All Messages</span>
                            </a>
                        </li>
                    </ul>

                </li>
                <li class="notify-toggle-wrapper">
                    <a href="#" data-toggle="dropdown" class="toggle">
                        <i class="fa fa-bell"></i>
                        <span class="badge badge-accent">3</span>
                    </a>
                    <ul class="dropdown-menu notifications animated fadeIn">
                        <li class="total">
                            <span class="small">
                                You have <strong>3</strong> new notifications.
                                <a href="javascript:;" class="pull-right">Mark all as Read</a>
                            </span>
                        </li>
                        <li class="list">

                            <ul class="dropdown-menu-list list-unstyled ps-scrollbar">
                                                                    <li class="unread available"> <!-- available: success, warning, info, error -->
                                        <a href="javascript:;">
                                            <div class="notice-icon">
                                                <i class="fa fa-check"></i>
                                            </div>
                                            <div>
                                                <span class="name">
                                                    <strong>Server needs to reboot</strong>
                                                    <span class="time small">15 mins ago</span>
                                                </span>
                                            </div>
                                        </a>
                                    </li>
                                    <li class="unread away"> <!-- available: success, warning, info, error -->
                                        <a href="javascript:;">
                                            <div class="notice-icon">
                                                <i class="fa fa-envelope"></i>
                                            </div>
                                            <div>
                                                <span class="name">
                                                    <strong>45 new messages</strong>
                                                    <span class="time small">45 mins ago</span>
                                                </span>
                                            </div>
                                        </a>
                                    </li>
                                    <li class=" busy"> <!-- available: success, warning, info, error -->
                                        <a href="javascript:;">
                                            <div class="notice-icon">
                                                <i class="fa fa-times"></i>
                                            </div>
                                            <div>
                                                <span class="name">
                                                    <strong>Server IP Blocked</strong>
                                                    <span class="time small">1 hour ago</span>
                                                </span>
                                            </div>
                                        </a>
                                    </li>
                                    <li class=" offline"> <!-- available: success, warning, info, error -->
                                        <a href="javascript:;">
                                            <div class="notice-icon">
                                                <i class="fa fa-user"></i>
                                            </div>
                                            <div>
                                                <span class="name">
                                                    <strong>10 Orders Shipped</strong>
                                                    <span class="time small">5 hours ago</span>
                                                </span>
                                            </div>
                                        </a>
                                    </li>
                                    <li class=" offline"> <!-- available: success, warning, info, error -->
                                        <a href="javascript:;">
                                            <div class="notice-icon">
                                                <i class="fa fa-user"></i>
                                            </div>
                                            <div>
                                                <span class="name">
                                                    <strong>New Comment on blog</strong>
                                                    <span class="time small">Yesterday</span>
                                                </span>
                                            </div>
                                        </a>
                                    </li>
                                    <li class=" available"> <!-- available: success, warning, info, error -->
                                        <a href="javascript:;">
                                            <div class="notice-icon">
                                                <i class="fa fa-check"></i>
                                            </div>
                                            <div>
                                                <span class="name">
                                                    <strong>Great Speed Notify</strong>
                                                    <span class="time small">14th Mar</span>
                                                </span>
                                            </div>
                                        </a>
                                    </li>
                                    <li class=" busy"> <!-- available: success, warning, info, error -->
                                        <a href="javascript:;">
                                            <div class="notice-icon">
                                                <i class="fa fa-times"></i>
                                            </div>
                                            <div>
                                                <span class="name">
                                                    <strong>Team Meeting at 6PM</strong>
                                                    <span class="time small">16th Mar</span>
                                                </span>
                                            </div>
                                        </a>
                                    </li>

                            </ul>

                        </li>

                        <li class="external">
                            <a href="javascript:;">
                                <span>Read All Notifications</span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="hidden-sm hidden-xs searchform">
                    <form action="ui-search.html" method="post">
                        <div class="input-group">
                            <span class="input-group-addon">
                                <i class="fa fa-search"></i>
                            </span>
                            <input type="text" class="form-control animated fadeIn" placeholder="Search & Enter">
                        </div>
                        <input type='submit' value="">
                    </form>
                </li>
            </ul>
        </div>		
        <div class='pull-right'>
            <ul class="info-menu right-links list-inline list-unstyled">
                <li class="profile">
                    <a href="#" data-toggle="dropdown" class="toggle">
                        <img src="${pageContext.request.contextPath}/resources/data/profile/profile.jpg" alt="user-image" class="img-circle img-inline">
                        <span>Shane Taylor <i class="fa fa-angle-down"></i></span>
                    </a>
                    <ul class="dropdown-menu profile animated fadeIn">
                        <li>
                            <a href="#settings">
                                <i class="fa fa-wrench"></i>
                                Settings
                            </a>
                        </li>
                        <li>
                            <a href="#profile">
                                <i class="fa fa-user"></i>
                                Profile
                            </a>
                        </li>
                        <li>
                            <a href="#help">
                                <i class="fa fa-info"></i>
                                Help
                            </a>
                        </li>
                        <li class="last">
                            <a href="ui-login.html">
                                <i class="fa fa-lock"></i>
                                Logout
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="chat-toggle-wrapper">
                    <a href="#" data-toggle="chatbar" class="toggle_chat">
                        <i class="fa fa-comments"></i>
                        <span class="badge badge-accent">9</span>
                        <i class="fa fa-times"></i>
                    </a>
                </li>
            </ul>			
        </div>		
    </div>

</div>
<!-- END TOPBAR -->
<!-- START CONTAINER -->
<div class="page-container row-fluid container-fluid">

    <!-- SIDEBAR - START -->

<div class="page-sidebar pagescroll">

    <!-- MAIN MENU - START -->
    <div class="page-sidebar-wrapper" id="main-menu-wrapper"> 

        <!-- USER INFO - START -->
        <div class="profile-info row">

            <div class="profile-image col-xs-4">
                <a href="ui-profile.html">
                    <img alt="" src="${pageContext.request.contextPath}/resources/data/profile/profile.jpg" class="img-responsive img-circle">
                </a>
            </div>

            <div class="profile-details col-xs-8">

                <h3>
                    <a href="ui-profile.html">Shane Taylor</a>

                    <!-- Available statuses: online, idle, busy, away and offline -->
                    <span class="profile-status online"></span>
                </h3>

                <p class="profile-title">Web Developer</p>

            </div>

        </div>
        <!-- USER INFO - END -->



        <ul class='wraplist'>	

            <li class='menusection'>Main</li>
                    <li class=""> 
                    <a href="index.html">
                    <i class="fa fa-dashboard"></i>
                    <span class="title">Dashboard</span>
                        </a>
                    </li>
                    <li class=""> 
                    <a href="javascript:;">
                    <i class="fa fa-suitcase"></i>
                    <span class="title">Multi Purpose</span>
                        <span class="arrow "></span><span class="label label-accent">HOT</span>
                        </a>
                        <ul class="sub-menu" >
                            <li>
                            <a class="" href="hospital.html"  target = '_blank' >Hospital Admin</a>
                            </li>
                            <li>
                            <a class="" href="music.html"  target = '_blank' >Music Admin</a>
                            </li>
                            <li>
                            <a class="" href="crm.html"  target = '_blank' >CRM Admin</a>
                            </li>
                            <li>
                            <a class="" href="socialmedia.html"  target = '_blank' >Social Media Admin</a>
                            </li>
                            <li>
                            <a class="" href="freelancing.html"  target = '_blank' >Freelancing Admin</a>
                            </li>
                            <li>
                            <a class="" href="university.html"  target = '_blank' >University Admin</a>
                            </li>
                            <li>
                            <a class="" href="ecommerce.html"  target = '_blank' >Ecommerce Admin</a>
                            </li>
                            <li>
                            <a class="" href="blog.html"  target = '_blank' >Blog Admin</a>
                            </li>
                        </ul>
                    </li>
                    <li class=""> 
                    <a href="javascript:;">
                    <i class="fa fa-columns"></i>
                    <span class="title">Layouts</span>
                        <span class="arrow "></span>
                        </a>
                        <ul class="sub-menu" >
                            <li>
                            <a class="" href="layout-default.html" >Default Layout</a>
                            </li>
                            <li>
                            <a class="" href="layout-collapsed.html" >Collapsed Menu</a>
                            </li>
                            <li>
                            <a class="" href="layout-chat.html" >Chat Open</a>
                            </li>
                            <li>
                            <a class="" href="layout-boxed.html" >Boxed Layout</a>
                            </li>
                            <li>
                            <a class="" href="layout-boxed-collapsed.html" >Boxed Collapsed Menu</a>
                            </li>
                            <li>
                            <a class="" href="layout-boxed-chat.html" >Boxed Chat Open</a>
                            </li>
                        </ul>
                    </li><li class='menusection'>Applications</li>
                    <li class=""> 
                    <a href="ui-calendar.html">
                    <i class="fa fa-calendar"></i>
                    <span class="title">Calendar</span>
                        </a>
                    </li>
                    <li class=""> 
                    <a href="javascript:;">
                    <i class="fa fa-envelope"></i>
                    <span class="title">Mailbox</span>
                        <span class="arrow "></span><span class="label label-accent">4</span>
                        </a>
                        <ul class="sub-menu" >
                            <li>
                            <a class="" href="mail-inbox.html" >Inbox</a>
                            </li>
                            <li>
                            <a class="" href="mail-compose.html" >Compose</a>
                            </li>
                            <li>
                            <a class="" href="mail-view.html" >View</a>
                            </li>
                        </ul>
                    </li>
                    <li class=""> 
                    <a href="javascript:;">
                    <i class="fa fa-comments"></i>
                    <span class="title">Chat API</span>
                        <span class="arrow "></span>
                        </a>
                        <ul class="sub-menu" >
                            <li>
                            <a class="" href="chat-api.html" >Chat API</a>
                            </li>
                            <li>
                            <a class="" href="chat-windows.html" >Chat Windows</a>
                            </li>
                        </ul>
                    </li>
                    <li class=""> 
                    <a href="javascript:;">
                    <i class="fa fa-envelope"></i>
                    <span class="title">Widgets</span>
                        <span class="arrow "></span>
                        </a>
                        <ul class="sub-menu" >
                            <li>
                            <a class="" href="widget-misc.html" >Miscellaneous</a>
                            </li>
                            <li>
                            <a class="" href="widget-tiles-counter.html" >Counter Tiles</a>
                            </li>
                            <li>
                            <a class="" href="widget-tiles-progress.html" >Progress Tiles</a>
                            </li>
                            <li>
                            <a class="" href="widget-socialmedia.html" >Social Media</a>
                            </li>
                            <li>
                            <a class="" href="widget-graphs.html" >Graphs</a>
                            </li>
                            <li>
                            <a class="" href="widget-todo.html" >To Do Tasks</a>
                            </li>
                        </ul>
                    </li><li class='menusection'>Data Visualization</li>
                    <li class=""> 
                    <a href="javascript:;">
                    <i class="fa fa-bar-chart"></i>
                    <span class="title">Echarts</span>
                        <span class="arrow "></span><span class="label label-accent">HOT</span>
                        </a>
                        <ul class="sub-menu" >
                            <li>
                            <a class="" href="charts-echart-line.html" >Line & Area Charts</a>
                            </li>
                            <li>
                            <a class="" href="charts-echart-bar.html" >Bar & Stacked Charts</a>
                            </li>
                            <li>
                            <a class="" href="charts-echart-scatter.html" >Scatter Charts</a>
                            </li>
                            <li>
                            <a class="" href="charts-echart-pie.html" >Pie Charts</a>
                            </li>
                            <li>
                            <a class="" href="charts-echart-gauge.html" >Gauge Charts</a>
                            </li>
                        </ul>
                    </li>
                    <li class=""> 
                    <a href="javascript:;">
                    <i class="fa fa-area-chart"></i>
                    <span class="title">Morris Charts</span>
                        <span class="arrow "></span>
                        </a>
                        <ul class="sub-menu" >
                            <li>
                            <a class="" href="charts-morris-line.html" >Line Charts</a>
                            </li>
                            <li>
                            <a class="" href="charts-morris-bar.html" >Bar & Stacked Charts</a>
                            </li>
                            <li>
                            <a class="" href="charts-morris-area.html" >Area Charts</a>
                            </li>
                            <li>
                            <a class="" href="charts-morris-pie.html" >Pie Charts</a>
                            </li>
                        </ul>
                    </li>
                    <li class=""> 
                    <a href="javascript:;">
                    <i class="fa fa-line-chart"></i>
                    <span class="title">Charts JS</span>
                        <span class="arrow "></span>
                        </a>
                        <ul class="sub-menu" >
                            <li>
                            <a class="" href="charts-chartjs-line.html" >Line Charts</a>
                            </li>
                            <li>
                            <a class="" href="charts-chartjs-bar.html" >Bar Charts</a>
                            </li>
                            <li>
                            <a class="" href="charts-chartjs-polar-radar.html" >Polar & Radar</a>
                            </li>
                            <li>
                            <a class="" href="charts-chartjs-pie-donut.html" >Pie & Donut</a>
                            </li>
                        </ul>
                    </li>
                    <li class=""> 
                    <a href="javascript:;">
                    <i class="fa fa-bar-chart"></i>
                    <span class="title">Flot Charts</span>
                        <span class="arrow "></span>
                        </a>
                        <ul class="sub-menu" >
                            <li>
                            <a class="" href="charts-flot-stacked.html" >Stacked Charts</a>
                            </li>
                            <li>
                            <a class="" href="charts-flot-area.html" >Area Charts</a>
                            </li>
                            <li>
                            <a class="" href="charts-flot-extensions.html" >Flot Extensions</a>
                            </li>
                            <li>
                            <a class="" href="charts-flot-combined.html" >Combined Charts</a>
                            </li>
                            <li>
                            <a class="" href="charts-flot-line.html" >Line Charts</a>
                            </li>
                            <li>
                            <a class="" href="charts-flot-pie.html" >Pie Charts</a>
                            </li>
                        </ul>
                    </li>
                    <li class=""> 
                    <a href="javascript:;">
                    <i class="fa fa-line-chart"></i>
                    <span class="title">Sparkline Charts</span>
                        <span class="arrow "></span>
                        </a>
                        <ul class="sub-menu" >
                            <li>
                            <a class="" href="charts-sparkline-line.html" >Line & Area Charts</a>
                            </li>
                            <li>
                            <a class="" href="charts-sparkline-bar.html" >Bar Charts</a>
                            </li>
                            <li>
                            <a class="" href="charts-sparkline-composite.html" >Composite Charts</a>
                            </li>
                            <li>
                            <a class="" href="charts-sparkline-other.html" >Other Charts</a>
                            </li>
                        </ul>
                    </li>
                    <li class=""> 
                    <a href="charts-easypiechart.html">
                    <i class="fa fa-pie-chart"></i>
                    <span class="title">Easy Pie Charts</span>
                        </a>
                    </li>
                    <li class=""> 
                    <a href="javascript:;">
                    <i class="fa fa-circle-o-notch"></i>
                    <span class="title">Knobs Charts</span>
                        <span class="arrow "></span>
                        </a>
                        <ul class="sub-menu" >
                            <li>
                            <a class="" href="charts-knobs.html" >Knobs</a>
                            </li>
                            <li>
                            <a class="" href="charts-knobs-clock.html" >Knob Clock</a>
                            </li>
                        </ul>
                    </li>
                    <li class=""> 
                    <a href="javascript:;">
                    <i class="fa fa-area-chart"></i>
                    <span class="title">Rickshaw Charts</span>
                        <span class="arrow "></span>
                        </a>
                        <ul class="sub-menu" >
                            <li>
                            <a class="" href="charts-rickshaw-extensions.html" >Extensions</a>
                            </li>
                            <li>
                            <a class="" href="charts-rickshaw-scatter.html" >Scatter Charts</a>
                            </li>
                            <li>
                            <a class="" href="charts-rickshaw-milliseconds.html" >Millisecond Charts</a>
                            </li>
                        </ul>
                    </li><li class='menusection'>Forms</li>
                    <li class="open"> 
                    <a href="javascript:;">
                    <i class="fa fa-check-square-o"></i>
                    <span class="title">Form Elements</span>
                        <span class="arrow open"></span>
                        </a>
                        <ul class="sub-menu" style='display:block;'>
                            <li>
                            <a class="active" href="form-elements.html" >Field Elements</a>
                            </li>
                            <li>
                            <a class="" href="form-elements-premade.html" >Pre Made Forms</a>
                            </li>
                            <li>
                            <a class="" href="form-elements-icheck.html" >Checkbox & Radio</a>
                            </li>
                            <li>
                            <a class="" href="form-elements-grid.html" >Form Grid</a>
                            </li>
                        </ul>
                    </li>
                    <li class=""> 
                    <a href="javascript:;">
                    <i class="fa fa-sliders"></i>
                    <span class="title">Form Components</span>
                        <span class="arrow "></span>
                        </a>
                        <ul class="sub-menu" >
                            <li>
                            <a class="" href="form-components-datepicker.html" >Date picker</a>
                            </li>
                            <li>
                            <a class="" href="form-components-timepicker.html" >Time picker</a>
                            </li>
                            <li>
                            <a class="" href="form-components-daterange.html" >Date Range</a>
                            </li>
                            <li>
                            <a class="" href="form-components-colorpicker.html" >Color picker</a>
                            </li>
                            <li>
                            <a class="" href="form-components-spinners.html" >Spinners</a>
                            </li>
                            <li>
                            <a class="" href="form-components-tagsinput.html" >Tags Input</a>
                            </li>
                            <li>
                            <a class="" href="form-components-switches.html" >Switches</a>
                            </li>
                            <li>
                            <a class="" href="form-components-sliders.html" >Sliders</a>
                            </li>
                            <li>
                            <a class="" href="form-components-select2.html" >Select2</a>
                            </li>
                            <li>
                            <a class="" href="form-components-typeahead.html" >Typeahead</a>
                            </li>
                            <li>
                            <a class="" href="form-components-multiselect.html" >Multi Select</a>
                            </li>
                        </ul>
                    </li>
                    <li class=""> 
                    <a href="form-wizard.html">
                    <i class="fa fa-dot-circle-o"></i>
                    <span class="title">Form Wizard</span>
                        </a>
                    </li>
                    <li class=""> 
                    <a href="form-validation.html">
                    <i class="fa fa-warning"></i>
                    <span class="title">Form Validations</span>
                        </a>
                    </li>
                    <li class=""> 
                    <a href="javascript:;">
                    <i class="fa fa-text-width"></i>
                    <span class="title">Form Editors</span>
                        <span class="arrow "></span>
                        </a>
                        <ul class="sub-menu" >
                            <li>
                            <a class="" href="form-editors-bootstrap.html" >Bootstrap WYSIHTML5</a>
                            </li>
                            <li>
                            <a class="" href="form-editors-ckeditor.html" >Ckeditor</a>
                            </li>
                            <li>
                            <a class="" href="form-editors-inline.html" >Inline editor</a>
                            </li>
                            <li>
                            <a class="" href="form-editors-markdown.html" >Markdown editor</a>
                            </li>
                        </ul>
                    </li>
                    <li class=""> 
                    <a href="javascript:;">
                    <i class="fa fa-terminal"></i>
                    <span class="title">Form Mask</span>
                        <span class="arrow "></span>
                        </a>
                        <ul class="sub-menu" >
                            <li>
                            <a class="" href="form-masks-input.html" >Input Masks</a>
                            </li>
                            <li>
                            <a class="" href="form-masks-autonumeric.html" >Autonumeric</a>
                            </li>
                        </ul>
                    </li><li class='menusection'>Page Kits</li>
                    <li class=""> 
                    <a href="javascript:;">
                    <i class="fa fa-lock"></i>
                    <span class="title">Access Pages</span>
                        <span class="arrow "></span>
                        </a>
                        <ul class="sub-menu" >
                            <li>
                            <a class="" href="ui-login.html" >Login</a>
                            </li>
                            <li>
                            <a class="" href="ui-lockscreen.html" >Lock Screen</a>
                            </li>
                            <li>
                            <a class="" href="ui-register.html" >Registration</a>
                            </li>
                        </ul>
                    </li>
                    <li class=""> 
                    <a href="javascript:;">
                    <i class="fa fa-warning"></i>
                    <span class="title">Error Pages</span>
                        <span class="arrow "></span>
                        </a>
                        <ul class="sub-menu" >
                            <li>
                            <a class="" href="ui-403.html" >403</a>
                            </li>
                            <li>
                            <a class="" href="ui-404.html" >404</a>
                            </li>
                            <li>
                            <a class="" href="ui-405.html" >405</a>
                            </li>
                            <li>
                            <a class="" href="ui-408.html" >408</a>
                            </li>
                            <li>
                            <a class="" href="ui-500.html" >500</a>
                            </li>
                            <li>
                            <a class="" href="ui-503.html" >503</a>
                            </li>
                            <li>
                            <a class="" href="ui-offline.html" >Offline</a>
                            </li>
                        </ul>
                    </li>
                    <li class=""> 
                    <a href="javascript:;">
                    <i class="fa fa-edit"></i>
                    <span class="title">Blog & Search</span>
                        <span class="arrow "></span>
                        </a>
                        <ul class="sub-menu" >
                            <li>
                            <a class="" href="ui-blogs.html" >Blogs</a>
                            </li>
                            <li>
                            <a class="" href="ui-search.html" >Search</a>
                            </li>
                            <li>
                            <a class="" href="ui-blog-item.html" >Single Blog</a>
                            </li>
                        </ul>
                    </li>
                    <li class=""> 
                    <a href="javascript:;">
                    <i class="fa fa-share-alt"></i>
                    <span class="title">Social Media</span>
                        <span class="arrow "></span>
                        </a>
                        <ul class="sub-menu" >
                            <li>
                            <a class="" href="ui-profile.html" >User Profile</a>
                            </li>
                            <li>
                            <a class="" href="ui-members.html" >Members</a>
                            </li>
                        </ul>
                    </li>
                    <li class=""> 
                    <a href="javascript:;">
                    <i class="fa fa-th"></i>
                    <span class="title">Gallery</span>
                        <span class="arrow "></span>
                        </a>
                        <ul class="sub-menu" >
                            <li>
                            <a class="" href="ui-gallery-2col.html" >2 Column Grid</a>
                            </li>
                            <li>
                            <a class="" href="ui-gallery-3col.html" >3 Column Grid</a>
                            </li>
                            <li>
                            <a class="" href="ui-gallery-4col.html" >4 Column Grid</a>
                            </li>
                            <li>
                            <a class="" href="ui-gallery-3col-masonry.html" >3 Column Masonry</a>
                            </li>
                            <li>
                            <a class="" href="ui-gallery-4col-masonry.html" >4 Column Masonry</a>
                            </li>
                            <li>
                            <a class="" href="ui-gallery-2col-outside.html" >2 Column with title</a>
                            </li>
                            <li>
                            <a class="" href="ui-gallery-3col-outside.html" >3 Column with title</a>
                            </li>
                            <li>
                            <a class="" href="ui-gallery-4col-outside.html" >4 Column with title</a>
                            </li>
                        </ul>
                    </li>
                    <li class=""> 
                    <a href="javascript:;">
                    <i class="fa fa-ellipsis-v"></i>
                    <span class="title">Timeline</span>
                        <span class="arrow "></span>
                        </a>
                        <ul class="sub-menu" >
                            <li>
                            <a class="" href="ui-timeline-centered.html" >Centered timeline</a>
                            </li>
                            <li>
                            <a class="" href="ui-timeline-left.html" >Left Aligned timeline</a>
                            </li>
                        </ul>
                    </li>
                    <li class=""> 
                    <a href="javascript:;">
                    <i class="fa fa-question-circle"></i>
                    <span class="title">Support</span>
                        <span class="arrow "></span>
                        </a>
                        <ul class="sub-menu" >
                            <li>
                            <a class="" href="ui-faq.html" >FAQ</a>
                            </li>
                            <li>
                            <a class="" href="ui-tocify.html" >Documentation</a>
                            </li>
                        </ul>
                    </li>
                    <li class=""> 
                    <a href="javascript:;">
                    <i class="fa fa-dollar"></i>
                    <span class="title">Pricing Tables</span>
                        <span class="arrow "></span>
                        </a>
                        <ul class="sub-menu" >
                            <li>
                            <a class="" href="ui-pricing-expanded.html" >Expanded</a>
                            </li>
                            <li>
                            <a class="" href="ui-pricing-narrow.html" >Narrow</a>
                            </li>
                        </ul>
                    </li>
                    <li class=""> 
                    <a href="javascript:;">
                    <i class="fa fa-money"></i>
                    <span class="title">Invoicing</span>
                        <span class="arrow "></span>
                        </a>
                        <ul class="sub-menu" >
                            <li>
                            <a class="" href="ui-invoice.html" >Invoice</a>
                            </li>
                            <li>
                            <a class="" href="ui-invoice-list.html" >Invoice Listing</a>
                            </li>
                            <li>
                            <a class="" href="ui-invoice-add.html" >Invoice Add</a>
                            </li>
                            <li>
                            <a class="" href="ui-invoice-edit.html" >Invoice Edit</a>
                            </li>
                        </ul>
                    </li><li class='menusection'>User Interface</li>
                    <li class=""> 
                    <a href="javascript:;">
                    <i class="fa fa-puzzle-piece"></i>
                    <span class="title">Components</span>
                        <span class="arrow "></span>
                        </a>
                        <ul class="sub-menu" >
                            <li>
                            <a class="" href="ui-tabs.html" >Tabs</a>
                            </li>
                            <li>
                            <a class="" href="ui-accordion.html" >Accordions</a>
                            </li>
                            <li>
                            <a class="" href="ui-progress.html" >Progress Bars</a>
                            </li>
                            <li>
                            <a class="" href="ui-buttons.html" >Buttons</a>
                            </li>
                            <li>
                            <a class="" href="ui-modals.html" >Modals</a>
                            </li>
                            <li>
                            <a class="" href="ui-alerts.html" >Alerts</a>
                            </li>
                            <li>
                            <a class="" href="ui-notifications.html" >Notifications</a>
                            </li>
                            <li>
                            <a class="" href="ui-tooltips.html" >Tooltips</a>
                            </li>
                            <li>
                            <a class="" href="ui-popovers.html" >Popovers</a>
                            </li>
                            <li>
                            <a class="" href="ui-navbars.html" >Navbars</a>
                            </li>
                            <li>
                            <a class="" href="ui-dropdowns.html" >Dropdowns</a>
                            </li>
                            <li>
                            <a class="" href="ui-carousel.html" >Carousel Slider</a>
                            </li>
                            <li>
                            <a class="" href="ui-tree.html" >Tree</a>
                            </li>
                            <li>
                            <a class="" href="ui-breadcrumbs.html" >Breadcrumbs</a>
                            </li>
                            <li>
                            <a class="" href="ui-pagination.html" >Pagination</a>
                            </li>
                            <li>
                            <a class="" href="ui-labels-badges.html" >Labels & Badges</a>
                            </li>
                        </ul>
                    </li>
                    <li class=""> 
                    <a href="javascript:;">
                    <i class="fa fa-th-large"></i>
                    <span class="title">Appearance</span>
                        <span class="arrow "></span>
                        </a>
                        <ul class="sub-menu" >
                            <li>
                            <a class="" href="ui-typography.html" >Typography</a>
                            </li>
                            <li>
                            <a class="" href="ui-grids.html" >Grids</a>
                            </li>
                            <li>
                            <a class="" href="ui-panels.html" >Draggable Panels</a>
                            </li>
                            <li>
                            <a class="" href="ui-embeds.html" >Video Embeds</a>
                            </li>
                            <li>
                            <a class="" href="ui-code.html" >Code styles</a>
                            </li>
                            <li>
                            <a class="" href="ui-group-list.html" >Group Listing</a>
                            </li>
                            <li>
                            <a class="" href="ui-responsive-images.html" >Responsive Images</a>
                            </li>
                        </ul>
                    </li>
                    <li class=""> 
                    <a href="javascript:;">
                    <i class="fa fa-thumbs-up"></i>
                    <span class="title">Icon Sets</span>
                        <span class="arrow "></span>
                        </a>
                        <ul class="sub-menu" >
                            <li>
                            <a class="" href="ui-icons.html" >Icon Styles</a>
                            </li>
                            <li>
                            <a class="" href="ui-fontawesome.html" >Font Awesome</a>
                            </li>
                            <li>
                            <a class="" href="ui-glyphicons.html" >Glyph Icons</a>
                            </li>
                        </ul>
                    </li>
                    <li class=""> 
                    <a href="javascript:;">
                    <i class="fa fa-table"></i>
                    <span class="title">Tables</span>
                        <span class="arrow "></span>
                        </a>
                        <ul class="sub-menu" >
                            <li>
                            <a class="" href="tables-basic.html" >Basic tables</a>
                            </li>
                            <li>
                            <a class="" href="tables-data.html" >Data Tables</a>
                            </li>
                            <li>
                            <a class="" href="tables-responsive.html" >Responsive Tables</a>
                            </li>
                        </ul>
                    </li><li class='menusection'>Maps</li>
                    <li class=""> 
                    <a href="ui-vectormaps.html">
                    <i class="fa fa-map-marker"></i>
                    <span class="title">Vector World Map</span>
                        </a>
                    </li>
                    <li class=""> 
                    <a href="ui-vectormaps-countries.html">
                    <i class="fa fa-map-marker"></i>
                    <span class="title">Vector Country Maps</span>
                        </a>
                    </li>
                    <li class=""> 
                    <a href="ui-googlemaps.html">
                    <i class="fa fa-map-marker"></i>
                    <span class="title">Google Maps</span>
                        </a>
                    </li>
                    <li class=""> 
                    <a href="ui-googlemapfull.html">
                    <i class="fa fa-map-marker"></i>
                    <span class="title">Google Map Search API</span>
                        </a>
                    </li><li class='menusection'>Extensions</li>
                    <li class=""> 
                    <a href="ui-imagecrop.html">
                    <i class="fa fa-file-image-o"></i>
                    <span class="title">Image Cropper</span>
                        </a>
                    </li>
                    <li class=""> 
                    <a href="javascript:;">
                    <i class="fa fa-upload"></i>
                    <span class="title">File Uploader</span>
                        <span class="arrow "></span>
                        </a>
                        <ul class="sub-menu" >
                            <li>
                            <a class="" href="form-fileupload-dropzone.html" >DropZone (Drag & Drop)</a>
                            </li>
                            <li>
                            <a class="" href="form-fileupload-custom.html" >Custom Uploader</a>
                            </li>
                        </ul>
                    </li>
                    <li class=""> 
                    <a href="javascript:;">
                    <i class="fa fa-table"></i>
                    <span class="title">Datatables</span>
                        <span class="arrow "></span>
                        </a>
                        <ul class="sub-menu" >
                            <li>
                            <a class="" href="tables-data-basic.html" >Basic Datatables</a>
                            </li>
                            <li>
                            <a class="" href="tables-data-collapsedrow.html" >Collapsed Rows</a>
                            </li>
                            <li>
                            <a class="" href="tables-data-download.html" >Download Formats</a>
                            </li>
                            <li>
                            <a class="" href="tables-data-complexheader.html" >Complex Headers</a>
                            </li>
                        </ul>
                    </li>
                    <li class=""> 
                    <a href="ui-sortable.html">
                    <i class="fa fa-sort-amount-asc"></i>
                    <span class="title">Sortable Group</span>
                        </a>
                    </li><li class='menusection'>Navigation</li>
                        <li class=""> <a href="javascript:;"> <i class="fa fa-folder-open"></i> <span class="title">Menu Levels</span> <span class="arrow "></span> </a>
                <ul class="sub-menu">
                    <li > <a href="javascript:;"> <span class="title">Level 1.1</span> </a> </li>
                    <li > <a href="javascript:;"> <span class="title">Level 1.2</span> <span class="arrow "></span> </a>
                        <ul class="sub-menu">
                            <li > <a href="javascript:;"> <span class="title">Level 2.1</span> </a></li>
                            <li > <a href="ujavascript:;"> <span class="title">Level 2.2</span> <span class="arrow "></span></a> 
                                <ul class="sub-menu">
                                    <li > <a href="javascript:;"> <span class="title">Level 3.1</span> <span class="arrow "></span></a> 
                                        <ul class="sub-menu">
                                            <li > <a href="ujavascript:;"> <span class="title">Level 4.1</span> </a> </li>
                                            <li > <a href="ujavascript:;"> <span class="title">Level 4.2</span> </a> </li>
                                        </ul>
                                    </li>
                                    <li > <a href="ujavascript:;"> <span class="title">Level 3.2</span> </a> </li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                </ul>
            </li>

            
        </ul>

    <div class="menustats">    
        <h5>Project Progress</h5>
        <div class="progress">
                    <div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width: 50%;">
                </div>
            </div>
        <h5>Target Achieved</h5>
        <div class="progress">
                    <div class="progress-bar progress-bar-accent" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width: 70%;">
                </div>
            </div>
    </div>

    </div>
    <!-- MAIN MENU - END -->



</div>
<!--  SIDEBAR - END -->
    <!-- START CONTENT -->
<section id="main-content" class=" ">
    <section class="wrapper main-wrapper row" style=''>

    <div class='col-xs-12'>
        <div class="page-title">

            <div class="pull-left">
                <!-- PAGE HEADING TAG - START --><h1 class="title">Form Elements - Basic Fields</h1><!-- PAGE HEADING TAG - END -->                            </div>

                            <div class="pull-right hidden-xs">
                    <ol class="breadcrumb">
                        <li>
                            <a href="index.html"><i class="fa fa-home"></i>Home</a>
                        </li>
                        <li>
                            <a href="form-elements.html">Form Elements</a>
                        </li>
                        <li class="active">
                            <strong>Field Elements</strong>
                        </li>
                    </ol>
                </div>
                                
        </div>
    </div>
    <div class="clearfix"></div>
    <!-- MAIN CONTENT AREA STARTS -->
    
<div class="col-xs-12">
    <section class="box ">
            <header class="panel_header">
                <h2 class="title pull-left">Basic Elements</h2>
                <div class="actions panel_actions pull-right">
                	<a class="box_toggle fa fa-chevron-down"></a>
                    <a class="box_setting fa fa-cog" data-toggle="modal" href="#section-settings"></a>
                    <a class="box_close fa fa-times"></a>
                </div>
            </header>
            <div class="content-body">
    <div class="row">
        <div class="box-content">

            <div class="form-group">
                 <div class="col-md-1">Section No*</div> 
                <div class="col-md-4">
                <input id="sectionId" value="${editSection.sectionId}"  name="sectionId" type="hidden"  >
                    <input id="sectionNo" class="form-control"
								placeholder="Section Code" value="${editSection.sectionNo}"  style="text-align: left;" name="sectionNo" type="text" required>
                </div>
                <div class="col-md-1"></div> 
                
                 <div class="col-md-2">Section Name*</div> 
                <div class="col-md-4">
                   <input id="sectionName" class="form-control"
								placeholder="Section Name" value="${editSection.sectionName}"  style="text-align: left;" name="sectionName" type="text" required>
                </div>
            </div><br><br> 

            <div class="form-group">
                 <div class="col-md-1">Section Description*</div> 
                <div class="col-md-4">
                 <input id="sectionDesc" class="form-control"
								placeholder="Section Description" value="${editSection.sectionDesc}"  style="text-align: left;" name="sectionDesc" type="text" required>
                </div>
                <div class="col-md-1"></div> 
                
                 <div class="col-md-2">Sequence No*</div> 
                <div class="col-md-4">
                   <input id="seqNo" class="form-control"
								placeholder="Sequence No" value="${editSection.sectionSortNo}"  style="text-align: left;" name="seqNo" type="text" required>
                </div>
            </div><br><br> 

        

        </div>
    </div>


    </div>
        </section></div>



<div class="col-xs-12">
    <section class="box ">
            <header class="panel_header">
                <h2 class="title pull-left">Input Groups</h2>
                <div class="actions panel_actions pull-right">
                	<a class="box_toggle fa fa-chevron-down"></a>
                    <a class="box_setting fa fa-cog" data-toggle="modal" href="#section-settings"></a>
                    <a class="box_close fa fa-times"></a>
                </div>
            </header>
            <div class="content-body">
    <div class="row">
        <div class="col-md-8 col-sm-9 col-xs-10">

            <div class="input-group transparent">
                <span class="input-group-addon">
                    <i class="fa fa-user"></i>
                </span>
                <input type="text" class="form-control" placeholder="Transparent">
            </div>

            <br>

            <div class="input-group">
                <span class="input-group-addon">
                    <span class="arrow"></span>
                    <i class="fa fa-envelope"></i>     
                </span>
                <input type="text" class="form-control" placeholder="Default">
            </div>                

            <br>

            <div class="input-group primary">
                <span class="input-group-addon">                
                    <span class="arrow"></span>
                    <i class="fa fa-user"></i>
                </span>
                <input type="text" class="form-control" placeholder="Primary">
            </div>


            <br>


            <div class="input-group primary">
                <input type="text" class="form-control text-right" placeholder="Right Align" aria-describedby="basic-addon1">
                <span class="input-group-addon" id="basic-addon1"><span class="arrow"></span><i class="fa fa-user"></i></span>
            </div>
            <br>

            <div class="input-group">
                <input type="text" class="form-control" placeholder="Recipient's username" aria-describedby="basic-addon2">
                <span class="input-group-addon" id="basic-addon2">@example.com</span>
            </div>
            <br>


            <div class="input-group primary">
                <span class="input-group-addon">$</span>
                <input type="text" class="form-control" aria-label="Amount (to the nearest dollar)">
                <span class="input-group-addon">.00</span>
            </div>







            <br>

            <div class="input-group">
                <span class="input-group-addon">$</span>
                <input type="text" class="form-control">
                <span class="input-group-addon">.00</span>
            </div>

            <br>

            <div class="input-group primary">
                <span class="input-group-btn">
                    <button type="button" class="btn btn-red dropdown-toggle" data-toggle="dropdown">
                        Action <span class="caret"></span>
                    </button>

                    <ul class="dropdown-menu dropdown-red no-spacing">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Separated link</a></li>
                    </ul>
                </span>

                <input type="text" class="form-control no-left-border form-focus-red" placeholder="Dropdown">
            </div>

            <br>


            <div class="input-group m-bot15">
                <span class="input-group-addon">
                    <input type="checkbox" class="iCheck">
                </span>
                <input type="text" class="form-control" placeholder="Input with Checkbox">
            </div>

        </div>
    </div>

    </div>
        </section></div>

<div class="col-xs-12">
    <section class="box ">
            <header class="panel_header">
                <h2 class="title pull-left">Element Sizes</h2>
                <div class="actions panel_actions pull-right">
                	<a class="box_toggle fa fa-chevron-down"></a>
                    <a class="box_setting fa fa-cog" data-toggle="modal" href="#section-settings"></a>
                    <a class="box_close fa fa-times"></a>
                </div>
            </header>
            <div class="content-body">
    <div class="row">
        <div class="col-md-8 col-sm-9 col-xs-10">

            <input class="form-control input-lg m-bot15" type="text" placeholder=".input-lg">
            <br>
            <input class="form-control m-bot15" type="text" placeholder="Default input">
            <br>
            <input class="form-control input-sm m-bot15" type="text" placeholder=".input-sm">
            <br>

            <select class="form-control input-lg m-bot15">
                <option>Option 1</option>
                <option>Option 2</option>
                <option>Option 3</option>
            </select>
            <br>
            <select class="form-control m-bot15">
                <option>Option 1</option>
                <option>Option 2</option>
                <option>Option 3</option>
            </select>
            <br>
            <select class="form-control input-sm m-bot15">
                <option>Option 1</option>
                <option>Option 2</option>
                <option>Option 3</option>
            </select>

        </div>
    </div>

    </div>
        </section></div>




<!-- MAIN CONTENT AREA ENDS -->
    </section>
    </section>
    <!-- END CONTENT -->
    <div class="page-chatapi hideit">

    <div class="search-bar">
        <input type="text" placeholder="Search" class="form-control">
    </div>

    <div class="chat-wrapper">
        <h4 class="group-head">Groups</h4>
        <ul class="group-list list-unstyled">
            <li class="group-row">
                <div class="group-status available">
                    <i class="fa fa-circle"></i>
                </div>
                <div class="group-info">
                    <h4><a href="#">Work</a></h4>
                </div>
            </li>
            <li class="group-row">
                <div class="group-status away">
                    <i class="fa fa-circle"></i>
                </div>
                <div class="group-info">
                    <h4><a href="#">Friends</a></h4>
                </div>
            </li>

        </ul>


        <h4 class="group-head">Favourites</h4>
        <ul class="contact-list">

                <li class="user-row " id='chat_user_1' data-user-id='1'>
                    <div class="user-img">
                        <a href="#"><img src="${pageContext.request.contextPath}/resources/data/profile/avatar-1.png" alt=""></a>
                    </div>
                    <div class="user-info">
                        <h4><a href="#">Clarine Vassar</a></h4>
                        <span class="status available" data-status="available"> Available</span>
                    </div>
                    <div class="user-status available">
                        <i class="fa fa-circle"></i>
                    </div>
                </li>
                <li class="user-row " id='chat_user_2' data-user-id='2'>
                    <div class="user-img">
                        <a href="#"><img src="${pageContext.request.contextPath}/resources/data/profile/avatar-2.png" alt=""></a>
                    </div>
                    <div class="user-info">
                        <h4><a href="#">Brooks Latshaw</a></h4>
                        <span class="status away" data-status="away"> Away</span>
                    </div>
                    <div class="user-status away">
                        <i class="fa fa-circle"></i>
                    </div>
                </li>
                <li class="user-row " id='chat_user_3' data-user-id='3'>
                    <div class="user-img">
                        <a href="#"><img src="${pageContext.request.contextPath}/resources/data/profile/avatar-3.png" alt=""></a>
                    </div>
                    <div class="user-info">
                        <h4><a href="#">Clementina Brodeur</a></h4>
                        <span class="status busy" data-status="busy"> Busy</span>
                    </div>
                    <div class="user-status busy">
                        <i class="fa fa-circle"></i>
                    </div>
                </li>

        </ul>


        <h4 class="group-head">More Contacts</h4>
        <ul class="contact-list">

                <li class="user-row " id='chat_user_4' data-user-id='4'>
                    <div class="user-img">
                        <a href="#"><img src="${pageContext.request.contextPath}/resources/data/profile/avatar-4.png" alt=""></a>
                    </div>
                    <div class="user-info">
                        <h4><a href="#">Carri Busey</a></h4>
                        <span class="status offline" data-status="offline"> Offline</span>
                    </div>
                    <div class="user-status offline">
                        <i class="fa fa-circle"></i>
                    </div>
                </li>
                <li class="user-row " id='chat_user_5' data-user-id='5'>
                    <div class="user-img">
                        <a href="#"><img src="${pageContext.request.contextPath}/resources/data/profile/avatar-5.png" alt=""></a>
                    </div>
                    <div class="user-info">
                        <h4><a href="#">Melissa Dock</a></h4>
                        <span class="status offline" data-status="offline"> Offline</span>
                    </div>
                    <div class="user-status offline">
                        <i class="fa fa-circle"></i>
                    </div>
                </li>
                <li class="user-row " id='chat_user_6' data-user-id='6'>
                    <div class="user-img">
                        <a href="#"><img src="${pageContext.request.contextPath}/resources/data/profile/avatar-1.png" alt=""></a>
                    </div>
                    <div class="user-info">
                        <h4><a href="#">Verdell Rea</a></h4>
                        <span class="status available" data-status="available"> Available</span>
                    </div>
                    <div class="user-status available">
                        <i class="fa fa-circle"></i>
                    </div>
                </li>
                <li class="user-row " id='chat_user_7' data-user-id='7'>
                    <div class="user-img">
                        <a href="#"><img src="${pageContext.request.contextPath}/resources/data/profile/avatar-2.png" alt=""></a>
                    </div>
                    <div class="user-info">
                        <h4><a href="#">Linette Lheureux</a></h4>
                        <span class="status busy" data-status="busy"> Busy</span>
                    </div>
                    <div class="user-status busy">
                        <i class="fa fa-circle"></i>
                    </div>
                </li>
                <li class="user-row " id='chat_user_8' data-user-id='8'>
                    <div class="user-img">
                        <a href="#"><img src="${pageContext.request.contextPath}/resources/data/profile/avatar-3.png" alt=""></a>
                    </div>
                    <div class="user-info">
                        <h4><a href="#">Araceli Boatright</a></h4>
                        <span class="status away" data-status="away"> Away</span>
                    </div>
                    <div class="user-status away">
                        <i class="fa fa-circle"></i>
                    </div>
                </li>
                <li class="user-row " id='chat_user_9' data-user-id='9'>
                    <div class="user-img">
                        <a href="#"><img src="${pageContext.request.contextPath}/resources/data/profile/avatar-4.png" alt=""></a>
                    </div>
                    <div class="user-info">
                        <h4><a href="#">Clay Peskin</a></h4>
                        <span class="status busy" data-status="busy"> Busy</span>
                    </div>
                    <div class="user-status busy">
                        <i class="fa fa-circle"></i>
                    </div>
                </li>
                <li class="user-row " id='chat_user_10' data-user-id='10'>
                    <div class="user-img">
                        <a href="#"><img src="${pageContext.request.contextPath}/resources/data/profile/avatar-5.png" alt=""></a>
                    </div>
                    <div class="user-info">
                        <h4><a href="#">Loni Tindall</a></h4>
                        <span class="status away" data-status="away"> Away</span>
                    </div>
                    <div class="user-status away">
                        <i class="fa fa-circle"></i>
                    </div>
                </li>
                <li class="user-row " id='chat_user_11' data-user-id='11'>
                    <div class="user-img">
                        <a href="#"><img src="${pageContext.request.contextPath}/resources/data/profile/avatar-1.png" alt=""></a>
                    </div>
                    <div class="user-info">
                        <h4><a href="#">Tanisha Kimbro</a></h4>
                        <span class="status idle" data-status="idle"> Idle</span>
                    </div>
                    <div class="user-status idle">
                        <i class="fa fa-circle"></i>
                    </div>
                </li>
                <li class="user-row " id='chat_user_12' data-user-id='12'>
                    <div class="user-img">
                        <a href="#"><img src="${pageContext.request.contextPath}/resources/data/profile/avatar-2.png" alt=""></a>
                    </div>
                    <div class="user-info">
                        <h4><a href="#">Jovita Tisdale</a></h4>
                        <span class="status idle" data-status="idle"> Idle</span>
                    </div>
                    <div class="user-status idle">
                        <i class="fa fa-circle"></i>
                    </div>
                </li>

        </ul>
    </div>

</div>


<div class="chatapi-windows ">




</div>    </div>
    <!-- END CONTAINER -->
<!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->


<!-- CORE JS FRAMEWORK - START --> 
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery-1.11.2.min.js" type="text/javascript"></script> 
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.easing.min.js" type="text/javascript"></script> 
<script src="${pageContext.request.contextPath}/resources/assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script> 
<script src="${pageContext.request.contextPath}/resources/assets/plugins/pace/pace.min.js" type="text/javascript"></script>  
<script src="${pageContext.request.contextPath}/resources/assets/plugins/perfect-scrollbar/perfect-scrollbar.min.js" type="text/javascript"></script> 
<script src="${pageContext.request.contextPath}/resources/assets/plugins/viewport/viewportchecker.js" type="text/javascript"></script>  
<script>window.jQuery||document.write('<script src="${pageContext.request.contextPath}/resources/assets/js/jquery-1.11.2.min.js"><\/script>');</script>
<!-- CORE JS FRAMEWORK - END --> 


<!-- OTHER SCRIPTS INCLUDED ON THIS PAGE - START --> 

<script src="${pageContext.request.contextPath}/resources/assets/plugins/autosize/autosize.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/assets/plugins/icheck/icheck.min.js" type="text/javascript"></script>
<!-- OTHER SCRIPTS INCLUDED ON THIS PAGE - END --> 


<!-- CORE TEMPLATE JS - START --> 
<script src="${pageContext.request.contextPath}/resources/assets/js/scripts.js" type="text/javascript"></script> 
<!-- END CORE TEMPLATE JS - END --> 


<!-- General section box modal start -->
<div class="modal" id="section-settings" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog animated bounceInDown">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Section Settings</h4>
            </div>
            <div class="modal-body">

                Body goes here...

            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">Close</button>
                <button class="btn btn-success" type="button">Save changes</button>
            </div>
        </div>
    </div>
</div>
<!-- modal end -->
</body>
</html>
 