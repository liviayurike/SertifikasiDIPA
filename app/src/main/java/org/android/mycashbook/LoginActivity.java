package org.android.mycashbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.android.mycashbook.R;

public class LoginActivity extends Activity {
    EditText username, password;
    Button login;
    TextView daftar;

    private org.android.mycashbook.DBHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper = new org.android.mycashbook.DBHelper(getApplicationContext());

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        daftar = findViewById(R.id.klikdaftar);

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), org.android.mycashbook.DaftarActivity.class);
                startActivity(intent);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!username.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
                    if (databaseHelper.checkUser(username.getText().toString().trim()
                            , password.getText().toString().trim())) {
                        Intent accountsIntent = new Intent(getApplicationContext(), org.android.mycashbook.MainActivity.class);
                        accountsIntent.putExtra("username", username.getText().toString().trim());
                        emptyInputEditText();
                        startActivity(accountsIntent);
                        finish();
                    } else {
                        // Snack Bar to show success message that record is wrong
                        Toast.makeText(getApplicationContext(), "Username atau password salah!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Username atau password harus diisi!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void emptyInputEditText() {
        username.setText(null);
        password.setText(null);
    }
}
