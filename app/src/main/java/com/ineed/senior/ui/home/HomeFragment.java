package com.ineed.senior.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.ineed.senior.LookAroundActivity;
import com.ineed.senior.Need;
import com.ineed.senior.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private AppBarConfiguration mAppBarConfiguration;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        // Create and assign Spinner and Button objects

        final NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        ImageButton addButton = root.findViewById(R.id.add_button);


        ArrayList<String> cityArray = new ArrayList<>();
        // Create and initialize an adapter to fill the CitySpinner. As the cityArray will be filled asnyc, we can assign the empty array here
        final ArrayAdapter<String> citySpinnerAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, cityArray);

        // Create and get an instance variable  and assign the FirebaseDatabase object to it
        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lookAround = new Intent(getActivity(), LookAroundActivity.class);
                startActivity(lookAround);
            }
        });

        return root;
    }
}