package com.dulich.dulich.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name ="ChiTietCtdt")
@Data
@IdClass(ChiTietCTDTKey.class)
public class ChiTietCTDT {
    @Id
    @Column(name = "mactdt")
    private String maCTDT;

    @Id
    @Column(name = "mahp")
    private String maHP;
}

