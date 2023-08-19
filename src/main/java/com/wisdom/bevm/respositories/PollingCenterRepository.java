package com.wisdom.bevm.respositories;

import com.wisdom.bevm.models.PollingCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PollingCenterRepository extends JpaRepository<PollingCenter, Long> {

//    @Query("SELECT pc FROM PollingCenter pc WHERE pc.supervisor_roll_no = :id")
//    Optional<PollingCenter> findBySupervisorRollNo(Long id);
    Optional<PollingCenter> findByLocation(String address);

    Long countByPollingCenterId(Long pollingCenterId);

    Long countBySupervisorRollNo(Long supervisorRollNo);

}
