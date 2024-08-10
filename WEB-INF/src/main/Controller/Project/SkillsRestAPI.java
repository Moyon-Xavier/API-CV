package main.Controller.Project;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.DAO.Project.SkillsDAO;
import main.DTO.Project.SkillsDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/skills/*")
public class SkillsRestAPI extends HttpServlet {
    private SkillsDAO dao = new SkillsDAO();

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json;charset=UTF-8");
        PrintWriter out = res.getWriter();
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
