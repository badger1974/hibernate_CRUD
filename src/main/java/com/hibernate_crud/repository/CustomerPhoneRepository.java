package com.hibernate_crud.repository;

import com.hibernate_crud.entity.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerPhoneRepository extends JpaRepository <PhoneEntity, Long> {
}
