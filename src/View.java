import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by Andrew on 23.04.2016.
 */
public class View extends DesignerComponents implements IView{
    public View()
    {
        InitializeComponents();
        this.setVisible(true);

        BufferedImage myPicture = null;

        File image = new File("img/NoImage.jpg");

        if (image.exists()){
            try {
                myPicture = ImageIO.read(image);
            } catch (IOException e) {
                e.printStackTrace();
            }

            setOriginalImage(myPicture);
            setResultingImage(myPicture);
        }

        this.pack();

        scrollBarBrightness.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                float result;
                result = scrollBarBrightness.getValue()/10f;
                labelBrightness.setText("Brightness: " + result);
            }
        });

        scrollBarContrast.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                float result;
                result = scrollBarContrast.getValue()/10f;
                labelContrast.setText("Contrast: " + result);
            }
        });

        scrollBarBinarizationThreshold.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                int result;
                result =
                        (scrollBarBinarizationThreshold.getValue()<<16)|
                                (scrollBarBinarizationThreshold.getValue()<<8)|
                                scrollBarBinarizationThreshold.getValue();
                labelBinarizationThreshold.setText("Threshold: " + Integer.toHexString(result));
            }
        });

        scrollBarCompressionRate.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                int result;
                result = scrollBarCompressionRate.getValue();
                labelCompressionRate.setText("Compress: " + result);
            }
        });
    }

    @Override
    public void setOriginalImage(BufferedImage image){
        labelPictureOriginal.setIcon(new ImageIcon(image));
        labelPictureOriginal.setText("");
    }

    @Override
    public void setResultingImage(BufferedImage image){
        labelPictureResulting.setIcon(new ImageIcon(image));
        labelPictureResulting.setText("");
    }

    @Override
    public void addCopyListener(ActionListener listener) {
        buttonProcess.addActionListener(listener);
    }

    @Override
    public void addAdjustmentListener(AdjustmentListener listener) {
        scrollBarBinarizationThreshold.addAdjustmentListener(listener);
        scrollBarBrightness.addAdjustmentListener(listener);
        scrollBarContrast.addAdjustmentListener(listener);
        scrollBarCompressionRate.addAdjustmentListener(listener);
    }

    @Override
    public boolean confirmBinarization() {
        return CheckBoxBinarization.isSelected();
    }

    @Override
    public boolean confirmBrightnessCorrection() {
        return CheckBoxBrightness.isSelected();
    }

    @Override
    public boolean confirmGrey() {
        return CheckBoxGrey.isSelected();
    }

    @Override
    public boolean confirmCompression() {
        return CheckBoxCompression.isSelected();
    }

    @Override
    public boolean confirmSubtraction() {
        return CheckBoxSubtraction.isSelected();
    }

    @Override
    public float getBrightness() {
        float result;
        result = scrollBarBrightness.getValue()/10f;
        return result;
    }

    @Override
    public float getContrast() {
        float result;
        result = scrollBarContrast.getValue()/10f;
        return result;
    }

    @Override
    public int getCompression() {
        int result;
        result = scrollBarCompressionRate.getValue();
        return result;
    }

    @Override
    public int getBinarizationThreshold() {
        int result;
        result =
                (scrollBarBinarizationThreshold.getValue()<<16)|
                (scrollBarBinarizationThreshold.getValue()<<8)|
                scrollBarBinarizationThreshold.getValue();
        return result;
    }

    @Override
    public BufferedImage getOriginalImage() {
        Icon icon = labelPictureOriginal.getIcon();

        return getBufferedImage(icon);
    }

    @Override
    public BufferedImage getResultingImage() {
        Icon icon = labelPictureResulting.getIcon();

        return getBufferedImage(icon);
    }

    private BufferedImage getBufferedImage(Icon icon) {
        BufferedImage bi = new BufferedImage(
                icon.getIconWidth(),
                icon.getIconHeight(),
                BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.createGraphics();
        // paint the Icon to the BufferedImage.
        icon.paintIcon(null, g, 0,0);
        g.dispose();

        return bi;
    }
}
