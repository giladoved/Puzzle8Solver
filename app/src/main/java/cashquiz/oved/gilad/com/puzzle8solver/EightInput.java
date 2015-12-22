package cashquiz.oved.gilad.com.puzzle8solver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EightInput extends AppCompatActivity {

    int[] inputs = {R.id.input81, R.id.input82, R.id.input83, R.id.input84, R.id.input85, R.id.input86, R.id.input87, R.id.input88, R.id.input89};
    int [] arr;
    EditText[] numbers;
    Button solveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eight_input);

        arr = new int[9];
        numbers = new EditText[9];
        for (int i = 0; i < 9; i++) {
            numbers[i] = (EditText) findViewById(inputs[i]);
        }

        solveBtn = (Button) findViewById(R.id.solve8Btn);
        solveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 9; i++) {
                    String input = numbers[i].getText().toString();
                    if (input.isEmpty()) {
                        input = "0";
                    }
                    arr[i] = Integer.parseInt(input);
                }

                int count = 0;
                int [][] b = new int[3][3];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        b[i][j] = arr[count++];
                    }
                }

                Board board = new Board(b);
                if (board.isValid()) {
                    if (board.isSolvable()) {
                        Intent i = new Intent(EightInput.this, MainActivity.class);
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
