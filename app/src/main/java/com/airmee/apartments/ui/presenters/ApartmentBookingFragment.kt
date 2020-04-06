package com.airmee.apartments.ui.presenters

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.airmee.apartments.R
import com.airmee.apartments.ui.viewmodel.ApartmentBookingViewModel
import com.airmee.apartments.utils.DatePicker
import com.airmee.apartments.utils.LocationProvider
import com.airmee.apartments.utils.ifNotNull
import kotlinx.android.synthetic.main.fragment_apartment_booking.*
import org.kodein.di.generic.instance

class ApartmentBookingFragment : KodeinFragment() {

    private val locationProvider: LocationProvider by instance()
    private val viewModel: ApartmentBookingViewModel by instance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        return inflater.inflate(
            R.layout.fragment_apartment_booking,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context = checkNotNull(context)

        with(viewModel) {
            userAddress.observe(
                this@ApartmentBookingFragment, Observer { addressTitle.text = it })
            date.observe(
                this@ApartmentBookingFragment, Observer { datePicker.text = it })
            bedroomsAmount.observe(
                this@ApartmentBookingFragment, Observer { count.text = it.toString() }
            )
        }
        viewModel.setHardcodeLocation()

        // Clicks
        containerLocation.setOnClickListener {
            // location request
            if (locationProvider.isLocationPermissionGranted()) {
                viewModel.displayUserLocation()
            } else {
                locationProvider.requestLocationPermissions(this)
            }
        }

        increase.setOnClickListener { viewModel.incrementValue() }
        decrease.setOnClickListener { viewModel.decrementValue() }
        datePicker.setOnClickListener { showDataPicker(context) }

        applyButton.setOnClickListener { applyAndNavigate() }
    }

    private fun showDataPicker(context: Context) {
        val datePickerDialog = DatePicker.getDatePickerDialog(context)
        fragmentManager?.let { fm -> datePickerDialog.show(fm, null) }
        datePickerDialog.addOnPositiveButtonClickListener {
            ifNotNull(it.first, it.second) { startDate, endDate ->
                val formatDateRange = DateUtils.formatDateRange(
                    context,
                    startDate,
                    endDate,
                    DateUtils.FORMAT_ABBREV_TIME
                )
                viewModel.saveDateValues(formatDateRange)
            }
        }
    }

    private fun applyAndNavigate() {
        with(viewModel) {
            val userLocation = userLocation.value
            if (userLocation == null) {
                Toast
                    .makeText(context, "You need to turn on location to continue", Toast.LENGTH_LONG)
                    .show()
            } else {
                findNavController().navigate(
                    ApartmentBookingFragmentDirections.applyFilteringAndSearch(
                        bedroomsAmount.value ?: 0, // todo ,
                        userLocation.latitude.toString(),
                        userLocation.longitude.toString()
                    )
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LocationProvider.PERMISSION_ACCESS_LOCATION) {
            if (grantResults.size == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                && grantResults[1] == PackageManager.PERMISSION_GRANTED
            ) {
                viewModel.displayUserLocation()
            } else {
                // handle error todo
            }
        }
    }
}