/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader();
    }
    
    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer(String filename)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader(filename);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    
    /**
     * Tracks number of accesses
     * @returns int - the total number of accesses
     */
    public int numberOfAccesses() {
        int total = 0;
        
        for(int hour = 0; hour < hourCounts.length; hour++) {
            total += hourCounts[hour];
        }
        return total;
    }
    
    /**
     * Tracks for the busiest hour
     * @returns int - the busiest hour
     */
    public int busiestHour() {
        int maxHour = 0;
        
        for(int hour = 1; hour < hourCounts.length; hour++) {
            if(hourCounts[hour] > hourCounts[maxHour]) {
                maxHour = hour;
            }
        }
        
        return maxHour;
    }
    
    /**
     * Tracks quietest hour
     * @returns int - the quietest hour
     */
    public int quietestHour() {
        int quietestHour = 0;
        
        for(int i = 0; i < hourCounts.length; i++) {
            if(hourCounts[i] > 0 && hourCounts[i] > quietestHour) {
                quietestHour = hourCounts[i];
            }
        }
        
        return quietestHour;
    }
    
    /**
     * Tracks busiest two hours, while returning first of the two hours
     * @returns int - the first of the two busiest hours
     */
    public int busiestTwoHours () {
        int firstHour = 0;
        int secondHour = 0;
        
        for(int i = 0; i < hourCounts.length; i++) {
            if (firstHour < hourCounts[i] ) {
                firstHour = hourCounts[i];
                secondHour = firstHour;
            }
        }
        
        return firstHour;
    }
        
}
