package org.greenbyme.angelhack.ui

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.content_main.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.ui.certification.CertificationFragment
import org.greenbyme.angelhack.ui.home.HomeFragment
import org.greenbyme.angelhack.ui.mission.MissionFragment
import org.greenbyme.angelhack.ui.mypage.MyPageFragment


class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private var backTime: Long = 0

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            if (System.currentTimeMillis() - backTime < 2000) {
                finish()
            }
            Toast.makeText(this, "종료하시려면 다시한번 눌러주세요.", Toast.LENGTH_SHORT).show()
            backTime = System.currentTimeMillis()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        menu_bottom_navi.setOnNavigationItemSelectedListener(this)
        setFragment(HomeFragment())
        userId = intent.getIntExtra("id", 0)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_bottom_home -> {
                setFragment(HomeFragment())
            }
            R.id.menu_bottom_mission -> {
                setFragment(MissionFragment.newInstance(0))
            }
            R.id.menu_bottom_certification -> {
                setFragment(CertificationFragment())
            }
            R.id.menu_bottom_my -> {
                setFragment(MyPageFragment.newInstance(userId))
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
            .addToBackStack(null)
            .replace(R.id.frame_main_frag, frag)
            .commit()
    }

    companion object {
        var userId: Int = 0
    }
}
