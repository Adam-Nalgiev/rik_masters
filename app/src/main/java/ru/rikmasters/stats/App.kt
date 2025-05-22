package ru.rikmasters.stats

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.rikmasters.circular_chart_impl.di.circularChartModule
import ru.rikmasters.line_chart_impl.di.lineChartModule
import ru.rikmasters.network_client_impl.di.networkModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                networkModule,
                lineChartModule,
                circularChartModule
            )
        }
    }
}