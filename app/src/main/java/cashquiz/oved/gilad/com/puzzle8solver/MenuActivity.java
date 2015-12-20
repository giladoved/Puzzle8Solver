package cashquiz.oved.gilad.com.puzzle8solver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    Button eightBtn;
    Button fifteenBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        eightBtn = (Button) findViewById(R.id.eightBtn);
        fifteenBtn = (Button) findViewById(R.id.fifteenBtn);

        eightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this, EightInput.class);
                startActivity(i);
            }
        });

        fifteenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this, FifteenInput.class);
                startActivity(i);
            }
        });
    }
}
