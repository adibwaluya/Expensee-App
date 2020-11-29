package de.htwberlin.expensee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import de.htwberlin.expensee.databinding.ActivityMainBinding
import de.htwberlin.expensee.databinding.FragmentLoginBinding
import de.htwberlin.expensee.databinding.FragmentInputBinding
import de.htwberlin.expensee.databinding.FragmentMainPageBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //
        // binding = DataBindingUtil.setContentView(this, R.layout.fragment_login)

        // TODO: Move this part to MainPageFragment
        val navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
        //NavigationUI.setupWithNavController(binding.navView, navController)
    }

    // TODO: Move this part to MainPageFragment
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }
}