package fr.xavier.moyon.DTO.Project;
public class YTBLinkDTO {
    int prono;
    int ytbno;
    String title;
    String iframe;
    public YTBLinkDTO(int prono, int ytbno, String title, String iframe) {
        this.prono = prono;
        this.ytbno = ytbno;
        this.title = title;
        this.iframe = iframe;
    }
    public int getProno() {
        return prono;
    }
    public void setProno(int prono) {
        this.prono = prono;
    }
    public int getYtbno() {
        return ytbno;
    }
    public void setYtbno(int ytbno) {
        this.ytbno = ytbno;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getIframe() {
        return iframe;
    }
    public void setIframe(String iframe) {
        this.iframe = iframe;
    }

}
