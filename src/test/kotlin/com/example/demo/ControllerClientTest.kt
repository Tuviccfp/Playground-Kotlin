package com.example.demo

import com.example.demo.controllers.ClientController
import com.example.demo.domain.Client
import com.example.demo.services.ClientService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import kotlin.test.assertNotNull

@ExtendWith(MockitoExtension::class)
class ControllerClientTest {

    private val client = Client(
        "65efc1356d2b115928a39832",
        "João",
        "joao@gmail.com")

    @Mock
    private lateinit var clientService: ClientService

    @InjectMocks
    private lateinit var clientController: ClientController

    @BeforeEach
    fun setUp() {
        Mockito.reset(clientService)
    }

    @Test
    fun saveUsuariosController() {
        Mockito.`when`(clientService.save(client)).thenReturn(client)

        val savedClient = clientController.createdClient(client);
        assertEquals(ResponseEntity.status(HttpStatus.CREATED).body("Criado com sucesso"), savedClient)
        assertNotNull(savedClient)

        Mockito.verify(clientService, Mockito.times(1)).save(client)
    }

    @Test
    fun findAllController() {
        val listClient = listOf(client)

        Mockito.`when`(clientService.findAllClients()).thenReturn(listClient)

        val listResult = clientController.searchByAll()
        assertEquals(listResult.body, listClient)
        assertEquals(HttpStatus.OK, listResult.statusCode)
        listResult.body?.let { assertEquals(1, it.size) }
        assertNotNull(listResult)

        Mockito.verify(clientService, Mockito.times(1)).findAllClients()
    }

    @Test
    fun findByIdController() {
        Mockito.`when`(client.id?.let { clientService.findClientById(it) }).thenReturn(client)

        val result = clientController.findById(client.id!!)
        assertEquals(result.body, client)
        assertEquals(HttpStatus.OK, result.statusCode)
        assertEquals(client, result.body)
        assertNotNull(result)

        Mockito.verify(clientService, Mockito.times(1)).findClientById(client.id!!)
    }

    @Test
    fun updateClient() {
        client.name = "Novo nome"
        client.email = "novo-email-nome@email.com"

        Mockito.`when`(clientService.update(client.id!!, client)).thenReturn(client)

        val result = clientController.updatedClient(client.id!!, client)

        assertEquals(HttpStatus.CREATED, result.statusCode)
        assertEquals("Alterado com sucesso", result.body)

        Mockito.verify(clientService, Mockito.times(1)).update(client.id!!, client)
    }

    @Test
    fun deleteController() {

    }
}