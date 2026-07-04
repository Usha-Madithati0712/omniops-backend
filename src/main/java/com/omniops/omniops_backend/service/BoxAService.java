package com.omniops.omniops_backend.service;

import com.omniops.omniops_backend.entity.BoxA;

import java.util.List;

public interface BoxAService {

    BoxA save(BoxA boxA);

    List<BoxA> findAll();

    BoxA findById(Integer id);

    BoxA update(Integer id, BoxA boxA);

    void delete(Integer id);

}