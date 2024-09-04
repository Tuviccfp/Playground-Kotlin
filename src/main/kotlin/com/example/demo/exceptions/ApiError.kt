package com.example.demo.exceptions

import com.fasterxml.jackson.annotation.JsonFormat
import lombok.Builder
import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime

@Builder
data class ApiError(
    @JsonFormat(pattern = "dd-MM-yyyy")
    val dateError: LocalDateTime,
    val statusCode: Int,
    val statusMessage: String,
    val message: String,
)
