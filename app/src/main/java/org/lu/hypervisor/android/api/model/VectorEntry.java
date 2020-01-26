package org.lu.hypervisor.android.api.model;


import java.io.Serializable;

public class VectorEntry implements Serializable {
    private Long id;
    private double[] faceVec;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double[] getFaceVec() {
        return faceVec;
    }

    public void setFaceVec(double[] faceVec) {
        this.faceVec = faceVec;
    }
}
