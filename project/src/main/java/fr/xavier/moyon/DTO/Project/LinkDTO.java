package fr.xavier.moyon.DTO.Project;

public class LinkDTO {
    int prono;
    int linno;
    String title;
    String lien;
    public int getProno() {
        return prono;
    }
    public void setProno(int prono) {
        this.prono = prono;
    }
    public int getLinno() {
        return linno;
    }
    public void setLinno(int linno) {
        this.linno = linno;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getLien() {
        return lien;
    }
    public void setLien(String lien) {
        this.lien = lien;
    }
    public LinkDTO(){

    }
    public LinkDTO(int prono,int linno,String title, String lien){
        this.prono=prono;
        this.linno=linno;
        this.title=title;
        this.lien=lien;
    }

}