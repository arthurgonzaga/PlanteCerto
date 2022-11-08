package br.com.plantecerto.domain.data.repository.ipinfo

import br.com.plantecerto.domain.data.entity.responses.LocationResponse

/**
 * Created by Arthur Gonzaga on 10/30/2022
 */
interface IpInfoRepository {

    suspend fun getLocation(): LocationResponse
}