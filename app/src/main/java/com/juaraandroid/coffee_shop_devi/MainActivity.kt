package com.juaraandroid.coffee_shop_devi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.CheckBox
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    var hargaKopi = 20000 //Harga Kopi
    var extraMilk = "" //checkbox Extra Milk
    var extraCoffee = "" //checkbox Extra Coffee
    var syrup_Extra = "" //checkbox Extra Syrup , radiobutton syrup flavour
    var nomerAntrian = 0 // nomer antrian
    var switch_Nilai = "" //Nilai switch hot or ice

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RadioGroupSyrupFlavour.visibility = View.INVISIBLE
        txtSyrup.visibility = View.INVISIBLE

        val adapter = ArrayAdapter.createFromResource(this, R.array.ListCoffee, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPilihCoffee.adapter = adapter;
        txtTotalHarga.text ="Total Harga: Rp." + hargaKopi.toString()



        RadioGroupSyrupFlavour.setOnCheckedChangeListener{group , id ->
            syrup_Extra = when(id){
                R.id.radioButtonChocolate -> "Chocolate"
                R.id.radioButtonHoney -> "Honey"
                R.id.radioButtonVanilla -> "Vanilla"
                else -> ""
            }

            toast(syrup_Extra)
            txtTotalHarga.text ="Total Harga: Rp." + hargaKopi.toString()

        }
        buttonConfirm.setOnClickListener{
                        if(switchHotIce.isChecked)
                        {
                            switch_Nilai ="Ice"
                        }
                        else
                        {
                            switch_Nilai = "Hot"
                        }

                        val pilihkopi = spinnerPilihCoffee.selectedItem.toString().trim()

                        var intent = Intent(this, Activity_Antrian_Pesanan::class.java)
                        intent.putExtra("Pesanan",switch_Nilai+" "+pilihkopi)
                        intent.putExtra("Harga",hargaKopi.toString())
                        intent.putExtra("Extra_Milk",extraMilk)
                        intent.putExtra("Extra_Coffee",extraCoffee)
                        if(syrup_Extra != "")
                        {
                            intent.putExtra("Syrup","Extra "+ syrup_Extra + " Syrup")
                            syrup_Extra=""
                        }
                        else
                        {
                            intent.putExtra("Syrup", "")
                        }

                        nomerAntrian+=1
                        intent.putExtra("NoAntrian",nomerAntrian.toString())
                        startActivity(intent)
                        extraMilk=""
                        extraCoffee=""
        }

    }

    fun onCheckBoxClicked(view: View) {
        view as CheckBox
        when(view.id) {
            R.id.checkBoxExtraSyrup ->
                if (view.isChecked) {
                    RadioGroupSyrupFlavour.visibility = View.VISIBLE
                    txtSyrup.visibility = View.VISIBLE
                    hargaKopi+=5000
                } else {
                    RadioGroupSyrupFlavour.visibility = View.INVISIBLE
                    txtSyrup.visibility = View.INVISIBLE
                    hargaKopi-=5000
                }
            R.id.checkBoxExtraMilk ->
                if (view.isChecked) {
                    hargaKopi+=5000
                    extraMilk+="Extra Milk"
                } else {
                    hargaKopi-=5000
                }

            R.id.checkBoxExtraCoffee ->
                if (view.isChecked) {
                    hargaKopi+=5000
                    extraCoffee+="Extra Coffee"
                } else
                {
                    hargaKopi-=5000
                }
        }
        txtTotalHarga.text ="Total Harga: Rp." + hargaKopi.toString()
    }
}
