package com.decagon.anietie.simplecrypto.ui.wallet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.decagon.anietie.simplecrypto.databinding.CryptoTypeItemBinding

class DocumentTypeAdapter(
    private val documents: List<DocumentTypeModel>
) : RecyclerView.Adapter<DocumentTypeAdapter.ViewHolder>() {

    var onClickListener: ((documents: DocumentTypeModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            CryptoTypeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(documents[position])
    }

    fun doOnWalletTypeSelected(listener: ((DocumentTypeModel) -> Unit)) {
        this.onClickListener = listener
    }

    override fun getItemCount(): Int = documents.size

    inner class ViewHolder(private var item: CryptoTypeItemBinding) :
        RecyclerView.ViewHolder(item.root) {
        fun bind(documents: DocumentTypeModel) {
            item.docName.text = documents.name
            item.docImg.setImageResource(documents.resource)
            item.root.setOnClickListener {
                onClickListener?.invoke(documents)
            }
        }
    }
}
