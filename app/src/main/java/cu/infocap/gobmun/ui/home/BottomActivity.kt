package cu.infocap.gobmun.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomnavigation.BottomNavigationView
import cu.infocap.gobmun.R
import cu.infocap.gobmun.base.OnItemClickListener
import cu.infocap.gobmun.databinding.ActivityBottomBinding
import cu.infocap.gobmun.domain.model.Data
import cu.infocap.gobmun.ui.aboutus.AboutUsFragment
import cu.infocap.gobmun.ui.detail.DetailActivity
import cu.infocap.gobmun.ui.gprocedure.GProcedureFragment
import cu.infocap.gobmun.ui.gservice.GServiceFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_bottom.*

class BottomActivity : DaggerAppCompatActivity(), OnItemClickListener {

    override fun onItemClick(item: Data?) {
        val mIntent = Intent(this@BottomActivity, DetailActivity::class.java)
        mIntent.putExtra("data", item)
        startActivity(mIntent)
    }

    private lateinit var binding: ActivityBottomBinding

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_container, GProcedureFragment.newInstance(1)).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_container, GServiceFragment.newInstance(1)).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_about_us -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_container, AboutUsFragment()).commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bottom)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        supportFragmentManager.beginTransaction()
                .replace(R.id.fl_container, GProcedureFragment.newInstance(1)).commit()
    }
}
