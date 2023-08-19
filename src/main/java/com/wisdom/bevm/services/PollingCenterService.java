package com.wisdom.bevm.services;

import com.wisdom.bevm.exceptions.PollingCenterNotFoundException;
import com.wisdom.bevm.models.PollingCenter;
import com.wisdom.bevm.models.Supervisor;
import com.wisdom.bevm.respositories.PollingCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PollingCenterService {

    @Autowired
    private PollingCenterRepository pollingCenterRepository;

    public PollingCenter add(PollingCenter pollingCenter){
        return pollingCenterRepository.save(pollingCenter);
    }

    public List<PollingCenter> findAll(){
        return pollingCenterRepository.findAll();
    }

    public void delete(Long pollingCenterId) throws PollingCenterNotFoundException {
        Long count = pollingCenterRepository.countByPollingCenterId(pollingCenterId);
        if (count == 0 || count == null) {
            throw new PollingCenterNotFoundException("Polling Center ID " +pollingCenterId+" Not Found");
        }
        pollingCenterRepository.deleteById(pollingCenterId);
    }

    public Optional<PollingCenter> findById(Long id){
        return pollingCenterRepository.findById(id);
    }

//    public Supervisor getSupervisor(Long id){
//        Optional<PollingCenter> p_center = pollingCenterRepository.findBySupervisorRollNo(id);
//        if (p_center.isPresent()){
//            return p_center.get().getSupervisor();
//        }else {
//            System.out.println("Not found");
//            return null;
//        }
//    }

    public boolean isAddressUnique(String address){
        Optional<PollingCenter> p_center = pollingCenterRepository.findByLocation(address);
        if (p_center.isPresent()){
            return true;
        }else {
            return false;
        }
    }

    public boolean isSupervisorUnique(Long supervisorRollNo){
        Long count = pollingCenterRepository.countBySupervisorRollNo(supervisorRollNo);
        if (count > 0){
            return false;
        }else {
            return true;
        }
    }

}
