package com.dulich.dulich.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.dulich.dulich.model.MonHoc;
import com.dulich.dulich.repository.HocPhanRepository;
import com.dulich.dulich.repository.MonHocRepository;

@Controller
public class MonHocController {
    @Autowired
    HocPhanRepository hocPhanRepository;
    @Autowired
    MonHocRepository monHocRepository;

    @GetMapping("/monhoc")
    public String monHoc(Model model, @CookieValue("account") String account, @CookieValue("role") String role) {
        model.addAttribute("monHocModel", new MonHoc());
        model.addAttribute("listMonHoc", monHocRepository.findAll());
        model.addAttribute("listHocPhan", hocPhanRepository.findAll());
        model.addAttribute("ma", account);
        model.addAttribute("role", role);
        return "monhoc";
    }

    @PostMapping("/monhoc")
    public String themMonHoc(@ModelAttribute("monHocModel") MonHoc monHoc, Model model) {
        String inputMaMH = monHoc.getMaMH();
        String inputTenMH = monHoc.getTenMH();
        int inputHocKy = monHoc.getHocKy();
        String inputNganh = monHoc.getNganh();
        String inputLoai = monHoc.getLoai();
        String inputMaHP = monHoc.getMaHP();
        MonHoc newMonHoc = new MonHoc();

        newMonHoc.setMaMH(inputMaMH);
        newMonHoc.setTenMH(inputTenMH);
        newMonHoc.setHocKy(inputHocKy);
        newMonHoc.setNganh(inputNganh);
        newMonHoc.setLoai(inputLoai);
        newMonHoc.setMaHP(inputMaHP);

        monHocRepository.save(newMonHoc);

        return "redirect:monhoc";
    }

    @PostMapping("/monhoc/update/{id}")
    public String updateMonHoc(@PathVariable(value = "id") String maMH, @ModelAttribute("monHocModel") MonHoc monHoc) {
        String inputMaMH = monHoc.getMaMH();
        String inputTenMH = monHoc.getTenMH();
        int inputHocKy = monHoc.getHocKy();
        String inputNganh = monHoc.getNganh();
        String inputLoai = monHoc.getLoai();
        String inputMaHP = monHoc.getMaHP();
        MonHoc newMonHoc = monHocRepository.findByMaMH(maMH).get();

        newMonHoc.setMaMH(inputMaMH);
        newMonHoc.setTenMH(inputTenMH);
        newMonHoc.setHocKy(inputHocKy);
        newMonHoc.setNganh(inputNganh);
        newMonHoc.setLoai(inputLoai);
        newMonHoc.setMaHP(inputMaHP);

        monHocRepository.save(newMonHoc);

        return "redirect:/monhoc";
    }

    @PostMapping("/monhoc/delete/{id}")
    public String deleteMonHoc(@PathVariable(value = "id") String maMH) {
        MonHoc monHoc = monHocRepository.findByMaMH(maMH).get();
        monHocRepository.delete(monHoc);
        return "redirect:/monhoc";
    }
}
