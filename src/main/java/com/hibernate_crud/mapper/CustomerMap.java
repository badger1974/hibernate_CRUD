package com.hibernate_crud.mapper;

import com.hibernate_crud.dto.CustomerDto;
import com.hibernate_crud.entity.CustomerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomerMap {

    private final PhoneMap phoneMap;

    public CustomerDto mapToDto(CustomerEntity entity) {
        CustomerDto dto = new CustomerDto();
        dto.setId(entity.getId());
        dto.setFio(entity.getFio());
        dto.setAddress(entity.getAddress());
        dto.setPhoneEntities(entity.getPhoneEntities().stream()
                .map(phoneMap::mapToPhoneDto)
                .collect(Collectors.toList()));
        return dto;
    }

    public CustomerEntity mapToEntity(CustomerDto dto) {
        CustomerEntity entity = new CustomerEntity();
        entity.setId(dto.getId());
        entity.setFio(dto.getFio());
        entity.setAddress(dto.getAddress());
        entity.setPhoneEntities(dto.getPhoneEntities().stream()
                .map(phoneMap::mapToPhoneEntity)
                .collect(Collectors.toList()));
        return entity;
    }

    public void map(CustomerEntity entity, CustomerDto dto) {
        entity.setId(dto.getId());
        entity.setFio(dto.getFio());
        entity.setAddress(dto.getAddress());
        entity.getPhoneEntities().forEach(it -> {
            var phoneOptional = dto.getPhoneEntities().stream().filter(ph -> it.getPhoneId().equals(ph.getPhoneId())).findFirst();
            phoneOptional.ifPresent(phoneDto -> it.setPhone(phoneDto.getPhone()));
        });
        entity.getPhoneEntities().removeIf(it -> dto.getPhoneEntities().stream().noneMatch(ph -> it.getPhoneId().equals(ph.getPhoneId())));

    }
}
