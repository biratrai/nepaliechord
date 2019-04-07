package com.nepali.echord

import android.app.Application
import com.nepali.echord.data.RepositoryModule
import com.nepali.echord.data.SongDataRepository
import com.nepali.echord.detailsong.DetailSongModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * This is a Dagger component. Refer to {@link ChordApplication} for the list of Dagger components
 * used in this application.
 * <p>
 * Even though Dagger allows annotating a {@link Component} as a singleton, the code
 * itself must ensure only one instance of the class is created. This is done in {@link
 * ToDoApplication}.
 * //{@link AndroidSupportInjectionModule}
 * // is the module from Dagger.Android that helps with the generation
 * // and location of subcomponents.
 */
@Singleton
@Component(
        modules = [
            MainApplicationModule::class,
            AndroidSupportInjectionModule::class,
            ActivityBindingModule::class,
            RepositoryModule::class,
            DetailSongModule::class
        ]
)
interface MainApplicationComponent : AndroidInjector<ChordApplication> {
    // Gives us syntactic sugar. we can then do DaggerAppComponent.builder().application(this).build().inject(this);
    // never having to instantiate any modules or say which module we are passing the application to.
    // Application will just be provided into our app graph now.

    abstract fun getSongRepository(): SongDataRepository

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): MainApplicationComponent.Builder

        fun build(): MainApplicationComponent
    }

}




