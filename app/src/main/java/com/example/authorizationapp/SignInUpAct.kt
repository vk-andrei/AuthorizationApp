package com.example.authorizationapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.authorizationapp.databinding.SignUpInBinding

class SignInUpAct : AppCompatActivity() {

    private lateinit var bindingClass: SignUpInBinding
    private var signState = "empty"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = SignUpInBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        signState = intent.getStringExtra(Constants.KEY_FOR_SIGN).toString()

        bindingClass.btnAvatar.setOnClickListener {
            bindingClass.ivAvatarSignIn.setImageResource(R.drawable.man_01)
            bindingClass.ivAvatarSignIn.visibility = View.VISIBLE
        }

        if (signState == Constants.KEY_SIGN_UP) {
            bindingClass.etFirstName.visibility = View.VISIBLE
            bindingClass.etLastName.visibility = View.VISIBLE
        }

        bindingClass.btnDone.setOnClickListener {
            if (signState == Constants.KEY_SIGN_UP) {
                intent.putExtra(Constants.KEY_LOGIN, bindingClass.etLogin.text.toString())
                intent.putExtra(Constants.KEY_PW, bindingClass.etPassword.text.toString())
                intent.putExtra(Constants.KEY_FIRST_NAME, bindingClass.etFirstName.text.toString())
                intent.putExtra(Constants.KEY_LAST_NAME, bindingClass.etLastName.text.toString())
                if (bindingClass.ivAvatarSignIn.isVisible) intent.putExtra(
                    Constants.KEY_AVATAR_ID,
                    R.drawable.man_03
                )
                setResult(RESULT_OK, intent)
                finish()
            } else {
                intent.putExtra(Constants.KEY_LOGIN, bindingClass.etLogin.text.toString())
                intent.putExtra(Constants.KEY_PW, bindingClass.etPassword.text.toString())
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }
}