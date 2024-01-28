package com.example.projectcuti.login.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String nip;
    private String displayName;
    private String cabang;
    private String jabatan;

    public LoggedInUser(
            String nip,
            String displayName,
            String cabang,
            String jabatan
    ) {
        this.nip = nip;
        this.displayName = displayName;
        this.cabang = cabang;
        this.jabatan = jabatan;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getCabang() {
        return cabang;
    }

    public void setCabang(String cabang) {
        this.cabang = cabang;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }
}