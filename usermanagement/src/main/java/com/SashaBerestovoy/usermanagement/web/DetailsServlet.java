package com.SashaBerestovoy.usermanagement.web;

import java.io.IOException;
import java.util.Collection;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;

import com.SashaBerestovoy.usermanagement.User;
import com.SashaBerestovoy.usermanagement.db.DaoFactory;
import com.SashaBerestovoy.usermanagement.db.DatabaseException;

public class DetailsServlet extends EditServlet {


	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("okButton") != null) {
            req.getRequestDispatcher("/browse").forward(req, resp);
            return;
        }
        showPage(req,resp);
    }
    
    protected void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/details.jsp").forward(req, resp);
        
    }
}
