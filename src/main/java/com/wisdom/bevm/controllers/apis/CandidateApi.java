package com.wisdom.bevm.controllers.apis;

import com.wisdom.bevm.exceptions.CandidateNotFoundException;
import com.wisdom.bevm.models.Candidate;
import com.wisdom.bevm.services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apis/candidates")
public class CandidateApi {

    @Autowired
    private CandidateService candidateService;

    @PostMapping("/")
    public void add(@RequestBody Candidate candidate){
        candidateService.add(candidate);
    }

    @GetMapping("/")
    public List<Candidate> findAll(){
        return candidateService.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws CandidateNotFoundException {
        candidateService.delete(id);
    }

    @GetMapping("/{id}")
    public Optional<Candidate> findById(@PathVariable Long id){
        return candidateService.findById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Candidate> update(
            @RequestBody Candidate candidate,
            @PathVariable Long id
    ){
        Optional<Candidate> candidateDate = candidateService.findById(id);
        if (candidateDate.isPresent()){
            Candidate _candidate = candidateDate.get();

            _candidate.setFullName(candidate.getFullName());
            _candidate.setPhoto(candidate.getPhoto());

            Candidate updatedCandidate = candidateService.add(_candidate);
            return new ResponseEntity<>(updatedCandidate, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
