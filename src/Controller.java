import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;

/**
 * Created by Andrew on 23.04.2016.
 */
public class Controller implements IController {
    IView view;
    IModel model;
    public Controller(IView view, IModel model){
        this.view = view;
        this.model = model;

        String path = "CamModel/home/Image03.jpg";
        File imageFile = new File(path);

        view.addCopyListener(
            new ButtonCopyListener(){
                @Override public void actionPerformed(ActionEvent e){
                    BufferedImage img = view.getOriginalImage();
                    BufferedImage groundImage = null;
                    try {
                        groundImage = ImageIO.read(new File("CamModel\\home\\Image02.jpg"));
                    }
                    catch (IOException ex) {
                    }

                    //BufferedImage img = ImageProcessor.imageToGrey(view.getOriginalImage());
                    //to grey
                    groundImage = ImageProcessor.imageToGrey(groundImage);
                    img = ImageProcessor.imageToGrey(img);

                    //image compression
                    groundImage = ImageProcessor.Compress(groundImage,1);
                    img = ImageProcessor.Compress(img,1);

                    //image binarization
                    groundImage = ImageProcessor.binarizationTreshold(groundImage,0x656565);
                    img = ImageProcessor.binarizationTreshold(img,0x656565);

                    //image subtraction

                    img = ImageProcessor.ToBufferedImage( MotionRecogniser.subtract(ImageProcessor.ToRGBArray(groundImage),ImageProcessor.ToRGBArray(img)) );

                    view.setResultingImage(img);
                }
            }
        );

        if (imageFile.exists()) {
            try {
                this.view.setOriginalImage(ImageIO.read(imageFile));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private class ButtonCopyListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
