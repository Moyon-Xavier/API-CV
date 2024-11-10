package fr.xavier.moyon.Controller.Project;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.xavier.moyon.DAO.Project.SkillsDAO;
import fr.xavier.moyon.DTO.Project.SkillsDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/skills/*")
public class SkillsRestAPI extends HttpServlet {
    private SkillsDAO dao = new SkillsDAO();

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json;charset=UTF-8");
        PrintWriter out = res.getWriter();
        res.setContentType("application/json;charset=UTF-8");
        res.addHeader("Access-Control-Allow-Origin", "*");
	ObjectMapper objectMapper = new ObjectMapper();
        String info = req.getPathInfo();
        String[] split = info.split("/");
        if (info == null || info.equals("/")) {
            Collection<SkillsDTO> l = dao.getListOfSkill();
            String jsonstring = objectMapper.writeValueAsString(l);
            out.println(jsonstring);
            return;
        }

        if (split.length > 2) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        } else {
            try {
                int code = Integer.parseInt(split[1]);
                SkillsDTO skill = dao.getSkillById(code);
                if (skill.getSkino() == -1) {
                    res.sendError(HttpServletResponse.SC_NOT_FOUND);
                } else {
                    String jsoString = objectMapper.writeValueAsString(skill);
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
