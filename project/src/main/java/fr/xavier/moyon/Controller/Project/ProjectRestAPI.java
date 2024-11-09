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

@WebServlet("/project/*")
public class ProjectRestAPI extends HttpServlet {
    private ProjectDAO dao = new ProjectDAO();

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json;charset=UTF-8");
        res.addHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = res.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        String info = req.getPathInfo();
        String[] split = info.split("/");
        if (info == null || info.equals("/")) {/*
                                                * Collection<PeriodDTO> periods = dao.getAllPeriod();
                                                * String jsonstring = objectMapper.writeValueAsString(periods);
                                                * out.println(jsonstring);
                                                * return;
                                                */
        }

        if (split.length > 2) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        } else {
            try {
                int code = Integer.parseInt(split[1]);
                ProjectDTO projet = dao.getProjectById(code);
                System.out.println(projet.toString());
                if (projet.getProno() == -1) {
                    res.sendError(HttpServletResponse.SC_NOT_FOUND);
                } else {
                    String jsoString = objectMapper.writeValueAsString(projet);
                    out.println(jsoString);
                }

                return;

            } catch (NumberFormatException e) {
                out.println("Le parametre entré ne correspond pas au criteres defini (Ce doit être un nombre): "
                        + e.getMessage());

            }
        }
        return;

    }
}
