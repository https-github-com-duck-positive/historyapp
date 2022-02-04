package com.example.history

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.history.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity() {
    lateinit var binding : ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val transaction = supportFragmentManager.beginTransaction()
            .add(R.id.signup_frm, NicknameFragment())
        transaction.commit()

        binding.signupExitIv.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }

}