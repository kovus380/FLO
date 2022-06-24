package com.example.practice.ui.main.locker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practice.data.local.SongDatabase
import com.example.practice.data.entities.Song
import com.example.practice.databinding.FragmentStoredBinding

class StoredSongFragment : Fragment() {
    lateinit var binding: FragmentStoredBinding
    private var songDatas = ArrayList<Song>()
    lateinit var songDB: SongDatabase


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoredBinding.inflate(inflater, container, false)
        songDB = SongDatabase.getInstance(requireContext())!!
//        songDatas.apply {
//            add(Song("LILAC", "아이유 (IU)", 0, 215, false, "music_lilac", R.drawable.img_album_exp2))
//            add(Song("Butter", "방탄소년단 (BTS)", 0, 215, false, "music_lilac", R.drawable.img_album_exp))
//            add(Song("Next Level", "에스파 (AESPA)", 0, 215, false, "music_lilac", R.drawable.img_album_exp3))
//            add(Song("아이와 나의 바다", "아이유(IU)", 0, 215, false, "music_lilac", R.drawable.img_album_exp2))
//            add(Song("작은 것들을 위한 시", "방탄소년단 (BTS)", 0, 215, false, "music_lilac", R.drawable.img_album_exp4))
//            add(Song("Weekend", "태연 (TAEYEON)", 0, 215, false, "music_lilac", R.drawable.img_album_exp6))
//            add(Song("에필로그", "아이유 (IU)", 0, 215, false, "music_lilac", R.drawable.img_album_exp2))
//            }

//        val songRVAdapter = SongRVAdapter(songDatas)
//        binding.lockerStoredSongRv.adapter = songRVAdapter
//        binding.lockerStoredSongRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initRecyclerView()
    }

    private fun initRecyclerView(){
        binding.lockerStoredSongRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val songRVAdapter = StoredSongRVAdapter()

        songRVAdapter.setMyItemClickListener(object : StoredSongRVAdapter.MyItemClickListener {
            override fun onRemoveSong(songId: Int) {
                songDB.songDao().updateIsLikeById(false, songId)
            }

        })
        binding.lockerStoredSongRv.adapter = songRVAdapter
        songRVAdapter.addSongs(songDB.songDao().getLikeSongs(true) as ArrayList<Song>)
    }
}