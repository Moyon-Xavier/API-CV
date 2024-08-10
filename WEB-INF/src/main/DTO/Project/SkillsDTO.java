package main.DTO.Project;

public class SkillsDTO {
    int skino;
    String title;

    public SkillsDTO() {

    }

    public SkillsDTO(int skino, String title) {
        this.skino = skino;
        this.title = title;
    }

    public int getSkino() {
        return skino;
    }

    public void setSkino(int skino) {
        this.skino = skino;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
