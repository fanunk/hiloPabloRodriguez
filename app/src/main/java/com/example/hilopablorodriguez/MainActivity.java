package com.example.hilopablorodriguez;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etCantidad;
    Button btnGenerar;
    ProgressBar pbCargando;
    TextView tvRespuesta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etCantidad=findViewById(R.id.etCantidad);
        btnGenerar=findViewById(R.id.btnGenerar);
        pbCargando=findViewById(R.id.pbCargar);
        tvRespuesta=findViewById(R.id.tvRespuesta);
        btnGenerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pbCargando.setVisibility(View.VISIBLE);
                tvRespuesta.setText("");
                int numero = Integer.parseInt( etCantidad.getText().toString() );
                HiloSecundario hilo1 = new HiloSecundario(numero);
                hilo1.start();
            }
        });
    }
    private String Generar(int num) {

        int i, n = 4, cont = 2;
        String cad = "";
        if (num > 2)
        {
            SystemClock.sleep(2000);
            cad = "2 - 3";
            while (cont < num)
            {
                i = 2;
                while (i <= n)
                {
                    if (i == n)
                    {
                        cad = cad + " - " + n;
                        cont = cont + 1;
                        SystemClock.sleep(1000);
                    }
                    else
                    {
                        if (n % i == 0)
                        {
                            i = n;
                        }
                    }
                    i = i + 1;
                }
                n = n + 1;

            }
            return cad;
        }
        else {
            if (num > 0)
            {
                if (num == 1)
                {
                    SystemClock.sleep(1000);
                    return "2";

                }
                else
                {
                    SystemClock.sleep(2000);
                    return "2 3";
                }
            }
            else {
                return "no ponga numeros negativos";
            }
        }

    }
    class HiloSecundario extends Thread{

        int numero;
        String respuesta;

        public HiloSecundario(int numero) {
            this.numero = numero;
        }

        @Override
        public void run() {
            try{
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        respuesta = Generar(numero);
                        tvRespuesta.append("primeros "+numero+" numeros primos son: ");
                        tvRespuesta.append(""+respuesta);
                        Toast.makeText(MainActivity.this,"fin",Toast.LENGTH_LONG).show();
                        pbCargando.setVisibility(View.INVISIBLE);
                    }
                });

            }catch (Exception ex){
                Toast.makeText(MainActivity.this,"error "+ex.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
    }
}
