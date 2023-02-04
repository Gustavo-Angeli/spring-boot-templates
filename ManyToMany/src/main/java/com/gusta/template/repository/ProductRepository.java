package com.gusta.template.repository;

import com.gusta.template.model.entities.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
