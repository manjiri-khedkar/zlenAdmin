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
	

@media only screen and (orientation:landscape){
	
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

		$(function() {
			var name, date, listt, datecounter;
			$.ajax( {
			      type : "Get", 
			      url : "${pageContext.request.contextPath}/dashboard/bar-chart", 
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
	            text: 'User-Information'
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

		 $(function() {
				var dates, gdata;
				$.ajax( {
				      type : "Get", 
				      url : "${pageContext.request.contextPath}/dashboard/stories-bar-chart", 
				      contentType : "application/json", 
				      dataType : 'json', 
				      success : function (data) {
				    	 
				      dates = data.dates;
				      gdata = data.count;

				      console.log("dates", dates);
			    	  console.log("graphdata", gdata);
			    	
		    Highcharts.chart('container1', {
		        chart: {
		            type: 'column'
		        }, 
		        
		        title: {
		            text: 'Stories-Information'
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
			    max:100,
			     title: {
			      text: 'User Count'
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
		        legend: {
                  reversed: true
			    },
		        series: gdata,
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
 <div class="container-fluid">
 <div class="row">
 	
	<div class="col-md-6" id="container" style=""></div>

	<div class="col-md-6" id="container1" style=""></div>
 </div>
 </div>
 </div>
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
        		<td>Lot No	</td>
        		<td>Lot Name </td>
        		<td>Division </td>
        	</tr>
        	</thead>
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