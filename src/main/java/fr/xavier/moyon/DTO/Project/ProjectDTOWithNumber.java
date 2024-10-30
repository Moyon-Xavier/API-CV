package fr.xavier.moyon.DTO.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectDTOWithNumber {
    int prono;
    String projectName;
    String dateProject;
    int perno;
    String description;
    List<Integer> requirements;
    String imageURL;

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public ProjectDTOWithNumber(int prono, String projectName, String dateProject, int perno, String descriptio,
            String iamgeURL) {
        this.prono = prono;
        this.projectName = projectName;
        this.dateProject = dateProject;
        this.perno = perno;
        this.description = description;
        this.requirements = new ArrayList<>();
        this.imageURL = imageURL;
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

    public int getPerno() {
        return perno;
    }

    public void setPerno(int perno) {
        this.perno = perno;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<Integer> requirements) {
        this.requirements = requirements;
    }

    public void addRequirements(Integer requirement) {
        requirements.add(requirement);
    }

}
