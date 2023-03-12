package com.stadium.servlet;

import com.stadium.entity.User;
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

@WebServlet("/personal")
public class PersonalServlet extends HttpServlet {
    UserService service;
    @Override
    public void init() throws ServletException {
        service = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        User user = (User) req.getSession().getAttribute("user");
        context.setVariable("studentid", user.getUserid());
        context.setVariable("username", user.getUsername());
        context.setVariable("password", user.getPassword());
        context.setVariable("telephone", user.getTelephone());
        ThymeleafUtil.process("personal.html", context, resp.getWriter());
    }
}
