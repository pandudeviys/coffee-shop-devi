package com.juaraandroid.coffee_shop_devi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity__antrian__pesanan.*

class Activity_Antrian_Pesanan : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__antrian__pesanan)

        val Intent = this.intent
        val pesanan = intent.getStringExtra("Pesanan")
        val totalharga = intent.getStringExtra("Harga")
        val TambahanMilk = intent.getStringExtra("Extra_Milk")
        val TambahanCoffee = intent.getStringExtra("Extra_Coffee")
        val syrup_tambahan = intent.getStringExtra("Syrup")
        val noAntrian = intent.getStringExtra("NoAntrian")

        txtNomerAntrian.setText(noAntrian.toString())
        txtNamaPesanan.setText(pesanan + "\n" + syrup_tambahan + "\n"+ TambahanMilk + "\n" + TambahanCoffee)
        txtTotalHarga.setText("Total Harga: Rp."+totalharga)
        buttonOrderAnother.setOnClickListener{
            finish()

        }
    }
}
