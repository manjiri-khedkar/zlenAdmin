		<!DOCTYPE html>  
		<html>  
		<head>  
		<meta name="viewport" content="width=device-width, initial-scale=1">  
		<style>  
		body{  
		  font-family: Calibri, Helvetica, sans-serif;  
		  
		}  
		.container {  
		   padding: 150px; 
			
		   border-radius: 25px; 
		   background-color:rgb(0, 128, 255) ;
		   opacity:0.5;
		     
		}  
 
		label{
		 
		 font-size:20px;
		 color:black;
		
		}
		
		input{
		 width:95%;
		 height:50px;
		 border-radius:20px;
		 text:center;
		}
		
		select{
		 width:95%;
		 height:50px;	
		 border-radius:20px;
		 text:center;
		}
		 
		hr {  
		  border: 1px solid blue;  
		  margin-bottom: 25px;  
		} 
		.row{
		  border:none;
		  border-radius: 15px 50px;
		  background-color:White;
		  color: black;  
		  padding: 50px;  
		  
		} 
		.registerbtn {
		  border-radius: 10px;
      		    
		  background-color: rgba(0, 0, 255, 0.3);  
		  color: black;  
		  padding: 16px;  
		  margin: 8px ;  
		  border: none;  
		  cursor: pointer;  
		  width: 50%; 
		  height:30% 
		  opacity: 0.3; 
		  
		}  
		.registerbtn:hover {  
		  opacity: 1;  
		}  
		</style>  
		</head> 
		<body> 
	 <div class="container">	
		 <div class="row">
		<div class="col-sm-6 mx-auto p-0 ">
        <div class="Register-space ">
    
		
		 
		<form action="/user" method="post"> 
			
  
		  <center>  <h1> <b> Registeration Form</b></h1> </center>
			
		  <hr>
		
		 <table class="table align-center " border=0 style="text-align:left; width:100%;">
		<tr>
			<td>
		  <label> Firstname : </label></td>  
		<td><input  type="text" name="firstName" placeholder= "Firstname"  required /></td>
		</tr>   
		<!-- <label> Middlename: </label>   
		<input type="text" name="middlename" placeholder="Middlename"  required />  --> 
 		<tr>
		<td><label> Lastname : </label></td>    
		<td><input type="text" name="lastName" placeholder="Lastname" required /></td>   
		</tr>

		<tr>
		<td><label for="email"> UserId : </label> </td>   
		<td><input type="text" name="userId" placeholder="Enter Email" required /></td> 
		</tr>

		<tr>
		<td><label> Age : </label></td>    
		<td><input type="text" name="age" placeholder="age" required /> </td>
		</tr>
		
		<tr>  
		<td><label>  Mobile No : </label></td>  
		<!-- <input type="text" name="country code" placeholder="Country Code"  value="+91" />  -->  
		<td><input type="text" name="mobileNo" placeholder="phone no."/></td>   
		</tr>

		<tr>
		<td><label> Address : </label></td>   
		<td><input type="text" name="address" placeholder= "Enter address"  required /></td>
		</tr>

		<tr>
		<td><label for="email">Email : </label></td>  
		<td><input type="text" placeholder="Enter Email" name="email" required /></td>
		</tr>  
		 
		<tr>
		<td><label> Aadhar Card No. : </label> </td>  
		<td><input type="text" name="aadhaar" placeholder= "Enter Number" required /></td>
		</tr>

		<tr> 
		<td><label for="psw"><b>Password : </b></label></td>  
		<td><input type="password" placeholder="Enter Password" name="password" required /></td>
		</tr>

		<tr> 
		<td><label for="psw-repeat"><b>Re-type Password : </b></label> </td> 
		<td><input type="password" placeholder="Retype Password" name="psw-repeat" required></td> 
		</tr>

		<tr>
		<td><label> Date :</label></td>
		<td><input type="date" name="date" placeholder="date"></td>
	   	</tr>
		
		<tr>				
		<td><label >Gender : </label></td> 
		<td><select class="form-control"  name="gender" placeholder="--Select Gender--"  >
		   <option >Select Gender</option>
		   <option value="'Male'" >Male</option>
		   <option value="'Female'">Female</option>
		   </select></td>
		</tr>
		
		<tr>
		<td colspan="2" style="text-align:center;"><br>
		<input style="font-size:15px;" type="submit" class="registerbtn" value="Register"/><br> </td>
		</tr>

		</table>   
		</form>
		</div>
		</div>
		</div>
		</div>
		</body>  
		
		</html>  