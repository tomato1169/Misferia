package azerot.azerot;

import lombok.Getter;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
@Getter
@Setter
public class RPGRecipesContainer
{
    private int id;

    private String items1;

    private String items2;

    private String craft;
    private static final Map<Integer, RPGRecipesContainer> rpgrecipes = new HashMap<>();



    public RPGRecipesContainer(ResultSet result) {
        try {
            if (result == null) {
                this.id = 0;
                this.items1 = null;
                this.items2 = null;
                this.craft = null;


            } else {
                this.id = result.getInt("id");
                this.items1 = result.getString("items1");
                this.items2 = result.getString("items2");
                this.craft = result.getString("craft");
                rpgrecipes.put(this.id, this);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static Map<Integer, RPGRecipesContainer> getRpgrecipes() {
        return rpgrecipes;
    }
    public static RPGRecipesContainer RPGRecipesContainer(int id) {
        return rpgrecipes.get(Integer.valueOf(id));
    }
}
