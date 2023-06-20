package com.dulich.dulich.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dulich.dulich.model.Lop;


@Repository
public interface LopRepository extends JpaRepository<Lop, Long> {
    Optional<Lop> findByMaLop(String maLop);
}
