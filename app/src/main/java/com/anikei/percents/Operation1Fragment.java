package com.anikei.percents;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class Operation1Fragment extends Fragment {

    EditText firstNum, secondNum;
    TextView result;
    double a, b, c;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_operation1, container, false);

        firstNum = view.findViewById(R.id.firstNumber);
        secondNum = view.findViewById(R.id.secondNumber);
        result = view.findViewById(R.id.percent);

        Thread t = new Thread() {


            @Override
            public void run() {

                while (!isInterrupted()) {

                    try {
                        Thread.sleep(100);
                        if (getActivity() != null) {
                            getActivity().runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    if (!isEmpty(firstNum) && !isEmpty(secondNum)) {

                                        a = Double.valueOf(firstNum.getText().toString());
                                        b = Double.valueOf(secondNum.getText().toString());

                                        c = (a / b) * 100;
                                        String aux = String.format("%.2f", c);
                                        result.setText(aux + "%");
                                        result.setTextColor(Color.parseColor("#000000"));
                                    } else {
                                        result.setText("Result");
                                        result.setTextColor(Color.parseColor("#696969"));
                                    }
                                }
                            });
                        }


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };

        t.start();
        return view;
    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }
}
