import javax.swing.*;
import java.awt.*;

/**
 * Created by Andrew on 23.04.2016.
 */
public abstract class DesignerComponents extends JFrame {

    protected JLabel labelPictureOriginal = new JLabel("Original picture");
    protected JLabel labelPictureResulting = new JLabel("Resulting picture");

    protected JPanel panelPictureOriginal = new JPanel();
    protected JPanel panelPictureResulting = new JPanel();
    protected JPanel panelControls = new JPanel();

    protected  JButton buttonCopy = new JButton("Copy");

    public void InitializeComponents(){
        this.setTitle("Swing Demo");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridBagLayout());

        GridBagConstraints c;

        //specifying panels
        //panelPictureOriginal
        panelPictureOriginal.add(labelPictureOriginal);
        panelPictureOriginal.setBorder(BorderFactory.createTitledBorder("Original"));
        panelPictureOriginal.setSize(640,480);

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;

        container.add(panelPictureOriginal,c);

        //panelPictureResulting
        panelPictureResulting.add(labelPictureResulting);
        panelPictureResulting.setBorder(BorderFactory.createTitledBorder("Resulting"));
        panelPictureResulting.setSize(640,480);

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;

        container.add(panelPictureResulting,c);

        //panelControls
        panelControls.setBorder(BorderFactory.createTitledBorder("Controls"));
        panelControls.add(buttonCopy);

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;

        container.add(panelControls,c);

        this.pack();
    }
}
