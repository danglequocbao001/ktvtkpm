package com.dulich.dulich.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dulich.dulich.model.ChiTietTKB;
import java.util.Optional;


@Repository
public interface ChiTietTKBRepository extends JpaRepository<ChiTietTKB, Long> {
    Optional<ChiTietTKB> findByMaTKBAndMaLopAndMaMHAndMaGVAndMaPhongAndThu(String maTKB, String maLop, String maMH, String maGV, String maPhong, String thu);
}
