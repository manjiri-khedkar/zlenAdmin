<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
 <%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  --%>
<html>
<head>
<title>Student Information</title>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<style>
		
		body{
		  background-color:lightblue;
			
		}
		
		.container{
		  background-color:white;
		  padding:50px;
		  
		  border-radius:15px 50px;
		  align:center;

		}
		.btn {
  		 background-color: DodgerBlue;
 		 border: none;
 		 color: #fff;
 		 padding: 12px 16px;
 		 font-size: 16px;
 		 cursor: pointer;
		}

		

		.btn.btn-primary {
  		  background-color: #4e5f81 !important;
 		  border-color: #4e5f81 !important;

		}

		.btn + .btn {
  		margin-left: 15px;
		}
		

	</style>
</head>
<body>
<body>
	<div class="container">
	<%-- <div class="row">
                <div class="col-md-12 ">
					<div if="${param.success}">
						<div class="alert alert-info">You've Successfully Added New Record.</div>
					</div>
					<div if="${param.editSuccess}">
						<div class="alert alert-info">You've Successfully Edited Existing 						Record.</div>
					</div>
				<div if="${param.deleteSuccess}">
				<div class="alert alert-info">You've Successfully Deleted Existing 								Record.</div>
					</div>
			   </div>
            </div> --%>
           <div class="row">
	         <div class="col-md-12">
			<center> <h1>User Form</h1> </center>
	         <a href="/user/adduser"><button type="button" name="b10" class="btn btn-primary">Add</button></a>
           	 </div>
          </div>
          <br>
          <div class="row">
	<div class="col-md-12">
		<div class="table-responsive">
		<table id="table1" class="table info-tbl text-left" style='border: 1px solid #d3d3d3;width: 98% !important; '>
			<thead class="thead-dark">
			<tr>
				<th >User ID</th>
				<th >First Name</th>
				<th >Last Name</th>
				<th >User ID</th>
				<th >Age</th>
				<th >Mobile No</th>
				<th >Edit/Delete</th>
			</tr>
			</thead>
			  <!--  app_user is a database table name -->
			<c:forEach var="app_user" items="${AppUserlist}">
			
				<tr>
					<td><c:out value="${app_user.id}" /></td>
					<td><c:out value="${app_user.firstName}" /></td>
					<td><c:out value="${app_user.lastName}" /></td>
					<td><c:out value="${app_user.userId}" /></td>
					<td><c:out value="${app_user.age}" /></td>
					<td><c:out value="${app_user.mobile}" /></td>
					<td><a href="/edituser?id=${app_user.id}"><button class="btn btn-primary">
					<i class="fa fa-edit"></i></button></a>&nbsp;
					<a href="/delete?id=${app_user.id}" onclick="'return confirm(\'Are you sure to Delete Record?\');'" >
					<button class="btn btn-primary" >
					<i class="fa fa-trash"></i></button></a></td>

				</tr>
			</c:forEach>
		</table>
		</div>
		</div>
	</div>
	</div>



	
</body>
</html>