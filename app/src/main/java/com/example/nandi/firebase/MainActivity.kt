package com.example.nandi.firebase

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLogin = findViewById<View>(R.id.btnLogin) as Button

        val btnDaftar = findViewById<View>(R.id.txtDaftar) as TextView

        btnLogin.setOnClickListener(View.OnClickListener {
            view -> login()
        })

        btnDaftar.setOnClickListener(View.OnClickListener {
            view -> daftar()
        })
    }

    private fun daftar() {
        startActivity(Intent(this, Register :: class.java))
    }

    private fun login() {
        val txtEmail = findViewById<View>(R.id.txtEmail) as EditText
        val txtPass = findViewById<View>(R.id.txtPass) as EditText

        var email = txtEmail.text.toString()
        var password = txtPass.text.toString()

        if (!email.isEmpty() && !password.isEmpty()){
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener {
                task -> if(task.isSuccessful){
                startActivity(Intent(this, Timeline :: class.java))
                Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
            }
            })
        }else{
            Toast.makeText(this, "Isi Semua", Toast.LENGTH_LONG).show()
        }
    }
}
