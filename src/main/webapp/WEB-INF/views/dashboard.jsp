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
<script
	src="https://blacklabel.github.io/custom_events/js/customEvents.js"></script>
<!-- 	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script> -->
<!-- 	<script src="components/highstock/highstock.js;"></script> -->

<!-- Custom styles for this template-->
<link
	href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css"
	rel="stylesheet">
<script>
		$(document).ready(function (){
			//loadStateData();
		});

		$(function() {
			debugger
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
													text : 'Daily Downloads'
												},

												xAxis : {

													categories : listt,
													crosshair : true
												},
												yAxis : {
													min : 0,
													max : null,
													title : {
														text : '<b>Users Count</b>'
													}
												},
												tooltip : {
													headerFormat : '<span style="font-size:10px">{point.key}</span><table>',
													pointFormat : '<tr><td style="color:{series.color};padding:1">{series.name}: </td>'
															+ '<td style="padding:0"><b>{point.y}</b></td></tr>',
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
																	//alert('Count: ' + this.category);
																	location.replace("${pageContext.request.contextPath}/userDetailsList/1?createdOn="+this.category)
																}
															}
														}
													},
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
									   	  location.replace("${pageContext.request.contextPath}/userDetailsList/1?createdOn="+this.textContent)
									    }
									   }
									 }); //chart end here
			  },
			  });
				     
		 });
		
		
		//2nd graph
		
		 $(function() {
				var dates, gdata,types;
				$.ajax( {
				      type : "Get", 
				      url : "${pageContext.request.contextPath}/dashboard/stories-bar-chart", 
				      contentType : "application/json", 
				      dataType : 'json', 
				      success : function (data) {
				    	 
				      dates = data.dates;
				      gdata = data.count;
				      types = data.types;

				      console.log("dates", dates);
			    	  console.log("graphdata", gdata);
			    	  var chart = Highcharts
						.chart('container1', {
		        chart: {
		            type: 'column'
		        }, 
		        
		        title: {
		            text: 'Posts Data'
		        }, 

		        noData: {
		            text: 'Loading...'
		        },

		        
 			  xAxis: {
                  categories: dates,        
                  
                crosshair: true
                
                
                },
                
    		  yAxis: {
			    min: 0,
			    max:null,
			     title: {
			      text: 'User Count'
			    }
              },
		        
		        tooltip: {
		            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
		            pointFormat: '<tr><td style="color:{series.color};padding:1">{series.name}: </td>' +
		            '<td style="padding:0"><b>{point.y}</b></td></tr>',
		            footerFormat: '</table>',
		            shared: true,
		            useHTML: true
		        },
		        plotOptions: {
		            column: {
		                pointPadding: 0.3,
		                borderWidth: 2
		            },
		            
		            series : {
						cursor : 'pointer',
						point : {
							events : {
								click : function() {
									// alert('Count: ' + this.y);
									var type =this.colorIndex;
									var typeValue = types[type];
									location.replace("${pageContext.request.contextPath}/userStoriesList?uploadedDateTime="+ this.category +"&mimeType="+ typeValue);
								}
							}
						}
					},
					
				
		            
		        },
		        
		        
		        
		        legend: {
                  reversed: true
			    },
		        series: gdata,
		        
// 		        series : [ {
// 					name : "dates",
// 					//data : [12,14,16,23,23,24]
// 					data : dates
// 				} ]
		     });
			    	 
			    	  chart.xAxis[0].labelGroup.element.childNodes.forEach(function(label) {
							label.style.cursor = "pointer";
							label.onclick = function() {
								//alert('You clicked on '+this.textContent);
								{
									console.log("data===="+this.textContent);
								location.replace("${pageContext.request.contextPath}/userStoriesList?uploadedDateTime="+ this.textContent)
								}
							}
						});	  
		 },
	});
});
		
		
		 //3rd chart
			$(function(){
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
														text : 'Daily Active Users'
													},

													xAxis : {

														categories : datescount,
														crosshair : true
													},
													yAxis : {
														min : 0,
														max : null,
														title : {
															text : '<b>LastSeen Count</b>'
														}
													},
													tooltip : {
														headerFormat : '<span style="font-size:10px">{point.key}</span><table>',
														pointFormat : '<tr><td style="color:{series.color};padding:1">{series.name}: </td>'
																+ '<td style="padding:0"><b>{point.y}</b></td></tr>',
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

	<!-- Page Wrapper -->
	<div id="wrapper">
		<jsp:include page="header.jsp"></jsp:include>
		<div class="container-fluid text-center ">
			<div class="row  center-align"
				style="padding: 8px; text-align: center;">
				

				<div class="col-md-3 bg-primary  align-center" style="border-radius: 15px; color: #ffff;">

					<span style="font-size: 20pt"> <b>Total Downloads</b> </span>
					
					<br> <br>
					<span style="font-size: 24pt"> <c:out value="${totalRegistrationCount}" /> </span>

				</div>

				<div class="col-md-1"></div>



				<div class="col-md-3 bg-primary  align-center"
					style="border-radius: 15px; color: #ffff;">
					<span style="font-size: 20pt">  <b>Last 24 Hrs Downloads</b>  </span> 
					<br> <br>
					<span style="font-size: 24pt"> <c:out value="${last24HoursCount}" /> </span>

				</div>
				<div class="col-md-1"></div>
				<div class="col-md-3 bg-primary  align-center"
					style="border-radius: 15px; color: #ffff;">
					<span style="font-size: 20pt">  <b>Today's Active Users</b>  </span> 
					<br> <br>
					<a id="bth-datetofrom" href="#" onclick="return datetofrom()" class='btn btn-sm btn-primary'> 
					<span style="font-size: 24pt"> <c:out value="${todaysActiveUser}" /> </span> </a>
					<br>
					<button type="button" id="bth-download" onclick="download()"
						class="btn btn-success btn-md">Download</button>
					<br>

				</div>
				
			</div>

		<br><br>
		
		<div class="row  center-align"
				style="padding: 8px; text-align: center;">
				

				<div class="col-md-3 bg-primary  align-center"
					style="border-radius: 15px; color: #ffff;">
					<span style="font-size: 20pt">  <b>Age Summary</b>  </span> 
					<br> <br>
					<span style="font-size: 20pt">  <b>0-19</b>  </span>&nbsp;&nbsp;&nbsp;
					<span style="font-size: 24pt"> <c:out value="${valuesage}" /> </span>
					<br>
					<span style="font-size: 20pt">  <b>20-25</b>  </span>&nbsp;&nbsp;&nbsp;
					<span style="font-size: 24pt"> <c:out value="${valuesage1}" /> </span>
					<br>
					<span style="font-size: 20pt">  <b>26-45</b>  </span>&nbsp;&nbsp;&nbsp;
					<span style="font-size: 24pt"> <c:out value="${valuesage2}" /> </span>
					<br>
					<span style="font-size: 20pt">  <b>NA</b>  </span>&nbsp;&nbsp;&nbsp;
					<span style="font-size: 24pt"> <c:out value="${valuesage3}" /> </span>
				</div>
				
								<div class="col-md-1"></div>
					
				<div class="col-md-3 bg-primary  align-center"
					style="border-radius: 15px; color: #ffff;">
					<span style="font-size: 20pt">  <b>Gender Summary</b>  </span>
					<br>
					<c:forEach var="curGender" items="${genderCounts}">
						<span style="font-size: 20pt">  <b><c:out  value="${curGender.gender}" /></b>  </span>&nbsp;&nbsp;&nbsp;
					<span style="font-size: 24pt"> <c:out  value="${curGender.count}" /> </span>
					<br>
					</c:forEach> 
				</div>
				
				<div class="col-md-1"></div>
				
				<div class="col-md-3 bg-primary  align-center"
					style="border-radius: 15px; color: #ffff;">
					<span style="font-size: 20pt">  <b>Friends Summary</b>  </span> 
					<br> <br>
					
					<a href="/userDetailsList/1?friendNumber=${friendNumber0}&friendNumber1=${friendNumbers0}" class="text" Style = "color:white;"><span style="font-size: 20pt">  <b>0</b>  </span>&nbsp;&nbsp;&nbsp;
					<span style="font-size: 24pt"> <c:out value="${valuesfriendNumber0}" />  </span> </a>&nbsp;
					<br>
					<a href="/userDetailsList/1?friendNumber=${friendNumber}&friendNumber1=${friendNumber1}" class="text" Style = "color:white;"> <span style="font-size: 20pt">  <b>1-3</b>  </span>&nbsp;&nbsp;&nbsp;
					<span style="font-size: 24pt"> <c:out value="${valuesfriendNumber}" />  </span> </a>&nbsp;
					<br>
					<a href="/userDetailsList/1?friendNumber=${friendNumber2}&friendNumber1=${friendNumber3}" class="text" Style = "color:white;"> <span style="font-size: 20pt">  <b>4-5</b>  </span>&nbsp;&nbsp;&nbsp;
					<span style="font-size: 24pt"> <c:out value="${valuesfriendNumber1}" /> </span> </a>&nbsp;
					<br>
					<a href="/userDetailsList/1?friendNumber=${friendNumber4}&friendNumber1=${friendNumber5}" class="text" Style = "color:white;"> <span style="font-size: 20pt">  <b>6-10</b>  </span>&nbsp;&nbsp;&nbsp;
					<span style="font-size: 24pt"> <c:out value="${valuesfriendNumber2}" />  </span> </a>&nbsp;
					<br>
					<a href="/userDetailsList/1?friendNumber=${friendNumber6}&friendNumber1=${friendNumber7}" class="text" Style = "color:white;"> <span style="font-size: 20pt">  <b>11 & Above</b>  </span>&nbsp;&nbsp;&nbsp;
					<span style="font-size: 24pt"> <c:out value="${valuesfriendNumber3}" /> </span> </a>&nbsp; 
					<br>
<!-- 					<span style="font-size: 20pt">  <b>Above 101</b>  </span>&nbsp;&nbsp;&nbsp; -->
<%-- 					<span style="font-size: 24pt"> <c:out value="${valuesfriendNumber4}" /> </span> --%>
				</div>
		</div>
		
		<br><br>
		
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
	
	<script type="text/javascript">
	
	$(document).ready(function() {

	});
		function datetofrom(e) {
		debugger
		$("#bth-datetofrom").prop("disabled", false);
		
			$.ajax({
						type : "GET",
						//contentType: "application/json",
						
						url:"${pageContext.request.contextPath}/todayUserCountsData",
 						//data : a,
 						
 						success : function(data) {
							
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
			 
			$('#btn-download').prop("disabled", false);
			 $.ajax({
				 
				 type : "GET",
	               url: '${pageContext.request.contextPath}/todayUserCountsDataDownload',
	               
	                cache: false,
	                xhr: function () {
	                    var xhr = new XMLHttpRequest();
	                    xhr.onreadystatechange = function () {
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
	                        window.navigator.msSaveBlob(blob, 'todayUserCountsData.xls');
	                    } else {
	                        var url1 = window.URL || window.webkitURL;
	                        link = url1.createObjectURL(blob);
	                        alert(link);
	                        var a = $("<a />");
	                        a.attr("download", 'todayUserCountsData.xls');
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
</html>