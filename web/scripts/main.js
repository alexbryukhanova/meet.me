/**
 * Created by bryukhaa on 10/6/15.
 */

var MenuApp = function() {
    // Default empty bindings
    var meetingsVM = ko.observable({ meetingList: []});
    ko.applyBindings(meetingsVM, $(".meeting-list-full")[0]);
    var reportsVM = ko.observable({ totalCost: '', agendaless: '' });
    ko.applyBindings(reportsVM, $(".reports")[0]);

    // Load data on menu change
    $("#menu-calendar").on("click", function () {
        // Load list of meetings
        $.get("/MeetingsList")
            .done(function (meetingsData) {
                meetingsVM(new MeetingList(meetingsData));
            });
    });
    $("#menu-reports").on("click", function () {
        // Load reports
        $.get("/Reports")
            .done(function (reportsData) {
                var reports = new Reports(reportsData);
                reportsVM(reports);
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

                $(".meeting-list").addClass("min");
                $(".details").show();
            });
    };
};

var $menuApp;
$(document).ready(function () {
    $menuApp = new MenuApp();
});