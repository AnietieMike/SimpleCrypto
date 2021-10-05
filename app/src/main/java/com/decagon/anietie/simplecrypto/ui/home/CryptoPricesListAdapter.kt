package com.decagon.anietie.simplecrypto.ui.home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.decagon.anietie.simplecrypto.R
import com.decagon.anietie.simplecrypto.databinding.ItemHomeBinding
import com.decagon.anietie.simplecrypto.model.domain.Cryptocurrency
import java.math.RoundingMode
import java.text.DecimalFormat

/**
* Adapter class for Cryptocurrency Recycler view
* */
class CryptoPricesListAdapter(
    private val cryptocurrencies: List<Cryptocurrency>
) : RecyclerView.Adapter<CryptoPricesListAdapter.CryptoPricesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoPricesViewHolder {
        val binding = ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CryptoPricesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CryptoPricesViewHolder, position: Int) {
        holder.bindView(cryptocurrencies[position])
    }

    override fun getItemCount(): Int = cryptocurrencies.size

    class CryptoPricesViewHolder(binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root) {

        private val cryptoIcon: ImageView = binding.cryptoIconImageView
        private val cryptoSymbol: TextView = binding.textViewItemTransform
        private val cryptoName: TextView = binding.cryptoNameText
        private val cryptoPrice: TextView = binding.cryptoPrice
        private val cryptoMarketCapPercent: TextView = binding.cryptoPercent

        private val drawables = listOf(
            R.drawable.bitcoin,
            R.drawable.ethereum,
            R.drawable.ripple,
            R.drawable.dogecoin,
            R.drawable.litecoin,
            R.drawable.dash
        )

        private val cryptoSymbols = listOf("BTC", "ETH", "XRP", "DOGE", "LTC", "DASH")

        fun bindView(cryptocurrency: Cryptocurrency) {
            val price = cryptocurrency.usd
            val marketCapPercent = cryptocurrency.usd_24h_change
            val percent = String.format("%.1f", marketCapPercent).toDouble()

            val df = DecimalFormat("#,###.##")
            df.roundingMode = RoundingMode.CEILING
            val formattedPrice = df.format(price)

            cryptoIcon.setImageDrawable(
                ResourcesCompat.getDrawable(
                    cryptoIcon.resources, drawables[layoutPosition], null
                )
            )
            cryptoSymbol.text = cryptoSymbols[layoutPosition]
            cryptoName.text = cryptocurrency.name
            "$$formattedPrice".also { cryptoPrice.text = it }
            if (percent < 0.0) {
                cryptoMarketCapPercent.setTextColor(Color.parseColor("#FF0000"))
            }
            "$percent%".also { cryptoMarketCapPercent.text = it }
        }
    }
}

// class TransformAdapter : ListAdapter<String, TransformViewHolder>(object : DiffUtil.ItemCallback<String>() {
//
//    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
//        oldItem == newItem
//
//    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
//        oldItem == newItem
// }) 
