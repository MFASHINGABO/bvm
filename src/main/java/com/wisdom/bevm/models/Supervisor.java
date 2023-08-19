package com.wisdom.bevm.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Supervisor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rollNo;

    private String photo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateAssigned;

    private String description;

    @JsonIgnore
    @OneToOne
    @JoinColumn(
            name = "nid",
            insertable = false,
            updatable = false
    )
    private Citizen citizen;
    private Long nid;

    @OneToOne(mappedBy = "supervisor")
    private PollingCenter pollingCenter;

    public Supervisor() {
    }

    public Supervisor(Long rollNo, String photo, Date dateAssigned, String description, Citizen citizen, Long nid, PollingCenter pollingCenter) {
        this.rollNo = rollNo;
        this.photo = photo;
        this.dateAssigned = dateAssigned;
        this.description = description;
        this.citizen = citizen;
        this.nid = nid;
        this.pollingCenter = pollingCenter;
    }

    public Long getRollNo() {
        return rollNo;
    }

    public void setRollNo(Long rollNo) {
        this.rollNo = rollNo;
    }

    public Date getDateAssigned() {
        return dateAssigned;
    }

    public void setDateAssigned(Date dateAssigned) {
        this.dateAssigned = dateAssigned;
    }

    public Citizen getCitizen() {
        return citizen;
    }

    public void setCitizen(Citizen citizen) {
        this.citizen = citizen;
    }

    public Long getNid() {
        return nid;
    }

    public void setNid(Long nid) {
        this.nid = nid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PollingCenter getPollingCenter() {
        return pollingCenter;
    }

    public void setPollingCenter(PollingCenter pollingCenter) {
        this.pollingCenter = pollingCenter;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supervisor that = (Supervisor) o;
        return Objects.equals(rollNo, that.rollNo) && Objects.equals(photo, that.photo) && Objects.equals(dateAssigned, that.dateAssigned) && Objects.equals(description, that.description) && Objects.equals(citizen, that.citizen) && Objects.equals(nid, that.nid) && Objects.equals(pollingCenter, that.pollingCenter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rollNo, photo, dateAssigned, description, citizen, nid, pollingCenter);
    }

    @Override
    public String toString() {
        return "Supervisor{" +
                "rollNo=" + rollNo +
                ", photo='" + photo + '\'' +
                ", dateAssigned=" + dateAssigned +
                ", description='" + description + '\'' +
                ", citizen=" + citizen +
                ", nid=" + nid +
                ", pollingCenter=" + pollingCenter +
                '}';
    }

    @Transient
    public String getPhotoImagePath(){
        if (rollNo == null || photo == null){
            return "/images/default-image.jpeg";
        }
        return "/supervisor-photos/" + this.rollNo + "/" + this.photo;
    }
}
