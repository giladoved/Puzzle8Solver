package cashquiz.oved.gilad.com.puzzle8solver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FifteenInput extends AppCompatActivity {

    int[] inputs = {R.id.input151, R.id.input152, R.id.input153, R.id.input154, R.id.input155, R.id.input156, R.id.input157, R.id.input158, R.id.input159, R.id.input1510, R.id.input1511, R.id.input1512, R.id.input1513, R.id.input1514, R.id.input1515, R.id.input1516};
    int [] arr;
    EditText[] numbers;
    Button solveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifteen_input);

        arr = new int[16];
        numbers = new EditText[16];
        for (int i = 0; i < 16; i++) {
            numbers[i] = (EditText) findViewById(inputs[i]);
        }

        solveBtn = (Button) findViewById(R.id.solve15Btn);
        solveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 16; i++) {
                    String input = numbers[i].getText().toString();
                    if (input.isEmpty()) {
                        input = "0";
                    }
                    arr[i] = Integer.parseInt(input);
                }

                int count = 0;
                int [][] b = new int[4][4];
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        b[i][j] = arr[count++];
                    }
                }

                Board board = new Board(b);
                if (board.isValid()) {
                    if (board.isSolvable()) {
                        Intent i = new Intent(FifteenInput.this, MainActivity.class);
                        i.putExtra("arr", arr);
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(), "This puzzle is not solvable", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter valid numbers", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
