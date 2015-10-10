<%--
  Created by IntelliJ IDEA.
  User: bryukhaa
  Date: 8/14/15
  Time: 11:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Meet Me</title>
    <link rel='stylesheet' href='../content/bootstrap-3.3.5/css/bootstrap.min.css'>
  </head>
  <body>
  <div class="container-fluid">
    <div class="row">
      <div class="col-sm-offset-4 col-sm-4 text-center">
        <p>Hey there! Enter your organizer email to see your meetings.</p>
      </div>
      <div class="col-sm-offset-3 col-sm-6 col-md-offset-5 col-md-2 text-center">
        <form action="/Login" method="post">
          <div class="form-group text-left">
            <label class="control-label"><small>ORGANIZER EMAIL</small></label>
            <input class="form-control" name="email" id="email" type="email" placeholder="Your organizer email" type="text" />
          </div>
          <button class="btn btn-primary" type="submit">Let's meet!</button>
        </form>
      </div>
    </div>
  </div>
  </body>
</html>
