package com.example.friendfinderapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.friendfinderapp.API.APIRequestData;
import com.example.friendfinderapp.API.RetroServer;
import com.example.friendfinderapp.Activity.SignUp;
import com.example.friendfinderapp.Home;
import com.example.friendfinderapp.Model.ResponseModel;
import com.example.friendfinderapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignIn extends AppCompatActivity {
    private EditText etEmail,etPassword;
    private Button btnLogin;
    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        etEmail = findViewById(R.id.signinEmail);
        etPassword = findViewById(R.id.signinPassword);
        btnLogin = findViewById(R.id.btn_sign_in);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();
                login(email, password);
            }
        });

        TextView link_sign_up = findViewById(R.id.link_sign_up);
        link_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
            }
        });
    }
    private void login(String email, String password){
        APIRequestData apiData = RetroServer.konekRetro().create(APIRequestData.class);
        Call<ResponseModel> Signin = apiData.resLogin(email,password);
        Signin.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                String pesan = response.body().getPesan();
                Toast.makeText(SignIn.this, pesan, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(SignIn.this, "Gagal Login "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}