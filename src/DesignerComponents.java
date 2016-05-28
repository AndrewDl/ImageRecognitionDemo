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
    protected JPanel panelControls = new JPanel(new GridLayout(2,1));
    protected JPanel panelCheckBoxes = new JPanel(new GridLayout(6,1));

    protected  JButton buttonProcess = new JButton("Process");

    protected  JCheckBox CheckBoxBrightness = new JCheckBox("Brightness/Contrast",false);
    protected  JCheckBox CheckBoxGrey = new JCheckBox("To Grey",false);
    protected  JCheckBox CheckBoxCompression = new JCheckBox("Compression",false);
    protected  JCheckBox CheckBoxBinarization = new JCheckBox("Binarization",false);
    protected  JCheckBox CheckBoxSubtraction = new JCheckBox("Subtraction",false);

    protected JLabel labelBrightness = new JLabel("Brightness: 1.0");
    protected JLabel labelContrast = new JLabel("Contrast: 1.0");
    protected JLabel labelCompressionRate = new JLabel("Compress: 1");
    protected JLabel labelBinarizationThreshold = new JLabel("Threshold: 80");

    protected JScrollBar scrollBarBrightness = new JScrollBar(JScrollBar.HORIZONTAL,10,0,0,30);
    protected JScrollBar scrollBarContrast = new JScrollBar(JScrollBar.HORIZONTAL,10,0,0,30);
    protected JScrollBar scrollBarCompressionRate = new JScrollBar(JScrollBar.HORIZONTAL,1,0,1,5);
    protected JScrollBar scrollBarBinarizationThreshold = new JScrollBar(JScrollBar.HORIZONTAL,0x50,0,0,0xFF);

    protected JTabbedPane tabbedPaneSettings = new JTabbedPane();

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

        //adding checkboxes
        panelCheckBoxes.add(buttonProcess);

        panelCheckBoxes.add(CheckBoxBrightness);
        panelCheckBoxes.add(CheckBoxGrey);
        panelCheckBoxes.add(CheckBoxCompression);
        panelCheckBoxes.add(CheckBoxBinarization);
        panelCheckBoxes.add(CheckBoxSubtraction);

        //building setting controls

        //brightness/contrast
        JPanel panelBrightness = new JPanel(new GridLayout(4,1));

        panelBrightness.add(labelBrightness);
        panelBrightness.add(scrollBarBrightness);
        panelBrightness.add(labelContrast);
        panelBrightness.add(scrollBarContrast);

        //Compression
        JPanel panelCompression = new JPanel(new GridLayout(4,1));

        panelBrightness.add(labelCompressionRate);
        panelBrightness.add(scrollBarCompressionRate);

        //Binarization
        JPanel panelBinarization = new JPanel(new GridLayout(4,1));

        panelBrightness.add(labelBinarizationThreshold);
        panelBrightness.add(scrollBarBinarizationThreshold);

        //building a tabbed pane
        tabbedPaneSettings.addTab("Brightness/Contrast", panelBrightness);
        tabbedPaneSettings.addTab("Compression", panelCompression);
        tabbedPaneSettings.addTab("Binarization", panelBinarization);

        panelControls.add(panelCheckBoxes);
        panelControls.add(panelBrightness);

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;

        container.add(panelControls,c);

        this.pack();
    }
}
