package com.nepali.echord.detailsong

import com.nepali.echord.ActivityScoped
import com.nepali.echord.data.SongDataRepository
import dagger.Module
import dagger.Provides


@Module
class DetailSongModule {
//    @Binds
//    @IntoMap
//    @ClassKey(SongDetailActivity::class)
//    internal abstract fun bindDetailSongInjectorFactory(builder: DetailSongComponent.Builder): AndroidInjector.Factory<*>

//    @ContributesAndroidInjector
//    internal abstract fun bindLobbyActivity(): SongDetailActivity

//    @Binds
//    internal abstract fun provideSongDetailView(songDetailActivity: SongDetailActivity): SongDetailContract.SongDetailView

//    @ActivityScoped
//    @Binds
//    internal abstract fun bindPresenter(songDetailPresenter: SongDetailPresenter): SongDetailPresenter

    @ActivityScoped
    @Provides
    internal fun songDetailPresenter(repository: SongDataRepository): SongDetailPresenter {
        return SongDetailPresenter(repository)
    }

}
