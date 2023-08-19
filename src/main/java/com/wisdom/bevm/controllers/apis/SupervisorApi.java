package com.wisdom.bevm.controllers.apis;

import com.wisdom.bevm.exceptions.SupervisorNotFoundException;
import com.wisdom.bevm.models.Supervisor;
import com.wisdom.bevm.services.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/supervisors")
public class SupervisorApi {

    @Autowired
    private SupervisorService supervisorService;

    @PostMapping("/")
    public void add(@RequestBody Supervisor supervisor){
        this.supervisorService.add(supervisor);
    }

    @GetMapping("/")
    public List<Supervisor> findAll(){
        return supervisorService.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws SupervisorNotFoundException {
        supervisorService.delete(id);
    }

    @GetMapping("/{id}")
    public Optional<Supervisor> findById(@PathVariable Long id){
        return supervisorService.findById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Supervisor> update(
            @RequestBody Supervisor supervisor,
            @PathVariable Long id
    ){
        Optional<Supervisor> supervisorDate = supervisorService.findById(id);
        if (supervisorDate.isPresent()){
            Supervisor _supervisor = supervisorDate.get();

            _supervisor.setNid(supervisor.getNid());
            _supervisor.setDateAssigned(supervisor.getDateAssigned());

            Supervisor updatedSupervisor = supervisorService.add(_supervisor);
            return new ResponseEntity<>(updatedSupervisor, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
