package com.vkg.pactice.calendar;

import org.quartz.*;
import org.quartz.impl.triggers.AbstractTrigger;

import java.text.ParseException;
import java.util.*;

/**
 * Created by Vishnu on 8/29/2018.
 */
public class MyTrigger<T extends Trigger> extends AbstractTrigger<T> {
    public static final int MISFIRE_INSTRUCTION_FIRE_ONCE_NOW = 1;

    /**
     * <p>
     * Instructs the <code>{@link Scheduler}</code> that upon a mis-fire
     * situation, the <code>{@link CronTrigger}</code> wants to have it's
     * next-fire-time updated to the next time in the schedule after the
     * current time (taking into account any associated <code>{@link java.util.Calendar}</code>,
     * but it does not want to be fired now.
     * </p>
     */
    public static final int MISFIRE_INSTRUCTION_DO_NOTHING = 2;

    /*
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * 
     * Constants.
     * 
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */

    /**
     * Required for serialization support. Introduced in Quartz 1.6.1 to 
     * maintain compatibility after the introduction of hasAdditionalProperties
     * method. 
     *
     * @see java.io.Serializable
     */
    private static final long serialVersionUID = -8644953146451592766L;

    protected static final int YEAR_TO_GIVEUP_SCHEDULING_AT = CronExpression.MAX_YEAR;
    
    
    /*
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * 
     * Data members.
     * 
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */

    private MyCalendar cal = null;
    private CalendarEvent event = null;
    private Date startTime = null;
    private Date endTime = null;
    private Date nextFireTime = null;
    private Date previousFireTime = null;

    /*
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * 
     * Constructors.
     * 
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */

    /**
     * <p>
     * Create a <code>CronTrigger</code> with no settings.
     * </p>
     *
     * <p>
     * The start-time will also be set to the current time, and the time zone
     * will be set the the system's default time zone.
     * </p>
     */
    public MyTrigger() {
        super();
        setStartTime(new Date());
    }

    /**
     * <p>
     * Create a <code>CronTrigger</code> with the given name and default group.
     * </p>
     *
     * <p>
     * The start-time will also be set to the current time, and the time zone
     * will be set the the system's default time zone.
     * </p>
     *
     * @deprecated use a TriggerBuilder instead
     */
    @Deprecated
    public MyTrigger(String name) {
        this(name, null);
    }

    /**
     * <p>
     * Create a <code>CronTrigger</code> with the given name and group.
     * </p>
     *
     * <p>
     * The start-time will also be set to the current time, and the time zone
     * will be set the the system's default time zone.
     * </p>
     *
     * @deprecated use a TriggerBuilder instead
     */
    @Deprecated
    public MyTrigger(String name, String group) {
        super(name, group);
        setStartTime(new Date());
    }

    /**
     * <p>
     * Create a <code>CronTrigger</code> with the given name, group and
     * expression.
     * </p>
     *
     * <p>
     * The start-time will also be set to the current time, and the time zone
     * will be set the the system's default time zone.
     * </p>
     *
     * @deprecated use a TriggerBuilder instead
     */
    @Deprecated
    public MyTrigger(String name, String group, MyCalendar cal, CalendarEvent event)
            throws ParseException {

        super(name, group);

        setCronExpression(cal, event);

        setStartTime(new Date());
    }

    /**
     * <p>
     * Create a <code>CronTrigger</code> with the given name and group, and
     * associated with the identified <code>{@link org.quartz.JobDetail}</code>.
     * </p>
     *
     * <p>
     * The start-time will also be set to the current time, and the time zone
     * will be set the the system's default time zone.
     * </p>
     *
     * @deprecated use a TriggerBuilder instead
     */
    @Deprecated
    public MyTrigger(String name, String group, String jobName,
                           String jobGroup) {
        super(name, group, jobName, jobGroup);
        setStartTime(new Date());
    }

