package com.example.lolgraphs.ui.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.lolgraphs.R
import com.example.lolgraphs.databinding.FragmentFavoritesBinding
import com.example.lolgraphs.ui.viewModel.ChampViewModel

class FavoritesFragment : Fragment() {

    private var _binding : FragmentFavoritesBinding? = null

    private val binding get() = _binding!!

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //val favoriteViewModel =
        //    ViewModelProvider(this).get(ChampViewModel::class.java)

        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val root : View = binding.root
        binding.container.setBackgroundColor(R.color.purple_200)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}