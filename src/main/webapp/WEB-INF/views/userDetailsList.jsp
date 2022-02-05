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

<title>User Details List</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!--====== Favicon Icon ======-->
<link rel="shortcut icon" href="resources/img/favicon.png"
	type="image/png">

<!-- CSS
    ============================================ -->

<!--===== Vendor CSS (Bootstrap & Icon Font) =====-->

<link rel="stylesheet" href="assets/css/plugins/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/plugins/fontawesome.min.css">
<link rel="stylesheet" href="assets/css/plugins/default.css">

<!--====== Main Style CSS ======-->
<link rel="stylesheet" href="assets/css/style.css">


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

#warning-message {
	position: fixed; /* Sit on top of the page content */
	display: none; /* Hidden by default */
	width: 100%; /* Full width (cover the whole page) */
	height: 100%; /* Full height (cover the whole page) */
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	background-color: rgba(0, 0, 0, 0.5);
	/* Black background with opacity */
	z-index: 2;
	/* Specify a stack order in case you're using a different order for other elements */
	cursor: pointer; /* Add a pointer on hover */
	font-weight: bold;
	text-align: center;
	color: white;
	padding: 40px;
	font-size: 25px;
}

.text-left1{
  grid-column: 1 / span 2;
}

@media only screen and (orientation:portrait) {
	#warning-message {
		display: block;
	}
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

@media only screen and (orientation:landscape) {
	#warning-message {
		display: none;
	}
	#page-top {
		-webkit-transform: rotate(0deg);
		-moz-transform: rotate(0deg);
		-o-transform: rotate(0deg);
		-ms-transform: rotate(0deg);
		transform: rotate(0deg);
	}
}
</style>
<!-- Custom styles for this template-->
<link
	href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css"
	rel="stylesheet">
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
</head>

<body id="page-top">


	<!--====== Section Start ======-->
	<section class="login min-height">
		<div id="wrapper">
			<jsp:include page="header.jsp"></jsp:include>

			<div class="container">
				<h1>User Details List</h1>

				<div class="row">
					<label for="userName"><b>User Name : </b></label>&nbsp; <input
						type="text" id="inputName" placeholder="Enter User Name......">&nbsp;&nbsp;

					<label for="userMobile"><b>Mobile No : </b></label>&nbsp; <input
						type="text" id="inputMobile" placeholder="Enter Mobile No......">&nbsp;&nbsp;

					<label for="zlenCode"><b>Zlen Code : </b></label>&nbsp;&nbsp; <input
						type="text" id="inputCode" placeholder="Enter Code......">
					<!--   				<button type="button" class="btn btn-danger btn-md" onclick="clearFilter()">Clear</button> -->
				</div>
				<br />

				<div class="row">

					<b>Select Device Type : </b>&nbsp;&nbsp;&nbsp;&nbsp; <select
						id="inputType">
						<option value="All" selected>All</option>
						<option value="android">Android</option>
						<option value="ios">Apple</option>
						<option value="Window">Window</option>
						<option value="Desktop">Desktop</option>
					</select>&nbsp;&nbsp;&nbsp;&nbsp; <label for="age">
					
					<b>Age : </b></label>&nbsp;&nbsp;
					<!--   				<input type="text" id="inputAge" placeholder="Enter Age......">&nbsp;&nbsp;&nbsp;&nbsp; -->

					<select id="inputAge">
						<option value="0,0" selected>All</option>
						<option value="0,19">0-19</option>
						<option value="20,25">20-25</option>
						<option value="26,45">26-45</option>
						<option value="46,10000">46 Above</option>
					</select>&nbsp;&nbsp;&nbsp;&nbsp; 
					
					<b>Select Gender : </b>&nbsp;&nbsp;&nbsp;&nbsp;
					<select id="inputGender">
						<option value="All" selected>All</option>
						<option value="male">Male</option>
						<option value="female">Female</option>
					</select>&nbsp;&nbsp;&nbsp;&nbsp;
					
					<b>Number Of Friends : </b>&nbsp;&nbsp;&nbsp;&nbsp;
					<select id="inputFrndCount">
						<option value="0,0" selected>All</option>
						<option value="0,2">0-2</option>
						<option value="2,5">2-5</option>
						<option value="5,10">5-10</option>
						<option value="10,100">10 & Above</option>
					</select>&nbsp;&nbsp;&nbsp;&nbsp;
					
					<button type="button" id="bth-search"
						class="btn btn-success btn-md"
						onclick="event.preventDefault(); search()">Search</button>
					&nbsp;&nbsp;
					<button type="button" class="btn btn-danger btn-md"
						onclick="clearFilter()">Clear</button>
					&nbsp;&nbsp;
					<%-- 						<a href="${pageContext.request.contextPath}/download?days"> --%>
					<button type="button" id="bth-download" onclick="download()"
						class="btn btn-success btn-md">Download</button>
				</div>
				<!--                    </form> -->
				<!--  			 <button type="submit" class="btn-primary btn" id="ajaxBtn">Search</button>  -->
				<!--   <button class="btn btn-success" type="submit" id="ajaxBtn" value="Search" onclick="searchFun()">Search</button>
 -->
				<!-- onclick ="searchFun()"  -->
				<br> <br>
				
