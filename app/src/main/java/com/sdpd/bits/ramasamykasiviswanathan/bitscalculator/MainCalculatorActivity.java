package com.sdpd.bits.ramasamykasiviswanathan.bitscalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainCalculatorActivity extends AppCompatActivity {

    private boolean num1_field=false, num2_field=false, resultCalculated_1 = false, resultCalculated_2 = false;
    EditText num1_editText, num2_editText;
    TextView answer;
    private static String num1_value = "", num2_value = "", answer_value="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_calculator);
        textEditSelection();
        ((Button) findViewById(R.id.button_clear)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1_editText.setText(num1_value="");
                num2_editText.setText(num2_value="");
                answer.setText(answer_value="");
            }
        });
        for(int keypress: new int[]{R.id.button_0,R.id.button_1,R.id.button_2,R.id.button_3,R.id.button_4,
                                    R.id.button_5,R.id.button_6,R.id.button_7, R.id.button_8,R.id.button_9})
        {
            keypadSelection((Button) findViewById(keypress),false);
        }
        for(int keypress : new int[]{R.id.button_divide,R.id.button_minus,R.id.button_plus,R.id.button_multiply})
        {
            keypadSelection((Button) findViewById(keypress),true);
        }
        num1_field = true;
        ((Button) findViewById(R.id.button_equalto)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer.setText(answer_value);
                answer_value = "";
            }
        });
    }

    private void keypadSelection(final Button keypadButton,boolean isOperator)
    {
        if(!isOperator)
        {
            keypadButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(num1_field) {
                            if(resultCalculated_1) {
                                num1_editText.setText("");
                                resultCalculated_1 = false;
                            }
                            num1_editText.append(keypadButton.getText());
                        }
                        else if(num2_field) {
                            if (resultCalculated_2) {
                                num2_editText.setText("");
                                resultCalculated_2 = false;
                            }
                            num2_editText.append(keypadButton.getText());
                        }
                    }
                }
            );
        }
        else {
            keypadButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    num1_value = num1_editText.getText().toString();
                    num2_value = num2_editText.getText().toString();
                    if(num1_value.isEmpty())
                    {
                        num1_value = "0";
                    }
                    if(num2_value.isEmpty())
                    {
                        num2_value = "0";
                    }
                    if(answer_value.isEmpty())
                    {
                        answer_value = "0";
                    }
                    try{
                       switch (keypadButton.getText().toString()){
                           case "+":
                                answer_value = String.valueOf(getLongValue(num1_value)+getLongValue(num2_value)+getLongValue(answer_value));
                                break;
                           case "-":
                               answer_value = String.valueOf(getLongValue(num1_value)-getLongValue(num2_value)-getLongValue(answer_value));
                               break;
                           case "/":
                               answer_value = String.valueOf(Double.valueOf(num1_value)/Double.valueOf(num2_value));
                               break;
                           case  "*":
                               answer_value = String.valueOf(getLongValue(num1_value)*getLongValue(num2_value));
                               break;
                            }
                    } catch (Exception e)
                    {
                        Toast.makeText(v.getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                    finally {
                        num1_field = true;
                        num2_field = false;
                        answer.setText(answer_value);
                        resultCalculated_1 = true;
                        resultCalculated_2 = true;
                    }
                }
            });
        }
    }

    private long getLongValue(String input)
    {
        return Long.valueOf(input);
    }

    private void textEditSelection()
    {
        num1_editText = (EditText) findViewById(R.id.num1_TextEdit);
        num1_editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1_field = true;
                num2_field = false;
                v.clearFocus();
                Log.v("num1_field i/p","num1_field:"+num1_field+"num2_field:"+num2_field);
            }
        });
        num2_editText = (EditText) findViewById(R.id.num2_TextEdit);
        num2_editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1_field = false;
                num2_field = true;
                v.clearFocus();
                Log.v("num2_field i/p","num1_field:"+num1_field+"num2_field:"+num2_field);
            }
         });
        answer = (TextView) findViewById(R.id.ans_TextView);
    }
}
