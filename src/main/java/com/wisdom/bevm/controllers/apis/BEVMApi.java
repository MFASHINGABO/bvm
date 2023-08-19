package com.wisdom.bevm.controllers.apis;

import com.wisdom.bevm.exceptions.BevmNotFoundException;
import com.wisdom.bevm.models.BEVM;
import com.wisdom.bevm.services.BEVMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bevms")
public class BEVMApi {

    @Autowired
    private BEVMService bevmService;

    @PostMapping("/")
    public void add(@RequestBody BEVM bevm){
        bevmService.add(bevm);
    }

    @GetMapping("/")
    public List<BEVM> findAll(){
        return bevmService.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws BevmNotFoundException {
        bevmService.delete(id);
    }

    @GetMapping("/{id}")
    public Optional<BEVM> findById(@PathVariable Long id){
        return bevmService.findById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BEVM> update(
            @RequestBody BEVM bevm,
            @PathVariable Long id
    ){
        Optional<BEVM> bevmData = bevmService.findById(id);
        if (bevmData.isPresent()){
            BEVM _bevm = bevmData.get();

            _bevm.setCapacity(bevm.getCapacity());
            _bevm.setPollingCenterId(bevm.getPollingCenterId());
            _bevm.setActive(bevm.isActive());
            _bevm.setCapacity(bevm.getCapacity());
            _bevm.setSecurityLevel(bevm.getSecurityLevel());

            BEVM updatedBEVM = bevmService.add(_bevm);
            return new ResponseEntity<>(updatedBEVM, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
