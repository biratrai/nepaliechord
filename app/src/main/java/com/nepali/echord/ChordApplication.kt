package com.nepali.echord

import dagger.android.DaggerApplication

/**
 * We create a custom {@link Application} class that extends  {@link DaggerApplication}.
 * We then override applicationInjector() which tells Dagger how to make our @Singleton Component
 * We never have to call `component.inject(this)` as {@link DaggerApplication} will do that for us.
 */
class ChordApplication : DaggerApplication() {

    override fun applicationInjector() = seedBuilder(DaggerMainApplicationComponent.builder())

    private fun seedBuilder(builder: MainApplicationComponent.Builder): MainApplicationComponent {
        builder.seedInstance(this)
        return builder.build()
    }

}