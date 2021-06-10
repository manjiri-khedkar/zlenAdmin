<!DOCTYPE html>
<html lang="en">
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head> 
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>User Stories List</title>
  <meta name="description" content="">
  <meta name="viewport" content="width=device-width, initial-scale=1">

    <!--====== Favicon Icon ======-->
    <link rel="shortcut icon" href="assets/images/favicon.png" type="image/png">

    <!-- CSS
    ============================================ -->

    <!--===== Vendor CSS (Bootstrap & Icon Font) =====-->

    <link rel="stylesheet" href="assets/css/plugins/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/plugins/fontawesome.min.css">
    <link rel="stylesheet" href="assets/css/plugins/default.css">

    <!--====== Main Style CSS ======-->
    <link rel="stylesheet" href="assets/css/style.css">
  
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
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

<!--====== Section Start ======-->
<section class="login min-height">
	<div id="wrapper">
		<jsp:include page="header.jsp"></jsp:include>
			<div class="container">
				<div class="clearfix">&nbsp;</div>                        
<%--        				<form:form action="${pageContext.request.contextPath}/friendList/{id}" id="table1" method="post"  modelAttribute="friendDetails"> --%>
       
			   			<div class="panel-heading">
							<h2 class="text-center" style="color: black;"><b>Activity Log Details</b></h2>
						</div><br/>       
                   
                   
              <div class="panel-body">

				<div class="row">
						<div class="col-md-12">
							<div class="table-responsive">
								<table id="table1" class="table info-tbl text-left" style='border: 1px solid #d3d3d3;width: 98% !important; '>
									<thead>
										<tr>
										    <th class="text-left" style="background: #d3d3d3">Sr.No.</th>
											<th class="text-left" style="background: #d3d3d3">Activity</th>
											<th class="text-left" style="background: #d3d3d3">Date</th> 
											<th class="text-left" style="background: #d3d3d3">UserName</th>
											<th class="text-left" style="background: #d3d3d3">Mobile</th>
											<th class="text-left" style="background: #d3d3d3">ZlenCode</th>
										</tr>
									</thead>
					
									<tbody>
										<c:forEach items="${userActivityList}"  var="userActivityList" varStatus="status"> 
		 	                        		<tr class="odd gradeX"> 
												
												<td><c:out value="${status.index+1}" /></td> 
												<td><c:out value="${userActivityList.activity}" /></td> 
												<td><c:out value="${userActivityList.createdDate}" /></td> 
												<td><c:out value="${userActivityList.userName}" /></td>
												<td><c:out value="${userActivityList.userMobile}" /></td>
												<td><c:out value="${userActivityList.zlenCode}" /></td>
											</tr> 
		 	                    		</c:forEach> 
		                           </tbody>
								</table>
							</div>
						</div>		
					</div>
				</div>
<%--              </form:form> --%>
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
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>
  <jsp:include page="otherModal.jsp"></jsp:include>
  
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
  		
  <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
  <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	
              
<!-- <script type="text/javascript">

	$(document).ready(function () {

    $("#search-form").submit(function (event) {
      
   
        event.preventDefault();
        search();
    });
});
	function search() {

	var detailsList;
	detailsList = {}
	detailsList["userId"] = $("#userId").val();

	$("#btn-search").prop("disabled",false);
     
    $.ajax({
        type: "GET",
        url:  "${pageContext.request.contextPath}/activity-log",
//         url: "http://localhost:8080/activity-log",
        data: detailsList,

              success:function(data){
                
            	  var result ;
            	 // var id;
            	  var activity;
				  var createdDate;
            	  //var userId;
				  var userName;
				  var userMobile;
				  var zlenCode;
				
				  
             	  $(data).each(function (index,ele){
                 	alert('ele===>'+ele);
                 	//id = ele.id;
                    //uploadedDateTime = ele.uploadedDateTime;
                    //userId = ele.userId;
                    userName = ele.userName;
 				    console.log("data", data);
                    console.log("ele", ele);   
                    //console.log("id", id);                 
                    //console.log("userId", userId);   
                    console.log("userName", userName);
                    


        result = "<tr><td>"+ele.activity+"</td><td>"+ele.createdDate+"</td><td>"+ele.userName+"</td><td>"+ele.userMobile+"</td><td>"+ele.zlenCode+"</td></tr>";                   
                                   
             	  });
            	  
                  $('#table1 tbody').html(result);
                   
                  return;  
                  alert(ele.success);        			   
              }
		});
		}

			</script>
 				function clearFilter(){
 					window.location = '/detailsList';
 					}
 			</script>   -->
</body>
</html>