package com.example.jobboard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobboard.model.Job
import com.example.jobboard.repo.JobRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JobDetailFragmentViewModel : ViewModel() {
    private val _jobs = MutableLiveData<List<Job>>()

    val jobs: LiveData<List<Job>> get() = _jobs

}