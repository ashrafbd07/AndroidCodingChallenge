package com.example.otchallenge.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.otchallenge.R
import com.example.otchallenge.model.data.Book
import androidx.recyclerview.widget.RecyclerView as RecyclerView1

class BookListAdapter(private val onClick: OnClickData<Book?>) :
    RecyclerView1.Adapter<BookListAdapter.ViewHolder>() {

    private var dataSet = emptyList<Book>()

    class ViewHolder(itemView: View, val onClick: OnClickData<Book?>) :
        RecyclerView1.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.book_Image)
        private val title: TextView = itemView.findViewById(R.id.book_title)
        private val description: TextView = itemView.findViewById(R.id.book_description)
        private var bookData: Book? = null

        init {
            itemView.setOnClickListener {
                bookData?.let {
                    onClick.onClick(it)
                }
            }
        }

        fun bind(data: Book) {
            bookData = data
            title.text = data.title
            description.text = data.description

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.no_image_placeholder)

            Glide.with(image.context)
                .load(data.bookImage)
                .apply(requestOptions)
                .into(image)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.book_item, viewGroup, false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun setDataset(bookList: List<Book>) {
        dataSet = bookList
        notifyDataSetChanged()
    }
}

interface OnClickData<T> {
    fun onClick(data: T)
}