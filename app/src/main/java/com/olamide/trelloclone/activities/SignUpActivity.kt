package com.olamide.trelloclone.activities

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.olamide.trelloclone.R
import com.olamide.trelloclone.databinding.ActivitySignUpBinding
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.dialog_progress.*

class SignUpActivity : BaseActivity() {

    private lateinit var myProgressDialog: Dialog
    private lateinit var myBinding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    var mProgressDialogs1 : ProgressDialog? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myBinding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(myBinding.root)


        setupActionBar()


        firebaseAuth = FirebaseAuth.getInstance()


        // Click event for sign-up button.
       myBinding.btnSignUp12.setOnClickListener {
            registerUser()
        }

        myBinding.yesAccount.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }



    private fun registerUser() {
        val email = myBinding.etEmailReal.text.toString()
        val pass = myBinding.etPasswordReal.text.toString()

        if (validateForm(email, pass)) {
            showProgressDialog("signing up")
            firebaseAuth.createUserWithEmailAndPassword(email, pass).
            addOnCompleteListener{

                // Hide the progress dialog
                hideProgressDialog()

                if (it.isSuccessful) {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this,
                        it.exception.toString(),
                        Toast.LENGTH_SHORT
                    ).show()

                    /**
                     * Here the new user registered is automatically signed-in so we just sign-out the user from firebase
                     * and send him to Intro Screen for Sign-In
                     */
                    firebaseAuth.signOut()
                    // Finish the Sign-Up Screen
                    finish()
                }
            }
        }
    }


    /**
     * This function is used to show the progress dialog with the title and message to user.
     */
    private fun showProgressDialog(text: String) {
        mProgressDialogs1 = ProgressDialog(this)
        mProgressDialogs1?.setMessage("signing you up")
        mProgressDialogs1?.show()
    }





  // TODO (Step 8: A function for setting up the actionBar.)

     // A function for actionBar Setup.

    private fun setupActionBar() {

        setSupportActionBar(toolbar_sign_up_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24dp)
        }

       toolbar_sign_up_activity.setNavigationOnClickListener {
            onBackPressed()
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
     * This function is used to dismiss the progress dialog if it is visible to user.
     */
    private fun hideProgressDialog() {
        mProgressDialogs1?.dismiss()
    }

}