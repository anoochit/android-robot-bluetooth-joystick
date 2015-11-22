package net.redlinesoft.androidrobotbtjoystick;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.jmedeisis.bugstick.Joystick;
import com.jmedeisis.bugstick.JoystickListener;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;

public class MainActivity extends AppCompatActivity {

    private static final int STICK_NONE = 0;
    private static final int STICK_UP = 1;
    private static final int STICK_UPRIGHT = 2;
    private static final int STICK_RIGHT = 3;
    private static final int STICK_DOWNRIGHT = 4;
    private static final int STICK_DOWN = 5;
    private static final int STICK_DOWNLEFT = 6;
    private static final int STICK_LEFT = 7;
    private static final int STICK_UPLEFT = 8;
    private static final int RESULT_SETTING = 0;

    private View mDecorView;
    Boolean buttonDownLeft = false;
    Boolean buttonDownRight = false;
    BluetoothSPP bt;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setBackgroundColor(Color.DKGRAY);

        // setup bluetooth
        bt = new BluetoothSPP(context);

        if (!bt.isBluetoothAvailable()) {
            // any command for bluetooth is not available
        }

        // set view
        mDecorView = getWindow().getDecorView();
        hideSystemUI();

        // set view
        final TextView txtAngle = (TextView) findViewById(R.id.txtAngle);
        final TextView txtOffset = (TextView) findViewById(R.id.txtOffset);
        final TextView txtHold = (TextView) findViewById(R.id.txtHold);

        Joystick joystickLeft = (Joystick) findViewById(R.id.joystickLeft);
        Joystick joystickRight = (Joystick) findViewById(R.id.joystickRight);

