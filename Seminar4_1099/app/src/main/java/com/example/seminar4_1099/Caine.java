package com.example.seminar4_1099;

public class Caine {
    private int varsta;
    private String rasa;
    private String nume;

    private float greutate;
    private boolean adoptabil;

    public Caine() {
        this.greutate= 0;
        this.nume="cutu";
        this.rasa="maidanez";
        this.greutate=0;
        this.adoptabil=true;
    }

    public Caine(int varsta, String rasa, String nume, float greutate,boolean adoptabil) {
        this.varsta = varsta;
        this.rasa = rasa;
        this.nume = nume;
        this.greutate = greutate;
        this.adoptabil=adoptabil;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public String getRasa() {
        return rasa;
    }

    public void setRasa(String rasa) {
        this.rasa = rasa;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public float getGreutate() {
        return greutate;
    }

    public void setGreutate(float greutate) {
        this.greutate = greutate;
    }

    public boolean isAdoptabil() {
        return adoptabil;
    }

    public void setAdoptabil(boolean adoptabil) {
        this.adoptabil = adoptabil;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Caine{");
        sb.append("varsta=").append(varsta);
        sb.append(", rasa='").append(rasa).append('\'');
        sb.append(", nume='").append(nume).append('\'');
        sb.append(", adoptabil='").append(adoptabil).append('\'');
        sb.append(", greutate=").append(greutate);
        sb.append('}');
        return sb.toString();
    }
}
