package com.nepali.echord.detailsong

import com.nepali.echord.ActivityScoped
import dagger.Binds
import dagger.Module

@Module
abstract class DetailSongModule {
//    @Binds
//    @IntoMap
//    @ClassKey(SongDetailActivity::class)
//    internal abstract fun bindDetailSongInjectorFactory(builder: DetailSongComponent.Builder): AndroidInjector.Factory<*>


    @ActivityScoped
    @Binds
    internal abstract fun sondDetailPresenter(presenter: SongDetailPresenter): SongDetailContract.SongDetailPresenter
}
