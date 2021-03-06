package tba.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import tba.Utils.DatabaseHandler;

public class Room
{

    private int id;
    private String name;
    private String description;

    public Room(){
        id=0;
        name="";
        description="";
    }

    public Room(int id, String name, String description)
    {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static List<Room> findAll()
    {
        try {
            // Crée une liste prête à accueillir les objets qui vont être créés à partir des données récupérées
            List<Room> rooms = new ArrayList<>();
            // Envoie une requête en base de données et récupère les résultats
            ResultSet set = DatabaseHandler.query("SELECT * FROM `room`");
            // Tant qu'il reste des résultats non traités, prend le résultat suivant...
            while (set.next()) {
                // ... et crée un objet à partir des colonnes présentes dans ce résultat
                Room room = new Room(
                    set.getInt("id"),
                    set.getString("name"),
                    set.getString("description")
                );
                // Ajoute l'objet à la liste
                rooms.add(room);
            }
            // Renvoie la liste
            return rooms;
        }
        catch (SQLException exception) {
            System.out.println(exception);
            System.exit(1);
            return null;
        }
    }

    public static Room findById(int id)
    {
        try {
            // Envoie une requête en base de données
            DatabaseHandler dbHandler = DatabaseHandler.getInstance();
            PreparedStatement statement = dbHandler.getConnection().prepareStatement("SELECT * FROM `room` WHERE `id` = ?"
                // Rajouter ces deux lignes si on rencontre une erreur de type "Operation not allowed for a result set of type ResultSet.TYPE_FORWARD_ONLY"
                ,ResultSet.TYPE_SCROLL_SENSITIVE
                ,ResultSet.CONCUR_UPDATABLE
            );
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
    
            // Comme on sait que la requête peut uniquement renvoyer un seul résultat (s'il existe),
            // ou aucun (s'il n'existe pas), cherche le premier résultat de la requête...
            if (set.first()) {
                // ...et renvoie un nouvel objet à partir de ses données
                return new Room(
                    set.getInt("id"),
                    set.getString("name"),
                    set.getString("description")
                );
            // Si la requête ne renvoie aucun résultat, renvoie null
            } else {
                return null;
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    public void save()
    {
        if (id == 0) {
            insert();
        } else {
            update();
        }
    }

    private void insert()
    {
        try {
            PreparedStatement statement = DatabaseHandler.getInstance().getConnection().prepareStatement("INSERT INTO `room` (`name`, `description`) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, name);
            statement.setString(2, description);
            statement.executeUpdate();
            ResultSet set = statement.getGeneratedKeys();
            if (set.first()) {
                id = set.getInt(1);
                return;
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            System.exit(1);
        }
    }

    private void update()
    {
        try {
            PreparedStatement statement = DatabaseHandler.getInstance().getConnection().prepareStatement("UPDATE `room` SET `name` = ?, `descritpion` = ? WHERE `id` = ?");
            statement.setString(1, name);
            statement.setString(2, description);
            statement.setInt(3, id);
            statement.executeUpdate();
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            System.exit(1);
        }
    }

    public void delete()
    {
        try {
            PreparedStatement statement = DatabaseHandler.getInstance().getConnection().prepareStatement("DELETE FROM `room` WHERE `id` = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
            id = 0;
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public String toString()
    {
        return "[Room #" + id + "] { name: " + name + ", description: " + description +" }";
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
