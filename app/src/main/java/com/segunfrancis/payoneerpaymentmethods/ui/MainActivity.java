package com.segunfrancis.payoneerpaymentmethods.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.segunfrancis.payoneerpaymentmethods.R;
import com.segunfrancis.payoneerpaymentmethods.data.remote.model.ApplicableItem;
import com.segunfrancis.payoneerpaymentmethods.databinding.ActivityMainBinding;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_main);
        navController = Objects.requireNonNull(navHostFragment).getNavController();
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            String title;
            if (destination.getId() == R.id.applicableFragment) {
                ApplicableItem item = Objects.requireNonNull(arguments).getParcelable("applicableItem");
                title = item.getLabel();
                Objects.requireNonNull(getSupportActionBar()).setTitle(title);
            } else {
                Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.app_name);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }
}
