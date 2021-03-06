<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

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
		$(document).ready(function (){
			
			loadStateData();
			
		});
		
		function loadStateData(state){
			$.ajax({
				url : "chartData",
				type : "GET",
				data : {state_id: $("#id_selectState").val()},
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
			loadYearWiseAmount(data["yearWiseAmount"]);
		}
		function loadYearWiseRates(yearWisedata){
			//var yearWisedata = JSON.parse(data);
			var labels = yearWisedata.map(function(e) {
				   return e.year +"("+e.round+")";
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
				   return e.year +"("+e.round+")";
				});
			var amount = yearWisedata.map(function(e) {
				   return e.amount;
			});
			
			var ctx =$("#yearWiseAmount");
			var myLineChart = new Chart(ctx, {
			  type: 'bar',
			  data: {
			    labels: labels,
			    datasets: [{
			      label: "Total Sale Amount (in Cr)",
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
			            return 'Rs' + value;
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
			          return datasetLabel + ': Rs' +tooltipItem.yLabel;
			        }
			      }
			    }
			  }
			});
		}
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

	<div  id="warning-message" style="">

     	In Mobile, this web site works better in landscape view. Please rotate your mobile to experience the best view.  

 	</div>
  <!-- Page Wrapper -->
  <div id="wrapper">

   		<jsp:include page="header.jsp"></jsp:include>

        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">Year Comparison</h1>
             <select name="state" id="id_selectState" onchange="loadStateData(this.value)">
	            <option value="1" selected>Madhya Pradesh</option>
	            <option value="2">Chattishgrah</option>
  			</select>
          </div>

         <!-- Content Row -->

          <div class="row">

            <!-- Area Chart -->
            <div class="col-xl-6 col-lg-6">
              <div class="card shadow mb-4">
                <!-- Card Header - Dropdown -->
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                  <h6 class="m-0 font-weight-bold text-primary">Year Wise Rates</h6>
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
                  <div class="chart-area">
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
                  <h6 class="m-0 font-weight-bold text-primary">Year Wise Amount</h6>
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
                  <div class="chart-area">
                    <canvas id="yearWiseAmount"></canvas>
                  </div>
                </div>
              </div>
            </div>

          </div>

         

        </div>
        <!-- /.container-fluid -->

      </div>
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

    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->

  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>

<jsp:include page="otherModal.jsp"></jsp:include>
  
   <div>
   
     <!-- Logout Modal-->
  <div class="modal fade" id="id_lotList" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Lot List</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">?</span>
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

</body>

</html>