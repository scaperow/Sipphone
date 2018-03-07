package com.linphone;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.linphone.R;
import org.linphone.core.LinphoneAddress;
import org.linphone.core.LinphoneCall;
import org.linphone.core.LinphoneCoreException;

import com.linphone.core.Address;
import com.linphone.core.Manager;
import com.linphone.core.PhoneService;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SipphoneActivity extends Activity implements OnClickListener {

	private Button button_call;
	private EditText text_address;

	private void setEventToControl() {
		button_call = (Button) this.findViewById(R.id.button_call);
		text_address = (EditText) this.findViewById(R.id.button_remoteaddress);
		button_call.setOnClickListener(this);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sipphone);
		setEventToControl();
		startPhoneService();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.activity_sipphone, menu);
		return true;
	}

	@Override
	public void onResume() {
		super.onResume();

		LinphoneCall call = Manager.getPendingCall();
		if (call != null) {
			Toast.makeText(this, "call id", Toast.LENGTH_SHORT).show();
		}
	}

	private void startPhoneService() {
		startService(new Intent().setClass(this, PhoneService.class));
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_call:
			try {
				Manager.Call(text_address.getText().toString());
			} catch (LinphoneCoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}
}