    /**
     * <p>
     * Create a <code>CronTrigger</code> with the given name and group,
     * associated with the identified <code>{@link org.quartz.JobDetail}</code>,
     * and with the given "cron" expression resolved with respect to the <code>TimeZone</code>.
     * </p>
     *
     * @deprecated use a TriggerBuilder instead
     */
    @Deprecated
    public MyTrigger(String name, String group, String jobName,
                           String jobGroup, MyCalendar cal, CalendarEvent event)
            throws ParseException {
        this(name, group, jobName, jobGroup, null, null, cal,
                event);
    }

    /**
     * <p>
     * Create a <code>CronTrigger</code> with fire time dictated by the
     * <code>cronExpression</code> resolved with respect to the specified
     * <code>timeZone</code> occurring from the <code>startTime</code> until
     * the given <code>endTime</code>.
     * </p>
     *
     * <p>
     * If null, the start-time will also be set to the current time. If null,
     * the time zone will be set to the system's default.
     * </p>
     *
     * @param name
     *          of the <code>Trigger</code>
     * @param group
     *          of the <code>Trigger</code>
     * @param jobName
     *          name of the <code>{@link org.quartz.JobDetail}</code>
     *          executed on firetime
     * @param jobGroup
     *          group of the <code>{@link org.quartz.JobDetail}</code>
     *          executed on firetime
     * @param startTime
     *          A <code>Date</code> set to the earliest time for the <code>Trigger</code>
     *          to start firing.
     * @param endTime
     *          A <code>Date</code> set to the time for the <code>Trigger</code>
     *          to quit repeat firing.
     * @param cal
     *          A cron expression dictating the firing sequence of the <code>Trigger</code>
     * @param event
     *          Specifies for which time zone the <code>cronExpression</code>
     *          should be interpreted, i.e. the expression 0 0 10 * * ?, is
     *          resolved to 10:00 am in this time zone.
     * @throws ParseException
     *           if the <code>cronExpression</code> is invalid.
     *
     * @deprecated use a TriggerBuilder instead
     */
    @Deprecated
    public MyTrigger(String name, String group, String jobName,
                     String jobGroup, Date startTime, Date endTime,
                     MyCalendar cal, CalendarEvent event) throws ParseException {
        super(name, group, jobName, jobGroup);

        setCronExpression(cal, event);

        if (startTime == null) {
            startTime = new Date();
        }
        setStartTime(startTime);
        if (endTime != null) {
            setEndTime(endTime);
        }
    }

    /*
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * 
     * Interface.
     * 
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */

    @Override
    public Object clone() {
        MyTrigger copy = (MyTrigger) super.clone();
        if (cal != null) {
            copy.setCronExpression(this.cal, this.event);
        }
        return copy;
    }

    public void setCronExpression(MyCalendar cal, CalendarEvent evt) {
        TimeZone origTz = getTimeZone();
        this.cal = cal;
        this.event = evt;
    }

    /* (non-Javadoc)
     * @see org.quartz.CronTriggerI#getCronExpression()
     */
    public String getCronExpression() {
        return cal == null ? null : cal.toString();
    }


    /**
     * <p>
     * Get the time at which the <code>CronTrigger</code> should occur.
     * </p>
     */
    @Override
    public Date getStartTime() {
        return this.startTime;
    }

    @Override
    public void setStartTime(Date startTime) {
        if (startTime == null) {
            throw new IllegalArgumentException("Start time cannot be null");
        }

        Date eTime = getEndTime();
        if (eTime != null && eTime.before(startTime)) {
            throw new IllegalArgumentException(
                    "End time cannot be before start time");
        }

        // round off millisecond...
        // Note timeZone is not needed here as parameter for
        // MyCalendar.getInstance(),
        // since time zone is implicit when using a Date in the setTime method.
        java.util.Calendar cl = java.util.Calendar.getInstance();
        cl.setTime(startTime);
        cl.set(java.util.Calendar.MILLISECOND, 0);

        this.startTime = cl.getTime();
    }

    /**
     * <p>
     * Get the time at which the <code>CronTrigger</code> should quit
     * repeating - even if repeastCount isn't yet satisfied.
     * </p>
     *
     * @see #getFinalFireTime()
     */
    @Override
    public Date getEndTime() {
        return this.endTime;
    }

