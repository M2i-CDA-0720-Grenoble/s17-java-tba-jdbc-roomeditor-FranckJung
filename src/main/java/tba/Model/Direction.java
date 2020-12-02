package tba.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import tba.Utils.DatabaseHandler;

public class Direction {
    
    private int id;
    private String name;
    private String command;

    public Direction()
    {
        id = 0;
        name = "";
        command = "";
    }

    public Direction(int id, String name, String command)
    {
        this.id = id;
        this.name = name;
        this.command = command;
    }

    public static List<Direction> findAll()
    {
        try {
            List<Direction> directions = new ArrayList<>();
            // Envoie une requête en base de données et récupère les résultats
            ResultSet set = DatabaseHandler.query("SELECT * FROM `direction`");
            // Tant qu'il reste des résultats non traités, prend le résultat suivant...
            while (set.next()) {
                // ... et crée un objet à partir des colonnes présentes dans ce résultat
                Direction direction = new Direction(
                    set.getInt("id"),
                    set.getString("name"),
                    set.getString("command")
                );
                // Ajoute l'objet à la liste
                directions.add(direction);
            }
            // Renvoie la liste
            return directions;
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    public static Direction findById(int id)
    {
        try {
            // Envoie une requête en base de données
            DatabaseHandler dbHandler = DatabaseHandler.getInstance();
            PreparedStatement statement = dbHandler.getConnection().prepareStatement("SELECT * FROM `direction` WHERE `id` = ?"
                // Rajouter ces deux lignes si on rencontre une erreur de type "Operation not allowed for a result set of type ResultSet.TYPE_FORWARD_ONLY"
                // ,ResultSet.TYPE_SCROLL_SENSITIVE
                // ,ResultSet.CONCUR_UPDATABLE
            );
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
    
            // Comme on sait que la requête peut uniquement renvoyer un seul résultat (s'il existe),
            // ou aucun (s'il n'existe pas), cherche le premier résultat de la requête...
            if (set.first()) {
                // ...et renvoie un nouvel objet à partir de ses données
                return new Direction(
                    set.getInt("id"),
                    set.getString("name"),
                    set.getString("command")
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
            PreparedStatement statement = DatabaseHandler.getInstance().getConnection().prepareStatement("INSERT INTO `direction` (`name`, `command`) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, name);
            statement.setString(2, command);
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
            PreparedStatement statement = DatabaseHandler.getInstance().getConnection().prepareStatement("UPDATE `direction` SET `name` = ?, `command` = ? WHERE `id` = ?");
            statement.setString(1, name);
            statement.setString(2, command);
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
            PreparedStatement statement = DatabaseHandler.getInstance().getConnection().prepareStatement("DELETE FROM `direction` WHERE `id` = ?");
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
        return "[Direction #" + id + "] { name: " + name + ", command: " + command + " }";
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

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

}
