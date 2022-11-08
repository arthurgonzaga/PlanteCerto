package br.com.plantecerto.domain.data.repository.ipinfo

import br.com.plantecerto.domain.data.entity.responses.LocationResponse
import br.com.plantecerto.domain.data.network.IpInfoService
import javax.inject.Inject

/**
 * Created by Arthur Gonzaga on 10/30/2022
 */
class IpInfoRepositoryImpl @Inject constructor(
    val service: IpInfoService
): IpInfoRepository {

    override suspend fun getLocation(): LocationResponse {
        return service.getLocation()
    }
}