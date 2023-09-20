package com.example.sports

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.motion.widget.MotionScene.Transition
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.example.sports.databinding.ItemSportBinding

class SportListAdapter(private val listener: OnClickListener) :
    ListAdapter<Sport, RecyclerView.ViewHolder>(SportDiffCallBack()){
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_sport, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val sport = getItem(position)
        with(holder as ViewHolder){
            binding.tvName.text = sport.name
            Glide.with(context)
                .asBitmap()
                .load(sport.imgURL)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(object: CustomTarget<Bitmap>(1920, 720){
                    override fun onResourceReady(resource: Bitmap, transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?) {
                        binding.progressBar.visibility = View.GONE
                        binding.imgPhoto.scaleType = ImageView.ScaleType.CENTER_CROP
                        binding.imgPhoto.setImageBitmap(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {}
                })
        }
    }
    inner class ViewHolder (view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemSportBinding.bind(view)
    }

    class SportDiffCallBack: DiffUtil.ItemCallback<Sport>(){
        override fun areItemsTheSame(oldItem: Sport, newItem: Sport): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Sport, newItem: Sport): Boolean = oldItem == newItem
}
}