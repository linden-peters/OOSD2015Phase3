<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*,customer.Customer,customer.CustomerDB"%>
   <%
     String loginStatus = (String)session.getAttribute("loginStatus");
     
    if(loginStatus == null)
    {
    	response.sendRedirect("login.jsp");
    }
    else
    {
     	Integer custId = (Integer)session.getAttribute("CustomerId");
     	int customerId = custId.intValue();
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/header.jsp"/>    <br/>
	<%
	if((loginStatus != null) && (loginStatus.equals("true")))
    {
    	Customer cust = CustomerDB.getCustomer(customerId);
  
     	
    %>
	
	<div class="container">
        <div class="row">
            <div class="box">
                <div class="col-lg-12 text-center">
	
     <%--  Hello <%=cust.getCustFirstName() + " " + cust.getCustLastName()%>! --%>
     <!-- <form name="editCustDetail" method="post" action="CustomerServlet">
       <input type="hidden" name="pagename" value="editCustDetail"/>
        -->
				  <div class="container">
				    <div class="row">
				        <div class="col-md-6 col-md-offset-3">
				            <div class="well well-sm">
				               <form name="editCustDetail" method="post" action="CustomerServlet"  class="form-horizontal">
				      			 <input type="hidden" name="pagename" value="editCustDetail"/>
				                    <fieldset>
				                        <legend class="text-center header">Update Information</legend>
				                        <div class="form-group">
				                            <div class="col-md-10 col-md-offset-1">
				                            First Name
				                                <input type="text" name="fname" value="<%=cust.getCustFirstName() == null? "" : cust.getCustFirstName() %>" class="form-control" /> 
				                            </div>
				                        </div>
				                         <div class="form-group">
				                            <div class="col-md-10 col-md-offset-1">
				                            	Last Name:
				                                <input type="text" name="lname" value="<%=cust.getCustLastName() == null ? "" : cust.getCustLastName() %>"  class="form-control">
				                            </div>
				                        </div>
				
				                        <div class="form-group">
				                            <div class="col-md-10 col-md-offset-1">
				                            	Address:
				                                <input type="text" name="address" value="<%=cust.getCustAddress() == null ? "" : cust.getCustAddress() %>" class="form-control">
				                            </div>
				                        </div>
				
				                        <div class="form-group">
				                            <div class="col-md-10 col-md-offset-1">
				                            City:
				                                <input type="text" name="city" value="<%=cust.getCustCity() == null ? "" : cust.getCustCity() %>" class="form-control">
				                            </div>
				                        </div>
				                        
				                         <div class="form-group">
				                            <div class="col-md-10 col-md-offset-1">
				                            Province:
				                                <input type="text" name="province" value="<%=cust.getCustProv() == null ? "" : cust.getCustProv() %>" class="form-control">
				                            </div>
				                        </div>
				                        
				                        <div class="form-group">
				                            <div class="col-md-10 col-md-offset-1">
				                            Postal:
				                                <input type="text" name="postal" value="<%=cust.getCustPostal() == null ? "" : cust.getCustPostal() %>" class="form-control">
				                            </div>
				                        </div>
				                        
				                         <div class="form-group">
				                            <div class="col-md-10 col-md-offset-1">
				                            Country:
				                                <input type="text" name="country" value="<%=cust.getCustCountry() == null ? "" : cust.getCustCountry() %>" class="form-control">
				                            </div>
				                        </div>
				                        
				                        <div class="form-group">
				                            <div class="col-md-10 col-md-offset-1">
				                            Home Phone:
				                                <input type="text" name="homephone" value="<%=cust.getCustHomePhone() == null ? "" : cust.getCustHomePhone() %>" class="form-control">
				                            </div>
				                        </div>
				                        
				                        <div class="form-group">
				                            <div class="col-md-10 col-md-offset-1">
				                            Business Phone:
				                                <input type="text" name="busphone" value="<%=cust.getCustBusPhone() == null ? "" : cust.getCustBusPhone() %>" class="form-control">
				                            </div>
				                        </div>
				
				                       <div class="form-group">
				                            <div class="col-md-10 col-md-offset-1">
				                            Email Address:
				                                <input type="text" name="email" value="<%=cust.getCustEmail() == null ? "" : cust.getCustEmail() %>" class="form-control">
				                            </div>
				                        </div>
				                        
				                        <div class="form-group">
				                            <div class="col-md-10 col-md-offset-1">
				                            Agent ID:
				                               <input type="text" name="agentid" value="<%=cust.getAgentId() == 0 ? 0 : cust.getAgentId() %>" class="form-control">
				                            </div>
				                        </div>
				                        
				                         <div class="form-group">
				                            <div class="col-md-10 col-md-offset-1">
				                            User Name:
				                                <input type="text" name="username" value="<%=cust.getUserName() == null ? "" : cust.getUserName() %>" readonly="readonly" class="form-control">
				                            </div>
				                        </div>
				                        
				                        <div class="form-group">
				                            <div class="col-md-10 col-md-offset-1">
				                            Password:
				                                 <input type="password" name="password" value="<%=cust.getPassword() == null ? "" : cust.getPassword() %>" class="form-control">
				                            </div>
				                        </div>
				
				                        <div class="form-group">
				                            <div class="col-md-6 col-md-offset-3 text-center">
				                               <input type="submit" name="update" value="Update" class="btn btn-primary btn-lg" align="left" />
				                             &nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
				                               <input type="reset" name="reset" value="Reset" class="btn btn-primary btn-lg" align="right"/>
				                            </div>
				                        </div>
				                    </fieldset>
				                </form>
				                <% 
								 	}
								 	}
								 %>
																
				                
				            </div>
				        </div>
				       </div>
				      </div>
      <!-- Form end -->
       			</div>
       		</div>
       	</div>
      </div>	
      <!-- container end -->
      
</body>
<jsp:include page="/footer.jsp"/>
</html>