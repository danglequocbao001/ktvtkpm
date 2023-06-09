package com.dulich.dulich.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "SinhVien")
@Data
public class SinhVien {
    @Id
    @Column(name = "masv")
    private String maSV;

    @Column(name = "tensv")
    private String tenSV;

    @Column(name = "ngaysinh")
    private LocalDate ngaySinh;

    @Column(name = "sdt")
    private String sdt;

    @Column(name = "diachi")
    private String diaChi;

    @Column(name = "email")
    private String email;

    @Column(name = "malop")
    private String maLop;
}
