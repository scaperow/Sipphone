package com.linphone.core;

import org.linphone.core.LinphoneCall;
import org.linphone.core.LinphoneCall.State;
import org.linphone.core.LinphoneCore.GlobalState;
import org.linphone.core.LinphoneCore.RegistrationState;

import android.media.MediaPlayer;

public interface ISimpleServiceListener {

	public static interface LinphoneServiceListener extends
			LinphoneOnGlobalStateChangedListener,
			LinphoneOnCallStateChangedListener,
			LinphoneOnCallEncryptionChangedListener {
		void tryingNewOutgoingCallButCannotGetCallParameters();

		void tryingNewOutgoingCallButWrongDestinationAddress();

		void tryingNewOutgoingCallButAlreadyInCall();

		void onRegistrationStateChanged(RegistrationState state, String message);

		void onRingerPlayerCreated(MediaPlayer mRingerPlayer);

		void onDisplayStatus(String message);
	}

	public static interface LinphoneOnCallEncryptionChangedListener extends
			ISimpleServiceListener {
		void onCallEncryptionChanged(LinphoneCall call, boolean encrypted,
				String authenticationToken);
	}

	public static interface LinphoneOnGlobalStateChangedListener extends
			ISimpleServiceListener {
		void onGlobalStateChanged(GlobalState state, String message);
	}

	public static interface LinphoneOnCallStateChangedListener extends
			ISimpleServiceListener {
		void onCallStateChanged(LinphoneCall call, State state, String message);
	}

	public static interface LinphoneOnAudioChangedListener extends
			ISimpleServiceListener {
		public enum AudioState {
			EARPIECE, SPEAKER
		}

		void onAudioStateChanged(AudioState state);
	}
}
