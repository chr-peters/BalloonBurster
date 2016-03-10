package com.christian_peters.balloonbuster;

import java.util.ArrayList;

import com.badlogic.gdx.files.FileHandle;

public class ScoreManager {
	private ArrayList<Score> scores;
	
	public ScoreManager(FileHandle file, int maxSize){
		
	}
	
	public Score get(int position){
		return new Score ("test", 0);
	}
	
	public void put(String name, float score){
		
	}
}
