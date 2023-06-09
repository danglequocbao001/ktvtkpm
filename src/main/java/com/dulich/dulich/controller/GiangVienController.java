package com.dulich.dulich.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dulich.dulich.form.GiangVienFormModel;
import com.dulich.dulich.model.GiangVien;
import com.dulich.dulich.model.TaiKhoan;
import com.dulich.dulich.repository.GiangVienRepository;
import com.dulich.dulich.repository.KhoaRepository;

@Controller
public class GiangVienController {
    @Autowired
    GiangVienRepository giangVienRepository;
    @Autowired
    KhoaRepository khoaRepository;

    @GetMapping("/giangvien")
    public String giangVien(Model model) {
        model.addAttribute("giangVienFormModel", new GiangVienFormModel());
        model.addAttribute("listGiangVien", giangVienRepository.findAll());
        model.addAttribute("listKhoa", khoaRepository.findAll());
        return "giangvien";
    }

    @PostMapping("/giangvien")
    public String themGiangVien(@ModelAttribute("giangVienFormModel") GiangVienFormModel giangVienFormModel, Model model) {
        String inputMaGV = giangVienFormModel.getMaGV();
        String inputTenGV = giangVienFormModel.getTenGV();
        String inputSDT = giangVienFormModel.getSdt();
        String inputDiaChi = giangVienFormModel.getDiaChi();
        String inputEmail = giangVienFormModel.getEmail();
        String inputMaKhoa = giangVienFormModel.getMaKhoa();
        String inputMatKhau = giangVienFormModel.getMatKhau();
        TaiKhoan taiKhoan = new TaiKhoan();
        GiangVien giangVien = new GiangVien();

        taiKhoan.setTaiKhoan(inputMaGV);
        taiKhoan.setMatKhau(inputMatKhau);
        taiKhoan.setChucVu("GV");

        giangVien.setMaGV(inputMaGV);
        giangVien.setTenGV(inputTenGV);
        giangVien.setSdt(inputSDT);
        giangVien.setDiaChi(inputDiaChi);
        giangVien.setEmail(inputEmail);
        giangVien.setMaKhoa(inputMaKhoa);
        
        return "giangvien";
    }
}
