package br.com.plantecerto

import android.app.Application
import br.com.plantecerto.domain.network.EmbrapaService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/**
 * Created by Arthur Gonzaga on 10/25/2022
 */
class PlanteCertoApplication: Application() {

    init {
        CoroutineScope(Dispatchers.IO).launch {
            EmbrapaService.init()
        }
    }

}