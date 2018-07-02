package com.tua.bona.dodinghaleluya;

public class Doding {


    private String kategori;
    private String judul;
    private String lirik;
    private int no;

    public Doding(){}

    public Doding(String kategori, String judul, String lirik, int no) {
        this.kategori = kategori;
        this.judul = judul;
        this.lirik = lirik;
        this.no = no;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getLirik() {
        return lirik;
    }

    public void setLirik(String lirik) {
        this.lirik = lirik;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }


}
