package de.htwberlin.expensee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import de.htwberlin.expensee.databinding.FragmentInputBinding
import de.htwberlin.expensee.databinding.FragmentLoginBinding
import de.htwberlin.expensee.databinding.FragmentMainPageBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: FragmentInputBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
}