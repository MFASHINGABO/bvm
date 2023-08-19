package com.wisdom.bevm.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_id")
    private Long candidateId;

    @Column(name = "full_name")
    private String fullName;
    private String photo;

    @Column(name = "description")
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

    @JsonIgnore
    @OneToMany(mappedBy = "candidate")
    private List<Votes> votes;

    public Candidate(Long candidateId, String fullName, String photo, String description, Long nid) {
        this.candidateId = candidateId;
        this.fullName = fullName;
        this.photo = photo;
        this.description = description;
        this.nid = nid;
    }

    public Candidate() {
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<Votes> getVotes() {
        return votes;
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

    @Override
    public String toString() {
        return "Candidate{" +
                "candidateId=" + candidateId +
                ", fullName='" + fullName + '\'' +
                ", photo='" + photo + '\'' +
                ", description='" + description + '\'' +
                ", citizen=" + citizen +
                ", nid=" + nid +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (Candidate) o;
        return Objects.equals(candidateId, candidate.candidateId) && Objects.equals(fullName, candidate.fullName) && Objects.equals(photo, candidate.photo) && Objects.equals(description, candidate.description) && Objects.equals(citizen, candidate.citizen) && Objects.equals(nid, candidate.nid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(candidateId, fullName, photo, description, citizen, nid);
    }

    @Transient
    public String getPhotoImagePath(){
        if (candidateId == null || photo == null){
            return "/images/default-image.jpeg";
        }
        return "/candidate-photos/" + this.candidateId + "/" + this.photo;
    }
}
