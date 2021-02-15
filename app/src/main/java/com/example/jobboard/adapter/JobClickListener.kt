package com.example.jobboard.adapter

import com.example.jobboard.model.Job

interface JobClickListener {
    fun itemClicked(job: Job)
}