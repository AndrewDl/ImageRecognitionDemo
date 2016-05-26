/**
 * Created by Andrew on 23.04.2016.
 */

public class SwingFormDemo {
        public static void main(String[] args) {
            IView view = new View();
            IModel model = new Model();
            IController presenter = new Controller(view, model);

        }

}


