package com.decagon.anietie.simplecrypto.ui.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.decagon.anietie.simplecrypto.databinding.FragmentCryptoTransactionBinding

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

        binding.proceedButton.setOnClickListener {
            val dialog = ConfirmationFragment()
            dialog.show(
                requireActivity().supportFragmentManager,
                "transaction_success_dialog"
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
