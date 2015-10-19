<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.sql.*,customer.Customer,customer.CustomerDB"%>
<%
     String loginStatus = (String)session.getAttribute("loginStatus");
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Details - Travel Experts</title>
</head>
<body>
	<jsp:include page="/header.jsp"/>    <br/>
	<div class="container">
        <div class="row">
            <div class="box">
                <div class="col-lg-12 text-center">
	<%
	if((loginStatus != null) && (loginStatus.equals("true")))
    {
    	Integer custId = (Integer)session.getAttribute("CustomerId");
    	int customerId = custId.intValue();
    	Customer cust = CustomerDB.getCustomer(customerId);
     	
      %>
			<div class="container">
				    <div class="row">
				        <div class="col-md-6 col-md-offset-3">
				            <div class="well well-sm">
				                <form class="form-horizontal" method="post" action="editCustDetail.jsp">
				                    <fieldset>
				                        <legend class="text-center header">Hello, <%=cust.getCustFirstName() + " " + cust.getCustLastName() %></legend>
				                        <div class="form-group">
				                            <div class="col-md-10 col-md-offset-1">
				                            First Name:
				                                <input id="fname" value="<%=cust.getCustFirstName() == null? "" : cust.getCustFirstName() %>" class="form-control" readonly="readonly">
				                            </div>
				                        </div>
				                        <div class="form-group">
				                            <div class="col-md-10 col-md-offset-1">
				                            	Last Name:
				                                <input id="lname" value="<%=cust.getCustLastName() == null ? "" : cust.getCustLastName() %>"  class="form-control" readonly="readonly">
				                            </div>
				                        </div>
				
				                        <div class="form-group">
				                            <div class="col-md-10 col-md-offset-1">
				                            	Address:
				                                <input id="address" value="<%=cust.getCustAddress() == null ? "" : cust.getCustAddress() %>" class="form-control" readonly="readonly">
				                            </div>
				                        </div>
				
				                        <div class="form-group">
				                            <div class="col-md-10 col-md-offset-1">
				                            City:
				                                <input id="city" value="<%=cust.getCustCity() == null ? "" : cust.getCustCity() %>" class="form-control" readonly="readonly">
				                            </div>
				                        </div>
				                        
				                         <div class="form-group">
				                            <div class="col-md-10 col-md-offset-1">
				                            Province:
				                                <input id="province" value="<%=cust.getCustProv() == null ? "" : cust.getCustProv() %>" class="form-control" readonly="readonly">
				                            </div>
				                        </div>
				                        
				                        <div class="form-group">
				                            <div class="col-md-10 col-md-offset-1">
				                            Postal:
				                                <input id="postal" value="<%=cust.getCustPostal() == null ? "" : cust.getCustPostal() %>" class="form-control" readonly="readonly">
				                            </div>
				                        </div>
				                        
				                         <div class="form-group">
				                            <div class="col-md-10 col-md-offset-1">
				                            Country:
				                                <input id="country" value="<%=cust.getCustCountry() == null ? "" : cust.getCustCountry() %>" class="form-control" readonly="readonly">
				                            </div>
				                        </div>
				                        
				                        <div class="form-group">
				                            <div class="col-md-10 col-md-offset-1">
				                            Home Phone:
				                                <input id="homephone" value="<%=cust.getCustHomePhone() == null ? "" : cust.getCustHomePhone() %>" class="form-control" readonly="readonly">
				                            </div>
				                        </div>
				                        
				                        <div class="form-group">
				                            <div class="col-md-10 col-md-offset-1">
				                            Business Phone:
				                                <input id="busphone" value="<%=cust.getCustBusPhone() == null ? "" : cust.getCustBusPhone() %>" class="form-control" readonly="readonly">
				                            </div>
				                        </div>
				
				                       <div class="form-group">
				                            <div class="col-md-10 col-md-offset-1">
				                            Email Address:
				                                <input id="email" value="<%=cust.getCustEmail() == null ? "" : cust.getCustEmail() %>" class="form-control" readonly="readonly">
				                            </div>
				                        </div>
				                        
				                        <div class="form-group">
				                            <div class="col-md-10 col-md-offset-1">
				                            Agent ID:
				                                <input id="agentid" value="<%=cust.getAgentId() == 0 ? 0 : cust.getAgentId() %>" class="form-control" readonly="readonly">
				                            </div>
				                        </div>
				                        
				                         <div class="form-group">
				                            <div class="col-md-10 col-md-offset-1">
				                            User Name:
				                                <input id="username" value="<%=cust.getUserName() == null ? "" : cust.getUserName() %>" class="form-control" readonly="readonly">
				                            </div>
				                        </div>
				
				                        <div class="form-group">
				                            <div class="col-md-12 text-center">
				                                <button type="button" onclick="javascript:location.href='editCustDetail.jsp'" class="btn btn-primary btn-lg">Edit</button>
				                            </div>
				                        </div>
				                    </fieldset>
				                </form>
				                <%  
				                }
				     			else
				     			{
				    			 	response.sendRedirect("login.jsp");
				     			}
								%>
				                
				            </div>
				        </div>
					</div>
				</div>
	<!-- form ends -->
			</div>
		</div>
	</div>
</div>
	<!-- container ends -->
</body>
<jsp:include page="/footer.jsp"/>
</html>