package com.example.madaim.ex10;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends Activity implements FragA.ClickHandler, FragB.DataReporter {

    private int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null){
            this.counter = savedInstanceState.getInt("counter", 0);
        }
        if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT){
            FragmentManager fm = getFragmentManager();
            if((savedInstanceState!=null)&&(fm.findFragmentByTag("AAA")!= null)){
                return;
            }
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.fragContainer, new FragA(), "AAA").commit();

        }

    }

    @Override
    public void onClickEvent() {
        this.counter++;
        FragB fragmentb;
        if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
            fragmentb = (FragB) getFragmentManager().findFragmentById(R.id.fragment_b);
        }
        else{
            fragmentb = new FragB();
            FragmentManager fm = getFragmentManager();
            fm.beginTransaction()
                    .add(R.id.fragContainer, fragmentb, "BBB")
                    .addToBackStack("BBB")
                    .commit();
            fm.executePendingTransactions();
        }
        fragmentb.onCounterChanged(this.counter);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("counter", this.counter);
        super.onSaveInstanceState(outState);
    }

    @Override
    public int getCounter(){
        return this.counter;
    }
}
