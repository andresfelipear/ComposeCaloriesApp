package com.aarevalo.calories.app.domain.tracker.di

import com.aarevalo.calories.app.domain.tracker.repository.TrackerRepository
import com.aarevalo.calories.app.domain.tracker.usecases.SearchFoodUseCase
import com.aarevalo.calories.app.domain.tracker.usecases.TrackerUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {

    @ViewModelScoped
    @Provides
    fun provideTrackerUseCase(
        trackerRepository: TrackerRepository
    ) : TrackerUseCase {
        return TrackerUseCase(
            searchFoodUseCase = SearchFoodUseCase(trackerRepository)
        )
    }
}