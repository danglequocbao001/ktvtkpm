package com.dulich.dulich.model;

import java.io.Serializable;
import java.time.LocalDate;

public class ChiTietTKBKey implements Serializable {
    private String maTKB;
    private String maLop;
    private String maMH;
    private String maGV;
    private int maPhong;
    private String thu;
    private int tietBatDau;
    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;
}
