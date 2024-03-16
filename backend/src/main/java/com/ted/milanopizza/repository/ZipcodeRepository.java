package com.ted.milanopizza.repository;

import com.ted.milanopizza.model.Zipcode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZipcodeRepository extends JpaRepository<Zipcode, Long> {

}
