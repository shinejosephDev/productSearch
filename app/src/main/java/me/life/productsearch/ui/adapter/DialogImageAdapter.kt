package me.life.productsearch.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.life.productsearch.databinding.ListItemDialogImagesBinding


class DialogImageAdapter(
    val resultLauncher: ActivityResultLauncher<Intent>,
    val list: List<String>,
    val isLimit: Boolean
) :
    RecyclerView.Adapter<DialogImageAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ListItemDialogImagesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.imageAdd.visibility = View.GONE
            binding.image.visibility = View.VISIBLE
            Glide.with(binding.image).load(item).into(binding.image)
        }

        fun showAddNew(resultLauncher: ActivityResultLauncher<Intent>) {
            binding.imageAdd.visibility = View.VISIBLE
            binding.image.visibility = View.GONE

            binding.imageAdd.setOnClickListener {
                val photoPickerIntent = Intent(Intent.ACTION_PICK)
                photoPickerIntent.type = "image/*"
                resultLauncher.launch(photoPickerIntent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemDialogImagesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (isLimit) {
            if (list.size == 2)
                holder.bind(list[position])
            else
                if (position == itemCount - 1)
                    holder.showAddNew(resultLauncher)
                else
                    holder.bind(list[position])
        }else{
            if (position == itemCount - 1)
                holder.showAddNew(resultLauncher)
            else
                holder.bind(list[position])
        }
    }

    override fun getItemCount(): Int {
        if (list.size == 2 && isLimit) {
            return 2
        } else
            return list.size + 1
    }
}