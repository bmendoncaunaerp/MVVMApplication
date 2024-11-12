package com.example.mvvmapplication.login.di

import com.example.mvvmapplication.login.data.LoginRepository
import com.example.mvvmapplication.login.data.LoginRepositoryImpl
import com.example.mvvmapplication.login.data.remote.LoginRemoteDatasource
import com.example.mvvmapplication.login.data.remote.LoginRemoteDatasourceImpl
import com.example.mvvmapplication.login.presentation.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    factory<LoginRemoteDatasource> {
        LoginRemoteDatasourceImpl(firebaseAuth = FirebaseAuth.getInstance())
    }

    factory<LoginRepository> {
        LoginRepositoryImpl(remoteDatasource = get())
    }

    viewModel {
        LoginViewModel(loginRepository = get())
    }
}