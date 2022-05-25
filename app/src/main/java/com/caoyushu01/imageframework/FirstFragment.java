package com.caoyushu01.imageframework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.caoyushu01.imageframe.Glide;
import com.caoyushu01.imageframework.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    private String[] pics = {
            "https://i-1-lanrentuku.52tup.com/2020/11/11/e20dd75a-56d3-4fd9-b741-197abc41361b.jpg?imageView2/2/w/1024/",
            "https://i-1-lanrentuku.52tup.com/2020/12/31/4891a6e2-084b-4d33-aa0d-905f5de7eb24.jpg?imageView2/2/w/1024/",
            "https://i-1-lanrentuku.52tup.com/2020/10/28/e7ea56aa-9ff9-4b9a-99a8-8d205cdf4888.png?imageView2/2/w/1024/",
            "https://i-1-lanrentuku.52tup.com/2020/12/31/f9833940-2c69-4231-b666-fdb7daa1de1b.jpg?imageView2/2/w/1024/",
            "https://i-1-lanrentuku.52tup.com/2020/12/31/7a76908f-09f2-4e8d-b017-f8ccd1a6046b.jpg?imageView2/2/w/1024/",
            "https://i-1-lanrentuku.52tup.com/2020/12/31/b19e9d2a-767a-43e7-8f5a-01baae4973b1.jpg?imageView2/2/w/1024/",
            "https://i-1-lanrentuku.52tup.com/2020/12/31/b1c7076e-7913-4c37-ab68-247404120c5a.jpg?imageView2/2/w/1024/",
            "https://i-1-lanrentuku.52tup.com/2020/12/30/05dcc169-4817-4173-9172-c522d5a07623.jpg?imageView2/2/w/1024/",
            "https://i-1-lanrentuku.52tup.com/2020/12/30/1ce8535d-1724-4c7d-ab09-f891fb34468f.jpg?imageView2/2/w/1024/"
    };

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "开始加载图片", Toast.LENGTH_SHORT).show();
                Glide.with(getContext()).load(pics[0])
                        .placeHolder(R.drawable.ic_launcher_background)
                        .into(binding.imgSingle);
            }
        });
        binding.buttonMulti.setOnClickListener((view1) -> {
            Glide.with(getContext()).load(pics[1])
                    .placeHolder(R.drawable.ic_launcher_background)
                    .into(binding.imgMulti1);
            Glide.with(getContext()).load(pics[2])
                    .placeHolder(R.drawable.ic_launcher_background)
                    .into(binding.imgMulti2);
            Glide.with(getContext()).load(pics[3])
                    .placeHolder(R.drawable.ic_launcher_background)
                    .into(binding.imgMulti3);
            Glide.with(getContext()).load(pics[4])
                    .placeHolder(R.drawable.ic_launcher_background)
                    .into(binding.imgMulti4);
            Glide.with(getContext()).load(pics[5])
                    .placeHolder(R.drawable.ic_launcher_background)
                    .into(binding.imgMulti5);
            Glide.with(getContext()).load(pics[6])
                    .placeHolder(R.drawable.ic_launcher_background)
                    .into(binding.imgMulti6);
            Glide.with(getContext()).load(pics[7])
                    .placeHolder(R.drawable.ic_launcher_background)
                    .into(binding.imgMulti7);
            Glide.with(getContext()).load(pics[8])
                    .placeHolder(R.drawable.ic_launcher_background)
                    .into(binding.imgMulti8);

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}