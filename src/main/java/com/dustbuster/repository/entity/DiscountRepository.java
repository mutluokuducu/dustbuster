package com.dustbuster.repository.entity;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
  Optional<Discount> findByDiscountCodeAndIsUsed(String discountCode,boolean isUsed);
}