    @Override
    public void setEndTime(Date endTime) {
        Date sTime = getStartTime();
        if (sTime != null && endTime != null && sTime.after(endTime)) {
            throw new IllegalArgumentException(
                    "End time cannot be before start time");
        }

        this.endTime = endTime;
    }

    /**
     * <p>
     * Returns the next time at which the <code>Trigger</code> is scheduled to fire. If
     * the trigger will not fire again, <code>null</code> will be returned.  Note that
     * the time returned can possibly be in the past, if the time that was computed
     * for the trigger to next fire has already arrived, but the scheduler has not yet
     * been able to fire the trigger (which would likely be due to lack of resources
     * e.g. threads).
     * </p>
     *
     * <p>The value returned is not guaranteed to be valid until after the <code>Trigger</code>
     * has been added to the scheduler.
     * </p>
     *
     * @see TriggerUtils#computeFireTimesBetween(org.quartz.spi.OperableTrigger, org.quartz.Calendar, java.util.Date, java.util.Date)
     */
    @Override
    public Date getNextFireTime() {
        return this.nextFireTime;
    }

    /**
     * <p>
     * Returns the previous time at which the <code>CronTrigger</code> 
     * fired. If the trigger has not yet fired, <code>null</code> will be
     * returned.
     */
    @Override
    public Date getPreviousFireTime() {
        return this.previousFireTime;
    }

    /**
     * <p>
     * Sets the next time at which the <code>CronTrigger</code> will fire.
     * <b>This method should not be invoked by client code.</b>
     * </p>
     */
    public void setNextFireTime(Date nextFireTime) {
        this.nextFireTime = nextFireTime;
    }

    /**
     * <p>
     * Set the previous time at which the <code>CronTrigger</code> fired.
     * </p>
     *
     * <p>
     * <b>This method should not be invoked by client code.</b>
     * </p>
     */
    public void setPreviousFireTime(Date previousFireTime) {
        this.previousFireTime = previousFireTime;
    }

    /* (non-Javadoc)
     * @see org.quartz.CronTriggerI#getTimeZone()
     */
    public TimeZone getTimeZone() {

        if(event != null) {
            return event.getTimeZone();
        }

        return TimeZone.getDefault();
    }

    /**
     * <p>
     * Returns the next time at which the <code>CronTrigger</code> will fire,
     * after the given time. If the trigger will not fire after the given time,
     * <code>null</code> will be returned.
     * </p>
     *
     * <p>
     * Note that the date returned is NOT validated against the related
     * org.quartz.MyCalendar (if any)
     * </p>
     */
    @Override
    public Date getFireTimeAfter(Date afterTime) {
        if (afterTime == null) {
            afterTime = new Date();
        }

        if (getStartTime().after(afterTime)) {
            afterTime = new Date(getStartTime().getTime() - 1000l);
        }

        if (getEndTime() != null && (afterTime.compareTo(getEndTime()) >= 0)) {
            return null;
        }

        Date pot = getTimeAfter(afterTime);
        if (getEndTime() != null && pot != null && pot.after(getEndTime())) {
            return null;
        }

        return pot;
    }

    /**
     * <p>
     * NOT YET IMPLEMENTED: Returns the final time at which the 
     * <code>CronTrigger</code> will fire.
     * </p>
     *
     * <p>
     * Note that the return time *may* be in the past. and the date returned is
     * not validated against org.quartz.calendar
     * </p>
     */
    @Override
    public Date getFinalFireTime() {
        Date resultTime;
        if (getEndTime() != null) {
            resultTime = getTimeBefore(new Date(getEndTime().getTime() + 1000l));
        } else {
            resultTime = (cal == null) ? null : null;//cal.getFinalFireTime();
        }

        if ((resultTime != null) && (getStartTime() != null) && (resultTime.before(getStartTime()))) {
            return null;
        }

        return resultTime;
    }

    /**
     * <p>
     * Determines whether or not the <code>CronTrigger</code> will occur
     * again.
     * </p>
     */
    @Override
    public boolean mayFireAgain() {
        return (getNextFireTime() != null);
    }

