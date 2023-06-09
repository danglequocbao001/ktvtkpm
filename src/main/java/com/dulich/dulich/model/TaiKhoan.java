package com.dulich.dulich.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "TaiKhoan")
@Data
public class TaiKhoan {
    @Id
    @Column(name = "taikhoan")
    private String taiKhoan;

    @Column(name = "matkhau")
    private String matKhau;

    @Column(name = "chucvu")
    private String chucVu;
}
