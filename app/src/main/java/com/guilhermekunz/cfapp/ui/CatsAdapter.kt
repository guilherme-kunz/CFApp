package com.guilhermekunz.cfapp.ui


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.guilhermekunz.cfapp.api.response.FactsResponseItem
import com.guilhermekunz.cfapp.databinding.CatsItemBinding

class CatsAdapter() : RecyclerView.Adapter<CatsAdapter.ViewHolder>() {

    private val response = arrayListOf<FactsResponseItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CatsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: CatsAdapter.ViewHolder, position: Int) {
        holder.bind(response[position])
    }

    override fun getItemCount(): Int = response.size

    fun append(response: FactsResponseItem?) {
        this.response.clear()
        this.response.addAll(response)
        notifyDataSetChanged()
    }

    inner class ViewHolder(binding: CatsItemBinding): RecyclerView.ViewHolder(binding.root) {
        private val tvText = binding.text

        fun bind(responseItem: FactsResponseItem) {
            tvText.text = responseItem.text
        }
    }

}