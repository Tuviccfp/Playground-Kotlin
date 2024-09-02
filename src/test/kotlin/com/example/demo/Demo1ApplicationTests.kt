package com.example.demo

import com.example.demo.domain.Client
import com.example.demo.repository.ClientRepository
import com.example.demo.services.ClientService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations.initMocks
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*
import kotlin.test.assertNull

@ExtendWith(MockitoExtension::class)
class Demo1ApplicationTests {

    private final val client = Client(
        "65efc1356d2b115928a39832",
        "João",
        "joao@gmail.com");

    @Mock
    private lateinit var clientRepository: ClientRepository;

    @InjectMocks
    private lateinit var clientService: ClientService

    @BeforeEach
    fun setUp() {
        Mockito.reset(clientRepository)
    }

    @Test
    fun findAllClients() {
        val listClients: List<Client> = listOf(client);

        Mockito.`when`(clientRepository.findAll()).thenReturn(listClients)

        var result = clientService.findAllClients();
        println("Retorno após when do mockito: $result");

        assertEquals(1, result.size);
        assertNotNull(result);
        Mockito.verify(clientRepository, Mockito.times(1)).findAll();
    }

    @Test
    fun findClientById() {
        Mockito.`when`(client.id?.let { clientRepository.findById(it) }).thenReturn(Optional.of(client))
        //Operador seguro de chamada: "?.", é um executor de chamada caso o valor não seja nulo, o retorno continua a direita do operador,
        //caso seja, retorna null por padrão

        val result = client.id?.let { clientService.findClientById(it) }
        assertNotNull(result);
        assertEquals(result, client);
        client.id?.let { Mockito.verify(clientRepository, Mockito.times(1)).findById(it) }
    }

    @Test
    fun createClient() {
        Mockito.`when`(clientRepository.save(client)).thenReturn(client);
        val savedClient = clientService.save(client);
        assertNotNull(savedClient);
        Mockito.verify(clientRepository,Mockito.times(1)).save(client);
    }

    @Test
    fun updateClient() {
        Mockito.`when`(client.id?.let { clientRepository.findById(it) }).thenReturn(Optional.of(client))
        Mockito.`when`(clientRepository.save(client)).thenReturn(client);
        val updatedClient = client.id?.let { clientService.update(it,client) };
        assertNotNull(updatedClient);
        Mockito.verify(clientRepository, Mockito.times(1)).save(client);
    }

    @Test
    fun deleteClient() {
        client.id?.let { Mockito.doNothing().`when`(clientRepository).deleteById(it) }
        client.id?.let { clientService.delete(it) }
        client.id?.let { Mockito.verify(clientRepository, Mockito.times(1)).deleteById(it) }
    }
}
