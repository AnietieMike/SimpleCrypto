package com.decagon.anietie.simplecrypto.ui.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.decagon.anietie.simplecrypto.R
import com.decagon.anietie.simplecrypto.databinding.FragmentCryptoTransactionBinding
import com.decagon.anietie.simplecrypto.util.SimpleCryptoSharedPreferences

class CryptoTransactionFragment : Fragment() {

    private var _binding: FragmentCryptoTransactionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCryptoTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViews()
        activateNext()

        binding.proceedButton.setOnClickListener {
            val initialBalance = SimpleCryptoSharedPreferences(requireContext()).getInt("WalletBalance")
            val newBalance = initialBalance - binding.amountEditText.text.toString().toInt()
            SimpleCryptoSharedPreferences(requireContext()).setInt("WalletBalance", newBalance)
            val dialog = ConfirmationFragment()
            dialog.show(
                requireActivity().supportFragmentManager,
                "transaction_success_dialog"
            )
        }

        binding.amountEditText.doAfterTextChanged {
            activateNext()
        }
    }

    private fun setUpViews() {
        val itemSelected = SimpleCryptoSharedPreferences(
            requireContext()
        ).getString(getString(R.string.item_selection))
        when (itemSelected) {
            "Bitcoin" -> binding.cryptoTextView.text = getString(R.string.btc)
            "Ethereum" -> binding.cryptoTextView.text = getString(R.string.eth)
            "Ripple" -> binding.cryptoTextView.text = getString(R.string.xrp)
            "Dogecoin" -> binding.cryptoTextView.text = getString(R.string.doge)
            "Litecoin" -> binding.cryptoTextView.text = getString(R.string.ltc)
            "Dash" -> binding.cryptoTextView.text = getString(R.string.dash_short)
        }
        binding.transactionTitle.text = String.format(resources.getString(R.string.transaction_title), itemSelected)
    }

    private fun activateNext() {
        val initialBalance = SimpleCryptoSharedPreferences(requireContext()).getInt("WalletBalance")
        binding.proceedButton.isEnabled = binding.amountEditText.text?.isNotBlank() == true &&
            0 < binding.amountEditText.text.toString().toInt() &&
            binding.amountEditText.text.toString().toInt() <= initialBalance
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
