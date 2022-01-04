
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    

  <!--====== Section Start ======-->
<section class="login min-height">
<div id="wrapper">


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
								     <c:out value="${user.userProfileImagePath}" />
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
	
