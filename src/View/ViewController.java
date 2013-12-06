package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Analyzer;
import model.Text;
import reader.FileReader;

public class ViewController implements ActionListener {

	private JFileChooser     fileChooser  	= new JFileChooser("");
	ArrayList<File> documentList = new ArrayList<File>();
	private Text text = new Text(null);

	public ViewController() {

	}

	private void openFile(File[] tempFiles) throws Exception {
		ArrayList<Text> docsToAnalyzer = new ArrayList<Text>();
		ArrayList<String> filesToRead = new ArrayList<String>();
		
		FileReader fileReader = new FileReader();
		
		for(File file : tempFiles){
			filesToRead.add(file.toString());
		}

		for(int integer = 0; integer < filesToRead.size(); integer++){
			System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHelvete");
			Text t = fileReader.readPDF(filesToRead.get(integer));
			docsToAnalyzer.add(t);
			System.out.println(docsToAnalyzer.size());
		}
		
		Analyzer analyzer = new Analyzer(docsToAnalyzer);
		
	}

	/**
	 * Action Performed Method.
	 */
	public void actionPerformed(ActionEvent e) {
		fileChooser.setFileFilter(new FileNameExtensionFilter("Text-files", "doc", "docx", "pdf"));
		fileChooser.setMultiSelectionEnabled(true);
		
		if(e.getSource() == View.getBrowseButton()){
			System.out.println("BROWSE BUTTON");
			int returnVal = fileChooser.showOpenDialog(fileChooser);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File[] tempFiles = fileChooser.getSelectedFiles();
				try {
					openFile(tempFiles);
				} catch (Exception excep) {
					excep.printStackTrace();
					System.exit(0);
				}
			}
		}
	}
}
