package com.gusta.template.repositories;

import com.gusta.template.models.entities.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query("SELECT p FROM ProductEntity p WHERE p.name =:name")
    Optional<ProductEntity> findByName(@Param("name") String name);

}
