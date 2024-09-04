package com.example.demo.services

import com.example.demo.domain.Client
import com.example.demo.exceptions.CreateExpection
import com.example.demo.exceptions.DeleteException
import com.example.demo.exceptions.UpdateException
import com.example.demo.repository.ClientRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ClientService (private val clientRepository: ClientRepository)  {
    fun findAllClients(): List<Client> {
        if (clientRepository.findAll().isEmpty()) {
            throw Exception()
        }
        return clientRepository.findAll();
    }

    fun findClientById(id: String): Client? {
        return clientRepository.findById(id).orElseThrow { com.example.demo.exceptions.NotFoundException("Client with id: $id") };
    }
            
    fun save(client: Client): Client {
        if (client.equals(null)) {
            throw CreateExpection("Erro ao tentar salvar um novo usuário")
        }
        return clientRepository.save(client);
    }

    fun update(id: String, client: Client): Client {
        if (findClientById(id) != null) {
            throw com.example.demo.exceptions.NotFoundException("Client with id: $id")
        } else if(client.equals(null)) {
            throw UpdateException("Erro ao tentar atualizar os dados")
        }
        return clientRepository.save(client);
    }
    fun delete(id: String) {
        if (findClientById(id) == null) {
            throw DeleteException("Erro ao localizar o id e deletar.")
        }
        clientRepository.deleteById(id);
    }
}