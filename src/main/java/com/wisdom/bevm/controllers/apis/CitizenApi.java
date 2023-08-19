package com.wisdom.bevm.controllers.apis;

import com.wisdom.bevm.exceptions.CandidateNotFoundException;
import com.wisdom.bevm.exceptions.CitizenNotFoundException;
import com.wisdom.bevm.models.Citizen;
import com.wisdom.bevm.services.CitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api-citizens")
public class CitizenApi {

    @Autowired
    private CitizenService citizenService;

    @PostMapping("/")
    public void add(@RequestBody Citizen citizen){
        citizenService.add(citizen);
    }

    @GetMapping("/")
    public List<Citizen> findAll(){
        return citizenService.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        try {
            citizenService.delete(id);
        } catch (CitizenNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public Optional<Citizen> findById(@PathVariable Long id){
        return citizenService.findById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Citizen> update(
            @RequestBody Citizen citizen,
            @PathVariable Long id
    ){
        Optional<Citizen> citizenDate = citizenService.findById(id);
        if (citizenDate.isPresent()){
            Citizen _citizen = citizenDate.get();

            _citizen.setAddress(citizen.getAddress());
            _citizen.setFirstname(citizen.getFirstname());
            _citizen.setLastname(citizen.getLastname());
            _citizen.setPhone(citizen.getPhone());
            _citizen.setDob(citizen.getDob());
            _citizen.setPhoto(citizen.getPhoto());

            Citizen updatedCitizen = citizenService.add(_citizen);
            return new ResponseEntity<>(updatedCitizen, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @GetMapping("/enroll-finger/{nid}")
//    public Integer enrollFinger(@RequestParam("f_id") Integer f_id, @PathVariable("nid") Long nid){
//        Optional<Citizen> citizen = citizenService.findById(nid);
//        if (citizen.isPresent()){
//            Citizen _citizen = citizen.get();
//            _citizen.setFingerPrintId(f_id);
//            Citizen updatedCitizen = citizenService.add(_citizen);
//
//            return f_id;
//        }
//        return 0;
//    }

}
