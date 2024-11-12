package com.example.mvvmapplication.login.data.remote

import com.example.mvvmapplication.login.model.UserAuth

interface LoginRemoteDatasource {
    suspend fun createAccount(email: String, password: String): UserAuth
    suspend fun signin(email: String, password: String): UserAuth
    suspend fun recover(email: String)
    fun isSessionValid(): Boolean
}