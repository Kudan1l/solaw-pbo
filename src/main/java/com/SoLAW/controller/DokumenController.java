package com.SoLAW.controller;

import com.SoLAW.model.Documents;
import com.SoLAW.repository.DocumentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.swing.text.Document;

@Controller
public class DokumenController {

    @GetMapping("/pembuatanDokum")
    public String pembuatanDokumen() {
        return "Dokumen/pembuatanDokum";
    }

    // Additional mappings for each document type
    @GetMapping("/kontrak")
    public String showKontrak() {
        return "Dokumen/kontrak";
    }

    @GetMapping("/surat-kuasa")
    public String suratKuasa() {
        return "Dokumen/suratKuasa";
    }

    @GetMapping("/gugatan")
    public String gugatan() {
        return "Dokumen/gugatan";
    }

    @GetMapping("/notaris")
    public String notaris() {
        return "Dokumen/notaris";
    }

    @GetMapping("/pengadilan")
    public String pengadilan() {
        return "Dokumen/pengadilan";
    }

    @GetMapping("/perjanjian")
    public String perjanjian() {
        return "Dokumen/perjanjian";
    }

    @GetMapping("/buatSurat")
    public String showForm(Model model) {
        model.addAttribute("document", new Documents());
        return "Dokumen/buatSurat"; // Nama template HTML
    }




    @Autowired
    private DocumentsRepository documentsRepository;

    // Mengirim data form saat di-submit
    @PostMapping("/store")
    public String submitForm(Documents document, Model model) {
        try {
            // Pemeriksaan manual (validasi di controller)
            if (document.getJenisSurat() == null || document.getJenisSurat().trim().isEmpty()) {
                model.addAttribute("errorJenisSurat", "Jenis surat harus dipilih");
                return "Dokumen/buatSurat";  // Mengembalikan ke form jika ada kesalahan
            }

            if (document.getPermasalahan() == null || document.getPermasalahan().trim().isEmpty()) {
                model.addAttribute("errorPermasalahan", "Permasalahan tidak boleh kosong");
                return "Dokumen/buatSurat";  // Mengembalikan ke form jika ada kesalahan
            }

            // Jika tidak ada kesalahan, simpan dokumen ke database
            documentsRepository.save(document);  // Menyimpan data ke database

            // Menambahkan pesan sukses setelah penyimpanan berhasil
            model.addAttribute("message", "Dokumen berhasil dibuat");
            return "Dokumen/pembuatanDokum";  // Mengarahkan ke halaman sukses

        } catch (Exception e) {
            // Jika terjadi kesalahan, tambahkan pesan error ke model
            model.addAttribute("error", "Terjadi kesalahan saat menyimpan dokumen.");
            return "Dokumen/buatSurat";  // Mengarahkan kembali ke halaman form
        }
    }
}
