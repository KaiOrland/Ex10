package com.example.madaim.ex10;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Madaim on 03/01/2017.
 */

public class FragB extends Fragment {
    TextView tvCounter;
    DataReporter listener;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_b, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.tvCounter = (TextView) view.findViewById(R.id.textView);

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        try{
            this.listener = (FragB.DataReporter) getActivity();
        }
        catch(ClassCastException e){
            throw new ClassCastException("the class " + getActivity().getClass().getName() + " must implement the interface 'ClickHandler'");
        }
        tvCounter.setText(Integer.toString(listener.getCounter()));
        super.onActivityCreated(savedInstanceState);
    }

    public void onCounterChanged(int newCounter){
        tvCounter.setText(Integer.toString(newCounter));

    }
    public interface DataReporter{

        public int getCounter();
    }
}
