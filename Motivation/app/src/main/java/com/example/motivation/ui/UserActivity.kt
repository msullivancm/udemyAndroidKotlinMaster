package com.example.motivation.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.R
import com.example.motivation.infra.SecurityPreferences
import com.example.motivation.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSave.setOnClickListener(this)

        supportActionBar?.hide()

        //verifica se o username já está salvo
        verifiUserName()

    }

    private fun verifiUserName(){
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        if (name != ""){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
    override fun onClick(v: View) {
        if (v.id == R.id.button_save) {
            val name = binding.editName.text.toString()
            if (name != "") {
                handleSave()
            } else {
                Toast.makeText(
                    this,
                    getString(R.string.validation_mandaroty_name),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun handleSave() {
        val name = binding.editName.text.toString()
        if (name != ""){
            Toast.makeText(this,"handleSave",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
            SecurityPreferences(this).storeString(MotivationConstants.KEY.USER_NAME, name)
            finish()
        }
    }
}