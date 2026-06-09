package com.vm.guesthouse.repository.property;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.vm.guesthouse.entity.Property;

public interface PropertyRepository extends JpaRepository<Property, Long>, JpaSpecificationExecutor<Property> {

	Optional<Property> findByIdAndIsActiveTrue(Long propertyId);

}
