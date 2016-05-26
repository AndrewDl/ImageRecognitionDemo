import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * Created by Andrew on 23.04.2016.
 */
public interface IView {
    void setOriginalImage(BufferedImage image);

    void setResultingImage(BufferedImage image);

    void addCopyListener(ActionListener listener);

    BufferedImage getOriginalImage();

    BufferedImage getResultingImage();
}
