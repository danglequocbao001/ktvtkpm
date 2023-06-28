package com.dulich.dulich.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.dulich.dulich.form.LoginFormModel;
import com.dulich.dulich.form.matKhauFormModel;
import com.dulich.dulich.model.TaiKhoan;
import com.dulich.dulich.repository.TaiKhoanRepository;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {
    @Autowired
    TaiKhoanRepository taiKhoanRepository;

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginModel", new LoginFormModel());
        return "dangnhap";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginModel") LoginFormModel loginModel, Model model, HttpServletResponse response) {
        String inputTaiKhoan = loginModel.getTaiKhoan();
        String inputMatKhau = loginModel.getMatKhau();

        if (!taiKhoanRepository.findByTaiKhoan(inputTaiKhoan).isPresent()) {
            model.addAttribute("errorTaiKhoan", "Tài khoản không tồn tại");
            return "dangnhap";
        }

        TaiKhoan taiKhoan = taiKhoanRepository.findByTaiKhoan(inputTaiKhoan).orElse(null);
        
        if (!taiKhoan.getMatKhau().trim().equals(inputMatKhau)) {
            model.addAttribute("errorMatKhau", "Mật khẩu không chính xác");
            return "dangnhap";
        }

        String role = taiKhoan.getChucVu().trim();
        String account = taiKhoan.getTaiKhoan().trim();
        response.addCookie(new Cookie("role", role));
        response.addCookie(new Cookie("account", account));
        return "redirect:/";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("role", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        cookie = new Cookie("account", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:login";
    }

    @RequestMapping("/matkhau")
    public String matKhau(Model model) {
        model.addAttribute("matKhauModel", new matKhauFormModel());
        return "matkhau";
    }

    @PostMapping("/matkhau")
    public String doiMatKhau(@ModelAttribute("matKhauModel") matKhauFormModel matKhauModel, @CookieValue("account") String account, Model model) {
        TaiKhoan taiKhoan = taiKhoanRepository.findByTaiKhoan(account).get();
        if (!taiKhoan.getMatKhau().trim().equals(matKhauModel.getMatKhauCu())) {
            model.addAttribute("errorMatKhauCu", "Mật khẩu cũ không chính xác!");
            return "matkhau";
        }
        if (!matKhauModel.getMatKhauMoi().equals(matKhauModel.getMatKhauNhapLai())) {
            model.addAttribute("errorMatKhauMoi", "Mật khẩu không trùng khớp!");
            return "matkhau";
        }
        taiKhoan.setMatKhau(matKhauModel.getMatKhauMoi());
        taiKhoanRepository.save(taiKhoan);
        return "redirect:/";
    }
    
}
