/**
 * Created by bryukhaa on 10/6/15.
 */
$("#menu-calendar").on("click", function() {
    // Load list of meetings
    $.get("/MeetingsList")
        .done(function (meetingsData) {
            var meetingsVM = new MeetingList(meetingsData);
            ko.applyBindings(meetingsVM, $(".meeting-list-full")[0]);
        });
});

var showMeetingDetailsPane = function showMeetingDetailsPane(meetingId) {
    $.get("/Meeting", { meetingId: meetingId})
        .done(function (meetingDetails) {
            var meetingDetailsVM = new Meeting(meetingDetails);
            ko.applyBindings(meetingDetailsVM, $(".details .meeting-details")[0]);

            $(".main").addClass("min");
            $(".details").show();
        });
};