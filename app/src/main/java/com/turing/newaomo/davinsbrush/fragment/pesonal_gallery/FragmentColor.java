package com.turing.newaomo.davinsbrush.fragment.pesonal_gallery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.turing.newaomo.davinsbrush.R;

/**
 * Created by newao on 2018/2/18.
 */

public class FragmentColor extends Fragment {

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_gallery_fragment2,container,false);;
        return view;
    }
}