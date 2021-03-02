package studio.lifehack.test.ui.company

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import com.gee12.peopledates.CompanyRecyclerViewAdapter
import studio.lifehack.test.R
import studio.lifehack.test.model.Company
import studio.lifehack.test.ui.BaseFragment
import studio.lifehack.test.utils.ErrorType
import javax.inject.Inject

class CompaniesFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: CompanyViewModel

    private lateinit var recyclerAdapter: CompanyRecyclerViewAdapter

    private var recycler: RecyclerView? = null

    @Inject
    lateinit var imageLoader: ImageLoader


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_companies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)

        viewModel.companiesList.observe(viewLifecycleOwner, Observer { updateAdapter(it) })
        viewModel.error.observe(viewLifecycleOwner, Observer { showError(it) })
        viewModel.loadingState.observe(viewLifecycleOwner, Observer { showLoading(it) })

        viewModel.loadCompanies()
    }

    private fun initViews(view: View) {
        progressBar = view.findViewById(R.id.progress_circle)

        recyclerAdapter = CompanyRecyclerViewAdapter(imageLoader) {
            id -> findNavController().navigate(CompaniesFragmentDirections.showDetailsFragment(id))
        }
        recycler = (view.findViewById(R.id.recycler_view_persons) as RecyclerView?)?.apply {
            adapter = recyclerAdapter
            setHasFixedSize(true)
        }
    }

    private fun updateAdapter(persons: List<Company>) {
        recyclerAdapter.submitList(persons)
    }

    override fun showError(error: ErrorType) {
        val message = when(error) {
            ErrorType.FailureResponse -> getString(R.string.error_failure_list)
            else -> getString(R.string.error_server_response)
        }
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

}