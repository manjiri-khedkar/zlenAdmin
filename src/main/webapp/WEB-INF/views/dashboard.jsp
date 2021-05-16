<!DOCTYPE html>
 <html lang="en"> 

   

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
	
	
	 
	<script type='text/javascript' src="${pageContext.request.contextPath}/resources/js/dashboard/dashboardchart.js" ></script>
	
	
  <!-- Custom styles for this template-->
  <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">
	<script>
		$(document).ready(function (){
			
			//loadStateData();
		});

		$(function()
				{
			var name, date, listt, datecounter;
			$.ajax( {
			      type : "Get", 
			      url : "/dashboard/bar-chart", 
			      contentType : "application/json", 
			      dataType : 'json', 
			      success : function (data) {
			    	 
			    	  listt = data.listt;
			    	  datecounter = data.datecounter;
			    	  
			    	  console.log("success", data.name );
			    	  console.log("listt", listt);
			    	  console.log("datecounter", datecounter);
			   
			
	    Highcharts.chart('container', {
	        chart: {
	            type: 'column'
	        }, 
	         title: {
	            text: 'User-Infomation'
	        }, 
	   
	        xAxis: {
	        	
	            categories : listt, 
	            crosshair: true
	        },
	        yAxis: {
	            min: 0,
	            max:100,
	            title: {
	                text: '<b>Users Count</b>'
	                
	            }       
	        },
	        tooltip: {
	            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
	            pointFormat: '<tr><td style="color:{series.color};padding:1">{series.name}: </td>' +
	            '<td style="padding:0"><b>{point.y}add</b></td></tr>',
	            footerFormat: '</table>',
	            shared: true,
	            useHTML: true
	        },
	        plotOptions: {
	            column: {
	                pointPadding: 0.3,
	                borderWidth: 2
	            }
	        },
	           
	         series: 
	        	 [{		 
	            name : "Dates",
	           //data : [12,14,16,23,23,24]
	            data : datecounter
	         
	        }] 
	       
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

	<div  id="warning-message" style="">

     	In Mobile, this web site works better in landscape view. Please rotate your mobile to experience the best view.  

 	</div>
  <!-- Page Wrapper -->
  <div id="wrapper">

   		<jsp:include page="header.jsp"></jsp:include>

        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
<!--           <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">Year Comparison</h1>
             <select name="state" id="id_selectState" onchange="loadStateData(this.value)">
	            <option value="1" selected>Madhya Pradesh</option>
	            <option value="2">Chattishgrah</option>
  			</select>
          </div>
 -->
         <!-- Content Row -->

          <div class="row">
<div id="container"  style="width: 550px; height: 400px; margin: 0 auto"></div>
            <!-- Area Chart -->
<!--             <div class="col-xl-6 col-lg-6">
              <div class="card shadow mb-4">
                Card Header - Dropdown
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
 -->            
     <!-- Card Body -->
               <!--  <div class="card-body">
                  <div class="chart-area">
                    <canvas id="myAreaChart"></canvas>
                  </div>
                </div>
              </div>
            </div> -->
            
            <!-- Area Chart -->
        <!--     <div class="col-xl-6 col-lg-6">
              <div class="card shadow mb-4">
                Card Header - Dropdown
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
                
                Card Body
                <div class="card-body">
                  <div class="chart-area">
                    <canvas id="yearWiseAmount"></canvas>
                  </div>
                </div>
              </div>
            </div>

          </div>

        --> 
<!-- <button id="xy" onclick="document.location='/user/barChart'">HTML Tutorial</button> -->
        </div>
        <!-- /.container-fluid -->

      </div>
       
      <!-- End of Main Content -->
      
         
    

      <!-- Footer -->
   <!--    <footer class="sticky-footer bg-white">
        <div class="container my-auto">
          <div class="copyright text-center my-auto">
            <span>Copyright &copy;Infosane.co.in</span>
          </div>
        </div>
      </footer> -->
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
        
      		 <%--  <c:forEach items="${sessionScope.sessionUser.party_lots}" var="tender">
	      		  <tr>
		      		  <td>${tender.lot_name}		</td>
		        		<td> ${tender.lot_given_name}		</td>
		        		<td>${tender.division}		</td>
				</tr>
		     </c:forEach> --%>
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
  <script src="https://code.highcharts.com/highcharts.js"></script>
  <script src="https://code.highcharts.com/modules/exporting.js"></script>

</body>

</html>