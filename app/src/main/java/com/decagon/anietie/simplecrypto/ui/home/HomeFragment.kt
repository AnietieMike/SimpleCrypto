package com.decagon.anietie.simplecrypto.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.decagon.anietie.simplecrypto.databinding.FragmentHomeBinding
import com.decagon.anietie.simplecrypto.model.domain.Cryptocurrency
import com.decagon.anietie.simplecrypto.util.DataState
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.IOException

/**
 * Fragment that demonstrates a responsive layout pattern where the format of the content
 * transforms depending on the size of the screen. Specifically this Fragment shows items in
 * the [RecyclerView] using LinearLayoutManager in a small screen
 * and shows items using GridLayoutManager in a large screen.
 */
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var cryptocurrencies: List<Cryptocurrency>
    private var _binding: FragmentHomeBinding? = null
    private val CRYPTO_IDS = "bitcoin,ethereum,ripple,dogecoin,litecoin,dash"
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    try {
                        Runtime.getRuntime()
                            .exec("am force-stop ".plus("com.decagon.anietie.simplecrypto"))
                        android.os.Process.killProcess(android.os.Process.myPid())
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        subscribeObservers()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val params = HashMap<String, String>()
        params["ids"] = CRYPTO_IDS
        params["vs_currencies"] = "usd"
        params["include_market_cap"] = "true"
        params["include_24hr_change"] = "true"
        homeViewModel.getPricesList(params)
    }

    private fun subscribeObservers() {
        homeViewModel.result.observe(
            viewLifecycleOwner,
            Observer { result ->
                when (result.status) {
                    DataState.Status.SUCCESS -> {
                        result.data?.let { list ->
                            Log.d("ResultsSuccess", "subscribeObservers: $list")
                            cryptocurrencies = list
                            val recyclerView = binding.recyclerviewHome
                            val adapter = CryptoPricesListAdapter(cryptocurrencies)
                            recyclerView.adapter = adapter
                        }
                    }
                    DataState.Status.ERROR -> {
                        result.message?.let {
                            Snackbar.make(
                                requireView(),
                                it,
                                Snackbar.LENGTH_LONG
                            ).setAction("Action", null).show()
                        }
                    }
                    DataState.Status.LOADING -> {
                        // Implement a progress bar
                        Snackbar.make(
                            requireView(),
                            "Loading...",
                            Snackbar.LENGTH_LONG
                        ).setAction("Action", null).show()
                    }
                }
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
