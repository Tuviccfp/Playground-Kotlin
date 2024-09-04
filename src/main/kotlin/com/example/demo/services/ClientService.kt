package com.example.demo.services

import com.example.demo.domain.Client
import com.example.demo.repository.ClientRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.mongodb.core.aggregation.BooleanOperators.Not
import org.springframework.stereotype.Service
import java.util.*

@Service
class ClientService (private val clientRepository: ClientRepository)  {
    fun findAllClients(): List<Client> {
        return clientRepository.findAll();
    }

    fun findClientById(id: String): Client? {
        return clientRepository.findById(id).orElse(null);
    }

    fun save(client: Client): Client {
        return clientRepository.save(client);
    }

    fun update(id: String, client: Client): Client {
        if (findClientById(id) != null) {
            throw NotFoundException()
        }
            return clientRepository.save(client);
    }
    fun delete(id: String) {
        clientRepository.deleteById(id);
    }
}