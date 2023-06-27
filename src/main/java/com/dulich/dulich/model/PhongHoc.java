package com.dulich.dulich.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "PhongHoc")
@Data
public class PhongHoc {
    @Id
    @Column(name = "maphong")
    private String maPhong;

    @Column(name = "loaiphong")
    private String loaiPhong;

    @Column(name = "trangthai")
    private String trangThai;
}
