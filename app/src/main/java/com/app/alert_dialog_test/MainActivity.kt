package com.app.alert_dialog_test

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showCustomDialogBox()
    }

    //função para mostrar o banner chamar
    private fun showCustomDialogBox() {
        val dialog = Dialog(this)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_custom_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val btnAttLater: Button = dialog.findViewById(R.id.btnAttLater)
        val btnAttNow: Button = dialog.findViewById(R.id.btnAttNow)


        btnAttLater.setOnClickListener {
            dialog.dismiss()
        }

        btnAttNow.setOnClickListener {
            dialog.dismiss()
            openPlayStore()
        }

        //variavel para verificar o retorno da api e mostrar o dialogo
        var apiReturn = callApiWhiteList()

        if (apiReturn) {
            dialog.show()
        }

    }

    //chamada da api
    private fun callApiWhiteList(): Boolean {
        //Logica da chamada da api
        val resp = true

        return resp
    }

    private fun openPlayStore() {
        val url: String =
            "https://play.google.com/store/apps/details?id=resource.br.com.tribanco.SuperCompras&hl=pt_BR&gl=US"
        //necessario o manifest: <uses-permission android:name="android.permission.INTERNET" />
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse(url))

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "Play Store não está disponível.", Toast.LENGTH_SHORT).show();
        }
    }
}