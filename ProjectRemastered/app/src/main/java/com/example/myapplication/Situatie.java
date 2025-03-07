package com.example.myapplication;

public class Situatie {
    private String disciplina;
    private String activitate;

    private  int valoare;
    private double pondere;
    private String data;
    private String descriere;

    public Situatie(String disciplina, String activitate, int valoare, double pondere, String data, String descriere) {
        this.disciplina = disciplina;
        this.activitate = activitate;
        this.valoare = valoare;
        this.pondere = pondere;
        this.data = data;
        this.descriere = descriere;
    }

    @Override
    public String toString() {
        return "Situatie{" +
                "disciplina='" + disciplina + '\'' +
                ", activitate='" + activitate + '\'' +
                ", valoare=" + valoare +
                ", pondere=" + pondere +
                ", data='" + data + '\'' +
                ", descriere='" + descriere + '\'' +
                '}';
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getActivitate() {
        return activitate;
    }

    public void setActivitate(String activitate) {
        this.activitate = activitate;
    }

    public int getValoare() {
        return valoare;
    }

    public void setValoare(int valoare) {
        this.valoare = valoare;
    }

    public double getPondere() {
        return pondere;
    }

    public void setPondere(double pondere) {
        this.pondere = pondere;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }
}
