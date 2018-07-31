package com.springboot.feladat.springbootfeladat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RightsRepository extends JpaRepository<Rights, Integer>{

}
