package org.greenbyme.angelhack.ui.mypage.editprofile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.bumptech.glide.Glide
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_edit_profile.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.network.ApiService
import org.greenbyme.angelhack.ui.BaseActivity
import org.greenbyme.angelhack.ui.home.model.ResponseBase

class EditProfileActivity : BaseActivity() {
    var isCheckedNickname = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        initView()
    }

    private fun initView() {
        val nickname = intent.getStringExtra(PARAMS_NICKNAME)
        val profile = intent.getStringExtra(PARAMS_PROFILE_IMG)

        supportActionBar?.title = "프로필설정"

        et_mypage_edit_nickname.hint = nickname
        Glide.with(this).load(profile).into(img_mypage_edit_profile)

        img_mypage_edit_add.setOnClickListener {
            //TODO:사진선택
        }

        btn_mypage_edit_save.setOnClickListener {
            if (isCheckedNickname) {
                putNickname(et_mypage_edit_nickname.text.toString())
                finish()
            }
        }

        et_mypage_edit_nickname.addTextChangedListener {
            checkNickname(it.toString())
        }
    }

    private fun putNickname(nickname: String) =
        ApiService.mypageAPI.updateNickname(getToken(),nickname)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()

    private fun checkNickname(nickname: String) =
        ApiService.mypageAPI.checkNickname(nickname)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                checkNicknameApply(it)
            }, this::throwError)

    private fun checkNicknameApply(it: ResponseBase<Boolean>) {
        isCheckedNickname = it.data
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