package com.oods.thelast.dependencies.enums;

import java.util.HashMap;
import java.util.Map;

public enum BidangJasa {
    CHOOSE("Bidang Jasa"),
    PIL1("Jasa Pelaksana Konstruksi Pemasangan Pendingin Udara (Air Conditioner)"),
    PIL2("Jasa Pelaksana Konstruksi Pemasangan Pipa air (Plumbing) Dalam Bangunan dan Salurannya"),
    PIL3("Jasa Pelaksana Konstruksi Pemasangan Pipa Gas Dalam Bangunan"),
    PIL4("Jasa Pelaksana Konstruksi Insulasi Dalam Bangunan"),
    PIL5("Jasa Pelaksana Konstruksi Pemasangan Lift dan Tangga Berjalan"),
    PIL6("Jasa Pelaksana Konstruksi Pertambangan dan Manufaktur"),
    PIL7("Jasa Pelaksana Konstruksi Instalasi Thermal, Bertekanan, Minyak dan Gas, Geothermal (Pekerjaan Rekayasa)"),
    PIL8("Jasa Pelaksana Konstruksi Instalasi Alat Angkut dan Alat Angkat"),
    PIL9("Jasa Pelaksana Konstruksi Instalasi Perpipaan, Gas, Energi (Pekerjaan Rekayasa)"),
    PIL10("Jasa Pelaksana Konstruksi Instalasi Pembangkit Tenaga Listrik Semua Daya"),
    PIL11("Jasa Pelaksana Konstruksi Instalasi Jaringan Transmisi Tenaga Listrik Tegangan Tinggi/Ekstra Tegangan Tinggi"),;

    private String opsi;
    private static final Map<String, BidangJasa> map = new HashMap<>();
    static {
        for (BidangJasa en : values()) {
            map.put(en.toString(), en);
        }
    }

    public static BidangJasa valueFor(String name) {
        return map.get(name);
    }

    BidangJasa(String opsi) {
        this.opsi = opsi;
    }
        
    @Override
    public String toString() {
        return opsi;
    }
}