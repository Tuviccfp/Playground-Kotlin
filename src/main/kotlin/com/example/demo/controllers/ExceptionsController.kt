package com.example.demo.controllers

import com.example.demo.exceptions.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@RestControllerAdvice
class ExceptionsController {

    @ExceptionHandler(Exception::class)
    fun genericExceptionHandler(e: Exception): ResponseEntity<ApiError> {
        val apiError = e.message?.let {
            ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                it,
                "Erro desconhecido de servidor")
        }

        return ResponseEntity(apiError, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(NotFoundException::class)
    fun notFoundHandler(e: RuntimeException): ResponseEntity<ApiError> {
        val apiError = e.message?.let {
            ApiError(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                it,
                "Não é possível encontrar nada com esse id")
        }
        return ResponseEntity(apiError, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(CreateExpection::class)
    fun notCreatedHandler(e: RuntimeException): ResponseEntity<ApiError> {
        val apiError = e.message?.let {
            ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                it,
                "Erro ao tentar criar um novo usuário")
        }
        return ResponseEntity(apiError, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(UpdateException::class)
    fun notUpdatedHandler(e: RuntimeException): ResponseEntity<ApiError> {
        val apiError = e.message?.let {
            ApiError(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                it,
                "Erro ao tentar atualizar os dados"
            )
        }
        return ResponseEntity(apiError, HttpStatus.CONFLICT)
    }

    @ExceptionHandler(DeleteException::class)
    fun notDeletedHandler(e: RuntimeException): ResponseEntity<ApiError> {
        val apiError = e.message?.let {
            ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                it,
                "Erro ao tentar excluir os dados"
            )
        }
        return ResponseEntity(apiError, HttpStatus.BAD_REQUEST)
    }

}