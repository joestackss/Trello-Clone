package com.olamide.trelloclone.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.olamide.trelloclone.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {

    private var myBinding : ActivityIntroBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myBinding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(myBinding?.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        myBinding?.btnSignIn?.setOnClickListener{
            //val intent = Intent (this, ActivityLoginBinding::class.java)
           // startActivity(intent)

            startActivity(Intent(this, LoginActivity::class.java))
        }


        myBinding?.btnSignUp?.setOnClickListener{
            //val intent = Intent (this, ActivityLoginBinding::class.java)
            // startActivity(intent)

            startActivity(Intent(this, SignUpActivity::class.java))
        }

    }
}