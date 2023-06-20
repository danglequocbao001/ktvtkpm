package com.dulich.dulich.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "MonHoc")
@Data
public class MonHoc {
    @Id
    @Column(name = "mamh")
    private String maMH;

    @Column(name = "tenmh")
    private String tenMH;

    @Column(name = "hocky")
    private int hocKy;

    @Column(name = "nganh")
    private String nganh;

    @Column(name = "loai")
    private String loai;

    @Column(name = "mahp")
    private String maHP;
}
