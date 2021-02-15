package com.example.jobboard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobboard.model.Job
import com.example.jobboard.repo.JobRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _jobs = MutableLiveData<List<Job>>()
    private val mutableSelectedJob = MutableLiveData<Job>()
    val selectedJob: LiveData<Job> get() = mutableSelectedJob

    val jobs: LiveData<List<Job>> get() = _jobs

    fun selectJob(job: Job) {
        mutableSelectedJob.value = job
    }

    fun getJobs(description: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val jobs = JobRepo.jobService.getJobs(description)
            _jobs.postValue(jobs)
        }
    }
}