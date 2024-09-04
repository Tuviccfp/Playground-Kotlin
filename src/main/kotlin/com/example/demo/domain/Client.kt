package com.example.demo.domain

import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.Setter
import org.jetbrains.annotations.NotNull
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable

@Document(collection = "users")
data class Client (@Id var id: String?, @NotNull var name: String, @NotNull var email: String): Serializable {
}