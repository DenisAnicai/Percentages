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

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Operation2Fragment extends Fragment {
    EditText percent, number;
    TextView percentResult;
    double a, b, c;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_operation2, container, false);

        percent = view.findViewById(R.id.percent);
        number = view.findViewById(R.id.number);
        percentResult = view.findViewById(R.id.Result);

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

                                    if (!isEmpty(percent) && !isEmpty(number)) {

                                        a = Double.valueOf(percent.getText().toString());
                                        b = Double.valueOf(number.getText().toString());

                                        c = a / 100 * b;
                                        DecimalFormat df = new DecimalFormat("#.####");
                                        df.setRoundingMode(RoundingMode.CEILING);
                                        percentResult.setText(df.format(c));
                                        percentResult.setTextColor(Color.parseColor("#000000"));

                                    } else {
                                        percentResult.setText("Result");
                                        percentResult.setTextColor(Color.parseColor("#696969"));
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
