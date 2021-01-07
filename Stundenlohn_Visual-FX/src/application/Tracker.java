package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.util.Duration;

public class Tracker {
	
	private Timeline animation;
	private Double loan = 0.00;
	private Double money = 0.00; 
	private Double elapsedTime = 00.00;
	private String topic;
	
	public Tracker(String topic, Double loan) {
		this.loan = loan ; 
		this.topic = topic;
	}
	
	public Tracker() {
		
	}

	public String getTopic() {
		return topic;
	}
	
	public void setTopic(String topic){
		this.topic = topic;
	}
	
	public void setLoan(Double loan){
		this.loan = loan;
	}
	
	public double getLoan() {
		return this.loan;
	}
	
	public Double getProgress() {
		return this.elapsedTime/3600; 
	}
	
	public Double calcEarning() {
		return (this.elapsedTime/3600) * loan ; 
	}
	
	public void stopAnimation() {
		this.animation.pause();
	}
	
	public void startAnimation() {
		this.animation.play();
	}

	public void track(Label timeLabel, Label earnLabel, ProgressIndicator progressInd) {
		
		if((this.elapsedTime/3600) == 1) {
			this.elapsedTime = 0.00; 
			this.money += this.loan ; 
		}
		
		this.elapsedTime += 1;
				
		timeLabel.setText(String.format("%.2f", elapsedTime/60) + " min.");
		earnLabel.setText(String.format("%.2f", this.money + calcEarning()) + "€");
		progressInd.setProgress(getProgress());
	}
	
	public void startTimer(Label timeLabel, Label earnLabel, ProgressIndicator progressInd) {
		this.animation = new Timeline(new KeyFrame(Duration.seconds(1), e -> this.track(timeLabel, earnLabel, progressInd)));
		this.animation.setCycleCount(Timeline.INDEFINITE);
		this.startAnimation();
	}

	
}
