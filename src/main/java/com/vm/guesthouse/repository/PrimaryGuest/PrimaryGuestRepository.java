package com.vm.guesthouse.repository.PrimaryGuest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.vm.guesthouse.entity.PrimaryGuest;

public interface PrimaryGuestRepository extends JpaRepository<PrimaryGuest, Long>, JpaSpecificationExecutor<PrimaryGuest> {

	
}
