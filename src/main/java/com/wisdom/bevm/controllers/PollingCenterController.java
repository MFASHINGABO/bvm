package com.wisdom.bevm.controllers;

import com.wisdom.bevm.FileUploadUtils;
import com.wisdom.bevm.exceptions.PollingCenterNotFoundException;
import com.wisdom.bevm.exceptions.SupervisorNotFoundException;
import com.wisdom.bevm.models.PollingCenter;
import com.wisdom.bevm.models.Supervisor;
import com.wisdom.bevm.services.CitizenService;
import com.wisdom.bevm.services.PollingCenterService;
import com.wisdom.bevm.services.SupervisorService;
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
public class PollingCenterController {

    @Autowired
    private PollingCenterService pollingCenterService;

    @Autowired
    private SupervisorService supervisorService;

    @GetMapping("/polling-centers")
    public String homePage(Model model){
        List<PollingCenter> pollingCenters = pollingCenterService.findAll();
        model.addAttribute("pollingCenters", pollingCenters);
        return "polling-center";
    }

    @PostMapping("/polling-centers")
    public String save(PollingCenter pollingCenter, RedirectAttributes redirectAttributes) throws IOException {
        boolean isUpdating;
        boolean isSupervisorUnique = true;
        Long supervisorRollNo = pollingCenter.getSupervisorRollNo();

        System.out.println("Supervisor Roll No: " + supervisorRollNo);

        if(supervisorRollNo == null){
            redirectAttributes.addFlashAttribute("message", "Roll No must not be null");
            return "redirect:/polling-centers";
        }

        if (!pollingCenterService.isSupervisorUnique(supervisorRollNo) && pollingCenter.getSupervisorRollNo() != null){
            redirectAttributes.addFlashAttribute("message",
                    "Supervisor with NID " +pollingCenter.getPollingCenterId()+ " Already Exist!");
            isSupervisorUnique = false;
//            return "redirect:/supervisors";
        }

        if(pollingCenter.getPollingCenterId() != null){
            //TODO Consider doing this a better way later
            Long _supervisorRollNo = pollingCenterService.findById(pollingCenter.getPollingCenterId()).get().getSupervisorRollNo();

            if (pollingCenter.getSupervisorRollNo() != _supervisorRollNo){
                if (!isSupervisorUnique){
                    return "redirect:/polling-centers";
                }
            }
            isUpdating = true;
        }else {
            if (!isSupervisorUnique){
                return "redirect:/polling-centers";
            }
            isUpdating = false;
        }

        PollingCenter savedPollingCenter = pollingCenterService.add(pollingCenter);

        if(isUpdating){
            redirectAttributes.addFlashAttribute("message", "Polling Center ID " +pollingCenter.getPollingCenterId()+ " has been updated successfully!");
        }else {
            redirectAttributes.addFlashAttribute("message", "The Polling Center has been saved successfully!");
        }

        return "redirect:/polling-centers";
    }

    @GetMapping("/polling-centers/add")
    public String showForm(PollingCenter pollingCenter, Model model){
        model.addAttribute("pollingCenter", pollingCenter);
        model.addAttribute("supervisors", supervisorService.findAll());
        return "polling-center_form";
    }

    @GetMapping("/polling-centers/edit/{pollingCenterId}")
    public String showEditAndDetailsForm(@PathVariable("pollingCenterId") Long pollingCenterId, Model model){
        Optional<PollingCenter> pollingCenter = pollingCenterService.findById(pollingCenterId);
        if (pollingCenter.isPresent()){
            PollingCenter _pollingCenter = pollingCenter.get();
            model.addAttribute("pollingCenter", _pollingCenter);
            model.addAttribute("supervisors", supervisorService.findAll());
        }else {
            model.addAttribute("message", "No Record found with Polling Center ID " + pollingCenterId);
        }

        return "polling-center_form";
    }

    @GetMapping("/polling-centers/delete/{pollingCenterId}")
    public String delete(@PathVariable("pollingCenterId") Long pollingCenterId, RedirectAttributes redirectAttributes){
        try {
            pollingCenterService.delete(pollingCenterId);
            redirectAttributes.addFlashAttribute("message", "The Supervisor RollNo " + pollingCenterId + " has been deleted!");
        } catch (PollingCenterNotFoundException ex) {
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
        }
        return "redirect:/polling-centers";
    }
}
