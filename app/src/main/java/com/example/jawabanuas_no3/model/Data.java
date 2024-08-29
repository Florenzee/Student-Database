package com.example.jawabanuas_no3.model;

public class Data {
    private String id, name, nim, matkul;
    private Float ipk;

    public Data() {

    }

    public Data(String id, String name, String nim, Float ipk, String matkul) {
        this.id = id;
        this.name = name;
        this.nim = nim;
        this.ipk = ipk;
        this.matkul = matkul;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getMatkul() {
        return matkul;
    }

    public void setMatkul(String matkul) {
        this.matkul = matkul;
    }

    public Float getIpk() {
        return ipk;
    }

    public void setIpk(Float ipk) {
        this.ipk = ipk;
    }
}
