package com.dulich.dulich.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dulich.dulich.model.ChiTietCTDT;
import java.util.List;
import java.util.Optional;


@Repository
public interface ChiTietCTDTRepository extends JpaRepository<ChiTietCTDT, Long> {
    List<ChiTietCTDT> findByMaCTDT(String maCTDT);
    Optional<ChiTietCTDT> findByMaCTDTAndMaHP(String maCTDT, String maHP);
}
