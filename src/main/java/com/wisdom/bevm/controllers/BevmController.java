package com.wisdom.bevm.controllers;

import com.wisdom.bevm.exceptions.BevmNotFoundException;
import com.wisdom.bevm.exceptions.PollingCenterNotFoundException;
import com.wisdom.bevm.models.BEVM;
import com.wisdom.bevm.models.PollingCenter;
import com.wisdom.bevm.services.BEVMService;
import com.wisdom.bevm.services.PollingCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class BevmController {

    @Autowired
    private PollingCenterService pollingCenterService;

    @Autowired
    private BEVMService bevmService;

    @GetMapping("/devices")
    public String homePage(Model model){
        List<BEVM> bevms = bevmService.findAll();
        model.addAttribute("bevms", bevms);
        return "bevm";
    }

    @PostMapping("/devices")
    public String save(BEVM bevm, RedirectAttributes redirectAttributes) throws IOException {
        boolean isUpdating;
//        boolean isSupervisorUnique = true;
        Long pollingCenterId = bevm.getPollingCenterId();

//        System.out.println("Supervisor Roll No: " + supervisorRollNo);

        if(pollingCenterId == null){
            redirectAttributes.addFlashAttribute("message", "Polling Center Id must not be null");
            return "redirect:/devices";
        }

//        if (!pollingCenterService.isSupervisorUnique(supervisorRollNo) && pollingCenter.getSupervisorRollNo() != null){
//            redirectAttributes.addFlashAttribute("message",
//                    "Supervisor with NID " +pollingCenter.getPollingCenterId()+ " Already Exist!");
//            isSupervisorUnique = false;
////            return "redirect:/supervisors";
//        }

//        if(pollingCenter.getPollingCenterId() != null){
//            Long _supervisorRollNo = pollingCenterService.findById(pollingCenter.getPollingCenterId()).get().getSupervisorRollNo();
//
//            if (pollingCenter.getSupervisorRollNo() != _supervisorRollNo){
//                if (!isSupervisorUnique){
//                    return "redirect:/polling-centers";
//                }
//            }
//            isUpdating = true;
//        }else {
//            if (!isSupervisorUnique){
//                return "redirect:/polling-centers";
//            }
//            isUpdating = false;
//        }

        BEVM savedBevm = bevmService.add(bevm);

//        System.out.println(pollingCenter.getPollingCenterId());
//        System.out.println(multipartFile.getOriginalFilename());
//
//        if(!multipartFile.isEmpty()){
//            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//            supervisor.setPhoto(fileName);
//
//            Supervisor savedSupervisor = supervisorService.add(supervisor);
//
//            String uploadDir = "supervisor-photos/" + savedSupervisor.getRollNo();
//            FileUploadUtils.cleanDir(uploadDir);
//            FileUploadUtils.saveFile(uploadDir, fileName, multipartFile);
//        }else {
//            if (supervisor.getPhoto().isEmpty()){
//                supervisor.setPhoto(null);
//            }
//
//            supervisorService.add(supervisor);
//        }

//        if(isUpdating){
//            redirectAttributes.addFlashAttribute("message", "Polling Center ID " +pollingCenter.getPollingCenterId()+ " has been updated successfully!");
//        }else {
//            redirectAttributes.addFlashAttribute("message", "The Polling Center has been saved successfully!");
//        }

        return "redirect:/devices";
    }

    @GetMapping("/devices/add")
    public String showForm(BEVM bevm, Model model){
        bevm.setActive(true);
        model.addAttribute("bevm", bevm);
        model.addAttribute("pollingCenters", pollingCenterService.findAll());
        return "bevm_form";
    }

    @GetMapping("/devices/edit/{bevmId}")
    public String showEditAndDetailsForm(@PathVariable("bevmId") Long bevmId, Model model){
        Optional<BEVM> bevm = bevmService.findById(bevmId);
        if (bevm.isPresent()){
            BEVM _bevm = bevm.get();
            model.addAttribute("bevm", _bevm);
            model.addAttribute("pollingCenters", pollingCenterService.findAll());
        }else {
            model.addAttribute("message", "No Record found with Device ID " + bevmId);
        }

        return "bevm_form";
    }

    @GetMapping("/devices/delete/{bevmId}")
    public String delete(@PathVariable("bevmId") Long bevmId, RedirectAttributes redirectAttributes){
        try {
            bevmService.delete(bevmId);
            redirectAttributes.addFlashAttribute("message", "The Device ID " + bevmId + " has been deleted!");
        } catch (BevmNotFoundException ex) {
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
        }
        return "redirect:/devices";
    }

}
