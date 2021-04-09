
  <!-- Logout Modal-->
  <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
        <div class="modal-footer">
          <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
          <a class="btn btn-primary" href="login.html">Logout</a>
        </div>
      </div>
    </div>
  </div>
  
    <!-- Logout Modal-->
  <div class="modal fade" id="changePasswordModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Change Password</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">
        	<div class="row">
        		Old Password: <input required id="oldpass" name="oldpassword" type="password" class="form-control form-control-user"/>
        	</div>
      		  <div class="row">
   				new Password: <input require id="pass" name="password" type="password" class="form-control form-control-user" />
   			 </div>
   			 <div class="row">
   				 Confirm Password:
   			 	<input require id="passConfirm" type="password" class="form-control form-control-user" />              
    		</div>
    		 <span id="error" style="display:none" class="form-control form-control-user">Password mismatch</span>
        </div>
        <div class="modal-footer">
        	<button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
          <button  class="btn btn-primary" type="submit" onclick="savePass()">Change Password</button>
        </div>
      </div>
    </div>
  </div>
  
  <div class="modal fade" id="lotReportUtilization" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Subscription</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">
        	<div class="row">
        		You have already used the number of lot reports allowed to you. To get more lot reports, please subscribe to higher version. Please contact on 8989 000 400, 7000 384 248 
        		
		              <div class="table-responsive">
		                <table class="table table-bordered" id="" width="100%" cellspacing="0">
			                <thead>
			                <tr>
			                	<td> Subscription Plan</td>
			                	<td> Lot Reports Allowed</td>
			                	<td> Price </td>
			                </tr>
			                </thead>
			                <tbody>
			                	<tr>
			                		<td> Free </td>
			                		<td> 2</td>
			                		<td> 0/-</td>
			                	</tr>
			                	<tr>
			                		<td> Silver </td>
			                		<td> 10 </td>
			                		<td> 500/-</td>
			                	</tr>
			                	<tr>
			                		<td> Gold </td>
			                		<td> 100 </td>
			                		<td> 2500/-</td>
			                	</tr>
			               		 <tr>
			                		<td> Platinum </td>
			                		<td> All </td>
			                		<td> 5000/-</td>
			                	</tr>
			                </tbody>
		                </table>
		                
		                
		           
	            </div>
        	</div>
        </div>
        <div class="modal-footer">
        	<button class="btn btn-secondary" type="button" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>
  
     
  
   