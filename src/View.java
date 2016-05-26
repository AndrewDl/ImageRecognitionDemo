import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
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
        buttonCopy.addActionListener(listener);
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
