package com.sample.dao;

import com.sample.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ClientRepository extends PagingAndSortingRepository<Client, Integer> {
    Page<Client> findByName(String name, Pageable pageable);

    Page<Client> findByNameOrderByBirthdayAsc(String name, Pageable pageable);

    Page<Client> findByNameAndBirthday(String name, LocalDate birthday, Pageable pageable);

    Page<Client> findByBirthdayBetween(LocalDate from, LocalDate to, Pageable pageable);
}