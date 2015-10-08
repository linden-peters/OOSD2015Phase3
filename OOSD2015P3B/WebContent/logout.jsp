<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    session.removeAttribute("loginStatus");
    session.removeAttribute("CustomerId");
    session.invalidate();
    
     %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to Travel Experts</title>
</head>
<body>
	<jsp:include page="/header.jsp"/>
	<div class="container">
        <div class="row">
            <div class="box">
                <div class="col-lg-12 text-center">
	<h1> You have successfully logged out.</h1>
	 <div class="form-group">
		 <div class="col-md-12 text-center">
			<button type="submit" onclick="login.jsp" class="btn btn-primary btn-lg">Login Here!</button>
		 </div>
	</div>
	
	<!-- <a href="login.jsp">Login Here!</a> -->
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/footer.jsp"/>
</body>
</html>