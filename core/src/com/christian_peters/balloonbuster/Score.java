package com.christian_peters.balloonbuster;

public class Score implements Comparable{
	public String name;
	public float score;
	
	public Score(String name, float score){
		this.name = name;
		this.score = score;
	}
	
	public Score(){
		
	}

	@Override
	public int compareTo(Object pScore) {
		Score tmp;
		if(pScore instanceof Score) {
			tmp = (Score) pScore;
			if(this.score > tmp.score){
				return 1;
			} else if(this.score == tmp.score){
				return 0;
			} else {
				return -1;
			}
		} else {
			throw new ClassCastException();
		}
	}
	
	//for debugging only
	public String toString(){
		return "Name: "+name+", Score: "+score;
	}
}
