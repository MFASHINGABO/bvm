package com.wisdom.bevm.respositories;

import com.wisdom.bevm.models.Candidate;
import com.wisdom.bevm.models.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Long> {
    Long countByNid(Long nid);

    Optional<Citizen> findByFingerPrintId(Integer fingerPrintId);
}
