package com.dulich.dulich.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dulich.dulich.model.ThoiKhoaBieu;
import java.util.Optional;


@Repository
public interface ThoiKhoaBieuRepository extends JpaRepository<ThoiKhoaBieu, Long> {
    Optional<ThoiKhoaBieu> findByMaTKB(String maTKB);
}
