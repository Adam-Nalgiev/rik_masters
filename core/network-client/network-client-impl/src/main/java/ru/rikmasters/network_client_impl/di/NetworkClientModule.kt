package ru.rikmasters.network_client_impl.di

import org.koin.dsl.module
import ru.rikmasters.network_client_impl.client.NetworkClient

val networkModule = module {
    single<NetworkClient> { NetworkClient }
}