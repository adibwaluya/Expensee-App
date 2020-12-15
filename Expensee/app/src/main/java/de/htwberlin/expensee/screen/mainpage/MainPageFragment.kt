package de.htwberlin.expensee.screen.mainpage

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import de.htwberlin.expensee.R
import de.htwberlin.expensee.databinding.FragmentMainPageBinding

class MainPageFragment : Fragment() {

    private lateinit var binding: FragmentMainPageBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var viewModel: MainPageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main_page,
            container,
            false
        )

        // ViewModel
        viewModel = ViewModelProvider(this).get(MainPageViewModel::class.java)
        binding.mainPageViewModel = viewModel
        binding.lifecycleOwner = this

        binding.inputButton.setOnClickListener { view: View ->
            Log.d("MainPage", "Button Clicked!")
            // Test for db
            /*var dataToSave = mutableMapOf<String, Float>()
            dataToSave.put("Income", 350f)
            mDocRef.set(dataToSave).addOnSuccessListener {
                Log.d("MainPage", "Input has been saved!")
            }*/
            view.findNavController().navigate(R.id.action_mainPageFragment_to_inputFragment)
        }

        setHasOptionsMenu(true)
        //drawerLayout = binding.drawerLayout
        //NavigationUI.setupWithNavController(binding.navView, findNavController())
        return binding.root

        // TODO: Add observer for finish event?
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.
                onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

    // Test for db
    private var mDocRef : DocumentReference = FirebaseFirestore.getInstance().document("sampleData/inputs")



    //TODO: Enable Access to the navigation drawer from the drawer button
}