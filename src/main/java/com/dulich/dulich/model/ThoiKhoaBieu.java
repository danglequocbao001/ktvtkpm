package com.dulich.dulich.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "ThoiKhoaBieu")
@Data
public class ThoiKhoaBieu {
    @Id
    @Column(name = "matkb")
    private String maTKB;

    @Column(name = "nienkhoa")
    private String nienKhoa;

    @Column(name = "hocky")
    private int hocKy;
}
