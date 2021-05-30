 <style>
   .nav-link {
  cursor: pointer;
}

 </style>
 <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
    
      <!-- Sidebar - Brand -->
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="${pageContext.request.contextPath}/dashboard">
        <div class="sidebar-brand-icon rotate-n-15">
          <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3">Infosane </div>
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
      
      <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath}/stories">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>User Stories</span></a>
      </li>
      
     <li class="nav-item active">
       <a class="nav-link" href="${pageContext.request.contextPath}/usersList"> 
         <i class="fas fa-fw fa-tachometer-alt"></i>
         <span>User List</span></a>
     </li>
     
      <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath}/userStoriesList">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Stories List</span></a>
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
		<%-- 	Lot Reports Utilized: ${sessionScope.sessionUser.lot_consumed}/${sessionScope.sessionUser.lot_allowed} --%>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="#" class="btn btn-secondary" data-toggle="modal" data-target="#id_lotList"  >Show List</a>
			 
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
      