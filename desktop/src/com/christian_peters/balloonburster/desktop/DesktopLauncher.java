package com.christian_peters.balloonburster.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//config.width = 720/2;
		//config.height = 1280/2;
		config.width = 1600/3;
		config.height = 2560/3;
		new LwjglApplication(new com.christian_peters.balloonburster.BalloonBusterGame(), config);
	}
}
