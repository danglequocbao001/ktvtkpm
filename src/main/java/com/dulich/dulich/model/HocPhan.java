package com.dulich.dulich.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "HocPhan")
@Data
public class HocPhan {
    @Id
    @Column(name = "mahp")
    private String maHP;
    
    @Column(name = "sotc")
    private int soTC;

    @Column(name = "sotchp")
    private int soTCHP;
}
