package com.example.demo.repository

import com.example.demo.domain.Client
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientRepository : MongoRepository<Client, String> {
}