package org.greenbyme.angelhack.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.internal.NavigationMenuItemView
import kotlinx.android.synthetic.main.activity_main.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.ui.mission.MissionFragment

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        menu_bottom_navi.setOnNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_bottom_home -> {
                supportFragmentManager.beginTransaction().add(R.id.frame_main_frag,MissionFragment.newInstance("",""))
            }
            R.id.menu_bottom_mission -> {
            }
            R.id.menu_bottom_timeline -> {
            }
            R.id.menu_bottom_my -> {

            }
        }
        return true
    }
}
