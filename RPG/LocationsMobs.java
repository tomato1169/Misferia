package azerot.azerot.RPG;

import azerot.azerot.azerot;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class LocationsMobs {
    String name;
    String location;
    static Map<String, LocationsMobs> loc = new HashMap<>();
    public LocationsMobs(ResultSet result) throws SQLException {
        try {
            if (result == null) {
                this.name = null;
                this.location = null;
            } else {
                this.name = result.getString("name");
                this.location = result.getString("location");
            }
            loc.put(name, this);

        } catch (Throwable $ex) {
            throw $ex;
        }
    }
    public static LocationsMobs getLocationsmobs(String name) {
        return loc.get(name);
    }

    public static Map<String, LocationsMobs> getLocationsMobs() {
        return loc;
    }

    public String getLocation() {
        return location;
    }
}
