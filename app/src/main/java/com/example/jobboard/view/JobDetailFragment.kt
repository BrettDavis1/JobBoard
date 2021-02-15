package com.example.jobboard.view

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.jobboard.R
import com.example.jobboard.databinding.FragmentJobDetailBinding
import com.example.jobboard.model.Job
import com.example.jobboard.viewmodel.JobDetailFragmentViewModel

class JobDetailFragment : Fragment() {
    private lateinit var binding: FragmentJobDetailBinding
    private val viewModel by viewModels<JobDetailFragmentViewModel>()
    private lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = arguments?.getParcelable(JOB_KEY)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentJobDetailBinding.inflate(inflater, container, false)
        .also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // set tool bar
        binding.toolbar.root.setNavigationIcon(R.drawable.ic_back_arrow)
        binding.toolbar.root.setNavigationOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
        binding.toolbar.root.title = "Job Board"
        // set WebView
        binding.tvDescription.settings.javaScriptEnabled = true
        binding.tvDescription.loadData(job.description.toString(), "text/html; charset=utf-8", "UTF-8")
        Glide.with(this).load(job.companyLogo).into(binding.ivLogo)
        binding.tvLocation.text = job.location
        binding.tvName.text = job.company
        binding.tvTitle.text = job.title
        binding.tvUrl.text = job.url
        binding.tvUrl.movementMethod = LinkMovementMethod.getInstance()

    }
    companion object {
        private const val JOB_KEY = "JOBS_KEY"
        @JvmStatic
        fun newInstance(job: Job) = JobDetailFragment()
            .apply {
                arguments = Bundle().apply {
                    putParcelable(JOB_KEY, job)
                }
            }

    }
}