<%-- 				<jsp:useBean id="pagedListHolder" scope="request" --%>
<%-- 								type="org.springframework.beans.support.PagedListHolder" /> --%>
<%-- 							<c:url value="/userDetailsList" var="pagedLink"> --%>
<%-- 								<c:param name="p" value="~" /> --%>
<%-- 							</c:url> --%>
				<div class="row">				
					<div class="col-md-12">
					
						<div class="table-responsive">
								
							<table id="table1" class="table info-tbl text-left "
								style='border: 1px solid #d3d3d3; width: 98% !important;'>
								<thead>
									<tr>
										<th class="text-left" style="background: #d3d3d3">Sr. No.</th>
										<th class="text-left" style="background: #d3d3d3">Create
											Date</th>
										<th class="text-left" style="background: #d3d3d3">User
											Name</th>
										<th class="text-left" style="background: #d3d3d3">Year Of
											Birth</th>
										<th class="text-left" style="background: #d3d3d3">Gender</th>
										<th class="text-left" style="background: #d3d3d3">Mobile
											No.</th>
										<th class="text-left" style="background: #d3d3d3">Zlen
											Code</th>
										<th class="text-left" style="background: #d3d3d3">Friend
											Count</th>
										<th class="text-left" style="background: #d3d3d3">Device
											Type</th>
											<th class="text-left1" style="background: #d3d3d3">Is Banned</th>
											
<!-- 										<th class="text-left" style="background: #d3d3d3">Action</th> -->
									</tr>
								</thead>

								<tbody class=>
									<c:forEach items="${userListDetails}" var="list"
										varStatus="status">
										
										<tr class="odd gradeX">

											<td><c:out value="${status.index + 1}" /></td>
											
											<td><fmt:formatDate  value="${list.createdOn}"  pattern = "yyyy/MM/dd HH:mm"  /></td>
											
											<td><c:out value="${list.userName}" /></td>

											<td><c:out value="${list.age}" /></td>

											<td><c:out value="${list.gender}" /></td>

											<td><c:out value="${list.userMobile}" /></td>

											<td><a
												href="${pageContext.request.contextPath}/userViewForm/<c:out value='${list.id}'/>"
												class="btn btn-info btn-sm showData"> <c:out
														value="${list.zlenCode}" />
											</a></td>

											<td><a href="${pageContext.request.contextPath}/friendList/<c:out value='${list.id}'/>"
												class="btn btn-primary btn-sm showData"><c:out value="${list.frnds_count}" /></a></td>

											<td><c:out value="${list.deviceType}" /></td>
											
											<td><c:out value="${list.isbanned}" /></td>
											
											<!--  <td><c:out value="${list.latitude}" /></td>
	 	                        		<td><c:out value="${list.longitude}" /></td>-->
