package com.airmee.apartments.ui.presenters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.airmee.apartments.R
import com.airmee.apartments.ui.ApartmentListAdapter
import com.airmee.apartments.ui.viewmodel.ApartmentListViewModel
import kotlinx.android.synthetic.main.fragment_apartment_list.*
import org.kodein.di.generic.instance

class ApartmentListFragment : KodeinFragment() {

    private val viewModel: ApartmentListViewModel by instance()
    private val listAdapter: ApartmentListAdapter by instance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_apartment_list,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context = checkNotNull(context)
        viewModel.fetchApartmentList()

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context).apply {
                val divider = DividerItemDecoration(
                    recyclerView.context,
                    orientation
                )
                addItemDecoration(divider)
            }
            adapter = listAdapter
            setHasFixedSize(true)
        }

        listAdapter.setOnClickListener {
            viewModel.applyBooking(it.id)
            Toast.makeText(context, "Booked!", Toast.LENGTH_SHORT).show();
        }

        with(viewModel) { uiState.observe(this@ApartmentListFragment, Observer(::updateUi)) }
    }

    private fun updateUi(model: ApartmentListViewModel.UIStateModel) {
        progressBar.visibility =
            if (model is ApartmentListViewModel.UIStateModel.Loading) View.VISIBLE else View.GONE
        emptyMessage.visibility =
            if (model is ApartmentListViewModel.UIStateModel.Empty) View.VISIBLE else View.GONE
        when (model) {
            is ApartmentListViewModel.UIStateModel.Content -> {
                val apartments = model.apartments
                listAdapter.apartments = apartments
            }
        }
    }
}