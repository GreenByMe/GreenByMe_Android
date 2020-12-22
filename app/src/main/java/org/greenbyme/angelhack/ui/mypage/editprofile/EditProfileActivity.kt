package org.greenbyme.angelhack.ui.mypage.editprofile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_edit_profile.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.databinding.ActivityEditProfileBinding
import org.greenbyme.angelhack.ui.BaseActivity

class EditProfileActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityEditProfileBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_edit_profile)
        val mViewModel = EditProfileViewModel(EditProfileRepository(this))

        initView()
        mViewModel.nickname.observe(this@EditProfileActivity, Observer {
            mViewModel.checkNickname(it)
        })
        mViewModel.isCheckedNickname.observe(this@EditProfileActivity, Observer {
            updateNicknameCaption(it)
        })
        mViewModel.isFinished.observe(this@EditProfileActivity, Observer {
            finish()
        })

        binding.editprofileVm = mViewModel
        binding.lifecycleOwner = this
    }

    private fun initView() {
        val nickname = intent.getStringExtra(PARAMS_NICKNAME)
        val profile = intent.getStringExtra(PARAMS_PROFILE_IMG)
        supportActionBar?.title = "프로필설정"
        et_mypage_edit_nickname.hint = nickname
        Glide.with(this).load(profile).into(img_mypage_edit_profile)
    }

    private fun updateNicknameCaption(it: Boolean?) {

        Log.d("Eidtuimodle", it.toString())
        val isCheckedNickname = it ?: false
        btn_mypage_edit_save.isEnabled = isCheckedNickname
        if (isCheckedNickname) {
            tv_mypage_edit_caption.text = "사용가능한 닉네임 입니다."
            tv_mypage_edit_caption.setTextColor(resources.getColor(R.color.sub_green))
        } else {
            tv_mypage_edit_caption.text = "사용 불가능한 닉네임 입니다."
            tv_mypage_edit_caption.setTextColor(resources.getColor(R.color.red))
        }
    }

    companion object {
        const val PARAMS_NICKNAME = "nickname"
        const val PARAMS_PROFILE_IMG = "profile"

        fun getIntent(context: Context, nickname: String, profileImg: String): Intent {
            return Intent(context, EditProfileActivity::class.java).apply {
                putExtra(PARAMS_NICKNAME, nickname)
                putExtra(PARAMS_PROFILE_IMG, profileImg)
            }
        }

    }
}