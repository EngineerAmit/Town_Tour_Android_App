package com.gujarex.towntour.Common.LoginSignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.gujarex.towntour.Databases.UserHelperClass;
import com.gujarex.towntour.R;

import java.util.concurrent.TimeUnit;

public class VerifyOTP extends AppCompatActivity {

    PinView pinFromUser;
    String codeBySystem;
//    TextView otpDescriptionText;
//    ImageView imageBtn;
//
//    String fullName, phoneNo, email, username, password, date, gender, whatToDO;
//
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);


        pinFromUser = findViewById(R.id.pin_view);
//        otpDescriptionText = findViewById(R.id.otp_description_text);
//        imageBtn = findViewById(R.id.Cancel_button);

        //intialize Firebase Auth
//        mAuth = FirebaseAuth.getInstance();

//        fullName = getIntent().getStringExtra("fullName");
//        email = getIntent().getStringExtra("email");
//        username = getIntent().getStringExtra("username");
//        password = getIntent().getStringExtra("password");
//        date = getIntent().getStringExtra("date");
//        gender = getIntent().getStringExtra("gender");
        String _phoneNo = getIntent().getStringExtra("phoneNo");
//        whatToDO = getIntent().getStringExtra("whatToDO");
//
//        otpDescriptionText.setText(getString(R.string.OTpassword) + phoneNo);
//

        sendVerificationCodeToUser(_phoneNo);

    }

    private void sendVerificationCodeToUser(String phoneNo) {
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(phoneNo)       // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(this)                 // Activity (for callback binding)
                .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                @Override
                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    codeBySystem = s;
                }

                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                    String code = phoneAuthCredential.getSmsCode();
                    if (code != null) {
                        pinFromUser.setText(code);
                        verifyCode(code);
                    }

                }

                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {
                    Toast.makeText(VerifyOTP.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            };

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeBySystem, code);
        signInWithPhoneAuthCredential(credential);

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(VerifyOTP.this, "Verification is completed ", Toast.LENGTH_SHORT).show();
                    }
                    else {
//                    else if (whatToDO.equals("createNewUser")) {
//                            storeNewUsersData();
////                            } else {
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(VerifyOTP.this, "Verification Not Completed! Try again.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

//    private void updateOldUsersData() {
//        Intent intent = new Intent(getApplicationContext(), SetNewPassword.class);
//        intent.putExtra("phoneNo", phoneNo);
//        startActivity(intent);
//        finish();
//    }

//    private void storeNewUsersData() {
//        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
//        DatabaseReference reference = rootNode.getReference("Users");
//
//        //reference.setValue("first record");
//
//        //Create helperclass reference and store data using firebase
//        UserHelperClass addNewUser = new UserHelperClass(fullName, username, email, phoneNo, password, date, gender);
//        reference.child(phoneNo).setValue(addNewUser);
//
////        startActivity(new Intent(getApplicationContext(), RetailerDashboard.class));
////        finish();
//    }

    public void callNextScreenFromOTP(View view) {
        String code = pinFromUser.getText().toString();
        if (!code.isEmpty()) {
            verifyCode(code);
        }
    }

    public void goTOHomeFromOTP (View view){
        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);

    }

}