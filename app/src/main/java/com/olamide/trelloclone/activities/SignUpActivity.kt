package com.olamide.trelloclone.activities

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.olamide.trelloclone.R
import com.olamide.trelloclone.databinding.ActivitySignUpBinding
import kotlinx.android.synthetic.main.dialog_progress.*

class SignUpActivity : BaseActivity() {

    private lateinit var myProgressDialog: Dialog
    private lateinit var myBinding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myBinding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(myBinding.root)



        firebaseAuth = FirebaseAuth.getInstance()


        // Click event for sign-up button.
       myBinding.btnSignUp12.setOnClickListener {
            registerUser()
        }
    }



    private fun registerUser() {
        val email = myBinding.etEmailReal.text.toString()
        val pass = myBinding.etPasswordReal.text.toString()

        if (email.isNotEmpty() && pass.isNotEmpty()) {

            firebaseAuth.createUserWithEmailAndPassword(email, pass).
            addOnCompleteListener{

                if (it.isSuccessful) {

                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this,
                        it.exception.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }




    /**
     * This function is used to show the progress dialog with the title and message to user.
     */
    private fun showProgressDialogs (text: String) {
        myProgressDialog = Dialog(this)

        /*Set the screen content from a layout resource.
        The resource will be inflated, adding all top-level views to the screen.*/
        myProgressDialog.setContentView(R.layout.dialog_progress)

        myProgressDialog.tv_progress_text.text = text

        //Start the dialog and display it on screen.
        myProgressDialog.show()
    }


  /*  // TODO (Step 8: A function for setting up the actionBar.)
    /**
     * A function for actionBar Setup.
     */
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
    } */

}