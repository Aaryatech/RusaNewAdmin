<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html class=" ">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<!-- CORE CSS TEMPLATE - END -->

</head>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body class=" ">
	<!-- START TOPBAR -->
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
							<!-- PAGE HEADING TAG - START -->
							<h1 class="title">Edit Contact</h1>
							<!-- PAGE HEADING TAG - END -->
						</div>


					</div>
				</div>
				<div class="clearfix"></div>
				<!-- MAIN CONTENT AREA STARTS -->

				<div class="col-lg-12"></div>

				<div class="col-lg-12">
					<section class="box ">

						<header class="panel_header">
							<h2 class="title pull-left">
								<c:choose>
									<c:when test="${isEdit==1}">Edit User</c:when>
									<c:otherwise>Edit Contact</c:otherwise>
								</c:choose>
							</h2>

							<div class="actions panel_actions pull-right">
								<a href="${pageContext.request.contextPath}/ContactList"><button
										type="button" class="btn btn-info">Back</button></a> <a
									class="box_toggle fa fa-chevron-down"></a>
							</div>

						</header>
						<%--  <div class="content-body">    <div class="row">
            <c:if test="${sessionScope.successMsg!=null}">
            <div class="col-lg-12">
    		          <div class="alert alert-success alert-dismissible fade in">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
                
                <strong>Success : </strong> ${sessionScope.successMsg}</div>
        	          </c:if>  --%>

						<form class="form-horizontal" id="addSupplier"
							action="${pageContext.request.contextPath}/editContactUs"
							onsubmit="return confirm('Do you really want to submit the form?');"
							method="post">

							<div class="content-body">
								<div class="row">
									<div class="col-xs-12">


										<input id="id" value="${editcontact.id}" name="id"
											type="hidden">

										<c:choose>
											<c:when test="${isEdit=='1'}">

												<div class="form-group">
													<label class="control-label col-sm-2"
														for="config_mail_protocol">Contact Name : <span
														class="text-danger">*</span>
													</label>
													<div class="col-sm-10">
														<input id="contactName" class="form-control"
															placeholder="Contact Name"
															value="${editcontact.contactName}"
															style="text-align: left;" name="name" type="text"
															readonly required>
													</div>
												</div>


												<div class="form-group">
													<label class="control-label col-sm-2"
														for="config_mail_protocol">Mobile No. : <span
														class="text-danger">*</span>
													</label>
													<div class="col-sm-10">
														<input id="firstname" class="form-control"
															placeholder="First Name" value="${editcontact.mobileNo}"
															style="text-align: left;" name="firstname" type="text"
															readonly required>
													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-2"
														for="config_mail_protocol">Message : <span
														class="text-danger">*</span>
													</label>
													<div class="col-sm-10">
														<input id="middlename" class="form-control"
															placeholder="Middle Name" value="${editcontact.message}"
															style="text-align: left;" name="message" type="text"
															readonly required>
													</div>
												</div>


												<div class="form-group">
													<label class="control-label col-sm-2"
														for="config_mail_protocol">Email : <span
														class="text-danger">*</span>
													</label>
													<div class="col-sm-10">
														<input id="userEmail" class="form-control"
															placeholder="Email" value="${editcontact.emailId}"
															style="text-align: left;" name="userEmail" type="email"
															readonly required>
													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-2"
														for="remark">Remark : <span
														class="text-danger">*</span>
													</label>
													<div class="col-sm-10">
														<input id="remark" class="form-control"
															placeholder="remark" value="${editcontact.remark}"
															style="text-align: left;" name="remark" type="text"
															required>
													</div>
												</div>

												<%--   <div class="form-group">
                                <label class="control-label col-sm-2" for="config_mail_protocol">Is Active : <span class="text-danger">*</span> </label>
                                <div class="col-sm-10">
                                   
                                  <select name="isActive" id="isActive" class="form-control">
                                  	<c:choose>
                                  		<c:when test="${editcontact.isActive==0}">
                                  			<option value="1" >YES</option>
                                   			<option value="0" selected>NO</option>
                                  		</c:when>
                                  		<c:otherwise>
                                  			<option value="1" >YES</option>
                                    		<option value="0">NO</option>
                                  		</c:otherwise>
                                  	</c:choose>
                                    
                                    </select>
                                </div>
                              </div> --%>
											</c:when>

										</c:choose>

										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<button type="submit" class="btn btn-primary">Submit</button>
												<button type="reset" class="btn btn-default">Reset</button>

											</div>
										</div>

									</div>
								</div>
							</div>


						</form>
					</section>
				</div>
			</section>
		</section>

	</div>



	<script type="text/javascript">
		function RestrictSpace() {
			if (event.keyCode == 32) {
				alert("Space not allowed");
				return false;
			}
		}
	</script>

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