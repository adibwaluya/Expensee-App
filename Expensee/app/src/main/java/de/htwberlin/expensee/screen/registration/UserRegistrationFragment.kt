package de.htwberlin.expensee.screen.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseUser
import de.htwberlin.expensee.R
import de.htwberlin.expensee.databinding.FragmentUserRegistrationBinding

class UserRegistrationFragment : Fragment() {

    private lateinit var binding: FragmentUserRegistrationBinding
    private lateinit var viewModel: UserRegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_user_registration,
            container,
            false
        )

        // TODO: Move this to onCreate (probably)
        viewModel = ViewModelProvider(this).get(UserRegistrationViewModel::class.java)
        viewModel.getUserMutableLiveData.observe(viewLifecycleOwner, Observer<FirebaseUser>() {
            fun onChanged(firebaseUser: FirebaseUser) {
                if(firebaseUser != null) {
                    Toast.makeText(activity, "User Created", Toast.LENGTH_SHORT).show()
                }
            }
        })

        binding.userRegistrationViewModel = viewModel
        binding.lifecycleOwner = this

        binding.registerButton.setOnClickListener {
            var email: String? = null
            var password: String? = null

            if (email!!.isNotEmpty() && password!!.isNotEmpty()) {
                viewModel.register(email, password)
            }
        }
        return binding.root
    }
}