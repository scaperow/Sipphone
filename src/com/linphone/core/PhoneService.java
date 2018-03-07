package com.linphone.core;

import org.linphone.R;
import org.linphone.core.LinphoneCall;
import org.linphone.core.LinphoneCall.State;
import org.linphone.core.LinphoneCore.GlobalState;
import org.linphone.core.LinphoneCore.RegistrationState;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;

import com.linphone.SipphoneActivity;
import com.linphone.common.LinphonePreferenceManager;
import com.linphone.core.ISimpleServiceListener.LinphoneServiceListener;

public class PhoneService extends Service implements LinphoneServiceListener {

	private Context context;
	private static PhoneService instance;
	private static Manager manager;

	@Override
	public void onCreate() {
		super.onCreate();
		LinphonePreferenceManager.getInstance(this);
		manager = new Manager();
		manager.start(this, this);
	}

	@Override
	public void onGlobalStateChanged(GlobalState state, String message) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onCallStateChanged(LinphoneCall call, State state,
			String message) {
		// TODO Auto-generated method stub
		if (state == State.IncomingReceived) {
			this.startActivity(new Intent(this, SipphoneActivity.class)
					.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
		} else if (state == State.StreamsRunning) {

		} else if (state == State.CallEnd || state == State.Error) {

		}
	}

	@Override
	public void onCallEncryptionChanged(LinphoneCall call, boolean encrypted,
			String authenticationToken) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tryingNewOutgoingCallButCannotGetCallParameters() {
		// TODO Auto-generated method stub

	}

	@Override
	public void tryingNewOutgoingCallButWrongDestinationAddress() {
		// TODO Auto-generated method stub

	}

	@Override
	public void tryingNewOutgoingCallButAlreadyInCall() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRegistrationStateChanged(RegistrationState state,
			String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRingerPlayerCreated(MediaPlayer mRingerPlayer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDisplayStatus(String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
}
