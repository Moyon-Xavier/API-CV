package main.DAO.Project;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import main.DTO.Project.PeriodDTO;
import main.DTO.Project.SkillsDTO;
import main.utils.DB;
import main.utils.Exception.DataNotFoundException;

public class PeriodDAO {
    public List<PeriodDTO> getAllPeriod() {
        List<PeriodDTO> periodList = new ArrayList<>();
        Connection con = null;
        try {
            con = DB.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * From Period");
            while (rs.next()) {
                int perno = rs.getInt(1);
                String title = rs.getString(2);
                periodList.add(new PeriodDTO(perno, title));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return periodList;
    }

    public PeriodDTO getPeriodById(int id) {
        PeriodDTO period = null;
        Connection con = null;
        try {
            con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("select * From Period Where perno = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int perno = rs.getInt(1);
                String title = rs.getString(2);
                period = new PeriodDTO(perno, title);
            } else {
                period = new PeriodDTO(-1, "periode non trouvée dans la base de données");
                throw new DataNotFoundException("periode " + id + " non trouvé");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return period;
    }
}
