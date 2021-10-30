package me.life.productsearch.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.components.SingletonComponent
import me.life.productsearch.data.OrdersRepository
import me.life.productsearch.data.PrescriptionRepository
import me.life.productsearch.data.SearchRepository
import me.life.productsearch.network.ApiService

@InstallIn(ActivityRetainedComponent::class)
@Module
object RepositoryModule {

    @Provides
    fun providesSearchRepo(apiService: ApiService): SearchRepository {
        return SearchRepository(apiService = apiService)
    }

    @Provides
    fun providesMyOrdersRepo(apiService: ApiService): OrdersRepository {
        return OrdersRepository(apiService = apiService)
    }

    @Provides
    fun providesPrescriptionRepo(apiService: ApiService): PrescriptionRepository {
        return PrescriptionRepository(apiService = apiService)
    }
}