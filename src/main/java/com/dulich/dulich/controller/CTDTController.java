package com.dulich.dulich.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.dulich.dulich.model.CTDT;
import com.dulich.dulich.model.ChiTietCTDT;
import com.dulich.dulich.model.HocPhan;
import com.dulich.dulich.repository.CTDTRepository;
import com.dulich.dulich.repository.ChiTietCTDTRepository;
import com.dulich.dulich.repository.HocPhanRepository;
import com.dulich.dulich.repository.MonHocRepository;

@Controller
public class CTDTController {
    @Autowired
    CTDTRepository ctdtRepository;

    @Autowired
    ChiTietCTDTRepository chiTietCTDTRepository;

    @Autowired
    HocPhanRepository hocPhanRepository;

    @Autowired
    MonHocRepository monHocRepository;

    @GetMapping("/ctdt")
    public String ctdt(Model model, @CookieValue("account") String account) {
        model.addAttribute("ctdtModel", new CTDT());
        //model.addAttribute("chiTietCTDTModel", new ChiTietCTDT());
        model.addAttribute("listCTDT", ctdtRepository.findAll());
        //model.addAttribute("listChiTietCTDT", chiTietCTDTRepository.findAll());
        model.addAttribute("ma", account);
        return "CTDT";
    }

    @PostMapping("/ctdt")
    public String themCTDT(@ModelAttribute("ctdtModel") CTDT ctdt) {
        ctdt.setSoMH(0);
        ctdtRepository.save(ctdt);
        return "redirect:ctdt";
    }

    @PostMapping("/ctdt/update/{id}")
    public String updateCTDT(@ModelAttribute("ctdtModel") CTDT ctdt, @PathVariable(value = "id") String maCTDT) {
        CTDT newCTDT = ctdtRepository.findByMaCTDT(maCTDT).get();
        newCTDT.setSoMH(ctdt.getSoMH());
        newCTDT.setTenCTDT(ctdt.getTenCTDT());
        ctdtRepository.save(newCTDT);
        return "redirect:/ctdt";
    }

    @GetMapping("/ctdt/detail/{id}")
    public String ChiTietCTDT(@PathVariable(value = "id") String maCTDT, Model model) {
        model.addAttribute("mactdt", maCTDT);
        model.addAttribute("chiTietCTDTModel", new ChiTietCTDT());
        model.addAttribute("listHocPhan", hocPhanRepository.findAll());
        model.addAttribute("listChiTietCTDT", chiTietCTDTRepository.findByMaCTDT(maCTDT));
        return "chitietCTDT";
    }

    @PostMapping("/ctdt/detail/{id}")
    public String themChiTietCTDT(@PathVariable(value = "id") String maCTDT, @ModelAttribute("chiTietCTDTModel") ChiTietCTDT chiTietCTDT) {
        CTDT ctdt = ctdtRepository.findByMaCTDT(maCTDT).get();
        //chiTietCTDT.setMaCTDT(maCTDT);
        //HocPhan hocPhan = hocPhanRepository.findByMaHP(chiTietCTDT.getMaHP()).get();
        ctdt.setSoMH(ctdt.getSoMH() +  monHocRepository.countByMaHP(chiTietCTDT.getMaHP()).intValue());
        ctdtRepository.save(ctdt);
        chiTietCTDTRepository.save(chiTietCTDT);
        return "redirect:" + maCTDT;
    }

    @PostMapping("/ctdt/detail/{mactdt}/{mahp}")
    public String deleteChiTietCTDT(@PathVariable Map<String,String> varsMap) {
        ChiTietCTDT chiTietCTDT = chiTietCTDTRepository.findByMaCTDTAndMaHP(varsMap.get("mactdt"), varsMap.get("mahp")).get();
        chiTietCTDTRepository.delete(chiTietCTDT);
        CTDT ctdt = ctdtRepository.findByMaCTDT(varsMap.get("mactdt")).get();
        ctdt.setSoMH(ctdt.getSoMH() -  monHocRepository.countByMaHP(chiTietCTDT.getMaHP()).intValue());
        ctdtRepository.save(ctdt);
        return "redirect:/ctdt/detail/" + varsMap.get("mactdt");
    }

    @PostMapping("/ctdt/delete/{id}")
    public String deleteCTDT(@PathVariable(value = "id") String maCTDT) {
        CTDT ctdt = ctdtRepository.findByMaCTDT(maCTDT).get();
        ctdtRepository.delete(ctdt);
        return "redirect:/ctdt";
    }
}
