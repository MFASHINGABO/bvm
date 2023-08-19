package com.wisdom.bevm.controllers.apis;

import com.wisdom.bevm.models.Citizen;
import com.wisdom.bevm.services.CitizenService;
import com.wisdom.bevm.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VotesApi {

//    @Autowired
//    private CitizenService citizenService;
//
//    @Autowired
//    private VoteService voteService;
//
//    @GetMapping("/enroll-finger")
//    public String enrollFinger(@RequestParam("f_id") Integer f_id){
//        Citizen byFingerPrintId = citizenService.findByFingerPrintId(f_id);
//        if (byFingerPrintId != null){
//            voteService.en
//        }
//        return "";
//    }
}
