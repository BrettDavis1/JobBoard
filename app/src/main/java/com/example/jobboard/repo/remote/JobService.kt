package com.example.jobboard.repo.remote

import com.example.jobboard.model.Job
import retrofit2.http.GET
import retrofit2.http.Query

interface JobService {
    @GET("positions.json?description=api")
    suspend fun getJobs(@Query("description") description : String) : List<Job>
}