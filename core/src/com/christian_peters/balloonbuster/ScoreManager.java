package com.christian_peters.balloonbuster;

import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

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
			scores = json.fromJson(ArrayList.class, file);
		} catch (Exception e) {
			scores = new ArrayList<Score>();
		}
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
			json.toJson(scores, file);
		} catch (Exception e){
			
		}
		Collections.sort(scores);
		Collections.reverse(scores);
	}
}