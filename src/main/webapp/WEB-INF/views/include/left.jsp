<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body  >
<c:url var="setSubModId" value="/setSubModId" />
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
            <c:choose>
            	<c:when test="${sessionScope.sessionModuleId==1}">
            	 <li class="open">
            	</c:when>
            	<c:otherwise> 
            	 <li class="">
            	</c:otherwise>
            </c:choose>  
                <a href="javascript:;">
                <i class="fa fa-columns"></i>
                <span class="title">Admin</span>
	                <c:choose>
		            	<c:when test="${sessionScope.sessionModuleId==1}">
		            		 <span class="arrow open"></span>
		            	</c:when>
		            	<c:otherwise> 
		            	  <span class="arrow "></span>
		            	</c:otherwise>
	            	</c:choose>		
               
                </a>
                <ul class="sub-menu" >
               		 <li >
	               		 <c:choose>
			            	<c:when test="${sessionScope.sessionSubModuleId==101}">
			            		 <a class="active" href="${pageContext.request.contextPath}/addUser" onclick="selectSubMod(1,101)">Add Admin  </a>
			            	</c:when>
			            	<c:otherwise> 
			            	  <a class="" href="${pageContext.request.contextPath}/addUser" onclick="selectSubMod(1,101)">Add Admin  </a>
			            	</c:otherwise>
		            	</c:choose>	
                         
                    </li>
                     <li >
                       <c:choose>
			            	<c:when test="${sessionScope.sessionSubModuleId==102}">
			            		 <a class="active" href="${pageContext.request.contextPath}/userList" onclick="selectSubMod(1,102)">Admin List </a>
			            	</c:when>
			            	<c:otherwise> 
			            	  <a class="" href="${pageContext.request.contextPath}/userList" onclick="selectSubMod(1,102)">Admin List </a>
			            	</c:otherwise>
		            	</c:choose>	
                        
                    </li>
                    
                     
                </ul>
            </li>
            
            <c:choose>
            	<c:when test="${sessionScope.sessionModuleId==2}">
            	 <li class="open">
            	</c:when>
            	<c:otherwise> 
            	 <li class="">
            	</c:otherwise>
            </c:choose>
                <a href="javascript:;">
                <i class="fa fa-columns"></i>
                <span class="title">Front End Control</span>
                	<c:choose>
		            	<c:when test="${sessionScope.sessionModuleId==2}">
		            		 <span class="arrow open"></span>
		            	</c:when>
		            	<c:otherwise> 
		            	  <span class="arrow "></span>
		            	</c:otherwise>
	            	</c:choose>
                </a>
                <ul class="sub-menu" > 
                    <li>
                    	<c:choose>
			            	<c:when test="${sessionScope.sessionSubModuleId==201}"> 
			            		 <a class="active" href="${pageContext.request.contextPath}/addLogo" onclick="selectSubMod(2,201)">Add Logo</a>
			            	</c:when>
			            	<c:otherwise> 
			            	  <a  href="${pageContext.request.contextPath}/addLogo" onclick="selectSubMod(2,201)">Add Logo</a>
			            	</c:otherwise>
		            	</c:choose>	
                        
                    </li>
                     <li>
                     	<c:choose>
			            	<c:when test="${sessionScope.sessionSubModuleId==202}"> 
			            		 <a class="active" href="${pageContext.request.contextPath}/addMetaNew" onclick="selectSubMod(2,202)">Add Meta Data</a>
			            	</c:when>
			            	<c:otherwise> 
			            	 <a class=" " href="${pageContext.request.contextPath}/addMetaNew" onclick="selectSubMod(2,202)">Add Meta Data</a>
			            	</c:otherwise>
		            	</c:choose>	
                        
                    </li>
                     <li>
                     	<c:choose>
			            	<c:when test="${sessionScope.sessionSubModuleId==203}"> 
			            		 <a class="active" href="${pageContext.request.contextPath}/addMetaNew" onclick="selectSubMod(2,203)">Add Social Links</a>
			            	</c:when>
			            	<c:otherwise> 
			            	 <a href="${pageContext.request.contextPath}/addMetaNew" onclick="selectSubMod(2,203)">Add Social Links</a>
			            	</c:otherwise>
		            	</c:choose>
                        
                    </li>
                      <li>
                      	<c:choose>
			            	<c:when test="${sessionScope.sessionSubModuleId==204}"> 
			            		<a class="active" href="${pageContext.request.contextPath}/sliderPicList" onclick="selectSubMod(2,204)">Header Slider</a>
			            	</c:when>
			            	<c:otherwise> 
			            	 <a href="${pageContext.request.contextPath}/sliderPicList" onclick="selectSubMod(2,204)">Header Slider</a>
			            	</c:otherwise>
		            	</c:choose>
                        
                    </li>
                    <li>
                    	<c:choose>
			            	<c:when test="${sessionScope.sessionSubModuleId==205}"> 
			            		<a class="active" href="${pageContext.request.contextPath}/addImageLink" onclick="selectSubMod(2,205)">Image Link</a>
			            	</c:when>
			            	<c:otherwise> 
			            	<a href="${pageContext.request.contextPath}/addImageLink" onclick="selectSubMod(2,205)">Image Link</a>
			            	</c:otherwise>
		            	</c:choose>
                        
                    </li>
                    <li>
                    	<c:choose>
			            	<c:when test="${sessionScope.sessionSubModuleId==206}"> 
			            		 <a class="active" href="${pageContext.request.contextPath}/uploadOtherMedia" onclick="selectSubMod(2,206)">Upload Media/Files</a>
			            	</c:when>
			            	<c:otherwise> 
			            	 <a href="${pageContext.request.contextPath}/uploadOtherMedia" onclick="selectSubMod(2,206)">Upload Media/Files</a>
			            	</c:otherwise>
		            	</c:choose>
                       
                    </li>
                     <li>
                     	 <c:choose>
			            	<c:when test="${sessionScope.sessionSubModuleId==207}"> 
			            		 <a class="active" href="${pageContext.request.contextPath}/maintenanceMode" onclick="selectSubMod(2,207)">Maintenance Mode</a>
			            	</c:when>
			            	<c:otherwise> 
			            	 <a href="${pageContext.request.contextPath}/maintenanceMode" onclick="selectSubMod(2,207)">Maintenance Mode</a>
			            	</c:otherwise>
		            	</c:choose>
                        
                    </li>
                     
                </ul>
            </li>
            
           <c:choose>
            	<c:when test="${sessionScope.sessionModuleId==3}">
            	 <li class="open">
            	</c:when>
            	<c:otherwise> 
            	 <li class="">
            	</c:otherwise>
            </c:choose>
                <a href="javascript:;">
                <i class="fa fa-columns"></i>
                <span class="title">Section</span>
               		<c:choose>
		            	<c:when test="${sessionScope.sessionModuleId==3}">
		            		 <span class="arrow open"></span>
		            	</c:when>
		            	<c:otherwise> 
		            	  <span class="arrow "></span>
		            	</c:otherwise>
	            	</c:choose>
                </a>
                <ul class="sub-menu" >
               		 <li>
               		 	<c:choose>
			            	<c:when test="${sessionScope.sessionSubModuleId==301}"> 
			            		  
			            		 <a class="active" href="${pageContext.request.contextPath}/sectionList" onclick="selectSubMod(3,301)">Section List </a>
			            	</c:when>
			            	<c:otherwise> 
			            	 <a class="" href="${pageContext.request.contextPath}/sectionList" onclick="selectSubMod(3,301)">Section List </a>
			            	</c:otherwise>
		            	</c:choose>
                        
                    </li>
                    <li>
                    	<c:choose>
			            	<c:when test="${sessionScope.sessionSubModuleId==302}">  
			            		<a class="active" href="${pageContext.request.contextPath}/categoryList" onclick="selectSubMod(3,302)">Menu List </a>
			            	</c:when>
			            	<c:otherwise> 
			            	 <a class="" href="${pageContext.request.contextPath}/categoryList" onclick="selectSubMod(3,302)">Menu List </a>
			            	</c:otherwise>
		            	</c:choose>
                        
                    </li>
                    <li>
                    	<c:choose>
			            	<c:when test="${sessionScope.sessionSubModuleId==303}">  
			            		<a class="active" href="${pageContext.request.contextPath}/subCategoryList" onclick="selectSubMod(3,303)">Sub Menu List</a>
			            	</c:when>
			            	<c:otherwise> 
			            	 <a class="" href="${pageContext.request.contextPath}/subCategoryList" onclick="selectSubMod(3,303)">Sub Menu List</a>
			            	</c:otherwise>
		            	</c:choose>
                        
                    </li>
                     <li>
                     	<c:choose>
			            	<c:when test="${sessionScope.sessionSubModuleId==304}">  
			            		<a class="active" href="${pageContext.request.contextPath}/sectionTreeList" onclick="selectSubMod(3,304)">Add Content</a>
			            	</c:when>
			            	<c:otherwise> 
			            	<a class=" " href="${pageContext.request.contextPath}/sectionTreeList" onclick="selectSubMod(3,304)">Add Content</a>
			            	</c:otherwise>
		            	</c:choose>
                        
                    </li>
                    
                    <li>	
                    	<c:choose>
			            	<c:when test="${sessionScope.sessionSubModuleId==305}">  
			            		<a class="active" href="${pageContext.request.contextPath}/addLogo" onclick="selectSubMod(3,305)">Add Logo</a>
			            	</c:when>
			            	<c:otherwise> 
			            		<a class=" " href="${pageContext.request.contextPath}/addLogo" onclick="selectSubMod(3,305)">Add Logo</a>
			            	</c:otherwise>
		            	</c:choose>
                        
                    </li>
                    <li>
                    	<c:choose>
			            	<c:when test="${sessionScope.sessionSubModuleId==306}">  
			            		<a class="active" href="${pageContext.request.contextPath}/uploadDocForm" onclick="selectSubMod(3,306)">Upload Document</a>
			            	</c:when>
			            	<c:otherwise> 
			            		<a class=" " href="${pageContext.request.contextPath}/uploadDocForm" onclick="selectSubMod(3,306)">Upload Document</a>
			            	</c:otherwise>
		            	</c:choose>
                        
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
    <script>
  
 function selectSubMod(modId, subModId){
	 
	 $.getJSON('${setSubModId}', {
		 subModId : subModId,
		 modId : modId,
						ajax : 'true'
					});
 }
 
 </script>
    <!-- MAIN MENU - END -->
</div>
</body>