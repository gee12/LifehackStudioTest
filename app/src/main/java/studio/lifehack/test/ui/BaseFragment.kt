package studio.lifehack.test.ui

import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import dagger.android.support.DaggerFragment
import studio.lifehack.test.utils.ErrorType

open class BaseFragment : DaggerFragment() {

    protected var progressBar: ProgressBar? = null

    protected open fun showError(error: ErrorType) {
        Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show()
    }

    protected open fun showLoading(loading: Boolean) {
        progressBar?.isVisible = loading
    }
}