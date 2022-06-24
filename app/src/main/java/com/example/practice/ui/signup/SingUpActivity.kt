package com.example.practice.ui.signup

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.practice.data.entities.User
import com.example.practice.data.remote.auth.AuthService
import com.example.practice.databinding.ActivitySignupBinding

class SingUpActivity : AppCompatActivity(), SignUpView {
    lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUpSignUpBtn.setOnClickListener {
            signUp()
        }
    }

    private fun getUser(): User {
        val email: String =
            binding.signUpIdEt.text.toString() + "@" + binding.signUpDirectInputEt.text.toString()
        val pwd: String = binding.signUpPasswordEt.text.toString()
        val name: String = binding.signUpNameEt.text.toString()

        return User(email, pwd, name)
    }

//    private fun signUp() {
//
//        val userDB = SongDatabase.getInstance(this)!!
//        userDB.userDao().insert(getUser())
//
//        val user = userDB.userDao().getUsers()
//        Log.d("SIGNUPACT", user.toString())
//
//
//    }

    private fun signUp() {
        if (binding.signUpIdEt.text.toString()
                .isEmpty() || binding.signUpDirectInputEt.text.toString().isEmpty()
        ) {
            Toast.makeText(this, "이메일 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        if (binding.signUpNameEt.text.toString().isEmpty()) {
            Toast.makeText(this, "이름 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        if (binding.signUpPasswordEt.text.toString().isEmpty()) {
            Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        if (binding.signUpPasswordCheckEt.text.toString().isEmpty()) {
            Toast.makeText(this, "확인 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }


        if (binding.signUpPasswordEt.text.toString() != binding.signUpPasswordCheckEt.text.toString()) {
            Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            return
        }


        val authService = AuthService()
        authService.setSignUpView(this)

        authService.signUp(getUser())
    }

    override fun onSignUpSuccess() {
        finish()
    }

    //    override fun onSignUpFailure() {
    override fun onSignUpFailure(code: Int, message: String) {
//    override fun onSignUpFailure(resp: AuthResponse) {
        Log.d("D/SIGNUP/FAILURE", code.toString())
        Log.d("D/SIGNUP/FAILURE", message)
        binding.signUpNameErrorTv.visibility = View.GONE
        binding.signUpEmailErrorTv.visibility = View.GONE

        when (code) {
            2016, 2017 -> {
                binding.signUpEmailErrorTv.visibility = View.VISIBLE
                binding.signUpEmailErrorTv.text = message
            }
            2018 -> {
                binding.signUpNameErrorTv.visibility = View.VISIBLE
                binding.signUpNameErrorTv.text = message
            }
            2000 -> {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
            else -> Toast.makeText(this, "회원가입에 실패하였습니다", Toast.LENGTH_SHORT).show()

        }
    }
}