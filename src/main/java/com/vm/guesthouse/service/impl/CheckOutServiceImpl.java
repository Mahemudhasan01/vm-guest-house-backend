package com.vm.guesthouse.service.impl;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional(rollbackOn = Exception.class)
public class CheckOutServiceImpl {
	
}
