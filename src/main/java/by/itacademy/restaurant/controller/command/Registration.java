package by.itacademy.restaurant.controller.command;

import by.itacademy.restaurant.bean.user.RegistrationUserInfo;
import by.itacademy.restaurant.bean.user.User;
import by.itacademy.restaurant.service.EmployeeService;
import by.itacademy.restaurant.service.ServiceException;
import by.itacademy.restaurant.service.validation.VerificationCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class Registration {

    private static final Logger log = LogManager.getLogger(Registration.class);
    private EmployeeService employeeService;

    @Autowired
    public Registration(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping("/toRegistration")
    public String doModel(Model model) {

        RegistrationUserInfo info = new RegistrationUserInfo();
        model.addAttribute("infoForRegistration", info);
        return "registration";
    }

    @RequestMapping("/doRegistration")
    public String execute(@ModelAttribute("infoForRegistration") RegistrationUserInfo info,
                          Model model) {

        int verificationCode = VerificationCode.CORRECT_INFORMATION;

        try {
            verificationCode = employeeService.register(info);
            System.out.println(info.toString());
            System.out.println(verificationCode);
            if (verificationCode == VerificationCode.CORRECT_INFORMATION) {
                model.addAttribute("code", verificationCode);
                return "toIndex";
            } else {
                return "registration";
            }
        } catch (ServiceException e) {
            log.error(e);
            return "toIndex";
        }
    }
}
