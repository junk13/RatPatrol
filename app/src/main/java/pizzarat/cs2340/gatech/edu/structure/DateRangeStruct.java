package pizzarat.cs2340.gatech.edu.structure;

import java.text.SimpleDateFormat;

/**
 * Created by Evie Brown.
 */

public class DateRangeStruct {
    private String from;
    private String to;

    public DateRangeStruct(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getDateSpan()
    {
        String fromString = new SimpleDateFormat("yyyy/MM/dd").format(from);
        String toString = new SimpleDateFormat("yyyy/MM/dd").format(this);
        int fromInSeconds = Integer.parseInt(fromString);
        int toInSeconds = Integer.parseInt(toString);
        return toInSeconds - fromInSeconds;
    }
}