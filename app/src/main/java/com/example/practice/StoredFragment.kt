package com.example.practice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.practice.databinding.FragmentDetailBinding
import com.example.practice.databinding.FragmentStoredBinding

class StoredFragment : Fragment() {
    lateinit var binding: FragmentStoredBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoredBinding.inflate(inflater, container, false)
        return binding.root
    }
}