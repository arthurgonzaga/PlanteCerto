package br.com.plantecerto.domain.di

import br.com.plantecerto.domain.data.repository.agritec.AgritecRepository
import br.com.plantecerto.domain.data.repository.agritec.AgritecRepositoryImpl
import br.com.plantecerto.domain.data.repository.ipinfo.IpInfoRepository
import br.com.plantecerto.domain.data.repository.ipinfo.IpInfoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Arthur Gonzaga on 10/30/2022
 */
@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindAgritecRepository(impl: AgritecRepositoryImpl): AgritecRepository

    @Binds
    @Singleton
    fun bindIpInfoRepository(impl: IpInfoRepositoryImpl): IpInfoRepository

}