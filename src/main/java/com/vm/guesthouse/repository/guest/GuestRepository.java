package com.vm.guesthouse.repository.guest;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.vm.guesthouse.entity.PrimaryGuest;
import com.vm.guesthouse.entity.Room;

public interface GuestRepository extends JpaRepository<PrimaryGuest, Long>, JpaSpecificationExecutor<PrimaryGuest> {

	Optional<PrimaryGuest> findTopByMobile(String mobile);


}
