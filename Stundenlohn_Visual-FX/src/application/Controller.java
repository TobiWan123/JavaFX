package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;

public class Controller{
	
	@FXML
	private Button startBtn ;
	@FXML
	private Button toggleBtn ;
	@FXML
	private Label topicLabel;
	@FXML
	private Label timeLabel;
	@FXML
	private Label earnLabel;
	@FXML
	private ProgressIndicator progressInd; 
	@FXML
	private Label doLabel;
	@FXML 
	private TextField textField ;
	
	private Tracker tracker = new Tracker(); 
	private Boolean notRunning = true;
	private Boolean toggle = false;
	
	public void startTracker(ActionEvent arg0){	
		
		try{
			if(notRunning) {
				
				toggle = true;
				
				if(tracker.getTopic() == null){
					
					String a = textField.getText() != "Tracker" ? textField.getText() : "nope";
					tracker.setTopic(a);

					if(tracker.getTopic() == "nope") {
						doLabel.setText("Enter Work-Topic!");
						tracker.setTopic(null); 
					}else{
						this.topicLabel.setText(tracker.getTopic());
						doLabel.setText("Please enter your loan p.hour");
					}
				}else if(tracker.getTopic() != null && tracker.getLoan() == 0.00 ) {
					try {
						tracker.setLoan(Double.valueOf(textField.getText()));
					}catch(RuntimeException e){
						System.out.println("Something went wrong..");
						}
					if(tracker.getLoan() == 0.00) {
						doLabel.setText("Enter your loan p. hour");
					}else{
						doLabel.setText("Thank you! Now tracking.");
						tracker.startTimer(timeLabel, earnLabel, progressInd);
						this.notRunning = false;
						System.out.println(tracker.getTopic());
					}
				}		
			}
		}catch(RuntimeException e) {
			System.out.println("Something went really wrong..");
			e.printStackTrace();
		}	
	}
	
	public void toggleTracker(ActionEvent arg0){
		
		if(notRunning) {
			return;
		}else {
			
			if(toggle) {
				tracker.stopAnimation();
				toggleBtn.setText("Restart");
				toggle = false;
			}else {
				tracker.startAnimation();
				toggleBtn.setText("Stop");
				toggle = true;
			}
			
			
		}
		
	}
	
	
	
}
