package com.mastercard.ceres.core.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mastercard.ceres.core.db.model.BisAppPO;

public interface BisAppRepository extends JpaRepository<BisAppPO, Long> {
}
