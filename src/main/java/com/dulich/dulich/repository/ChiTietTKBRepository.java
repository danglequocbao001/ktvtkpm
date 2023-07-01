package com.dulich.dulich.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dulich.dulich.model.ChiTietTKB;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface ChiTietTKBRepository extends JpaRepository<ChiTietTKB, Long> {
    Optional<ChiTietTKB> findByMaTKBAndMaLopAndMaMHAndMaGVAndMaPhongAndThuAndTietBatDauAndNgayBatDauAndNgayKetThuc(String maTKB, String maLop, String maMH, String maGV, int maPhong, String thu, int tietBatDau, LocalDate ngayBatDau, LocalDate ngayKetThuc);
    List<ChiTietTKB> findByMaTKBAndMaLop(String maTKB, String maLop);
    List<ChiTietTKB> findByMaTKBAndMaGV(String maTKB, String maGV);
    List<ChiTietTKB> findByMaTKBAndMaLopAndThuAndTietBatDau(String maTKB, String maLop, String thu, int tietBatDau);
    List<ChiTietTKB> findByMaTKBAndMaGVAndThuAndTietBatDau(String maTKB, String maGV, String thu, int tietBatDau);
}
