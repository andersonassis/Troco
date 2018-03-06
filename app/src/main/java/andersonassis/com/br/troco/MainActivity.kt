package andersonassis.com.br.troco

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

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

    //arrays troco
    var notas = arrayOf(100, 50, 20, 10, 5, 2, 1)
    var centavos = arrayOf(50, 25, 10, 5, 1)


    // metodo on Create
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // click botão calcular
        calcular.setOnClickListener {
            calcula()
        }

        //botão limpar limpa todas as variaveis
        btn_limpar.setOnClickListener {
            conta = ""
            pago = ""
            troco = 0.0
            result = ""
            resultCent = ""
            valor_a_pagar.setText("")
            valor_pago.setText("")
            textoTroco.setText("")//text view
            texto_centavos.setText("")//text view
            textomoedas.setText("")
        }

    }//fim do oncreate


    //função para calcular
    fun calcula() {
        //pegando os valores nos editText em string
        conta = valor_a_pagar.text.toString()
        pago = valor_pago.text.toString()
        if (conta != "" && pago != "") {
            //converte as strings em double
            var valorConta: Double = conta.toDouble()
            var valorPago: Double = pago.toDouble()

            //calculo do troco
            if (valorPago < valorConta) {
                Toast.makeText(this@MainActivity, "Valor insuficiente para o pagamento", Toast.LENGTH_SHORT).show()
            } else {

                //valor do troco mostra no texview
                troco = valorConta - valorPago
                val formatado = String.format("%.2f", troco)//formata com casas decimais
                textoTroco.setText("VALOR DE TROCO É:  " + formatado + " R$")

                //Notas do troco
                // definindo as notas e moedas  para o troco em inteiro
                try {
                    vlr = troco.toInt()//convertendo para inteiro
                    while (vlr != 0) {
                        ct = vlr / notas[i] //calcula a qtd de notas
                        if (ct != 0) {
                            result += ("$ct" + " NOTAS(s) de R$" + notas[i] + "\n")
                            vlr = vlr % notas[i]
                        }
                        i = i + 1; // próxima nota
                        textomoedas.setText(result)//text view
                    }

                    // valores centavos
                    vlr = Math.round((troco - troco.toInt()) * 100).toInt()
                    i = 0
                    while (vlr != 0) {
                        ct = vlr / centavos[i]
                        if (ct != 0) {
                            resultCent += ("$ct" + " MOEDAS(s) de " + centavos[i] + "Centavos(s) \n")
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


    }//FIM DA FUNÇÃO CALCULAR


}//fim da classe Mainactivity