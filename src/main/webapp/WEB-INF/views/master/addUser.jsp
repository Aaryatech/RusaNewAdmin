<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"  %><%@ taglib
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
                <!-- PAGE HEADING TAG - START --><h1 class="title">Add User</h1><!-- PAGE HEADING TAG - END -->                            </div>
			 
                                
        </div>
    </div>
    <div class="clearfix"></div>
    <!-- MAIN CONTENT AREA STARTS -->
 
	<div class="col-lg-12">
    	                                                            
    </div>
	
    <div class="col-lg-12">
        <section class="box ">
       
                <header class="panel_header">
                    <h2 class="title pull-left"><c:choose><c:when test="${isEdit==1}">Edit User</c:when><c:otherwise>Add User</c:otherwise></c:choose></h2>
                   
                    <div class="actions panel_actions pull-right">
                	      <a href="${pageContext.request.contextPath}/userList"><button type="button" class="btn btn-info"> Back</button></a>
                	       <a class="box_toggle fa fa-chevron-down"></a>
                </div>
                     
                </header>
                 <%--  <div class="content-body">    <div class="row">
            <c:if test="${sessionScope.successMsg!=null}">
            <div class="col-lg-12">
    		          <div class="alert alert-success alert-dismissible fade in">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
                
                <strong>Success : </strong> ${sessionScope.successMsg}</div>
        	          </c:if>  --%>                             </div> 
           
                   <form class="form-horizontal" id="addSupplier" action="${pageContext.request.contextPath}/insertUser" 
                   onsubmit="return confirm('Do you really want to submit the form?');" method="post">
                   
                <div class="content-body"> 
                    <div class="row">
                    <div class="col-xs-12"> 
                      <div class="form-group">
                                <label class="control-label col-sm-2" for="config_mail_protocol">Roles : <span class="text-danger">*</span> </label>
                                <div class="col-sm-10">
                                   
                                  <select name="roles" id="roles" class="form-control" placeholder="User Role">
                                  	<c:choose>
                                  		<c:when test="${editUser.roles=='SA'}">
                                  			<option value="SA">SA</option>
                                   			<option value="A" selected>A</option>
                                  		</c:when>
                                  		<c:otherwise>
                                  			<option value="SA">SA</option>
                                    		<option value="A">A</option>
                                  		</c:otherwise>
                                  	</c:choose>
                                    
                                    </select>
                                </div>
                              </div>
                              
                                      
                        <input id="userId" value="${editUser.userId}" name="userId" type="hidden"  >
                        
                        <c:choose>
    					<c:when test="${isEdit=='1'}">
      
                         <div class="form-group">
                                <label class="control-label col-sm-2" for="config_mail_protocol">User Name : <span class="text-danger">*</span> </label>
                                <div class="col-sm-10"> 
                                  <input id="userName" class="form-control"
								placeholder="User Name" value="${editUser.userName}"  style="text-align: left;" name="userName" type="text" onKeyDown="if(event.keyCode === 32)
                           return false;" readonly required>
                                </div>
                              </div>
                              
                              <br />
   						 </c:when>    
   						 
    					<c:otherwise>
                              <div class="form-group">
                               <label class="control-label col-sm-2" for="config_mail_protocol">User Name : <span class="text-danger">*</span> </label>
                               <div class="col-sm-10"> 
                               <input id="userName" class="form-control" placeholder="User Name" value="${editUser.userName}"  style="text-align: left;" name="userName" type="text" onKeyDown="if(event.keyCode === 32)
                          				 return false;" required>
                               </div>
                            </div>
        					<br />
    					</c:otherwise>
					</c:choose>
					
                        
                              <div class="form-group">
                                <label class="control-label col-sm-2" for="config_mail_protocol">First Name : <span class="text-danger">*</span> </label>
                                <div class="col-sm-10"> 
                                  <input id="firstname" class="form-control"
								placeholder="First Name" value="${editUser.firstname}"  style="text-align: left;" name="firstname" type="text" required>
                                </div>
                              </div>
                              
                              <div class="form-group">
                                <label class="control-label col-sm-2" for="config_mail_protocol">Middle Name : </label>
                                <div class="col-sm-10"> 
                                  <input id="middlename" class="form-control"
								placeholder="Middle Name" value="${editUser.middlename}"  style="text-align: left;" name="middlename" type="text" >
                                </div>
                              </div>
                              
                              <div class="form-group">
                                <label class="control-label col-sm-2" for="config_mail_protocol">Last Name : <span class="text-danger">*</span> </label>
                                <div class="col-sm-10"> 
                                  <input id="lastname" class="form-control"
								placeholder="Last Name" value="${editUser.lastname}"  style="text-align: left;" name="lastname" type="text" required>
                                </div>
                              </div>
                              
                              <div class="form-group">
                                <label class="control-label col-sm-2" for="config_mail_protocol">Email : <span class="text-danger">*</span> </label>
                                <div class="col-sm-10"> 
                                  <input id="userEmail" class="form-control"
								placeholder="Email" value="${editUser.userEmail}"  style="text-align: left;" name="userEmail" type="email" required>
                                </div>
                              </div>
                              
                               <div class="form-group">
                                <label class="control-label col-sm-2" for="config_mail_protocol">Password : <span class="text-danger">*</span> </label>
                                <div class="col-sm-10"> 
                                  <input id="userPass" class="form-control"
								placeholder="Password" value="${editUser.userPass}"  style="text-align: left;" name="userPass" type="text" required>
                                </div>
                              </div>
                              
                     		    <div class="form-group">
                                <label class="control-label col-sm-2" for="config_mail_protocol">Sort No : <span class="text-danger">*</span> </label>
                                <div class="col-sm-10"> 
                                  <input id="seqNo" class="form-control"
								placeholder="Sequence No" value="${editUser.sortNo}"  style="text-align: left;" name="seqNo" type="number" required>
                                </div>
                              </div>
                              
                                <div class="form-group">
                                <label class="control-label col-sm-2" for="config_mail_protocol">Is Active : <span class="text-danger">*</span> </label>
                                <div class="col-sm-10">
                                   
                                  <select name="isActive" id="isActive" class="form-control">
                                  	<c:choose>
                                  		<c:when test="${editUser.isActive==0}">
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
                              </div>
                              
                               
                              <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                  <button type="submit" class="btn btn-primary">Submit</button> <button type="reset" class="btn btn-default">Reset</button> 
                                  
                                </div>
                              </div> 
                            
                        </div>
                    </div> 
                </div>
                
              
                </form>
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