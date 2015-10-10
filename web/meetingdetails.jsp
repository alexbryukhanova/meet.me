<%--
  Created by IntelliJ IDEA.
  User: bryukhaa
  Date: 10/8/15
  Time: 8:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="meeting-details">
<div class="meeting-header" data-bind="attr: { mid: meetingId }">
  <h1 class="meeting-title" data-bind="text: title"></h1>
  <div><i class="fa fa-calendar"></i> <span data-bind="text: timestamp"></span></div>
  <div><small><i class="fa fa-user-secret"></i></small> <small data-bind="text: organizer.name"></small></div>
</div>
<div class="row">
  <div class="col-md-8">
    <div class="meeting-section">
      <label>PRE</label>
      <ul>
        <li>
          <a>
            <span class="fa-stack">
                <i class="fa fa-cloud fa-stack-1x"></i>
                <i class="fa fa-sun-o fa-stack-1x" style="margin-left: 5px;"></i>
            </span>
            RAINY DAY: 21/10/2015</a>
          <p><small>This meeting will be re-scheduled in case of rainy weather.</small></p>
        </li>
      </ul>
    </div>
    <div class="meeting-section">
      <label>AGENDA</label>
      <p class="meeting-agenda"><span class="meeting-agenda-empty">No agenda.</span></p>
    </div>
    <div class="meeting-section">
      <label>POST</label>
      <ul>
        <li>
          <a><i class="fa fa-file-text-o"></i> SURVEY</a>
          <p><small>A survey will be sent out to all attendees after this meeting.</small></p>
        </li>
      </ul>
    </div>
  </div>
  <div class="col-md-4">
    <div class="meeting-section">
      <label>PARTICIPANTS</label>
      <ul class="meeting-participants">
        <li>
          <a class="meeting-participant">
            <img class="meeting-participant-picture img-circle" src="http://wehearttours.com/Content/public/alex.jpg"/> Alex Bryukhanova
          </a>
        </li>
      </ul>
    </div>
  </div>
</div>
</div>
