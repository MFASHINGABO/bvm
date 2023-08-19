package com.wisdom.bevm.services;

import com.wisdom.bevm.models.Votes;
import com.wisdom.bevm.respositories.BEVMRepository;
import com.wisdom.bevm.respositories.VotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VotesService {

    @Autowired
    private VotesRepository votesRepository;

    public Votes add(Votes votes){
        return votesRepository.save(votes);
    }

    public List<Votes> findAll(){
        return votesRepository.findAll();
    }

    public void delete(Long id){
        votesRepository.deleteById(id);
    }

    public Optional<Votes> findById(Long id){
        return votesRepository.findById(id);
    }


}
