package com.example.ttong.View;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;
import com.example.ttong.R;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Created by inhuh on 2015. 5. 21..
 */
public class SignUpView extends ViewGroup{

    Button button_next;

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    public SignUpView(Context context) {
        super(context);

        WifiManager wifi_mng = (WifiManager) context.getSystemService(context.WIFI_SERVICE);
        WifiInfo info = wifi_mng.getConnectionInfo();
        final String mac_addr = info.getMacAddress();

        TelephonyManager tel_mng = (TelephonyManager)context.getSystemService(context.TELEPHONY_SERVICE);
        final String phone_num = tel_mng.getLine1Number();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_signup, null, false);

        button_next = (Button) view.findViewById(R.id.button_next);
        button_next.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                    int error_check = 0;
                        try {
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ttong", "root", null);
                            PreparedStatement pStmt = conn.prepareStatement("insert into Users values(?,?)");

                            pStmt.setString(1, mac_addr);
                            pStmt.setString(2, phone_num);
                            pStmt.executeUpdate();

                            error_check++;

                            if (error_check > 0) {
                               System.out.println("Safely registered!");
                            }
                        } catch (Exception e) {
                            System.out.println("Exception : " + e);
                        }
            }
        });
    }
}
