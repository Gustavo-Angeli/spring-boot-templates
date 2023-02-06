package com.gusta.template.repositories;

import com.gusta.template.models.entities.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface CartRepository extends JpaRepository <CartEntity, Long> {
}
