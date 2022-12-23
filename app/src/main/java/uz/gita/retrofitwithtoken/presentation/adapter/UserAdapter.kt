package uz.gita.retrofitwithtoken.presentation.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.retrofitwithtoken.data.source.remote.dto.response.BookResponse
import uz.gita.retrofitwithtoken.data.source.remote.dto.response.UserResponse
import uz.gita.retrofitwithtoken.databinding.BookItemBinding
import uz.gita.retrofitwithtoken.databinding.UserItemBinding


class UserAdapter : ListAdapter<UserResponse, UserAdapter.VH>(DiffCallBack1()) {

    inner class VH(private val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            binding.userName.text = "${ currentList[bindingAdapterPosition].firstName} ${currentList[bindingAdapterPosition].lastName }"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind()

}

class DiffCallBack1 : DiffUtil.ItemCallback<UserResponse>() {

    override fun areItemsTheSame(oldItem: UserResponse, newItem: UserResponse): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: UserResponse, newItem: UserResponse): Boolean {
        return oldItem.id == newItem.id && oldItem.firstName == newItem.firstName &&
                oldItem.lastName == newItem.lastName
    }

}