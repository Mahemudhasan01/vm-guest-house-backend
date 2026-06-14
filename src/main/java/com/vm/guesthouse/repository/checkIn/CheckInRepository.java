package com.vm.guesthouse.repository.checkIn;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.vm.guesthouse.entity.CheckIn;


public interface CheckInRepository extends JpaRepository<CheckIn, Long>, JpaSpecificationExecutor<CheckIn> {

	CheckIn findByRoomId(Long roomId);

}
