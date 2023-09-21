package com.example.sports


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.sports.databinding.ActivityMainBinding
import com.example.sports.databinding.FragmentDetailBinding
import com.google.android.material.snackbar.Snackbar


class DetailFragment: Fragment(){
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var sport: Sport
    private lateinit var adapter: SportAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let{
            sport = Sport(0,
                it.getString("name", ""),
                it.getString("description", ""),
                it.getString("url", ""))
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvName2.text = sport.name
        binding.tvDescripcion.text = sport.description
        Glide.with(this)
            .load(sport.imgURL)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(binding.imgSport2)
        binding.tvTTPI.text= "También podrías estar interesado en..."
        setupRecyclerView()
        getAllSports()
    }


    private fun setupRecyclerView() {
        adapter = SportAdapter()
        binding.rvVistas.apply {
            setHasFixedSize(true)
            adapter = this@DetailFragment.adapter
        }
    }

    fun getAllSports() {
       val sportData = FakeDB.sports()
        sportData.forEach{sport ->
            adapter.add(sport)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}