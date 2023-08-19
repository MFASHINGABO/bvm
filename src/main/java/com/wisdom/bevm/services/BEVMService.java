package com.wisdom.bevm.services;

import com.wisdom.bevm.exceptions.BevmNotFoundException;
import com.wisdom.bevm.exceptions.PollingCenterNotFoundException;
import com.wisdom.bevm.models.BEVM;
import com.wisdom.bevm.respositories.BEVMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BEVMService {

    @Autowired
    private BEVMRepository bevmRepository;

    public BEVM add(BEVM bevm){
        return bevmRepository.save(bevm);
    }

    public List<BEVM> findAll(){
        return bevmRepository.findAll();
    }

    public void delete(Long bevmId) throws BevmNotFoundException {
        Long count = bevmRepository.countByBevmId(bevmId);
        if (count == 0 || count == null) {
            throw new BevmNotFoundException("Device ID " +bevmId+" Not Found");
        }
        bevmRepository.deleteById(bevmId);
    }

    public Optional<BEVM> findById(Long id){
        return bevmRepository.findById(id);
    }

}
