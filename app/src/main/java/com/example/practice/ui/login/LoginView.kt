package com.example.practice.ui.login

import com.example.practice.data.remote.auth.Result

interface LoginView {
    fun onLoginSuccess(code : Int, result: Result)
    fun onLoginFailure(code: Int, message: String)
}