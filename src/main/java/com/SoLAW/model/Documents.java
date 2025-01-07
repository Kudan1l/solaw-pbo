package com.SoLAW.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;


import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter

public class Documents {
    private String jenisSurat;
    private String permasalahan;
    private String permintaan;
    @Id
    private Long id;

    // Default constructor
    public Documents() {
    }

    // Getter dan setter dengan validasi untuk jenisSurat
    public String getJenisSurat() {
        return jenisSurat;
    }

    public void setJenisSurat(String jenisSurat) {
        if (jenisSurat == null || jenisSurat.trim().isEmpty()) {
            throw new IllegalArgumentException("Jenis surat harus dipilih");
        }
        this.jenisSurat = jenisSurat;
    }

    // Getter dan setter dengan validasi untuk permasalahan
    public String getPermasalahan() {
        return permasalahan;
    }

    public void setPermasalahan(String permasalahan) {
        if (permasalahan == null || permasalahan.trim().isEmpty()) {
            throw new IllegalArgumentException("Permasalahan tidak boleh kosong");
        }
        this.permasalahan = permasalahan;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getPermintaan() {
        return permintaan;
    }

    public void setPermintaan(String permintaan) {
        this.permintaan = permintaan;
    }
}



