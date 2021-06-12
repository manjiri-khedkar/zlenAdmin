
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    


<section class="login min-height">
	<div id="wrapper">
		
			<div class="container">
				<div class="clearfix">&nbsp;</div>                        
       				<form:form action="${pageContext.request.contextPath}/friendList/{id}" id="table1" method="post"  modelAttribute="friendDetails">
       
			   			<div class="panel-heading">
							<h2 class="text-center" style="color: black;"><b>Friend Details</b></h2>
						</div><br/>
						
		<div class="panel-body">

				<div class="row">
						<div class="col-md-12">
							<div class="table-responsive">
								<table id="table1" class="table info-tbl text-left" style='border: 1px solid #d3d3d3;width: 98% !important; '>
									<thead>
										<tr>
										    <th class="text-left" style="background: #d3d3d3">Sr.No.</th>
											<th class="text-left" style="background: #d3d3d3">User ID</th>
											<th class="text-left" style="background: #d3d3d3">User Name</th> 
											<th class="text-left" style="background: #d3d3d3">Device Type</th>
											<th class="text-left" style="background: #d3d3d3">Zlen Code</th>
											<th class="text-left" style="background: #d3d3d3">Mobile No.</th>
											<th class="text-left" style="background: #d3d3d3">Latitude</th>
											<th class="text-left" style="background: #d3d3d3">Longitude</th>								
										</tr>
									</thead>
					
									<tbody>
										<c:forEach items="${friendDetails}"  var="userdetails" varStatus="status"> 
		 	                        		<tr class="odd gradeX"> 
												
												<td><c:out value="${status.index+1}" /></td> 
												<td><c:out value="${userdetails.id}" /></td> 
												<td><c:out value="${userdetails.userName}" /></td> 
												<td><c:out value="${userdetails.deviceType}" /></td>
												<td><c:out value="${userdetails.zlenCode}" /></td>
												<td><c:out value="${userdetails.userMobile}" /></td>
												<td><c:out value="${userdetails.latitude}" /></td>
												<td><c:out value="${userdetails.longitude}" /></td>
											</tr> 
		 	                    		</c:forEach> 
		                           </tbody>
								</table>
							</div>
						</div>		
					</div>
				</div>
			</form:form>
		</div>
	</div>
</section> 

