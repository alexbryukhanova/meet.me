/**
 * Created by bryukhaa on 10/6/15.
 */

//var Meeting = function(meetingData) {
//    this.title = meetingData.title;
//    this.organizer = meetingData.organizer;
//};



var MeetingList = function(meetings) {
    // Model
    this.meetingList = meetings;
    $(this.meetingList).each(function (i, item) {
        item.timestamp = Meeting.Utils.getTimestamp(item.startDate, item.endDate);
    });

    this.loadMeeting = function(meetingId) {
        alert(meetingId);
    }
};

var Meeting = function (meetingDetails) {
    $.extend(this, meetingDetails);

    this.timestamp = Meeting.Utils.getTimestamp(this.startDate, this.endDate);
};

Meeting.Utils = {
    getTimestamp: function (startMillis, endMillis) {
        var start = moment(startMillis);
        var end = moment(endMillis);

        var timestamp = start.format("dddd, MMMM D YYYY, h:mmA");
        timestamp += " || ";
        if(end.diff(start, 'days') > 0) {
            timestamp += end.format("dddd, MMMM D YYYY, ");
        }
        timestamp += end.format("h:mmA");

        return timestamp;
    }
};
