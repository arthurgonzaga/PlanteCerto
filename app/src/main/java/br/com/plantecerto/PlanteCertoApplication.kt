package br.com.plantecerto

import android.app.Application
import br.com.plantecerto.domain.di.NetworkModule
import br.com.plantecerto.domain.data.network.EmbrapaService
import br.com.plantecerto.domain.data.network.interceptor.AgritecInteceptor
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Arthur Gonzaga on 10/25/2022
 */
@HiltAndroidApp
class PlanteCertoApplication(): Application() {

    init { initEmbrapaService() }

    fun initEmbrapaService() {
        CoroutineScope(Dispatchers.IO).launch {
            AgritecInteceptor.accessToken = NetworkModule.providesEmbrapaService().getAccessToken().get()
        }
    }
}