package com.dulich.dulich.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.dulich.dulich.model.HocPhan;
import com.dulich.dulich.repository.HocPhanRepository;

@Controller
public class HocPhanController {
    @Autowired
    HocPhanRepository hocPhanRepository;

    @GetMapping("/hocphan")
    public String hocPhan(Model model) {
        model.addAttribute("hocPhanModel", new HocPhan());
        model.addAttribute("listHocPhan", hocPhanRepository.findAll());
        return "hocphan";
    }

    @PostMapping("/hocphan")
    public String themHocPhan(@ModelAttribute("hocPhanModel") HocPhan hocPhan, Model model) {
        String inputMaHP = hocPhan.getMaHP();
        String inputTenHP = hocPhan.getTenHP();
        int inputSoTC = hocPhan.getSoTC();
        int inputSoTCHP = hocPhan.getSoTCHP();
        HocPhan newHocPhan = new HocPhan();

        newHocPhan.setTenHP(inputTenHP);
        newHocPhan.setMaHP(inputMaHP);
        newHocPhan.setSoTC(inputSoTC);
        newHocPhan.setSoTCHP(inputSoTCHP);

        hocPhanRepository.save(newHocPhan);

        return "redirect:hocphan";
    }

    @PostMapping("/hocphan/update/{id}")
    public String updateHocPhan(@PathVariable(value = "id") String maHP, @ModelAttribute("hocPhanModel") HocPhan hocPhan) {
        String inputMaHP = hocPhan.getMaHP();
        String inputTenHP = hocPhan.getTenHP();
        int inputSoTC = hocPhan.getSoTC();
        int inputSoTCHP = hocPhan.getSoTCHP();
        HocPhan newHocPhan = hocPhanRepository.findByMaHP(maHP).get();

        newHocPhan.setMaHP(inputMaHP);
        newHocPhan.setTenHP(inputTenHP);
        newHocPhan.setSoTC(inputSoTC);
        newHocPhan.setSoTCHP(inputSoTCHP);

        hocPhanRepository.save(newHocPhan);

        return "redirect:/hocphan";
    }

    @PostMapping("/hocphan/delete/{id}")
    public String deleteHocPhan(@PathVariable(value = "id") String maHP) {
        HocPhan hocPhan = hocPhanRepository.findByMaHP(maHP).get();
        hocPhanRepository.delete(hocPhan);

        return "redirect:/hocphan";
    }
}
