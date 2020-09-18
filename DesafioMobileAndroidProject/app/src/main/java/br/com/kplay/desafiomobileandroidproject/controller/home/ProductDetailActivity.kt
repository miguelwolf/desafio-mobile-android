package br.com.kplay.desafiomobileandroidproject.controller.home

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import br.com.kplay.desafiomobileandroidproject.R
import br.com.kplay.desafiomobileandroidproject.model.Product
import br.com.kplay.desafiomobileandroidproject.utils.Constants
import br.com.kplay.desafiomobileandroidproject.utils.Currency
import com.squareup.picasso.Picasso
import java.lang.NullPointerException

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var product: Product

    companion object {
        val ARG_PRODUCT: String = "ARG_PRODUCT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val toolbar: Toolbar = findViewById(R.id.toolbar_product_detail)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_close_black_24dp)


        val tvTitle: AppCompatTextView =
            findViewById(R.id.product_detail_tv_title)
        val tvStock: AppCompatTextView =
            findViewById(R.id.product_detail_tv_stock)
        val tvPrice: AppCompatTextView =
            findViewById(R.id.product_detail_tv_price)
        val tvDescription: AppCompatTextView =
            findViewById(R.id.product_detail_tv_description)
        val ivImageView: ImageView = findViewById(R.id.product_detail_iv)


        try{

            val bundle = intent.getBundleExtra(Constants.BUNDLE)
            product = bundle.getParcelable(ARG_PRODUCT)!!

            tvTitle.text = product.name
            tvStock.text =
                if (product.stock > 0) getString(R.string.stock) else getString(R.string.out_stock)
            tvPrice.text = Currency.mascaraDinheiro(product.price, Currency.DINHEIRO_REAL)
            tvDescription.text = product.description
            Picasso.get().load(product.imageUrl).into(ivImageView)

        } catch (e: NullPointerException) {
            Toast.makeText(applicationContext, getString(R.string.sem_dados), Toast.LENGTH_SHORT).show()
            onBackPressed()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.product_details, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            (android.R.id.home) -> onBackPressed()
            (R.id.action_pesquisar) -> Toast.makeText(applicationContext, getString(R.string.pesquisar), Toast.LENGTH_SHORT).show()

        }

        return false
    }
}