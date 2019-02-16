<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class='page-topbar '>
	<div class='logo-area'></div>
	<div class='quick-area'>
		<div class='pull-left'>
			<ul class="info-menu left-links list-inline list-unstyled">
				<li class="sidebar-toggle-wrap"><a href="#"
					data-toggle="sidebar" class="sidebar_toggle"> <i
						class="fa fa-bars"></i>
				</a></li>



			</ul>
		</div>
		<div class='pull-right'>
			<ul class="info-menu right-links list-inline list-unstyled">
				<li class="profile"><a href="#" data-toggle="dropdown"
					class="toggle"> <c:choose>
							<c:when test="${sessionScope.UserDetail.fileName==null}">
								<img
									src="${pageContext.request.contextPath}/resources/assets/images/avatar.png"
									alt="user-image" class="img-circle img-inline">
								<span>${sessionScope.UserDetail.firstname}
									${sessionScope.UserDetail.lastname}<i class="fa fa-angle-down"></i>
								</span>
							</c:when>
							<c:otherwise>
								<img src="${imageProfileUrl}${sessionScope.UserDetail.fileName}"
									alt="user-image" class="img-circle img-inline">
								<span>${sessionScope.UserDetail.firstname}
									${sessionScope.UserDetail.lastname}<i class="fa fa-angle-down"></i>
								</span>
							</c:otherwise>
						</c:choose>


				</a>
					<ul class="dropdown-menu profile animated fadeIn">

						<li><a href="#profile"> <i class="fa fa-user"></i>
								Profile
						</a></li>

						<li class="last"><a
							href="${pageContext.request.contextPath}/logout"> <i
								class="fa fa-lock"></i> Logout
						</a></li>
					</ul></li>

			</ul>
		</div>
	</div>

</div>