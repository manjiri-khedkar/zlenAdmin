<!DOCTYPE html>
<html>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
  
<head> 
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>User View Details</title>
  <meta name="description" content="">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
	
  <!-- Custom fonts for this template-->
  <link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

<style>
	
	#page-top { display:block; }
	#warning-message { 
	  position: fixed; /* Sit on top of the page content */
	  display: none; /* Hidden by default */
	  width: 100%; /* Full width (cover the whole page) */
	  height: 100%; /* Full height (cover the whole page) */
	  top: 0; 
	  left: 0;
	  right: 0;
	  bottom: 0;
	  background-color: rgba(0,0,0,0.5); /* Black background with opacity */
	  z-index: 2; /* Specify a stack order in case you're using a different order for other elements */
	  cursor: pointer; /* Add a pointer on hover */
	  font-weight:bold;
	  text-align: center;
	  color: white;
	  padding: 40px;     
	  font-size: 25px;
	}

@media only screen and (orientation:portrait){
    #warning-message { display:block; }
  /*  #page-top {
    height: 100vw;
    width: 100vh;
    -webkit-transform: rotate(90deg);
    -moz-transform: rotate(90deg);
    -o-transform: rotate(90deg);
    -ms-transform: rotate(90deg);
    transform: rotate(90deg);
  }  */

}


@media only screen and (orientation:landscape){
	#warning-message { display:none; }
  #page-top {
     -webkit-transform: rotate(0deg);
     -moz-transform: rotate(0deg);
     -o-transform: rotate(0deg);
     -ms-transform: rotate(0deg);
     transform: rotate(0deg);
  }
	
</style>
  <!-- Custom styles for this template-->
  <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">
	<script>
		function savePass(){
		    var pass = $("#pass").val();
		    var valid = pass == $("#passConfirm").val();
		    if(!valid) {
		      $("#error").show();
		      return;
		    }
		    $.post( "changePassword",
		      {newPwd: pass, pwd: $("#oldpass").val()} ,function(data){
		    	  $("#error").show().html(data);
		    	  $('#changePasswordModal').modal('hide');
		    })
		    .fail(function(data) {
		        $("#error").show().html(data);
		    });
		}
	</script>
</head>

<body id="page-top">

  <!--====== Section Start ======-->
<section class="login min-height">
<div id="wrapper">
	<jsp:include page="header.jsp"></jsp:include>

<div class="container">
	<div class="clearfix">&nbsp;</div>                        
       <form:form action="${pageContext.request.contextPath}/userViewForm/{id}" id="table1" method="post"  modelAttribute="user">
            <div class="row">
			     <div class="col-md-12">
				 <div class="panel panel-primary">
					<div class="panel-heading">
						<h2 class="text-center" style="color: black;"><b>User Details</b></h2>
					</div><br/>

					<div class="panel-body">
					  
					   <form:hidden path="id"/>
					   
					   <div class="container">
					   		 <div class="row">
							    <div class="col">
								     <form:label path="userId" class="control-label"><b>User ID :</b></form:label>&nbsp;
								     <c:out value="${user.userId}" />
							    </div>
							    <div class="col">
								   <form:label path="userPassword" class="control-label"><b>User Password :</b></form:label>&nbsp;
								     <c:out value="${user.userPassword}" />
							     </div>
							  </div>
					   			
							  <div class="row">
							    <div class="col">
								     <form:label path="userName" class="control-label"><b>User Name :</b></form:label>&nbsp;
								     <c:out value="${user.userName}" />
							    </div>
							    <div class="col">
							     	<form:label path="userMobile" class="control-label"><b>User Mobile :</b></form:label>&nbsp;
							     	<c:out value="${user.userMobile}" />
							    </div>
							  </div>
							  
							  <div class="row">
							    <div class="col">
								     <form:label path="userEmailID" class="control-label"><b>Email ID :</b></form:label>&nbsp;
								     <c:out value="${user.userEmailID}" />
							    </div>
							    <div class="col">
								      <form:label path="zlenCode" class="control-label"><b>ZlenCode :</b></form:label>&nbsp;
								      <c:out value="${user.zlenCode}" />
							    </div>
							  </div>
							  
							  <div class="row">
							    <div class="col">
								     <form:label path="longitude" class="control-label"><b>Longitude :</b></form:label>&nbsp;
								     <c:out value="${user.longitude}" />
							    </div>
							    <div class="col">
								      <form:label path="latitude" class="control-label"><b>Latitude :</b></form:label>&nbsp;
								      <c:out value="${user.latitude}" />
							    </div>
							  </div>
							  
							  <div class="row">
							    <div class="col">
								     <form:label path="deviceID" class="control-label"><b>DeviceID :</b></form:label>&nbsp;
								     <c:out value="${user.deviceID}" />
							    </div>
							    <div class="col">
								      <form:label path="deviceType" class="control-label"><b>DeviceType :</b></form:label>&nbsp;
								      <c:out value="${user.deviceType}" />
							    </div>
							  </div>
							  
							  <div class="row">
							    <div class="col">
								     <form:label path="notificationID" class="control-label"><b>NotificationID :</b></form:label>&nbsp;
								     <c:out value="${user.notificationID}" />
							    </div>
							    <div class="col">
								      <form:label path="readReceiptStatus" class="control-label"><b>Read Receipt Status :</b></form:label>&nbsp;
								      <c:out value="${user.readReceiptStatus}" />
							    </div>
							  </div>
							  
							  <div class="row">
							    <div class="col">
								    <form:label path="createdOn" class="control-label"><b>Created On :</b></form:label>&nbsp;
								     <c:out value="${user.createdOn}" />
							    </div>
							    <div class="col">
								      <form:label path="modifiedOn" class="control-label"><b>Modified On :</b></form:label>&nbsp;
								      <c:out value="${user.modifiedOn}" />
							    </div>
							  </div>
							  
							   <div class="row">
							    <div class="col">
								    <form:label path="userProfileImagePath" class="control-label"><b>User Profile Image :</b></form:label>&nbsp;
								     <img src="${user.userProfileImagePath}" style="width: 100px;"></img>
