package com.hibernate_crud.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CustomerDto {

    private Long id;

    private String fio;

    private String address;

    private List<PhoneDto> phoneEntities = new ArrayList<>();
}
