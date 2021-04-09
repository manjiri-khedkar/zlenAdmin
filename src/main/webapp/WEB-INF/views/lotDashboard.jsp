<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Infosane - Dashboard</title>
	<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
  <!-- Custom fonts for this template-->
  <link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">
	<script>
		$(document).ready(function (){
			loadLotData($("#id_selectLot").val());
		});
		function loadLotData(lotId){
			$.ajax({
				url : "lotData",
				type : "GET",
				data : {lot_id: lotId},
				async: false,
				success : function (response) {
					loadCharts(response);
				},
				error : function (error) {
					
				}
			});
		}
		
		function loadCharts(data){
			
			loadYearWiseRates(data["yearWiseRates"]);
			loadYearWiseAmount(data["yearWiseLot"]);
			var partyList = data["partyList"];
			var partyOptions="";
			$.each(partyList,function (idx,value){
				partyOptions+="<option value="+value.id+">"+ value.name+"</option>";
			});
			$('#id_selectParty').html(partyOptions);
			$('#id_maxRate').html(data["maxRate"]);
			$('#id_minRate').html(data["minRate"]);
			$('#id_avgRate').html(data["avgRate"]);
			$('#id_lotCount').html(data["lotCount"]);
			
		}
		function loadYearWiseRates(yearWisedata){
			//var yearWisedata = JSON.parse(data);
			var labels = yearWisedata.map(function(e) {
				   return e.year +" ("+ e.round +")" ;
				});
			var avg_data = yearWisedata.map(function(e) {
				   return e.avg_rate;
			});
			var min_data = yearWisedata.map(function(e) {
				   return e.min_rate;
			});
			var max_data = yearWisedata.map(function(e) {
				   return e.max_rate;
			});
			$("#div_myAreaChart").html('<canvas id="myAreaChart"></canvas>');
			var ctx = $("#myAreaChart");
			
			var myLineChart = new Chart(ctx, {
			  type: 'line',
			  
			  data: {
			    labels: labels,
			    datasets: [{
			      label: "Max Rate",
			      lineTension: 0.3,
			      backgroundColor: "rgba(78, 115, 223, 0.05)",
			      borderColor: "rgba(78, 115, 223, 1)",
			      pointRadius: 3,
			      pointBackgroundColor: "rgba(78, 115, 223, 1)",
			      pointBorderColor: "rgba(78, 115, 223, 1)",
			      pointHoverRadius: 3,
			      pointHoverBackgroundColor: "rgba(78, 115, 223, 1)",
			      pointHoverBorderColor: "rgba(78, 115, 223, 1)",
			      pointHitRadius: 10,
			      pointBorderWidth: 2,
			      data: max_data,
			    },{
				      label: "Avg Rate",
				      lineTension: 0.3,
				      backgroundColor: "rgba(178, 115, 223, 0.05)",
				      borderColor: "rgba(178, 115, 223, 1)",
				      pointRadius: 3,
				      pointBackgroundColor: "rgba(178, 115, 223, 1)",
				      pointBorderColor: "rgba(178, 115, 223, 1)",
				      pointHoverRadius: 3,
				      pointHoverBackgroundColor: "rgba(178, 115, 223, 1)",
				      pointHoverBorderColor: "rgba(178, 115, 223, 1)",
				      pointHitRadius: 10,
				      pointBorderWidth: 2,
				      data: avg_data,
				    },{
					      label: "Min Rate",
					      lineTension: 0.3,
					      backgroundColor: "rgba(78, 115, 78, 0.05)",
					      borderColor: "rgba(78, 115, 78, 1)",
					      pointRadius: 3,
					      pointBackgroundColor: "rgba(78, 115, 78, 1)",
					      pointBorderColor: "rgba(78, 115, 78, 1)",
					      pointHoverRadius: 3,
					      pointHoverBackgroundColor: "rgba(78, 115, 78, 1)",
					      pointHoverBorderColor: "rgba(78, 115, 78, 1)",
					      pointHitRadius: 10,
					      pointBorderWidth: 2,
					      data: min_data,
					    }],
			  },
			  options: {
			    maintainAspectRatio: false,
			    layout: {
			      padding: {
			        left: 10,
			        right: 25,
			        top: 25,
			        bottom: 0
			      }
			    },
			    scales: {
			      xAxes: [{
			        time: {
			          unit: 'date'
			        },
			        gridLines: {
			          display: false,
			          drawBorder: false
			        },
			        ticks: {
			          maxTicksLimit: 15
			        }
			      }],
			      yAxes: [{
			        ticks: {
			          maxTicksLimit: 5,
			          padding: 10,
			          // Include a dollar sign in the ticks
			          callback: function(value, index, values) {
			            return 'Rs' + number_format(value);
			          }
			        },
			        gridLines: {
			          color: "rgb(234, 236, 244)",
			          zeroLineColor: "rgb(234, 236, 244)",
			          drawBorder: false,
			          borderDash: [2],
			          zeroLineBorderDash: [2]
			        }
			      }],
			    },
			    legend: {
			      display: true
			    },
			    tooltips: {
			      backgroundColor: "rgb(255,255,255)",
			      bodyFontColor: "#858796",
			      titleMarginBottom: 10,
			      titleFontColor: '#6e707e',
			      titleFontSize: 14,
			      borderColor: '#dddfeb',
			      borderWidth: 1,
			      xPadding: 15,
			      yPadding: 15,
			      displayColors: false,
			      intersect: false,
			      mode: 'index',
			      caretPadding: 10,
			      callbacks: {
			        label: function(tooltipItem, chart) {
			          var datasetLabel = chart.datasets[tooltipItem.datasetIndex].label || '';
			          return datasetLabel + ': Rs' + number_format(tooltipItem.yLabel);
			        }
			      }
			    }
			  }
			});
		}
		function loadYearWiseAmount(yearWisedata){
			var labels = yearWisedata.map(function(e) {
				 return e.year +" ("+ e.round +")" ;
				});
			var amount = yearWisedata.map(function(e) {
				   return e.avg_rate;
			});
			$("#div_yearWiseAmount").html('<canvas id="yearWiseAmount"></canvas>');

			
			var ctx = document.getElementById("yearWiseAmount");
			var myLineChart = new Chart(ctx, {
			  type: 'bar',
			  data: {
			    labels: labels,
			    datasets: [{
			      label: "Lot Count",
			      lineTension: 0.3,
			      backgroundColor: "rgba(178, 115, 223)",
			      borderColor: "rgba(178, 115, 223, 1)",
			      
			      data: amount,
			    }],
			  },
			  options: {
			    maintainAspectRatio: false,
			    layout: {
			      padding: {
			        left: 10,
			        right: 25,
			        top: 25,
			        bottom: 0
			      }
			    },
			    scales: {
			      xAxes: [{
			        time: {
			          unit: 'date'
			        },
			        gridLines: {
			          display: false,
			          drawBorder: false
			        },
			        ticks: {
			          maxTicksLimit: 15
			        }
			      }],
			      yAxes: [{
			        ticks: {
			          maxTicksLimit: 5,
			          padding: 10,
			          // Include a dollar sign in the ticks
			          callback: function(value, index, values) {
			            return '' + value;
			          }
			        },
			        gridLines: {
			          color: "rgb(234, 236, 244)",
			          zeroLineColor: "rgb(234, 236, 244)",
			          drawBorder: false,
			          borderDash: [2],
			          zeroLineBorderDash: [2]
			        }
			      }],
			    },
			    legend: {
			      display: true
			    },
			    tooltips: {
			      backgroundColor: "rgb(255,255,255)",
			      bodyFontColor: "#858796",
			      titleMarginBottom: 10,
			      titleFontColor: '#6e707e',
			      titleFontSize: 14,
			      borderColor: '#dddfeb',
			      borderWidth: 1,
			      xPadding: 15,
			      yPadding: 15,
			      displayColors: false,
			      intersect: false,
			      mode: 'index',
			      caretPadding: 10,
			      callbacks: {
			        label: function(tooltipItem, chart) {
			          var datasetLabel = chart.datasets[tooltipItem.datasetIndex].label || '';
			          return datasetLabel + ': ' +tooltipItem.yLabel;
			        }
			      }
			    }
			  }
			});
		}
		function lotReport(yearCount){
			var lot_name = $("#id_selectLot").val();
			$.ajax({
				url : "lotReportAllowed",
				type : "GET",
				data : {lot_id: lot_name},
				async: false,
				success : function (response) {
					if (response==true){
						var state = $("#id_state").val();
						
						var link = document.createElement("a");
					    link.download = name;
					    link.href = "lotReportForYear?chk="+lot_name +"&state="+state+"&yearCount="+yearCount;
					    link.click();
					}else{
						$("#lotReportUtilization").modal('show');
					}
				},
				error : function (error) {
					
				}
			});
			
		}
	</script>
