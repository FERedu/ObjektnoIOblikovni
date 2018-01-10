package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

public class ApplicationFrame extends JFrame{
	
	
	private final Webcam webcam;

	public ApplicationFrame() {
		//General frame settings
		setBounds(500, 250, 600, 600);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(true);

		//Initialize web-cam
		webcam = Webcam.getDefault();
		webcam.setViewSize(WebcamResolution.VGA.getSize());
		
		//UI initializations
		setTitle("Old selfie app");
		
		WebcamPanel webcamPanel = new WebcamPanel(webcam);
		webcamPanel.setFPSDisplayed(true);
		webcamPanel.setImageSizeDisplayed(true);

		
		JButton snapButton = new JButton(new AbstractAction("Snap") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				BufferedImage rawImage = webcam.getImage();
				ImageIcon image = new ImageIcon(rawImage);
				
				int choice = JOptionPane.showConfirmDialog(null, new JLabel(image), "Save?", JOptionPane.YES_NO_OPTION);
				if(choice==JOptionPane.YES_OPTION) {
					
					JFileChooser saveDialog = new JFileChooser("pictures/");
					choice = saveDialog.showSaveDialog(null);
					if(choice == JFileChooser.APPROVE_OPTION) {
						try {
							File selectedFile = saveDialog.getSelectedFile();
							if(selectedFile!=null) {
								if(!Files.exists(selectedFile.toPath())) {
									ImageIO.write(rawImage, "PNG", selectedFile);
								}
								else {
									String message = selectedFile.getName() + " already exists!";
									JOptionPane.showMessageDialog(null, message, "Warning", JOptionPane.WARNING_MESSAGE);
								}
							}
							else {
								String message = "Selected file was null!";
								JOptionPane.showMessageDialog(null, message, "Warning", JOptionPane.WARNING_MESSAGE);
							}
						} catch (IOException e1) {
							//TODO: do something useful
						}				
					}
				}
			}
		});
		
		JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		actionPanel.add(snapButton);
		
		setLayout(new BorderLayout());
		add(webcamPanel,BorderLayout.CENTER);
		add(actionPanel,BorderLayout.SOUTH);
		
		//
		pack();
		
		//adding window listeners
		addWindowListener(new WindowClosingManager());
	}

	
	//=================================================================================================
	//Listeners
	
	private final class WindowClosingManager extends WindowAdapter {
				
		@Override
		public void windowClosing(WindowEvent e) {
			webcam.close();
			for(AutoCloseable closeable:closingHooks)
				try {
					closeable.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			removeWindowListener(this);
		}
	}

	
	private final List<AutoCloseable> closingHooks = new ArrayList<>();

	public void addCloseHook(AutoCloseable closable) {
		closingHooks.add(closable);
	}

}
