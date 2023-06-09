package com.dulich.dulich.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dulich.dulich.model.GiangVien;

@Repository
public interface GiangVienRepository extends JpaRepository<GiangVien, Long> {
    
}
