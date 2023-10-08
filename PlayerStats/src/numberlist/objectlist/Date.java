package numberlist.objectlist;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneOffset;


/**
 * class rep month day year
 * 
 * @author [Aman Siid]
 * @version 3/19/2020
 */
public final class Date implements Copiable, Serializable, Comparable<Date> {

    //fields
    private int month;
    private int day;
    private int year;

    /**
     * Default constructor. Creates a new Player object.
     */
    public Date() {
        month = 0;
        day = 0;
        year = 0;

    }

    /**
     * Full constructor. Creates a new Object
     *
     * @param month
     * @param day
     * @param year
     */
    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;

    }

    /**
     * gives access to the month component of date
     * 
     * @return
     */
    public int getMonth() {
        return month;
    }

    /**
     * gives access to the day component of date
     * 
     * @return
     */
    public int getDay() {
        return day;
    }

    /**
     * gives access to the year component of date
     * 
     * @return
     */
    public int getYear() {
        return year;
    }

    /**
     * gives a string display of the date
     *  
     * @return
     */
    @Override
    public String toString() {
        return  month + "/" + day + "/" + year ;
    }

    /**
     *
     * @return
     */
    public Long toInteger() {
        Instant instant = Instant.now();
        instant = instant.atZone(ZoneOffset.UTC)
                .withDayOfMonth(day)
                .withMonth(month)
                .withYear(year)

                .toInstant();

        return instant.getEpochSecond();
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.month;
        hash = 59 * hash + this.day;
        hash = 59 * hash + this.year;
        return hash;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Date other = (Date) obj;
        if (this.month != other.month) {
            return false;
        }
        if (this.day != other.day) {
            return false;
        }
        if (this.year != other.year) {
            return false;
        }
        return true;
    }

    /**
     * Creats new object of date
     * 
     * @return
     */
    @Override
    public Copiable makeDeepCopy() {
        return new Date(month, day, year);
    }

    /**
     *
     * @param t
     * @return
     */
    @Override
    public int compareTo(Date t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
