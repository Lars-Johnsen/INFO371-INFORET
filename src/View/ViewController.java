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
	ArrayList<Text> docsToAnalyzer = new ArrayList<Text>();
	private Text text = new Text(null);
	private View view;
	public ViewController(View view) {
		this.view = view;
	}

	private void openFile(File[] tempFiles) throws Exception {
		
		ArrayList<File> filesToRead = new ArrayList<File>();
		
		FileReader fileReader = new FileReader();
		
		for(File file : tempFiles){
			
			filesToRead.add(file);
		}

		for(int integer = 0; integer < filesToRead.size(); integer++){
			
			Text t = fileReader.readPDF(filesToRead.get(integer).toString());
			t.setName(filesToRead.get(integer).getName());
			docsToAnalyzer.add(t);
			updateResultList();
			System.out.println(docsToAnalyzer.size());
			
		}
		
		Analyzer analyzer = new Analyzer(docsToAnalyzer);
		
	}

	/**
	 * Action Performed Method.
	 */
	public void actionPerformed(ActionEvent e) {
		System.out.println("DU TRYKKE");
		fileChooser.setFileFilter(new FileNameExtensionFilter("Text-files", "doc", "docx", "pdf"));
		fileChooser.setMultiSelectionEnabled(true);
		
		if(e.getSource() == View.getBrowseButton()){
			
			int returnVal = fileChooser.showOpenDialog(fileChooser);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File[] tempFiles = fileChooser.getSelectedFiles();
				System.out.println(tempFiles);
				try {
					openFile(tempFiles);
					
				} catch (Exception excep) {
					excep.printStackTrace();
					System.exit(0);
					
				}
			}
		}
		updateResultList();
	}
	
	/**
	 * Skal oppdatere listen virker ikke
	 */
	public void updateResultList(){
		int counter = 0;
		for(Text text : docsToAnalyzer){
			
			view.getResults().addElement(text);
			view.repaint();	
			view.validate();
			counter++;
		}
	}
}
