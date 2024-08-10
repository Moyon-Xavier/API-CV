package main.Controller.Project;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import main.DAO.Project.PeriodDAO;
import main.DTO.Project.PeriodDTO;

@WebServlet("/period/*")
public class PeriodRestAP extends HttpServlet {
    private PeriodDAO dao = new PeriodDAO();

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json;charset=UTF-8");
        PrintWriter out = res.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        String info = req.getPathInfo();
        String[] split = info.split("/");
        if (info == null || info.equals("/")) {
            Collection<PeriodDTO> periods = dao.getAllPeriod();
            String jsonstring = objectMapper.writeValueAsString(periods);
            out.println(jsonstring);
            return;
        }

        if (split.length > 2) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        } else {
            try {
                int code = Integer.parseInt(split[1]);
                PeriodDTO period = dao.getPeriodById(code);
                if (period.getPerno() == -1) {
                    res.sendError(HttpServletResponse.SC_NOT_FOUND);
                } else {
                    String jsoString = objectMapper.writeValueAsString(period);
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
