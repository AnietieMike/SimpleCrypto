package com.decagon.anietie.simplecrypto.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.decagon.anietie.simplecrypto.R
import com.decagon.anietie.simplecrypto.databinding.FragmentSignInDialogBinding
import com.decagon.anietie.simplecrypto.util.SimpleCryptoSharedPreferences

class SignInDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentSignInDialogBinding.bind(view)

        binding.buttonPost.setOnClickListener {
            val userName = binding.userNameInputText.text.toString()
            val userPin = binding.userPinInputText.text.toString()

            if (userName.isEmpty() || userPin.isEmpty()) {
                binding.userNameInputText.error = "This field can't be empty"
                binding.userPinInputText.error = "This field can't be empty"
            } else {
                // Pass the userName through shared preferences to the home fragment
                SimpleCryptoSharedPreferences(requireContext()).setString("UserName", userName)
                SimpleCryptoSharedPreferences(requireContext()).setString("UserPin", userPin)
                SimpleCryptoSharedPreferences(requireContext()).setInt("WalletBalance", 500)
                findNavController().navigate(R.id.nav_home)
                dismiss()
            }

            requireActivity().recreate()
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length in 5..8
    }
}
