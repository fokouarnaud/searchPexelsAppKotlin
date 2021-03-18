package com.example.cleanpexels.ui.fragment.discover.tabLayout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanpexels.R


class PhotosLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<PhotosLoadStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {

        holder.binData(loadState,retry)

    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.load_state_layout, parent, false)
        )
    }

    class LoadStateViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun binData(loadState: LoadState, retry: () -> Unit) {
            val progress = itemView.findViewById<ProgressBar>(R.id.load_state_progress)
            val btnRetry = itemView.findViewById<Button>(R.id.load_state_retry)
            val txtErrorMessage = itemView.findViewById<TextView>(R.id.load_state_errorMessage)

            btnRetry.isVisible = loadState !is LoadState.Loading
            txtErrorMessage.isVisible = loadState !is LoadState.Loading
            progress.isVisible = loadState is LoadState.Loading

            if (loadState is LoadState.Error){
                txtErrorMessage.text = loadState.error.localizedMessage
            }

            btnRetry.setOnClickListener {
                retry.invoke()
            }
        }
    }
}