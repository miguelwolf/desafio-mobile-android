package br.com.kplay.desafiomobileandroidproject.controller.home

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.kplay.desafiomobileandroidproject.R
import br.com.kplay.desafiomobileandroidproject.adapter.CarrinhoAdapter
import br.com.kplay.desafiomobileandroidproject.dao.RetrofitClient
import br.com.kplay.desafiomobileandroidproject.interfaces.DataService
import br.com.kplay.desafiomobileandroidproject.interfaces.OnClickListenerHack
import br.com.kplay.desafiomobileandroidproject.model.Product
import br.com.kplay.desafiomobileandroidproject.utils.Currency
import br.com.kplay.desafiomobileandroidproject.utils.VerifyUtils
import com.google.android.material.transition.platform.MaterialElevationScale
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CartFragment : Fragment() {


    private var mListProduto: List<Product>? = null

    private lateinit var tvQtdCarrinho: TextView
    private lateinit var pb: ProgressBar
    private lateinit var rv: RecyclerView
    private lateinit var tvCarrinhoVazio: AppCompatTextView
    private lateinit var tvTotal: AppCompatTextView
    private lateinit var tvSubTotal: AppCompatTextView
    private lateinit var tvShipping: AppCompatTextView
    private lateinit var tvTax: AppCompatTextView

    private lateinit var mListenerClick: OnClickListenerHack

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        exitTransition = MaterialElevationScale(/* growing= */ false)
        reenterTransition = MaterialElevationScale(/* growing= */ true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {


        val root = inflater.inflate(R.layout.fragment_cart, container, false)

        tvQtdCarrinho = root.findViewById(R.id.fragment_home_tv_itens)
        pb = root.findViewById(R.id.fragment_home_pb)
        rv = root.findViewById(R.id.fragment_home_rv)
        tvCarrinhoVazio = root.findViewById(R.id.fragment_home_tv_carrinho_vazio)
        tvTotal = root.findViewById(R.id.fragment_home_tv_total_value)
        tvSubTotal = root.findViewById(R.id.fragment_home_tv_subtotal_value)
        tvShipping = root.findViewById(R.id.fragment_home_tv_shipping_value)
        tvTax = root.findViewById(R.id.fragment_home_tv_tax_value)

        pb.visibility = View.VISIBLE
        rv.visibility = View.GONE


        mListenerClick = object : OnClickListenerHack {

            override fun onClick(view: View, position: Int) {

                when (view.id) {

                    (R.id.item_produto_carrinho_cl) -> {


                        val intent = Intent(activity, ProductDetailActivity::class.java)
                        val bundle = Bundle()

                        bundle.putParcelable(ProductDetailActivity.ARG_PRODUCT,
                            mListProduto!![position])
                        intent.putExtra("Bundle", bundle)
                        startActivity(intent)

//                        childFragmentManager
//                            .beginTransaction()
//                            .addSharedElement(view, Constants.IV_TRANSITION)
//                            .replace(R.id.fragment_home_container,
//                                ProductDetailFragment.newInstance(
//                                    mListProduto!![position]))
//                            .addToBackStack(ProductDetailFragment.TAG)
//                            .commit()

                    }

                }

            }

        }


        consultarDados()

        return root
    }

    override fun onResume() {
        super.onResume()
        consultarDados()

    }

    fun consultarDados() {
        val remote = RetrofitClient.createService(DataService::class.java)
        val call: Call<List<Product>> = remote.list()
        val response = call.enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, res: Response<List<Product>>) {

                mListProduto = res.body()!!

                if (mListProduto != null && !mListProduto!!.isEmpty()) {

                    val adapter = CarrinhoAdapter(mListProduto!!, context!!)

                    adapter.attachListener(mListenerClick)
                    rv.adapter = adapter
                    val layoutManager = LinearLayoutManager(context)
                    rv.layoutManager = layoutManager


                    tvQtdCarrinho.text = context?.getString(R.string.items_cart, adapter.itemCount)

                    var total = 0.0
                    var subTotal = 0.0
                    var shipping = 0.0
                    var tax = 0.0

                    for (prod in mListProduto!!) {
                        subTotal += prod.price
                        shipping += prod.shipping
                        tax += prod.tax
                    }

                    total += subTotal + shipping + tax

                    tvTotal.text = Currency.mascaraDinheiro(total, Currency.DINHEIRO_REAL)
                    tvSubTotal.text = Currency.mascaraDinheiro(subTotal, Currency.DINHEIRO_REAL)
                    tvShipping.text = Currency.mascaraDinheiro(shipping, Currency.DINHEIRO_REAL)
                    tvTax.text = Currency.mascaraDinheiro(tax, Currency.DINHEIRO_REAL)


                    tvCarrinhoVazio.visibility = View.GONE
                    rv.visibility = View.VISIBLE

                } else {
                    tvQtdCarrinho.text = "-"
                    tvCarrinhoVazio.visibility = View.VISIBLE
                }

                pb.visibility = View.GONE

            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {

                val alertDialog = AlertDialog.Builder(activity!!).create()
                alertDialog.setTitle(getString(R.string.alerta))
                alertDialog.setCancelable(false)
                alertDialog.setMessage(getString(R.string.problema_conexao_novamente_value, VerifyUtils.responseRequest(activity!!, t)))
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.tentar_novamente)) {
                    dialog: DialogInterface, which: Int ->
                    dialog.dismiss()
                    consultarDados()
                }
                alertDialog.setCancelable(false)
                alertDialog.show()

                pb.visibility = View.GONE

            }

        })
    }
}