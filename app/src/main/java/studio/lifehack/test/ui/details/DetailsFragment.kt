package studio.lifehack.test.ui.details

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.HtmlCompat
import androidx.navigation.fragment.navArgs
import coil.ImageLoader
import coil.load
import studio.lifehack.test.R
import studio.lifehack.test.model.Company
import studio.lifehack.test.ui.BaseFragment
import studio.lifehack.test.utils.ErrorType
import javax.inject.Inject

class DetailsFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: DetailsViewModel

    @Inject
    lateinit var imageLoader: ImageLoader

    private val params by navArgs<DetailsFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = params.companyId
        if (id < 0) {
            Toast.makeText(requireContext(), getString(R.string.error_open_company), Toast.LENGTH_LONG).show()
            parentFragmentManager.popBackStack()
            return
        }

        viewModel.company.observe(viewLifecycleOwner, { updateCompany(it) })
        viewModel.error.observe(viewLifecycleOwner, { showError(it) })
        viewModel.loadingState.observe(viewLifecycleOwner, { showLoading(it) })

        viewModel.loadCompany(id)
    }

    private fun updateCompany(company: Company) {
        initViews(requireView(), company)
    }

    private fun initViews(view: View, company: Company) {
        view.findViewById<TextView>(R.id.tv_company_name).text = company.name
        company.www?.let {
            if (it.isNotBlank()) {
                view.findViewById<TextView>(R.id.tv_company_www).apply {
                    visibility = View.VISIBLE
                    text = it
                }
            }
        }
        company.phone?.let {
            if (it.isNotBlank()) {
                view.findViewById<TextView>(R.id.tv_company_phone).apply {
                    visibility = View.VISIBLE
                    text = company.phone
                }
            }
        }
        if (company.lat > 0 && company.lon > 0) {
            view.findViewById<TextView>(R.id.tv_company_location).apply {
                visibility = View.VISIBLE
                // формируем кликабельную ссылку на расположение
                isClickable = true
                movementMethod = LinkMovementMethod.getInstance()
                text = HtmlCompat.fromHtml(
                    getString(R.string.location_mask, company.lat, company.lon),
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                )
            }
        }
        view.findViewById<ImageView>(R.id.iv_company_photo).load(
            company.getFullImageUrl(),
            imageLoader
        ) {
            // параметры изображения
            crossfade(true)
            placeholder(android.R.drawable.ic_menu_gallery)
            error(android.R.drawable.ic_menu_close_clear_cancel)
        }
        view.findViewById<TextView>(R.id.tv_company_desc).text = company.desc
    }

    override fun showError(error: ErrorType) {
        val message = when(error) {
            ErrorType.EmptyList -> getString(R.string.incorrect_server_response)
            ErrorType.FailureResponse -> getString(R.string.error_failure_info)
            else -> getString(R.string.error_server_response)
        }
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

}