package com.csci448.kennylieu.kennylieu_A2.ui.history

import android.content.Context
import android.os.Bundle
import android.preference.PreferenceFragment
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.csci448.kennylieu.kennylieu_A2.R
import com.csci448.kennylieu.kennylieu_A2.data.Result
import com.csci448.kennylieu.kennylieu_A2.data.ResultDatabase

class HistoryFragment : Fragment() {
    interface Callbacks {
        fun onGameClicked()
        fun onSettingsClicked()
        fun onHistoryBackClicked()
    }
    //TODO there are two viewmodels, fix that

    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var resultRecyclerView: RecyclerView
    private lateinit var adapter: HistoryAdapter

    private val logTag = "448.HistoryFragment"

    private var callbacks: Callbacks? = null

    private lateinit var viewModel: HistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(logTag, "onCreate() called")
        val factory = HistoryViewModelFactory(requireContext())
        historyViewModel = ViewModelProvider(this, factory).get(HistoryViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(logTag, "onCreateView() called")
        setHasOptionsMenu(true)
        val view = inflater.inflate(R.layout.history_fragment, container, false)
        resultRecyclerView = view.findViewById(R.id.history_recycler_view)
        resultRecyclerView.layoutManager = LinearLayoutManager(context)
        updateUI(emptyList())
        return view
    }

    private fun updateUI(results: List<Result>){
        adapter = HistoryAdapter(results){
            result: Result -> Unit
        }
        resultRecyclerView.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(logTag, "onViewCreated() called")
        historyViewModel.resultLiveListData.observe(
            viewLifecycleOwner,
            Observer { results -> results?.let {
                Log.i(logTag, "Got results ${results.size}")
                updateUI(results)
            }}
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d(logTag, "onActivityCreated() called")
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HistoryViewModel::class.java)
        // TODO: Use the ViewModel
    }

    companion object {
        fun newInstance() = HistoryFragment()
    }

    override fun onAttach(context: Context) {
        Log.d(logTag, "onAttach() called")
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onDetach() {
        Log.d(logTag, "onDetach() called")
        super.onDetach()
        callbacks = null
    }

    override fun onStart(){
        Log.d(logTag, "onStart() called")
        super.onStart()
    }
    override fun onResume(){
        Log.d(logTag, "onResume() called")
        super.onResume()
    }
    override fun onPause(){
        Log.d(logTag, "onPause() called")
        super.onPause()
    }
    override fun onStop(){
        Log.d(logTag, "onStop() called")
        super.onStop()
        callbacks?.onHistoryBackClicked()
    }
    override fun onDestroy(){
        Log.d(logTag, "onDestroy() called")
        super.onDestroy()
    }
    override fun onDestroyView(){
        Log.d(logTag, "onDestroyView() called")
        super.onDestroyView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        Log.d(logTag, "onCreateOptionsMenu() called")
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.history_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            /*R.id.new_result_menu_item -> {
                val result = Result()
                historyViewModel.addResult(result)
                true
            }*/
            R.id.game_menu_item -> {
                Log.d(logTag, "game clicked")
                callbacks?.onGameClicked()
                true
            }
            R.id.settings_menu_item -> {
                Log.d(logTag, "settings clicked")
                callbacks?.onSettingsClicked()
                true
            }
            R.id.exit_menu_item -> {
                Log.d(logTag, "exit clicked")
                System.exit(0)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}