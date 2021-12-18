<!DOCTYPE html>
<html lang="en">
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

<title>Active User Dashboard</title>
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

@media only screen and (orientation:landscape) {
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
	<link href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">  
      <script src="https://code.jquery.com/jquery-1.10.2.js"></script>  
      <script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>  
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.css" integrity="sha512-bYPO5jmStZ9WI2602V2zaivdAnbAhtfzmxnEGh9RwtlI00I9s8ulGe4oBa5XxiC6tCITJH/QG70jswBhbLkxPw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
      <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.full.min.js"></script>
      
      
      <!-- Javascript -->  
      <script>  
         $(function() {  
            $( "#inputFromDate" ).datetimepicker({  
            	format:'Y-m-d H:i',
              // altField: "#datepicker-4",  
              // altFormat: "DD, d MM, yy"  
            });  
         });  
      </script>  
      
      <script>  
         $(function() {  
            $( "#inputToDate" ).datetimepicker({
            	format:'Y-m-d H:i',
              // altField: "#datepicker-4",  
              // altFormat: "DD, d MM, yy"  
            });  
         });  
      </script>  


</head>

<body id="page-top">
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

	<!--====== Section Start ======-->
	<section class="login min-height">
		<div id="wrapper">
			<jsp:include page="header.jsp"></jsp:include>
			<div class="container">
				<div class="clearfix">&nbsp;</div>

				<div class="panel-heading">
					<h2 class="text-center" style="color: black;">
						<b>Active User Dashboard</b>
					</h2>
					
					<div class="row  center-align"
						style="padding: 8px; text-align: center;">
					<div class="col-md-3"></div>
					<div class="col-md-1"></div>
					<div class="col-md-3 bg-primary  align-center"
							style="border-radius: 15px; color: #ffff;" >
							<span style="font-size: 15pt" > <b> Last 30 days users</b>
							</span> <br> 
							<span style="font-size: 20pt" id="monthlyCount"> 
							<c:out value="${monthlyActiveUser}" />
							</span>

					</div>
						<div class="col-md-1"></div>
					<div class="col-md-3"></div>
				</div>
				
				<br>

				<div class="row">

					&nbsp;&nbsp;<label for="formdate"><b>From Date : </b></label>&nbsp; <input
						type="text" id="inputFromDate"  placeholder="yyyy-MM-dd">&nbsp;&nbsp;
						
						&nbsp;&nbsp;<label for="todaydate"><b>To Date : </b></label>&nbsp;
					<input type="text" id="inputToDate"  placeholder="yyyy-MM-dd">&nbsp;&nbsp;

   					<button type="button" id="bth-search" class="btn btn-success btn-md"  onclick="search()">Search</button>&nbsp;&nbsp;
					<button type="button" class="btn btn-danger btn-md"
						onclick="clearFilter()">Clear</button>
				</div>

				<br> 



				<div class="container-fluid text-center ">
					<div class="row  center-align"
						style="padding: 8px; text-align: center;">


						<div class="col-md-3 bg-primary  align-center"
							style="border-radius: 15px; color: #ffff;">

							<span style="font-size: 15pt"> 
								<b>Users per day</b>
							</span> <br> <br> <span style="font-size: 20pt" id ="todayCount"> <c:out
									value="${todayActiveUser}" />
							</span>

						</div>

						<div class="col-md-1"></div>
						<div class="col-md-3 bg-primary  align-center"
							style="border-radius: 15px; color: #ffff;" >
							<span style="font-size: 15pt" > <b> Total Event Count</b>
							</span> <br> <br> 
							<span style="font-size: 20pt" id = "totalEventCount"> 
							<c:out value="${totalCount}" />
							</span>

						</div>
						<div class="col-md-1"></div>
						<div class="col-md-3 bg-primary  align-center"
							style="border-radius: 15px; color: #ffff;">
							<span style="font-size: 15pt"> <b>Average Time Spend
									One User Per Day</b>
							</span> <br>  <span style="font-size: 20pt" id = "averageCount"> <c:out
									value="${averageTimeSpendOneUserPerDay}" />
							</span>

						</div>
					</div>
				</div>

				<br>

				<div class="panel-body">

					<div class="panel-heading">
						<h2 class="text-center" style="color: black;">
							<b>Summary of Event</b>
						</h2>
					</div>

					<div class="row">
						<div class="col-md-12">
							<div class="table-responsive">
								<table id="table1" class="table info-tbl text-left"
									style='border: 1px solid #d3d3d3; width: 98% !important;'>
									<thead>
										<tr>
											<th class="text-left" style="background: #d3d3d3">Sr.No.</th>
											<th class="text-left" style="background: #d3d3d3">Event
												Count</th>
											<th class="text-left" style="background: #d3d3d3">Event
												Type</th>
										</tr>
									</thead>

									<tbody>
										<c:forEach items="${eventList}" var="list"
											varStatus="status">
											<tr class="odd gradeX">

												<td><c:out value="${status.index+1}" /></td>
												<td><c:out value="${list.count}" /></td>
												<td><c:out value="${list.event}" /></td>

											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>

	</section>
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

	<script type="text/javascript">
		$(document).ready(function() {

		});
		function search() {
debugger
			var updatedList;
			updatedList = {}
			updatedList["todaydate"] = $("#inputToDate").val();
			updatedList["fromdate"] = $("#inputFromDate").val();

			$("#btn-search").prop("disabled", false);

			$.ajax({
						type : "GET",
						//contentType: "application/json",
						url : "${pageContext.request.contextPath}/activeUserDashboardListContent",
						// success:function(result)
						data : updatedList,
						//dataType: 'json',

						/// data:{userName:inputName, userMobile:inputMobile, zlenCode:inputCode, deviceType:inputType},
						success : function(data) {
							console.log(data);

							var result = "";
							var count;
							var event;
							var monthlycount = data.monthlyCount;
							var todayCount = data.today;
							var averageCount = data.averageCount;
							

						var eventlist = data.eventList;

						
							$(eventlist)
									.each(
											function(index, ele) {

												count = ele.count;
												event = ele.event;
												result += "<tr><td>"+ (index+1) + "</td><td>"
														+ ele.count
														+ "</td><td>"
														+ ele.event
														+ "</td></tr>";
											});
							
						
							
							
							
							$('#totalEventCount').html(monthlycount);
							$('#todayCount').html(todayCount);
							$('#averageCount').html(averageCount);
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
			window.location = '/activeUserDashboard';
		}
	</script>

</body>
</html>