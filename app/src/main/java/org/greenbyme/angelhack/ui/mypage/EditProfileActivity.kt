package org.greenbyme.angelhack.ui.mypage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_edit_profile.*
import org.greenbyme.angelhack.R

class EditProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        actionBar?.title = "프로필설정"

        val nickname = intent.getStringExtra("nickname")
        val profile = intent.getStringExtra("profile")
        et_mypage_edit_nickname.hint = nickname
        Picasso.get().load(profile).into(img_mypage_edit_profile)

        img_mypage_edit_add.setOnClickListener {

        }
    }
}