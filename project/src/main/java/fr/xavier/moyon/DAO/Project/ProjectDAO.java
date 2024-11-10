package fr.xavier.moyon.DAO.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.xavier.moyon.DTO.Project.ProjectDTO;
import fr.xavier.moyon.DTO.Project.ProjectDTOWithNumber;
import fr.xavier.moyon.DTO.Project.LinkDTO;
import fr.xavier.moyon.DTO.Project.YTBLinkDTO;
import fr.xavier.moyon.utils.DB;
import fr.xavier.moyon.utils.Exception.DataNotFoundException;

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
    private void getProjectLink(ProjectDTO project,Connection con){
        try {
            List<LinkDTO> listLink = new ArrayList<>();
            PreparedStatement ps = con.prepareStatement("Select * From linkInProject Where prono = ?");
            ps.setInt(1, project.getProno());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                listLink.add(new LinkDTO(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4)));
                
            }
            project.setLink(listLink);
        } catch (Exception e) {

        }
    }

    private void getProjectYtbLink(ProjectDTO project,Connection con){
        try {
            List<YTBLinkDTO> listLink = new ArrayList<>();
            PreparedStatement ps = con.prepareStatement("Select * From ytbLink Where prono = ?");
            ps.setInt(1, project.getProno());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                listLink.add(new YTBLinkDTO(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4)));
                
            }
            project.setYtbLink(listLink);
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
                String imageUrl = rs.getString("imageUrl");
                projet = new ProjectDTOWithNumber(prono, projectName, dateProject, perno, description, imageUrl);
                getProjectRequirements(projet, con);
                projetFinal = new ProjectDTO(prono, projectName, dateProject, perno, description,
                        projet.getRequirements(), imageUrl);
                this.getProjectLink(projetFinal, con);
                this.getProjectYtbLink(projetFinal, con);
            } else {
                projetFinal = new ProjectDTO(-1, "periode non trouvée dans la base de données", null, null, null, null,
                        null);
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

    public List<ProjectDTO> getAllProject() {
        List<ProjectDTO> projets = new ArrayList<>();
        ProjectDTOWithNumber projet = null;
        ProjectDTO projetFinal = null;
        Connection con = null;
        try {
            con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("select * From project ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int prono = rs.getInt("prono");
                String projectName = rs.getString("projectName");
                String dateProject = rs.getString("dateproject");
                int perno = rs.getInt("perno");
                String description = rs.getString("description");
                String imageUrl = rs.getString("imageUrl");
                projet = new ProjectDTOWithNumber(prono, projectName, dateProject, perno, description, imageUrl);
                getProjectRequirements(projet, con);
                projetFinal = new ProjectDTO(prono, projectName, dateProject, perno, description,
                        projet.getRequirements(), imageUrl);
                        this.getProjectLink(projetFinal, con);
                        this.getProjectYtbLink(projetFinal, con);
                projets.add(projetFinal);

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
        return projets;
    }

}
