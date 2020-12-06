package com.mastercard.ceres.core.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mastercard.ceres.core.db.model.BisPluginPO;

public interface BisPluginRepository extends JpaRepository<BisPluginPO, Long> {
}
