package com.example.bgm.repository;

import com.example.bgm.entity.Bgm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BgmRepository extends JpaRepository<Bgm, Integer> {}
