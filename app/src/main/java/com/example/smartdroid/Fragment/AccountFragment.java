package com.example.smartdroid.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.smartdroid.Activity.OnBoardingActivity;
import com.example.smartdroid.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.synnapps.carouselview.ImageListener;

public class AccountFragment extends Fragment {

    Button signOut;
    FirebaseAuth mAuth;
    GoogleSignInClient googleSignInClient;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_account, container, false);
        signOut = rootView.findViewById(R.id.Signout);
        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("787951658726-29raq743ktn3a7n0c71v222ian5gs41o.apps.googleusercontent.com")
                .requestEmail()
                .build();
        googleSignInClient =  GoogleSignIn.getClient(getContext(), gso);

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                googleSignInClient.signOut();
                startActivity(new Intent(getActivity(), OnBoardingActivity.class));
                Toast.makeText(getContext(), "Signed out", Toast.LENGTH_SHORT).show();
                getActivity().finish();

            }
        });
        return rootView;

    }
}