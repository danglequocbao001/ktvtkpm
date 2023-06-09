package com.dulich.dulich.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TrangChuController {
    @GetMapping("/")
    public String index(@CookieValue(name = "role", required = false) String role) {
        try {
            if (role.equals("GV") || role.equals("SV")) return "redirect:/xemtkb";
            if (role.equals("NVDT")) return "redirect:/lop";
            if (role.equals("NVGV")) return "redirect:/tkb";
        } catch (NullPointerException ex) {
            return "redirect:/login";
        }
        return "redirect:/login";
    }
}