</head>

<body id="page-top">

  <!-- Page Wrapper -->
  <div id="wrapper">

   <jsp:include page="header.jsp"></jsp:include>

        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <div class="d-sm-flex align-items-center justify-content-between mb-4">
           <c:if test="${state==1}">
          		<h1 class="h3 mb-0 text-gray-800">MP Tender</h1>
          	</c:if>
           <c:if test="${state==2}">
          		<h1 class="h3 mb-0 text-gray-800">CG Tender</h1>
          	</c:if>
            <select name="tender" id="id_selectLot" onchange="loadLotData(this.value)">
		        <c:forEach items="${lotList}" var="lot">
		            <option value="${lot.id}">${lot.lot_name}(${lot.lot_given_name})</option>
		        </c:forEach>
  			</select>
  			
    <input type="hidden" name="state" value=${state} id="id_state"/>
            
            <!-- <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i class="fas fa-download fa-sm text-white-50"></i> Generate Report</a> -->
          </div>

          <!-- Content Row -->
          <div class="row">

            <!-- Earnings (Monthly) Card Example -->
            <div class="col-xl-3 col-md-6 mb-4">
              <div class="card border-left-primary shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">Minimum Rate</div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800" id="id_minRate"></div>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-rupee fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <!-- Earnings (Monthly) Card Example -->
            <div class="col-xl-3 col-md-6 mb-4">
              <div class="card border-left-success shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-success text-uppercase mb-1">Max Rate</div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800" id="id_maxRate"> </div>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-rupee fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <!-- Earnings (Monthly) Card Example -->
            <div class="col-xl-3 col-md-6 mb-4">
              <div class="card border-left-info shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-info text-uppercase mb-1">Average Rate</div>
                      <div class="row no-gutters align-items-center">
                        <div class="col-auto">
                          <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800" id="id_avgRate" ></div>
                        </div>
                        <!-- <div class="col">
                          <div class="progress progress-sm mr-2">
                            <div class="progress-bar bg-info" role="progressbar" style="width: 50%" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
                          </div>
                        </div> -->
                      </div>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-rupee fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <!-- Pending Requests Card Example -->
            <div class="col-xl-3 col-md-6 mb-4">
              <div class="card border-left-warning shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">Lots Alloted</div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800" id="id_lotCount"></div>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-comments fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <!-- Content Row -->
			
		<div class="row">
			<div class="col-xl-3 col-md-6 mb-4">
				<a href="#" class="btn btn-primary" onclick="lotReport(1)">Download Last Year Lot Report</a>
			</div>
			<div class="col-xl-3 col-md-6 mb-4">
				<a href="#" class="btn btn-primary" onclick="lotReport(5)">Download Previous Year Comparison</a>
			</div>
			
		</div>
          <div class="row">

            <!-- Area Chart -->
            <div class="col-xl-6 col-lg-6">
              <div class="card shadow mb-4">
                <!-- Card Header - Dropdown -->
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                  <h6 class="m-0 font-weight-bold text-primary">Year Wise Rates </h6>
                  <div class="dropdown no-arrow">
                    <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                      <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in" aria-labelledby="dropdownMenuLink">
                      
                    </div>
                  </div>
                </div>
                <!-- Card Body -->
                <div class="card-body">
                  <div class="chart-area" id="div_myAreaChart">
                    <canvas id="myAreaChart"></canvas>
                  </div>
                </div>
              </div>
            </div>
            
            <!-- Area Chart -->
            <div class="col-xl-6 col-lg-6">
              <div class="card shadow mb-4">
                <!-- Card Header - Dropdown -->
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                  <h6 class="m-0 font-weight-bold text-primary">Lot Count vs Rate Range</h6>
                  <div class="dropdown no-arrow">
                    <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                      <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in" aria-labelledby="dropdownMenuLink">
                      
                    </div>
                  </div>
                </div>
                <!-- Card Body -->
                <div class="card-body">
                  <div class="chart-area" id="div_yearWiseAmount">
                    <canvas id="yearWiseAmount"></canvas>
                  </div>
                </div>
              </div>
            </div>
            </div>

		 <!-- Footer -->
      <footer class="sticky-footer bg-white">
        <div class="container my-auto">
          <div class="copyright text-center my-auto">
            <span>Copyright &copy;Infosane.co.in</span>
          </div>
        </div>
      </footer>
        </div>
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->


    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->

  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>

 <jsp:include page="otherModal.jsp"></jsp:include>
  
  <div id="myModal" class="modal fade" role="dialog">
	  <div class="modal-dialog modal-xl">
	
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title" id="id_modal_title"></h4>
	      </div>
	      <div class="modal-body">
	      
	          <div class="card shadow mb-4">
	           
	            <div class="card-body">
	              <div class="table-responsive">
	                <table class="table table-bordered" id="dataTable_partyBids" width="100%" cellspacing="0">
	                  
	                </table>
	              </div>
	            </div>
	          </div>
	          </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	      </div>
	    </div>
	
	  </div>
	</div>
  <div>
   
     <!-- Logout Modal-->
  <div class="modal fade" id="id_lotList" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Lot List</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">
        <table class="table table-bordered"  width="100%" cellspacing="0">
        	<thead>
        	<tr>
        		<td>Lot No		</td>
        		<td>Lot Name		</td>
        		<td>Division		</td>
        	</tr>
        	</thead>
        
      		  <c:forEach items="${sessionScope.sessionUser.party_lots}" var="tender">
	      		  <tr>
		      		  <td>${tender.lot_name}		</td>
		        		<td> ${tender.lot_given_name}		</td>
		        		<td>${tender.division}		</td>
				</tr>
		     </c:forEach>
		     </table>
        </div>
        <div class="modal-footer">
        	<button class="btn btn-secondary" type="button" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>                 
   
  

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
  <!-- Page level plugins -->
  <script src="${pageContext.request.contextPath}/resources/vendor/datatables/jquery.dataTables.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/vendor/datatables/dataTables.bootstrap4.min.js"></script>

 
</body>

</html>