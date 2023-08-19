package com.wisdom.bevm.respositories;

import com.wisdom.bevm.models.BEVM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BEVMRepository extends JpaRepository<BEVM, Long> {
    public Long countByBevmId(Long bevmId);
}
