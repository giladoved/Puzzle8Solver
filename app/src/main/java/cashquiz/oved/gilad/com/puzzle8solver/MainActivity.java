package cashquiz.oved.gilad.com.puzzle8solver;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int[][] board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        int[] arr = extras.getIntArray("arr");
        if (arr.length == 9) {
            int count = 0;
            board = new int[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    board[i][j] = arr[count++];
                }
            }
        } else if (arr.length == 16) {
            int count = 0;
            board = new int[4][4];
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    board[i][j] = arr[count++];
                }
            }
        }

        Board initial = new Board(board);
        Solver solver = new Solver(initial);
        ArrayList<Board> solution = solver.solution();

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new CustomPagerAdapter(this, solution));
    }
}
