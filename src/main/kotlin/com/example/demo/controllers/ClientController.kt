package com.example.demo.controllers

import com.example.demo.domain.Client
import com.example.demo.services.ClientService
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.mongodb.core.aggregation.BooleanOperators.Not
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.io.Serializable

@RestController
@RequestMapping(value = ["/client"])
class ClientController (private val clientService: ClientService) {
    @PostMapping(value = ["/save-usuários"])
    fun createdClient(@RequestBody client: Client): ResponseEntity<String> {
        try {
            clientService.save(client);
            return ResponseEntity.status(HttpStatus.CREATED).body("Criado com sucesso");
        } catch (e: NotFoundException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao criar um novo cliente.")
        }
    }

    @GetMapping(value = ["/get-usuarios"])
    fun searchByAll() : ResponseEntity<List<Client>> {
        try {
            val result = clientService.findAllClients();
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (e: NotFoundException) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(emptyList())
        }
    }

    @GetMapping(value = ["/get-usuarios/{id}"])
    fun findById(@PathVariable("id") id: String): ResponseEntity<Client> {
        try {
            val result = clientService.findClientById(id);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (e: NotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        }
    }

    @PutMapping(value = ["/update-usuarios/{id}"])
    fun updatedClient(@PathVariable id: String, @RequestBody client: Client): ResponseEntity<String> {
        try {
            clientService.update(id, client);
            return ResponseEntity.status(HttpStatus.OK).body("Alterado com sucesso")
        } catch (e: ChangeSetPersister.NotFoundException) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao atualizar o client")
        }
    }

    @DeleteMapping(value = ["/delete-usuarios/{id}"])
    fun deleteById(@PathVariable("id") id: String): ResponseEntity<String> {
        try {
            clientService.delete(id)
            return ResponseEntity.status(HttpStatus.OK).body("Deletado com sucesso")
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao deletar o client")
        }
    }
}