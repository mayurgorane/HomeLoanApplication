package com.HomeLoanApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HomeLoanApplication.entites.EMI;

@Repository
public interface IEMIRepository extends JpaRepository<EMI, Long> {

}
