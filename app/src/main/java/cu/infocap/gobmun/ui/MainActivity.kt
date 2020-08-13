package cu.infocap.gobmun.ui

import android.content.Intent
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import cu.infocap.gobmun.R
import cu.infocap.gobmun.base.OnItemClickListener
import cu.infocap.gobmun.databinding.ActivityMainBinding
import cu.infocap.gobmun.domain.model.Data
import cu.infocap.gobmun.ui.detail.DetailActivity
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity(), OnItemClickListener {

    lateinit var binding: ActivityMainBinding

    lateinit var appBarConfig: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        navController = findNavController(R.id.hostFragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfig = AppBarConfiguration(
                setOf(
                        R.id.navigation_procedure,
                        R.id.navigation_service,
                        R.id.navigation_about_us
                ), binding.drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfig)
        binding.navigationView.setupWithNavController(navController)
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
        val mIntent = Intent(this@MainActivity, DetailActivity::class.java)
        mIntent.putExtra("data", item)
        startActivity(mIntent)
    }
}