package com.oods.thelast.model;

import java.io.Serializable;

public class Perusahaan implements Serializable {
    private String namaPerusahaan;
    private String namaPemimpin;
    private String email;
    private String kontak;
    private String bidangUsaha;

    public Perusahaan(String namaPerusahaan, String namaPemimpin, String email, String kontak, String bidangUsaha) {
        this.namaPerusahaan = namaPerusahaan;
        this.namaPemimpin = namaPemimpin;
        this.email = email;
        this.kontak = kontak;
        this.bidangUsaha = bidangUsaha;
    }

    public String getNamaPerusahaan() {
        return namaPerusahaan;
    }

    public void setNamaPerusahaan(String namaPerusahaan) {
        this.namaPerusahaan = namaPerusahaan;
    }

    public String getNamaPemimpin() {
        return namaPemimpin;
    }

    public void setNamaPemimpin(String namaPemimpin) {
        this.namaPemimpin = namaPemimpin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKontak() {
        return kontak;
    }

    public void setKontak(String kontak) {
        this.kontak = kontak;
    }

    public String getBidangUsaha() {
        return bidangUsaha;
    }

    public void setBidangUsaha(String bidangUsaha) {
        this.bidangUsaha = bidangUsaha;
    }

    @Override
    public String toString() {
        return "Perusahaan{" +
                "namaPerusahaan='" + namaPerusahaan + '\'' +
                ", namaPemimpin='" + namaPemimpin + '\'' +
                ", email='" + email + '\'' +
                ", kontak='" + kontak + '\'' +
                ", bidangUsaha='" + bidangUsaha + '\'' +
                '}';
    }
}