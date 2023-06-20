package com.dulich.dulich.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dulich.dulich.model.MonHoc;

@Repository
public interface MonHocRepository extends JpaRepository<MonHoc, Long> {
    Optional<MonHoc> findByMaMH(String maMH);
}
