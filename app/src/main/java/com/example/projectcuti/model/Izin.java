package com.example.projectcuti.model;

public class Izin {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getTgl_mulai() {
        return tgl_mulai;
    }

    public void setTgl_mulai(String tgl_mulai) {
        this.tgl_mulai = tgl_mulai;
    }

    public String getTgl_akhir() {
        return tgl_akhir;
    }

    public void setTgl_akhir(String tgl_akhir) {
        this.tgl_akhir = tgl_akhir;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public Izin(String id, String nama, String keterangan, String status, String jenis, String tgl_mulai, String tgl_akhir, String nik) {
        this.id = id;
        this.nama = nama;
        this.keterangan = keterangan;
        this.status = status;
        this.jenis = jenis;
        this.tgl_mulai = tgl_mulai;
        this.tgl_akhir = tgl_akhir;
        this.nik = nik;
    }

    String id, nama, keterangan, status, jenis, tgl_mulai, tgl_akhir, nik;

}
