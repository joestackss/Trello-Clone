package com.olamide.trelloclone.activities

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.olamide.trelloclone.R
import com.olamide.trelloclone.databinding.ActivityLoginBinding
import com.olamide.trelloclone.model.User

class LoginActivity : BaseActivity() {

    var binding : ActivityLoginBinding? = null
    private lateinit var firebaseAuth: FirebaseAuth
    var mProgressDialogs1 : ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setupActionBar()

        firebaseAuth = FirebaseAuth.getInstance()

        // TODO(Step 4: Add click event for sign-in button and call the function to sign in.)
        // START
        binding?.btnSignIn?.setOnClickListener{
            signInRegisteredUser()
        }
        // END

        binding?.noAccount?.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }


    // TODO (Step 2: A function for Sign-In using the registered user using the email and password.)
    // START
    /**
     * A function for Sign-In using the registered user using the email and password.
     */
    private fun signInRegisteredUser() {

        // Here we get the text from editText and trim the space

        val email = binding?.etEmailLoginReal?.text.toString()
        val pass = binding?.etPasswordLoginReal?.text.toString()

        if (validateForm(email, pass)) {

            // Show the progress dialog.
            showProgressDialog("signing in")

            // Sign-In using FirebaseAuth
            firebaseAuth.signInWithEmailAndPassword(email, pass).
            addOnCompleteListener{

                // Hide the progress dialog
                hideProgressDialog()

                if (it.isSuccessful) {

                    Toast.makeText(this@LoginActivity,
                        "You have successfully signed in.",
                        Toast.LENGTH_LONG
                    ).show()
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                } else {
                    Toast.makeText(this@LoginActivity,
                        it.exception.toString(),
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
        }

    }



    private fun validateForm(email: String, pass: String): Boolean {
        return when {
            TextUtils.isEmpty(email) -> {
                showErrorSnackBar("Please enter email.")
                false
            }
            TextUtils.isEmpty(pass) -> {
                showErrorSnackBar("Please enter password.")
                false
            }
            else -> {
                true
            }
        }
    }






    /**
     * This function is used to show the progress dialog with the title and message to user.
     */
    private fun showProgressDialog(text: String) {
        mProgressDialogs1 = ProgressDialog(this)
        mProgressDialogs1?.setMessage("signing in")
        mProgressDialogs1?.setCanceledOnTouchOutside(false)
        mProgressDialogs1?.setCancelable(false)
        mProgressDialogs1?.show()
    }


    /**
     * This function is used to dismiss the progress dialog if it is visible to user.
     */
    fun hideProgressDialog() {
        mProgressDialogs1?.dismiss()
    }


    // TODO (Step 8: A function for setting up the actionBar.)
    /**
     * A function for actionBar Setup.
     */
    private fun setupActionBar() {

        setSupportActionBar(binding?.toolbarSignInActivity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24dp)
        }

        binding?.toolbarSignInActivity?.setNavigationOnClickListener {
            onBackPressed()
        }
    }


    /**
     * A function to get the user details from the firestore database after authentication.
     */
    fun signInSuccess(user: User) {

        hideProgressDialog()

        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        this.finish()
    }
}
// END