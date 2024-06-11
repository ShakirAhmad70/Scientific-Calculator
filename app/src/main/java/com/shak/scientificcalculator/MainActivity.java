package com.shak.scientificcalculator;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.operator.Operator;

import java.util.Objects;
import android.animation.ObjectAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private static final double PI = Math.PI;
    private static final double E = Math.E;

    private AppCompatTextView tvSec;
    private AppCompatEditText edtMain;
    private AppCompatButton btnAC, btnC, btnOP, btnCP, btnE, btnSin, btnCos, btnTan, btnLog, btnLn, btnSquare, btnFact, btnSqrt, btnInv, btnPi, btn7, btn8, btn9, btnDiv, btn4, btn5, btn6, btnMul, btn1, btn2, btn3, btnSub, btnDot, btn0, btnEqual, btnAdd;
    private ToggleButton btnToggleSound;

    private Handler handlerForClear;
    private Runnable runnableForClear;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        //Set orientation
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_main_portrait);
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.llRoot), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_main_landscape);
        }

        //Initialize all the id's
        findAllIds();

        //Set default values
        tvSec.setText("");
        edtMain.setText("");


        //Todo: Fix the cursor position
        edtMain.setOnTouchListener((v, event) -> {
            int action = event.getAction();
            if (action == MotionEvent.ACTION_UP) {
                int x = (int) event.getX();
                int y = (int) event.getY();
                int offset = edtMain.getOffsetForPosition(x, y);
                if (offset >= 0) {
                    edtMain.setSelection(offset);
                } else {
                    Toast.makeText(this, "Invalid cursor position", Toast.LENGTH_SHORT).show();
                }
            }
            return true;
        });


        //Set click listeners to all the buttons
        btnAC.setOnClickListener(v -> {
            animateAndBeep(v);
            edtMain.setText("");
            tvSec.setText("");
        });

        btnC.setOnClickListener(v -> {
            animateAndBeep(v);
            clearChars();
        });

        handlerForClear = new Handler();  //make a handler
        //Create a runnable thread which will clear the characters continuously after every 100ms
        runnableForClear = new Runnable() {
            @Override
            public void run() {
                animateAndBeep(btnC);
                clearChars();
                handlerForClear.postDelayed(this, 100);  //delay of 100ms
            }
        };
        btnC.setOnLongClickListener(v -> {
            handlerForClear.post(runnableForClear);  //perform the task
            return true;
        });

        btnC.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
                handlerForClear.removeCallbacks(runnableForClear);
            }
            return false; // Allow other touch events to be handled
        });

        btnOP.setOnClickListener(v -> {
                animateAndBeep(v);
                edtMain.append("(");
        });

        btnCP.setOnClickListener(v -> {
            animateAndBeep(v);
            edtMain.append(")");
        });

        btnE.setOnClickListener(v -> {
            animateAndBeep(v);
            edtMain.append("e");
        });

        btnSin.setOnClickListener(v -> {
            animateAndBeep(v);
            edtMain.append("sin(");
        });

        btnCos.setOnClickListener(v -> {
            animateAndBeep(v);
            edtMain.append("cos(");
        });

        btnTan.setOnClickListener(v -> {
            animateAndBeep(v);
            edtMain.append("tan(");
        });

        btnLog.setOnClickListener(v -> {
            animateAndBeep(v);
            edtMain.append("log(");
        });

        btnLn.setOnClickListener(v -> {
            animateAndBeep(v);
            edtMain.append("ln(");
        });

        btnSquare.setOnClickListener(v -> {
            animateAndBeep(v);
            String str = Objects.requireNonNull(edtMain.getText()).toString();
            if (!str.isEmpty()) {
                edtMain.append("^2");
            }
        });

        btnFact.setOnClickListener(v -> {
            animateAndBeep(v);
            String str = Objects.requireNonNull(edtMain.getText()).toString();
            if (!str.isEmpty()) {
                edtMain.append("!");
            }
        });

        btnSqrt.setOnClickListener(v -> {
            animateAndBeep(v);
            edtMain.append("√");
        });

        btnInv.setOnClickListener(v -> {
            animateAndBeep(v);
            edtMain.append("1/");
        });

        btnPi.setOnClickListener(v -> {
            animateAndBeep(v);
            edtMain.append("π");
        });

        btn0.setOnClickListener(v -> {
            animateAndBeep(v);
            edtMain.append("0");
        });

        btn1.setOnClickListener(v -> {
            animateAndBeep(v);
            edtMain.append("1");
        });

        btn2.setOnClickListener(v -> {
            animateAndBeep(v);
            edtMain.append("2");
        });

        btn3.setOnClickListener(v -> {
            animateAndBeep(v);
            edtMain.append("3");
        });

        btn4.setOnClickListener(v -> {
            animateAndBeep(v);
            edtMain.append("4");
        });

        btn5.setOnClickListener(v -> {
            animateAndBeep(v);
            edtMain.append("5");
        });

        btn6.setOnClickListener(v -> {
            animateAndBeep(v);
            edtMain.append("6");
        });

        btn7.setOnClickListener(v -> {
            animateAndBeep(v);
            edtMain.append("7");
        });

        btn8.setOnClickListener(v -> {
            animateAndBeep(v);
            edtMain.append("8");
        });

        btn9.setOnClickListener(v -> {
            animateAndBeep(v);
            edtMain.append("9");
        });

        btnDot.setOnClickListener(v -> {
            animateAndBeep(v);
            String str = Objects.requireNonNull(edtMain.getText()).toString();
            String lastNumber = findLastNumber(str);
            if (lastNumber.isEmpty()) {
                edtMain.append("0.");
            } else if (!lastNumber.contains(".")) {
                edtMain.append(".");
            } else {
                Toast.makeText(this, "Not allowed", Toast.LENGTH_SHORT).show();
            }
        });

        btnAdd.setOnClickListener(v -> {
            animateAndBeep(v);
            String str = Objects.requireNonNull(edtMain.getText()).toString();
            if (str.endsWith("+") || str.endsWith("-") || str.endsWith("*") || str.endsWith("/")) {
                edtMain.setText(str.substring(0, str.length() - 1));
                edtMain.append("+");
            } else {
                edtMain.append("+");
            }
        });

        btnSub.setOnClickListener(v -> {
            animateAndBeep(v);
            String str = Objects.requireNonNull(edtMain.getText()).toString();
            if (str.endsWith("+") || str.endsWith("-") || str.endsWith("*") || str.endsWith("/")) {
                edtMain.setText(str.substring(0, str.length() - 1));
                edtMain.append("-");
            } else {
                edtMain.append("-");
            }
        });

        btnMul.setOnClickListener(v -> {
            animateAndBeep(v);
            String str = Objects.requireNonNull(edtMain.getText()).toString();
            if (str.isEmpty()) {
                Toast.makeText(this, "Not allowed", Toast.LENGTH_SHORT).show();
            } else if (str.length() == 1) {
                if (str.equals("+") || str.equals("-")) {
                    Toast.makeText(this, "Not allowed", Toast.LENGTH_SHORT).show();
                } else {
                    edtMain.append("*");
                }
            } else {
                if (str.endsWith("+") || str.endsWith("-") || str.endsWith("*") || str.endsWith("/")) {
                    edtMain.setText(str.substring(0, str.length() - 1));
                    edtMain.append("*");
                } else {
                    edtMain.append("*");
                }
            }
        });

        btnDiv.setOnClickListener(v -> {
            animateAndBeep(v);
            String str = Objects.requireNonNull(edtMain.getText()).toString();
            if (str.isEmpty()) {
                Toast.makeText(this, "Not allowed", Toast.LENGTH_SHORT).show();
            } else if (str.length() == 1) {
                if (str.equals("+") || str.equals("-")) {
                    Toast.makeText(this, "Not allowed", Toast.LENGTH_SHORT).show();
                } else {
                    edtMain.append("/");
                }
            } else {
                if (str.endsWith("+") || str.endsWith("-") || str.endsWith("*") || str.endsWith("/")) {
                    edtMain.setText(str.substring(0, str.length() - 1));
                    edtMain.append("/");
                } else {
                    edtMain.append("/");
                }
            }
        });


        btnEqual.setOnClickListener(v -> {
            animateAndBeep(v);
            String exp = Objects.requireNonNull(edtMain.getText()).toString();
            String result = parseExp(exp);
            if(result.equals("Error")){
                tvSec.setText(R.string.error);
            } else {
                tvSec.setText(exp);
                edtMain.setText(result);
            }
        });
    }



    //make the tap animation for buttons
    private void animateAndBeep(View view) {
        //Animation for tap
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0.9f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0.9f, 1f);
        scaleX.setDuration(150);
        scaleY.setDuration(150);
        scaleX.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleY.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleX.start();
        scaleY.start();

        //Beep
        if (btnToggleSound.isChecked()) {
            MediaPlayer mediaPlayer;
            if(view.getId() == R.id.btnEqual){
                mediaPlayer = MediaPlayer.create(this, R.raw.equal_button_click);
            } else if(view.getId() == R.id.btnAC) {
                mediaPlayer = MediaPlayer.create(this, R.raw.ac_button_click);
            } else if(view.getId() == R.id.btnAdd || view.getId() == R.id.btnSub || view.getId() == R.id.btnMul || view.getId() == R.id.btnDiv) {
                mediaPlayer = MediaPlayer.create(this, R.raw.operator_button_click);
            } else {
                mediaPlayer = MediaPlayer.create(this, R.raw.other_button_click);
            }
            mediaPlayer.start();

            // Release the media player resources after the sound has finished playing
            mediaPlayer.setOnCompletionListener(MediaPlayer::release);
        }

    }


    private String parseExp(String exp) {
        if (exp.isEmpty()) {
            return "";
        }

        exp = insertMulBeforeConstants(exp);

        // Replace custom characters with appropriate function names
        exp = exp.replaceAll("÷", "/");
        exp = exp.replaceAll("×", "*");
        exp = exp.replaceAll("√", "sqrt");
        exp = exp.replaceAll("π", String.valueOf(PI));
        exp = exp.replaceAll("e", String.valueOf(E));

        try {
            Expression expression = new ExpressionBuilder(exp)
                    .operator(new Operator("^", 2, true, Operator.PRECEDENCE_POWER + 1) {
                        @Override
                        public double apply(double... args) {
                            return Math.pow(args[0], args[1]);
                        }
                    })
                    .functions(
                            new net.objecthunter.exp4j.function.Function("sin") {
                                @Override
                                public double apply(double... args) {
                                    return Math.sin(Math.toRadians(args[0])); // exp4j uses radians
                                }
                            },
                            new net.objecthunter.exp4j.function.Function("cos") {
                                @Override
                                public double apply(double... args) {
                                    return Math.cos(Math.toRadians(args[0]));
                                }
                            },
                            new net.objecthunter.exp4j.function.Function("tan") {
                                @Override
                                public double apply(double... args) {
                                    return Math.tan(Math.toRadians(args[0]));
                                }
                            },
                            new net.objecthunter.exp4j.function.Function("log") {
                                @Override
                                public double apply(double... args) {
                                    return Math.log10(args[0]);
                                }
                            },
                            new net.objecthunter.exp4j.function.Function("ln") {
                                @Override
                                public double apply(double... args) {
                                    return Math.log(args[0]);
                                }
                            },
                            new net.objecthunter.exp4j.function.Function("sqrt") {
                                @Override
                                public double apply(double... args) {
                                    return Math.sqrt(args[0]);
                                }
                            }
                    )
                    .build();
            double result = expression.evaluate();
            return String.valueOf(result);
        } catch (Exception e) {
            return "Error";
        }
    }


    private String insertMulBeforeConstants(String exp) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);
            if (c == 'e' || c == 'π') {
                // Check if previous character is a digit, ')', 'e' or 'π'
                if (i > 0 && (Character.isDigit(exp.charAt(i - 1)) || exp.charAt(i - 1) == ')' || exp.charAt(i - 1) == 'e' || exp.charAt(i - 1) == 'π')) {
                    result.append('×');
                }
            }
            result.append(c);
        }
        return result.toString();
    }

    private void findAllIds() {
        tvSec = findViewById(R.id.tvSec);
        edtMain = findViewById(R.id.edtMain);
        btnAC = findViewById(R.id.btnAC);
        btnC = findViewById(R.id.btnC);
        btnOP = findViewById(R.id.btnOP);
        btnCP = findViewById(R.id.btnCP);
        btnToggleSound = findViewById(R.id.btnToggleSound);
        btnE = findViewById(R.id.btnE);
        btnSin = findViewById(R.id.btnSin);
        btnCos = findViewById(R.id.btnCos);
        btnTan = findViewById(R.id.btnTan);
        btnLog = findViewById(R.id.btnLog);
        btnLn = findViewById(R.id.btnLn);
        btnSquare = findViewById(R.id.btnSquare);
        btnFact = findViewById(R.id.btnFact);
        btnSqrt = findViewById(R.id.btnSqrt);
        btnInv = findViewById(R.id.btnInv);
        btnPi = findViewById(R.id.btnPi);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnDiv = findViewById(R.id.btnDiv);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btnMul = findViewById(R.id.btnMul);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btnSub = findViewById(R.id.btnSub);
        btnDot = findViewById(R.id.btnDot);
        btn0 = findViewById(R.id.btn0);
        btnEqual = findViewById(R.id.btnEqual);
        btnAdd = findViewById(R.id.btnAdd);
    }

    private void clearChars(){
        String str = Objects.requireNonNull(edtMain.getText()).toString();
        if (!str.isEmpty()) {
            if (str.endsWith("sin") || str.endsWith("cos") || str.endsWith("tan") || str.endsWith("log")) {
                edtMain.setText(str.substring(0, str.length() - 3));
            } else if (str.endsWith("ln") || str.endsWith("^2")) {
                edtMain.setText(str.substring(0, str.length() - 2));
            } else {
                edtMain.setText(str.substring(0, str.length() - 1));
            }
            edtMain.setSelection(edtMain.getText().length());  //move to last of string/expression
        } else {
            tvSec.setText("");  //in case of error showing on tvSec
        }
    }

    private String findLastNumber(String exp) {
        for (int i = exp.length() - 1; i >= 0; i--) {
            if (exp.charAt(i) == '+' || exp.charAt(i) == '-' || exp.charAt(i) == '*' || exp.charAt(i) == '/' || exp.charAt(i) == '(' || exp.charAt(i) == ')') {
                return exp.substring(i + 1);
            }
        }
        return exp;
    }
}