package andersonassis.com.br.troco

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Intent
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.view.WindowManager


class MainActivity : AppCompatActivity() {
    //declaração das variaveis
    var conta: String = ""
    var pago: String = ""
    var troco: Double = 0.0
    var result: String = ""
    var resultCent: String = ""
    var i: Int = 0
    var vlr: Int = 0
    var ct: Int? = 0
    var valorCalculadora:String =""
    //arrays troco
    var notas = arrayOf(100, 50, 20, 10, 5, 2, 1)
    var centavos = arrayOf(50, 25, 10, 5, 1)


    // metodo on Create
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        try {
            valorCalculadora = intent.getStringExtra("valor")

            if (!valorCalculadora.equals(""))
                {
                  valor_a_pagar.setText(valorCalculadora)

                }
        }catch (e: Exception )
           {
              e.printStackTrace()
           }

        // click botão calcular
        calcular.setOnClickListener {
            calcula()
            escondeTeclado()
        }

        //botão limpar limpa todas as variaveis
        btn_limpar.setOnClickListener {
            conta = ""
            pago = ""
            troco = 0.0
            vlr = 0
            result = ""
            resultCent = ""
            ct = 0
            i = 0
            valor_a_pagar.setText("")
            valor_pago.setText("")
            textoTroco.setText("")//text view
            texto_centavos.setText("")//text view
            textomoedas.setText("")
            valorCalculadora =""

        }

    }//fim do oncreate


    //função para calcular
    fun calcula() {
        if (!valorCalculadora.equals("")){
            // valorCalculadora = valor_a_pagar.text.toString()
             pago = valor_pago.text.toString()
            if (valorCalculadora != "" && pago != "") {
                //converte as strings em double
                var valorConta: Double = valorCalculadora.toDouble()
                var valorPago: Double = pago.toDouble()
                troco = valorConta - valorPago
                val formatado = String.format("%.2f", troco)//formata com casas decimais
                //calculo do troco
                if (valorPago < valorConta) {

                    Toast.makeText(this@MainActivity, "Valor insuficiente para o pagamento "+ "falta "+formatado+" R$", Toast.LENGTH_SHORT).show()
                } else {
                    //valor do troco mostra no texview
                    textoTroco.setText("VALOR DE TROCO É:  " + formatado + " R$")

                    //Notas do troco
                    // definindo as notas e moedas  para o troco em inteiro
                    try {
                        vlr = troco.toInt()//convertendo para inteiro
                        while (vlr != 0) {
                            ct = vlr / notas[i] //calcula a qtd de notas
                            if (ct != 0) {
                                if (i == 6) { // AQUI moedas de 1 real

                                    result += ("$ct" + " MOEDA de R$ " + notas[i] + "\n")
                                } else {

                                    result += ("$ct" + " NOTA(s) de R$ " + notas[i] + "\n")
                                }

                                vlr = vlr % notas[i]
                            }
                            i = i + 1; // próxima nota
                            textomoedas.setText(result)//text view
                        }

                        // valores centavos fração
                        vlr = Math.round((troco - troco.toInt()) * 100).toInt()
                        i = 0
                        while (vlr != 0) {
                            ct = vlr / centavos[i]
                            if (ct != 0) {
                                resultCent += ("$ct" + " MOEDA(s) de " + centavos[i] + " Centavos(s) \n")
                                vlr = vlr % centavos[i]

                            }
                            i = i + 1; // próxima nota
                            texto_centavos.setText(resultCent)//text view
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                }

            } else {
                Toast.makeText(this@MainActivity, "NÃO É PERMITIDO CAMPOS EM BRANCO", Toast.LENGTH_SHORT).show();
            }


        }else {

            //pegando os valores nos editText em string
            conta = valor_a_pagar.text.toString()
            pago = valor_pago.text.toString()
            if (conta != "" && pago != "") {
                //converte as strings em double
                var valorConta: Double = conta.toDouble()
                var valorPago: Double = pago.toDouble()
                troco = valorConta - valorPago
                val formatado = String.format("%.2f", troco)//formata com casas decimais
                //calculo do troco
                if (valorPago < valorConta) {
                    Toast.makeText(this@MainActivity, "Valor insuficiente para o pagamento "+ "falta "+formatado+" R$", Toast.LENGTH_SHORT).show()
                } else {
                    //valor do troco mostra no texview
                    textoTroco.setText("VALOR DE TROCO É:  " + formatado + " R$")

                    //Notas do troco
                    // definindo as notas e moedas  para o troco em inteiro
                    try {
                        vlr = troco.toInt()//convertendo para inteiro
                        while (vlr != 0) {
                            ct = vlr / notas[i] //calcula a qtd de notas
                            if (ct != 0) {
                                if (i == 6) { // AQUI moedas de 1 real

                                    result += ("$ct" + " MOEDA de R$ " + notas[i] + "\n")
                                } else {

                                    result += ("$ct" + " NOTA(s) de R$ " + notas[i] + "\n")
                                }

                                vlr = vlr % notas[i]
                            }
                            i = i + 1; // próxima nota
                            textomoedas.setText(result)//text view
                        }

                        // valores centavos fração
                        vlr = Math.round((troco - troco.toInt()) * 100).toInt()
                        i = 0
                        while (vlr != 0) {
                            ct = vlr / centavos[i]
                            if (ct != 0) {
                                resultCent += ("$ct" + " MOEDA(s) de " + centavos[i] + " Centavos(s) \n")
                                vlr = vlr % centavos[i]

                            }
                            i = i + 1; // próxima nota
                            texto_centavos.setText(resultCent)//text view
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                }

            } else {
                Toast.makeText(this@MainActivity, "NÃO É PERMITIDO CAMPOS EM BRANCO", Toast.LENGTH_SHORT).show();
            }

        }//fim do else intent
    }//FIM DA FUNÇÃO CALCULAR


     //função para esconder o teclado no click do calcular
    @RequiresApi(Build.VERSION_CODES.CUPCAKE)
    fun escondeTeclado() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(valor_pago.getWindowToken(), 0)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

    }



    // INICIO DOS MENU calculadora
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.calc) { // CLICK DO BOTÃO CALCULADORA
            val intent = Intent(applicationContext, Calculadora::class.java)
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }//fim do menu sobre


}//fim da classe Mainactivity
