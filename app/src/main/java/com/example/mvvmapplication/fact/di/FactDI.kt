package com.example.mvvmapplication.fact.di

import com.example.mvvmapplication.fact.data.FactRepository
import com.example.mvvmapplication.fact.data.FactRepositoryImpl
import com.example.mvvmapplication.fact.data.remote.FactApi
import com.example.mvvmapplication.fact.data.remote.FactRemoteDatasource
import com.example.mvvmapplication.fact.data.remote.FactRemoteDatasourceImpl
import com.example.mvvmapplication.fact.presentation.FactViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val factModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://uselessfacts.jsph.pl/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    factory<FactApi> {
        get<Retrofit>().create(FactApi::class.java)
    }

    factory<FactRemoteDatasource> {
        FactRemoteDatasourceImpl(factApi = get())
    }

    factory<FactRepository> {
        FactRepositoryImpl(factRemoteDatasource = get())
    }

    viewModel<FactViewModel> {
        FactViewModel(factRepository = get())
    }
}