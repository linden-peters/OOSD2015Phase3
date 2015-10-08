<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="customer.CustomerDB, customer.Booking, customer.Package, customer.BookingDetail, java.util.List"%>
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
	     System.out.println("Customerid : " + customerId);
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Customer Profile</title>
<style>
@import url(http://fonts.googleapis.com/css?family=Roboto:400,500,700,300,100);

body {
  background-color: #3e94ec;
  font-family: "Roboto", helvetica, arial, sans-serif;
  font-size: 16px;
  font-weight: 400;
  text-rendering: optimizeLegibility;
}

div.table-title {
   display: block;
  margin: auto;
  max-width: 600px;
  padding:5px;
  width: 100%;
}

.table-title h3 {
   color: #fafafa;
   font-size: 30px;
   font-weight: 400;
   font-style:normal;
   font-family: "Roboto", helvetica, arial, sans-serif;
   text-shadow: -1px -1px 1px rgba(0, 0, 0, 0.1);
   text-transform:uppercase;
}


/*** Table Styles **/

.table-fill {
  background: white;
  border-radius:3px;
  border-collapse: collapse;
  height: 320px;
  margin: auto;
  max-width: 600px;
  padding:5px;
  width: 100%;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
  animation: float 5s infinite;
}
 
th {
  color:#D5DDE5;;
  background:#1b1e24;
  border-bottom:4px solid #9ea7af;
  border-right: 1px solid #343a45;
  font-size:23px;
  font-weight: 100;
  padding:24px;
  text-align:left;
  text-shadow: 0 1px 1px rgba(0, 0, 0, 0.1);
  vertical-align:middle;
}

th:first-child {
  border-top-left-radius:3px;
}
 
th:last-child {
  border-top-right-radius:3px;
  border-right:none;
}
  
tr {
  border-top: 1px solid #C1C3D1;
  border-bottom-: 1px solid #C1C3D1;
  color:#666B85;
  font-size:16px;
  font-weight:normal;
  text-shadow: 0 1px 1px rgba(256, 256, 256, 0.1);
}
 
tr:hover td {
  background:#4E5066;
  color:#FFFFFF;
  border-top: 1px solid #22262e;
  border-bottom: 1px solid #22262e;
}
 
tr:first-child {
  border-top:none;
}

tr:last-child {
  border-bottom:none;
}
 
tr:nth-child(odd) td {
  background:#EBEBEB;
}
 
tr:nth-child(odd):hover td {
  background:#4E5066;
}

tr:last-child td:first-child {
  border-bottom-left-radius:3px;
}
 
tr:last-child td:last-child {
  border-bottom-right-radius:3px;
}
 
td {
  background:#FFFFFF;
  padding:20px;
  text-align:left;
  vertical-align:middle;
  font-weight:500;
  font-size:18px;
  text-shadow: -1px -1px 1px rgba(0, 0, 0, 0.1);
  border-right: 1px solid #C1C3D1;
}

td:last-child {
  border-right: 0px;
}

th.text-left {
  text-align: left;
}

th.text-center {
  text-align: center;
}

th.text-right {
  text-align: right;
}

td.text-left {
  text-align: left;
}

td.text-center {
  text-align: center;
}

td.text-right {
  text-align: right;
}


</style>
</head>
<body>
	<jsp:include page="/header.jsp"/>    <br/>
	 <div class="container">
        <div class="row">
            <div class="box">
                <div class="col-lg-12 text-center">
   <!--  <table align="center">
    	<tr>
    		<td> -->
    		
    		<%
				if((loginStatus != null) && (loginStatus.equals("true")))
			     {
			     System.out.println("LoginStatus : " + loginStatus);
			     	List<Booking> bookdtlList = CustomerDB.getBookingDetails(customerId);
			     	if(bookdtlList != null && bookdtlList.size() != 0)
			     	{
			     		System.out.println("Booklist size: " + bookdtlList.size());
				     	for(Booking bookdtl : bookdtlList)
				     	{
				     		int packageId = bookdtl.getPackageId();
				     		int bookingId = bookdtl.getBookingId();
				     		String tripTypeId = bookdtl.getTripTypeId();
				     		Package pkgDtl = CustomerDB.getPackageDetails(packageId);
							System.out.println("Pkgname : " + pkgDtl.getPkgName());
							BookingDetail tripDetails = CustomerDB.getTripDetails(bookingId);
							String tripName = CustomerDB.getTripName(tripTypeId);
   			 %>
   			 		
				   			
				    
						     
															
							<div class="table-title">
								<hr>
                  				  <h2 class="intro-text text-center">Booking
                        		  <strong>Details</strong>
                				  </h2>
                    			<hr>
							</div>
							<table class="table-fill">
								<thead>
								<tr>
									<th class="text-left">Booking</th>
									<th class="text-left">Your Details</th>
								</tr>
								</thead>
								<tbody class="table-hover">
								<tr>
									<td class="text-left">Booking ID:</td>
									<td class="text-left"><%=bookdtl.getBookingId() != 0 ? bookdtl.getBookingId() : 0 %></td>
								</tr>
								<tr>
									<td class="text-left">Traveler Count:</td>
									<td class="text-left"><%=bookdtl.getTravelerCount() != 0 ? bookdtl.getTravelerCount() : 0 %></td>
								</tr>
								<tr>
									<td class="text-left">Package Name: </td>
									<td class="text-left"><%=pkgDtl.getPkgName() != null ? pkgDtl.getPkgName() : "" %></td>
								</tr>
								<tr>
									<td class="text-left">Package Description: </td>
									<td class="text-left"><%=pkgDtl.getPkgDesc() != null ? pkgDtl.getPkgDesc() : "" %></td>
								</tr>
								<tr>
						    		<td class="text-left">Package Start Date: </td>
						    		<td class="text-left"><%=pkgDtl.getPkgStartDate() != null ? pkgDtl.getPkgStartDate() : "" %></td>
						    	</tr>
						    	<tr>	
						    		<td class="text-left">Package End Date: </td>
						    		<td class="text-left"><%=pkgDtl.getPkgEndDate() != null ? pkgDtl.getPkgEndDate() : "" %></td>
						    	</tr>
						    	<tr >
						    		<td class="text-left">Package Base Price: </td>
						    		<td class="text-left"><%=pkgDtl.getPkgBasePrice() != 0 ? pkgDtl.getPkgBasePrice() : 0 %></td>
						    	</tr>
						    <%-- 	<tr align="left">
						    		<td>Trip Itinerary No: </td>
						    		<td><%=tripDetails.getItineraryNo() != 0 ? tripDetails.getItineraryNo() : 0 %></td>
						    	</tr> --%>
						    	<tr >
						    		<td class="text-left">Trip Start Date:</td>
						    		<td class="text-left"><%=tripDetails.getTripStart() != null ? tripDetails.getTripStart() : "" %></td>
						    	</tr>
						    	<tr>
						    		<td class="text-left">Trip End Date:</td>
						    		<td class="text-left"><%=tripDetails.getTripEnd() != null ? tripDetails.getTripEnd() : "" %></td>
						    	</tr>
						    	<tr>
						    		<td class="text-left">Description: </td>
						    		<td class="text-left"><%=tripDetails.getDescription() != null ? tripDetails.getDescription() : "" %></td>
						    	</tr>
						    	<tr>
						    		<td class="text-left">Destination: </td>
						    		<td class="text-left"><%=tripDetails.getDestination() != null ? tripDetails.getDestination() : "" %></td>
						    	</tr>
						    	<tr>
						    		<td class="text-left">Base Price: </td>
						    		<td class="text-left"><%=tripDetails.getBasePrice() != 0 ? tripDetails.getBasePrice() : 0 %></td>
						    	</tr>
						    	<tr>
						    		<td class="text-left">TripTypeName: </td>
						    		<td class="text-left"><%=tripName != null ? tripName : "" %></td>
						    	</tr>
								</tbody>
							</table>
						   
						
				   <%
		   		 		}
		   		    %>
		   		 	<br/><br/>
		    		<input type="button" value="Print" onclick="window.print()" class="btn btn-primary btn-lg" align="middle"/>
		     <%	
		     }
		    	
		    	else
		    	{
		    	%>
		    		<h5>You have no travel bookings</h5>
		    	<%
		    	}
		    	}
		     }
		    %>
    	<!-- 	</td>
    	</tr>
    </table> -->
   	 </div>
   	</div>
   </div>
  </div>
  
 
</body>
</html>