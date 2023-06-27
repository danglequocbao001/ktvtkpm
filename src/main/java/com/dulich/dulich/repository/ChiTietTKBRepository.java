package com.dulich.dulich.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dulich.dulich.model.ChiTietTKB;

@Repository
public interface ChiTietTKBRepository extends JpaRepository<ChiTietTKB, Long> {
    
}
