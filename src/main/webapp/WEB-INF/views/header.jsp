 <style>
   .nav-link {
  cursor: pointer;
}

.table-responsive {
    max-height:350px;
}
.table td, .table th {
    padding: .25rem !important;
}
.sticky-footer{
	padding: .5rem !important;
}
.sidebar .nav-item .nav-link {
    padding: .5rem 1rem; 
}

 </style>
 <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
    
      <!-- Sidebar - Brand -->
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="${pageContext.request.contextPath}/dashboard">
        <div class="sidebar-brand-icon rotate-n-15">
          <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3">Zlen </div>
      </a>
 
      <!-- Divider -->
      <hr class="sidebar-divider my-0">

       <!-- Nav Item - Dashboard -->
      <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath}/dashboard">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Dashboard</span></a>
      </li>
      
      <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath}/user">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>User Master</span></a>
      </li>
      
      <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath}/role">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Role Master</span></a>
      </li>
      
<%--       <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath}/stories">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>User Post</span></a>
      </li>
       --%>
     <li class="nav-item active">
       <a class="nav-link" href="${pageContext.request.contextPath}/userDetailsList/1"> 
         <i class="fas fa-fw fa-tachometer-alt"></i>
         <span>User List</span></a>
     </li>
     
      <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath}/userStoriesList">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Post List</span></a>
      </li>
	  
	   <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath}/activitylog">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>User Activity</span></a>
      </li>
      
      <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath}/userFeedBackList">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Feedback</span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath}/pendingRegistration">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Pending Registration</span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath}/inActive ">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Inactive Users</span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath}/notification">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Notification</span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath}/bannerList">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Banners</span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath}/activeUserDashboard">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Active User Dashboard</span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath}/pollList">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Poll</span></a>
      </li>
       <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath}/reportedPostList">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Reported Post</span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath}/reportedpollList">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Reported Poll</span></a>
      </li>
       <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath}/trendingHashTaglist">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Trending HashTag</span></a>
      </li>
      
      
      
      <!-- Divider -->
      <hr class="sidebar-divider d-none d-md-block">

      <!-- Sidebar Toggler (Sidebar) -->
      <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
      </div>

    </ul>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- Topbar -->
        <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

          <!-- Sidebar Toggle (Topbar) -->
          <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
            <i class="fa fa-bars"></i>
          </button>
			
			 
          <!-- Topbar Navbar -->
          <ul class="navbar-nav ml-auto">

            <!-- Nav Item - User Information -->
            <li class="nav-item dropdown no-arrow">
              <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span class="mr-2 d-none d-lg-inline text-gray-600 small"> ${sessionScope.sessionUser.user_name}
                </span>
                <img class="img-profile rounded-circle" src="${pageContext.request.contextPath}/resources/img/profile.png">
              </a>
              <!-- Dropdown - User Information -->
              <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
               
               	<a class="dropdown-item" href="#" data-toggle="modal" data-target="#changePasswordModal">
                  <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                  Change Password
                </a>
                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                  <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                  Logout
                </a>
               <!--    <a class="dropdown-item" href="">
                  <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                  User Master
                </a>
                <a class="dropdown-item" href="/role">
                  <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                  Role Master
                </a>	-->
              </div>
            </li>
          </ul>
        </nav>
        <!-- End of Topbar -->

<script type="text/javascript" src="~/lib/bootstrap/dist/js/bootstrap.min.js"></script> 
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>      
      
      <script>
      function bindFunction (){
    	 
    	  $(".showData").off('click').click(function (event){
    		  debugger
  	      	var modal = $("#dataModal");
  	      	
  	      	event.preventDefault();
  	      	var get = $(this).attr('href');
  	      	 $.ajax({
  	      	        type: "GET",
  	      	        url: get,
  	                  success:function(data){
  	      	            	modal.find(".modal-body").html(data);
  	      	            	$("#dataModal").modal('show');
  	      	        }
  	      	 });
  	      });
  	      
  	      $(".img-view").off('click').click(function (event){
  	    	  var modal = $("#dataModal");
  	          event.preventDefault();
  	          var src = $(this).attr('src');
  	          
  	          var tmp = "<img class='img-fluid' src='"+src+"' />" ;
  	          console.log(tmp);
  	          modal.find(".modal-body").html(tmp);
  	          $("#dataModal").modal('show');
  	      });
      }
      $(document).ready(function () {
    	  bindFunction();
      });
      </script>