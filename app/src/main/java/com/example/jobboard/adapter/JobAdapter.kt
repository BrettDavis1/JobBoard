package com.example.jobboard.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jobboard.databinding.JobItemBinding
import com.example.jobboard.model.Job
import java.net.URI

class JobAdapter(val jobs: List<Job>, private val listener: JobClickListener):
    RecyclerView.Adapter<JobAdapter.JobViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val binding: JobItemBinding = JobItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
        )
        return JobViewHolder(binding, listener)
    }

    override fun getItemCount(): Int {
        return jobs.size
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        holder.setJob(jobs[position], listener)
    }

    class JobViewHolder(val binding: JobItemBinding, val listener: JobClickListener):RecyclerView.ViewHolder(binding.root) {
        fun setJob(job: Job, listener: JobClickListener) {
            binding.root.setOnClickListener(View.OnClickListener {
                listener.itemClicked(job)
            })
            binding.tvTitle.text = job.title
            binding.tvName.text = job.company
            binding.tvLocation.text = job.location
            Glide.with(binding.root.context).load(job.companyLogo).into(binding.ivLogo)
        }
    }
}