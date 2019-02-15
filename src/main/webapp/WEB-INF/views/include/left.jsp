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
			            	<a href="${pageContext.request.contextPath}/addImageLink" onclick="selectSubMod(2,205)">Other GOV Links</a>
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
                    
                   
                   
                     
                </ul>
                 <a href="javascript:;">
                <i class="fa fa-columns"></i>
                <span class="title">Content List</span>
               		<c:choose>
		            	<c:when test="${sessionScope.sessionModuleId==4}">
		            		 <span class="arrow open"></span>
		            	</c:when>
		            	<c:otherwise> 
		            	  <span class="arrow "></span>
		            	</c:otherwise>
	            	</c:choose>
                </a>
             <ul class="sub-menu" >
             <li  > 
                    <a href="${pageContext.request.contextPath}/cmsList">
               
                    <span class="title">CMS List</span>
                        </a>
                    </li> 
                    <a href="${pageContext.request.contextPath}/faqList">
                    
                    <span class="title">FAQ List</span>
                        </a>
                    </li>   
                       <li  > 
                    <a href="${pageContext.request.contextPath}/testImonialList">
                  
                    <span class="title">TestImonial List</span>
                        </a>
                    </li>   
                         
            
            <li  > 
                    <a href="${pageContext.request.contextPath}/galleryCategoryList">
                  
                    <span class="title">Gallery Category List</span>
                        </a>
                    </li>   
                
             <li  > 
              <li  > 
                    <a href="${pageContext.request.contextPath}/documentUploadList">
                  
                    <span class="title">Document List</span>
                        </a>
                    </li>   
                        <li  > 
                    <a href="${pageContext.request.contextPath}/NewsBlogList">
                  
                    <span class="title">News Blog List</span>
                        </a>
                    </li>   
                
               </ul>
              <li>
                    	
			            	
			            	 <a href="${pageContext.request.contextPath}/uploadOtherMedia" >
			            	   <i class="fa fa-columns"></i>
			             <span class="title">Upload Media/Files</span>
		            	</a>
                    </li>
          
             
              
                   <li> 
                    <a href="${pageContext.request.contextPath}/ContactList">
                    <i class="fa fa-columns"></i>
                    <span class="title">Contact List</span>
                        </a>
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