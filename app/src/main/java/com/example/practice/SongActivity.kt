package com.example.practice

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.practice.databinding.ActivitySongBinding

class SongActivity : AppCompatActivity(){
    lateinit var binding : ActivitySongBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.songDownIb.setOnClickListener{
            finish()
        }
        binding.songMiniplayerIv.setOnClickListener{
            setPlayerStatus(false)
        }
        binding.songPauseIv.setOnClickListener{
            setPlayerStatus(true)
        }
        binding.songRepeatIv.setOnClickListener {
            setSongRepeat(false)
        }
        binding.songRepeatOneIv.setOnClickListener {
            setSongRepeat(true)
        }
        if(intent.hasExtra("title") && intent.hasExtra("singer")) {
            binding.songMusicTitleTv.text = intent.getStringExtra("title")
            binding.songSingerNameTv.text = intent.getStringExtra("singer")
        }
    }

    fun setPlayerStatus(isPlaying : Boolean) {
        if(isPlaying) {
            binding.songMiniplayerIv.visibility = View.VISIBLE
            binding.songPauseIv.visibility = View.GONE
        }
        else {
            binding.songMiniplayerIv.visibility = View.GONE
            binding.songPauseIv.visibility = View.VISIBLE
        }
    }

    fun setSongRepeat(isRepeat : Boolean) {
        if(isRepeat) {
            binding.songRepeatOneIv.visibility = View.GONE
            binding.songRepeatIv.visibility = View.VISIBLE
        }
        else {
            binding.songRepeatOneIv.visibility = View.VISIBLE
            binding.songRepeatIv.visibility = View.GONE
        }
    }
}