<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Stories</title>
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
  <!-- Custom styles for this template-->
  <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">
	
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
							<h2 class="text-center" style="color: black;"><b>Activity Log Lists</b></h2>
						</div><br/>       
                   
                   
              <div class="panel-body">

				<div class="row">
						<div class="col-md-12">
							<div class="table-responsive">
								<table id="table1" class="table info-tbl text-left" style='border: 1px solid #d3d3d3;width: 98% !important; '>
									<thead>
										<tr>
										    <th class="text-left" style="background: #d3d3d3">Sr.No.</th>
										    <th class="text-left" style="background: #d3d3d3">ID</th>
											<th class="text-left" style="background: #d3d3d3">Activity</th>
											<th class="text-left" style="background: #d3d3d3">Created Date</th> 
											<th class="text-left" style="background: #d3d3d3">Notify User Device ID</th>
											<th class="text-left" style="background: #d3d3d3">Notify User ID</th>
											<th class="text-left" style="background: #d3d3d3">Zlen Code</th>
										</tr>
									</thead>
					
									<tbody>
										<c:forEach items="${userActivityList}"  var="list" varStatus="status"> 
		 	                        		<tr class="odd gradeX"> 
												
												<td><c:out value="${status.index+1}" /></td>
												<td><c:out value="${list.id}" /></td>  
												<td><c:out value="${list.activity}" /></td> 
												<td><c:out value="${list.createdDate}" /></td> 
												<td><c:out value="${list.notifyUserDeviceId}" /></td>
												<td><c:out value="${list.notifyUserId}" /></td>
												<td>
												<a href="${pageContext.request.contextPath}/userViewZlen/<c:out value='${list.zlenCode}'/>" class="showData">
													<c:out value="${list.zlenCode}" />
													</a>
													</td>
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
  
  <script type="text/javascript"> 

 $(document).ready(function () {

     
 });
 function search() {
	 
 	
 	var ustoriesList;
 		ustoriesList = {} 		
 		ustoriesList["uploadedDateTime"] = $("#inputDate").val();
 		ustoriesList["mimeType"] = $("#inputMimeType").val();    
 		ustoriesList["zlenCode"] = $("#inputCode").val();    
		
 		
 $("#btn-search").prop("disabled",false);
     
     $.ajax({
         type: "GET",
         //contentType: "application/json",
         url:  "${pageContext.request.contextPath}/storiesViews",
        // success:function(result)
         data: ustoriesList,
         //dataType: 'json',

        /// data:{userName:inputName, userMobile:inputMobile, zlenCode:inputCode, deviceType:inputType},
               success:function(data){
                
             	  var result ="";
             	  var id;
 				  var uploadedDateTime;
  		      	  var mimeType;
  		      	  var zlenCode;
				  
              	  $(data).each(function (index,ele){
                  	
                  	id = ele.id;
                  	uploadedDateTime = ele.uploadedDateTime;
                    mimeType = ele.mimeType;
                    zlenCode = ele.zlenCode;
 				     
  				     console.log("data", data);
                     console.log("ele", ele);   
                     console.log("id", id);                 
                     console.log("uploadedDateTime", uploadedDateTime);
                     console.log("mimeType", mimeType);
                     console.log("zlenCode", zlenCode);
				
                     result  += "<tr><td>"+ele.id+"</td><td>"+ele.uploadedDateTime+"</td><td>"+ele.mimeType+"</td><td>"+ele.zlenCode+"</td></tr>";                   
                                     
              	  });
            	  
                   $('#table1 tbody').html(result);
                   
                   return;  
                   alert(ele.success);        			   
               }

 		});
 }

</script>

<!-- <script type="text/javascript"> -->
// 	function clearFilter(){
// 		window.location = '/storiesViews';
// 		}
<!-- </script> -->
</body>
</html>        
