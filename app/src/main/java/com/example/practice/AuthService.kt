package com.example.practice

import android.util.Log
import android.view.View
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthService {
    private lateinit var signUpView: SignUpView
    private lateinit var loginView: LoginView

    fun setSignUpView(signUpView: SignUpView) {
        this.signUpView = signUpView
    }

    fun setLoginView(loginView: LoginView) {
        this.loginView = loginView
    }

    fun signUp(user: User) {

        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)

        authService.signUp(user).enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                Log.d("SIGNUP/SUCCESS", response.toString())
                val resp: AuthResponse = response.body()!!
                when (val code = resp.code) {
                    1000 -> signUpView.onSignUpSuccess()
//                    else -> Log.d("SIGNUP/FAIL", code.toString())
                    else -> {
//                        signUpView.onSignUpFailure(resp)
                        signUpView.onSignUpFailure(resp.code, resp.message)

                        Log.d("SIGNUP/FAIL", code.toString())
                    }
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("SIGNUP/FAILURE", t.message.toString())
            }

        })

        Log.d("SIGNUP", "HELLO")
    }

    fun login(user: User) {

        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)

        authService.login(user).enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                Log.d("LOGIN/SUCCESS", response.toString())
                val resp: AuthResponse = response.body()!!
                when (val code = resp.code) {
                    1000 -> loginView.onLoginSuccess(code, resp.result!!)
                    else -> Log.d("LOGIN/FAIL", code.toString())
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                loginView.onLoginFailure()
                Log.d("LOGIN/FAILURE", t.message.toString())
            }

        })

        Log.d("LOGIN", "HELLO")
    }
}

