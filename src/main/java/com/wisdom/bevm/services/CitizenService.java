package com.wisdom.bevm.services;

import com.wisdom.bevm.exceptions.CandidateNotFoundException;
import com.wisdom.bevm.exceptions.CitizenNotFoundException;
import com.wisdom.bevm.models.Citizen;
import com.wisdom.bevm.respositories.CitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitizenService {

    @Autowired
    private CitizenRepository citizenRepository;

    public Citizen add(Citizen citizen){
        return citizenRepository.save(citizen);
    }

    public List<Citizen> findAll(){
        return citizenRepository.findAll();
    }

    public void delete(Long nid) throws CitizenNotFoundException {
        Long count = citizenRepository.countByNid(nid);
        if (count == 0 || count == null) {
            throw new CitizenNotFoundException("Citizen NID " +nid+" Not Found");
        }

        citizenRepository.deleteById(nid);
    }
    public Optional<Citizen> findById(Long id){
        return citizenRepository.findById(id);
    }

    public Optional<Citizen> findByFingerPrintId(Integer fingerPrintId){
        return citizenRepository.findByFingerPrintId(fingerPrintId);
    }

}
