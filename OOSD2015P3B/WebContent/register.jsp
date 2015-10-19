<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%! String message = ""; %>    
<% message = request.getParameter("message"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>
<script type="text/javascript" src="js/customerValidation.js"></script>
<link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>
	<jsp:include page="/header.jsp"/>    <br/>
	<div class="container">
        <div class="row">
            <div class="box">
                <div class="col-lg-12 text-center">

	<h5 style="color:red">
	<% 
	if(message == null)
	{
	  out.print("");
	}
	else 
	{
	  out.print(message);
	}
	%> 
	</h5>
			<div class="container">
				    <div class="row">
				        <div class="col-md-6 col-md-offset-3">
				            <div class="well well-sm">
				            
		<form name="regCustDetail" class="form-horizontal" id="regCustDetail" method="post" action="CustomerServlet">
       	<input type="hidden" name="pagename" value="registerCustDetail"/>
       	
       	<fieldset>
		<legend class="text-center header">Please enter your details</legend>
       	 <div class="form-group">
			<div class="col-md-10 col-md-offset-1">
			 User Name:
			 <input input type="text" name="username" class="form-control" >
			  </div>
		</div>
       	
       	<div class="form-group">
			<div class="col-md-10 col-md-offset-1">
				Password
				   <input type="password" name="password" class="form-control" />
       				<span class="error" id="passwordErr"></span>
			</div>
	    </div>
	    
	    <div class="form-group">
			<div class="col-md-10 col-md-offset-1">
				 First Name:
				<input type="text" name="fname" class="form-control"/>
       				<span class="error" id="fnameErr"></span>
		   </div>
		</div>
	    
		<div class="form-group">
			<div class="col-md-10 col-md-offset-1">
				 Last Name:
				<input type="text" name="lname" class="form-control" />
       			<span class="error" id="lnameErr"></span>
		   </div>
		</div>
				
		   <div class="form-group">
			 <div class="col-md-10 col-md-offset-1">
					Address:
				     <input type="text" name="address" class="form-control" />
       				<span class="error" id="addressErr"></span>
			</div>
		 </div>
       	
       	<div class="form-group">
			  <div class="col-md-10 col-md-offset-1">
			  City:
				<input type="text" name="city" class="form-control" />
       			<span class="error" id="cityErr"></span>
			 </div>
		</div>
		
		<div class="form-group">
			  <div class="col-md-10 col-md-offset-1">
			  Province:
				<input type="text" name="province" class="form-control" />
       			<span class="error" id="provinceErr"></span>
			 </div>
		</div>		                        
				                        
      <div class="form-group">
			  <div class="col-md-10 col-md-offset-1">
			  Postal:
				<input type="text" name="postal"  class="form-control"/>
       			<span class="error" id="postalErr"></span>
			 </div>
		</div>	
      
      <div class="form-group">
			  <div class="col-md-10 col-md-offset-1">
			  Country:
				<input type="text" name="country" class="form-control" />
       			<span class="error" id="countryErr"></span>
			 </div>
		</div>	
       			
       	<div class="form-group">
			  <div class="col-md-10 col-md-offset-1">
			  Home Phone:
				<input type="text" name="homephone" class="form-control"  />
       			<span class="error" id="homephoneErr"></span>
			 </div>
		</div>	
       	
       		<div class="form-group">
			  <div class="col-md-10 col-md-offset-1">
			  Business Phone:
				<input type="text" name="busphone" class="form-control" />
       			<span class="error" id="busphoneErr"></span>
			 </div>
		</div>	
       		
       	
       		<div class="form-group">
			  <div class="col-md-10 col-md-offset-1">
			  Email Address:
				<input type="text" name="email" class="form-control" />
       			<span class="error" id="emailErr"></span>
			 </div>
		</div>		
       		
       	<div class="form-group">
			  <div class="col-md-10 col-md-offset-1">
			  AgentId:
				<input type="text" name="agentid" class="form-control" />
       			<span class="error" id="agentidErr"></span>
			 </div>
		</div>	
       		
       	<div class="form-group">
			  <div class="col-md-6 col-md-offset-3 text-center">
				   <input type="submit" name="register" value="Register" onclick="return validateCustomer();" class="btn btn-primary btn-lg" align="left" />
				    &nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
				    <input type="button" name="clear" value="Clear" class="btn btn-primary btn-lg" align="right"/>
			  </div>
		</div>
		</fieldset>
       </form>
       
       		
       						</div>
       					</div>
       				</div>
       			</div>
       		</div>
 	   </div>
	</div>
</div>
       
</body>
<jsp:include page="/footer.jsp"/>
</html>