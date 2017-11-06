package pizzarat.cs2340.gatech.edu.structure;

/**
 * Created by Evie Brown.
 */

public class DateRangeStruct {
    private final String from;
    private final String to;

    public DateRangeStruct(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

// --Commented out by Inspection START (11/6/2017 1:49 AM):
//    public void setFrom(String from) {
//        this.from = from;
//    }
// --Commented out by Inspection STOP (11/6/2017 1:49 AM)

    public String getTo() {
        return to;
    }

// --Commented out by Inspection START (11/6/2017 1:49 AM):
//    public void setTo(String to) {
//        this.to = to;
//    }
// --Commented out by Inspection STOP (11/6/2017 1:49 AM)

// --Commented out by Inspection START (11/6/2017 1:51 AM):
//    public int getDateSpan()
//    {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//        String fromString = sdf.format(from);
//        String toString = sdf.format(to);
//        int fromInSeconds = Integer.parseInt(fromString);
//        int toInSeconds = Integer.parseInt(toString);
//        return toInSeconds - fromInSeconds;
//    }
// --Commented out by Inspection STOP (11/6/2017 1:51 AM)
}