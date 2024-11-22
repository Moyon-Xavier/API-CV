package fr.xavier.moyon.Controller.Project;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.xavier.moyon.DAO.Project.ProjectDAO;
import fr.xavier.moyon.DTO.Project.ProjectDTO;
import fr.xavier.moyon.DTO.Project.ProjectDTOWithNumber;

@WebServlet("/project/*")
public class ProjectRestAPI extends HttpServlet {
    private ProjectDAO dao = new ProjectDAO();

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json;charset=UTF-8");
        res.addHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = res.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        String info = req.getPathInfo();
        
        if (info == null || info.equals("/")) {
            Collection<ProjectDTO> projects = dao.getAllProject();
            String jsonstring = objectMapper.writeValueAsString(projects);
            out.println(jsonstring);
            return;

        }
        String[] split = info.split("/");
        if (split.length > 2) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        } else {
            try {
                int code = Integer.parseInt(split[1]);
                ProjectDTO projet = dao.getProjectById(code);
                if (projet == null) {
                    res.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
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
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.addHeader("Access-Control-Allow-Origin", "*");
        
        PrintWriter out = res.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        ProjectDTO result =null;
        String info = req.getPathInfo();
        //out.println(info);
        ProjectDTOWithNumber pr = new ProjectDTOWithNumber(-1,req.getParameter("projectName"),req.getParameter("dateProject"),Integer.parseInt(req.getParameter("perno")),req.getParameter("description"),req.getParameter("imageUrl"));
        //List<String> requirements = List.of(req.getParameter("requirements"));
        String[]reqs = req.getParameterValues("requirements");
        List<Integer> requir = new ArrayList<>();
        for (int i = 0; i < reqs.length; i++) {
            requir.add(Integer.parseInt(reqs[i]));
        }
        pr.setRequirements(requir);
        //out.println("out + " + pr);
        
        //out.println(req.getParameter("requirements").length);
           
        if (info == null || info.equals("/")) {
            String data = req.getReader().readLine();
            //out.println(data);
            //ProjectDTOWithNumber pr = objectMapper.readValue(data, ProjectDTOWithNumber.class);
            //ProjectDTOWithNumber pr = new ProjectDTOWithNumber(-1,req.getParameter("projectName"),req.getParameter("dateProject"),Integer.parseInt(req.getParameter("perno")),req.getParameter("description"),req.getParameter("imageUrl"));
            //out.println(pr);
            try {
                result = dao.addProject(pr);
                out.println(objectMapper.writeValueAsString(result));
            } catch (Exception e) {
                out.println(e.getMessage());
                for (int i = 0; i<e.getStackTrace().length;i++){
                    out.println(e.getStackTrace()[i].toString());
                }
            
                
            }
            
            
        }
    }
}
