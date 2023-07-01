package com.dulich.dulich.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.dulich.dulich.form.GiangVienFormModel;
import com.dulich.dulich.model.GiangVien;
import com.dulich.dulich.model.TaiKhoan;
import com.dulich.dulich.repository.GiangVienRepository;
import com.dulich.dulich.repository.KhoaRepository;
import com.dulich.dulich.repository.TaiKhoanRepository;

@Controller
public class GiangVienController {
    @Autowired
    GiangVienRepository giangVienRepository;
    @Autowired
    KhoaRepository khoaRepository;
    @Autowired
    TaiKhoanRepository taiKhoanRepository;

    @GetMapping("/giangvien")
    public String giangVien(Model model, @CookieValue("account") String account, @CookieValue("role") String role) {
        model.addAttribute("giangVienFormModel", new GiangVienFormModel());
        model.addAttribute("listGiangVien", giangVienRepository.findAll());
        model.addAttribute("listKhoa", khoaRepository.findAll());
        model.addAttribute("ma", account);
        model.addAttribute("role", role);
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
        TaiKhoan taiKhoan = new TaiKhoan();
        GiangVien giangVien = new GiangVien();

        taiKhoan.setTaiKhoan(inputMaGV);
        taiKhoan.setMatKhau("1408");
        taiKhoan.setChucVu("GV");

        giangVien.setMaGV(inputMaGV);
        giangVien.setTenGV(inputTenGV);
        giangVien.setSdt(inputSDT);
        giangVien.setDiaChi(inputDiaChi);
        giangVien.setEmail(inputEmail);
        giangVien.setMaKhoa(inputMaKhoa);

        taiKhoanRepository.save(taiKhoan);
        giangVienRepository.save(giangVien);
        
        return "redirect:giangvien";
    }

    @PostMapping("/giangvien/update/{id}")
    public String updateGiangVien(@PathVariable(value = "id") String maGV, @ModelAttribute("giangVienFormModel") GiangVienFormModel giangVienFormModel) {
        String inputMaGV = giangVienFormModel.getMaGV();
        String inputTenGV = giangVienFormModel.getTenGV();
        String inputSDT = giangVienFormModel.getSdt();
        String inputDiaChi = giangVienFormModel.getDiaChi();
        String inputEmail = giangVienFormModel.getEmail();
        String inputMaKhoa = giangVienFormModel.getMaKhoa();
        GiangVien giangVien = giangVienRepository.findByMaGV(maGV).get();

        giangVien.setMaGV(inputMaGV);
        giangVien.setTenGV(inputTenGV);
        giangVien.setSdt(inputSDT);
        giangVien.setDiaChi(inputDiaChi);
        giangVien.setEmail(inputEmail);
        giangVien.setMaKhoa(inputMaKhoa);

        giangVienRepository.save(giangVien);

        return "redirect:/giangvien";
    }

    @PostMapping("/giangvien/delete/{id}")
    public String deleteGiangVien(@PathVariable(value = "id") String maGV) {
        GiangVien giangVien = giangVienRepository.findByMaGV(maGV).get();
        giangVienRepository.delete(giangVien);
        return "redirect:/giangvien";
    }
}
