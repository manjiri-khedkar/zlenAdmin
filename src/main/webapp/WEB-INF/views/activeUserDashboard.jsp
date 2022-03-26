<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Active User Dashboard</title>

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
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>

<!-- Custom styles for this template-->
<link
	href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css"
	rel="stylesheet">">
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

	
	<!--====== Section Start ======-->
	<section class="login min-height">
		<div id="wrapper">
			<jsp:include page="header.jsp"></jsp:include>
				
			<div class="container">
					
					<h2 class="text-center"> <b>Active User Dashboard</b> </h2>
					<br>
					<div class="row  center-align"
						style="padding: 8px; text-align: center;">
						<div class="col-md-3"></div>
						<div class="col-md-1"></div>
						<div class="col-md-3 bg-primary  align-center"
							style="border-radius: 15px; color: #ffff;">
							<span style="font-size: 15pt"> <b> Last 30 days users</b>
							</span> <br> <span style="font-size: 20pt" id="monthlyCount">
								<c:out value="${monthlyActiveUser}" />
							</span>

						</div>
						<div class="col-md-1"></div>
						<div class="col-md-3"></div>
					</div>
					
			</div>
			<br>
			
			<div class="container">

				<div class="row">
					&nbsp;&nbsp;<label for="formdate"><b>From Date : </b></label>&nbsp;
						<input type="text" id="inputFromDate" placeholder="yyyy-MM-dd">&nbsp;&nbsp;

						&nbsp;&nbsp;<label for="todaydate"><b>To Date : </b></label>&nbsp;
						<input type="text" id="inputToDate" placeholder="yyyy-MM-dd">&nbsp;&nbsp;
					
					<button type="button" id="bth-search"
						class="btn btn-success btn-md"
						onclick="search()">Search</button>
					&nbsp;&nbsp;
					<button type="button" class="btn btn-danger btn-md"
						onclick="clearFilter()">Clear</button>&nbsp;&nbsp;
						
						<button type="button" id="bth-download" onclick="download()"
						class="btn btn-success btn-md">Download</button>
					
			</div>
			</div>
			<br><br>
			<div class="container">
			
				<div class="row  center-align" style=" padding: 8px; text-align: center; ">
					
					<div class="col-md-3 bg-primary  align-center"
								style="border-radius: 15px; color: #ffff;">

								<span style="font-size: 15pt"> <b>Users per day</b>
								</span> <br> <br>
								<a id="bth-datetofrom" href="#" onclick="return datetofrom()" class='btn btn-sm btn-primary'>
									<span style="font-size: 20pt" id="todayCount"> <c:out value="${todayActiveUser}" /> </span>
								</a>

					</div> 
					
					<div class="col-md-1"></div>
					<div class="col-md-3 bg-primary  align-center" style="border-radius: 15px; color: #ffff;">
						<span style="font-size: 15pt"> <b> Total Event Count</b> </span> 
						<br> <br> 
						<span style="font-size: 20pt" id="totalEventCount"> <c:out value="${totalCount}" /> </span>

					</div>
					
					<div class="col-md-1"></div>
					<div class="col-md-3 bg-primary  align-center" style="border-radius: 15px; color: #ffff;">
						<span style="font-size: 15pt"> <b>Average Time Spend One User Per Day</b> </span> 
						<br> 
						<span style="font-size: 20pt" id="averageCount"> <c:out value="${averageTimeSpendOneUserPerDay}" /> </span>

					</div>
					
				</div>
				
			</div>
			
			<br><br>
			<div class="container">
			
				<h2 class="text-center"> <b>Summary of Event</b> </h2>
			
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
											<c:forEach items="${eventList}" var="list" varStatus="status">
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


	<!-- Custom scripts for all pages-->
	<script
		src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>
		
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
	
	<script type="text/javascript">
	
	$(document).ready(function() {

	});
		function datetofrom(e) {
		
		var todaydate = $("#inputToDate").val();
		var fromdate = $("#inputFromDate").val();
		
		$("#bth-datetofrom").prop("disabled", false);
		
			$.ajax({
						type : "GET",
						//contentType: "application/json",
						
						url:"${pageContext.request.contextPath}/userPerDayCountDataView?fromdate="+fromdate+"&todaydate="+todaydate,
 						//data : a,
 						
 						success : function(data) {
						
							var fromDate = data.fromDate;
							var todaydate = data.todaydate;
							
							$("#dataModal").find(".modal-body").html(data);
  	      	            	$("#dataModal").modal('show');
							//bindFunction();
						}

					});
			return false;
		}
	</script>
	
	<script type="text/javascript">
		$(document).ready(function() {

		});
		function download() {
				debugger
			var todaydate = $("#inputToDate").val();
			var fromdate = $("#inputFromDate").val();

			$('#bth-download').prop("disabled", false);
			$.ajax({
				type : "GET",
				//contentType: "application/json",
				url : "${pageContext.request.contextPath}/userPerDayCountDataViewdownload?fromdate="+fromdate+"&todaydate="+todaydate,
				//timeout: 4000,
				// success:function(result)
 				//data : datefromto,
				cache : false,
				xhr : function() {
					var xhr = new XMLHttpRequest();
					xhr.onreadystatechange = function() {
						debugger
						if (xhr.readyState == 2) {
							debugger
							if (xhr.status == 200) {
								debugger
								xhr.responseType = "blob";
							} else {
								debugger
								xhr.responseType = "text";
							}
						}
					};
					return xhr;
				},
				success: function (data) {
                	alert(data);
                	debugger
                    //Convert the Byte Data to BLOB object.
                    var blob = new Blob([data], { type: "application/vnd.ms-excel" });
 					alert(blob);
                    //Check the Browser type and download the File.
                    var isIE = false || !!document.documentMode;
                    if (isIE) {
                        window.navigator.msSaveBlob(blob, 'userPerDayData.xls');
                    } else {
                        var url1 = window.URL || window.webkitURL;
                        link = url1.createObjectURL(blob);
                        alert(link);
                        var a = $("<a />");
                        a.attr("download", 'userPerDayData.xls');
                        a.attr("href", link);
                        $("body").append(a);
                        a[0].click();
                        $(a).on("click", "button.removeButton", function()  { 
                            $(a, "body").remove();
                    });
                }
            }
        });
		}
	</script>
	
</body>
	<jsp:include page="otherModal.jsp"></jsp:include>		
</html>