package com.dulich.dulich.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.dulich.dulich.model.ThoiKhoaBieu;
import com.dulich.dulich.repository.ThoiKhoaBieuRepository;

@Controller
public class TKBController {
    @Autowired
    ThoiKhoaBieuRepository tkbRepository;

    @GetMapping("/tkb")
    public String tkb(Model model) {
        model.addAttribute("tkbModel", new ThoiKhoaBieu());
        model.addAttribute("listTKB", tkbRepository.findAll());
        return "tkb";
    }

    @PostMapping("/tkb")
    public String themTKB(@ModelAttribute("tkbModel") ThoiKhoaBieu tkb) {
        tkbRepository.save(tkb);
        return "redirect:tkb";
    }

    @PostMapping("/tkb/update/{id}")
    public String updateTKB(@ModelAttribute("tkbModel") ThoiKhoaBieu tkb, @PathVariable(value = "id") String maTKB) {
        ThoiKhoaBieu newTKB = tkbRepository.findByMaTKB(maTKB).get();
        newTKB.setNienKhoa(tkb.getNienKhoa());
        newTKB.setHocKy(tkb.getHocKy());
        tkbRepository.save(newTKB);
        return "redirect:/tkb";
    }

    @PostMapping("/tkb/delete/{id}")
    public String deleteTKB(@PathVariable(value = "id") String maTKB) {
        ThoiKhoaBieu tkb = tkbRepository.findByMaTKB(maTKB).get();
        tkbRepository.delete(tkb);
        return "redirect:/tkb";
    }
}
