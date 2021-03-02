package studio.lifehack.test.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import studio.lifehack.test.R

class MainActivity : BaseActivity() {

    private var navController: NavController? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.persons_container)
        // слушаем смену фрагментов
        navController?.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.companiesFragment -> {}
                R.id.detailsFragment -> {}
                else -> {}
            }
        }
        // меняем заголовок фрагментов
        NavigationUI.setupActionBarWithNavController(this, navController!!)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // здесь нужно вручную обработать команду
                navController?.popBackStack();
                return true;
            }
        }
        return super.onOptionsItemSelected(item)
    }
}