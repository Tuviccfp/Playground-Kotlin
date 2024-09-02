package com.example.demo.domain

import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.Setter
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable

@Document(collection = "users")
data class Client (@Id val id: String?, val name: String, val email: String): Serializable {
}