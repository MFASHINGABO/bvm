package com.wisdom.bevm.respositories;

import com.wisdom.bevm.models.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, Long> {

    public Long countByNid(Long nid);

    public Long countByRollNo(Long rollNo);
}
