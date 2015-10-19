<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    %>
<%! String message = ""; %>    
<% message = request.getParameter("message"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/main.css">
<script></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login - Travel Experts</title>
</head>
<body>
	<jsp:include page="/header.jsp"/>    <br/>
	<div class="container">
        <div class="row">
            <div class="box">
                <div class="col-lg-12 text-center">
	<h1 align="center">Welcome to Travel Experts</h1>
	<h4 style="color:red">
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
	</h4>
	
	<div class="container">
				    <div class="row">
				        <div class="col-md-6 col-md-offset-3">
				            <div class="well well-sm">
				              <form name="login" id="login" action="CustomerServlet" method="post" style="height: 294px;" class="form-horizontal">
								<input type="hidden" name="pagename" value="login"/>  
				                    <fieldset>
				                        <legend class="text-center header">Login</legend>
				                        <div class="form-group">
				                            <div class="col-md-10 col-md-offset-1">
				                            User Name:<span class="mandatory"> * </span>
				                                <input type="text" name="txtUserName"  class="form-control" /> 
				                                <span id="usererr" class="mandatory"></span>
				                            </div>
				                        </div>
				                        
				                        <div class="form-group">
				                            <div class="col-md-10 col-md-offset-1">
				                            Password:<span class="mandatory"> * </span>
				                                <input type="password" name="txtPassword"  class="form-control" /> 
				                                <span id="passworderr" class="mandatory"></span>
				                            </div>
				                        </div>
				                        
				                         <div class="form-group">
				                            <div class="col-md-12 text-center">
				                                <button type="submit" value="Login" onclick="return validateForm();" class="btn btn-primary btn-lg">Login</button>
				                            </div>
				                          <br/><a href="register.jsp" align="center">New User? Register Now!</a>
				                        </div>
				                    </fieldset>
				             	</form>
				             </div>
				          </div>
				        </div>
				      </div>
				      
				                      
		<!-- <table align="center" style="width: 389px; height: 228px; ">
			<tr>
				<td><label><b>UserName <span class="mandatory"> * </span></b></label>  </td>
				<td><input type="text" name="txtUserName" />
					<span id="usererr" class="mandatory"></span>
				</td>
			</tr>
			<tr>
				<td> <label><b>Password <span class="mandatory"> * </span></b></label></td>
				<td><input type="password" name="txtPassword"/>
					<span id="passworderr" class="mandatory"></span>
			</tr>
			<tr>
				<td colspan="2" align="center"><button type="submit" value="Login" onclick="return validateForm();" style="width: 69px; ">Login</button>
			</tr>
		</table> -->

	</div>
	</div>
	</div>
	</div>
	
	
	<script>
		function validateForm()
		{
			var errormsg = false;
			var usererror,passerror;
			var username = document.login.txtUserName.value;
			var password = document.login.txtPassword.value;
			if(username == "")
			{
				usererror = "UserName is required";
				document.getElementById("usererr").innerHTML = usererror;
				errormsg = true;
			}
			if(password == "" && password.length<6)
			{
				passerror = "Password is required";
				document.getElementById("passworderr").innerHTML = passerror;
				errormsg = true;
			}
			if(errormsg)
				return false;
			else
				return true;
		}
	</script>
</body>
<jsp:include page="/footer.jsp"/>

</html>