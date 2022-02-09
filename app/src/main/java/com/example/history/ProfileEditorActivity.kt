package com.example.history

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.history.databinding.ActivityLockSettingBinding
import com.example.history.databinding.ActivityProfileEditorBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProfileEditorActivity: AppCompatActivity() {
    private var mBinding: ActivityProfileEditorBinding?=null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityProfileEditorBinding.inflate(layoutInflater)
        setContentView(binding.root)

            binding.profileEditorExitIv.setOnClickListener{
                super.onBackPressed()
        }
        val retrofit = Retrofit.Builder()
            .baseUrl("https://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com/").addConverterFactory(
                GsonConverterFactory.create()).build()



        binding.profileEditorCompleteBtnIv.setOnClickListener {
            val request=NicknameChangeRequest("amy123",binding.profileEditorNicknameTv.text.toString())

            val retrofitService = retrofit.create(NicknameChangeInterface::class.java)
            retrofitService.changeNickname(request)
                .enqueue(object : Callback<NicknameChangeResponse> {
                    override fun onResponse(
                        call: Call<NicknameChangeResponse>,
                        response: Response<NicknameChangeResponse>
                    ) {
                        if (response.isSuccessful) {
                            val body = response.body()
                            body?.let {

                            }
                            Log.d("Nickname_onResponse", response.toString())
                        }
                    }

                    override fun onFailure(call: Call<NicknameChangeResponse>, t: Throwable) {
                        t.message?.let {
                            Log.d("nicknameChange error", it)
                        }
                    }
                })
        }

    }



}