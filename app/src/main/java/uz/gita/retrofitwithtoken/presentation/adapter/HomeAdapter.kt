package uz.gita.retrofitwithtoken.presentation.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.retrofitwithtoken.R
import uz.gita.retrofitwithtoken.data.source.remote.dto.response.BookResponse
import uz.gita.retrofitwithtoken.databinding.BookItemBinding

/*
 * Arzigul Nazarbaeva
 * 12/21/2022, Wednesday, 1:58 PM
*/


class HomeAdapter : ListAdapter<BookResponse.OwnerBook, HomeAdapter.VH>(DiffCallBack()) {
    private var itemDeleteClickOnListener: ((BookResponse.OwnerBook) -> Unit)? = null
    private var itemEditClickOnListener: ((BookResponse.OwnerBook) -> Unit)? = null
    private var itemFavClickOnListener: ((BookResponse.OwnerBook) -> Unit)? = null

    fun setOnItemDeleteClickOnListener(block: (BookResponse.OwnerBook) -> Unit) {
        itemDeleteClickOnListener = block
    }

    fun setOnItemEditClickOnListener(block: (BookResponse.OwnerBook) -> Unit) {
        itemEditClickOnListener = block
    }

    fun setOnItemFavClickOnListener(block: (BookResponse.OwnerBook) -> Unit) {
        itemFavClickOnListener = block
    }

    inner class VH(private val binding: BookItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            binding.authorName.text = currentList[bindingAdapterPosition].author
            binding.bookName.text = currentList[bindingAdapterPosition].title

            if (currentList[bindingAdapterPosition].fav) {
                binding.favourite.setImageResource(R.drawable.ic_baseline_favorite)
            } else {
                binding.favourite.setImageResource(R.drawable.ic_baseline_favorite_24)
            }

        }

        init {
            binding.btnEdit.setOnClickListener {
                itemEditClickOnListener?.invoke(currentList[bindingAdapterPosition])
            }

            binding.btnDelete.setOnClickListener {
                itemDeleteClickOnListener?.invoke(currentList[bindingAdapterPosition])
            }

            binding.favourite.setOnClickListener {
                itemFavClickOnListener?.invoke(currentList[bindingAdapterPosition])
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(BookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind()

}

class DiffCallBack : DiffUtil.ItemCallback<BookResponse.OwnerBook>() {

    override fun areItemsTheSame(oldItem: BookResponse.OwnerBook, newItem: BookResponse.OwnerBook): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: BookResponse.OwnerBook, newItem: BookResponse.OwnerBook): Boolean {
        return oldItem.id == newItem.id && oldItem.title == newItem.title && oldItem.author == newItem.author &&
                oldItem.description == newItem.description && oldItem.fav == newItem.fav && oldItem.pageCount == newItem.pageCount
    }

}