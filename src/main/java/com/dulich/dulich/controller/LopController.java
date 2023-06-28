package com.dulich.dulich.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.dulich.dulich.model.Lop;
import com.dulich.dulich.repository.KhoaRepository;
import com.dulich.dulich.repository.LopRepository;

@Controller
public class LopController {
    @Autowired
    KhoaRepository khoaRepository;
    @Autowired
    LopRepository lopRepository;

    @GetMapping("/lop")
    public String lop(Model model, @CookieValue("account") String account) {
        model.addAttribute("lopModel", new Lop());
        model.addAttribute("listLop", lopRepository.findAll());
        model.addAttribute("listKhoa", khoaRepository.findAll());
        model.addAttribute("ma", account);
        return "lop";
    }

    @PostMapping("/lop")
    public String themLop(@ModelAttribute("lopModel") Lop lop, Model model) {
        String inputMaLop = lop.getMaLop();
        String inputTenLop = lop.getTenLop();
        String inputMaKhoa = lop.getMaKhoa();
        Lop newLop = new Lop();

        newLop.setMaLop(inputMaLop);
        newLop.setTenLop(inputTenLop);
        newLop.setMaKhoa(inputMaKhoa);

        lopRepository.save(newLop);

        return "redirect:lop";
    }

    @PostMapping("/lop/update/{id}")
    public String updateLop(@PathVariable(value = "id") String maLop, @ModelAttribute("lopModel") Lop lop) {
        String inputMaLop = lop.getMaLop();
        String inputTenLop = lop.getTenLop();
        String inputMaKhoa = lop.getMaKhoa();
        Lop newLop = lopRepository.findByMaLop(maLop).get();

        newLop.setMaLop(inputMaLop);
        newLop.setTenLop(inputTenLop);
        newLop.setMaKhoa(inputMaKhoa);

        lopRepository.save(newLop);

        return "redirect:/lop";
    }

    @PostMapping("/lop/delete/{id}")
    public String deleteLop(@PathVariable(value = "id") String maLop) {
        Lop lop = lopRepository.findByMaLop(maLop).get();
        lopRepository.delete(lop);
        return "redirect:/lop";
    }
}
