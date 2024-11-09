package fr.xavier.moyon.Controller.Project;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.xavier.moyon.DAO.Project.ProjectDAO;
import fr.xavier.moyon.DTO.Project.ProjectDTO;

@WebServlet("/test/*")
public class Test extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json;charset=UTF-8");
        res.addHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = res.getWriter();
        out.println("Hello From Test");

    }
}
