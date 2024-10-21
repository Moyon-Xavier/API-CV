package main.DTO.Project;

import java.util.ArrayList;
import java.util.List;

import javax.print.DocFlavor.STRING;

import main.DAO.Project.PeriodDAO;
import main.DAO.Project.SkillsDAO;

public class ProjectDTO {

    private SkillsDAO skillDao = new SkillsDAO();
    private PeriodDAO periodDao = new PeriodDAO();
    int prono;
    String projectName;
    String dateProject;
    PeriodDTO period;
    String description;
    List<SkillsDTO> requirements;
    String imageURL;

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public ProjectDTO(int prono, String projectName, String dateProject,
            Integer period, String description, List<Integer> requirements, String imageURL) {

        this.prono = prono;
        this.projectName = projectName;
        this.dateProject = dateProject;
        this.period = convertIntegerToPeriodDTO(period);
        this.description = description;
        this.requirements = convertListIntegerToListSkillDTO(requirements);
        this.imageURL = imageURL;
    }

    private PeriodDTO convertIntegerToPeriodDTO(int period) {
        return periodDao.getPeriodById(period);
    }

    private SkillsDTO convertIntegerToSkillDTO(int skino) {
        return skillDao.getSkillById(skino);
    }

    private List<SkillsDTO> convertListIntegerToListSkillDTO(List<Integer> skinosInt) {
        List<SkillsDTO> skinos = new ArrayList<>();
        for (Integer skino : skinosInt) {
            skinos.add(convertIntegerToSkillDTO(skino));
        }
        return skinos;
    }

    public int getProno() {
        return prono;
    }

    public void setProno(int prono) {
        this.prono = prono;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDateProject() {
        return dateProject;
    }

    public void setDateProject(String dateProject) {
        this.dateProject = dateProject;
    }

    public PeriodDTO getPeriod() {
        return period;
    }

    public void setPeriod(PeriodDTO period) {
        this.period = period;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SkillsDTO> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<SkillsDTO> requirements) {
        this.requirements = requirements;
    }

}
