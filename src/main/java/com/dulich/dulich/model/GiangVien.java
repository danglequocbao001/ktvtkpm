package com.dulich.dulich.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

import lombok.Data;

@Entity
@Table(name = "GiangVien")
@Data
public class GiangVien {
    @Id
    @Column(name = "magv")
    private String maGV;

    @Column(name = "tengv")
    private String tenGV;

    @Column(name = "sdt")
    private String sdt;

    @Column(name = "diachi")
    private String diaChi;

    @Column(name = "email")
    private String email;

    @Column(name = "makhoa")
    private String maKhoa;
}
