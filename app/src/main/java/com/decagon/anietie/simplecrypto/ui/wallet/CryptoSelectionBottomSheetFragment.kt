package com.decagon.anietie.simplecrypto.ui.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.decagon.anietie.simplecrypto.R
import com.decagon.anietie.simplecrypto.databinding.CryptoTypeBottomSheetBinding
import com.decagon.anietie.simplecrypto.util.SimpleCryptoSharedPreferences
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CryptoSelectionBottomSheetFragment() : BottomSheetDialogFragment() {

    private lateinit var binding: CryptoTypeBottomSheetBinding

    private var onBottomSheetActionClicked: ((DocumentTypeModel) -> Unit)? = null
    private lateinit var adapter: DocumentTypeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CryptoTypeBottomSheetBinding.bind(inflater.inflate(R.layout.crypto_type_bottom_sheet, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(getDocuments())
    }

    fun doOnWalletTypeSelected(listener: ((DocumentTypeModel) -> Unit)) {
        this.onBottomSheetActionClicked = listener
    }

    private fun getDocuments() = listOf(
        DocumentTypeModel(getString(R.string.bitcoin), R.drawable.bitcoin),
        DocumentTypeModel(getString(R.string.ethereum), R.drawable.ethereum),
        DocumentTypeModel(getString(R.string.ripple), R.drawable.ripple),
        DocumentTypeModel(getString(R.string.dogecoin), R.drawable.dogecoin),
        DocumentTypeModel(getString(R.string.litecoin), R.drawable.litecoin),
        DocumentTypeModel(getString(R.string.dash), R.drawable.dash)
    )

    private fun setUpViews(documents: List<DocumentTypeModel>) {
        adapter = DocumentTypeAdapter(documents = documents).apply {
            doOnWalletTypeSelected {
                onBottomSheetActionClicked?.invoke(it)
                SimpleCryptoSharedPreferences(
                    requireContext()
                ).setString(getString(R.string.item_selection), it.name)
                dismiss()
            }
        }
        binding.recyclerView.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance(): CryptoSelectionBottomSheetFragment {
            val fragment = CryptoSelectionBottomSheetFragment()
            return fragment
        }
    }
}
