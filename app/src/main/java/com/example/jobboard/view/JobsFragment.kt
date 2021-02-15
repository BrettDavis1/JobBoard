package com.example.jobboard.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jobboard.R
import com.example.jobboard.adapter.JobAdapter
import com.example.jobboard.adapter.JobClickListener
import com.example.jobboard.databinding.FragmentJobsBinding
import com.example.jobboard.model.Job
import com.example.jobboard.viewmodel.JobsFragmentViewModel
import com.example.jobboard.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class JobsFragment : Fragment() {
    private lateinit var binding: FragmentJobsBinding
    private val viewModel by viewModels<JobsFragmentViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentJobsBinding.inflate(inflater, container, false)
        .also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.root.title = "Job Board"
        binding.btnFetch.setOnClickListener(View.OnClickListener {
            viewModel.getJobs(binding.etDescription.text.toString())
        })
        val listener: JobClickListener = object : JobClickListener {
            override fun itemClicked(job: Job) {
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.container, JobDetailFragment.newInstance(job), "JobDetailFragment")
                    ?.addToBackStack(null)
                    ?.commit()
            }
        }
        val linearLayout = LinearLayoutManager(this.context)
        viewModel.jobs.observe(this.viewLifecycleOwner, Observer {
            binding.rvJobs.layoutManager = linearLayout
            binding.rvJobs.adapter = JobAdapter(it, listener)
        })
    }
    companion object {
        private const val MSG_KEY = "MSG_KEY"
        @JvmStatic
        fun newInstance() = JobsFragment().apply {
            arguments = Bundle().apply { putString(MSG_KEY, "msg") }
        }
    }
}