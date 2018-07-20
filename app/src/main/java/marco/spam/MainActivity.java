package marco.spam;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    TextView textView;
    String choice;



    public void setAnswer(String string) {

        sharedPreferences.edit().putString("choice", string).apply();
        choice = sharedPreferences.getString("choice", "");

        textView.setText(choice);

        if (choice.equals("YES")) {

            textView.setBackgroundColor(Color.parseColor("#FF0000"));

        }

        if (choice.equals("NO")) {

            textView.setBackgroundColor(Color.parseColor("#000000"));

        }

    }


    /***
     *
     *  THE  MENU
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
                case R.id.yes:

                    setAnswer("YES");

                return true;

                 case R.id.no:

                     setAnswer("NO");

                 return true;

            default:
                return false;
        }
    }



    /***
     *
     * Start
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = this.getSharedPreferences("marco.spam", Context.MODE_PRIVATE);

        choice = sharedPreferences.getString("choice", "");

        textView = findViewById(R.id.textView);

        if (choice.equals("")) {

            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_btn_speak_now)
                    .setTitle("Are you sure?")
                    .setMessage("Do you want to do this?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            setAnswer("YES");

                            Toast.makeText(MainActivity.this, "yes", Toast.LENGTH_SHORT).show();

                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            setAnswer("NO");

                            Toast.makeText(MainActivity.this, "no", Toast.LENGTH_SHORT).show();

                        }
                    })
                    .show();

            } else {

            setAnswer(choice);
        }

    }

}

