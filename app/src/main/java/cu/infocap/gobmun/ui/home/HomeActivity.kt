package cu.infocap.gobmun.ui.home

import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import cu.infocap.gobmun.R
import cu.infocap.gobmun.base.BaseItem
import cu.infocap.gobmun.base.OnItemClickListener
import cu.infocap.gobmun.domain.model.Data
import cu.infocap.gobmun.ui.gprocedure.GProcedureFragment
import cu.infocap.gobmun.ui.gprocedure.item.GProcedureItem
import cu.infocap.gobmun.ui.gservice.GServiceFragment
import cu.infocap.gobmun.ui.gservice.item.GServiceItem
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        toolbar.title = "Trámites"
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        setupFragment()
    }

    private fun setupFragment() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fl_container, GProcedureFragment.newInstance(1))
                .commit()
        nav_view.menu.findItem(R.id.nav_camera).isChecked = true
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_container, GProcedureFragment.newInstance(1)).commit()
                toolbar.title = "Trámites"
            }
            R.id.nav_gallery -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_container, GServiceFragment.newInstance(1)).commit()
                toolbar.title = "Servicios"
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onItemClick(item: Data?) {
    }
}
