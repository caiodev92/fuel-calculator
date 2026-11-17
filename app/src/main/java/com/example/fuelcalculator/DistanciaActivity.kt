package com.example.fuelcalculator

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.MaterialToolbar


class DistanciaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_distancia)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Recuperar dados da tela anterior
        //FindViewById
        //Fazer calculo
        //Passar todos os dados para proxima tela

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar_distancia)
        setSupportActionBar(toolbar)

        // codigo que habilita o toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val precoCombustivel = intent.getDoubleExtra("PRECO_COMBUSTIVEL", 0.0)
        val consumoLitro = intent.getIntExtra("CONSUMO_LITRO", 0)

        val edtDistancia = findViewById<EditText>(R.id.edtDistancia)
        val btnDistanciaProximo = findViewById<Button>(R.id.btnDistanciaCalcular)

        btnDistanciaProximo.setOnClickListener {

            val distancia = edtDistancia.text.toString().toInt()
            val consumo = consumoLitro
            val precoLitro = precoCombustivel

            val litrosNecessarios = distancia / consumo
            val custoTotal = litrosNecessarios * precoLitro

            println("Distancia: $distancia")
            println("Consumo: $consumoLitro")
            println("Preço Combustivel: $precoCombustivel")
            println("Litros Necessarios: $litrosNecessarios")
            println("Custo Viagem: $custoTotal")

            val intent = Intent(this, ResultadoActivity::class.java)
            intent.putExtra("DISTANCIA_PRECO_LITRO", precoLitro)
            intent.putExtra("DISTANCIA_CONSUMO_LITRO", consumo)
            intent.putExtra("DISTANCIA_VALOR", distancia)
            intent.putExtra("DISTANCIA_CUSTO_TOTAL", custoTotal)

            startActivity(intent)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home ->{
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}