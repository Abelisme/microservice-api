package com.example.will.repository;


import com.example.will.domain.NameValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author
 */
@Repository
public interface NameValueRepository extends JpaRepository<NameValue, String> {

  @Query(value = "SELECT nv FROM NameValue nv WHERE name = :name")
  Optional<NameValue> findByName(String name);

}
