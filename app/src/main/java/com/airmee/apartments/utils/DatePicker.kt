package com.airmee.apartments.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.core.util.Pair
import com.airmee.apartments.R
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*

object DatePicker {

    @SuppressLint("PrivateResource")
    fun getDatePickerDialog(context: Context): MaterialDatePicker<Pair<Long, Long>> {
        val dialogTheme = resolveOrThrow(context, R.attr.materialCalendarTheme)
        val calendar = Calendar.getInstance(TimeZone.getDefault());

        // Current date
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH) + 1
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

        val startDate = calendar.apply {
            set(currentYear, currentMonth, currentDay)
        }.timeInMillis

        val endDate = calendar.apply {
            set(currentYear + 10, currentMonth, currentDay) // 2019-12-25
        }.timeInMillis

        val startDatePlusWeek = calendar.time.time + 604800000L // + week :)

        val calendarConstraints = CalendarConstraints.Builder()
            .setStart(startDate)
            .setEnd(endDate)
            .setValidator(MinMaxDateValidator(startDate, startDatePlusWeek))
            .build()

        return MaterialDatePicker.Builder.dateRangePicker()
            .setTheme(dialogTheme)
            .setTitleText("Select Date Range")
            .setCalendarConstraints(calendarConstraints)
            .build()
    }

    private fun resolveOrThrow(context: Context, @AttrRes attributeResId: Int): Int {
        val typedValue = TypedValue()
        if (context.theme.resolveAttribute(attributeResId, typedValue, true)) {
            return typedValue.data
        }
        throw IllegalArgumentException(context.resources.getResourceName(attributeResId))
    }

    data class MinMaxDateValidator(
        var minDate: Long = 0,
        var maxDate: Long = 0) : CalendarConstraints.DateValidator {
        companion object CREATOR : Parcelable.Creator<MinMaxDateValidator> {
            override fun createFromParcel(parcel: Parcel): MinMaxDateValidator {
                return MinMaxDateValidator()
            }
            override fun newArray(size: Int): Array<MinMaxDateValidator?> {
                return arrayOfNulls(size)
            }
        }
        override fun isValid(date: Long) = date in minDate..maxDate
        override fun writeToParcel(parcel: Parcel, flags: Int) {}
        override fun describeContents() = 0
    }
}