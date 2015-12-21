<%--
  Created by IntelliJ IDEA.
  User: bryukhaa
  Date: 9/30/15
  Time: 11:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meet Me</title>
    <link rel='stylesheet' href='../content/bootstrap-3.3.5/css/bootstrap.min.css'>
    <link rel='stylesheet' href='../content/font-awesome-4.4.0/css/font-awesome.min.css'>
    <link rel='stylesheet' href='../content/css/menu.css'>
    <link rel='stylesheet' href='../content/css/main.css'>
    <link rel='stylesheet' href='../content/css/detail.css'>

</head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2 menu">
                    <div>
                        <img class="logo" src="../content/logo.png" />
                    </div>
                    <ul class="menu-list" role="tablist">
                        <li class="menu-item" role="presentation">
                            <a href="#meetings" aria-controls="meetings" role="tab" data-toggle="tab" id="menu-calendar">
                                <i class="fa fa-calendar"></i> MEETINGS
                            </a>
                        </li>
                        <li class="menu-item" role="presentation">
                            <%--<a href="#meetings" aria-controls="participants" role="tab" data-toggle="tab">--%>
                            <a>
                                <i class="fa fa-users"></i> PARTICIPANTS
                            </a>
                        </li>
                        <li class="menu-item" role="presentation">
                            <a href="#reports" aria-controls="reports" role="tab" data-toggle="tab" id="menu-reports">
                                <i class="fa fa-pie-chart"></i> REPORTS
                            </a>
                        </li>
                        <li class="menu-item" role="presentation"><a><i class="fa fa-sign-out"></i> LOGOUT</a></li>
                    </ul>
                </div>
                <div class="col-md-10 tab-content main">
                    <div role="tabpanel" class="tab-pane" id="meetings">
                        <div class="meeting-list">
                            <%--Meeting list--%>
                            <%@include file="meetinglist.jsp"%>
                        </div>
                        <div class="col-md-8 details" style="display: none;">
                            <%@include file="meetingdetails.jsp"%>
                        </div>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="reports">
                        <div class="report-list">
                            <%@include file="reportslist.jsp"%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>

<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="http://code.highcharts.com/highcharts.js"></script>
<script type="text/javascript" src="../content/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../content/lib/knockout-3.3.0.js"></script>
<script type="text/javascript" src="../content/lib/moment.min.js"></script>
<script type="text/javascript" src="../scripts/main.js"></script>
<script type="text/javascript" src="../scripts/MeetingsModel.js"></script>
<script type="text/javascript" src="../scripts/ReportsModel.js"></script>
</html>
