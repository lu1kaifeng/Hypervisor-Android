package org.lu.hypervisor.android.api.model;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;


public class CourseTrigger implements Serializable {
    private Long id;
    private LocalTime startTime;
    private Duration duration;
    private Boolean isCreated = Boolean.FALSE;
    private Boolean isDestroyed = Boolean.TRUE;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Boolean getCreated() {
        return isCreated;
    }

    public void setCreated(Boolean created) {
        isCreated = created;
    }

    public Boolean getDestroyed() {
        return isDestroyed;
    }

    public void setDestroyed(Boolean destroyed) {
        isDestroyed = destroyed;
    }
}