    @Override
    protected boolean validateMisfireInstruction(int misfireInstruction) {
        return misfireInstruction >= MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY && misfireInstruction <= MISFIRE_INSTRUCTION_DO_NOTHING;
    }

    /**
     * <p>
     * Updates the <code>CronTrigger</code>'s state based on the
     * MISFIRE_INSTRUCTION_XXX that was selected when the <code>CronTrigger</code>
     * was created.
     * </p>
     *
     * <p>
     * If the misfire instruction is set to MISFIRE_INSTRUCTION_SMART_POLICY,
     * then the following scheme will be used: <br>
     * <ul>
     * <li>The instruction will be interpreted as <code>MISFIRE_INSTRUCTION_FIRE_ONCE_NOW</code>
     * </ul>
     * </p>
     */
    @Override
    public void updateAfterMisfire(org.quartz.Calendar cal) {
        int instr = getMisfireInstruction();

        if(instr == Trigger.MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY)
            return;

        if (instr == MISFIRE_INSTRUCTION_SMART_POLICY) {
            instr = MISFIRE_INSTRUCTION_FIRE_ONCE_NOW;
        }

        if (instr == MISFIRE_INSTRUCTION_DO_NOTHING) {
            Date newFireTime = getFireTimeAfter(new Date());
            while (newFireTime != null && cal != null
                    && !cal.isTimeIncluded(newFireTime.getTime())) {
                newFireTime = getFireTimeAfter(newFireTime);
            }
            setNextFireTime(newFireTime);
        } else if (instr == MISFIRE_INSTRUCTION_FIRE_ONCE_NOW) {
            setNextFireTime(new Date());
        }
    }

    /**
     * <p>
     * Determines whether the date and (optionally) time of the given MyCalendar
     * instance falls on a scheduled fire-time of this trigger.
     * </p>
     *
     * <p>
     * Equivalent to calling <code>willFireOn(cal, false)</code>.
     * </p>
     *
     * @param test the date to compare
     *
     * @see #willFireOn(java.util.Calendar, boolean)
     */
    public boolean willFireOn(java.util.Calendar test) {
        return willFireOn(test, false);
    }

    /**
     * <p>
     * Determines whether the date and (optionally) time of the given MyCalendar
     * instance falls on a scheduled fire-time of this trigger.
     * </p>
     *
     * <p>
     * Note that the value returned is NOT validated against the related
     * org.quartz.MyCalendar (if any)
     * </p>
     *
     * @param test the date to compare
     * @param dayOnly if set to true, the method will only determine if the
     * trigger will fire during the day represented by the given MyCalendar
     * (hours, minutes and seconds will be ignored).
     * @see #willFireOn(java.util.Calendar)
     */
    public boolean willFireOn(java.util.Calendar test, boolean dayOnly) {

        test = (java.util.Calendar) test.clone();

        test.set(java.util.Calendar.MILLISECOND, 0); // don't compare millis.

        if(dayOnly) {
            test.set(java.util.Calendar.HOUR_OF_DAY, 0);
            test.set(java.util.Calendar.MINUTE, 0);
            test.set(java.util.Calendar.SECOND, 0);
        }

        Date testTime = test.getTime();

        Date fta = getFireTimeAfter(new Date(test.getTime().getTime() - 1000));

        if(fta == null)
            return false;

        java.util.Calendar p = java.util.Calendar.getInstance(test.getTimeZone());
        p.setTime(fta);

        int year = p.get(java.util.Calendar.YEAR);
        int month = p.get(java.util.Calendar.MONTH);
        int day = p.get(java.util.Calendar.DATE);

        if(dayOnly) {
            return (year == test.get(java.util.Calendar.YEAR)
                    && month == test.get(java.util.Calendar.MONTH)
                    && day == test.get(java.util.Calendar.DATE));
        }

        while(fta.before(testTime)) {
            fta = getFireTimeAfter(fta);
        }

        return fta.equals(testTime);
    }

