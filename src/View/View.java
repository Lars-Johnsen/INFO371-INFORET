package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class View extends JFrame {

	private JPanel			northPanel = new JPanel();
	private JPanel			northWestPanel = new JPanel();
	private JPanel			northEastPanel = new JPanel();
	private JScrollPane		westPanel = new JScrollPane();
	private JPanel			eastPanel = new JPanel();
	private JPanel			detailsPanel = new JPanel();
	private JPanel 			imagePanel = new JPanel();
	private JPanel			southPanel = new JPanel();
	private JTextField		inputText = new JTextField();
	private JButton			goButton = new JButton("Go!");
	private static JButton			browseButton = new JButton("Browse");
	private static DefaultListModel results      = new DefaultListModel();
	private JList            resultList   = new JList(results);

	private String stringForTest = "DETTE ER EN TEST STRENG OG SKAL";
	/**
	 * Creating fields for GUI elements
	 */
	private JLabel eventNameLabel = new JLabel("Eventname: ");
	private JLabel eventNameArea = new JLabel(stringForTest);
	private JPanel eventNamePanel = new JPanel();

	private JLabel eventIdLabel = new JLabel("Event ID: ");
	private JLabel eventIdArea = new JLabel(stringForTest);
	private JPanel eventIdpanel = new JPanel();

	private JLabel headLinerLabel = new JLabel("Headliner: ");
	private JLabel headlinerArea = new JLabel(stringForTest);
	private JPanel headlinerpanel = new JPanel();

	private JLabel dateLabel = new JLabel("Date: ");
	private JLabel DateArea = new JLabel(stringForTest);
	private JPanel datePanel = new JPanel();

	private JLabel venueLabel = new JLabel("Venue: ");
	private JLabel venueArea = new JLabel(stringForTest);
	private JPanel venuePanel = new JPanel();





	private JLabel ArtistBioLabel = new JLabel("Bio:");
	private JTextArea artistBioArea = new JTextArea(stringForTest);
	private JPanel artistBioPanel = new JPanel();

	private ViewController viewController = new ViewController();

	public JLabel getArtistBioLabel() {
		return ArtistBioLabel;
	}

	public void setArtistBioLabel(JLabel artistBioLabel) {
		ArtistBioLabel = artistBioLabel;
	}

	public JTextArea getArtistBioArea() {
		return artistBioArea;
	}

	public void setArtistBioArea(JTextArea artistBioArea) {
		this.artistBioArea = artistBioArea;
	}

	public JPanel getArtistBioPanel() {
		return artistBioPanel;
	}

	public void setArtistBioPanel(JPanel artistBioPanel) {
		this.artistBioPanel = artistBioPanel;
	}

	public View(){
		setupInterFace();
	}

	private void setupInterFace() {	
		this.setPreferredSize(new Dimension(1024, 1224));
		inputText.setPreferredSize(new Dimension(100, 20));

//			mapShower.setPreferredSize(new Dimension(400, 300));
//			mapShower.setBorder(BorderFactory.createBevelBorder(DEFAULT_CURSOR));

//			System.out.println(mapShower.getWidth());

//			mapShower.setBackground(Color.BLACK);
		northPanel.removeAll();
		northPanel.setLayout(new BorderLayout());
		northPanel.setPreferredSize(new Dimension(1024, 120));
		northPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));

		southPanel.removeAll();
		southPanel.setPreferredSize(new Dimension(1024, 120));
		southPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));

		eastPanel.removeAll();
		eastPanel.setPreferredSize(new Dimension(512,384));
		eastPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));
		eastPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		westPanel.removeAll();
		westPanel.setPreferredSize(new Dimension(512, 384));
		westPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));

