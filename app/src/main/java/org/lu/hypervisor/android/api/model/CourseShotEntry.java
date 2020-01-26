package org.lu.hypervisor.android.api.model;

public class CourseShotEntry {
    private Subject target;
    private boolean isEngaged;

    public Subject getTarget() {
        return target;
    }

    public void setTarget(Subject target) {
        this.target = target;
    }

    public boolean isEngaged() {
        return isEngaged;
    }

    public void setEngaged(boolean engaged) {
        isEngaged = engaged;
    }
}
