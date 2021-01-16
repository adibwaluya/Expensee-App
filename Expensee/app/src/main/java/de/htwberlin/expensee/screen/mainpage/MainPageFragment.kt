package de.htwberlin.expensee.screen.mainpage

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import de.htwberlin.expensee.R
import de.htwberlin.expensee.databinding.FragmentMainPageBinding
import java.lang.Exception

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

        viewModel.saldo.observe(viewLifecycleOwner, Observer { currentSaldo ->

            val spannableString = SpannableString(currentSaldo.toDouble().toString())
            val mUnderlineSpan = UnderlineSpan()
            spannableString.setSpan(mUnderlineSpan, 0, spannableString.length, 0)
            binding.currentSaldoEt.text = "$spannableString €"

        })

        // LiveData Observer for data from Firestore
        viewModel.inputData.observe(viewLifecycleOwner, Observer { newInput ->
            binding.budgetList.text = newInput
        })

        binding.refreshButton.setOnClickListener { view: View ->
            Log.d("MainPage", "Refresh!")

            //retrieveInput()
            try {
                viewModel.vmRetrieveInput()
                //binding.budgetList.text = viewModel.sb.value
                Toast.makeText(activity, "Refreshed!", Toast.LENGTH_LONG).show()
            }
            catch (e: Exception) {
                Toast.makeText(activity, e.message, Toast.LENGTH_LONG).show()
            }
        }

        binding.fab.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_mainPageFragment_to_inputFragment)
        }

        /* // TODO: Implemented soon!
        binding.fabDelete.setOnClickListener { view: View ->
            try {
                viewModel.deletePerson()
            } catch (e: Exception) {
                Toast.makeText(activity, e.message, Toast.LENGTH_LONG).show()
            }

        }

         */

        viewModel.vmRetrieveInput()

        setHasOptionsMenu(true)
        //drawerLayout = binding.drawerLayout
        //NavigationUI.setupWithNavController(binding.navView, findNavController())
        return binding.root
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

    //TODO: Enable Access to the navigation drawer from the drawer button -> Still needed?
}