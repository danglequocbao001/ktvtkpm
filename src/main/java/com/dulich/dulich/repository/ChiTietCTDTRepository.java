package com.dulich.dulich.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dulich.dulich.model.ChiTietCTDT;

@Repository
public interface ChiTietCTDTRepository extends JpaRepository<ChiTietCTDT, Long> {
    
}
