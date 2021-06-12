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

	 
<!--====== Section Start ======-->
<section class="login min-height">
<div id="wrapper">
	<jsp:include page="header.jsp"></jsp:include>
	
   		<div class="container">
	   		<h1>Stories Hostory</h1>	 
	        <br/>
	        
	        <div class="row" > 
  		        <!-- <label for="uploadedDateTime"><b>Date : </b></label>&nbsp; --> 
 				<input style="display: none" type="text" id="inputDate">&nbsp;&nbsp;
 						    
   				<label for="mimeType"><b>Mime Type : </b></label>&nbsp;  
   				<!-- <input type="text"  id="inputMimeType" placeholder="Enter Mime Type......">&nbsp;&nbsp; -->	 
   				<select id="inputMimeType">  
 					<option value="All" selected>All</option>  
  					<option value="image">Image</option> 
  					<option value="video">Video</option>
 					<option value="text">Text</option>  
  				</select>&nbsp;&nbsp;&nbsp;&nbsp;	
  				
  				<label for="zlenCode"><b>Zlen Code: </b></label>&nbsp;   
    				<input type="text"  id="inputCode" placeholder="Enter Zlen Code......">&nbsp;&nbsp;
    				 
   				
   					<button type="button" id="bth-search" class="btn btn-success btn-md"  onclick="search()">Search</button>&nbsp;&nbsp;	
  				
   			</div>
   			<br/> <br/>
	        
	        <div class="row">
                        	<c:forEach items="${storiesList}"  var="list" varStatus="status">
	                        	  <div class="col-md-4"  class="center-align" style="margin: 5px" >
	                        		<c:if test="${not empty list.uploadedPath}">
	                        			<div style="background-color: white; padding: 5px;" class="border">
                        			    		<div style="text-align:center">
                        			   		 		<img class="img-fluid img-view" style="max-height: 175px;" alt="No Image Available" src="${list.uploadedPath}"/>
                        			   		 	</div>
			                        		   <div>
			                        		   		<b>Date:</b> <c:out value="${list.uploadedDateTime}" />
			                        		   		<br>
			                        		   		<b>ZlenCode:</b> 
			                        		   		<a href="${pageContext.request.contextPath}/userViewZlen/<c:out value='${list.zlenCode}'/>" class="showData">
			                        		   			<c:out value="${list.zlenCode}" />
			                        		   		</a>
			                        		   		<br>
			                        		   		
			                        		   		<a href="${pageContext.request.contextPath}/userViewComment/<c:out value='${list.id}'/>" class="btn btn-sm btn-info showData">
			 	                    					View Comments
			 	                    				</a> 
			                        		   </div>
	                        		   </div>
                                    </c:if>
	                        	  </div>
	                    	</c:forEach>
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
