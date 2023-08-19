package com.wisdom.bevm.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

//@Entity
public class RegisteredVoter {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long roll_no;
//
//    private String fingerPrintId;
//
//    @Temporal(TemporalType.DATE)
//    private Date dateRegistered;
//
//    @OneToOne
//    @JoinColumn(
//            name = "nid",
//            insertable = false,
//            updatable = false
//    )
//    private Citizen citizen;
//    private Long nid;
//
//    @ManyToOne
//    @JoinColumn(
//            name = "pollingCenterId",
//            insertable = false,
//            updatable = false
//    )
//    private PollingCenter pollingCenter;
//    private Long pollingCenterId;
//
//    @OneToOne(mappedBy = "voter")
//    private Votes myVote;
//
//    public RegisteredVoter(Long roll_no, String fingerPrintId, Date dateRegistered, Citizen citizen, Long nid, PollingCenter pollingCenter, Long pollingCenterId, Votes myVote) {
//        this.roll_no = roll_no;
//        this.fingerPrintId = fingerPrintId;
//        this.dateRegistered = dateRegistered;
//        this.citizen = citizen;
//        this.nid = nid;
//        this.pollingCenter = pollingCenter;
//        this.pollingCenterId = pollingCenterId;
//        this.myVote = myVote;
//    }
//
//    public RegisteredVoter() {
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        RegisteredVoter that = (RegisteredVoter) o;
//        return Objects.equals(roll_no, that.roll_no) && Objects.equals(fingerPrintId, that.fingerPrintId) && Objects.equals(dateRegistered, that.dateRegistered) && Objects.equals(citizen, that.citizen) && Objects.equals(nid, that.nid) && Objects.equals(pollingCenter, that.pollingCenter) && Objects.equals(pollingCenterId, that.pollingCenterId) && Objects.equals(myVote, that.myVote);
//    }
//
//    public String getFingerPrintId() {
//        return fingerPrintId;
//    }
//
//    public void setFingerPrintId(String fingerPrintId) {
//        this.fingerPrintId = fingerPrintId;
//    }
//
//    public Date getDateRegistered() {
//        return dateRegistered;
//    }
//
//    public void setDateRegistered(Date dateRegistered) {
//        this.dateRegistered = dateRegistered;
//    }
//
//    public Citizen getCitizen() {
//        return citizen;
//    }
//
//    public void setCitizen(Citizen citizen) {
//        this.citizen = citizen;
//    }
//
//    public Long getNid() {
//        return nid;
//    }
//
//    public void setNid(Long nid) {
//        this.nid = nid;
//    }
//
//    public PollingCenter getPollingCenter() {
//        return pollingCenter;
//    }
//
//    public void setPollingCenter(PollingCenter pollingCenter) {
//        this.pollingCenter = pollingCenter;
//    }
//
//    public Long getPollingCenterId() {
//        return pollingCenterId;
//    }
//
//    public void setPollingCenterId(Long pollingCenterId) {
//        this.pollingCenterId = pollingCenterId;
//    }
//
//    public Votes getMyVote() {
//        return myVote;
//    }
//
//    public void setMyVote(Votes myVote) {
//        this.myVote = myVote;
//    }
//
//    public Long getRoll_no() {
//        return roll_no;
//    }
//
//    public void setRoll_no(Long roll_no) {
//        this.roll_no = roll_no;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(roll_no, fingerPrintId, dateRegistered, citizen, nid, pollingCenter, pollingCenterId, myVote);
//    }
//
//    @Override
//    public String toString() {
//        return "RegisteredVoter{" +
//                "roll_no=" + roll_no +
//                ", fingerPrintId='" + fingerPrintId + '\'' +
//                ", dateRegistered=" + dateRegistered +
//                ", citizen=" + citizen +
//                ", nid=" + nid +
//                ", pollingCenter=" + pollingCenter +
//                ", pollingCenterId=" + pollingCenterId +
//                ", myVote=" + myVote +
//                '}';
//    }

}
