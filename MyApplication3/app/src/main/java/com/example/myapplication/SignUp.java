package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;
import android.os.Bundle;
import 	android.text.TextUtils;
import 	android.util.Patterns;
import java.util.regex.Matcher;

import android.widget.Button;
import android.view.View;
import java.util.regex.Pattern;
public class SignUp extends AppCompatActivity {

    Button backButton;
    EditText firstName;
    EditText lastName;
    EditText address;
    EditText email;
    EditText pw;
    EditText conpw;
    EditText phone;
    Button register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        firstName = findViewById(R.id.fn);
        lastName = findViewById(R.id.ln);
        address = findViewById(R.id.address);
        email = findViewById(R.id.email);
        register = findViewById(R.id.reg);
        phone = findViewById(R.id.phone);
        pw = findViewById(R.id.pw);
        conpw = findViewById(R.id.conpw);
        backButton = findViewById(R.id.button2);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view){
               checkDataEntered();
           }
        });
    }
    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
    public boolean isValid(String editTextInput){
        boolean isInputValid = false;

        String expression = "^([a-zA-Z ]*)$";
        CharSequence inputStr = editTextInput;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isInputValid = true;
        }
        return isInputValid ;
    }

//     (/^[0-9]+$/)

    public boolean isValid2(String editTextInput){
        boolean isInputValid = false;

        String expression = "(^[0-9]+$)";
        CharSequence inputStr = editTextInput;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isInputValid = true;
        }
        return isInputValid ;
    }

    void checkDataEntered(){
        if (isEmpty(firstName)) {
        firstName.setError("You must enter first name to register!");
    }

        if (isEmpty(lastName)) {
            lastName.setError("You must enter last name to register!");
        }

        if (isEmpty(email)) {
            email.setError("You must enter email to register!");
        }

        if (isEmpty(pw)) {
            pw.setError("You must enter a password to register!");
        }

        if (isEmpty(conpw)) {
            conpw.setError("You must enter password to register!");
        }

        if (isEmpty(address)) {
            address.setError("You must enter address to register!");
        }

        if (isEmpty(phone)) {
            phone.setError("You must enter phone number to register!");
        }
        if (isEmail(email) == false) {
            email.setError("Enter valid email!");
        }
        if(!isValid(firstName.getText().toString().trim())){
            firstName.setError("Can contain only text");
//                        editText.setText("");
        }
        if(!isValid(lastName.getText().toString().trim())){
            lastName.setError("Can contain only text");
//                        editText.setText("");
        }
        if(!isValid2(phone.getText().toString().trim())){
            phone.setError("Can contain only numbers");
//                        editText.setText("");
        }
        if(!isValid(address.getText().toString().trim())){
            address.setError("Can contain only text");
//                        editText.setText("");
        }

        if( !pw.getText().toString().equals(conpw.getText().toString())){
            conpw.setError("Passwords must be equal");
        }

    }

}