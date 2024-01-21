package com.example.cafebrown.domain.repository

interface CheckUserDataRepository {

    suspend fun check(): Int

}