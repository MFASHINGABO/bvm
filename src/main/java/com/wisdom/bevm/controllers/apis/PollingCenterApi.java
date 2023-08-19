package com.wisdom.bevm.controllers.apis;

import com.wisdom.bevm.exceptions.PollingCenterNotFoundException;
import com.wisdom.bevm.models.PollingCenter;
import com.wisdom.bevm.services.PollingCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/polling-centers")
public class PollingCenterApi {

    @Autowired
    private PollingCenterService pollingCenterService;

    //TODO Complete handing exception
//    @PostMapping("/")
//    public void add(@RequestBody PollingCenter pollingCenter){
//
//        String address = pollingCenter.getAddress();
//        if (pollingCenterService.isSupervisorAssigned(supervisor_roll_no)){
//            System.out.println("Already Assigned Supervisor with id: " + pollingCenter.getSupervisor().getRoll_no());
//        } else if (pollingCenterService.isAddressUnique(address)) {
//            System.out.println("Already occupied this address with : " + pollingCenter.getSupervisor().getRoll_no());
//        } else {
//            pollingCenterService.add(pollingCenter);
//            System.out.println("Saved successfully");
//        }
//
//    }

//    public boolean isUniqueSupervisorAndAddress(PollingCenter pollingCenter){
//        Supervisor supervisor = pollingCenterService.getSupervisor(pollingCenter.getSupervisor_roll_no());
//        Long supervisor_roll_no = pollingCenter.getSupervisor_roll_no();
//
//        if (supervisor != null){
//            return true;
//        }else {
//            return false;
//        }
//    }

    @GetMapping("/")
    public List<PollingCenter> findAll(){
        return pollingCenterService.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws PollingCenterNotFoundException {
        pollingCenterService.delete(id);
    }

    @GetMapping("/{id}")
    public Optional<PollingCenter> findById(@PathVariable Long id){
        return pollingCenterService.findById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PollingCenter> update(
            @RequestBody PollingCenter pollingCenter,
            @PathVariable Long id
    ){
        Optional<PollingCenter> pollingCenterDate = pollingCenterService.findById(id);
        if (pollingCenterDate.isPresent()){
            PollingCenter _pollingCenter = pollingCenterDate.get();

            _pollingCenter.setLocation(pollingCenter.getLocation());
            _pollingCenter.setPollingCenterId(pollingCenter.getPollingCenterId());

            PollingCenter updatedPollingCenter = pollingCenterService.add(_pollingCenter);
            return new ResponseEntity<>(updatedPollingCenter, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
