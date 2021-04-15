public class TotalTime {

	private int seconds;
	private int minutes;
	private int hours;
	//constructor to take in the basic time with seconds,minutes and hours 
	public TotalTime(int pSeconds, int pMinutes, int pHours){
		assert pSeconds >=0 && pSeconds < 60 && pMinutes>=0 && pMinutes < 60 && pHours>=0;
		seconds = pSeconds;
		minutes = pMinutes;
		hours = pHours;
	}
	public TotalTime(int pSeconds, int pMinutes){
		assert pSeconds >=0 && pSeconds < 60 && pMinutes>=0 && pMinutes <60;
		seconds = pSeconds;
		minutes = pMinutes;
		hours = 0;
	}
	public TotalTime(TotalTime pTotalTime){
		assert pTotalTime != null;
		seconds = pTotalTime.seconds;
		minutes = pTotalTime.minutes;
		hours = pTotalTime.hours;
	}
	/**
	 * 
	 * @param aTime
	 * @pre aTime != null
	 * @return
	 */
	public void addTime(TotalTime aTime) {
		assert aTime !=null;//assert that the time is not null and calculates the seconds,minutes and hours 
		int combinedSeconds = aTime.seconds + seconds;
		int combinedMinutes = aTime.minutes + minutes +divideBySixty(combinedSeconds);
		int combinedHour = aTime.hours + hours + divideBySixty(combinedMinutes);
		seconds = combinedSeconds%60;
		minutes = combinedMinutes%60;
		hours = combinedHour;

	}//helper method in order to divide by 60
	public static int divideBySixty(int aTimeFrame) {
		return aTimeFrame/60;
	}
	//basic getter to get the seconds
	public int getSeconds(){

		return seconds;
	}
	// set the seconds but a second cannot be larger than 60 or lesser than  0
	public void setSeconds(int seconds) 
	{
		assert seconds >= 0 && seconds < 60;
		this.seconds = seconds;
	}
	public int getMinutes() {
		return minutes;
	}
	/**
	 * 
	 * @param minutes
	 * @pre minutes >=0 && minutes < 60
	 */
	public void setMinutes(int minutes) {
		assert minutes >=0 && minutes < 60;
		this.minutes = minutes;
	}

	public int getHours() {
		return hours;
	}
	/**
	 * 
	 * @param hours
	 * @pre hours >=0
	 */
	public void setHours(int hours) {
		assert hours >= 0;
		this.hours = hours;

	}//returns the total time for a playlist as a string
	public String getStringOfTimeWithHours(){
		//return "Hours: " + hours + " , Minutes: " + minutes + " and Seconds: " + seconds + " long";
		return hours + "hours, " + minutes + " minutes, and  " + seconds + " seconds long";

	}
	//return the minutes and seconds as a string for a single song
	public String getMinutesAndSecondsTime(){
		//return "Minutes: "  + minutes + " and Seconds: " + seconds + "long";
		return minutes + " minutes and  " + seconds + " seconds long";
	}
	public String toString(){
		if(hours<0){
			return getMinutesAndSecondsTime();
		} 
		return getStringOfTimeWithHours();
	}
	//subtracts two songs 
	public void subtractTime(TotalTime pTime) {
		assert pTime != null;
		//asserts if the time is null and calcualtes the combined seconds of the subtraction between them
		int combinedSeconds = (seconds-pTime.seconds) + (minutes-pTime.minutes)*60 + (hours-pTime.hours)*60*60;
		if(combinedSeconds<=0) {//if this combined seconds is less than 0 the total time would be 0
			this.hours = 0;
			this.seconds =0;
			this.minutes = 0;
			return;
		}
		//divide by 3600 in order to get the hours due to 1 hours coresponding to 3600 seconds
		this.hours = combinedSeconds/3600;
		this.minutes = (combinedSeconds/60)-(hours*60);//we subtract the minutes by the hours * 60 and we would get the total minutes 
		this.seconds = combinedSeconds%60;//modulo 60 for seconds would be the final seconds and we return the total time 
		

	}
	public TotalTime getTotalTime() {
		return new TotalTime(this.seconds,this.minutes,this.hours);
		
	}
	public int convertToSeconds() {
		return(seconds+minutes*60+hours*3600);
	}

}

