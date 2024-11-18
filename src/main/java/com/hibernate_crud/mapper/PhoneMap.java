package com.hibernate_crud.mapper;

import com.hibernate_crud.dto.CustomerDto;
import com.hibernate_crud.dto.PhoneDto;
import com.hibernate_crud.entity.CustomerEntity;
import com.hibernate_crud.entity.PhoneEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class PhoneMap {
    //из entity в dto
    public PhoneDto mapToPhoneDto(PhoneEntity entity) {
        PhoneDto dto = new PhoneDto();
        dto.setPhoneId(entity.getPhoneId());
        dto.setPhone(entity.getPhone());
        return dto;
    }

    //из dto в entity
    public PhoneEntity mapToPhoneEntity(PhoneDto dto) {
        PhoneEntity entity = new PhoneEntity();
        entity.setPhoneId(dto.getPhoneId());
        entity.setPhone(dto.getPhone());
        return entity;
    }
}
