package org.lu.hypervisor.android.api.model;

public class Subject {
    private Long id;
    private String name;
    private String role;
    private String password;
    private Long numEngagement = 0L;
    private Long numDisengagement = 0L;
    private String photoId;
    private Long photoLength;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public Long getPhotoLength() {
        return photoLength;
    }

    public void setPhotoLength(Long photoLength) {
        this.photoLength = photoLength;
    }

    public Long getNumEngagement() {
        return numEngagement;
    }

    public void setNumEngagement(Long numEngagement) {
        this.numEngagement = numEngagement;
    }

    public Long getNumDisengagement() {
        return numDisengagement;
    }

    public void setNumDisengagement(Long numDisengagement) {
        this.numDisengagement = numDisengagement;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
