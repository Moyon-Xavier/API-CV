package fr.xavier.moyon.DTO.Project;

public class PeriodDTO {
    private int perno;
    private String name;

    public PeriodDTO(int perno, String name) {
        this.perno = perno;
        this.name = name;
    }

    public int getPerno() {
        return perno;
    }

    public void setPerno(int perno) {
        this.perno = perno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
