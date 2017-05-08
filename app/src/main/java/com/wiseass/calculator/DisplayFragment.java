package com.wiseass.calculator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;


/**
 * Why seperate the UI (View) into two Fragments?
 *
 * In this case, I am attempting to apply the Single Responsibility Principle.
 *
 * This View is responsible for Displaying the result of Inputs to the InputFragment.
 *
 * A simple {@link Fragment} subclass.
 */
public class DisplayFragment extends Fragment implements CalculatorContract.PublishToView {

    private CalculatorContract.ForwardDisplayInteractionToPresenter forwardInteraction;

    public void setPresenter (CalculatorContract.ForwardDisplayInteractionToPresenter
                                      forwardInteraction){
        this.forwardInteraction = forwardInteraction;
    }

    @BindView(R.id.lbl_display)
    TextView display;

    @OnClick(R.id.imb_delete)
    public void onDeleteShortClick(View v){
        forwardInteraction.onDeleteShortClick();
    }

    @OnLongClick(R.id.imb_delete)
    public boolean onDeleteLongClick(View v){
        forwardInteraction.onDeleteLongClick();
        return true;
    }

    public DisplayFragment() {
        // Required empty public constructor
    }

    public static DisplayFragment newInstance(){
        return new DisplayFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_display, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void showResult(String result) {
        display.setText(result);
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
