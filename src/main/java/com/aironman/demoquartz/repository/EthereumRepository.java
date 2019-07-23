package com.aironman.demoquartz.repository;

import com.aironman.demoquartz.model.EthereumEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EthereumRepository extends JpaRepository<EthereumEntity,Long> {

}