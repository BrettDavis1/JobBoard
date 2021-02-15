package com.example.jobboard.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import com.example.jobboard.R
import com.example.jobboard.databinding.ActivityMainBinding
import com.example.jobboard.databinding.FragmentJobsBinding
import com.example.jobboard.model.Job
import com.example.jobboard.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        loadJobsFragment()
    }
    private fun loadJobsFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, JobsFragment.newInstance(), "JobsFragment")
            .commit()
    }
}