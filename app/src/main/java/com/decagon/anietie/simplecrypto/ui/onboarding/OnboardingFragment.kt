package com.decagon.anietie.simplecrypto.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.decagon.anietie.simplecrypto.R
import com.decagon.anietie.simplecrypto.databinding.FragmentOnboardingBinding
import com.decagon.anietie.simplecrypto.util.SimpleCryptoSharedPreferences

/**
 * This fragment gives the user a quick overview of what the app can do
 * and allows the user to sign in.
 */
class OnboardingFragment : Fragment() {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (restorePrefsData()) {
            findNavController().navigate(R.id.nav_home)
        }
    }

    private fun restorePrefsData(): Boolean {
        return SimpleCryptoSharedPreferences(requireContext())
            .getBoolean("isIntroOpened", false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.exploreButton.setOnClickListener {
            findNavController().popBackStack()
            findNavController().navigate(R.id.nav_home)

            savePrefsData()
        }

        binding.signInButton.setOnClickListener {
            activity?.supportFragmentManager?.let {
                SignInDialogFragment().show(it, "Sign In")
            }
            savePrefsData()
        }
    }

    private fun savePrefsData() {
        SimpleCryptoSharedPreferences(requireContext()).setBoolean("isIntroOpened", true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
