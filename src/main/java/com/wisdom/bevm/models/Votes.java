package com.wisdom.bevm.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Votes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateVoted;

    @OneToOne
    @JoinColumn(
            name = "voterNid",
            insertable = false,
            updatable = false
    )
    private Citizen voter;
    private Long voterNid;

    @ManyToOne
    @JoinColumn(
            name = "candidateId",
            insertable = false,
            updatable = false
    )
    private Candidate candidate;
    private Long candidateId;

    @ManyToOne
    @JoinColumn(
            name = "pollingCenterId",
            insertable = false,
            updatable = false
    )
    public PollingCenter pollingCenter;
    private Long pollingCenterId;

    public Votes() {
    }

    public Votes(Long id, Date dateVoted, Citizen voter, Long voterNid, Candidate candidate, Long candidateId, PollingCenter pollingCenter, Long pollingCenterId) {
        this.id = id;
        this.dateVoted = dateVoted;
        this.voter = voter;
        this.voterNid = voterNid;
        this.candidate = candidate;
        this.candidateId = candidateId;
        this.pollingCenter = pollingCenter;
        this.pollingCenterId = pollingCenterId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateVoted() {
        return dateVoted;
    }

    public void setDateVoted(Date dateVoted) {
        this.dateVoted = dateVoted;
    }

    public Citizen getVoter() {
        return voter;
    }

    public void setVoter(Citizen voter) {
        this.voter = voter;
    }

    public Long getVoterNid() {
        return voterNid;
    }

    public void setVoterNid(Long voterNid) {
        this.voterNid = voterNid;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public PollingCenter getPollingCenter() {
        return pollingCenter;
    }

    public void setPollingCenter(PollingCenter pollingCenter) {
        this.pollingCenter = pollingCenter;
    }

    public Long getPollingCenterId() {
        return pollingCenterId;
    }

    public void setPollingCenterId(Long pollingCenterId) {
        this.pollingCenterId = pollingCenterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Votes votes = (Votes) o;
        return Objects.equals(id, votes.id) && Objects.equals(dateVoted, votes.dateVoted) && Objects.equals(voter, votes.voter) && Objects.equals(voterNid, votes.voterNid) && Objects.equals(candidate, votes.candidate) && Objects.equals(candidateId, votes.candidateId) && Objects.equals(pollingCenter, votes.pollingCenter) && Objects.equals(pollingCenterId, votes.pollingCenterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateVoted, voter, voterNid, candidate, candidateId, pollingCenter, pollingCenterId);
    }

    @Override
    public String toString() {
        return "Votes{" +
                "id=" + id +
                ", dateVoted=" + dateVoted +
                ", voterNid=" + voter +
                ", voter_id=" + voterNid +
                ", candidate=" + candidate +
                ", candidate_id=" + candidateId +
                ", pollingCenter=" + pollingCenter +
                ", pollingCenterId=" + pollingCenterId +
                '}';
    }
}
