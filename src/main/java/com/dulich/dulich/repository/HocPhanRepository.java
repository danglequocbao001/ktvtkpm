package com.dulich.dulich.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dulich.dulich.model.HocPhan;

@Repository
public interface HocPhanRepository extends JpaRepository<HocPhan, Long> {
    Optional<HocPhan> findByMaHP(String maHP);
}
