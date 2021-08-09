
<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">


<title>Infosane - Dashboard</title>
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
<script type='text/javascript'
	src="${pageContext.request.contextPath}/resources/js/dashboard/dashboardchart.js"></script>

<!-- Custom styles for this template-->
<link
	href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css"
	rel="stylesheet">
<script>
	$(document).ready(function() {
		//loadStateData();
	});

	$(function() {
		var name, date, listt, datecounter;
		$.ajax({
					type : "Get",
					url : "${pageContext.request.contextPath}/dashboard/bar-chart",
					contentType : "application/json",
					dataType : 'json',
					success : function(data) {

						listt = data.listt;
						datecounter = data.datecounter;

						console.log("success", data.name);
						console.log("listt", listt);
						console.log("datecounter", datecounter);

						var chart = Highcharts
								.chart(
										'container',
										{
											chart : {
												type : 'column'
											},
											title : {
												text : 'User-Information'
											},

											xAxis : {

												categories : listt,
												crosshair : true
											},
											yAxis : {
												min : 0,
												max : 100,
												title : {
													text : '<b>Users Count</b>'
												}
											},
											tooltip : {
												headerFormat : '<span style="font-size:10px">{point.key}</span><table>',
												pointFormat : '<tr><td style="color:{series.color};padding:1">{series.name}: </td>'
														+ '<td style="padding:0"><b>{point.y}add</b></td></tr>',
												footerFormat : '</table>',
												shared : true,
												useHTML : true
											},
											plotOptions : {
												column : {
													pointPadding : 0.3,
													borderWidth : 2
												}
											},

											series : [ {
												name : "Dates",
												//data : [12,14,16,23,23,24]
												data : datecounter

											} ]

										});
						//start
						
						 chart.xAxis[0].labelGroup.element.childNodes.forEach(function(label)
								 {
								 	label.style.cursor = "pointer";
								    label.onclick = function(){
								   	//alert('You clicked on '+this.textContent);
								    {
								   	  location.replace("http://localhost:8080/usersList?createdOn="+this.textContent)
								    }
								   }
								 }); //chart end here
		  },
		  });
			     
	 });
	
	


	$(function() {
		var dates, gdata;
		$
				.ajax({
					type : "Get",
					url : "${pageContext.request.contextPath}/dashboard/stories-bar-chart",
					contentType : "application/json",
					dataType : 'json',
					success : function(data) {

						dates = data.dates;
						gdata = data.count;

						console.log("dates", dates);
						console.log("graphdata", gdata);

						var chart = Highcharts
								.chart(
										'container1',
										{
											chart : {
												type : 'column'
											},

											title : {
												text : 'Stories-Information'
											},

											noData : {
												text : 'Loading...'
											},

											xAxis : {
												categories : dates,
											// crosshair: true
											},

											yAxis : {
												min : 0,
												max : 100,
												title : {
													text : 'User Count'
												}
											},

											tooltip : {
												headerFormat : '<span style="font-size:10px">{point.key}</span><table>',
												pointFormat : '<tr><td style="color:{series.color};padding:1">{series.name}: </td>'
														+ '<td style="padding:0"><b>{point.y}add</b></td></tr>',
												footerFormat : '</table>',
												shared : true,
												useHTML : true
											},
											plotOptions : {
												column : {
													pointPadding : 0.3,
													borderWidth : 2
												},
												series : {
													cursor : 'pointer',
													point : {
														events : {
															click : function() {
																// alert('Count: ' + this.y);
																location
																.replace("${pageContext.request.contextPath}/stories?uploadedDateTime="
																		+ this.textContent)
															}
														}
													}
												}
												
											},
											legend : {
												reversed : true
											},
											series : gdata,
										}); // ending the chart here
						chart.xAxis[0].labelGroup.element.childNodes
								.forEach(function(label) {
									label.style.cursor = "pointer";
									label.onclick = function() {
										//alert('You clicked on '+this.textContent);

										location
												.replace("${pageContext.request.contextPath}/stories?uploadedDateTime="
														+ this.textContent)

									}
								});
					},
				});//ajax end here
	});
	

    //3rd chart
		$(function() 
			var datescount, count;
			$.ajax({
						type : "Get",
						url : "${pageContext.request.contextPath}/dashboard/lastseen",
						contentType : "application/json",
						dataType : 'json',
						success : function(data) {

							count = data.count;
							datescount = data.datescount;

							console.log("success", data.name);
							console.log("newcount", count);
							console.log("dates++", datescount);

							var chart = Highcharts
									.chart(
											'container2',
											{
												chart : {
													type : 'column'
												},
												title : {
													text : 'User-LastSeen'
												},

												xAxis : {

													categories : datescount,
													crosshair : true
												},
												yAxis : {
													min : 0,
													max : 100,
													title : {
														text : '<b>LastSeen Count</b>'
													}
												},
												tooltip : {
													headerFormat : '<span style="font-size:10px">{point.key}</span><table>',
													pointFormat : '<tr><td style="color:{series.color};padding:1">{series.name}: </td>'
															+ '<td style="padding:0"><b>{point.y}add</b></td></tr>',
													footerFormat : '</table>',
													shared : true,
													useHTML : true
												},
												plotOptions : {
													column : {
														pointPadding : 0.3,
														borderWidth : 2
													}

												},

												series : [ {
													name : "Dates",
													//data : [12,14,16,23,23,24]
													data : count
												} ]
											});
						},
					});
	        	});




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

	<!-- Page Wrapper -->
	<div id="wrapper">
		<jsp:include page="header.jsp"></jsp:include>
		<div class="container-fluid text-center ">
			<br>

			<h5>
				<b>Register Ratio Count</b>
			</h5>
			<div class="row  center-align"
				style="padding: 8px; text-align: center;">




				<div class="col-md-5 bg-primary  align-center"
					style="border-radius: 15px; color: #ffff;">

					<b>Total Count</b> <br> <br>
					<c:out value="${totalRegistrationCount}" />

				</div>

				<div class="col-md-2"></div>



				<div class="col-md-5 bg-primary  align-center"
					style="border-radius: 15px; color: #ffff;">
					<b>Last 24 Hours Count</b> <br> <br>
					<c:out value="${last24HoursCount}" />

				</div>

			</div>
			<br> <br>
			<div class="row">
				<div class="col-md-4" id="container" style=""></div>

				<div class="col-md-4" id="container1" style=""></div>

				<div class="col-md-4" id="container2" style=""></div>
			</div>

		</div>
	</div>
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
	<script src="https://code.highcharts.com/highcharts.js"></script>
	<script src="https://code.highcharts.com/modules/exporting.js"></script>
</body>
</html>