package com.example.history

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.history.databinding.FragmentIdBinding


class IdFragment : Fragment(), ExistView {
    lateinit var binding : FragmentIdBinding
    var existFlag = true
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIdBinding.inflate(inflater, container, false)
        var nickname = arguments?.getString("nickname")
        Log.d("nickname","$nickname")

        binding.signupIdCheckTv.setOnClickListener {
            checkExist()
        }

        binding.signupIdNextBtn.setOnClickListener {
            Log.d("onResponse","$existFlag")
            if(existFlag){
                showWarning("중복체크 버튼을 눌러주세요")
            } else if (binding.signupIdEt.text.toString().isEmpty()) {
                showWarning("아이디를 입력해주세요")
            } else if(binding.signupIdEt.length() < 4) {
                showWarning("아이디는 4글자 이상 15글자 미만이어야합니다.")
            } else {
                var passwordFragment = PasswordFragment()
                var bundle = Bundle()
                bundle.putString("nickname", nickname)
                bundle.putString("id", binding.signupIdEt.text.toString())
                passwordFragment.arguments = bundle
                (context as SignUpActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.signup_frm, passwordFragment)
                    .commitAllowingStateLoss()
            }
        }

        binding.signupIdEt.onFocusChangeListener = View.OnFocusChangeListener{ p0, p1 ->
            if(p1){

            } else {
                hideKeyboard(binding.signupIdEt)
            }
        }
        return binding.root
    }
    private fun hideKeyboard(editText: EditText){
        (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
            hideSoftInputFromWindow(editText.windowToken, 0)
        }
    }

    private fun showWarning(message : String){
        binding.signupIdWarningTv.visibility = View.VISIBLE
        binding.signupIdWarningIv.visibility = View.VISIBLE
        binding.signupIdWarningTv.text = message
    }

    override fun onAuthFailure() {
        TODO("Not yet implemented")
    }

    override fun onAuthLoading() {
        TODO("Not yet implemented")
    }

    override fun onAuthSuccess(body: Boolean) {
        Log.d("onExist_Success","$body")
        existFlag = body
        when(existFlag){
            false ->{
                binding.signupIdWarningIv.visibility = View.GONE
                binding.signupIdWarningTv.visibility = View.GONE
            }
            true ->{
                showWarning("중복입니다")
            }
        }
    }
    private fun checkExist(){
        val authService = AuthService()
        authService.setExistView(this)
        authService.userIdExist(binding.signupIdEt.text.toString())
    }

}