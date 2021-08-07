package model;

public class GetScoreLogic {
	public int[] execute(long[] digestionMinutes, long[] durationMinutes) {
		int[] score = new int[10];

		for (int j = 0; j < 10; j++) {

			if (durationMinutes[j] == 0) {
				score[j] = 0;
			} else if (durationMinutes[j] >= digestionMinutes[j] + 180) {
				score[j] = 80;

			} else if (durationMinutes[j] >= digestionMinutes[j] + 150) {
				score[j] = 80;

			} else if (durationMinutes[j] >= digestionMinutes[j] + 120) {
				score[j] = 60;

			} else if (durationMinutes[j] >= digestionMinutes[j] + 90) {
				score[j] = 50;

			} else if (durationMinutes[j] >= digestionMinutes[j] + 60) {
				score[j] = 40;

			} else if (durationMinutes[j] >= digestionMinutes[j]) {
				score[j] = 30;

			} else if (durationMinutes[j] >= digestionMinutes[j] - 60) {
				score[j] = 10;

			} else if (durationMinutes[j] >= digestionMinutes[j] - 90) {
				score[j] = 0;
			} else {
				score[j] = -10;

			}
		}
		return score;
	}
}
