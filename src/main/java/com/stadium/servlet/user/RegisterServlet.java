package com.stadium.servlet.user;

import com.stadium.service.UserService;
import com.stadium.service.impl.UserServiceImpl;
import com.stadium.utils.ThymeleafUtil;
import org.thymeleaf.context.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    UserService service;
    @Override
    public void init() throws ServletException {
        service = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        if (req.getSession().getAttribute("register-failure") != null){
            context.setVariable("failure1", true);
            req.getSession().removeAttribute("register-failure");
        }
        ThymeleafUtil.process("register.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String telephone = req.getParameter("telephone");
        String identity = req.getParameter("identity");
        if(!Objects.equals(username, "")&&!Objects.equals(password, "")&&!Objects.equals(telephone, "")&&!Objects.equals(identity, "")){
            boolean result = service.register(username, password, telephone, identity);
            if(result) {
                resp.sendRedirect("login");
            }else {
                req.getSession().setAttribute("register-failure", new Object());
                resp.sendRedirect("register");
            }
        }else {
            req.getSession().setAttribute("register-failure", new Object());
            resp.sendRedirect("register");
        }

    }
}
