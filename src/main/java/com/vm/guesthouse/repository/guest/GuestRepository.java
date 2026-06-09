package com.vm.guesthouse.repository.guest;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.vm.guesthouse.entity.Guest;
import com.vm.guesthouse.entity.Room;

public interface GuestRepository extends JpaRepository<Guest, Long>, JpaSpecificationExecutor<Guest> {

	Optional<Guest> findByMobile(String mobile);


}
