package org.greenbyme.angelhack.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.content_main.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.ui.home.HomeFragment
import org.greenbyme.angelhack.ui.mission.MissionFragment
import org.greenbyme.angelhack.ui.mypage.MyPageFragment


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        menu_bottom_navi.setOnNavigationItemSelectedListener(this)
        setFragment(HomeFragment())
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_bottom_home -> {
                setFragment(HomeFragment())
            }
            R.id.menu_bottom_mission -> {
                setFragment(MissionFragment.newInstance("mission"))
//                setFragment(MissionDetailFragment.newInstance("", ""))
            }
            R.id.menu_bottom_timeline -> {
            }
            R.id.menu_bottom_my -> {
                setFragment(MyPageFragment.newInstance("missi",""))
            }
        }
        return true
    }

    fun setFragment(frag: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_main_frag, frag)
            .commit()
    }

    fun addFragment(frag: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_main_frag, frag)
            .commit()
    }

}
