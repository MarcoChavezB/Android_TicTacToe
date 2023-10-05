package com.example.android_tictactoe;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity{

    int[] buttonId = {
            R.id.uno, R.id.dos, R.id.tres, R.id.cuatro,
            R.id.cinco, R.id.seis, R.id.siete, R.id.ocho, R.id.nueve
    };

    String[] board = {
            "", "", "",
            "", "", "",
            "", "", ""
    };

    int countWinner = 0;

    boolean turno = true;
    Button btnReset;
    TextView ganador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        btnReset = findViewById(R.id.reset);
        ganador = findViewById(R.id.ganador);


        for(int buttonStep : buttonId){
            final Button button = findViewById(buttonStep);
            button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    turno(v);

                    if (checkWinner(board, "X")) {
                        ganador.setText("Ganador: X");
                        countWinner ++;
                        if(mostrarData()){
                            showCustomDialog();
                            clearAllButtons();
                        }
                    } else if (checkWinner(board, "O")) {
                        ganador.setText("Ganador: O");
                        countWinner ++;
                        if(mostrarData()){
                            showCustomDialog();
                            clearAllButtons();
                        }
                    }
                }
            });
        }

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllButtons();
            }
        });
    }

    public void turno (View view){
        Button b = (Button) view;

        if (turno){
            if (b.getText().toString().equals("X") || b.getText().toString().equals("O")){
                return;
            }

            b.setText("X");
            int buttonIndex = getButtonIndex(b.getId());
            board[buttonIndex] = "X";
            turno = false;
        } else {
            if (b.getText().toString().equals("X") || b.getText().toString().equals("O")){
                return;
            }
            b.setText("O");
            int buttonIndex = getButtonIndex(b.getId());
            board[buttonIndex] = "O";
            turno = true;
        }

        if (checkWinner(board, "X")) {
            ganador.setText("Ganador X");
        } else if (checkWinner(board, "O")) {
            ganador.setText("Ganador O");
        }
    }

    private int getButtonIndex(int buttonId) {
        for (int i = 0; i < this.buttonId.length; i++) {
            if (buttonId == this.buttonId[i]) {
                return i;
            }
        }
        return -1;
    }



    private void clearAllButtons(){
        for(int buttonVoid : buttonId){
            Button button = findViewById(buttonVoid);
            button.setText("");
        }
        for(int i = 0; i < board.length; i++){
            board[i] = "";
        }
        ganador.setText("");
    }

    public boolean checkWinner(String[] board, String player){
        int[][] posicionesGanadoras = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}
        };

        for (int[] combinacion : posicionesGanadoras){
            int c1 = combinacion[0];
            int c2 = combinacion[1];
            int c3 = combinacion[2];

            if (board[c1].equals(player) && board[c2].equals(player) && board[c3].equals(player)){
                return true;
            }
        }
        return false;
    }


    public void showCustomDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.activity_data, null);

        Button modalCloseButton = dialogView.findViewById(R.id.modalCloseButton);


        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();

        modalCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    public boolean mostrarData(){
        if (countWinner == 5){
            return true;
        }
        return false;
    }
}
