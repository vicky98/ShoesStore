package shoesstore.com.shoesstore;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;

public class Main extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private static final int RC_SIGN_IN = 2;
    private static final String TAG = "ShoesStore/Main";

    // onCreate - the app is open for the first time in ram
    // onStart - the app is starting in ram (after onCreate/onRestart)
    // onResume - when you are going to another activity, the previous one is resumed
    // onStop - when you leave the app but it is not removed from the ram
    // onRestart - when you reopen the app after putting it on onStop
    // onDestroy - when the app is removed from the ram

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate");

        float f = (float) 0.5;

        ImageView iv = (ImageView) findViewById(R.id.main_bg);
        iv.setAlpha(f);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            String email = firebaseAuth.getCurrentUser().getEmail();
            String uid = firebaseAuth.getCurrentUser().getUid();

            goToShop(email, uid);

            Log.d(TAG, "User already logged in >> User email: " + email + " User UID: " + uid);
        } else {
            startActivityForResult(AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setProviders(
                            AuthUI.EMAIL_PROVIDER,
                            AuthUI.GOOGLE_PROVIDER)
                    .build(), RC_SIGN_IN);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                String userEmail = firebaseAuth.getCurrentUser().getEmail();
                String userUid = firebaseAuth.getCurrentUser().getUid();

                Log.d(TAG, "User logged in >> User email: " + userEmail + " UserUid: " + userUid);

                goToShop(userEmail, userUid);

                /*Intent intent = new Intent(this, Dummy.class);

                Bundle bundle = new Bundle();
                bundle.putString("userEmail", userEmail);
                bundle.putString("userUID", userUid);
                startActivity(intent, bundle);*/
            } else {
                Log.d(TAG, "Log in unsuccessful");
                Toast.makeText(this, "Log in unsuccessful.", Toast.LENGTH_SHORT);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy");
    }

    public void goToShop(String email, String UID) {
        Intent intent = new Intent(this, Dummy.class);
        intent.putExtra("userEmail", email);
        intent.putExtra("userUID", UID);

        startActivity(intent);
    }
}
