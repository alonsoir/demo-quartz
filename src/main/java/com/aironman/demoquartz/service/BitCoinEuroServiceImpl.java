package com.aironman.demoquartz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aironman.demoquartz.model.BitcoinEuroEntity;
import com.aironman.demoquartz.repository.BitCoinEuroRepository;

@Service
public class BitCoinEuroServiceImpl implements BitCoinEuroService{

	@Autowired
	private BitCoinEuroRepository repository;
	
	@Override
	@Transactional
	public BitcoinEuroEntity create(BitcoinEuroEntity entity) {
		// TODO Auto-generated method stub
		return repository.save(entity);
	}

}
