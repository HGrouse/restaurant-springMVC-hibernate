package by.itacademy.restaurant.controller.command;

import by.itacademy.restaurant.bean.user.User;
import by.itacademy.restaurant.controller.ParameterName;
import by.itacademy.restaurant.service.EmployeeService;
import by.itacademy.restaurant.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class Authorization {

    private EmployeeService employeeService;

    @Autowired
    Authorization(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping("/authorization")
    public String execute(HttpServletRequest request,
                          @RequestParam(ParameterName.REQ_PARAM_LOGIN) String login,
                          @RequestParam(ParameterName.REQ_PARAM_PASSWORD) String password) {

        User user = null;
        String goToPage = null;

        HttpSession session = request.getSession(false);
        if (session == null) {
            session = request.getSession(true);
        }

        try {
            user = employeeService.logOn(login, password);

            if (user != null) {
                session.setAttribute(ParameterName.SESSION_ATTRIBUTE_USER, user);
                goToPage = "user_information";
            } else {
                goToPage = "index";
                //returnCode
            }

        } catch (ServiceException e) {
            //log.error(e);
            //goToPage = JSPPath.REDIRECT_TO_ERROR_PAGE;
        }
        //response.sendRedirect(goToPage);

        return goToPage;
    }

}
