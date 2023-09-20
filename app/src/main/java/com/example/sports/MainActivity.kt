package com.example.sports

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sports.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), OnClickListener{
    private lateinit var binding: ActivityMainBinding
    private lateinit var listAdapter: SportListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        getAllSports()
    }
    private fun setupRecyclerView(){
        listAdapter = SportListAdapter(this)
        binding.recyclerView.apply{
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = listAdapter
        }
    }
    private fun sports(): MutableList<Sport>{
        val futbolSport = Sport(1, "Futbol", "https://upload.wikimedia.org/wikipedia/commons/thumb/7/74/Football_%28Soccer%29.JPG/1024px-Football_%28Soccer%29.JPG")
        val baloncestoSport = Sport(2, "Baloncesto", "https://garrampa.es/blog/wp-content/uploads/2022/12/blog-1-a-que-altura-esta-la-canasta-de-baloncesto-768x432-1.jpg")
        val balonmanoSport = Sport(3, "Balmonmano", "https://s1.significados.com/foto/imagen-de-destaque-que-es-el-balonmano.jpg")
        val volleyballSport = Sport(4, "Volley Ball", "https://media.istockphoto.com/id/476695348/es/foto/beachvolleyball-reproductor-net.jpg?s=612x612&w=0&k=20&c=eZkkypFpOTQ7oIQA3MCmO-NzGrI2dUVKjnO3-qqJntc=")
        val tenisSport = Sport(5, "Tenis", "https://s3.abcstatics.com/media/bienestar/2019/08/02/tenis-abecedario-kgNF--1248x698@abc.jpg")
        val rugbySport = Sport(6, "Rugby", "https://nwscdn.com/media/catalog/product/d/o/dominate-rugby-ball_7.jpg")
        val golfSport = Sport(7, "Golf", "https://www.realia.es/uploads/sizes/blog/blog/2020/jugar-a-golf.jpg")
        val boxeoSport = Sport(8, "Boxeo", "https://s1.eestatic.com/2023/03/30/actualidad/752435605_232074095_1706x960.jpg")
        val hockeySport = Sport(9, "Hockey", "https://patoruzurc.com.ar/wp-content/uploads/2016/02/Hockey-300x202.jpg")
        val f1Sport = Sport (10, "Formula 1", "https://cdn-4.motorsport.com/images/amp/0Zrwxq50/s6/f1-brazilian-gp-2008-kimi-raik.jpg")
        val eSport = Sport (11, "E-Sport", "https://www.riotgames.com/darkroom/1440/a4e88f6b04bf83f1c417e87292b85606:b62ff9b783f3dd26a6321e1440efe13b/riot-games-the-team-behind-worlds-2022-esports-broadcast-league-of-legends.png")
        return mutableListOf(futbolSport, baloncestoSport, balonmanoSport, volleyballSport, tenisSport, rugbySport, golfSport, boxeoSport, hockeySport, f1Sport, eSport)
    }
    private fun getAllSports(){
        val sportsData = sports()
        listAdapter.submitList(sportsData)
    }
    override fun onClick(sport: Sport) {
        Snackbar.make(binding.root, sport.name, Snackbar.LENGTH_SHORT).show()
    }
}