<!-- 											<td><a -->
<%-- 												href="${pageContext.request.contextPath}/friendList/<c:out value='${list.id}'/>" --%>
<!-- 												class="btn btn-primary btn-sm showData"> <i -->
<!-- 													class="fas fa-user-friends" -->
<!-- 													style="font-size: 20px; color: #ffff;"></i> -->
<!-- 											</a>&nbsp; -->
<%-- 											 <a href="${pageContext.request.contextPath}/contactlist/<c:out value='${list.id}'/>" 
<%-- 	 	                    					 class="btn btn-danger btn-sm showData"> --%>
<%-- 	 	                    					 <i class="fas fa-address-book" style="font-size: 20px; color: #ffff;"></i> --%>
<%-- 	 	                    					 </a>&nbsp; --%> 
<!-- 	 	                    					 </td> -->
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<a href="${pageContext.request.contextPath}/userDetailsList/1">1</a>&nbsp;&nbsp;
						<a href="${pageContext.request.contextPath}/userDetailsList/2">2</a>&nbsp;&nbsp;
<%-- 						<a href="${pageContext.request.contextPath}/userDetailsList/3">3</a>&nbsp;&nbsp; --%>
<%-- 						<a href="${pageContext.request.contextPath}/userDetailsList/4">4</a>&nbsp;&nbsp; --%>
<%-- 						<a href="${pageContext.request.contextPath}/userDetailsList/5">5</a>&nbsp;&nbsp; --%>
<%-- 						<a href="${pageContext.request.contextPath}/userDetailsList/6">6</a>&nbsp;&nbsp; --%>
<%-- 						<a href="${pageContext.request.contextPath}/userDetailsList/7">7</a>&nbsp;&nbsp; --%>
<%-- 						<a href="${pageContext.request.contextPath}/userDetailsList/8">8</a>&nbsp;&nbsp; --%>
<%-- 						<a href="${pageContext.request.contextPath}/userDetailsList/9">9</a>&nbsp;&nbsp; --%>
<%-- 						<a href="${pageContext.request.contextPath}/userDetailsList/10">10</a> --%>
<%-- 						<a href="${pageContext.request.contextPath}/userDetailsList/3">3</a> --%>
<%-- 						<a href="${pageContext.request.contextPath}/userDetailsList/4">4</a> --%>
				</div>
			</div>
		</div>
