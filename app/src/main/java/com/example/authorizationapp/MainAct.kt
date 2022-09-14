package com.example.authorizationapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.authorizationapp.databinding.ActivityMainBinding

class MainAct : AppCompatActivity() {

    private lateinit var bindingMainAct: ActivityMainBinding
    private var login: String = "empty"
    private var password: String = "empty"
    private var firstName: String = "empty"
    private var lastName: String = "empty"
    private var imageAvatarId: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMainAct = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMainAct.root)

        bindingMainAct.btnSignIn.setOnClickListener {
            runSecondActivity(Constants.KEY_SIGN_IN, Constants.KEY_REQUEST_CODE_SIGN_IN)
        }
        bindingMainAct.btnSignUp.setOnClickListener {
            runSecondActivity(Constants.KEY_SIGN_UP, Constants.KEY_REQUEST_CODE_SIGN_UP)
        }
        bindingMainAct.btnExit.setOnClickListener {
            bindingMainAct.btnSignUp.visibility = View.VISIBLE
            bindingMainAct.btnSignIn.visibility = View.VISIBLE
            bindingMainAct.btnExit.visibility = View.INVISIBLE
            bindingMainAct.ivMain.visibility = View.INVISIBLE
            bindingMainAct.tvTextHello.text = ""
        }
    }


    private fun runSecondActivity(keyForSign: String, requestCode: Int) {
        val i = Intent(this, SignInUpAct::class.java)
        i.putExtra(Constants.KEY_FOR_SIGN, keyForSign)
        startActivityForResult(i, requestCode)
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Constants.KEY_REQUEST_CODE_SIGN_IN && data != null && resultCode == RESULT_OK) {
            val l = data.getStringExtra(Constants.KEY_LOGIN)
            val pw = data.getStringExtra(Constants.KEY_PW)
            if (l == login && pw == password) {
                val textHello = "HELLO, $firstName $lastName!!!"
                bindingMainAct.tvTextHello.text = textHello
                bindingMainAct.ivMain.setImageResource(imageAvatarId)
                bindingMainAct.ivMain.visibility = View.VISIBLE

                bindingMainAct.btnSignUp.visibility = View.INVISIBLE
                bindingMainAct.btnSignIn.visibility = View.INVISIBLE
                bindingMainAct.btnExit.visibility = View.VISIBLE


            } else {
                bindingMainAct.tvTextHello.text = "WHO ARE YOU, MOTHERFUCKER????!!!"
                bindingMainAct.ivMain.setImageResource(R.drawable.girl_02)
                bindingMainAct.ivMain.visibility = View.VISIBLE
            }

        } else if (requestCode == Constants.KEY_REQUEST_CODE_SIGN_UP && data != null && resultCode == RESULT_OK) {

            login = data.getStringExtra(Constants.KEY_LOGIN).toString()
            password = data.getStringExtra(Constants.KEY_PW).toString()
            firstName = data.getStringExtra(Constants.KEY_FIRST_NAME).toString()
            lastName = data.getStringExtra(Constants.KEY_LAST_NAME).toString()
            imageAvatarId = data.getIntExtra(Constants.KEY_AVATAR_ID, R.drawable.man_01)

            val textHello = "HELLO, $firstName $lastName!!!"
            bindingMainAct.tvTextHello.text = textHello
            bindingMainAct.ivMain.setImageResource(imageAvatarId)
            bindingMainAct.ivMain.visibility = View.VISIBLE
        }
    }

}