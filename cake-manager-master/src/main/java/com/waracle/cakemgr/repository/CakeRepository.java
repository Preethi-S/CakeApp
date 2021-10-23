package com.waracle.cakemgr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.waracle.cakemgr.dao.CakeDao;

@Repository
public interface CakeRepository extends JpaRepository<CakeDao, Integer> {

}
