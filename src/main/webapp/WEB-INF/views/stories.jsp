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
   	.pager {
  		display: flex;
  		list-style : none;
   		margin-top : 50px;
	}
   .pager a {
	  color: black;
	  float: left;
	  padding: 8px;
	  text-decoration: none;
	}
	.activePage {
		background-color : #337ab7;
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
	   		<h1>Stories History</h1>	 
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
	        
	        <div class="row" id="storiesDiv">
				<c:forEach items="${storiesList}"  var="list" varStatus="status">
                	<!--  <td><c:out value="${iStat.index + 1}" /></td> -->
                	<!--  <td><c:out value="${list.mimeType}" /></td> -->
               	    <div class="pagination pagination-large col-md-4" class="center-align">
	               		<c:if test="${not empty list.uploadedPath}">
	               			<div style="background-color: white; padding: 5px;">
		               			<div>
		             				<a href="${pageContext.request.contextPath}/userViewComment/<c:out value='${list.id}'/>">
		             			    <button class="btn btn-info" style="margin-left: 185px;">View Comments</button></a>
		          				</div>
		              			    
		              			<img style="width: 100px;margin-top: -40px;" alt="No Image Available" src="${list.uploadedPath}"/>
		               			<div>
		               		   		<b>Date:</b> <c:out value="${list.uploadedDateTime}" />
		               		   		<br>
		               		   		<b>ZlenCode:</b> 
		               		   </div>
		               		   
	               		   </div>
	                    </c:if>
               	    </div>
            	</c:forEach>
			</div>
			
<!-- 			<div class="row"><div class="col-md-2 col-lg-2 col-sm-2">
			     <h3 class=" page-info" style="margin-top:14px;"></h3>
			</div> -->
			<div class="col-md-10 col-lg-10 col-sm-10">
		        <div class="pagination pagination-large pull-right">
		            <button class="btn btn-primary btn-md" onClick="previous()">Prev</button>
		            <ul class="pager"></ul>
		            <button class="btn btn-primary btn-md" onClick="next()">Next</button>
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
	    var listElement = $('#storiesDiv');
	    var perPage=9;
	    var numItems = ${totalElement};
	    var numPages = Math.ceil(numItems / perPage);

	    $('.pager').data("curr", 0);
	    $('.pager').html('');
	    var curr = 0;
	    /* $('<li><a href="" class="page_link" onClick="previous()">'Prev'</a></li>').appendTo('.pager'); */
	    while (10 > curr) {
	        $('<li><a href="${pageContext.request.contextPath}/stories/paginated" class="page_link" data-pageNumber="'+curr+'">' + (curr + 1) + '</a></li>').appendTo('.pager');
	        curr++;
	    }
/* 	    $('<li><a href="" class="page_link" onClick="next()">'Next'</a></li>').appendTo('.pager'); */
	    $('.pager .page_link:first').addClass('activePage');

	    $('.pager li a').click(function (event) {
	    	event.preventDefault();
	        var index = $(this).parents('li:first').index();
	     	$('.pager:first li').eq(index).find('a').css({ 'background-color': '#337ab7', 'color': 'white', 'font-weight': 'bold' });
	        $('.pager:first li').not(':eq(' + index.toString() + ')').find('a').css({ 'background-color': 'white', 'color': '#337ab7', 'font-weight': 'normal' });
	        $('.pager:last li').eq(index).find('a').css({ 'background-color': '#337ab7', 'color': 'white', 'font-weight': 'bold' });
	        $('.pager:last li').not(':eq(' + index.toString() + ')').find('a').css({ 'background-color': 'white', 'color': '#337ab7', 'font-weight': 'normal' });
	        var clickedPage = $(this).html().valueOf() - 1;
	        var hrefUrl = $(this).attr("href") + "?pageNumber=" + clickedPage + "&size=9";
 	        goTo(clickedPage, hrefUrl);
	    });
		
		window.previous = function previous() {
			
		    var goToPage = parseInt($('.pager').data("curr")) - 1;
		    var hrefUrl = "/stories/paginated?pageNumber=" + goToPage + "&size=9";
		    /* if ($('.active').prev('.page_link').length == true) { */
		        goTo(goToPage, hrefUrl);
		}

		window.next = function next() {
		    goToPage = parseInt($('.pager').data("curr")) + 1;
		    var hrefUrl =  "/stories/paginated?pageNumber=" + goToPage + "&size=9";
		    goTo(goToPage, hrefUrl);
		}

		function goTo(page, url) {
			$.ajax({
			       type: "GET",
			       url:  url,
	               success:function(data){
	                
	             	 var storiesText = "";
	             	 $.each(data, function(index, story) {

	             		 storiesText += '<div class="pagination pagination-large col-md-4" class="center-align">'
				    	               			+ '<div style="background-color: white; padding: 5px;">'
				    		               			+ '<div>'
				    		             				+ '<a href="${pageContext.request.contextPath}/userViewComment/<c:out value="' + story.id'" />">'
				    		             				+ '<button class="btn btn-info" style="margin-left: 185px;">View Comments</button></a>'
				    		             			+ '</div>'
				    		             			+ '<img style="width: 100px;margin-top: -40px;" alt="No Image Available" src="'+ story.uploadedPath+'"/>'
				    		             			+ '<div>'
				    		             				+ '<b>Date:</b>'+ story.uploadedDateTime 
				    		             				+ '<br>'
				    		             				+ '<b>ZlenCode:</b>'
				    		             			+ '</div>'
			    		             			+ ' </div>'
		    		             			+ '</div>'
	                   })

	                   $("#storiesDiv").empty();
	              	   $("#storiesDiv").append(storiesText);
	               }

			 	});
		    $('.pager').data("curr", page);
		}

		/* $(document).on("click",".pager li a",function(event) {

		    event.preventDefault();

		    var hrefUrl = $(this).attr("href") + "?pageNumber=" +$(this).data("pagenumber") + "&size=9";

		    $.ajax({
		       type: "GET",
		       url:  hrefUrl,
               success:function(data){
                
             	 var storiesText = "";
             	 $.each(data, function(index, story) {

             		 storiesText += '<div class="pagination pagination-large col-md-4" class="center-align">'
			    	               			+ '<div style="background-color: white; padding: 5px;">'
			    		               			+ '<div>'
			    		             				+ '<a href="${pageContext.request.contextPath}/userViewComment/<c:out value="' + story.id'" />">'
			    		             				+ '<button class="btn btn-info" style="margin-left: 185px;">View Comments</button></a>'
			    		             			+ '</div>'
			    		             			+ '<img style="width: 100px;margin-top: -40px;" alt="No Image Available" src="'+ story.uploadedPath+'"/>'
			    		             			+ '<div>'
			    		             				+ '<b>Date:</b>'+ story.uploadedDateTime 
			    		             				+ '<br>'
			    		             				+ '<b>ZlenCode:</b>'
			    		             			+ '</div>'
		    		             			+ ' </div>'
	    		             			+ '</div>'
                   })

                   $("#storiesDiv").empty();
              	   $("#storiesDiv").append(storiesText);
               }

		 	});
	        
	    }); */
			
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
<!-- // 	function clearFilter(){
// 		window.location = '/storiesViews';
// 		} -->
<!-- </script> -->
</body>
</html>        
