package com.example.jobboard.repo

import com.example.jobboard.model.Job
import com.example.jobboard.repo.remote.RetrofitInstance

object JobRepo {
    val jobService = RetrofitInstance.jobService

    suspend fun getJobs(description: String) : List<Job> {
        return RetrofitInstance.jobService.getJobs(description)
    }
}