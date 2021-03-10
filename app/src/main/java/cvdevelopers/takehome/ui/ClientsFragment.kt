package cvdevelopers.takehome.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import cvdevelopers.githubstalker.databinding.ClientsFragmentBinding
import cvdevelopers.takehome.ClientsViewModel
import cvdevelopers.takehome.utils.Resource
import cvdevelopers.takehome.utils.toView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClientsFragment : Fragment() {

    lateinit var binding: ClientsFragmentBinding
    private val viewModel: ClientsViewModel by viewModels()
    private lateinit var adapter: ClientsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ClientsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ClientsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refresh()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun setupRecyclerView() {

        binding.rvClientsList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@ClientsFragment.adapter
        }

    }

    private fun setupObservers() {
        viewModel.clients.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data.map { it.toView() }))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    override fun onDestroyView() {
        binding.rvClientsList.adapter = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance() = ClientsFragment()
    }
}