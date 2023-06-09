package com.dulich.dulich.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "khoa")
@Data
public class Khoa {
    @Id
    @Column(name = "makhoa")
    private String maKhoa;

    @Column(name = "tenkhoa")
    private String tenKhoa;
}
