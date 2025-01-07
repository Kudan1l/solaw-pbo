package com.SoLAW.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.cglib.core.Local;


import java.sql.Date;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp  // Menambahkan timestamp otomatis pada saat entitas pertama kali disimpan
    @Column(nullable = false, updatable = false)  // Pastikan kolom ini tidak dapat diubah setelah diset
    private LocalDateTime createdAt;

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}



