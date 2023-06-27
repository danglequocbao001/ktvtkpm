package com.dulich.dulich.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.dulich.dulich.form.ChiTietTKBFormModel;
import com.dulich.dulich.model.ChiTietTKB;
import com.dulich.dulich.repository.ChiTietTKBRepository;
import com.dulich.dulich.repository.GiangVienRepository;
import com.dulich.dulich.repository.LopRepository;
import com.dulich.dulich.repository.MonHocRepository;
import com.dulich.dulich.repository.PhongHocRepository;
import com.dulich.dulich.repository.ThoiKhoaBieuRepository;

@Controller
public class ChiTietTKBController {
    @Autowired
    ThoiKhoaBieuRepository tkbRepository;
    @Autowired
    LopRepository lopRepository;
    @Autowired
    MonHocRepository monHocRepository;
    @Autowired
    GiangVienRepository giangVienRepository;
    @Autowired
    PhongHocRepository phongHocRepository;
    @Autowired
    ChiTietTKBRepository chiTietTKBRepository;

    @GetMapping("/chitiettkb")
    public String chiTietTKB(Model model) {
        List<String> listThu = new ArrayList<>();
        listThu.add("2");
        listThu.add("3");
        listThu.add("4");
        listThu.add("5");
        listThu.add("6");
        listThu.add("7");
        listThu.add("CN");
        model.addAttribute("chiTietTKBModel", new ChiTietTKBFormModel());
        model.addAttribute("listTKB", tkbRepository.findAll());
        model.addAttribute("listLop", lopRepository.findAll());
        model.addAttribute("listMonHoc", monHocRepository.findAll());
        model.addAttribute("listGiangVien", giangVienRepository.findAll());
        model.addAttribute("listPhongHoc", phongHocRepository.findAll());
        model.addAttribute("listThu", listThu);
        model.addAttribute("listChiTiet", chiTietTKBRepository.findAll());
        return "chitiettkb";
    }

    @PostMapping("/chitiettkb")
    public String themChiTietTKB(@ModelAttribute("chiTietTKBModel") ChiTietTKBFormModel chiTietTKBFormModel) {
        ChiTietTKB chiTietTKB = new ChiTietTKB();
        chiTietTKB.setMaTKB(chiTietTKBFormModel.getMaTKB());
        chiTietTKB.setMaLop(chiTietTKBFormModel.getMaLop());
        chiTietTKB.setMaMH(chiTietTKBFormModel.getMaMH());
        chiTietTKB.setMaGV(chiTietTKBFormModel.getMaGV());
        chiTietTKB.setMaPhong(chiTietTKBFormModel.getMaPhong());
        chiTietTKB.setThu(chiTietTKBFormModel.getThu());
        chiTietTKB.setTietBatDau(chiTietTKBFormModel.getTietBatDau());
        chiTietTKB.setSoTiet(chiTietTKBFormModel.getSoTiet());
        chiTietTKB.setNgayBatDau(LocalDate.parse(chiTietTKBFormModel.getNgayBatDau(),  DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        chiTietTKB.setNgayKetThuc(LocalDate.parse(chiTietTKBFormModel.getNgayKetThuc(),  DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        chiTietTKBRepository.save(chiTietTKB);
        return "redirect:chitiettkb";
    }

    @PostMapping("/chitiettkb/update/{matkb}/{malop}/{mamh}/{magv}/{maphong}/{thu}")
    public String updateChiTietTKB(@PathVariable Map<String, String> varsMap, @ModelAttribute("chiTietTKBModel") ChiTietTKBFormModel chiTietTKBFormModel) {
        ChiTietTKB chiTietTKB = chiTietTKBRepository.findByMaTKBAndMaLopAndMaMHAndMaGVAndMaPhongAndThu(
            varsMap.get("matkb"), varsMap.get("malop"), varsMap.get("mamh"), 
            varsMap.get("magv"), varsMap.get("maphong"), varsMap.get("thu")).get();
        chiTietTKB.setTietBatDau(chiTietTKBFormModel.getTietBatDau());
        chiTietTKB.setSoTiet(chiTietTKBFormModel.getSoTiet());
        chiTietTKB.setNgayBatDau(LocalDate.parse(chiTietTKBFormModel.getNgayBatDau(),  DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        chiTietTKB.setNgayKetThuc(LocalDate.parse(chiTietTKBFormModel.getNgayKetThuc(),  DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        chiTietTKBRepository.save(chiTietTKB);
        return "redirect:/chitiettkb";
    }

    @PostMapping("/chitiettkb/delete/{matkb}/{malop}/{mamh}/{magv}/{maphong}/{thu}")
    public String deleteChiTietTKB(@PathVariable Map<String, String> varsMap) {
        ChiTietTKB chiTietTKB = chiTietTKBRepository.findByMaTKBAndMaLopAndMaMHAndMaGVAndMaPhongAndThu(
            varsMap.get("matkb"), varsMap.get("malop"), varsMap.get("mamh"), 
            varsMap.get("magv"), varsMap.get("maphong"), varsMap.get("thu")).get();
        chiTietTKBRepository.delete(chiTietTKB);
        return "redirect:/chitiettkb";
    }
}
