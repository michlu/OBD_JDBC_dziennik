import controller.Controller;
import util.DBInit;

/**
 * @author Michał Nowiński
 * @sience 15.05.2017
 */
public class Main {

    public static void main(String[] args) {

        DBInit.initTables();
//        DBInit.initData();
        Controller controller = new Controller();

        controller.start();

    }
}