    /**
     * <p>
     * Called when the <code>{@link Scheduler}</code> has decided to 'fire'
     * the trigger (execute the associated <code>Job</code>), in order to
     * give the <code>Trigger</code> a chance to update itself for its next
     * triggering (if any).
     * </p>
     *
     * @see #executionComplete(JobExecutionContext, JobExecutionException)
     */
    @Override
    public void triggered(org.quartz.Calendar calendar) {
        previousFireTime = nextFireTime;
        nextFireTime = getFireTimeAfter(nextFireTime);

        while (nextFireTime != null && calendar != null
                && !calendar.isTimeIncluded(nextFireTime.getTime())) {
            nextFireTime = getFireTimeAfter(nextFireTime);
        }
    }

    /**
     *
     * @see AbstractTrigger#updateWithNewCalendar(org.quartz.Calendar, long)
     */
    @Override
    public void updateWithNewCalendar(org.quartz.Calendar calendar, long misfireThreshold)
    {
        nextFireTime = getFireTimeAfter(previousFireTime);

        if (nextFireTime == null || calendar == null) {
            return;
        }

        Date now = new Date();
        while (nextFireTime != null && !calendar.isTimeIncluded(nextFireTime.getTime())) {

            nextFireTime = getFireTimeAfter(nextFireTime);

            if(nextFireTime == null)
                break;

            //avoid infinite loop
            // Use gregorian only because the constant is based on Gregorian
            java.util.Calendar c = new java.util.GregorianCalendar();
            c.setTime(nextFireTime);
            if (c.get(java.util.Calendar.YEAR) > YEAR_TO_GIVEUP_SCHEDULING_AT) {
                nextFireTime = null;
            }

            if(nextFireTime != null && nextFireTime.before(now)) {
                long diff = now.getTime() - nextFireTime.getTime();
                if(diff >= misfireThreshold) {
                    nextFireTime = getFireTimeAfter(nextFireTime);
                }
            }
        }
    }

    /**
     * <p>
     * Called by the scheduler at the time a <code>Trigger</code> is first
     * added to the scheduler, in order to have the <code>Trigger</code>
     * compute its first fire time, based on any associated calendar.
     * </p>
     *
     * <p>
     * After this method has been called, <code>getNextFireTime()</code>
     * should return a valid answer.
     * </p>
     *
     * @return the first time at which the <code>Trigger</code> will be fired
     *         by the scheduler, which is also the same value <code>getNextFireTime()</code>
     *         will return (until after the first firing of the <code>Trigger</code>).
     *         </p>
     */
    @Override
    public Date computeFirstFireTime(org.quartz.Calendar calendar) {
        nextFireTime = getFireTimeAfter(new Date(getStartTime().getTime() - 1000l));

        while (nextFireTime != null && calendar != null
                && !calendar.isTimeIncluded(nextFireTime.getTime())) {
            nextFireTime = getFireTimeAfter(nextFireTime);
        }

        return nextFireTime;
    }

    /* (non-Javadoc)
     * @see org.quartz.CronTriggerI#getExpressionSummary()
     */
    public String getExpressionSummary() {
        return cal == null ? null : cal.toString();
    }

    /**
     * Used by extensions of CronTrigger to imply that there are additional 
     * properties, specifically so that extensions can choose whether to be 
     * stored as a serialized blob, or as a flattened CronTrigger table. 
     */
    public boolean hasAdditionalProperties() {
        return false;
    }
    /**
     * Get a {@link ScheduleBuilder} that is configured to produce a 
     * schedule identical to this trigger's schedule.
     *
     * @see #getTriggerBuilder()
     */

    @Override
    public ScheduleBuilder<T> getScheduleBuilder() {
        return null;
    }

    ////////////////////////////////////////////////////////////////////////////
    //
    // Computation Functions
    //
    ////////////////////////////////////////////////////////////////////////////

    protected Date getTimeAfter(Date afterTime) {
        return (cal == null) ? null : null;//cal.getTimeAfter(afterTime);
    }

    /**
     * NOT YET IMPLEMENTED: Returns the time before the given time
     * that this <code>CronTrigger</code> will fire.
     */
    protected Date getTimeBefore(Date eTime) {
        return (cal == null) ? null : null;//cal.getTimeBefore(eTime);
    }
}