//		BufferedImage myPicture = null;
//		try {
//			myPicture = ImageIO.read(new File("img/lastfmlogo.png"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}


		JLabel inputTextLabel = new JLabel("Input search query here:");
		JPanel inputArea = new JPanel();



		//Code for creating the Eventname area

		Dimension detailPanelDimension = new Dimension(400, 25);
		Dimension resultListDimension = new Dimension(500, 200);

		inputText.setPreferredSize(detailPanelDimension);


		eventNamePanel.add(eventNameLabel);
		eventNamePanel.add(eventNameArea);
		eventNamePanel.setPreferredSize(detailPanelDimension);

		//Event ID field and presentation

		eventIdpanel.add(eventIdLabel);
		eventIdpanel.add(eventIdArea);
		eventIdpanel.setPreferredSize(detailPanelDimension);

		//headliner field and presentation

		headlinerpanel.add(headLinerLabel);
		headlinerpanel.add(headlinerArea);
		headlinerpanel.setPreferredSize(detailPanelDimension);

		//date field and presentation

		datePanel.add(dateLabel);
		datePanel.add(DateArea);
		datePanel.setPreferredSize(detailPanelDimension);

		//venue field and presentation

		venuePanel.add(venueLabel);
		venuePanel.add(venueArea);
		venuePanel.setPreferredSize(detailPanelDimension);


		//Bandwebsite field and presentation


		artistBioPanel.add(ArtistBioLabel);
		artistBioPanel.setLayout(new BorderLayout(5,5));
		artistBioArea.setEditable(false);
		artistBioArea.setBackground(detailsPanel.getBackground());
		artistBioArea.setLineWrap(true);
		artistBioArea.setWrapStyleWord(true);
		artistBioPanel.add(artistBioArea);

		artistBioPanel.setPreferredSize(new Dimension(400, 300));
		this.validate();
		this.repaint();

		inputArea.setLayout(new BoxLayout(inputArea, BoxLayout.Y_AXIS));

		JLabel searchDescription = new JLabel("- For instance 'Coffee'");

		searchDescription.setFont(searchDescription.getFont().deriveFont(10.0f));
		inputArea.add(inputTextLabel);
		inputArea.add(inputText);
		inputArea.add(searchDescription);
		inputArea.add(goButton);

		inputArea.setPreferredSize(new Dimension(250, 75));
		northWestPanel.add(browseButton);
		northPanel.add(northWestPanel, BorderLayout.WEST);
		northPanel.add(northEastPanel, BorderLayout.EAST);
		resultList.setPreferredSize(new Dimension(500, 425));
		resultList.setBackground(westPanel.getBackground());
		resultList.setBorder(new EmptyBorder(10,10,10,10));

		northEastPanel.add(inputArea);
		
		westPanel.add(resultList);

		detailsPanel.add(eventNamePanel);
		detailsPanel.add(eventIdpanel);
		detailsPanel.add(headlinerpanel);
		detailsPanel.add(datePanel);
		detailsPanel.add(venuePanel);
		detailsPanel.add(artistBioPanel);


		detailsPanel.setPreferredSize(new Dimension(500, 900));
		detailsPanel.setVisible(false);

		eastPanel.add(detailsPanel);
		//			eastPanel.setBackground(Color.BLACK);
		//			eastPanel.add(imagePanel);

		//TODO
		// IFølge MVC skal denne ligge i controller?
		goButton.setActionCommand("go");
		browseButton.addActionListener(viewController);
		browseButton.setActionCommand("browse");

		getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(northPanel, BorderLayout.NORTH);
		this.getContentPane().add(westPanel, BorderLayout.WEST);
		this.getContentPane().add(eastPanel, BorderLayout.EAST);
		this.getContentPane().add(southPanel, BorderLayout.SOUTH);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.pack();
		this.setBounds(0, 0, 1024, 768);
	}

	public JPanel getDetailsPanel() {
		return detailsPanel;
	}

	public void setDetailsPanel(JPanel detailsPanel) {
		this.detailsPanel = detailsPanel;
	}

	public JPanel getImagePanel() {
		return imagePanel;
	}

	public void setImagePanel(JPanel imagePanel) {
		this.imagePanel = imagePanel;
	}

	public JPanel getSouthPanel() {
		return southPanel;
	}

	public void setSouthPanel(JPanel southPanel) {
		this.southPanel = southPanel;
	}


	public JPanel getNorthPanel() {
		return northPanel;
	}

	public void setNorthPanel(JPanel northPanel) {
		this.northPanel = northPanel;
	}

	public JScrollPane getWestPanel() {
		return westPanel;
	}

	public void setWestPanel(JScrollPane westPanel) {
		this.westPanel = westPanel;
	}

	public JPanel getEastPanel() {
		return eastPanel;
	}

	public void setEastPanel(JPanel eastPanel) {
		this.eastPanel = eastPanel;
	}

	public JButton getGoButton() {
		return goButton;
	}

	public void setGoButton(JButton goButton) {
		this.goButton = goButton;
	}

	public String getStringForTest() {
		return stringForTest;
	}

	public void setStringForTest(String stringForTest) {
		this.stringForTest = stringForTest;
	}

	public JLabel getEventNameLabel() {
		return eventNameLabel;
	}

	public void setEventNameLabel(JLabel eventNameLabel) {
		this.eventNameLabel = eventNameLabel;
	}

	public JLabel getEventNameArea() {
		return eventNameArea;
	}

	public void setEventNameArea(JLabel eventNameArea) {
		this.eventNameArea = eventNameArea;
	}

	public JPanel getEventNamePanel() {
		return eventNamePanel;
	}

	public void setEventNamePanel(JPanel eventNamePanel) {
		this.eventNamePanel = eventNamePanel;
	}

	public JLabel getEventIdLabel() {
		return eventIdLabel;
	}

	public void setEventIdLabel(JLabel eventIdLabel) {
		this.eventIdLabel = eventIdLabel;
	}

	public JLabel getEventIdArea() {
		return eventIdArea;
	}

	public void setEventIdArea(JLabel eventIdArea) {
		this.eventIdArea = eventIdArea;
	}

	public JPanel getEventIdpanel() {
		return eventIdpanel;
	}

	public void setEventIdpanel(JPanel eventIdpanel) {
		this.eventIdpanel = eventIdpanel;
	}

	public JLabel getHeadLinerLabel() {
		return headLinerLabel;
	}

	public void setHeadLinerLabel(JLabel headLinerLabel) {
		this.headLinerLabel = headLinerLabel;
	}

	public JLabel getHeadlinerArea() {
		return headlinerArea;
	}

	public void setHeadlinerArea(JLabel headlinerArea) {
		this.headlinerArea = headlinerArea;
	}

	public JPanel getHeadlinerpanel() {
		return headlinerpanel;
	}

	public void setHeadlinerpanel(JPanel headlinerpanel) {
		this.headlinerpanel = headlinerpanel;
	}

	public JLabel getDateLabel() {
		return dateLabel;
	}

	public void setDateLabel(JLabel dateLabel) {
		this.dateLabel = dateLabel;
	}

	public JLabel getDateArea() {
		return DateArea;
	}

	public void setDateArea(JLabel dateArea) {
		DateArea = dateArea;
	}

	public JPanel getDatePanel() {
		return datePanel;
	}

	public void setDatePanel(JPanel datePanel) {
		this.datePanel = datePanel;
	}

	public JLabel getVenueLabel() {
		return venueLabel;
	}

	public void setVenueLabel(JLabel venueLabel) {
		this.venueLabel = venueLabel;
	}

	public JLabel getVenueArea() {
		return venueArea;
	}

	public void setVenueArea(JLabel venueArea) {
		this.venueArea = venueArea;
	}

	public JPanel getVenuePanel() {
		return venuePanel;
	}

	public void setVenuePanel(JPanel venuePanel) {
		this.venuePanel = venuePanel;
	}




	public JScrollPane getCenterPanel() {
		return westPanel;
	}

	public void setCenterPanel(JScrollPane centerPanel) {
		this.westPanel = centerPanel;
	}

	public JTextField getInputText() {
		return inputText;
	}

	public void setInputText(JTextField inputText) {
		this.inputText = inputText;
	}

	public static DefaultListModel getResults() {
		return results;
	}

	public void setResults(DefaultListModel results) {
		this.results = results;
	}

	public JList getResultList() {
		return resultList;
	}

	public void setResultList(JList resultList) {
		this.resultList = resultList;
	}

	public static JButton getBrowseButton() {
		return browseButton;
	}

	public void setBrowseButton(JButton browseButton) {
		this.browseButton = browseButton;
	}
}
