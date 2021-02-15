package com.example.jobboard.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.jobboard.databinding.FragmentJobsBinding
import com.example.jobboard.databinding.JobItemBinding
import com.example.jobboard.model.Job
import com.example.jobboard.viewmodel.JobItemViewModel
import com.example.jobboard.viewmodel.JobsFragmentViewModel

class JobItem : Fragment() {
    private lateinit var binding: JobItemBinding
    private val viewModel by viewModels<JobItemViewModel>()
    private lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = arguments?.getParcelable(JOB_KEY)!!
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ) = JobItemBinding.inflate(inflater, container, false)
            .also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.tvDisplay.text = msg
    }
    companion object {
        private const val JOB_KEY = "JOBS_KEY"
        @JvmStatic
        fun newInstance(job: Job) = JobsFragment()
                .apply {
                    arguments = Bundle().apply {
                        putParcelable(JOB_KEY, job)
                    }
                }

    }
}