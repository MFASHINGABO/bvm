package com.wisdom.bevm.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class BEVM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bevm_id")
    private Long bevmId;

    private boolean active;
    private int capacity; //300
    private int securityLevel; //1-5[3]

    private String description;

    //station_id
    @ManyToOne
    @JoinColumn(
            name = "pollingCenterId",
            insertable = false,
            updatable = false
    )
    private PollingCenter pollingCenter;
    private Long pollingCenterId;

    public BEVM(Long bevmId, boolean active, int capacity, int securityLevel, String description, PollingCenter pollingCenter, Long pollingCenterId) {
        this.bevmId = bevmId;
        this.active = active;
        this.capacity = capacity;
        this.securityLevel = securityLevel;
        this.description = description;
        this.pollingCenter = pollingCenter;
        this.pollingCenterId = pollingCenterId;
    }

    public BEVM() {
    }

    public Long getBevmId() {
        return bevmId;
    }

    public void setBevmId(Long bevmId) {
        this.bevmId = bevmId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(int securityLevel) {
        this.securityLevel = securityLevel;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BEVM bevm = (BEVM) o;
        return capacity == bevm.capacity && securityLevel == bevm.securityLevel && Objects.equals(bevmId, bevm.bevmId) && Objects.equals(active, bevm.active) && Objects.equals(description, bevm.description) && Objects.equals(pollingCenter, bevm.pollingCenter) && Objects.equals(pollingCenterId, bevm.pollingCenterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bevmId, active, capacity, securityLevel, description, pollingCenter, pollingCenterId);
    }

    @Override
    public String toString() {
        return "BEVM{" +
                "bevmId=" + bevmId +
                ", status='" + active + '\'' +
                ", capacity=" + capacity +
                ", securityLevel=" + securityLevel +
                ", description='" + description + '\'' +
                ", pollingCenter=" + pollingCenter +
                ", pollingCenterId=" + pollingCenterId +
                '}';
    }
}
