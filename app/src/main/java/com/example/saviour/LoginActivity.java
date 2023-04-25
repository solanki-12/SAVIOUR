package com.example.saviour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class LoginActivity extends Activity {

    private EditText username, password;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    ImageView google_btn;
    private ProgressBar progressBar;

    MaterialButton registBtn;

    ImageView fb_Btn;
    private FirebaseAuth authProfile;

    CallbackManager callbackManager;

    private static final String TAG = "LoginActivity";



    // Check if the user is already logged In. In such case, Straightaway take the user to the User's Profile
    @Override
    protected void onStart() {
        super.onStart();
        if(authProfile.getCurrentUser() != null)
        {
            Toast.makeText(LoginActivity.this, "Already Logged In", Toast.LENGTH_SHORT).show();
            // Start the UserProfileActivity
            startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
            finish(); // close login activity
        }else {
            Toast.makeText(LoginActivity.this, "You can login now", Toast.LENGTH_SHORT).show();
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //Register Button redirect
        registBtn = findViewById(R.id.button_register);
        registBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });



        //Login using username and password from firebase;
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressbar);

        authProfile = FirebaseAuth.getInstance();

        //show hide password using hide icon
        ImageView imageViewShowHidePwd = findViewById(R.id.imageView_show_hide_pwd);
        imageViewShowHidePwd.setImageResource(R.drawable.ic_hide_pwd);
        imageViewShowHidePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                    //if password is visible then hide it
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    //change icon
                    imageViewShowHidePwd.setImageResource(R.drawable.ic_hide_pwd);
                }else {
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    imageViewShowHidePwd.setImageResource(R.drawable.ic_show_pwd);
                }
            }
        });





        //Login User
        Button buttonLogin = findViewById(R.id.login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textEmail = username.getText().toString();
                String textPass = password.getText().toString();

                if(TextUtils.isEmpty(textEmail))
                {
                    Toast.makeText(LoginActivity.this,"Please enter your email",Toast.LENGTH_SHORT).show();
                    username.setError("Email is required");
                    username.requestFocus();
                }else if(!Patterns.EMAIL_ADDRESS.matcher(textEmail).matches()){
                    Toast.makeText(LoginActivity.this,"Please re-enter your email",Toast.LENGTH_SHORT).show();
                    username.setError("Valid email is required");
                    username.requestFocus();
                } else if(TextUtils.isEmpty(textPass))
                {
                    Toast.makeText(LoginActivity.this,"Please enter your password",Toast.LENGTH_SHORT).show();
                    username.setError("Password is required");
                    username.requestFocus();
                }
                else{
                    progressBar.setVisibility(View.VISIBLE);
                    loginUser(textEmail,textPass);

                }
            }

            private void loginUser(String email, String pass) {
                authProfile.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            //Get instance of the current user
                            FirebaseUser firebaseUser = authProfile.getCurrentUser();

                            //check if email is verified before user can access their profile
                            if(firebaseUser.isEmailVerified()){

                                Toast.makeText(LoginActivity.this, "You are logged in now", Toast.LENGTH_SHORT).show();

                                //open user profile
                                startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                                finish(); // close login activity

                            }
//                            if(email.equals("solankianuj12@gmail.com") && pass.equals("solanki123!"))
//                            {
//                                Toast.makeText(LoginActivity.this, "Admin logged in now", Toast.LENGTH_SHORT).show();
//
//                                startActivity(new Intent(LoginActivity.this, AdminDashboard.class));
//                                finish();
//
//                            }

                            else {
                                firebaseUser.sendEmailVerification();
                                authProfile.signOut();
                                showAlertDialog();
                            }

                        } else{
                            try{
                                throw task.getException();
                            }catch (FirebaseAuthInvalidUserException e)
                            {
                                username.setError("User does not exists or is no longer valid. Please register again");
                                username.requestFocus();
                            }catch (FirebaseAuthInvalidCredentialsException e)
                            {
                                username.setError("Invalid credentials. Kindly, check and re-enter.");
                                username.requestFocus();
                            }catch (Exception e)
                            {
                                Log.e(TAG, e.getMessage());
                                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                            }

                        }
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }

        });


        // Facebook button services
        fb_Btn = findViewById(R.id.fb_btn);
        fb_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // login to facebook
                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile"));
            }
        });

        callbackManager = CallbackManager.Factory.create();
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if(accessToken!=null && !accessToken.isExpired())
        {
            startActivity(new Intent(LoginActivity.this,DashboardActivity.class));
            finish();
        }

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                        finish();
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });





//        // Google button services
//        google_btn = findViewById(R.id.google_btn);
//
//        google_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
//
//            }
//        });


       // Google button services
        google_btn = findViewById(R.id.google_btn);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acc = GoogleSignIn.getLastSignedInAccount(this);
        if(acc!=null)
        {
            navigateToSecondActivity();
        }

        google_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });


    }

    private void signIn() {
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent, 1000);
    }

    private void navigateToSecondActivity() {
        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

    private void showAlertDialog() {
        // setup the Alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle("Email Not verified ");
        builder.setMessage("Please verify your email now, you can not login without email verification");

        //Open Email Apps if User clicks/taps continue button
        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_APP_EMAIL);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // To email app in new window and not within our app
                startActivity(intent);

            }
        });

        // Create AlertDialog
        AlertDialog alertDialog = builder.create();

        // show the AlertDialog
        alertDialog.show();
    }
}