package com.dulich.dulich.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.dulich.dulich.form.LoginFormModel;
import com.dulich.dulich.model.TaiKhoan;
import com.dulich.dulich.repository.TaiKhoanRepository;

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
        response.addCookie(new Cookie("role", role));
        return "redirect:sinhvien";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("role", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:login";
    }
    
}
