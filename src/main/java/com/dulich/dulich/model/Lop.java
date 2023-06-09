package com.dulich.dulich.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "lop")
@Data
public class Lop {
    @Id
    @Column(name = "malop")
    private String maLop;

    @Column(name = "tenlop")
    private String tenLop;

    @Column(name = "makhoa")
    private String maKhoa;
}
