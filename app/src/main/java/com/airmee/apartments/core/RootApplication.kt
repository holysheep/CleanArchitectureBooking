package com.airmee.apartments.core

import android.app.Application
import com.airmee.apartments.data.dataModule
import com.airmee.apartments.domain.domainModule
import com.airmee.apartments.ui.uiModule
import com.airmee.apartments.utils.FragmentArgsExternalSource
import com.airmee.apartments.utils.geoModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule

class RootApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@RootApplication))
        import(networkModule)
        import(dataModule)
        import(domainModule)
        import(uiModule)
        import(geoModule)

        externalSources.add(FragmentArgsExternalSource()) // workaround for injecting args
    }
}