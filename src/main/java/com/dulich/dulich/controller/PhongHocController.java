package com.dulich.dulich.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.dulich.dulich.model.PhongHoc;
import com.dulich.dulich.repository.PhongHocRepository;

@Controller
public class PhongHocController {
    @Autowired
    PhongHocRepository phongHocRepository;

    @GetMapping("/phonghoc")
    public String phongHoc(Model model) {
        List<String> listLoaiPhong = new ArrayList<>();
        listLoaiPhong.add("LT");
        listLoaiPhong.add("TH");
        List<String> listTrangThai = new ArrayList<>();
        listTrangThai.add("Bình thường");
        listTrangThai.add("Đang tu sửa");
        model.addAttribute("phongHocModel", new PhongHoc());
        model.addAttribute("listPhongHoc", phongHocRepository.findAll());
        model.addAttribute("listLoaiPhong", listLoaiPhong);
        model.addAttribute("listTrangThai", listTrangThai);
        return "phonghoc";
    }

    @PostMapping("/phonghoc")
    public String themPhongHoc(@ModelAttribute("phongHocModel") PhongHoc phongHoc) {
        phongHocRepository.save(phongHoc);
        return "redirect:phonghoc";
    }

    @PostMapping("/phonghoc/update/{id}") 
    public String updatePhongHoc(@PathVariable(value = "id") int maPH, @ModelAttribute("phongHocModel") PhongHoc phongHoc) {
        PhongHoc newPhongHoc = phongHocRepository.findByMaPhong(maPH).get();
        newPhongHoc.setLoaiPhong(phongHoc.getLoaiPhong());
        newPhongHoc.setTrangThai(phongHoc.getTrangThai());
        phongHocRepository.save(newPhongHoc);
        return "redirect:/phonghoc";
    }

    @PostMapping("/phonghoc/delete/{id}")
    public String deletePhongHoc(@PathVariable(value = "id") int maPH) {
        PhongHoc phongHoc = phongHocRepository.findByMaPhong(maPH).get();
        phongHocRepository.delete(phongHoc);
        return "redirect:/phonghoc";
    }
}
