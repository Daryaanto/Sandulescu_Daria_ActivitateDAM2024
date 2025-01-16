package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dogs")

public class Dog implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int varsta;
    private String rasa;
    private String nume;

    private float greutate;
    private boolean adoptabil;

    public Dog() {
        this.greutate= 0;
        this.nume="cutu";
        this.rasa="maidanez";
        this.greutate=0;
        this.adoptabil=true;
    }

    public Dog(int varsta, String rasa, String nume, float greutate, boolean adoptabil) {

        this.varsta = varsta;
        this.rasa = rasa;
        this.nume = nume;
        this.greutate = greutate;
        this.adoptabil = adoptabil;
    }

    protected Dog(Parcel in) {
        id = in.readInt();
        varsta = in.readInt();
        rasa = in.readString();
        nume = in.readString();
        greutate = in.readFloat();
        adoptabil = in.readByte() != 0;
    }

    public static final Creator<Dog> CREATOR = new Creator<Dog>() {
        @Override
        public Dog createFromParcel(Parcel in) {
            return new Dog(in);
        }

        @Override
        public Dog[] newArray(int size) {
            return new Dog[size];
        }
    };

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
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "varsta=" + varsta +
                ", rasa='" + rasa + '\'' +
                ", nume='" + nume + '\'' +
                ", greutate=" + greutate +
                ", adoptabil=" + adoptabil +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(varsta);
        dest.writeString(rasa);
        dest.writeString(nume);
        dest.writeFloat(greutate);
        dest.writeByte((byte) (adoptabil ? 1 : 0));
    }
}
