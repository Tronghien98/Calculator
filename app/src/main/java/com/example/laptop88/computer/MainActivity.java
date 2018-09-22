package com.example.laptop88.computer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtNumber;
    private TextView tvResult;
    private Button bt1;
    private Button bt2;
    private Button bt3;
    private Button bt4;
    private Button bt5;
    private Button bt6;
    private Button bt7;
    private Button bt8;
    private Button bt9;
    private Button bt0;
    private Button btcong;
    private Button bttru;
    private Button btnhan;
    private Button btchia;
    private Button btcham;
    private Button btbang;
    private Button btclean;
    private Button btcleanon;
    private Button btResult;

    private final String Tag = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
        addEvents();
    }

    public void Init() {
        edtNumber = findViewById(R.id.edt_input);
        tvResult = findViewById(R.id.tv_result);
        bt1 = findViewById(R.id.bt1);
        bt0 = findViewById(R.id.bt0);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);
        bt4 = findViewById(R.id.bt4);
        bt5 = findViewById(R.id.bt5);
        bt6 = findViewById(R.id.bt6);
        bt7 = findViewById(R.id.bt7);
        bt8 = findViewById(R.id.bt8);
        bt9 = findViewById(R.id.bt9);
        btcong = findViewById(R.id.btcong);
        bttru = findViewById(R.id.bttru);
        btnhan = findViewById(R.id.btnhan);
        btchia = findViewById(R.id.btchia);
        btcham = findViewById(R.id.btcham);
        btclean = findViewById(R.id.btclean);
        btcleanon = findViewById(R.id.btcleanon);
        btbang = findViewById(R.id.btbang);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt0:
                edtNumber.append("0");
                break;
            case R.id.bt1:
                edtNumber.append("1");
                break;
            case R.id.bt2:
                edtNumber.append("2");
                break;
            case R.id.bt3:
                edtNumber.append("3");
                break;
            case R.id.bt4:
                edtNumber.append("4");
                break;
            case R.id.bt5:
                edtNumber.append("5");
                break;
            case R.id.bt6:
                edtNumber.append("6");
                break;
            case R.id.bt7:
                edtNumber.append("7");
                break;
            case R.id.bt8:
                edtNumber.append("8");
                break;
            case R.id.bt9:
                edtNumber.append("9");
                break;
            case R.id.btcong:
                edtNumber.append("+");
                break;
            case R.id.bttru:
                edtNumber.append("-");
                break;
            case R.id.btnhan:
                edtNumber.append("x");
                break;
            case R.id.btchia:
                edtNumber.append("/");
                break;
            case R.id.btcham:
                edtNumber.append(".");
                break;
            case R.id.btclean:
                BaseInputConnection textFiedInputConnection = new BaseInputConnection(edtNumber , true);
                textFiedInputConnection .sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN , KeyEvent.KEYCODE_DEL));
                break;
            case R.id.btcleanon:
                edtNumber.setText("");
                break;
            case R.id.btbang:
                DecimalFormat df = new DecimalFormat("###.####");
                double result = 0;
                addOperation(edtNumber.getText().toString());
                addNumber(edtNumber.getText().toString());
                if (arrOperation.size() >= arrNumber.size() || arrOperation.size() < 1)
                {
                    tvResult.setText("Lỗi định dạng");
                }
                else
                {
                    for(int i = 0 ; i < (arrNumber.size()-1) ; i++)
                    {
                        switch (arrOperation.get(i))
                        {
                            case "+":
                                if (i == 0)
                                {
                                    result = arrNumber.get(i) + arrNumber.get(i+1);
                                }else {
                                    result = result + arrNumber.get(i+1);
                                }
                                break;
                            case "-":
                                if (i == 0)
                                {
                                    result = arrNumber.get(i) - arrNumber.get(i+1);
                                }else {
                                    result = result - arrNumber.get(i+1);
                                }
                                break;
                            case "x":
                                if (i == 0)
                                {
                                    result = arrNumber.get(i) * arrNumber.get(i+1);
                                }else {
                                    result = result * arrNumber.get(i+1);
                                }
                                break;
                            case "/":
                                if (i == 0)
                                {
                                    result = arrNumber.get(i) / arrNumber.get(i+1);
                                }else {
                                    result = result / arrNumber.get(i+1);
                                }
                                break;
                            default:
                                break;
                        }
                    }
                    tvResult.setText(df.format(result) + "");
                }

                break;
            default:
                break;
        }
    }



    private void addEvents() {
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
        bt6.setOnClickListener(this);
        bt7.setOnClickListener(this);
        bt8.setOnClickListener(this);
        bt9.setOnClickListener(this);
        bt0.setOnClickListener(this);

        btcong.setOnClickListener(this);
        bttru.setOnClickListener(this);
        btnhan.setOnClickListener(this);
        btchia.setOnClickListener(this);

        btcham.setOnClickListener(this);
        btclean.setOnClickListener(this);
        btcleanon.setOnClickListener(this);
        btbang.setOnClickListener(this);
    }

    public  ArrayList<String> arrOperation ;
    public ArrayList<Double> arrNumber ;

    public int addOperation(String input) {
        arrOperation = new ArrayList<>();
        char []cArr = input.toCharArray();
        for (int i = 0; i < cArr.length; i++) {
            switch (cArr[i]) {
                case '+':
                    arrOperation.add(cArr[i] + "");
                    break;
                case '-':
                    arrOperation.add(cArr[i] + "");
                    break;
                case 'x':
                    arrOperation.add(cArr[i] + "");
                    break;
                case '/':
                    arrOperation.add(cArr[i] + "");
                    break;
                default:
                    break;
            }
        }
        return 0;
    }

    public void addNumber(String stringInput)
    {
        arrNumber = new ArrayList<>();
        Pattern regex = Pattern.compile("(\\d+(?:\\.\\d+)?)");
        Matcher matcher = regex.matcher(stringInput);
        while (matcher.find())
        {
            arrNumber.add(Double.valueOf(matcher.group(1)));
        }
    }




}
