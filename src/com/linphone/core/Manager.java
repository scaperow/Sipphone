package com.linphone.core;

import java.util.ArrayList;
import java.util.List;

import org.linphone.R;
import org.linphone.core.CallDirection;
import org.linphone.core.LinphoneAddress;
import org.linphone.core.LinphoneCall;
import org.linphone.core.LinphoneCall.State;
import org.linphone.core.LinphoneChatRoom;
import org.linphone.core.LinphoneCore;
import org.linphone.core.LinphoneCore.EcCalibratorStatus;
import org.linphone.core.LinphoneCore.GlobalState;
import org.linphone.core.LinphoneCore.RegistrationState;
import org.linphone.core.LinphoneCore.Transports;
import org.linphone.core.LinphoneCallParams;
import org.linphone.core.LinphoneCoreException;
import org.linphone.core.LinphoneCoreFactory;
import org.linphone.core.LinphoneCoreListener;
import org.linphone.core.LinphoneFriend;
import org.linphone.core.LinphoneProxyConfig;

import com.linphone.common.LinphonePreferenceManager;
import com.linphone.core.ISimpleServiceListener.LinphoneServiceListener;

import android.app.Service;
import android.content.Context;
import android.content.res.Resources;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.os.PowerManager;
import android.telephony.TelephonyManager;

public class Manager implements LinphoneCoreListener {

	private static Manager instance;
	public Context serviceContext;
	private AudioManager audioManager;
	private ConnectivityManager networkManager;
	private PowerManager powerManager;
	private static List<LinphoneServiceListener> listeners;
	public Path configuration;
	private LinphonePreferenceManager perference;
	public Resources resources;
	private LinphoneCore core;

	public Manager() {
		listeners = new ArrayList<LinphoneServiceListener>();
		instance = this;
	
	}

	public static void Call(String address) throws LinphoneCoreException {

		if (instance == null) {
			Log.d("-----", "instance is null");
			return;
		}

		if (instance.core == null) {
			Log.d("------", "core is null");
			return;
		}
		LinphoneAddress uri = instance.core.interpretUrl(address);

		instance.core.invite(uri);
	}

	public static void Call(LinphoneAddress address)
			throws LinphoneCoreException {

		instance.core.invite(address);
	}

	public static void videoCall(LinphoneAddress address)
			throws LinphoneCoreException {

		if (instance.core.isMyself(address.getDomain())) {
			return;
		}

		LinphoneCallParams parameter = instance.core
				.createDefaultCallParameters();
		parameter.setVideoEnabled(true);

		instance.core.inviteAddressWithParams(address, parameter);
	}

	public static void start(Context context, LinphoneServiceListener listener) {
		// TODO Auto-generated method stub
		instance = new Manager();
		instance.setProperties(context, listener);
		instance.setFirewall(context);
		instance.loadConfiguration();
	}

	private void setProperties(final Context context,
			LinphoneServiceListener listener) {
		configuration = new Path(context);
		perference = LinphonePreferenceManager.getInstance(context);
		powerManager = (PowerManager) context
				.getSystemService(Context.POWER_SERVICE);
		networkManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		resources = context.getResources();
		listeners.add(listener);

		try {
			LinphoneCoreFactory factory = CoreFactory.instance();

			if (factory == null) {
				Log.d("-----------------", "factory is null");
			} else {
				core = factory.createLinphoneCore(this, configuration.user,
						configuration.factory, null);
			}
		} catch (LinphoneCoreException e) {
			// TODO Auto-generated catch block
			Log.d("exception-0---------------------" + e.getMessage());
			e.printStackTrace();
		}
		if (core == null) {
			Log.d("-----------", "core is null");
		} else {
			core.setPlaybackGain(3);
			core.setRing(null);
			core.setRootCA(configuration.ca);
			core.setPlayFile(configuration.sound.pause);
		}
	}

	private void loadConfiguration() {
		LinphoneCoreFactory.instance().setDebugMode(true);
		Transports transport = new Transports();
		transport.udp = 5060;
		core.setSignalingTransportPorts(transport);

		try {
			core.enablePayloadType(core.findPayloadType("g729", 8000), true);
			core.enablePayloadType(core.findPayloadType("h264", 8000), true);
		} catch (LinphoneCoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void setFirewall(final Context context) {
		TelephonyManager manager = (TelephonyManager) context
				.getSystemService(Service.TELEPHONY_SERVICE);
		if (manager.getCallState() == TelephonyManager.CALL_STATE_IDLE) {
			core.setMaxCalls(0);
		} else {
			core.setMaxCalls(core.getMaxCalls());
		}
	}

	public static void addListener(LinphoneServiceListener listener) {
		listeners.add(listener);
	}

	public static void remoteListener(LinphoneCoreListener listener) {
		listeners.remove(listener);
	}

	public void call(Address address) {

	}

	@Override
	public void show(LinphoneCore lc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void authInfoRequested(LinphoneCore lc, String realm, String username) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayStatus(LinphoneCore lc, String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayMessage(LinphoneCore lc, String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayWarning(LinphoneCore lc, String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void globalState(LinphoneCore lc, GlobalState state, String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void callState(LinphoneCore lc, LinphoneCall call, State cstate,
			String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void callEncryptionChanged(LinphoneCore lc, LinphoneCall call,
			boolean encrypted, String authenticationToken) {
		// TODO Auto-generated method stub

	}

	@Override
	public void registrationState(LinphoneCore lc, LinphoneProxyConfig cfg,
			RegistrationState cstate, String smessage) {
		// TODO Auto-generated method stub

	}

	@Override
	public void newSubscriptionRequest(LinphoneCore lc, LinphoneFriend lf,
			String url) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyPresenceReceived(LinphoneCore lc, LinphoneFriend lf) {
		// TODO Auto-generated method stub

	}

	@Override
	public void textReceived(LinphoneCore lc, LinphoneChatRoom cr,
			LinphoneAddress from, String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ecCalibrationStatus(LinphoneCore lc, EcCalibratorStatus status,
			int delay_ms, Object data) {
		// TODO Auto-generated method stub

	}

	public static LinphoneCall getPendingCall() {

		if (instance == null || instance.core == null) {
			return null;
		}

		LinphoneCall call = instance.core.getCurrentCall();

		if ((call.getState() == State.IncomingReceived | call.getState() == State.CallIncomingEarlyMedia)
				&& call.getDirection() == CallDirection.Incoming) {
			return call;
		}

		return null;
	}

	void call(LinphoneAddress lAddress) throws LinphoneCoreException {
		LinphoneCallParams params = core.createDefaultCallParameters();
		core.inviteAddressWithParams(lAddress, params);
	}

}
