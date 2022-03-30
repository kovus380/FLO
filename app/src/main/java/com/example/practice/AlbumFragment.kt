package com.example.practice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.practice.databinding.FragmentAlbumBinding
import com.example.prcatice.HomeFragment

class AlbumFragment : Fragment() {
    lateinit var binding : FragmentAlbumBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlbumBinding.inflate(inflater, container, false)

        binding.albumBackIv.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment()).commitAllowingStateLoss()
        }
        binding.songLilacLayout.setOnClickListener {
            Toast.makeText(activity, "라일락", Toast.LENGTH_SHORT).show()
        }
        binding.songFluLayout.setOnClickListener {
            Toast.makeText(activity, "Flu", Toast.LENGTH_SHORT).show()
        }
        binding.songCoinLayout.setOnClickListener {
            Toast.makeText(activity, "Coin", Toast.LENGTH_SHORT).show()
        }
        binding.songSpringLayout.setOnClickListener {
            Toast.makeText(activity, "봄 안녕 봄", Toast.LENGTH_SHORT).show()
        }
        binding.songCelebrityLayout.setOnClickListener {
            Toast.makeText(activity, "Celebrity", Toast.LENGTH_SHORT).show()
        }
        binding.songSingLayout.setOnClickListener {
            Toast.makeText(activity, "돌림노래(Feat. Dean)", Toast.LENGTH_SHORT).show()
        }

        binding.songMixoffTg.setOnClickListener {
            setMixStatus(false)
        }

        binding.songMixonTg.setOnClickListener {
            setMixStatus(true)
        }

        return binding.root
    }
    fun setMixStatus(isMix : Boolean) {
        if(isMix) {
            binding.songMixoffTg.visibility = View.VISIBLE
            binding.songMixonTg.visibility = View.GONE
        }
        else {
            binding.songMixoffTg.visibility = View.GONE
            binding.songMixonTg.visibility = View.VISIBLE

        }
    }
}