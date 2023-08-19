package com.wisdom.bevm.services;

import com.wisdom.bevm.exceptions.CandidateNotFoundException;
import com.wisdom.bevm.exceptions.SupervisorNotFoundException;
import com.wisdom.bevm.models.Supervisor;
import com.wisdom.bevm.respositories.SupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupervisorService {

    @Autowired
    private SupervisorRepository supervisorRepository;

    public Supervisor add(Supervisor supervisor){
        return supervisorRepository.save(supervisor);
    }

    public List<Supervisor> findAll(){
        return supervisorRepository.findAll();
    }

    public void delete(Long rollNo) throws SupervisorNotFoundException {
        Long count = supervisorRepository.countByRollNo(rollNo);
        if (count == 0 || count == null) {
            throw new SupervisorNotFoundException("Supervisor RollNo " +rollNo+" Not Found");
        }
        supervisorRepository.deleteById(rollNo);
    }

    public Optional<Supervisor> findById(Long id){
        return supervisorRepository.findById(id);
    }

    public boolean isCitizenUnique(Long nid){
        Long count = supervisorRepository.countByNid(nid);
        if (count > 0){
            return false;
        }else {
            return true;
        }
    }
}
