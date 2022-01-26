<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Poll List</title>
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

.switch {
  position: relative;
  display: inline-block;
  width: 60px;
  height: 34px;
}

.switch input { 
  opacity: 0;
  width: 0;
  height: 0;
}

.slider {
  position: absolute;
  cursor: pointer;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #ccc;
  -webkit-transition: .4s;
  transition: .4s;
}

.slider:before {
  position: absolute;
  content: "";
  height: 26px;
  width: 26px;
  left: 4px;
  bottom: 4px;
  background-color: white;
  -webkit-transition: .4s;
  transition: .4s;
}

input:checked + .slider {
  background-color: #2196F3;
}

input:focus + .slider {
  box-shadow: 0 0 1px #2196F3;
}

input:checked + .slider:before {
  -webkit-transform: translateX(26px);
  -ms-transform: translateX(26px);
  transform: translateX(26px);
}

/* Rounded sliders */
.slider.round {
  border-radius: 34px;
}

.slider.round:before {
  border-radius: 50%;
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

<link
	href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<!-- Javascript -->
<script>
	$(function() {
		$("#inputdate").datepicker({
			// appendText:"(yy-mm-dd)",  
			dateFormat : "yy-mm-dd"
		// altField: "#datepicker-4",  
		// altFormat: "DD, d MM, yy"  
		});
	});
</script>

</head>

<body id="page-top">


	<!--====== Section Start ======-->
	<section class="login min-height">
		<div id="wrapper">
			<jsp:include page="header.jsp"></jsp:include>

			<div class="container">
				<h1>Poll List</h1>

				<div class="row">

					<label for="zlenCode"><b>Zlen Code : </b></label>&nbsp; 
					<input type="text" id="inputCode" placeholder="Enter Zlen Code......">&nbsp;&nbsp; 
					<label for="createdAt"><b>Date : </b></label>&nbsp; 
					<input type="text" id="inputdate" placeholder="Enter Date......">&nbsp;&nbsp;
						<label for="zlenWorld"><b>Zlen World Active: </b></label>&nbsp;
					<label class="switch"><input type="checkbox" id="zlenWorld">
					<span class="slider round"></span></label> &nbsp;&nbsp;
									
						<button type="button" id="bth-search"
						class="btn btn-success btn-md" onclick="search()">Search</button>
					&nbsp;&nbsp;
					<button type="button" class="btn btn-danger btn-md"
						onclick="clearFilter()">Clear</button>
				</div>
				<br/>

				<div class="row">
					<div class="col-md-12">
						<div class="table-responsive">
							<table id="table1" class="table info-tbl text-left"
								style='border: 1px solid #d3d3d3; width: 98% !important;'>
								<thead>
									<tr>
										<th class="text-left" style="background: #d3d3d3">Sr.No.</th>
										<th class="text-left" style="background: #d3d3d3">Content</th>
										<th class="text-left" style="background: #d3d3d3">Zlen Code</th>
										<th class="text-left" style="background: #d3d3d3">Name</th>
										<th class="text-left" style="background: #d3d3d3">Date</th>
										<th class="text-left" style="background: #d3d3d3">Completed Status</th>
<!-- 										<th class="text-left" style="background: #d3d3d3">Likes</th> -->
<!-- 										<th class="text-left" style="background: #d3d3d3">Active Post</th> -->
<!-- 										<th class="text-left" style="background: #d3d3d3">Action</th> -->
									</tr>
								</thead>

								<tbody>
									<c:forEach items="${pdList}" var="list"
										varStatus="status">
										<tr class="odd gradeX">
											<td><c:out value="${status.index+1}" /></td>
											<td><c:out value="${list.content}" /></td>
											<td><a
												href="${pageContext.request.contextPath}/userViewZlen/<c:out value='${list.zlenCode}'/>"
												class="showData"> <c:out value="${list.zlenCode}" />
											</a></td>
											<td><c:out value="${list.userName}" /></td>
											<td><c:out value="${list.createdAt}" /></td>
<%-- 											<td><c:out value="${list.isCompleted}" /></td> --%>
<%-- 											<td><c:out value="${list.isActive}" /></td> --%>
<%-- 											<td><a href="#" src='${list.uploadedPath}'class="btn btn-info btn-sm img-view"> View </a>&nbsp; --%>
<%-- 												<a href="/activePost?id=${list.id}"><button class="btn btn-primary"> Post </button></a>&nbsp; --%>
<%-- 												<a href="/activeUser?id=${list.id}"><button class="btn btn-primary"> User </button></a></td> --%>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
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

	<jsp:include page="otherModal.jsp"></jsp:include>
	<!-- Bootstrap core JavaScript-->

<!-- 	<script -->
<!-- 		src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script> -->

	<!-- Core plugin JavaScript-->
	<script
		src="${pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

	<script
		src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script> -->


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

		});
		function search() {
debugger
			var pdList;
			pdList = {}
			pdList["zlenCode"] = $("#inputCode").val();
			pdList["createdAt"] = $("#inputdate").val();
			pdList["zlenWorld"] = $("#zlenWorld").val();
			
			$("#btn-search").prop("disabled", false);

			$
					.ajax({
						type : "GET",
						//contentType: "application/json",
						url : "${pageContext.request.contextPath}/pollListContents",
						// success:function(result)
						data : pdList,
						//dataType: 'json',

						/// data:{userName:inputName, userMobile:inputMobile, zlenCode:inputCode, deviceType:inputType},
						success : function(data) {

							var result = "";
							var id;
							var createdAt;
							var zlenWorld;
							var zlenCode;

							$(data)
									.each(
											function(index, ele) {

											
												createdAt = ele.createdAt;
												zlenWorld = ele.zlenWorld;
												zlenCode = ele.zlenCode;

												result += "<tr><td>"
														+ index
														+ "</td><td>"
														+ ele.content
														+ "</td><td><a href='${pageContext.request.contextPath}/userViewZlen/"+ele.zlenCode+"' class='showData'>"
														+ ele.zlenCode
														+ "</a></td>"
														+ "<td>"
														+ ele.userName
														+ " </td>"
														+ "<td>"
														+ ele.createdAt
														+ " </td></tr>";

											});

							$('#table1 tbody').html(result);
							bindFunction();
							return;
							alert(ele.success);
						}

					});
		}
	</script>

	<script type="text/javascript">
		function clearFilter() {
			window.location = '/pollList';
		}
	</script>

</body>
</html>




