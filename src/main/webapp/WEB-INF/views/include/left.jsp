<body  >
<div class="page-sidebar pagescroll">
    <!-- MAIN MENU - START -->
    <div class="page-sidebar-wrapper" id="main-menu-wrapper">
        <!-- USER INFO - START -->
        <div class="profile-info row">
            <div class="profile-image col-xs-4">
                <a href="ui-profile.html">
                <img alt="" src="../data/profile/profile.jpg" class="img-responsive img-circle">
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
            <li class="open"> 
                <a href="index.html">
                <i class="fa fa-dashboard"></i>
                <span class="title">Dashboard</span>
                </a>
            </li>
            
            <li class="">
                <a href="javascript:;">
                <i class="fa fa-columns"></i>
                <span class="title">Admin</span>
                <span class="arrow "></span>
                </a>
                <ul class="sub-menu" >
               		 <li>
                        <a class="" href="${pageContext.request.contextPath}/addUser" >Add Admin  </a>
                        <a class="" href="${pageContext.request.contextPath}/userList" >Admin List </a>
                    </li>
                    
                     
                </ul>
            </li>
            
            <li class="">
                <a href="javascript:;">
                <i class="fa fa-columns"></i>
                <span class="title">Front End Control</span>
                <span class="arrow "></span>
                </a>
                <ul class="sub-menu" >
               		 
                    
                    <li>
                        <a href="${pageContext.request.contextPath}/addLogo">Add Logo</a>
                    </li>
                     <li>
                        <a href="${pageContext.request.contextPath}/addMetaNew">Add Meta Data</a>
                    </li>
                     <li>
                        <a href="${pageContext.request.contextPath}/addMetaNew">Add Social Links</a>
                    </li>
                      <li>
                        <a href="${pageContext.request.contextPath}/sliderPicList">Header Slider</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/uploadOtherMedia">Upload Media/Files</a>
                    </li>
                     <li>
                        <a href="${pageContext.request.contextPath}/maintenanceMode">Maintenance Mode</a>
                    </li>
                     
                </ul>
            </li>
            
            <li class="">
                <a href="javascript:;">
                <i class="fa fa-columns"></i>
                <span class="title">Section</span>
                <span class="arrow "></span>
                </a>
                <ul class="sub-menu" >
               		 <li>
                        <a class="" href="${pageContext.request.contextPath}/sectionList" >Section List </a>
                    </li>
                    <li>
                        <a class="" href="${pageContext.request.contextPath}/categoryList" >Menu List </a>
                    </li>
                    <li>
                        <a class="" href="${pageContext.request.contextPath}/subCategoryList" >Sub Menu List</a>
                    </li>
                     <li>
                        <a href="${pageContext.request.contextPath}/sectionTreeList">Add Content</a>
                    </li>
                    
                    <li>
                        <a href="${pageContext.request.contextPath}/addLogo">Add Logo</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/uploadDocForm">Upload Document</a>
                    </li>
                     
                </ul>
            </li>
            
             
                
             <li  > 
                    <a href="#">
                    <i class="fa fa-lock"></i>
                    <span class="title">Logout</span>
                        </a>
                    </li> 
        </ul>
        <div class="menustats">    
        </div>
    </div>
    <!-- MAIN MENU - END -->
</div>
</body>