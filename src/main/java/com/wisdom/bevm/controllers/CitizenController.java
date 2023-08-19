package com.wisdom.bevm.controllers;

import com.wisdom.bevm.FileUploadUtils;
import com.wisdom.bevm.exceptions.CandidateNotFoundException;
import com.wisdom.bevm.exceptions.CitizenNotFoundException;
import com.wisdom.bevm.models.Citizen;
import com.wisdom.bevm.services.CitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class CitizenController {

    //TODO Add Pagination and Sorting
    @Autowired
    private CitizenService citizenService;

    @GetMapping("/citizens")
    public String homePage(Model model){
        List<Citizen> citizens = citizenService.findAll();
        model.addAttribute("citizens", citizens);
        return "citizen";
    }

    @PostMapping("/citizens")
    public String save(Citizen citizen, RedirectAttributes redirectAttributes,
                       @RequestParam("image")MultipartFile multipartFile) throws IOException {

        System.out.println(citizen.getNid());
        System.out.println(multipartFile.getOriginalFilename());

        if(!multipartFile.isEmpty()){
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            citizen.setPhoto(fileName);
            Citizen savedCitizen = citizenService.add(citizen);

            String uploadDir = "citizen-photos/" + savedCitizen.getNid();
            FileUploadUtils.cleanDir(uploadDir);
            FileUploadUtils.saveFile(uploadDir, fileName, multipartFile);
        }else {
            if (citizen.getPhoto().isEmpty()){
                citizen.setPhoto(null);
            }

            citizenService.add(citizen);
        }

        if(citizen.getNid() != null){
            redirectAttributes.addFlashAttribute("message", "Citizen NID " +citizen.getNid()+ " has been updated successfully!");
        }else {
            redirectAttributes.addFlashAttribute("message", "The Citizen has been saved successfully!");
        }
        return "redirect:/citizens";
    }

    @GetMapping("/citizens/add")
    public String showForm(Citizen citizen, Model model){
//        Citizen citizen = new Citizen();
//        citizen.setPhoto("iii");
        model.addAttribute("citizen", citizen);
        return "citizen_form";
    }

    @GetMapping("/citizens/edit/{nid}")
    public String showEditAndDetailsForm(@PathVariable("nid") Long nid, Model model){
        Optional<Citizen> citizen = citizenService.findById(nid);
        if (citizen.isPresent()){
            Citizen _citizen = citizen.get();
            model.addAttribute("citizen", _citizen);
        }else {
            model.addAttribute("message", "No Record found with id " + nid);
        }

        return "citizen_form";
    }

    @GetMapping("/citizens/delete/{nid}")
    public String delete(@PathVariable("nid") Long nid, RedirectAttributes redirectAttributes){
        try {
            citizenService.delete(nid);
            redirectAttributes.addFlashAttribute("message", "The Candidate NID " + nid + " has been deleted!");
        } catch (CitizenNotFoundException ex) {
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
        }
        return "redirect:/citizens";
    }

    @GetMapping("/fingerprintid")
    public String enrollFinger(@RequestParam("f_id") Integer f_id, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("f_id", f_id);
        return "redirect:/citizens";
    }

    @GetMapping("/enroll-finger/{nid}")
    public String enrollFinger(@RequestParam("f_id") Integer f_id, @PathVariable("nid") Long nid, RedirectAttributes redirectAttributes){
        Optional<Citizen> citizen = citizenService.findById(nid);
        if (citizen.isPresent()){
            Citizen _citizen = citizen.get();
            _citizen.setFingerPrintId(f_id);
            Citizen updatedCitizen = citizenService.add(_citizen);
            redirectAttributes.addFlashAttribute("message", "Citizen NID " + updatedCitizen.getNid() + " finger print " + updatedCitizen.getFingerPrintId() + " added successfully!");
        }
        return "redirect:/citizens";
    }
}
