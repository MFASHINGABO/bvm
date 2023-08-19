package com.wisdom.bevm.controllers;

import com.wisdom.bevm.FileUploadUtils;
import com.wisdom.bevm.exceptions.SupervisorNotFoundException;
import com.wisdom.bevm.models.Supervisor;
import com.wisdom.bevm.models.Supervisor;
import com.wisdom.bevm.services.SupervisorService;
import com.wisdom.bevm.services.CitizenService;
import com.wisdom.bevm.services.SupervisorService;
import net.bytebuddy.implementation.bind.annotation.Super;
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
public class SupervisorController {

    @Autowired
    private SupervisorService supervisorService;

    @Autowired
    private CitizenService citizenService;

    @GetMapping("/supervisors")
    public String homePage(Model model){
        List<Supervisor> supervisors = supervisorService.findAll();
        model.addAttribute("supervisors", supervisors);
        return "supervisor";
    }

    @PostMapping("/supervisors")
    public String save(Supervisor supervisor, RedirectAttributes redirectAttributes,
                       @RequestParam("image") MultipartFile multipartFile) throws IOException {
        boolean isUpdating;
        boolean isCitizenUnique = true;
        Long nid = supervisor.getNid();

        System.out.println("Citizen Nid: " + nid);

        if(nid == null){
            return "redirect:/supervisors";
        }

        if (!supervisorService.isCitizenUnique(nid) && supervisor.getNid() != null){
            redirectAttributes.addFlashAttribute("message", "Supervisor with NID " +supervisor.getNid()+ " Already Exist!");
            isCitizenUnique = false;
//            return "redirect:/supervisors";
        }

        if(supervisor.getRollNo() != null){
            //TODO Consider doing this a better way later
            Long _nid = supervisorService.findById(supervisor.getRollNo()).get().getNid();

            if (supervisor.getNid() != _nid){
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

        System.out.println(supervisor.getRollNo());
        System.out.println(multipartFile.getOriginalFilename());

        if(!multipartFile.isEmpty()){
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            supervisor.setPhoto(fileName);

            Supervisor savedSupervisor = supervisorService.add(supervisor);

            String uploadDir = "supervisor-photos/" + savedSupervisor.getRollNo();
            FileUploadUtils.cleanDir(uploadDir);
            FileUploadUtils.saveFile(uploadDir, fileName, multipartFile);
        }else {
            if (supervisor.getPhoto().isEmpty()){
                supervisor.setPhoto(null);
            }

            supervisorService.add(supervisor);
        }

        if(isUpdating){
            redirectAttributes.addFlashAttribute("message", "Supervisor RollNo " +supervisor.getRollNo()+ " has been updated successfully!");
        }else {
            redirectAttributes.addFlashAttribute("message", "The Supervisor has been saved successfully!");
        }

        return "redirect:/supervisors";
    }

    @GetMapping("/supervisors/add")
    public String showForm(Supervisor supervisor, Model model){
        model.addAttribute("supervisor", supervisor);
        model.addAttribute("citizens", citizenService.findAll());
        return "supervisor_form";
    }

    @GetMapping("/supervisors/edit/{rollNo}")
    public String showEditAndDetailsForm(@PathVariable("rollNo") Long rollNo, Model model){
        Optional<Supervisor> supervisor = supervisorService.findById(rollNo);
        if (supervisor.isPresent()){
            Supervisor _supervisor = supervisor.get();
            model.addAttribute("supervisor", _supervisor);
            model.addAttribute("citizens", citizenService.findAll());
        }else {
            model.addAttribute("message", "No Record found with ID " + rollNo);
        }

        return "supervisor_form";
    }

    @GetMapping("/supervisors/delete/{rollNo}")
    public String delete(@PathVariable("rollNo") Long rollNo, RedirectAttributes redirectAttributes){
        try {
            supervisorService.delete(rollNo);
            redirectAttributes.addFlashAttribute("message", "The Supervisor RollNo " + rollNo + " has been deleted!");
        } catch (SupervisorNotFoundException ex) {
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
        }
        return "redirect:/supervisors";
    }

}
