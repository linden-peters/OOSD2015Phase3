
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <jsp:include page="/header.jsp"/>
      <div class="container">

        <div class="row">
            <div class="box">
                <div class="col-lg-12">
                    <hr>
                    <h2 class="intro-text text-center">Welcome to                        
                       <strong>travel experts</strong>
                    </h2>
                    <hr>
                </div>
                  <div class="col-md-3">
                    <img class="img-responsive img-border-left" src="img/slide.jpg" alt="funtimes" width="400" height="400">
                </div>
                <div class="col-md-9">
                    <p>This is a great place to introduce your company or project and describe what you do.</p>
                   <p>With this application you will be able to sign up, modify your information, and view the packages you have purchased and a financial summary of that information (all links to do so are found on the top). To get started please either sign up or log in.</p></span>
                <br />
                <br />
               <p> If you have any concerns or questions regarding anything, please let us assist you by emailing us at <a href="mailto:contact@travelexperts.com" target="_blank">contact@travelexperts.com</a> or phoning us at <a href="tel:(403)-593-4939" target="_blank" value="+14035934939">(403)-593-4939</a>.
               <br/>
               </p>
            <p>
                <span style="font-size: 11.5pt; line-height: 107%;  color: black;">Thank you for choosing Travel Experts – Fun times ahead.&nbsp;</span></p>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>

        <div class="row">
            <div class="box">
                <div class="col-lg-12">
                    <hr>
                    <h2 class="intro-text text-center">Popular
                        <strong>Destinations</strong>
                    </h2>
                    <hr>
                </div>
                <div class="col-sm-4 text-center">
                    <img class="img-responsive" width="450" height="750" src="img/chicago.JPG" alt="">
                    <h3>Chicago</h3>
                </div>
                <div class="col-sm-4 text-center">
                    <img class="img-responsive" width="450" height="600" src="img/fall.JPG" alt="">
                    <h3>London </h3>
                </div>
                <div class="col-sm-4 text-center">
                    <img class="img-responsive" width="450" height="750" src="img/winter.JPG" alt="">
                    <h3>Singapore</h3>
                </div>
              <!--   <div class="clearfix"></div> -->
            </div>
        </div>

    </div>
    <!-- /.container -->
    </body>
    <jsp:include page="/footer.jsp"/>
</html>
