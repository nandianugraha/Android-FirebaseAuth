package com.example.nandi.firebase

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance()
    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btnRegister = findViewById<View>(R.id.btnRegister) as Button

        mDatabase = FirebaseDatabase.getInstance().getReference("Names")

        btnRegister.setOnClickListener(View.OnClickListener {
            view -> daftar()
        })
    }

    private fun daftar() {

        val txtEmail = findViewById<View>(R.id.txtEmail) as EditText
        val txtPass = findViewById<View>(R.id.txtPass) as EditText
        val txtUser = findViewById<View>(R.id.txtUser) as EditText

        var email = txtEmail.text.toString()
        var password = txtPass.text.toString()
        var name = txtUser.text.toString()

        if (!name.isEmpty() && !password.isEmpty() && !email.isEmpty()){
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener {
                task -> if (task.isSuccessful){
                val user = mAuth.currentUser
                val uid = user!!.uid
                mDatabase.child(uid).child("Name").setValue(name)
                startActivity(Intent(this, Timeline :: class.java))
                Toast.makeText(this, "Success Sign In", Toast.LENGTH_LONG).show()

            }else{
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
            }
            })
        }else{
            Toast.makeText(this, "Please Isi Dulu", Toast.LENGTH_LONG).show()
        }

    }
}