<%-- 		<tg:paging pagedListHolder="${pagedListHolder}" --%>
<%-- 			pagedLink="${pagedLink}" /> --%>
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
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>


	<!-- Bootstrap core JavaScript-->
	<script
		src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->


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



	<script type="text/javascript">
		$(document).ready(function() {

			$("#search-form").submit(function(event) {

				//stop submit the form, we will post it manually.
				event.preventDefault();
				search();
				// fire_ajax_submit();
			});

		});

		function search() {
			debugger
			
			var udetailList;
			udetailList = {}
			udetailList["userName"] = $("#inputName").val();
			udetailList["userMobile"] = $("#inputMobile").val();
			udetailList["zlenCode"] = $("#inputCode").val();
			udetailList["deviceType"] = $("#inputType").val();
			udetailList["gender"] = $("#inputGender").val();
			udetailList["age"] = $("#inputAge").val().split(',')[0];
			udetailList["age1"] = $("#inputAge").val().split(',')[1];
			udetailList["friendNumber"] = $("#inputFrndCount").val().split(',')[0];
			udetailList["friendNumber1"] = $("#inputFrndCount").val().split(',')[1];
			$("#btn-search").prop("disabled", false);
			debugger
			$
					.ajax({
						type : "GET",
						//contentType: "application/json",
						data : udetailList,
						
					url : "${pageContext.request.contextPath}/userDetailsListContents",
						// success:function(result)
						
						//dataType: 'json',

						/// data:{userName:inputName, userMobile:inputMobile, zlenCode:inputCode, deviceType:inputType},
						success : function(data) {

							var result;
							var id;
							var userName;
							var userMobile;
							var zlenCode;
							var deviceType;
							var age;
							var gender;
							var createdOn;
							var frnds_count;
							debugger
							$(data)
									.each(
											function(index, ele) {
												//                  	alert('ele===>'+ele);
												id = ele.id;
												userName = ele.userName;
												userMobile = ele.userMobile;
												zlenCode = ele.zlenCode;
												deviceType = ele.deviceType;
												age = ele.age;
												gender = ele.gender;
												frnds_count = ele.frnds_count;
												//createdOn = ele.createdOn
												var date = new Date(
														ele.createdOn);
												createdOn = date
														.toDateString("yyyy-MM-dd");

												result += "<tr><td>"
														+ index
														+ "</td><td>"
														+ createdOn
														+ "</td><td>"
														+ ele.userName
														+ "</td><td>"
														+ ele.age
														+ "</td><td>"
														+ ele.gender
														+ "</td><td>"
														+ ele.userMobile
														+ "</td><td><a href='${pageContext.request.contextPath}/userViewForm/"+ele.id +
                    " ' class='btn btn-info btn-sm showData'>"
														+ ele.zlenCode
														+ "</a></td><td><a href='${pageContext.request.contextPath}/friendList/"+ele.id+
									                    "' class='btn btn-primary  btn-sm showData'>"
														+ ele.frnds_count
														+ "</a></td><td>"
														+ ele.deviceType
														+ "</td><td>"+ele.isbanned+"</td></tr>";
// 														<td><a href='${pageContext.request.contextPath}/friendList/"+ele.id+
//                     "' class='btn btn-primary  btn-sm showData'><i class='fas fa-user-friends' style='font-size: 20px; color: #ffff;'></i></a>&nbsp;</td>


											});

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

			debugger
			window.location.href = '${pageContext.request.contextPath}/userDetailsList/1';
		}
	</script>


	<script type="text/javascript">
		$(document).ready(function() {

		});
		function download() {
			var userDetails;
			userDetails = {}
			userDetails["userName"] = $('#inputName').val();
			userDetails["userMobile"] = $('#inputMobile').val();
			userDetails["zlenCode"] = $('#inputCode').val();
			userDetails["deviceType"] = $('#inputType').val();
			userDetails["gender"] = $("#inputGender").val();
			userDetails["age"] = $("#inputAge").val().split(',')[0];
			userDetails["age1"] = $("#inputAge").val().split(',')[1];
			userDetails["friendNumber"] = $("#inputFrndCount").val().split(',')[0];
			userDetails["friendNumber1"] = $("#inputFrndCount").val().split(',')[1];

			$('#btn-download').prop("disabled", false);
			$.ajax({
				type : "GET",
				//contentType: "application/json",
				url : "${pageContext.request.contextPath}/userDetailsDownload",
				//timeout: 4000,
				// success:function(result)
				data : userDetails,
				cache : false,
				xhr : function() {
					var xhr = new XMLHttpRequest();
					xhr.onreadystatechange = function() {
						if (xhr.readyState == 2) {

							if (xhr.status == 200) {
								xhr.responseType = "blob";
							} else {
								xhr.responseType = "text";
							}
						}
					};
					return xhr;
				},
				success : function(data) {
					//alert("data==>"+ data);

					//var newDate = dateFormat(cdate, "mm/dd/yyyy");
					debugger

					var a = document.createElement('a');
					var url1 = window.URL.createObjectURL(data);
					a.href = url1;
					a.download = 'UserDetails.xls';
					document.body.append(a);
					a.click();
					a.remove();
					window.URL.revokeObjectURL(url);

					//bindFunction();
					return;
					alert(ele.success);

					/* var fileName='test.xlsx';
					var blob = new Blob([data], { type: "application/vnd.ms-excel" });
					 
					//Check the Browser type and download the File.
					var isIE = false || !!document.documentMode;
					if (isIE) {
					    window.navigator.msSaveBlob(blob, fileName);
					} else {
					    var url = window.URL || window.webkitURL;
					    link = url.createObjectURL(blob);
					    var a = $("<a />");
					    a.attr("download", fileName);
					    a.attr("href", link);
					    $("body").append(a);
					    a[0].click();
					    $("body").remove(a);
					}
					 */},
				error : function(result) {
					//alert(data);
					alert(result.status + ' ' + result.statusText);
				}

			});
		}
	</script>

</body>
<jsp:include page="otherModal.jsp"></jsp:include>
</html>



