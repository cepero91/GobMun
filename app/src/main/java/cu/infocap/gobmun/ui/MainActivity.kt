package cu.infocap.gobmun.ui

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_LOCKED_CLOSED
import androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_UNLOCKED
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import cu.infocap.gobmun.R
import cu.infocap.gobmun.base.OnItemClickListener
import cu.infocap.gobmun.databinding.ActivityMainBinding
import cu.infocap.gobmun.domain.model.Data
import cu.infocap.gobmun.util.Constants
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity(), NavController.OnDestinationChangedListener, OnItemClickListener {

    lateinit var binding: ActivityMainBinding

    lateinit var appBarConfig: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        navController = findNavController(R.id.hostFragment)
        navController.addOnDestinationChangedListener(this@MainActivity)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfig = AppBarConfiguration(
                setOf(
                        R.id.navigation_procedure,
                        R.id.navigation_service,
                        R.id.navigation_about_us,
                        R.id.navigation_covid
                ), binding.drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfig)
        binding.navigationView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean { //Setup appBarConfiguration for back arrow
        return NavigationUI.navigateUp(navController, appBarConfig)
    }

    override fun onBackPressed() {
        when { //If drawer layout is open close that on back pressed
            binding.drawerLayout.isDrawerOpen(GravityCompat.START) -> {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }
            else -> {
                super.onBackPressed() //If drawer is already in closed condition then go back
            }
        }
    }

    override fun onItemClick(item: Data?) {
        navController.navigate(R.id.navigation_detail, bundleOf(Constants.EXTRA_DATA to item))
    }

    override fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle?) {
        when (destination.id) {
            R.id.navigation_detail, R.id.navigation_about_service_detail -> {
                binding.toolbar.visibility = View.GONE
                binding.drawerLayout.setDrawerLockMode(LOCK_MODE_LOCKED_CLOSED, GravityCompat.START)
            }
            else -> {
                binding.toolbar.visibility = View.VISIBLE
                binding.drawerLayout.setDrawerLockMode(LOCK_MODE_UNLOCKED, GravityCompat.START)
            }
        }
    }
}