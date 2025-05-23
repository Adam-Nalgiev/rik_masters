package ru.rikmasters.network_client_impl.di

import org.koin.dsl.bind
import org.koin.dsl.module
import ru.rikmasters.network_client_api.NetworkClientApi
import ru.rikmasters.network_client_impl.client.NetworkClient

val networkModule = module {
    single { NetworkClient } bind NetworkClientApi::class
}