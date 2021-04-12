package com.anikei.percents;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Operation3Fragment extends Fragment {


    float Result = 0;
    private LinearLayout[] row;
    boolean OnceItWasOk = true;
    int z = 0;
    TextView result;
    Button addNumber, deleteNumber, deleteNumber2;
    private LinearLayout parentLinearLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_operation3, container, false);
        parentLinearLayout = view.findViewById(R.id.linearLayout);
        deleteNumber2 = view.findViewById(R.id.delete2);
        deleteNumber2.setOnClickListener(deleteButtonClickListener);
        addNumber = view.findViewById(R.id.addNumber);
        addNumber.setOnClickListener(addNumberButtonClickListener);
        result = view.findViewById(R.id.Result);

        row = new LinearLayout[101];
        row[z++] = view.findViewById(R.id.Row1);
        row[z++] = view.findViewById(R.id.Row2);
        Thread t = new Thread() {


            @Override
            public void run() {

                while (!isInterrupted()) {

                    try {
                        Thread.sleep(50);
                        if (getActivity() != null) {
                            getActivity().runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    Result = 0;
                                    float PercentSum = 0;
                                    boolean OK = true;

                                    for (int i = 0; i < z; i++) {
                                        row[i].setTag(i);
                                        ((EditText)(row[i].getChildAt(0))).setHint("Number " + (i + 1));
                                        String x = ((EditText) row[i].getChildAt(0)).getText().toString();
                                        String y = ((EditText) row[i].getChildAt(2)).getText().toString();
                                        if (!isEmptyString(x) && !isEmptyString(y)) {
                                            PercentSum += Float.valueOf(y);
                                            Result += Float.valueOf(x) * (Float.valueOf(y) / 100);
                                        }
                                        else
                                        {
                                            OK = false;
                                        }
                                    }
                                    if(PercentSum <= 100){
                                        OnceItWasOk = true;
                                    }
                                    if(PercentSum > 100 && OnceItWasOk) {
                                        OnceItWasOk = false;
                                        Toast.makeText(getActivity(), "The sum of the percentages is over 100% !",
                                                Toast.LENGTH_LONG).show();
                                    }

                                    if(OK)
                                    {
                                        DecimalFormat df = new DecimalFormat("#.####");
                                        df.setRoundingMode(RoundingMode.CEILING);
                                        result.setText(String.valueOf(df.format(Result)));
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

    private View.OnClickListener addNumberButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View rowView = inflater.inflate(R.layout.operation3_field, null);
            LinearLayout x = rowView.findViewById(R.id.Row);
            x.setTag(z);
            row[z++] = x;

            deleteNumber = rowView.findViewById(R.id.deleteButton);
            deleteNumber.setOnClickListener(deleteButtonClickListener);

            parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 2);

        }
    };

    private View.OnClickListener deleteButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            View parent = (View) v.getParent();
            LinearLayout x = (LinearLayout)((RelativeLayout)parent).getChildAt(1);
            for(int i=0;i<z;i++){
                if(row[i].getTag() == x.getTag())
                {
                    for(int j=i;j<z-1;j++)
                        row[j] = row[j+1];
                    z--;
                    break;
                }
            }
            parentLinearLayout.removeView(parent);
        }
    };


    private boolean isEmptyString(String s) {
        return s.trim().length() == 0;
    }


}
