package dev.diegodc.data.preferences

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CommonPreferencesModule {
    @Provides
    @Singleton
    fun provideCommonPreferences(
        @ApplicationContext application: Context
    ) : CommonPreferences{
        return CommonSharedPreferences(application)
    }
}