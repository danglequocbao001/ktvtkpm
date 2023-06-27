package com.dulich.dulich.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "ctdt")
@Data
public class CTDT {
    @Id
    @Column(name = "mactdt")
    private String maCTDT;

    @Column(name = "tenctdt")
    private String tenCTDT;

    @Column(name = "somh")
    private int soMH;
}
