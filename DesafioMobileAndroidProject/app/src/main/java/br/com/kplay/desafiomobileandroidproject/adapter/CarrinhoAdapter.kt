package br.com.kplay.desafiomobileandroidproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import br.com.kplay.desafiomobileandroidproject.R
import br.com.kplay.desafiomobileandroidproject.interfaces.OnClickListenerHack
import br.com.kplay.desafiomobileandroidproject.model.Product
import br.com.kplay.desafiomobileandroidproject.utils.Currency
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_produto_carrinho.view.*

class CarrinhoAdapter(
    private val list: List<Product>,
    private val context: Context,
) : Adapter<CarrinhoAdapter.ViewHolder>() {

    private lateinit var mListener: OnClickListenerHack

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_produto_carrinho, parent, false)
        return ViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindView(list.get(position), context)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View, private val listener: OnClickListenerHack) :
        RecyclerView.ViewHolder(itemView) {

        fun bindView(product: Product, context: Context) {
            val photo = itemView.item_produto_carrinho_iv_photo
            val title = itemView.item_produto_carrinho_tv_title
            val stock = itemView.item_produto_carrinho_tv_stock
            val price = itemView.item_produto_carrinho_tv_price
//            val view = itemView.item_produto_carrinho_start_view


            Picasso.get().load(product.imageUrl).into(photo)
            title.text = product.name
            stock.text = if (product.stock > 0) context.getString(R.string.stock)
                            else context.getString(R.string.out_stock)
            price.text = Currency.mascaraDinheiro(product.price, Currency.DINHEIRO_REAL)



            itemView.setOnClickListener {
                listener.onClick(itemView, layoutPosition)
            }

        }
    }

    /**
     * Eventos na listagem
     */
    fun attachListener(listener: OnClickListenerHack) {
        mListener = listener
    }

}

