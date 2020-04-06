package com.airmee.apartments.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.airmee.apartments.R
import com.airmee.apartments.domain.entity.Apartment
import com.airmee.apartments.utils.diffUtil
import com.airmee.apartments.utils.formatDist
import kotlinx.android.synthetic.main.item_apartment.view.*

internal class ApartmentListAdapter :
    RecyclerView.Adapter<ApartmentListAdapter.ViewHolder>() {

    private lateinit var onClickListener: ((apartment: Apartment) -> Unit)
    var apartments: List<Apartment> by diffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_apartment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val apartment = apartments[position]
        holder.itemView.bookButton.setOnClickListener { onClickListener(apartment) }
        holder.bind(apartment)
    }

    override fun getItemCount(): Int = apartments.size

    fun setOnClickListener(listener: (apartment: Apartment) -> Unit) {
        this.onClickListener = listener
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(apartment: Apartment) {
            with(itemView) {
                title.text = apartment.name
                address.text = apartment.address
                distance.text = apartment.distanceTo.formatDist()
            }
        }
    }
}