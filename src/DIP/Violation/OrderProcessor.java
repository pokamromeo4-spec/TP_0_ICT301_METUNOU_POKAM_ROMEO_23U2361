package DIP.Violation;


import DIP.Solution.DataBase;

public class OrderProcessor {

    private MySQLDatabase database;

    public OrderProcessor(DataBase dataBase) {

        this.database = new MySQLDatabase();

    }

    public void processOrder(String order) {

        database.save(order);

    }

}
