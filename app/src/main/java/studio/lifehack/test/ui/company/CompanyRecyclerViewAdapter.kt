package com.gee12.peopledates

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import coil.ImageLoader
import coil.load
import coil.transform.CircleCropTransformation
import studio.lifehack.test.R
import studio.lifehack.test.model.Company


class CompanyRecyclerViewAdapter(
    private val imageLoader: ImageLoader,
    private val onItemClicked: (id: Int) -> Unit
) : ListAdapter<Company, CompanyRecyclerViewAdapter.ViewHolder>(PersonsCallback()) {

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(parent)
        holder.itemView.setOnClickListener {
            onItemClicked(holder.itemId.toInt())
        }
        return holder
    }

    override fun getItemId(position: Int): Long {
        return (getItem(position).id).toLong()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_company, parent, false)
    ) {
        private val nameView: TextView? = itemView.findViewById(R.id.tv_company_name)
        private val imageView: ImageView? = itemView.findViewById(R.id.iv_company_photo)

        fun bind(person: Company) {
            nameView?.text = person.name
            imageView?.load(person.getFullImageUrl(), imageLoader) {
                // параметры изображения
                crossfade(true)
                placeholder(android.R.drawable.ic_menu_gallery)
                error(android.R.drawable.ic_menu_close_clear_cancel)
                transformations(CircleCropTransformation())
            }
        }
    }
}

class PersonsCallback : DiffUtil.ItemCallback<Company>() {
    override fun areItemsTheSame(oldItem: Company, newItem: Company): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Company, newItem: Company): Boolean {
        return oldItem == newItem
    }
}