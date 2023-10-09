package com.example.android_tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    Button btnSiguiente, partidas, listo;
    TextView input, txtError;
    ConstraintLayout numeroPartidas;

    int nPartidas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = findViewById(R.id.textoAnim);

        btnSiguiente = findViewById(R.id.btnsiguiente);
        partidas = findViewById(R.id.seleccion);
        numeroPartidas = findViewById(R.id.contenedorNumero);
        listo = findViewById(R.id.siguiente);
        input = findViewById(R.id.numeroPartidas);
        txtError = findViewById(R.id.txtError);



        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Dashboard.class);
                startActivity(intent);
            }
        });

        listo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    nPartidas = Integer.parseInt(input.getText().toString());
                    if (nPartidas == 0) {
                        txtError.setVisibility(View.VISIBLE);

                        new CountDownTimer(4000, 100) {
                            @Override
                            public void onTick(long l) {

                            }

                            public void onFinish() {
                                txtError.setVisibility(View.INVISIBLE);
                            }
                        }.start();

                        return;
                    }

                    Intent intent = new Intent(MainActivity.this, Dashboard.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("nPartidas", nPartidas);
                    intent.putExtras(bundle);
                    startActivity(intent);

                } catch (NumberFormatException e) {
                    txtError.setVisibility(View.VISIBLE);

                    new CountDownTimer(4000, 100) {
                        @Override
                        public void onTick(long l) {

                        }

                        public void onFinish() {
                            txtError.setVisibility(View.INVISIBLE);
                        }
                    }.start();
                }
            }
        });




        Animation salida = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        Animation entrada = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        partidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numeroPartidas.setVisibility(View.VISIBLE);
                btnSiguiente.setVisibility(View.INVISIBLE);
                partidas.setVisibility(View.INVISIBLE);

            }
        });
        entrada.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                textView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        salida.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                textView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        textView.startAnimation(entrada);
    }


    /*
        new CountDownTimer(4000, 1000) {
            @Override
            public void onTick(long l) {

            }
            public void onFinish() {
                Intent intent = new Intent(MainActivity.this, Dashboard.class);
                startActivity(intent);
            }
        }.start();
*/
}
