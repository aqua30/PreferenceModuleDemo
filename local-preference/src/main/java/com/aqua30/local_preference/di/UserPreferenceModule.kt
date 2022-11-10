package com.aqua30.local_preference.di

import com.aqua30.local_preference.UserPreference
import com.aqua30.local_preference.data.UserDataStore
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent

/**
 * @author Saurabh Pant
 * @since 11/8/22
 * @summary Hilt Module for User Preferences
 */
@InstallIn(ViewModelComponent::class)
@Module
abstract class UserPreferenceModule {

    @Binds
    abstract fun bindUserPreferences(impl: UserDataStore): UserPreference
}