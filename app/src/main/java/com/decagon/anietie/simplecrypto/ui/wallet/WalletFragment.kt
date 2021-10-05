package com.decagon.anietie.simplecrypto.ui.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.decagon.anietie.simplecrypto.R
import com.decagon.anietie.simplecrypto.databinding.FragmentWalletBinding
import com.decagon.anietie.simplecrypto.ui.onboarding.SignInDialogFragment
import com.decagon.anietie.simplecrypto.util.SimpleCryptoSharedPreferences
import org.koin.androidx.viewmodel.ext.android.viewModel

class WalletFragment : Fragment() {

    private val walletViewModel: WalletViewModel by viewModel()
    private var _binding: FragmentWalletBinding? = null
    private val args: WalletFragmentArgs by navArgs()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWalletBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (isNotSignedIn()) {
            binding.walletBalanceCardView.visibility = View.GONE
            binding.buyButton.visibility = View.GONE
            binding.sellButton.visibility = View.GONE
            binding.cryptoPriceCardView.visibility = View.GONE
        } else {
            binding.signInButton.visibility = View.GONE
            binding.signInText.visibility = View.GONE
        }

        binding.signInButton.setOnClickListener {
            activity?.supportFragmentManager?.let {
                SignInDialogFragment().show(it, "Sign In")
            }
        }

        binding.cardholderName.text = SimpleCryptoSharedPreferences(requireContext())
            .getString("UserName")
        val cardBalance = SimpleCryptoSharedPreferences(requireContext()).getInt("WalletBalance")
        binding.cardBalance.text = String.format(resources.getString(R.string.wallet_acct_balance), cardBalance)
        binding.buyButton.setOnClickListener {
            showCryptoSelectBottomSheet()
        }
    }

    private fun isNotSignedIn(): Boolean {
        return SimpleCryptoSharedPreferences(requireContext()).getString("UserName").isEmpty()
    }

    private fun showCryptoSelectBottomSheet() {
        activity?.supportFragmentManager?.let {

            CryptoSelectionBottomSheetFragment().apply {
                doOnWalletTypeSelected { document ->
                    val action = WalletFragmentDirections.actionNavWalletToCryptoTransactionFragment(args.cryptocurrency)
                    findNavController().navigate(action)
                }
                show(it, tag)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
