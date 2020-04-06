package com.airmee.apartments.ui

import androidx.fragment.app.Fragment
import com.airmee.apartments.ui.presenters.ApartmentListFragmentArgs
import com.airmee.apartments.ui.viewmodel.ApartmentBookingViewModel
import com.airmee.apartments.ui.viewmodel.ApartmentListViewModel
import com.airmee.apartments.utils.ViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

internal val uiModule = Kodein.Module("UiModule") {

    // Start fragment view model
    bind<ApartmentBookingViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
        ViewModelFactory.of(context) { ApartmentBookingViewModel(instance()) }
    }
    // List view model
    bind<ApartmentListViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
        ViewModelFactory.of(context) { ApartmentListViewModel(instance(), instance(), instance()) }
    }

    bind() from singleton { ApartmentListAdapter() }
    bind() from singleton { ApartmentListFragmentArgs }
}