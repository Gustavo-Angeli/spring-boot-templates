package com.gusta.template.repository;

import com.gusta.template.model.entities.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface PersonRepository extends JpaRepository <PersonEntity, Long> {

    @Query("SELECT p FROM PersonEntity p WHERE p.name =:name")
    Optional<PersonEntity> findByName(@Param("name") String name);

}
