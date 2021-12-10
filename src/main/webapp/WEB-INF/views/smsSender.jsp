<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Notification</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!--====== Favicon Icon ======-->
<link rel="shortcut icon" href="resources/img/favicon.png"
	type="image/png">

<!-- CSS
    ============================================ -->

<!--===== Vendor CSS (Bootstrap & Icon Font) =====-->

<link rel="stylesheet" href="assets/css/plugins/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/plugins/fontawesome.min.css">
<link rel="stylesheet" href="assets/css/plugins/default.css">

<!--====== Main Style CSS ======-->
<link rel="stylesheet" href="assets/css/style.css">

<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
<!-- Custom fonts for this template-->
<link
	href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<style>
#page-top {
	display: block;
}

#warning-message {
	position: fixed; /* Sit on top of the page content */
	display: none; /* Hidden by default */
	width: 100%; /* Full width (cover the whole page) */
	height: 100%; /* Full height (cover the whole page) */
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	background-color: rgba(0, 0, 0, 0.5);
	/* Black background with opacity */
	z-index: 2;
	/* Specify a stack order in case you're using a different order for other elements */
	cursor: pointer; /* Add a pointer on hover */
	font-weight: bold;
	text-align: center;
	color: white;
	padding: 40px;
	font-size: 25px;
}

@media only screen and (orientation:portrait) {
	#warning-message {
		display: block;
	}
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

@media only screen and (orientation:landscape) {
	#warning-message {
		display: none;
	}
	#page-top {
		-webkit-transform: rotate(0deg);
		-moz-transform: rotate(0deg);
		-o-transform: rotate(0deg);
		-ms-transform: rotate(0deg);
		transform: rotate(0deg);
	}
}
</style>
<!-- Custom styles for this template-->
<link
	href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css"
	rel="stylesheet">
<script>
	function savePass() {
		var pass = $("#pass").val();
		var valid = pass == $("#passConfirm").val();
		if (!valid) {
			$("#error").show();
			return;
		}
		$.post("changePassword", {
			newPwd : pass,
			pwd : $("#oldpass").val()
		}, function(data) {
			$("#error").show().html(data);
			$('#changePasswordModal').modal('hide');
		}).fail(function(data) {
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
				<h1>Notification</h1><br>
				 
				 <p id="demo" style="text:center; color:red;p"></p><br>
				 
				<div class="row">
 					 
 					<br><br> 
					<form:form action="smsSender" method="POST">
						<b>Group Number : </b>&nbsp;&nbsp;&nbsp;&nbsp; <select
							id="inputType">
							<option value="1"  selected >ALL_USERS</option>
							<option value="2">INACTIVE_USERS</option>
							<option value="3">NEWLY_REGISTERED_USERS</option>
							<option value="4">ALL_ANDROID_USERS</option>
							<option value="5">ALL_IOS_USERS</option>
						</select>
						<br><br>
						<div class="form-group form-inline">
					<label for="msg"><b>Message : </b></label>&nbsp;&nbsp;
					<textArea  id="textAreaInput" placeholder="Text Message" path="textArea" rows = "5" cols = "40" ></textArea>
						</div>	
							<br>
							
							
							

					<button type="button" id="bth-sms" class="btn btn-success btn-md" onclick="sms()" > Send </button>
						
				</form:form>
				</div>
			</div>
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
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>


	<!-- Bootstrap core JavaScript-->
	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->


	<!-- Custom scripts for all pages-->
	<script
		src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>

	<!-- Page level plugins -->
	<script
		src="${pageContext.request.contextPath}/resources/vendor/chart.js/Chart.min.js"></script>
	<!-- Page level custom scripts -->
	<script
		src="${pageContext.request.contextPath}/resources/js/demo/chart-area-demo.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/demo/chart-pie-demo.js"></script>

	<script type="text/javascript">
		$(document).ready(function() {
		//sms();
		});
		function sms() {
			debugger
			var smssender;
			smssender = {}
			smssender["groupNo"] = $('#inputType').val();
			smssender["message"] = $('#textAreaInput').val();

			$("#btn-sms").prop("disabled", false);

			$.ajax({
						type : "POST",
						//contentType: "application/json",
						url : "${pageContext.request.contextPath}/smsSender",
						//dataType: 'json',
						data : smssender,
						cache: false,
						//async: false,
						
						 //success:function(data)
								Response : 
								{
								    "success" : "true",
								    "message" : "Successfully notified",
								    "data" : true
								},
								success:function(Response){
									debugger
 								var	value = Response;
							
					                  $('#demo').html(value);
					    				
					                  }
								

					});
			return;
		
		}
			
	</script>


</body>
</html>
