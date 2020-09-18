package br.com.kplay.desafiomobileandroidproject.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Product (

    @SerializedName("name")
    var name: String = "",

    @SerializedName("quantity")
    var quantity: Int = 0,

    @SerializedName("stock")
    var stock: Int = 0,

    @SerializedName("image_url")
    var imageUrl: String = "",

    @SerializedName("price")
    var price: Double = 0.0,

    @SerializedName("tax")
    var tax: Double = 0.0,

    @SerializedName("shipping")
    var shipping: Double = 0.0,

    @SerializedName("description")
    var description: String = "",

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString()!!) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel?, p1: Int) {
        parcel!!.writeString(name)
        parcel.writeInt(quantity)
        parcel.writeInt(stock)
        parcel.writeString(imageUrl)
        parcel.writeDouble(price)
        parcel.writeDouble(tax)
        parcel.writeDouble(shipping)
        parcel.writeString(description)
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}