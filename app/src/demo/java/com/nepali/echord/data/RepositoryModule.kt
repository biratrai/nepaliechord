package com.nepali.echord.data

import dagger.Module
import dagger.Provides

/**
 * This is used by Dagger to inject the required arguments into the {@link SongDataRepositoryImpl}.
 */
@Module
 class RepositoryModule {
    @Provides
    internal fun providesSongRepository(): SongDataRepository {
        return SongDataRepositoryImpl()
    }
}