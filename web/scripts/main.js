/**
 * Created by bryukhaa on 10/6/15.
 */

var MenuApp = function() {
    $("#menu-calendar").on("click", function () {
        // Load list of meetings
        $.get("/MeetingsList")
            .done(function (meetingsData) {
                var meetingsVM = new MeetingList(meetingsData);
                ko.applyBindings(meetingsVM, $(".meeting-list-full")[0]);
            });
    });

    // Bind to a blank "meeting"
    var currentMeeting = ko.observable({
        meetingId: '',
        title: '',
        timestamp: '',
        organizer: { name: '' },
        pre: [],
        agenda: '',
        post: [],
        attendees: []
    });
    ko.applyBindings(currentMeeting, $(".details .meeting-details")[0]);

    this.showMeetingDetailsPane = function showMeetingDetailsPane(meetingId) {
        $.get("/Meeting", {meetingId: meetingId})
            .done(function (meetingDetails) {
                var meetingDetailsVM = new Meeting(meetingDetails);
                currentMeeting(meetingDetailsVM);

                $(".main").addClass("min");
                $(".details").show();
            });
    };
};

var $menuApp;
$(document).ready(function () {
    $menuApp = new MenuApp();
});