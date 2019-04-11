package com.nepali.echord.mainsong

import com.nepali.echord.ActivityScoped
import com.nepali.echord.data.SongDataRepository
import dagger.Module
import dagger.Provides

@Module
class MainSongModule {
    @ActivityScoped
    @Provides
    internal fun songMainPresenter(repository: SongDataRepository): MainSongContract.MainSongPresenter {
        return MainSongActivityPresenter(repository)
    }
}