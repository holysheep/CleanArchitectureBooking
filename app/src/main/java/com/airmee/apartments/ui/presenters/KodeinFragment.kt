package com.airmee.apartments.ui.presenters

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.airmee.apartments.BuildConfig
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinTrigger
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.kcontext

abstract class KodeinFragment : Fragment(), KodeinAware {

    @SuppressWarnings("LeakingThisInConstructor")
    final override val kodeinContext = kcontext<Fragment>(this)
    final override val kodein: Kodein by kodein()

    final override val kodeinTrigger: KodeinTrigger?
        get() = if (BuildConfig.DEBUG) KodeinTrigger() else super.kodeinTrigger

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        kodeinTrigger?.trigger()
    }
}