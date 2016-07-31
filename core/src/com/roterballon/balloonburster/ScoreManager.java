package com.roterballon.balloonburster;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;
import java.util.Collections;

public class ScoreManager {
	private ArrayList<Score> scores;
	private Json json;
	private FileHandle file;
	private int maxSize;
	
	public ScoreManager(FileHandle file, int maxSize){
		this.file = file;
		this.json = new Json();
		this.maxSize = maxSize;
		try {
			scores = json.fromJson(ArrayList.class, Base64Coder.decodeString(file.readString()));
		} catch (Exception e) {
			scores = new ArrayList<Score>();
		}
		Collections.sort(scores);
		Collections.reverse(scores);
	}
	
	public Score get(int position){
		if(position<scores.size()){
			return scores.get(position);
		} else {
			return null;
		}
	}
	
	public int getMaxSize(){
		return this.maxSize;
	}
	
	public int getSize(){
		return scores.size();
	}
	
	public void put(String name, float score){
		if(scores.size()>=maxSize){
			if(get(maxSize-1).score < score){
				scores.remove(maxSize -1);
			} else {
				return;
			}
		}
		scores.add(new Score(name, score));
		try {
			file.writeString(Base64Coder.encodeString(json.toJson(scores)), false);
		} catch (Exception e){
			
		}
		Collections.sort(scores);
		Collections.reverse(scores);
	}
}
