<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Edit Trending Hash Tag</title>
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

</head>

<body id="page-top">

	<div id="warning-message" style="">In Mobile, this web site works
		better in landscape view. Please rotate your mobile to experience the
		best view.</div>
	<!-- Page Wrapper -->
	<div id="wrapper">

		<jsp:include page="header.jsp"></jsp:include>

		<!--====== Section Start ======-->
		<div class="container">
			<div class="clearfix">&nbsp;</div>
			<form:form action="${pageContext.request.contextPath}/edits" modelAttribute="editTrendinghashTag" id="frm" method="post">
				<c:forEach items="${trendingHashTaglist}" var="list" >
				<div class="row">
					<div class="col-md-12">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h2 class="text-center" style="color: black;">
									<b>Edit Trending Hash Tag</b>
								</h2>
							</div>
							<br />
							<div class="panel-body">
								<form:hidden path="id" value="${list.id}" />
								<div class="row justify-content-center">
									<div class="col-md-2 text-right">
										<form:label  path="hash_tag" class="control-label">Hash Tag  :
<!--   								 <span class="text-danger">*</span>   -->
										</form:label>
									</div>
									<div class="col-md-3">
										<div class="form-group form-group-sm">
											<form:input path="hash_tag"  value="${list.hash_tag}" class="form-control"
												  autofocus="autofocus" />
<%--  											<form:errors path="hash_tag" cssClass="text-danger" />  --%>
										</div>
									</div>

									<div class="col-md-2 text-right">
										<form:label path="created_at"  class="control-label">Created Date :
<!--  								 <span class="text-danger">*</span>  -->
										</form:label>
									</div>
									<div class="col-md-3">
										<div class="form-group form-group-sm">
											<form:input path="created_at"   class="form-control"
												placeholder="yyyy-MM-dd" value="${list.created_at}" autofocus="autofocus" />
<%--  											<form:errors path="created_at" cssClass="text-danger" />  --%>
										</div>
									</div>
								</div>
								<div class="row">
									

									<div class="col-md-2 text-right">
										<form:label path="url" class="control-label">Url :
<!--  								 <span class="text-danger">*</span>  -->
										</form:label>
									</div>
									<div class="col-md-3">
										<div class="form-group form-group-sm">
											<form:input path="url" class="form-control"
												placeholder="Enter url" value="${list.url}" autofocus="autofocus" />
<%--  											<form:errors path="url" cssClass="text-danger" />  --%>
										</div>
									</div>
									
								</div>
								
								<div class="row">
									<div class="col-md-12 text-center">
										<button type="submit" class="btn-primary btn">Update</button>
										&nbsp; <a href="${pageContext.request.contextPath}/trendingHashTaglist"
											class="btn btn-outline-danger" title="Cancel">Cancel</a>
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>
				</c:forEach>
			</form:form>
		</div>
	</div>
	<!--====== Section Ends ======-->
	<!-- End of Main Content -->

	<!-- Footer -->
	<footer class="sticky-footer bg-white">
		<div class="container my-auto">
			<div class="copyright text-center my-auto">
				<span>Copyright &copy;Infosane.co.in</span>
			</div>
		</div>
	</footer>
	<!-- End of Footer -->
	<!-- End of Content Wrapper -->
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>
	<jsp:include page="otherModal.jsp"></jsp:include>

	<!-- Bootstrap core JavaScript-->

	<script
		src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script
		src="${pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

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
</body>
</html>