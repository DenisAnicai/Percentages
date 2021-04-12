package com.anikei.percents;

import android.content.res.ColorStateList;
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
import android.widget.Toast;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Operation4Fragment extends Fragment {

    boolean OnceItWasOk = true;
    float a, b;
    EditText number1, number2;
    TextView num1, num2, result1, result2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_operation4, container, false);

        number1 = view.findViewById(R.id.number1);
        number2 = view.findViewById(R.id.number2);
        num1 = view.findViewById(R.id.num1);
        num2 = view.findViewById(R.id.num2);
        result1 = view.findViewById(R.id.result1);
        result2 = view.findViewById(R.id.result2);

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
                                    if (!isEmpty(number1) && !isEmpty(number2)) {
                                        num1.setText(number1.getText().toString());
                                        num2.setText(number2.getText().toString());

                                        a = Float.valueOf(number1.getText().toString());
                                        b = Float.valueOf(number2.getText().toString());

                                        if (a < b) {
                                            OnceItWasOk = true;
                                            result1.setText("Smaller");
                                            result1.setTextColor(Color.parseColor("#CE3F3F"));
                                            float c = ((b - a) / a * 100);
                                            DecimalFormat df = new DecimalFormat("#.####");
                                            df.setRoundingMode(RoundingMode.CEILING);

                                            result2.setText(df.format(c));
                                        } else if (a > b) {
                                            OnceItWasOk = true;
                                            result1.setText("Greater");
                                            result1.setTextColor(Color.parseColor("#02A229"));
                                            float c = ((a - b) / b * 100);
                                            DecimalFormat df = new DecimalFormat("#.####");
                                            df.setRoundingMode(RoundingMode.CEILING);
                                            result2.setText(df.format(c));
                                        } else {
                                            if (OnceItWasOk) {
                                                Toast.makeText(getActivity(), "The numbers are equal !",
                                                        Toast.LENGTH_LONG).show();
                                                OnceItWasOk = false;
                                            }
                                            result1.setText("Equal");
                                            result2.setText(String.valueOf(0));
                                        }

                                    } else {
                                        result1.setText("Result");
                                        result2.setText("Result");
                                        result1.setTextColor(Color.parseColor("#696969"));
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
