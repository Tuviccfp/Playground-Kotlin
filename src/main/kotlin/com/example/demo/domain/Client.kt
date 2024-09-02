package com.example.demo.domain

import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.Setter
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable

@Getter
@Setter
@EqualsAndHashCode
@Document(collection = "users")
data class Client (@Id private val id: String?, private val name: String, private val email: String): Serializable {
}