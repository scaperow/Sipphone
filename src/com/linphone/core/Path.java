package com.linphone.core;

import android.content.Context;

public class Path {

	public String root;
	public String user;
	public String factory;
	public String ca;
	public Sound sound;
	public String zrtp_secrets;

	public Path(Context context) {
		root = context.getFilesDir().getAbsolutePath();
		user = root + "/linphonerc";
		factory = root+"/linphonerc";
		ca = root + "/rootca.pem";
		zrtp_secrets = root+"/zrtp_secrets";

		sound = new Sound(root);
	}

	public class Sound {
		public String ringback;
		public String pause;
		public String ring;

		public Sound(String root) {
			ringback = root + "/ringback.wav";
			ring = root + "/oldphone_mono.wav";
			pause = root + "/toy_mono.wav";
		}
	}

}
