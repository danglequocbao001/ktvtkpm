package com.dulich.dulich.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dulich.dulich.model.Khoa;

@Repository
public interface KhoaRepository extends JpaRepository<Khoa, Long>  {
    
}
