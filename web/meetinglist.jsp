<%--
  Created by IntelliJ IDEA.
  User: bryukhaa
  Date: 10/5/15
  Time: 10:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h1>Meetings</h1>
  <div class="row">
  <%--<div class="col-md-8 meeting-list-calendar">--%>
    <%--<div class="meeting-list-calendar-today text-center">--%>
      <%--<p>WEDNESDAY</p>--%>
      <%--<h1>1</h1>--%>
      <%--<a><small>3 meetings</small></a>--%>
    <%--</div>--%>
  <%--</div>--%>
  <div class="col-md-12 meeting-list-full">
    <ul data-bind="foreach: meetingList, visible: meetingList.length > 0">
      <li>
        <a data-bind="click: $parent.loadMeeting.bind($data, meetingId), click: $menuApp.showMeetingDetailsPane.bind(window, meetingId)">
          <h4 class="meeting-title" data-bind="text: title"></h4>
          <div><i class="fa fa-calendar"></i> <small data-bind="text: timestamp"></small></div>
          <div><i class="fa fa-user-secret"></i> <small data-bind="text: organizer.name"></small></div>
        </a>
      </li>
    </ul>
    <span data-bind="visible: meetingList.length == 0">No meetings.</span>
  </div>
</div>
