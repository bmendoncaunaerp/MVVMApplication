package com.example.mvvmapplication.login.data

import com.example.mvvmapplication.login.data.remote.LoginRemoteDatasource
import com.example.mvvmapplication.login.model.UserAuth

class LoginRepositoryImpl(
    private val remoteDatasource: LoginRemoteDatasource
): LoginRepository {
    override fun isSessionValid(): Boolean {
        return remoteDatasource.isSessionValid()
    }

    override suspend fun createAccount(email: String, password: String): UserAuth {
        return remoteDatasource.createAccount(email, password)
    }

    override suspend fun login(email: String, password: String): UserAuth {
        return remoteDatasource.signin(email, password)
    }

    override suspend fun recover(email: String) {
        remoteDatasource.recover(email)
    }
}