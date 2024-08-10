package main.DAO.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.DTO.Project.ProjectDTO;
import main.DTO.Project.ProjectDTOWithNumber;
import main.utils.DB;
import main.utils.Exception.DataNotFoundException;

public class ProjectDAO {
    /*
     * public List<ProjectDTO> getAllPeriod() {
     * List<ProjectDTO> periodList = new ArrayList<>();
     * Connection con = null;
     * try {
     * con = DB.getConnection();
     * Statement stmt = con.createStatement();
     * ResultSet rs = stmt.executeQuery("select * From Period");
     * while (rs.next()) {
     * int perno = rs.getInt(1);
     * String title = rs.getString(2);
     * periodList.add(new ProjectDTO(perno, title));
     * }
     * 
     * } catch (Exception e) {
     * e.printStackTrace();
     * } finally {
     * try {
     * con.close();
     * } catch (Exception e) {
     * e.printStackTrace();
     * }
     * }
     * return periodList;
     * }
     */

    private void getProjectRequirements(ProjectDTOWithNumber project, Connection con) {
        try {
            PreparedStatement ps = con.prepareStatement("Select skino From requirement Where prono = ?");
            ps.setInt(1, project.getProno());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                project.addRequirements(rs.getInt(1));
            }
        } catch (Exception e) {

        }

    }

    public ProjectDTO getProjectById(int id) {
        ProjectDTOWithNumber projet = null;
        ProjectDTO projetFinal = null;
        Connection con = null;
        try {
            con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("select * From project Where prono = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int prono = rs.getInt("prono");
                String projectName = rs.getString("projectName");
                String dateProject = rs.getString("dateproject");
                int perno = rs.getInt("perno");
                String description = rs.getString("description");
                projet = new ProjectDTOWithNumber(prono, projectName, dateProject, perno, description);
                getProjectRequirements(projet, con);
                projetFinal = new ProjectDTO(prono, projectName, dateProject, perno, description,
                        projet.getRequirements());

            } else {
                projetFinal = new ProjectDTO(-1, "periode non trouvée dans la base de données", null, null, null, null);
                throw new DataNotFoundException("periode " + id + " non trouvée");
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
        return projetFinal;
    }
}
