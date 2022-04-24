package com.olamide.trelloclone.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.olamide.trelloclone.databinding.ActivitySplashScreenBinding
import com.olamide.trelloclone.firebase.FirestoreClass

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    var binding : ActivitySplashScreenBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        // This is used to hide the status bar and make the splash screen as a full screen activity.
        // START
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )


        Handler().postDelayed({
            // TODO (Step 2: Check if the current user id is not blank then send the user to MainActivity as he have logged in
            //  before or else send him to Intro Screen as earlier.)
            // START
            // Here if the user is signed in once and not signed out again from the app. So next time while coming into the app
            // we will redirect him to MainScreen or else to the Intro Screen as it was before.

            // Get the current user id
            val currentUserID = FirestoreClass().getCurrentUserID()
            // Start the Intro Activity

            if (currentUserID.isNotEmpty()) {

                // Start the Main Activity
                startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            } else {
                // Start the Intro Activity
                startActivity(Intent(this@SplashScreenActivity, IntroActivity::class.java))
            }
            finish() // Call this when your activity is done and should be closed.
            // END
       }, 3000)
    }
}