package main.DAO.Project;

import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import main.utils.Exception.*;

import main.DTO.Project.SkillsDTO;
import main.utils.DB;

public class SkillsDAO {
    public List<SkillsDTO> getListOfSkill() {

        List<SkillsDTO> myListOfSkills = new ArrayList<>();
        Connection con = null;
        try {
            con = DB.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select * From skills");
            int skino;
            String title;
            SkillsDTO temp;
            while (rs.next()) {
                skino = rs.getInt(1);
                title = rs.getString(2);
                temp = new SkillsDTO(skino, title);
                myListOfSkills.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception e) {

            }
        }

        return myListOfSkills;

    }

    public SkillsDTO getSkillById(int id) {
        Connection con = null;
        SkillsDTO result = null;
        try {
            con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("Select * From skills Where skino = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int skino = rs.getInt(1);
                String title = rs.getString(2);
                result = new SkillsDTO(skino, title);
            } else {
                result = new SkillsDTO(-1, "competence non trouvée dans la base de données");
                throw new DataNotFoundException("skills " + id + " non trouvé");
            }
        } catch (DataNotFoundException e) {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
