package com.dulich.dulich.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "ChiTietTkb")
@Data
@IdClass(ChiTietTKBKey.class)
public class ChiTietTKB {
    @Id
    @Column(name = "matkb")
    private String maTKB;

    @Id
    @Column(name = "malop")
    private String maLop;

    @Id
    @Column(name = "mamh")
    private String maMH;

    @Id
    @Column(name = "magv")
    private String maGV;

    @Id
    @Column(name = "maphong")
    private int maPhong;

    @Id
    @Column(name = "thu")
    private String thu;

    @Column(name = "tietbatdau")
    private int tietBatDau;

    @Column(name = "sotiet")
    private int soTiet;

    @Column(name = "ngaybatdau")
    private LocalDate ngayBatDau;

    @Column(name = "ngayketthuc")
    private LocalDate ngayKetThuc;
}
