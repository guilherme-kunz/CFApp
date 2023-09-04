package com.guilhermekunz.cfapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.guilhermekunz.cfapp.api.response.FactsResponse
import com.guilhermekunz.cfapp.api.response.FactsResponseItem
import com.guilhermekunz.cfapp.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModel<CatsViewModel>()
    private val catsAdapter by lazy { CatsAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getCatsFacts()
        observer()
    }

    private fun observer() {
        viewModel.catsFactsResponse.observe(this, Observer {
            setAdapter(it)
        })
        viewModel.loadingStateLiveData.observe(this, Observer {
            setProgressBar(it)
        })
    }

    private fun setProgressBar(state: CatsViewModel.State?) {
        when (state) {
            CatsViewModel.State.LOADING -> binding.progressBar.visibility = View.VISIBLE
            CatsViewModel.State.LOADING_FINISH -> binding.progressBar.visibility = View.GONE
        }
    }

    private fun setAdapter(response: FactsResponse?) {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = catsAdapter
            catsAdapter.append(response)
        }
    }
}