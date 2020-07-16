package org.greenbyme.angelhack.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.ui.home.HomeFragment
import org.greenbyme.angelhack.ui.mission.MissionFragment

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        menu_bottom_navi.setOnNavigationItemSelectedListener(this)
        supportFragmentManager.beginTransaction().add(R.id.frame_main_frag, HomeFragment()).commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_bottom_home -> {
                supportFragmentManager.beginTransaction().replace(R.id.frame_main_frag, HomeFragment()).commit()
            }
            R.id.menu_bottom_mission -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_main_frag, MissionFragment.newInstance("", "")).commit()
            }
            R.id.menu_bottom_timeline -> {
            }
            R.id.menu_bottom_my -> {
            }
        }
        return true
    }
}
