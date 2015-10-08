<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="customer.Customer,customer.CustomerDB"%>
    
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/business-casual.css" rel="stylesheet">

    <!-- Fonts -->
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic" rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<body>


    <div class="brand"><img src="img/logo.png" width="80" height="80"/>Travel Experts</div>
    <div class="address-bar"> 1155 8th Ave SW | Calgary, AB T2P1N3 | 403.271.9873</div>

 <!-- Navigation -->
    <nav class="navbar navbar-default" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <!-- navbar-brand is hidden on larger screens, but visible when the menu is collapsed -->
                <a class="navbar-brand" href="index.jsp">Travel Experts</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="viewCustDetail.jsp">View Details</a>
                    </li>
                    <li>
                        <a href="editCustDetail.jsp">Edit Details</a>
                    </li>
                    <li>
                        <a href="bookingDetails.jsp">Booking</a>
                    </li>
                    <li>
                        <a href="contact.jsp">Contact</a>
                    </li>
                    <li>
                    	<a href="login.jsp">Login</a>
                    </li>
                    <li> 
                    	<a href="logout.jsp" > Logout </a>
                    </li> 
                 <%--  <%
                  String loginStatus = (String)session.getAttribute("loginStatus");
				  if((loginStatus != null) && (loginStatus.equalsIgnoreCase("true")))
     			 { 
     			 %>
					   <li> <a href="logout.jsp" > Logout </a></li>
                 <% 
                 }
                 else
                 { 
                 %>
                   <li><a href="login.jsp">Login</a></li>
                 <% 
                 } %> --%>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
    
    
</body>
</html>


