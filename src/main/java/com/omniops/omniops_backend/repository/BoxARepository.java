package com.omniops.omniops_backend.repository;

import com.omniops.omniops_backend.entity.BoxA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoxARepository extends JpaRepository<BoxA, Integer> {

}