package com.wisdom.bevm.services;

import com.wisdom.bevm.exceptions.CitizenNotFoundException;
import com.wisdom.bevm.exceptions.VoteNotFoundException;
import com.wisdom.bevm.models.Citizen;
import com.wisdom.bevm.models.Votes;
import com.wisdom.bevm.respositories.CitizenRepository;
import com.wisdom.bevm.respositories.VotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoteService {

    @Autowired
    private VotesRepository votesRepository;

    public Votes add(Votes vote){
        return votesRepository.save(vote);
    }

    public List<Votes> findAll(){
        return votesRepository.findAll();
    }

    public void delete(Long id) throws VoteNotFoundException {
        Long count = votesRepository.countById(id);
        if (count == 0 || count == null) {
            throw new VoteNotFoundException("Vote ID " +id+" Not Found");
        }

        votesRepository.deleteById(id);
    }

    public Optional<Votes> findById(Long id){
        return votesRepository.findById(id);
    }

    public boolean isVoterUnique(Long voterNid){
        Long count = votesRepository.countByVoterNid(voterNid);
        if (count > 0){
            return false;
        }else {
            return true;
        }
    }

    public boolean isCandidateUnique(Long candidateId){
        Long count = votesRepository.countByCandidateId(candidateId);
        if (count > 0){
            return false;
        }else {
            return true;
        }
    }

    public Optional<Votes> findByVoterNid(Long nid) {
        return votesRepository.findByVoterNid(nid);
    }
}
