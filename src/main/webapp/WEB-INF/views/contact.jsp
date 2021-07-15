<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!--====== Section Start ======-->
<section class="login min-height">
	<div id="wrapper">
			<div class="container">
				<div class="clearfix">&nbsp;</div>                        
        			<form:form action="${pageContext.request.contextPath}/contactlist/{id}" id="table1" method="post"  modelAttribute="ContactList"> 
       
			   			<div class="panel-heading">
							<h2 class="text-center" style="color: black;"><b>Contact Details</b></h2>
						</div><br/>

				<div class="panel-body">
				 	<div class="container">
						<div class="row">
							<div class="col">
								<form:label path="created_at" class="control-label" id="inputCreatedDate"><b>Created Date :</b></form:label>&nbsp;
								<c:out value="${ContactList.created_at}" />
							</div>
							<div class="col">
								<form:label path="updated_at" class="control-label" id="inputUpdatedDate"><b>Updated Date :</b></form:label>&nbsp;
								<c:out value="${ContactList.updated_at}" />
							</div>
							<div class="col">
								<form:label path="user_mobile" class="control-label" id="inputMobile"><b>Mobile No :</b></form:label>&nbsp;
								<c:out value="${ContactList.user_mobile}" />
							</div>
						</div>
						<div class="row">
				<div class="col-md-12">
					<div class="table-responsive">
						<table id="table1" class="table info-tbl text-left" style='border: 1px solid #d3d3d3;width: 98% !important; '>
							<thead>
								<tr>
								    <th class="text-left" style="background: #d3d3d3">Sr.No.</th>
									<th class="text-left" style="background: #d3d3d3">Name</th>
									<th class="text-left" style="background: #d3d3d3">Mobile</th>
									
								</tr>
							</thead>
							
 							<tbody> 
                        		<c:forEach items="${contacts}" var="list" varStatus="status"> 
 	                        		<tr class="odd gradeX"> 
	                        		
 	                        			<td><c:out value="${status.index+1}" /></td>
 	                        			<td><c:out value="${list.name}" /></td> 
	 	                        		<td><c:out value="${list.phoneNumber}" /></td>
	 	                        		 
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
		
<!-- 		<div class="row">
				<div class="col-md-12">
					<div class="table-responsive">
						<table id="table1" class="table info-tbl text-left" style='border: 1px solid #d3d3d3;width: 98% !important; '>
							<thead>
								<tr>
								    <th class="text-left" style="background: #d3d3d3">Sr.No.</th>
									<th class="text-left" style="background: #d3d3d3">Created Date</th>
									<th class="text-left" style="background: #d3d3d3">Updated Date</th>
									<th class="text-left" style="background: #d3d3d3">Mobile No</th>
								</tr>
							</thead>
							
 							<tbody> 
<%--                         		<c:forEach items="${ContactList}" var="list" varStatus="status">  --%>
 	                        		<tr class="odd gradeX"> 
	                        		
 	                        			<td><c:out value="${status.index+1}" /></td>
 	                        			<td><c:out value="${ContactList.created_at}" /></td> 
	 	                        		<td><c:out value="${ContactList.updated_at}" /></td>
	 	                        		<td><c:out value="${ContactList.user_mobile}" /></td> 
 	                       			</tr> 
<%--  	                    		</c:forEach>  --%>
                           </tbody>
						</table>
					</div>
				</div>		
			</div>   -->
	
		
	</div>					  
</section> 