        joystickLeft.setJoystickListener(new JoystickListener() {
            @Override
            public void onDown() {
                buttonDownLeft = true;
            }

            @Override
            public void onDrag(float degrees, float offset) {
                // set text
                txtAngle.setText(String.valueOf(angleConvert(degrees)));
                txtOffset.setText(String.valueOf(distanceConvert(offset)));

                // check position
                int direction = get8Direction(degrees);

                // Right hold
                if (buttonDownRight) {
                    // set text
                    txtHold.setText("Right Hold");
                    // action left joystick
                    if (direction == STICK_UP) {
                        String data = "";
                        // start
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_before_token", "");
                        }
                        // data
                        data = data + prefs.getString("pref_hold_left_pos_up", "");
                        // pwm
                        if (prefs.getBoolean("pref_send_pwm_switch", true) == true) {
                            data = data + prefs.getString("pref_pwm_separator", "");
                            data = data + distanceConvert(offset);
                        }
                        // stop
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_end_token", "");
                        }
                        sendBluetoothData(data);
                    } else if (direction == STICK_UPRIGHT) {
                        String data = "";
                        // start
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_before_token", "");
                        }
                        // data
                        data = data + prefs.getString("pref_hold_left_pos_upright", "");
                        // pwm
                        if (prefs.getBoolean("pref_send_pwm_switch", true) == true) {
                            data = data + prefs.getString("pref_pwm_separator", "");
                            data = data + distanceConvert(offset);
                        }
                        // stop
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_end_token", "");
                        }
                        sendBluetoothData(data);
                    } else if (direction == STICK_RIGHT) {
                        String data = "";
                        // start
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_before_token", "");
                        }
                        // data
                        data = data + prefs.getString("pref_hold_left_pos_right", "");
                        // pwm
                        if (prefs.getBoolean("pref_send_pwm_switch", true) == true) {
                            data = data + prefs.getString("pref_pwm_separator", "");
                            data = data + distanceConvert(offset);
                        }
                        // stop
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_end_token", "");
                        }
                        sendBluetoothData(data);
                    } else if (direction == STICK_DOWNRIGHT) {
                        String data = "";
                        // start
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_before_token", "");
                        }
                        // data
                        data = data + prefs.getString("pref_hold_left_pos_downright", "");
                        // pwm
                        if (prefs.getBoolean("pref_send_pwm_switch", true) == true) {
                            data = data + prefs.getString("pref_pwm_separator", "");
                            data = data + distanceConvert(offset);
                        }
                        // stop
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_end_token", "");
                        }
                        sendBluetoothData(data);
                    } else if (direction == STICK_DOWN) {
                        String data = "";
                        // start
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_before_token", "");
                        }
                        // data
                        data = data + prefs.getString("pref_hold_left_pos_down", "");
                        // pwm
                        if (prefs.getBoolean("pref_send_pwm_switch", true) == true) {
                            data = data + prefs.getString("pref_pwm_separator", "");
                            data = data + distanceConvert(offset);
                        }
                        // stop
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_end_token", "");
                        }
                        sendBluetoothData(data);
                    } else if (direction == STICK_DOWNLEFT) {
                        String data = "";
                        // start
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_before_token", "");
                        }
                        // data
                        data = data + prefs.getString("pref_hold_left_pos_downleft", "");
                        // pwm
                        if (prefs.getBoolean("pref_send_pwm_switch", true) == true) {
                            data = data + prefs.getString("pref_pwm_separator", "");
                            data = data + distanceConvert(offset);
                        }
                        // stop
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_end_token", "");
                        }
                        sendBluetoothData(data);
                    } else if (direction == STICK_LEFT) {
                        String data = "";
                        // start
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_before_token", "");
                        }
                        // data
                        data = data + prefs.getString("pref_hold_left_pos_left", "");
                        // pwm
                        if (prefs.getBoolean("pref_send_pwm_switch", true) == true) {
                            data = data + prefs.getString("pref_pwm_separator", "");
                            data = data + distanceConvert(offset);
                        }
                        // stop
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_end_token", "");
                        }
                        sendBluetoothData(data);
                    } else if (direction == STICK_UPLEFT) {
                        String data = "";
                        // start
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_before_token", "");
                        }
                        // data
                        data = data + prefs.getString("pref_hold_left_pos_upleft", "");
                        // pwm
                        if (prefs.getBoolean("pref_send_pwm_switch", true) == true) {
                            data = data + prefs.getString("pref_pwm_separator", "");
                            data = data + distanceConvert(offset);
                        }
                        // stop
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_end_token", "");
                        }
                        sendBluetoothData(data);
                    } else {
                        // no direction
                    }
                } else {
                    // action left joystick
                    if (direction == STICK_UP) {
                        String data = "";
                        // start
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_before_token", "");
                        }
                        // data
                        data = data + prefs.getString("pref_left_pos_up", "");
                        // pwm
                        if (prefs.getBoolean("pref_send_pwm_switch", true) == true) {
                            data = data + prefs.getString("pref_pwm_separator", "");
                            data = data + distanceConvert(offset);
                        }
                        // stop
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_end_token", "");
                        }
                        sendBluetoothData(data);
                    } else if (direction == STICK_UPRIGHT) {
                        String data = "";
                        // start
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_before_token", "");
                        }
                        // data
                        data = data + prefs.getString("pref_left_pos_upright", "");
                        // pwm
                        if (prefs.getBoolean("pref_send_pwm_switch", true) == true) {
                            data = data + prefs.getString("pref_pwm_separator", "");
                            data = data + distanceConvert(offset);
                        }
                        // stop
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_end_token", "");
                        }
                        sendBluetoothData(data);
                    } else if (direction == STICK_RIGHT) {
                        String data = "";
                        // start
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_before_token", "");
                        }
                        // data
                        data = data + prefs.getString("pref_left_pos_right", "");
                        // pwm
                        if (prefs.getBoolean("pref_send_pwm_switch", true) == true) {
                            data = data + prefs.getString("pref_pwm_separator", "");
                            data = data + distanceConvert(offset);
                        }
                        // stop
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_end_token", "");
                        }
                        sendBluetoothData(data);
                    } else if (direction == STICK_DOWNRIGHT) {
                        String data = "";
                        // start
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_before_token", "");
                        }
                        // data
                        data = data + prefs.getString("pref_left_pos_downright", "");
                        // pwm
                        if (prefs.getBoolean("pref_send_pwm_switch", true) == true) {
                            data = data + prefs.getString("pref_pwm_separator", "");
                            data = data + distanceConvert(offset);
                        }
                        // stop
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_end_token", "");
                        }
                        sendBluetoothData(data);
                    } else if (direction == STICK_DOWN) {
                        String data = "";
                        // start
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_before_token", "");
                        }
                        // data
                        data = data + prefs.getString("pref_left_pos_down", "");
                        // pwm
                        if (prefs.getBoolean("pref_send_pwm_switch", true) == true) {
                            data = data + prefs.getString("pref_pwm_separator", "");
                            data = data + distanceConvert(offset);
                        }
                        // stop
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_end_token", "");
                        }
                        sendBluetoothData(data);
                    } else if (direction == STICK_DOWNLEFT) {
                        String data = "";
                        // start
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_before_token", "");
                        }
                        // data
                        data = data + prefs.getString("pref_left_pos_downleft", "");
                        // pwm
                        if (prefs.getBoolean("pref_send_pwm_switch", true) == true) {
                            data = data + prefs.getString("pref_pwm_separator", "");
                            data = data + distanceConvert(offset);
                        }
                        // stop
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_end_token", "");
                        }
                        sendBluetoothData(data);
                    } else if (direction == STICK_LEFT) {
                        String data = "";
                        // start
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_before_token", "");
                        }
                        // data
                        data = data + prefs.getString("pref_left_pos_left", "");
                        // pwm
                        if (prefs.getBoolean("pref_send_pwm_switch", true) == true) {
                            data = data + prefs.getString("pref_pwm_separator", "");
                            data = data + distanceConvert(offset);
                        }
                        // stop
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_end_token", "");
                        }
                        sendBluetoothData(data);
                    } else if (direction == STICK_UPLEFT) {
                        String data = "";
                        // start
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_before_token", "");
                        }
                        // data
                        data = data + prefs.getString("pref_left_pos_upleft", "");
                        // pwm
                        if (prefs.getBoolean("pref_send_pwm_switch", true) == true) {
                            data = data + prefs.getString("pref_pwm_separator", "");
                            data = data + distanceConvert(offset);
                        }
                        // stop
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_end_token", "");
                        }
                        sendBluetoothData(data);
                    } else {
                        // no direction
                    }
                }
            }

            @Override
            public void onUp() {
                // set text
                txtAngle.setText("");
                txtOffset.setText("");
                txtHold.setText("");
                buttonDownLeft = false;
            }
        });

        joystickRight.setJoystickListener(new JoystickListener() {
            @Override
            public void onDown() {
                buttonDownRight = true;
            }

            @Override
            public void onDrag(float degrees, float offset) {

                // set text
                txtAngle.setText(String.valueOf(angleConvert(degrees)));
                txtOffset.setText(String.valueOf(distanceConvert(offset)));

                // check position
                int direction = get8Direction(degrees);

                // Left hold
                if (buttonDownLeft) {
                    // set text
                    txtHold.setText("Left Hold");
                    // action
                    if (direction == STICK_UP) {
                        String data = "";
                        // start
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_before_token", "");
                        }
                        // data
                        data = data + prefs.getString("pref_hold_right_pos_up", "");
                        // pwm
                        if (prefs.getBoolean("pref_send_pwm_switch", true) == true) {
                            data = data + prefs.getString("pref_pwm_separator", "");
                            data = data + distanceConvert(offset);
                        }
                        // stop
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_end_token", "");
                        }
                        sendBluetoothData(data);
                    } else if (direction == STICK_UPRIGHT) {
                        String data = "";
                        // start
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_before_token", "");
                        }
                        // data
                        data = data + prefs.getString("pref_hold_right_pos_upright", "");
                        // pwm
                        if (prefs.getBoolean("pref_send_pwm_switch", true) == true) {
                            data = data + prefs.getString("pref_pwm_separator", "");
                            data = data + distanceConvert(offset);
                        }
                        // stop
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_end_token", "");
                        }
                        sendBluetoothData(data);
                    } else if (direction == STICK_RIGHT) {
                        String data = "";
                        // start
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_before_token", "");
                        }
                        // data
                        data = data + prefs.getString("pref_hold_right_pos_right", "");
                        // pwm
                        if (prefs.getBoolean("pref_send_pwm_switch", true) == true) {
                            data = data + prefs.getString("pref_pwm_separator", "");
                            data = data + distanceConvert(offset);
                        }
                        // stop
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_end_token", "");
                        }
                        sendBluetoothData(data);
                    } else if (direction == STICK_DOWNRIGHT) {
                        String data = "";
                        // start
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_before_token", "");
                        }
                        // data
                        data = data + prefs.getString("pref_hold_right_pos_downright", "");
                        // pwm
                        if (prefs.getBoolean("pref_send_pwm_switch", true) == true) {
                            data = data + prefs.getString("pref_pwm_separator", "");
                            data = data + distanceConvert(offset);
                        }
                        // stop
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_end_token", "");
                        }
                        sendBluetoothData(data);
                    } else if (direction == STICK_DOWN) {
                        String data = "";
                        // start
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_before_token", "");
                        }
                        // data
                        data = data + prefs.getString("pref_hold_right_pos_down", "");
                        // pwm
                        if (prefs.getBoolean("pref_send_pwm_switch", true) == true) {
                            data = data + prefs.getString("pref_pwm_separator", "");
                            data = data + distanceConvert(offset);
                        }
                        // stop
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_end_token", "");
                        }
                        sendBluetoothData(data);
                    } else if (direction == STICK_DOWNLEFT) {
                        String data = "";
                        // start
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_before_token", "");
                        }
                        // data
                        data = data + prefs.getString("pref_hold_right_pos_downleft", "");
                        // pwm
                        if (prefs.getBoolean("pref_send_pwm_switch", true) == true) {
                            data = data + prefs.getString("pref_pwm_separator", "");
                            data = data + distanceConvert(offset);
                        }
                        // stop
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_end_token", "");
                        }
                        sendBluetoothData(data);
                    } else if (direction == STICK_LEFT) {
                        String data = "";
                        // start
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_before_token", "");
                        }
                        // data
                        data = data + prefs.getString("pref_hold_right_pos_left", "");
                        // pwm
                        if (prefs.getBoolean("pref_send_pwm_switch", true) == true) {
                            data = data + prefs.getString("pref_pwm_separator", "");
                            data = data + distanceConvert(offset);
                        }
                        // stop
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_end_token", "");
                        }
                        sendBluetoothData(data);
                    } else if (direction == STICK_UPLEFT) {
                        String data = "";
                        // start
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_before_token", "");
                        }
                        // data
                        data = data + prefs.getString("pref_hold_right_pos_upleft", "");
                        // pwm
                        if (prefs.getBoolean("pref_send_pwm_switch", true) == true) {
                            data = data + prefs.getString("pref_pwm_separator", "");
                            data = data + distanceConvert(offset);
                        }
                        // stop
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_end_token", "");
                        }
                        sendBluetoothData(data);
                    } else {
                        // no direction
                    }
                } else {
                    // Not hold
                    // set action
                    if (direction == STICK_UP) {
                        String data = "";
                        // start
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_before_token", "");
                        }
                        // data
                        data = data + prefs.getString("pref_right_pos_up", "");
                        // pwm
                        if (prefs.getBoolean("pref_send_pwm_switch", true) == true) {
                            data = data + prefs.getString("pref_pwm_separator", "");
                            data = data + distanceConvert(offset);
                        }
                        // stop
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_end_token", "");
                        }
                        sendBluetoothData(data);
                    } else if (direction == STICK_UPRIGHT) {
                        String data = "";
                        // start
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_before_token", "");
                        }
                        // data
                        data = data + prefs.getString("pref_right_pos_upright", "");
                        // pwm
                        if (prefs.getBoolean("pref_send_pwm_switch", true) == true) {
                            data = data + prefs.getString("pref_pwm_separator", "");
                            data = data + distanceConvert(offset);
                        }
                        // stop
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_end_token", "");
                        }
                        sendBluetoothData(data);
                    } else if (direction == STICK_RIGHT) {
                        String data = "";
                        // start
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_before_token", "");
                        }
                        // data
                        data = data + prefs.getString("pref_right_pos_right", "");
                        // pwm
                        if (prefs.getBoolean("pref_send_pwm_switch", true) == true) {
                            data = data + prefs.getString("pref_pwm_separator", "");
                            data = data + distanceConvert(offset);
                        }
                        // stop
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_end_token", "");
                        }
                        sendBluetoothData(data);
                    } else if (direction == STICK_DOWNRIGHT) {
                        String data = "";
                        // start
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_before_token", "");
                        }
                        // data
                        data = data + prefs.getString("pref_right_pos_downright", "");
                        // pwm
                        if (prefs.getBoolean("pref_send_pwm_switch", true) == true) {
                            data = data + prefs.getString("pref_pwm_separator", "");
                            data = data + distanceConvert(offset);
                        }
                        // stop
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_end_token", "");
                        }
                        sendBluetoothData(data);
                    } else if (direction == STICK_DOWN) {
                        String data = "";
                        // start
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_before_token", "");
                        }
                        // data
                        data = data + prefs.getString("pref_right_pos_down", "");
                        // pwm
                        if (prefs.getBoolean("pref_send_pwm_switch", true) == true) {
                            data = data + prefs.getString("pref_pwm_separator", "");
                            data = data + distanceConvert(offset);
                        }
                        // stop
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_end_token", "");
                        }
                        sendBluetoothData(data);
                    } else if (direction == STICK_DOWNLEFT) {
                        String data = "";
                        // start
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_before_token", "");
                        }
                        // data
                        data = data + prefs.getString("pref_right_pos_downleft", "");
                        // pwm
                        if (prefs.getBoolean("pref_send_pwm_switch", true) == true) {
                            data = data + prefs.getString("pref_pwm_separator", "");
                            data = data + distanceConvert(offset);
                        }
                        // stop
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_end_token", "");
                        }
                        sendBluetoothData(data);
                    } else if (direction == STICK_LEFT) {
                        String data = "";
                        // start
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_before_token", "");
                        }
                        // data
                        data = data + prefs.getString("pref_right_pos_left", "");
                        // pwm
                        if (prefs.getBoolean("pref_send_pwm_switch", true) == true) {
                            data = data + prefs.getString("pref_pwm_separator", "");
                            data = data + distanceConvert(offset);
                        }
                        // stop
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_end_token", "");
                        }
                        sendBluetoothData(data);
                    } else if (direction == STICK_UPLEFT) {
                        String data = "";
                        // start
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_before_token", "");
                        }
                        // data
                        data = data + prefs.getString("pref_right_pos_upleft", "");
                        // pwm
                        if (prefs.getBoolean("pref_send_pwm_switch", true) == true) {
                            data = data + prefs.getString("pref_pwm_separator", "");
                            data = data + distanceConvert(offset);
                        }
                        // stop
                        if (prefs.getBoolean("pref_token_switch", true) == true) {
                            data = data + prefs.getString("pref_end_token", "");
                        }
                        sendBluetoothData(data);
                    } else {
                        // no direction
                    }
                }

            }

            @Override
            public void onUp() {
                txtAngle.setText("");
                txtOffset.setText("");
                txtHold.setText("");
                buttonDownRight = false;
                buttonDownLeft = false;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPreference();
    }


    SharedPreferences prefs;

    public void loadPreference() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Map<String, ?> keys = prefs.getAll();
        Log.d("LOG", "Keys = " + keys.size() + "");
        if (keys.size() > 8) {
            for (Map.Entry<String, ?> entry : keys.entrySet()) {
                Log.d("LOG", entry.getKey() + ": " +
                        entry.getValue().toString());
            }
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.alert_nodata_setup_first);
            builder.setCancelable(false);
            builder.setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                    Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
                    startActivityForResult(i, RESULT_SETTING);
                }
            });
            builder.show();
        }
    }


    public void sendBluetoothData(final String data) {
        Log.d("LOG", data);

    }

    public int get8Direction(float degrees) {
        float angle = angleConvert(degrees);

        if (angle >= 85 && angle < 95) {
            return STICK_UP;
        } else if (angle >= 40 && angle < 50) {
            return STICK_UPRIGHT;
        } else if (angle >= 355 || angle < 5) {
            return STICK_RIGHT;
        } else if (angle >= 310 && angle < 320) {
            return STICK_DOWNRIGHT;
        } else if (angle >= 265 && angle < 275) {
            return STICK_DOWN;
        } else if (angle >= 220 && angle < 230) {
            return STICK_DOWNLEFT;
        } else if (angle >= 175 && angle < 185) {
            return STICK_LEFT;
        } else if (angle >= 130 && angle < 140) {
            return STICK_UPLEFT;
        }

        return 0;
    }

    public int angleConvert(float degrees) {
        int angle = 0;
        if ((int) degrees < 0) angle = (360 + (int) degrees);
        else angle = (int) degrees;
        return angle;
    }

    public int distanceConvert(float offset) {
        int pwm = (int) (offset * 100);
        return (pwm);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.mnuBluetooth) {

        } else if (itemId == R.id.mnuSetting) {
            Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivityForResult(i, RESULT_SETTING);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus)
            hideSystemUI();
    }

    // This snippet hides the system bars.
    private void hideSystemUI() {
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            mDecorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                            | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                            | View.SYSTEM_UI_FLAG_IMMERSIVE
            );
        }
    }

    // This snippet shows the system bars. It does this by removing all the flags
// except for the ones that make the content appear under the system bars.
    private void showSystemUI() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            mDecorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
    }


}



