package com.example.practice

import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.flo.SongDatabase
import com.example.practice.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), LoginView {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginSignUpTv.setOnClickListener {
            startActivity(Intent(this, SingUpActivity::class.java))
        }

        binding.loginSignInBtn.setOnClickListener {
            login()
        }
    }

    private fun login() {
        if (binding.loginIdEt.text.toString()
                .isEmpty() || binding.loginDirectInputEt.text.toString().isEmpty()
        ) {
            Toast.makeText(this, "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }
        if (binding.loginPasswordEt.text.toString().isEmpty()) {
            Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        val email: String =
            binding.loginIdEt.text.toString() + "@" + binding.loginDirectInputEt.text.toString()
        val pwd: String = binding.loginPasswordEt.text.toString()
//        val songDB = SongDatabase.getInstance(this)!!
//        val user = songDB.userDao().getUser(email, pwd)
//
//        user?.let {
//            Log.d("LOGIN_ACT/GET_USER", "userId : ${user.id}, $user")
////            saveJwt(user.id)
//            startMainActivity()
//        }

        val authService = AuthService()
        authService.setLoginView(this)

        authService.login(User(email, pwd, ""))



    }

//    private fun saveJwt(jwt: Int){
//        val spf = getSharedPreferences("auth", MODE_PRIVATE)
//        val editor = spf.edit()
//
//        editor.putInt("jwt", jwt)
//        editor.apply()
//    }

    private fun saveJwt2(jwt: String) {
        val spf = getSharedPreferences("auth2", MODE_PRIVATE)
        val editor = spf.edit()

        editor.putString("jwt", jwt)
        editor.apply()
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onLoginSuccess(code: Int, result: Result) {
        when (code) {
            1000 -> {
                saveJwt2(result.jwt)
                startMainActivity()
            }
        }
        Toast.makeText(this, "로그인에 성공했습니다", Toast.LENGTH_SHORT).show()
    }

    override fun onLoginFailure(code: Int, message: String) {
        Log.d("LOGIN/FAIL/MESSAGE", message)
//        Toast.makeText(this, "회원 정보가 존재하지 않습니다", Toast.LENGTH_SHORT).show()

        binding.loginEmailErrorTv.visibility = View.GONE
        binding.loginPasswordErrorTv.visibility = View.GONE
        when (code) {
            2019 -> {
                binding.loginEmailErrorTv.visibility = View.VISIBLE
                binding.loginEmailErrorTv.text = message
            }
            3014 -> {
                binding.loginPasswordErrorTv.visibility = View.VISIBLE
                binding.loginPasswordErrorTv.text = message
            }
            else -> Toast.makeText(this, "로그인에 실패하였습니다", Toast.LENGTH_SHORT).show()
        }

    }
}