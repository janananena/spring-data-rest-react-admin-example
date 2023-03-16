package com.example.springdatarestreactadmin

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository

interface TestEntityRepository : CrudRepository<TestEntity, Long>, PagingAndSortingRepository<TestEntity, Long>