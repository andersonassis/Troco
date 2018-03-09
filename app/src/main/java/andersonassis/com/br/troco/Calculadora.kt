package andersonassis.com.br.troco

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class Calculadora : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora)

        if (savedInstanceState == null) {

          //  supportFragmentManager.beginTransaction().add(R.id.activity_calculadora, MainFragment()).commit()

        }
    }//fim do oncreate
}
