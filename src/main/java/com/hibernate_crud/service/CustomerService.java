package com.hibernate_crud.service;

import com.hibernate_crud.dto.CustomerDto;
import com.hibernate_crud.entity.CustomerEntity;
import com.hibernate_crud.mapper.CustomerMap;
import com.hibernate_crud.mapper.PhoneMap;
import com.hibernate_crud.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMap customerMap;
    private final PhoneMap phoneMap;

    public List<CustomerDto> getAllCustomers() {//    для листа пользователей используем стрим
        return customerRepository.findAll().stream()// создали из листа стрим
                .map(customerMap::mapToDto) // оператором из streamAPI map, использовали для каждого элемента метод mapToCustomerDto из класса CustomerMap
                .collect(Collectors.toList());//превратили стрим обратно в коллекцию, а точнее в лист
    }

    public CustomerDto getCustomerById(Long id) {
        return customerMap.mapToDto( //в метод mapToCustomer
                customerRepository.findById(id)//поместили результат поиска по id
                        .orElseThrow(RuntimeException::new));//если ничего не нашли, то вернем исключение
    }

    @Transactional
    public CustomerEntity createCustomer(CustomerDto customerDto) {
        CustomerEntity customerEntity = customerMap.mapToEntity(customerDto);
        return customerRepository.save(customerEntity);
    }

    @Transactional
    public CustomerDto updateCustomer(CustomerDto dto) {
        CustomerEntity customerEntity = customerRepository.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));// получение кастомера из базы

        customerMap.map(customerEntity, dto);// вызвать метод маппера для перекладывания данных из ДТО в Ентити
        customerRepository.save(customerEntity);
        return customerMap.mapToDto(customerEntity);// вернуть резульат маппинга из ентити в дто опять преобразовать)
    }

    @Transactional
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
