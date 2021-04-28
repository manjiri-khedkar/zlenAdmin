		<!DOCTYPE html>  
		<html>  
		<head>  
		<meta name="viewport" content="width=device-width, initial-scale=1">  
		<style>  
		body{  
		  font-family: Calibri, Helvetica, sans-serif;  
		  
		}  
		.container {  
		    padding: 50px;  
		  background-color: lightblue;  
		}  
		  
		input[type=text], input[type=password], textarea {  
		  width: 100%;  
		  padding: 15px;  
		  margin: 5px 0 22px 0;  
		  display: inline-block;  
		  border: none;  
		  background: #f1f1f1;  
		}  
		input[type=email] 
		{  
		 width: 100%;  
		  padding: 15px;  
		  margin: 5px 0 22px 0;  
		  display: inline-block;  
		  border: none;  
		  background: #f1f1f1;  
		}
		
		
		input[type=text]:focus, input[type=password]:focus {  
		  background-color: orange;  
		  outline: none;  
		}  
		 div {  
		            padding: 10px 0;  
		         }  
		hr {  
		  border: 1px solid #f1f1f1;  
		  margin-bottom: 25px;  
		}  
		.registerbtn {  
		  background-color: #4CAF50;  
		  color: white;  
		  padding: 16px 20px;  
		  margin: 8px 0;  
		  border: none;  
		  cursor: pointer;  
		  width: 100%;  
		  opacity: 0.9;  
		}  
		.registerbtn:hover {  
		  opacity: 1;  
		}  
		</style>  
		</head> 
		<body> 
	 <div class="container">	
		 
		
		
		 
		<form action="/edituser" method="post">  
		  <div class="container">  
		  <center>  <h1>  User Add Form</h1> </center>  
		  <hr>  
		  <label> Firstname </label>   
		<input type="text" name="firstName" placeholder= "Firstname" size="15" required />   
		<label> Middlename: </label>   
		<input type="text" name="middlename" placeholder="Middlename" size="15" required />   
		<label> Lastname: </label>    
		<input type="text" name="lastName" placeholder="Lastname" size="15"required />   
		
		<label for="email"> UserId </label>    
		<input type="email" name="userId" placeholder="Enter Email" size="15"required /> 
		
		<label> Age </label>    
		<input type="text" name="age" placeholder="age" size="3"required />   
		<label>   
		Mobile No :  
		</label>  
		<input type="text" name="country code" placeholder="Country Code"  value="+91" size="2"/>   
		<input type="text" name="mobileNo" placeholder="phone no." size="10" />   
		
		 <label> Address </label>   
		<input type="text" name="address" placeholder= "Enter address" size="25" required /> 
		 
		 <label for="email">Email</label>  
		 <input type="email" placeholder="Enter Email" name="email" required>  
		 
		 <label> Aadhar Card No. </label>   
		<input type="text" name="aadhaar" placeholder= "Enter Number" size="10" required /> 
		  
		 <label for="psw"><b>Password</b></label>  
		 <input type="password" placeholder="Enter Password" name="password" required>  
		  
		  <label for="psw-repeat"><b>Re-type Password</b></label>  
		  <input type="password" placeholder="Retype Password" name="psw-repeat" required> 
		    
		  <label>
		  Date:
		  <input type="date" name="date">
		  </label>
		    
	   <div>					
		   <label >Gender</label> 
		   <select class="form-control"  name="gender" >
		   <option value="'Male'" >Male</option>
		   <option value="'Female'">Female</option>
		   </select>
		</div>								
		</div> 
		    <button type="submit" class="registerbtn">ADD User</button>    
		</form>
		    </div>
		</body>  
		
		</html>  