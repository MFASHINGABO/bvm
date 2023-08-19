package com.wisdom.bevm.controllers;

import com.wisdom.bevm.FileUploadUtils;
import com.wisdom.bevm.exceptions.CandidateNotFoundException;
import com.wisdom.bevm.exceptions.CitizenNotFoundException;
import com.wisdom.bevm.models.Candidate;
import com.wisdom.bevm.models.Citizen;
import com.wisdom.bevm.services.CandidateService;
import com.wisdom.bevm.services.CitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private CitizenService citizenService;

    @GetMapping("/candidates")
    public String homePage(Model model){
        List<Candidate> candidates = candidateService.findAll();
        model.addAttribute("candidates", candidates);
        return "candidate";
    }

    @PostMapping("/candidates")
    public String save(Candidate candidate, RedirectAttributes redirectAttributes,
                       @RequestParam("image") MultipartFile multipartFile) throws IOException{
        boolean isUpdating;
        boolean isCitizenUnique = true;
        Long nid = candidate.getNid();
        System.out.println("Citizen ID: " + nid);

        if(nid == null){
            return "redirect:/candidates";
        }

        if (!candidateService.isCitizenUnique(nid) && candidate.getNid() != null){
            redirectAttributes.addFlashAttribute("message", "Candidate with NID " +candidate.getNid()+ " Already Exist!");
            isCitizenUnique = false;
        }

        if(candidate.getCandidateId() != null){
            //TODO Consider doing this a better way later
            Long _nid = candidateService.findById(candidate.getCandidateId()).get().getNid();

            if (candidate.getNid() != _nid){
                if (!isCitizenUnique){
                    return "redirect:/supervisors";
                }
            }
            isUpdating = true;
        }else {
            if (!isCitizenUnique){
                return "redirect:/supervisors";
            }
            isUpdating = false;
        }

        System.out.println(candidate.getCandidateId());
        System.out.println(multipartFile.getOriginalFilename());

        if(!multipartFile.isEmpty()){
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            candidate.setPhoto(fileName);

            Candidate savedCandidate = candidateService.add(candidate);

            String uploadDir = "candidate-photos/" + savedCandidate.getCandidateId();
            FileUploadUtils.cleanDir(uploadDir);
            FileUploadUtils.saveFile(uploadDir, fileName, multipartFile);
        }else {
            if (candidate.getPhoto().isEmpty()){
                candidate.setPhoto(null);
            }

            candidateService.add(candidate);
        }

        if(isUpdating){
            redirectAttributes.addFlashAttribute("message", "Candidate ID " +candidate.getCandidateId()+ " has been updated successfully!");
        }else {
            redirectAttributes.addFlashAttribute("message", "The Candidate has been saved successfully!");
        }

        return "redirect:/candidates";
    }

    @GetMapping("/candidates/add")
    public String showForm(Candidate candidate, Model model){
        model.addAttribute("candidate", candidate);
        model.addAttribute("citizens", citizenService.findAll());
        return "candidate_form";
    }

    @GetMapping("/candidates/edit/{candidateId}")
    public String showEditAndDetailsForm(@PathVariable("candidateId") Long candidateId, Model model){
        Optional<Candidate> candidate = candidateService.findById(candidateId);
        if (candidate.isPresent()){
            Candidate _candidate = candidate.get();
            model.addAttribute("candidate", _candidate);
            model.addAttribute("citizens", citizenService.findAll());
        }else {
            model.addAttribute("message", "No Record found with candidateId " + candidateId);
        }

        return "candidate_form";
    }

    @GetMapping("/candidates/delete/{candidateId}")
    public String delete(@PathVariable("candidateId") Long candidateId, RedirectAttributes redirectAttributes){
        try {
            candidateService.delete(candidateId);
            redirectAttributes.addFlashAttribute("message", "The Candidate NID " + candidateId + " has been deleted!");
        } catch (CandidateNotFoundException ex) {
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
        }
        return "redirect:/candidates";
    }

}