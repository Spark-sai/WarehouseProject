package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Part;

public interface PartRepository extends JpaRepository<Part, Integer>{

}
