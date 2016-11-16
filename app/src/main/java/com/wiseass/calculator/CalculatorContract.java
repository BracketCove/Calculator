package com.wiseass.calculator;

/**
 * Created by Ryan on 14/11/2016.
 */

public interface CalculatorContract {

    //Our View handles these methods (DisplayFragment
    interface PublishToView {
        void showResult(String result);

        void showToastMessage(String message);
    }

    //passes click events from our View (DisplayFragment), to the presenter
    interface ForwardDisplayInteractionToPresenter{
        void onDeleteShortClick();

        void onDeleteLongClick();
    }

    //passes click events from our View (InputFragment), to the presenter
    interface ForwardInputInteractionToPresenter{
        void onNumberClick(int number);

        void onDecimalClick();

        void onEvaluateClick();

        void onOperatorClick(String operator);


    }
}
