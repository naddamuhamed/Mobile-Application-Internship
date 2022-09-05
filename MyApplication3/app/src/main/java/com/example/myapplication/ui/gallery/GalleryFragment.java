package com.example.myapplication.ui.gallery;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {
    TimePicker alarmTimePicker;
    PendingIntent pendingIntent;
    PendingIntent fdg;
    AlarmManager alarmManager;
    Button onbtn;
    Button offbtn;
    long time;
private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

    binding = FragmentGalleryBinding.inflate(inflater, container, false);
//        alarmTimePicker = (TimePicker) binding.timePicker ;
////    findViewById(R.id.timePicker);
////        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        onbtn=(Button)  binding.alarmonbtn;
////                findViewById(R.id.alarmonbtn);
//        offbtn=(Button) binding.alarmoffbtn;
//                findViewById(R.id.alarmoffbtn);
//        binding.
    View root = binding.getRoot();

//        final TextView textView = binding.textGallery;
//        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}