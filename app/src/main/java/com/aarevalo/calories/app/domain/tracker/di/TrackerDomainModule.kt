package com.aarevalo.calories.app.domain.tracker.di

import com.aarevalo.calories.app.domain.tracker.repository.TrackerRepository
import com.aarevalo.calories.app.domain.tracker.usecases.CalculateMealNutrientsUseCase
import com.aarevalo.calories.app.domain.tracker.usecases.DeleteTrackedFoodUseCase
import com.aarevalo.calories.app.domain.tracker.usecases.GetFoodsForDateUseCase
import com.aarevalo.calories.app.domain.tracker.usecases.SearchFoodUseCase
import com.aarevalo.calories.app.domain.tracker.usecases.TrackFoodUseCase
import com.aarevalo.calories.app.domain.tracker.usecases.TrackerUseCases
import com.aarevalo.calories.core.domain.preferences.Preferences
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
        trackerRepository: TrackerRepository,
        preferences: Preferences
    ) : TrackerUseCases {
        return TrackerUseCases(
            searchFoodUseCase = SearchFoodUseCase(trackerRepository),
            trackFoodUseCase = TrackFoodUseCase(trackerRepository),
            getFoodsForDateUseCase = GetFoodsForDateUseCase(trackerRepository),
            calculateMealNutrientsUseCase = CalculateMealNutrientsUseCase(preferences),
            deleteTrackedFoodUseCase = DeleteTrackedFoodUseCase(trackerRepository)
        )
    }
}