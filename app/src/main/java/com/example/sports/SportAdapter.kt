package com.example.sports

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.example.sports.databinding.ItemSportBinding

class SportAdapter() : RecyclerView.Adapter<SportAdapter.ViewHolder>() {
    private lateinit var context: Context
    private val sports = mutableListOf<Sport>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_sport, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int = sports.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sport = sports[position]
        with(holder){
            binding.tvName.text = sport.name
            Glide.with(context)
                .asBitmap()
                .load(sport.imgURL)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(object: CustomTarget<Bitmap>(1280, 720){
                    override fun onResourceReady(resource: Bitmap, transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?) {
                        binding.progressBar.visibility = View.GONE
                        binding.imgPhoto.scaleType = ImageView.ScaleType.CENTER_CROP
                        binding.imgPhoto.setImageBitmap(resource)
                    }

                    override fun onLoadStarted(placeholder: Drawable?) {
                        super.onLoadStarted(placeholder)
                        binding.imgPhoto.setImageResource(R.drawable.ic_loading)
                    }

                    override fun onLoadFailed(errorDrawable: Drawable?) {
                        super.onLoadFailed(errorDrawable)
                        binding.progressBar.visibility = View.GONE
                        binding.imgPhoto.setImageResource(R.drawable.ic_error)
                    }
                    override fun onLoadCleared(placeholder: Drawable?) {}
                })
        }
    }

    fun add(sport: Sport) {
        if(!sports.contains(sport)){
            sports.add(sport)
            notifyItemInserted(sports.size-1)
        }
    }

    inner class ViewHolder (view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemSportBinding.bind(view)
    }

}