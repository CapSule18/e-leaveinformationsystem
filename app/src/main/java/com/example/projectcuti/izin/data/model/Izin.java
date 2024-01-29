package com.example.projectcuti.izin.data.model;

public class Izin {
    private String nip;
    private String since;
    private String until;
    private String type;
    private String keterangan;
    private String id;

    private String status;

    public Izin(String nip, String since, String until, String type, String keterangan) {
        this.nip = nip;
        this.since = since;
        this.until = until;
        this.type = type;
        this.keterangan = keterangan;
    }

    public Izin(String nip, String since, String until, String type, String keterangan, String id, String status) {
        this.nip = nip;
        this.since = since;
        this.until = until;
        this.type = type;
        this.keterangan = keterangan;
        this.id = id;
        this.status = status;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getSince() {
        return since;
    }

    public void setSince(String since) {
        this.since = since;
    }

    public String getUntil() {
        return until;
    }

    public void setUntil(String until) {
        this.until = until;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
