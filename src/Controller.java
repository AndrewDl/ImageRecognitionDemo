import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Andrew on 23.04.2016.
 */
public class Controller implements IController {
    IView view;
    IModel model;
    public Controller(IView view, IModel model) {
        this.view = view;
        this.model = model;

        String path = "CamModel/home/Image02.jpg";
        File imageFile = new File(path);

        if (imageFile.exists()) {
            try {
                this.view.setOriginalImage(ImageIO.read(imageFile));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        view.addCopyListener(
                new ButtonCopyListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Action();
                    }
                }
        );

        view.addAdjustmentListener(
                new ScrollAdjustmentListener(){
                    @Override
                    public void adjustmentValueChanged(AdjustmentEvent e) {
                        Action();
                    }
                }
        );
    }

    private void Action(){
        BufferedImage img = view.getOriginalImage();
        BufferedImage groundImage = null;
        try {
            groundImage = ImageIO.read(new File("CamModel\\home\\Image03.jpg"));
        }
        catch (IOException ex) {
        }

        boolean allowBrightness = view.confirmBrightnessCorrection();
        boolean allowGrey = view.confirmGrey();
        boolean allowCompression = view.confirmCompression();
        boolean allowBinarization = view.confirmBinarization();
        boolean allowSubtraction = view.confirmSubtraction();

        float brightness = view.getBrightness();
        float contrast = view.getContrast();
        int compressionRate = view.getCompression();
        int threshold = view.getBinarizationThreshold();

        //BufferedImage img = ImageProcessor.imageToGrey(view.getOriginalImage());

        //incBrightness
        if (allowBrightness) {

            groundImage = ImageProcessor.IncreaseBrightness(groundImage, brightness, contrast);
            img = ImageProcessor.IncreaseBrightness(img, brightness, contrast);
        }

        //to grey
        if (allowGrey) {
            groundImage = ImageProcessor.imageToGrey(groundImage);
            img = ImageProcessor.imageToGrey(img);
        }

        //image compression
        if (allowCompression) {

            groundImage = ImageProcessor.Compress(groundImage, compressionRate);
            img = ImageProcessor.Compress(img, compressionRate);
        }

        //image binarization
        if (allowBinarization) {
            groundImage = ImageProcessor.binarizationThreshold(groundImage, threshold);
            img = ImageProcessor.binarizationThreshold(img, threshold);
        }
        //image subtraction

        if (allowSubtraction) {
            img = ImageProcessor.ToBufferedImage(MotionRecogniser.subtract(ImageProcessor.ToRGBArray(groundImage), ImageProcessor.ToRGBArray(img)));
        }
        view.setResultingImage(img);
    }


    private class ButtonCopyListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class ScrollAdjustmentListener implements AdjustmentListener{

        @Override
        public void adjustmentValueChanged(AdjustmentEvent e) {

        }
    }

}
