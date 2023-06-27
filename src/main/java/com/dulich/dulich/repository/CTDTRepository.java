package com.dulich.dulich.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dulich.dulich.model.CTDT;

@Repository
public interface CTDTRepository extends JpaRepository<CTDT, Long> {
    Optional<CTDT> findByMaCTDT(String maCTDT);
}
