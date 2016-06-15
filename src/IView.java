import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.image.BufferedImage;

/**
 * Created by Andrew on 23.04.2016.
 */
public interface IView {
    void setOriginalImage(BufferedImage image);

    void setResultingImage(BufferedImage image);

    void addCopyListener(ActionListener listener);

    void addAdjustmentListener(AdjustmentListener listener);

    boolean confirmBinarization();
    boolean confirmBrightnessCorrection();
    boolean confirmGrey();
    boolean confirmCompression();
    boolean confirmSubtraction();

    float getBrightness();
    float getContrast();
    int getCompression();
    int getBinarizationThreshold();

    void setLabelInfo(String s);

    BufferedImage getOriginalImage();

    BufferedImage getResultingImage();
}
