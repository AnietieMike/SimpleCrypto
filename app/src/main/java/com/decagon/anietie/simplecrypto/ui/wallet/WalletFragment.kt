package com.decagon.anietie.simplecrypto.ui.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.decagon.anietie.simplecrypto.R
import com.decagon.anietie.simplecrypto.databinding.FragmentWalletBinding

class WalletFragment : Fragment() {

    private lateinit var slideshowViewModel: SlideshowViewModel
    private var _binding: FragmentWalletBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentWalletBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buyButton.setOnClickListener {
            showCryptoSelectBottomSheet()
        }

        binding.sellButton.setOnClickListener {
            showCryptoSelectBottomSheet()
        }
    }

    private fun showCryptoSelectBottomSheet() {
        activity?.supportFragmentManager?.let {

            CryptoSelectionBottomSheetFragment().apply {
                doOnWalletTypeSelected { document ->
                    val action = WalletFragmentDirections.actionNavWalletToCryptoTransactionFragment()
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
