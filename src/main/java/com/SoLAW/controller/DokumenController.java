package com.SoLAW.controller;

import com.SoLAW.model.Documents;
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

    @PostMapping("/store")
    public String storeSurat(@ModelAttribute Documents documents, Model model) {
        // Proses data yang dikirimkan (misalnya, simpan ke database)
        // Di sini hanya untuk menunjukkan bagaimana data diterima dan diproses
        System.out.println("Jenis Surat: " + documents.getJenisSurat());
        System.out.println("Permasalahan: " + documents.getPermasalahan());
        System.out.println("Permintaan: " + documents.getPermintaan());

        // Setelah data diproses, tambahkan pesan sukses ke model
        model.addAttribute("message", "Surat berhasil dibuat!");

        // Redirect atau tampilkan halaman sukses setelah penyimpanan
        return "redirect:/suratBerhasil"; // Halaman success.html setelah form berhasil disubmit
    }
}
