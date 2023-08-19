package com.wisdom.bevm.controllers.apis;

import com.wisdom.bevm.models.RegisteredVoter;
import com.wisdom.bevm.services.RegisteredVoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@RestController
//@RequestMapping("/registeredVoters")
public class RegisteredVoterApi {

//    @Autowired
//    private RegisteredVoterService registeredVoterService;
//
//    @PostMapping("/")
//    public void add(@RequestBody RegisteredVoter registeredVoter){
//        this.registeredVoterService.add(registeredVoter);
//    }
//
//    @GetMapping("/")
//    public List<RegisteredVoter> findAll(){
//        return registeredVoterService.findAll();
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id){
//        registeredVoterService.delete(id);
//    }
//
//    @GetMapping("/{id}")
//    public Optional<RegisteredVoter> findById(@PathVariable Long id){
//        return registeredVoterService.findById(id);
//    }
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<RegisteredVoter> update(
//            @RequestBody RegisteredVoter registeredVoter,
//            @PathVariable Long id
//    ){
//        Optional<RegisteredVoter> registeredVoterDate = registeredVoterService.findById(id);
//        if (registeredVoterDate.isPresent()){
//            RegisteredVoter _registeredVoter = registeredVoterDate.get();
//
//            _registeredVoter.setNid(registeredVoter.getNid());
//            _registeredVoter.setDateRegistered(registeredVoter.getDateRegistered());
//            _registeredVoter.setFingerPrintId(registeredVoter.getFingerPrintId());
//
//            RegisteredVoter updatedRegisteredVoter = registeredVoterService.add(_registeredVoter);
//            return new ResponseEntity<>(updatedRegisteredVoter, HttpStatus.OK);
//        }else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
}
