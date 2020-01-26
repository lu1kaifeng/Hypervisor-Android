package org.lu.hypervisor.android.api.model;


import java.io.Serializable;

public class CourseAttendee implements Serializable {
    private Long id;
    private Long courseId;
    private Boolean isPresent = Boolean.FALSE;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Boolean getPresent() {
        return isPresent;
    }

    public void setPresent(Boolean present) {
        isPresent = present;
    }
}
