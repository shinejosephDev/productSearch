package me.life.productsearch.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import me.life.productsearch.data.OrdersRepository
import me.life.productsearch.data.PrescriptionRepository
import me.life.productsearch.data.SearchRepository
import me.life.productsearch.usecase.OrderUsecase
import me.life.productsearch.usecase.PrescriptionUsecase
import me.life.productsearch.usecase.SearchUsecase

@InstallIn(ActivityRetainedComponent::class)
@Module
object UsecaseModule {

    @Provides
    fun providesDataUsecase(searchRepository: SearchRepository): SearchUsecase {
        return SearchUsecase(searchRepository)
    }

    @Provides
    fun providesOrderUsecase(ordersRepository: OrdersRepository): OrderUsecase {
        return OrderUsecase(ordersRepository)
    }

    @Provides
    fun providesPrescriptionUsecase(prescriptionRepository: PrescriptionRepository): PrescriptionUsecase {
        return PrescriptionUsecase(prescriptionRepository)
    }

}