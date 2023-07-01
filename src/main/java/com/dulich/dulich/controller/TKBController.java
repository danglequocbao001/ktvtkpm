package com.dulich.dulich.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
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
    public String tkb(Model model, @CookieValue("account") String account, @CookieValue("role") String role) {
        model.addAttribute("tkbModel", new ThoiKhoaBieu());
        model.addAttribute("listTKB", tkbRepository.findAll());
        model.addAttribute("ma", account);
        model.addAttribute("role", role);
        return "tkb";
    }

    @PostMapping("/tkb")
    public String themTKB(@ModelAttribute("tkbModel") ThoiKhoaBieu tkb, Model model, @CookieValue("account") String account, @CookieValue("role") String role) {
        try {
            tkbRepository.findByNienKhoaAndHocKy(tkb.getNienKhoa(), tkb.getHocKy()).get();
            model.addAttribute("hasErrors", true);
            model.addAttribute("error", "Học kỳ này của niên khóa đã có thời khóa biểu rồi");
            model.addAttribute("tkbModel", new ThoiKhoaBieu());
            model.addAttribute("listTKB", tkbRepository.findAll());
            model.addAttribute("ma", account);
            model.addAttribute("role", role);
            return "tkb";
        } catch (NoSuchElementException ex) {

        }
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
