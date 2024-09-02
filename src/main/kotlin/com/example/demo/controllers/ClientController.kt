package com.example.demo.controllers

import com.example.demo.domain.Client
import com.example.demo.services.ClientService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/client"])
class ClientController (private val clientService: ClientService) {
    @PostMapping(value = ["/save-usuários"])
    fun createdClient(@RequestBody client: Client) {
        clientService.save(client);
    }

    @GetMapping(value = ["/get-usuarios"])
    fun searchByAll() : List<Client> {
        return clientService.findAllClients();
    }

    @GetMapping(value = ["/get-usuarios/{id}"])
    fun findById(@PathVariable("id") id: String): Client? {
        return clientService.findClientById(id);
    }

    @PutMapping(value = ["/update-usuarios/{id}"])
    fun updatedClient(@PathVariable id: String, @RequestBody client: Client) {
        clientService.update(id, client);
    }

    @DeleteMapping(value = ["/delete-usuarios/{id}"])
    fun deleteById(@PathVariable("id") id: String) {
        clientService.delete(id)
    }
}