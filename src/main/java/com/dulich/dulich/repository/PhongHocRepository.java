package com.dulich.dulich.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dulich.dulich.model.PhongHoc;


@Repository
public interface PhongHocRepository extends JpaRepository<PhongHoc, Long> {
    Optional<PhongHoc> findByMaPhong(int maPhong);
}
