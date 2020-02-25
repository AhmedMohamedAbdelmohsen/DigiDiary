package com.example.digidiary;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.digidiary.databinding.FragmentSplashBinding;
import com.example.digidiary.pojo.SqliteHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

import static com.example.digidiary.AppActivity.sqLiteOpenHelper;


public class SplashFragment extends Fragment {

    private Animation topAnimation, bottomAnimation;
    private FragmentSplashBinding binding;

    public SplashFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        topAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.bottom_animation);
        binding.dairyIcon.setAnimation(topAnimation);
        binding.tvDigiDiary.setAnimation(bottomAnimation);
        binding.tvCollectionNotes.setAnimation(bottomAnimation);
        sqLiteOpenHelper = new SqliteHelper(getActivity(), "DiariesDB.sqlite", null, 1);

        try {
            sqLiteOpenHelper.createTable();
            Toast.makeText(getActivity(), "Created DataBase", Toast.LENGTH_SHORT).show();
            BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottom_nav_bar);
            bottomNavigationView.setVisibility(view.GONE);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    NavDirections action =
                            SplashFragmentDirections.actionSplashFragmentToHomeFragment22();
                    Navigation.findNavController(view).navigate(action);
                }
            }, 4000);
        } catch (Exception e) {
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();

        }

    }
}
