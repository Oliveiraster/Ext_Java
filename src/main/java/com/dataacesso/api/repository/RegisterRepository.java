package com.dataacesso.api.repository;

import com.dataacesso.api.model.Register;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface RegisterRepository extends JpaRepository<Register, Long> {
    List<Register> findByName(String name);
}
