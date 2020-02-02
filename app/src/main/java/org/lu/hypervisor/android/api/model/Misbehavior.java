package org.lu.hypervisor.android.api.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Misbehavior implements Serializable {
    private Long id;
    private Course course;
    private Subject subject;
    private LocalDateTime when;
    private BehaviorType type;

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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public LocalDateTime getWhen() {
        return when;
    }

    public void setWhen(LocalDateTime when) {
        this.when = when;
    }

    public BehaviorType getType() {
        return type;
    }

    public void setType(BehaviorType type) {
        this.type = type;
    }
}
