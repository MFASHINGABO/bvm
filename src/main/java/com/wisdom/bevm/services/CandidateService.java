package com.wisdom.bevm.services;

import com.wisdom.bevm.exceptions.CandidateNotFoundException;
import com.wisdom.bevm.models.Candidate;
import com.wisdom.bevm.respositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    public Candidate add(Candidate candidate){
        return candidateRepository.save(candidate);
    }

    public List<Candidate> findAll(){
        return candidateRepository.findAll();
    }

    public void delete(Long candidateId) throws CandidateNotFoundException {
        Long count = candidateRepository.countByCandidateId(candidateId);
        if (count == 0 || count == null) {
            throw new CandidateNotFoundException("Candidate NID " +candidateId+" Not Found");
        }
        candidateRepository.deleteById(candidateId);
    }
    public Optional<Candidate> findById(Long id){
        return candidateRepository.findById(id);
    }

    public boolean isCitizenUnique(Long nid){
        Long count = candidateRepository.countByNid(nid);
        if (count > 0){
            return false;
        }else {
            return true;
        }
    }
}
