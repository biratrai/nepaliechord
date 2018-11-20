package com.example.gooner10.nepaliechord.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.gooner10.nepaliechord.R
import com.example.gooner10.nepaliechord.model.SingerDetail
import com.example.gooner10.nepaliechord.model.Song
import com.example.gooner10.nepaliechord.model.SongDetail
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import java.util.*


/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity(), AnkoLogger {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val RC_SIGN_IN = 123
    private val database = FirebaseDatabase.getInstance()
    val databaseRef = database.reference

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        handleLogin()
        submit.setOnClickListener { view ->
            if (view!!.id == R.id.submit) {
                saveSong()
            }
        }
    }

    private fun saveSong() {
        databaseRef.child("singers").addListenerForSingleValueEvent(
                object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                        error("Cancelled ${p0.code}")
                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        var singerKey: String? = ""
                        info("dataSnapshot Name ${dataSnapshot.children.count()}")
                        for (data in dataSnapshot.children) {
                            val singerDetail = data.getValue(SingerDetail::class.java)
                            info("singer Name ${singerDetail!!.singerName}")
                            if (singerDetail!!.singerName == singerName.text.toString()) {
                                debug("dataKey ${data.child(data.key!!)}")
                                singerKey = singerDetail.singerId
                                info("singerId ${singerDetail.singerId}")
                            }
                        }
                        info("singerId $singerKey")
                        if (singerKey.equals("")) {
                            singerKey = databaseRef.child("singers").push().key
                        }

                        submitData(singerKey!!)
                    }

                }
        )

//        val singerKey = databaseRef.child("singers").push().key

    }

    private fun submitData(singerKey: String) {
        val singerDetail = SingerDetail(singerKey, singerName.text.toString(), "link")
        databaseRef.child("singers").child(singerKey).setValue(singerDetail)

        val songDetailKey = databaseRef.child("song-detail").push().key
        val songKey = databaseRef.child("allSongs").push().key
        val song = Song(singerName.text.toString(),
                songTitle.text.toString(),
                false,
                0,
                singerKey,
                songDetailKey!!,
                System.currentTimeMillis())

        databaseRef.child("allSongs").child(songKey!!).setValue(song)

        val songDet = SongDetail(songKey,
                "file:///android_asset/song.html")
        databaseRef.child("song-detail").child(songDetailKey).setValue(songDet)
        songTitle.text.clear()
        toast("Save song successfully!")
    }

    private fun handleLogin() {
        if (firebaseAuth.currentUser != null) {
            // already signed in
            info("user email ${firebaseAuth.currentUser!!.email}")
            if (firebaseAuth.currentUser!!.email == "birat.rc.rai@gmail.com") {
                return
            } else {
                finish()
            }
        } else {
            // not signed in
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(Arrays.asList(
                                    AuthUI.IdpConfig.GoogleBuilder().build()))
                            .build(),
                    RC_SIGN_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        // RC_SIGN_IN is the request code you passed into startActivityForResult(...) when starting the sign in flow.
        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            // Successfully signed in
            if (resultCode == Activity.RESULT_OK) {
//                startActivity(SignedInActivity.createIntent(this, response))
//                finish()
                info("user email ${firebaseAuth.currentUser!!.email}")
                info("Successful sign-in $response")
            } else {
                // Sign in failed
                if (response == null) {
                    // User pressed back button
                    error(R.string.sign_in_cancelled)
                    return
                }

                if (response.error!!.errorCode == ErrorCodes.NO_NETWORK) {
                    error(R.string.no_internet_connection)
                    return
                }

                error("Sign-in error: " + R.string.unknown_error + response.error)
            }
        }
    }

}
