package com.example.practice.ui.signup

interface SignUpView {
    fun onSignUpSuccess()

    //    fun onSignUpFailure()
//    fun onSignUpFailure(resp: AuthResponse)
    fun onSignUpFailure(code: Int, message: String)
}