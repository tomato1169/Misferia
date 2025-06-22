package azerot.azerot;

import java.sql.SQLException;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class Task extends BukkitRunnable {
    public static final HashMap<String, Task> tasks = new HashMap<>();

    private int periods;

    private int delay;

    private int period;

    private final String name;

    public Task(String name, int periods, int delayInMilliseconds, int periodInMilliseconds) {
        if (delayInMilliseconds != 0 && delayInMilliseconds < 50)
            throw new IllegalArgumentException("Delay time must be 0 or not less than 50ms!");
        if (periodInMilliseconds < 50)
            throw new IllegalArgumentException("Period time must be not less than 50ms!");
        this.name = name;
        if (periods == 0)
            this.periods = -1;
        this.periods = periods;
        this.delay = delayInMilliseconds / 50;
        this.period = periodInMilliseconds / 50;
        runTaskTimer((Plugin)azerot.getInstance(), this.delay, this.period);
        tasks.put(name, this);
    }

    public abstract void onTick() throws SQLException;

    public void run() {
        if (this.periods > 0)
            this.periods--;
        try {
            onTick();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (this.periods == 0)
            cancel();
    }

    public int getPeriods() {
        return this.periods;
    }

    public int getDelayInTicks() {
        return this.delay;
    }

    public int getPeriodInTicks() {
        return this.period;
    }

    public String getName() {
        return this.name;
    }

    public void setPeriods(int periods) {
        this.periods = periods;
    }

    public void cancel() {
        super.cancel();
        tasks.remove(getName());
    }

    public static Task getTask(String name) {
        return tasks.get(name);
    }

    public static void schedule(Runnable r) {
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)azerot.getInstance(), r);
    }

    public static void schedule(Runnable r, long delay) {
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)azerot.getInstance(), r, delay);
    }

    public static void schedule(Runnable r, long delay, long period) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)azerot.getInstance(), r, delay, period);
    }

    public static void runAsync(Runnable r) {
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)azerot.getInstance(), r);
    }
}