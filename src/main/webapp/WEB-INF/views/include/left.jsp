<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body>
	<c:url var="setSubModId" value="/setSubModId" />
	<c:url var="clearUserSessionAttribute" value="/clearUserSessionAttribute" />
	<div class="page-sidebar pagescroll">
		<!-- MAIN MENU - START -->
		<div class="page-sidebar-wrapper" id="main-menu-wrapper">
			<!-- USER INFO - START -->
			<!-- <div class="profile-info row">
				<div class="profile-image col-xs-4">
					<a href="ui-profile.html"> <img alt=""
						src="../data/profile/profile.jpg"
						class="img-responsive img-circle">
					</a>
				</div>
				<div class="profile-details col-xs-8">
					<h3>
						<a href="ui-profile.html">Shane Taylor</a>
						Available statuses: online, idle, busy, away and offline
						<span class="profile-status online"></span>
					</h3>
					<p class="profile-title">Web Developer</p>
				</div>
			</div> -->
			<!-- USER INFO - END -->
			<ul class='wraplist'>
				<li class='menusection'>&nbsp;</li>
				<c:choose>
					<c:when test="${sessionScope.sessionModuleId==0}">
						<li class="open">
					</c:when>
					<c:otherwise>
						<li class="">
					</c:otherwise>
				</c:choose>
				<a href="${pageContext.request.contextPath}/dashboard"
					onclick="selectSubMod(0,0)"> <i class="fa fa-dashboard"></i> <span
					class="title">Dashboard</span>
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
				<a href="javascript:;"> <i class="fa fa fa-male"></i> <span
					class="title">Admin</span> <c:choose>
						<c:when test="${sessionScope.sessionModuleId==1}">
							<span class="arrow open"></span>
						</c:when>
						<c:otherwise>
							<span class="arrow "></span>
						</c:otherwise>
					</c:choose>

				</a>
				
				<ul class="sub-menu">
					<li><c:choose>
							<c:when test="${sessionScope.sessionSubModuleId==101}">
								<a class="active"
									href="${pageContext.request.contextPath}/addUser"
									onclick="selectSubMod(1,101)">Add Admin </a>
							</c:when>
							<c:otherwise>
								<a class="" href="${pageContext.request.contextPath}/addUser"
									onclick="selectSubMod(1,101)">Add Admin </a>
							</c:otherwise>
						</c:choose></li>
					<li><c:choose>
							<c:when test="${sessionScope.sessionSubModuleId==102}">
								<a class="active"
									href="${pageContext.request.contextPath}/userList"
									onclick="selectSubMod(1,102)">Admin List </a>
							</c:when>
							<c:otherwise>
								<a class="" href="${pageContext.request.contextPath}/userList"
									onclick="selectSubMod(1,102)">Admin List </a>
							</c:otherwise>
						</c:choose></li>


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
				<a href="javascript:;"> <i class="fa fa fa fa-desktop"></i> <span
					class="title">Front End Control</span> <c:choose>
						<c:when test="${sessionScope.sessionModuleId==2}">
							<span class="arrow open"></span>
						</c:when>
						<c:otherwise>
							<span class="arrow "></span>
						</c:otherwise>
					</c:choose>
				</a>
				<ul class="sub-menu">
					<li><c:choose>
							<c:when test="${sessionScope.sessionSubModuleId==201}">
								<a class="active"
									href="${pageContext.request.contextPath}/addLogo"
									onclick="selectSubMod(2,201)">Add Logo</a>
							</c:when>
							<c:otherwise>
								<a href="${pageContext.request.contextPath}/addLogo"
									onclick="selectSubMod(2,201)">Add Logo</a>
							</c:otherwise>
						</c:choose></li>
					<li><c:choose>
							<c:when test="${sessionScope.sessionSubModuleId==202}">
								<a class="active"
									href="${pageContext.request.contextPath}/addMetaNew"
									onclick="selectSubMod(2,202)">Add Meta Data</a>
							</c:when>
							<c:otherwise>
								<a class=" "
									href="${pageContext.request.contextPath}/addMetaNew"
									onclick="selectSubMod(2,202)">Add Meta Data</a>
							</c:otherwise>
						</c:choose></li>
					<li><c:choose>
							<c:when test="${sessionScope.sessionSubModuleId==203}">
								<a class="active"
									href="${pageContext.request.contextPath}/socialChannelList"
									onclick="selectSubMod(2,203)">Social Links</a>
							</c:when>
							<c:otherwise>
								<a href="${pageContext.request.contextPath}/socialChannelList"
									onclick="selectSubMod(2,203)">Social Links </a>
							</c:otherwise>
						</c:choose></li>

					<c:if test="${sessionScope.UserDetail.roles eq 'DA'}">
						<li><c:choose>
								<c:when test="${sessionScope.sessionSubModuleId==204}">
									<a class="active"
										href="${pageContext.request.contextPath}/sliderPicList"
										onclick="selectSubMod(2,204)">Header Slider</a>
								</c:when>
								<c:otherwise>
									<a href="${pageContext.request.contextPath}/sliderPicList"
										onclick="selectSubMod(2,204)">Header Slider</a>
								</c:otherwise>
							</c:choose></li>
					</c:if>

					<li><c:choose>
							<c:when test="${sessionScope.sessionSubModuleId==205}">
								<a class="active"
									href="${pageContext.request.contextPath}/imageLinkList"
									onclick="selectSubMod(2,205)">Other GOV Links</a>
							</c:when>
							<c:otherwise>
								<a href="${pageContext.request.contextPath}/imageLinkList"
									onclick="selectSubMod(2,205)">Other Links</a>
							</c:otherwise>
						</c:choose></li>

					<li><c:choose>
							<c:when test="${sessionScope.sessionSubModuleId==207}">
								<a class="active"
									href="${pageContext.request.contextPath}/siteMaintenances"
									onclick="selectSubMod(2,207)">Maintenance Mode</a>
							</c:when>
							<c:otherwise>
								<a href="${pageContext.request.contextPath}/siteMaintenances"
									onclick="selectSubMod(2,207)">Maintenance Mode</a>
							</c:otherwise>
						</c:choose></li>

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
				<a href="javascript:;"> <i class="fa fa-sitemap"></i> <span
					class="title">Section</span> <c:choose>
						<c:when test="${sessionScope.sessionModuleId==3}">
							<span class="arrow open"></span>
						</c:when>
						<c:otherwise>
							<span class="arrow "></span>
						</c:otherwise>
					</c:choose>
				</a>
				<ul class="sub-menu">
					<c:if test="${sessionScope.UserDetail.roles eq 'DA'}">
						<li><c:choose>
								<c:when test="${sessionScope.sessionSubModuleId==301}">

									<a class="active"
										href="${pageContext.request.contextPath}/sectionList"
										onclick="selectSubMod(3,301)">Section List </a>
								</c:when>
								<c:otherwise>
									<a class=""
										href="${pageContext.request.contextPath}/sectionList"
										onclick="selectSubMod(3,301)">Section List </a>
								</c:otherwise>
							</c:choose></li>
						<li><c:choose>
								<c:when test="${sessionScope.sessionSubModuleId==302}">
									<a class="active"
										href="${pageContext.request.contextPath}/categoryList"
										onclick="selectSubMod(3,302)">Menu List </a>
								</c:when>
								<c:otherwise>
									<a class=""
										href="${pageContext.request.contextPath}/categoryList"
										onclick="selectSubMod(3,302)">Menu List </a>
								</c:otherwise>
							</c:choose></li>
						<li><c:choose>
								<c:when test="${sessionScope.sessionSubModuleId==303}">
									<a class="active"
										href="${pageContext.request.contextPath}/subCategoryList"
										onclick="selectSubMod(3,303)">Sub Menu List</a>
								</c:when>
								<c:otherwise>
									<a class=""
										href="${pageContext.request.contextPath}/subCategoryList"
										onclick="selectSubMod(3,303)">Sub Menu List</a>
								</c:otherwise>
							</c:choose></li>
					</c:if>
					<li><c:choose>
							<c:when test="${sessionScope.sessionSubModuleId==304}">
								<a class="active"
									href="${pageContext.request.contextPath}/sectionTreeList"
									onclick="selectSubMod(3,304)">Add Content</a>
							</c:when>
							<c:otherwise>
								<a class=" "
									href="${pageContext.request.contextPath}/sectionTreeList"
									onclick="selectSubMod(3,304)">Add Content</a>
							</c:otherwise>
						</c:choose></li>




				</ul>


				<c:choose>
					<c:when test="${sessionScope.sessionModuleId==4}">
						<li class="open">
					</c:when>
					<c:otherwise>
						<li class="">
					</c:otherwise>
				</c:choose>
				<a href="javascript:;"> <i class="fa fa-reorder"></i> <span
					class="title">Content List</span> <c:choose>
						<c:when test="${sessionScope.sessionModuleId==4}">
							<span class="arrow open"></span>
						</c:when>
						<c:otherwise>
							<span class="arrow "></span>
						</c:otherwise>
					</c:choose>
				</a>
				<ul class="sub-menu">

					<li><c:choose>
							<c:when test="${sessionScope.sessionSubModuleId==404}">

								<a class="active"
									href="${pageContext.request.contextPath}/galleryCategoryList"
									onclick="selectSubMod(4,404)"> <span class="title"> Category List</span>
								</a>
							</c:when>
							<c:otherwise>
								<a href="${pageContext.request.contextPath}/galleryCategoryList"
									onclick="selectSubMod(4,404)"> <span class="title"> Category List</span>
								</a>
							</c:otherwise>
						</c:choose></li>
					<li><c:choose>
							<c:when test="${sessionScope.sessionSubModuleId==401}">
								<a class="active"
									href="${pageContext.request.contextPath}/cmsList"
									onclick="selectSubMod(4,401)"> <span class="title">CMS
										List</span></a>
							</c:when>
							<c:otherwise>
								<a class=" " href="${pageContext.request.contextPath}/cmsList"
									onclick="selectSubMod(4,401)"> <span class="title">CMS
										List</span></a>
							</c:otherwise>
						</c:choose></li>

					<li><c:choose>
							<c:when test="${sessionScope.sessionSubModuleId==402}">
								<a class="active"
									href="${pageContext.request.contextPath}/faqList"
									onclick="selectSubMod(4,402)"> <span class="title">FAQ
										List</span>
								</a>
							</c:when>
							<c:otherwise>
								<a href="${pageContext.request.contextPath}/faqList"
									onclick="selectSubMod(4,402)"> <span class="title">FAQ
										List</span>
								</a>
							</c:otherwise>
						</c:choose></li>
						<li><c:choose>
							<c:when test="${sessionScope.sessionSubModuleId==407}">
								<a class="active"
									href="${pageContext.request.contextPath}/uploadedImageList"
									onclick="selectSubMod(4,407)"> <span class="title">Gallery Image
										List</span>
								</a>
							</c:when>
							<c:otherwise>
								<a href="${pageContext.request.contextPath}/uploadedImageList"
									onclick="selectSubMod(4,407)"> <span class="title">Gallery Image
										List</span>
								</a>
							</c:otherwise>
						</c:choose></li>
						
						<li><c:choose>
							<c:when test="${sessionScope.sessionSubModuleId==409}">
								<a class="active"
									href="${pageContext.request.contextPath}/VedioFormList"
									onclick="selectSubMod(4,409)"> <span class="title">Video List</span>
								</a>
							</c:when>
							<c:otherwise>
								<a href="${pageContext.request.contextPath}/VedioFormList"
									onclick="selectSubMod(4,409)"> <span class="title">Video
										List</span>
								</a>
							</c:otherwise>
						</c:choose></li>
						
					<li><c:choose>
							<c:when test="${sessionScope.sessionSubModuleId==403}">
								<a class="active"
									href="${pageContext.request.contextPath}/testImonialList"
									onclick="selectSubMod(4,403)"> <span class="title">TestImonial
										List</span>
								</a>
							</c:when>
							<c:otherwise>
								<a href="${pageContext.request.contextPath}/testImonialList"
									onclick="selectSubMod(4,403)"> <span class="title">TestImonial
										List</span>
								</a>
							</c:otherwise>
						</c:choose></li>
						
					<li><c:choose>
							<c:when test="${sessionScope.sessionSubModuleId==410}">
								<a class="active"
									href="${pageContext.request.contextPath}/successStoryList"
									onclick="selectSubMod(4,410)"> <span class="title">Success Story
										List</span>
								</a>
							</c:when>
							<c:otherwise>
								<a href="${pageContext.request.contextPath}/successStoryList"
									onclick="selectSubMod(4,410)"> <span class="title">Success Story
										List</span>
								</a>
							</c:otherwise>
						</c:choose></li>
						
						<li><c:choose>
							<c:when test="${sessionScope.sessionSubModuleId==408}">
								<a class="active"
									href="${pageContext.request.contextPath}/teamList"
									onclick="selectSubMod(4,408)"> <span class="title">Team
										List</span>
								</a>
							</c:when>
							<c:otherwise>
								<a href="${pageContext.request.contextPath}/teamList"
									onclick="selectSubMod(4,408)"> <span class="title">Team
										List</span>
								</a>
							</c:otherwise>
						</c:choose></li>


					<li>
					<li><c:choose>
							<c:when test="${sessionScope.sessionSubModuleId==405}">

								<a class="active"
									href="${pageContext.request.contextPath}/documentUploadList"
									onclick="selectSubMod(4,405)"> <span class="title">Document
										List</span>
								</a>
							</c:when>
							<c:otherwise>
								<a class=" "
									href="${pageContext.request.contextPath}/documentUploadList"
									onclick="selectSubMod(4,405)"> <span class="title">Document
										List</span>
								</a>
							</c:otherwise>
						</c:choose></li>

					<li><c:choose>
							<c:when test="${sessionScope.sessionSubModuleId==406}">

								<a class="active"
									href="${pageContext.request.contextPath}/NewsBlogList"
									onclick="selectSubMod(4,406)"> <span class="title">News List</span>
								</a>
							</c:when>
							<c:otherwise>
								<a class=" "
									href="${pageContext.request.contextPath}/NewsBlogList"
									onclick="selectSubMod(4,406)"> <span class="title">News List</span>
								</a>
							</c:otherwise>
						</c:choose></li>
						
					<li><c:choose>
							<c:when test="${sessionScope.sessionSubModuleId==411}">

								<a class="active"
									href="${pageContext.request.contextPath}/EventFormList"
									onclick="selectSubMod(4,411)"> <span class="title">Event
										  List</span>
								</a>
							</c:when>
							<c:otherwise>
								<a class=" "
									href="${pageContext.request.contextPath}/EventFormList"
									onclick="selectSubMod(4,411)"> <span class="title">Event
										  List</span>
								</a>
							</c:otherwise>
						</c:choose></li>

				</ul>
				</li>


				<c:choose>
					<c:when test="${sessionScope.sessionModuleId==5}">
						<li class="open">
					</c:when>
					<c:otherwise>
						<li class="">
					</c:otherwise>
				</c:choose>
				<a href="${pageContext.request.contextPath}/uploadOtherMedia"
					onclick="selectSubMod(5,0)"> <i class="fa fa-cloud-upload"></i> <span
					class="title">Upload Media/Files</span>
				</a>
				</li>



				<c:choose>
					<c:when test="${sessionScope.sessionModuleId==6}">
						<li class="open">
					</c:when>
					<c:otherwise>
						<li class="">
					</c:otherwise>
				</c:choose>
				<a href="${pageContext.request.contextPath}/ContactList"
					onclick="selectSubMod(6,0)"> <i class="fa fa-envelope"></i> <span
					class="title">Contact List</span>
				</a>
				</li>
						
					<c:choose>
					<c:when test="${sessionScope.sessionModuleId==7}">
						<li class="open">
					</c:when>
					<c:otherwise>
						<li class="">
					</c:otherwise>
				</c:choose>
				<a href="${pageContext.request.contextPath}/activeUserList"
					onclick="selectSubMod(7,0)"> <i class="fa fa-envelope"></i> <span
					class="title">User Registration List</span>
				</a>
				</li>
				
				 <c:choose>
					<c:when test="${sessionScope.sessionModuleId==8}">
						<li class="open">
					</c:when>
					<c:otherwise>
						<li class="">
					</c:otherwise>
				</c:choose>
				<a href="${pageContext.request.contextPath}/getAllEventList"
					onclick="selectSubMod(8,0)"> <i class="fa fa-envelope"></i> <span
					class="title">Event Applied List</span>
				</a>
				</li> 
				
					

				<li><a href="${pageContext.request.contextPath}/logout"> <i
						class="fa fa-lock"></i> <span class="title">Logout</span>
				</a></li>
			</ul>
			<div class="menustats"></div>
		</div>
		<script>
			function selectSubMod(modId, subModId) {

				$.getJSON('${setSubModId}', {
					subModId : subModId,
					modId : modId,
					ajax : 'true'
				});
				
				clearUserSessionAttribute();
			}
			function clearUserSessionAttribute() {
				 
				 

				$.getJSON('${clearUserSessionAttribute}', {
			  
					ajax : 'true',

				}, function(data) { 
					 
				
				});

			}
		</script>
		<!-- MAIN MENU - END -->
	</div>
</body>