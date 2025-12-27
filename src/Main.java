import DIP.Solution.DataBase;
import DIP.Solution.MongoDBDatabase;
import DIP.Solution.MySQLDatabase;
import DIP.Violation.OrderProcessor;
import ISP.Solution.HumanWorkerISP;
import ISP.Solution.RobotWorkerISP;
import ISP.Violation.HumanWorker;
import ISP.Violation.RobotWorker;
import LSP.Solution.RectangleLSPs;
import LSP.Solution.ShapeLSP;
import LSP.Solution.SquareLSPs;
import LSP.Violation.RectangleLSPV;
import LSP.Violation.Square;
import OCP.Solution.CircleOCP;
import OCP.Solution.RectangleOCP;
import OCP.Solution.Shape;
import OCP.Violation.AreaCalculator;
import OCP.Violation.Circle;
import OCP.Violation.Rectangle;
import SRP.Solution.BookBusnessLogic;
import SRP.Solution.BookPrinter;
import SRP.Solution.BookSRP;
import SRP.Solution.BookSave;
import SRP.Violation.Book;

public class Main {
    public static void main(String[] args) {
        // --- SRP ---
        System.out.println("=== SRP avec violation ===");
        Book book = new Book(" les principes solid", "Etudiants de M1-G1", "Revision des principes Solid");
        book.printToScreen();
        book.saveToDatabase();
        book.emprunter("pokam");

        System.out.println("\n=== SRP sans violation ===");
        BookSRP bookSRP = new BookSRP("les principes SOLID", "Etudiants de M1-G1", "Revision des principes Solid");
        new BookSave().saveToDatabase(bookSRP);
        new BookBusnessLogic().emprunter(bookSRP, "pokam");
        new BookPrinter().printToScreen(bookSRP);

        // --- OCP ---
        System.out.println("\n=== OCP avec violation ===");
        AreaCalculator areaCalculator = new AreaCalculator();
        System.out.println("Aire Rectangle: " + areaCalculator.calculateArea(new OCP.Violation.Rectangle(12, 24)));

        System.out.println("\n=== OCP sans violation ===");
        Shape rect = new RectangleOCP(12, 47);
        System.out.println("Aire Rectangle: " + rect.calculateArea());

        // --- LSP ---
        System.out.println("\n=== LSP avec violation ===");
        RectangleLSPV rectangleLSPV = new RectangleLSPV();
        rectangleLSPV.setHeight(4);
        rectangleLSPV.setWidth(8);
        System.out.println("Aire du rectangle: " + rectangleLSPV.getArea());

        // --- ISP ---
        System.out.println("\n=== ISP avec violation ===");
        HumanWorker humanWorker = new HumanWorker();
        humanWorker.work();

        System.out.println("\n=== ISP sans violation ===");
        new HumanWorkerISP().work();

        // 1. DIP SANS VIOLATION (Solution)
        System.out.println("\n=== DIP sans violation ===");
        DataBase mySqlDB = new MySQLDatabase();
        // Utilisation explicite du package pour éviter toute confusion
        DIP.Solution.OrderProcessor orderProcessorSolution = new DIP.Solution.OrderProcessor(mySqlDB);
        orderProcessorSolution.processOrder("Données via MySQL");

        // 2. DIP AVEC VIOLATION
        System.out.println("\n=== DIP avec violation ===");
        DataBase dbViolation = new MySQLDatabase();
        DIP.Violation.OrderProcessor orderViolation = new DIP.Violation.OrderProcessor(dbViolation);
        orderViolation.processOrder("Données avec violation");
    }
}