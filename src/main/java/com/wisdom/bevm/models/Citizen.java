package com.wisdom.bevm.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Citizen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nid;

    private String firstname;
    private String lastname;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    private String address;
    private String phone;
    private String photo;

//    @Column(unique = true, nullable = true)
    private int fingerPrintId;

    @OneToOne(mappedBy = "citizen")
    private Supervisor supervisor;

    @OneToOne(mappedBy = "citizen")
    private Candidate candidate;

    @OneToOne(mappedBy = "voter")
    private Votes vote;

    public Citizen() {
    }

    public Citizen(Long nid, String firstname, String lastname, Date dob, String address, String phone, String photo, int fingerPrintId, Supervisor supervisor, Candidate candidate) {
        this.nid = nid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
        this.address = address;
        this.phone = phone;
        this.photo = photo;
        this.fingerPrintId = fingerPrintId;
        this.supervisor = supervisor;
        this.candidate = candidate;
    }

    public Long getNid() {
        return nid;
    }

    public void setNid(Long nid) {
        this.nid = nid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getFingerPrintId() {
        return fingerPrintId;
    }

    public void setFingerPrintId(int fingerPrintId) {
        this.fingerPrintId = fingerPrintId;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Citizen citizen = (Citizen) o;
        return fingerPrintId == citizen.fingerPrintId && Objects.equals(nid, citizen.nid) && Objects.equals(firstname, citizen.firstname) && Objects.equals(lastname, citizen.lastname) && Objects.equals(dob, citizen.dob) && Objects.equals(address, citizen.address) && Objects.equals(phone, citizen.phone) && Objects.equals(photo, citizen.photo) && Objects.equals(supervisor, citizen.supervisor) && Objects.equals(candidate, citizen.candidate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nid, firstname, lastname, dob, address, phone, photo, fingerPrintId, supervisor, candidate);
    }

    @Override
    public String toString() {
        return "Citizen{" +
                "nid=" + nid +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", dob=" + dob +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", photo='" + photo + '\'' +
                ", fingerPrintId=" + fingerPrintId +
                ", supervisor=" + supervisor +
                ", candidate=" + candidate +
                '}';
    }

    @Transient
    public String getPhotoImagePath(){
        if (nid == null || photo == null){
            return "/images/default-image.jpeg";
        }
        return "/citizen-photos/" + this.nid + "/" + this.photo;
    }
}
