
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
                    <p>Lid est laborum dolo rumes fugats untras. Etharums ser quidem rerum facilis dolores nemis omnis fugats vitaes nemo minima rerums unsers sadips amets.</p>
                    <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.</p>
               		<p>Lid est laborum dolo rumes fugats untras. Etharums ser quidem rerum facilis dolores nemis omnis fugats vitaes nemo minima rerums unsers sadips amets.</p>
               		
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
                <div class="clearfix"></div>
            </div>
        </div>

    </div>
    <!-- /.container -->
    </body>
    <jsp:include page="/footer.jsp"/>
</html>