<%-- 								     <c:out value="${user.userProfileImagePath}" /> --%>
							    </div>
							    <div class="col">
								      <form:label path="QRCodePath" class="control-label"><b>QR Code :</b></form:label>&nbsp;
								      <img src="${user.QRCodePath}" style="width: 100px;"></img>
<%-- 								      <c:out value="${user.QRCodePath}" /> --%>
							    </div>
							  </div>
							  
							  <div class="row">
							    <div class="col">
								   <form:label path="lastSeenStatus" class="control-label"><b>Last Seen Status :</b></form:label>&nbsp;
								     <c:out value="${user.lastSeenStatus}" />
							    </div>
							    <div class="col">
								     <form:label path="displayName" class="control-label"><b>Display Name :</b></form:label>&nbsp;
								      <c:out value="${user.displayName}" />
							    </div>
							  </div>
							  
							  <div class="row">
							    <div class="col">
								   <form:label path="onlineStatus" class="control-label"><b>Online Status :</b></form:label>&nbsp;
								     <c:out value="${user.onlineStatus}" />
							    </div>
							    <div class="col">
								     <form:label path="statusMessage" class="control-label"><b>Status Message :</b></form:label>&nbsp;
								      <c:out value="${user.statusMessage}" />
							    </div>
							  </div>
							  
							  <div class="row">
							    <div class="col">
								   <form:label path="isActive" class="control-label"><b>Is Active :</b></form:label>&nbsp;
								     <c:out value="${user.isActive}" />
							    </div>
							    <div class="col">
								     <form:label path="isDeActivatedByAdmin" class="control-label"><b>Is DeActivatedBy Admin :</b></form:label>&nbsp;
								      <c:out value="${user.isDeActivatedByAdmin}" />
							    </div>
							  </div>
							  
							   <div class="row">
								    <div class="col">
									   <form:label path="detoxStart" class="control-label"><b>Detox Start :</b></form:label>&nbsp;
									     <c:out value="${user.detoxStart}" />
								    </div>
								    <div class="col">
									     <form:label path="detoxEnd" class="control-label"><b>Detox End :</b></form:label>&nbsp;
									      <c:out value="${user.detoxEnd}" />
								    </div>
							 	</div>
							 	
							 	<div class="row">
								    <div class="col">
									     <form:label path="isDetoxing" class="control-label"><b>Is Detoxing :</b></form:label>&nbsp;
									      <c:out value="${user.isDetoxing}" />
								    </div>
							  	</div>
							  
							  
<!-- 							  <div class="row">  -->
<!-- 							  	 <div class="col"> -->
<%-- 								   <form:label path="userPassword" class="control-label"><b>User Password :</b></form:label>&nbsp; --%>
<%-- 								     <c:out value="${user.userPassword}" /> --%>
<!-- 							     </div> -->
<!--  							    <div class="col">  -->
<%--  								  <form:label path="SignUpDeviceId" class="control-label"><b>Sign Up Device Id :</b></form:label>&nbsp; --%>
<%--  								     <c:out value="${user.SignUpDeviceId}" />  --%>
<!--  							    </div>  -->
<!--  							  </div>  -->
							  
					   </div>

						
			</div>
		</div></div></div>	
	     </form:form>
        </div>
</section>
	
<!-- Footer -->
      <footer class="sticky-footer bg-white">
        <div class="container my-auto">
          <div class="copyright text-center my-auto">
            <span>Copyright &copy;Infosane.co.in</span>
          </div>
        </div>
      </footer>
<!-- End of Footer -->

 <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>
  <jsp:include page="otherModal.jsp"></jsp:include>
  
  <!-- Bootstrap core JavaScript-->
  
  <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="${pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>

  <!-- Page level plugins -->
  <script src="${pageContext.request.contextPath}/resources/vendor/chart.js/Chart.min.js"></script>
  <!-- Page level custom scripts -->
  <script src="${pageContext.request.contextPath}/resources/js/demo/chart-area-demo.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/demo/chart-pie-demo.js"></script>

</body>
</html>