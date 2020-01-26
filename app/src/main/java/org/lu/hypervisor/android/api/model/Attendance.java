package org.lu.hypervisor.android.api.model;

public class Attendance {
    private Long id;
    private Course course;
    private Subject attendee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Subject getAttendee() {
        return attendee;
    }

    public void setAttendee(Subject attendee) {
        this.attendee = attendee;
    }
}
