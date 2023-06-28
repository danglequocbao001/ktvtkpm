package com.dulich.dulich.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.dulich.dulich.form.SinhVienFormModel;
import com.dulich.dulich.model.SinhVien;
import com.dulich.dulich.model.TaiKhoan;
import com.dulich.dulich.repository.LopRepository;
import com.dulich.dulich.repository.SinhVienRepository;
import com.dulich.dulich.repository.TaiKhoanRepository;

@Controller
public class SinhVienController {
    @Autowired
    SinhVienRepository sinhVienRepository;
    @Autowired
    LopRepository lopRepository;
    @Autowired
    TaiKhoanRepository taiKhoanRepository;

    @GetMapping("/sinhvien")
    public String sinhVien(Model model, @CookieValue("account") String account, @CookieValue("role") String role) {
        model.addAttribute("sinhVienFormModel", new SinhVienFormModel());
        model.addAttribute("listSinhVien", sinhVienRepository.findAll());
        model.addAttribute("listLop", lopRepository.findAll());
        model.addAttribute("ma", account);
        model.addAttribute("role", role);
        //System.out.println(account);
        return "sinhvien";
    }

    @PostMapping("/sinhvien")
    public String themSinhVien(@ModelAttribute("sinhVienFormModel") SinhVienFormModel sinhVienFormModel, Model model) {
        String inputMaSV = sinhVienFormModel.getMaSV();
        String inputTenSV = sinhVienFormModel.getTenSV();
        String inputNgaySinh = sinhVienFormModel.getNgaySinh();
        String inputSDT = sinhVienFormModel.getSdt();
        String inputDiaChi = sinhVienFormModel.getDiaChi();
        String inputEmail = sinhVienFormModel.getEmail();
        String inputMaLop = sinhVienFormModel.getMaLop();
        TaiKhoan taiKhoan = new TaiKhoan();
        SinhVien sinhVien = new SinhVien();

        taiKhoan.setTaiKhoan(inputMaSV);
        taiKhoan.setMatKhau("1408");
        taiKhoan.setChucVu("SV");

        sinhVien.setMaSV(inputMaSV);
        sinhVien.setTenSV(inputTenSV);
        sinhVien.setNgaySinh(LocalDate.parse(inputNgaySinh, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        sinhVien.setSdt(inputSDT);
        sinhVien.setDiaChi(inputDiaChi);
        sinhVien.setEmail(inputEmail);
        sinhVien.setMaLop(inputMaLop);

        taiKhoanRepository.save(taiKhoan);
        sinhVienRepository.save(sinhVien);

        return "redirect:sinhvien";
    }

    @PostMapping("/sinhvien/update/{id}")
    public String updateSinhVien(@PathVariable(value = "id") String maSV, @ModelAttribute("sinhVienFormModel") SinhVienFormModel sinhVienFormModel, Model model) {
        String inputMaSV = sinhVienFormModel.getMaSV();
        String inputTenSV = sinhVienFormModel.getTenSV();
        String inputNgaySinh = sinhVienFormModel.getNgaySinh();
        String inputSDT = sinhVienFormModel.getSdt();
        String inputDiaChi = sinhVienFormModel.getDiaChi();
        String inputEmail = sinhVienFormModel.getEmail();
        String inputMaLop = sinhVienFormModel.getMaLop();
        SinhVien sinhVien = sinhVienRepository.findByMaSV(maSV).get();

        sinhVien.setMaSV(inputMaSV);
        sinhVien.setTenSV(inputTenSV);
        sinhVien.setNgaySinh(LocalDate.parse(inputNgaySinh, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        sinhVien.setSdt(inputSDT);
        sinhVien.setDiaChi(inputDiaChi);
        sinhVien.setEmail(inputEmail);
        sinhVien.setMaLop(inputMaLop);

        sinhVienRepository.save(sinhVien);

        return "redirect:/sinhvien";
    }

    @PostMapping("/sinhvien/delete/{id}")
    public String xoaSinhVien(@PathVariable(value = "id") String maSV) {
        SinhVien sinhVien = sinhVienRepository.findByMaSV(maSV).get();
        sinhVienRepository.delete(sinhVien);
        return "redirect:/sinhvien";
    }
}
