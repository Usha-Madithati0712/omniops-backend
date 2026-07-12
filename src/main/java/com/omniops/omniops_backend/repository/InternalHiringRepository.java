package com.omniops.omniops_backend.repository;

import com.omniops.omniops_backend.entity.InternalHiring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternalHiringRepository extends JpaRepository<InternalHiring, Integer> {

}