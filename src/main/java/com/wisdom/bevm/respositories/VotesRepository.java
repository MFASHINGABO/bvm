package com.wisdom.bevm.respositories;

import com.wisdom.bevm.models.BEVM;
import com.wisdom.bevm.models.Votes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VotesRepository extends JpaRepository<Votes, Long> {
    public Long countById(Long id);

    public Long countByVoterNid(Long voterNid);

    public Long countByCandidateId(Long candidateId);

    Optional<Votes> findByVoterNid(Long nid);
}
