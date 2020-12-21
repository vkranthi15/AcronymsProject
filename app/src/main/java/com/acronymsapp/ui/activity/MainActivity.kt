package com.acronymsapp.ui.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.acronymsapp.R
import com.acronymsapp.repository.Repository
import com.acronymsapp.repository.remote.model.Result
import com.acronymsapp.ui.adapter.MeaningsAdapter
import com.acronymsapp.viewmodel.AcromineViewModel
import com.acronymsapp.viewmodel.AcromineViewModelFactory
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity(), Observer<Result> {

    @Inject
    lateinit var repository: Repository

    private lateinit var meaningsAdapter: MeaningsAdapter
    private lateinit var viewModel: AcromineViewModel
    private lateinit var viewModelFactory: AcromineViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidInjection.inject(this)

        val acronymEditext = findViewById<EditText>(R.id.acronym)
        val searchButton = findViewById<Button>(R.id.search)
        val recyclerView = findViewById<RecyclerView>(R.id.meaningsList)
        meaningsAdapter = MeaningsAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = meaningsAdapter

        viewModelFactory = AcromineViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(AcromineViewModel::class.java)

        searchButton.setOnClickListener {
            viewModel.fetchMeanings(acronymEditext.text.toString()).observe(this, this)

            // hide keyboard after search click
            val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    override fun onChanged(result: Result?) {
        result?.let {
            when(it) {
                is Result.Success -> {
                    val responseList = it.acromineList
                    if (responseList.isNullOrEmpty()) {
                      Toast.makeText(applicationContext, "No results for the acronym!!", Toast.LENGTH_LONG).show()
                    } else {
                        meaningsAdapter.setData(responseList[0].longForms)
                        meaningsAdapter.notifyDataSetChanged()
                    }
                }

                is Result.Error -> {
                    Toast.makeText(applicationContext, it.exception.errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}