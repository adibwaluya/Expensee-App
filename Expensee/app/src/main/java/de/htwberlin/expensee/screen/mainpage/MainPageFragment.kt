package de.htwberlin.expensee.screen.mainpage

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import de.htwberlin.expensee.R
import de.htwberlin.expensee.databinding.FragmentMainPageBinding
import de.htwberlin.expensee.screen.input.Input
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.StringBuilder

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
            /*
            var dataToSave = mutableMapOf<String, Float>()
            var incomeValue = binding.editTextNumber.text.toString().toFloat()
            dataToSave.put("Income", incomeValue)
            viewModel.mDocRef.set(dataToSave).addOnSuccessListener {
                Log.d("MainPage", "Input has been saved!")
            }

             */
            view.findNavController().navigate(R.id.action_mainPageFragment_to_inputFragment)
        }

        binding.refreshButton.setOnClickListener { view: View ->
            Log.d("MainPage", "Refresh!")

            vmRetrieveInput()
            // TODO: Ask Mike what should I do here
            /*
            viewModel.fetchInput()
            Thread.sleep(2000L)
             */
            //binding.textView.setText(viewModel.data.toString())
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

    // Updated on 06.01.2021
    private val budgetCollectionRef = Firebase.firestore
            .collection("sampleData")

    fun vmRetrieveInput() = CoroutineScope(Dispatchers.IO).launch {
        try {

            // querySnapshot = The result of our query in firestore
            // get() = return all the available document inside of the relevant collection
            val querySnapshot = budgetCollectionRef.get().await()
            val sb = StringBuilder()
            for (document in querySnapshot.documents) {

                // Get the data from our document and convert it to our Input class
                val income = document.toObject<Input>()
                sb.append("$income\n")
            }
            withContext(Dispatchers.Main) {
                binding.budgetList.text = sb.toString()
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(activity, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }




    //TODO: Enable Access to the navigation drawer from the drawer button
}