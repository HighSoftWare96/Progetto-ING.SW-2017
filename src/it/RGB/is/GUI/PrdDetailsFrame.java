package it.RGB.is.GUI;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import it.RGB.is.Classes.ArtistaGenerico;
import it.RGB.is.Classes.Catalogo;
import it.RGB.is.Classes.Prodotto;

public class PrdDetailsFrame extends JDialog {

	private static final long serialVersionUID = 1L;

	public PrdDetailsFrame(JFrame frame, int id) {
		super(frame, true);

		// ESC key
		setFocusable(true);
		addKeyListener(new KeyboardListener(this));

		setLayout(new FlowLayout());

		Prodotto prodotto = Catalogo.searchByID(id);

		setTitle("Dettagli prodotto");

		JPanel firstPanel = new JPanel(new GridLayout(2, 1));

		ImageIcon[] photos = prodotto.getPhotos();
		JPanel imagesPanel = new JPanel(new GridLayout(photos.length, 1));

		for (ImageIcon item : photos) {
			imagesPanel.add(new JLabel(item, SwingConstants.CENTER));
		}

		JPanel titlePanel = new JPanel(new GridLayout(1, 1));
		JLabel titleLabelName = new JLabel("<html><center>Titolo: <h1>" + prodotto.getTitolo() + "</h1></html>",
				SwingConstants.CENTER);
		titlePanel.add(titleLabelName);
		titlePanel.setBorder(new EmptyBorder(0, 15, 0, 15));

		firstPanel.add(imagesPanel);
		firstPanel.add(titlePanel);

		JPanel secondPanel = new JPanel(new FlowLayout());

		String[] titoli = prodotto.getTitoliPezzi();

		String titoliFormatted = "<html><center>Brani:<br></center><ul>";
		for (String item : titoli) {
			titoliFormatted += "<li>";
			titoliFormatted += item;
			titoliFormatted += "</li>";
		}
		titoliFormatted += "</ul></html>";

		secondPanel.add(new JLabel(titoliFormatted));
		secondPanel.setBorder(new EmptyBorder(0, 0, 0, 30));

		JPanel fourthPanel = new JPanel(new GridLayout(8, 1));

		JLabel dataLabel = new JLabel("Data aggiunta:", SwingConstants.CENTER);
		dataLabel.setFont(dataLabel.getFont().deriveFont(Font.BOLD));
		JLabel singerGroupLabel = new JLabel("Musicista/Band titolare:", SwingConstants.CENTER);
		singerGroupLabel.setFont(dataLabel.getFont().deriveFont(Font.BOLD));
		JLabel descrlLabel = new JLabel("Descrizione:", SwingConstants.CENTER);
		descrlLabel.setFont(dataLabel.getFont().deriveFont(Font.BOLD));
		JLabel genLabel = new JLabel("Genere:", SwingConstants.CENTER);
		genLabel.setFont(dataLabel.getFont().deriveFont(Font.BOLD));

		fourthPanel.add(dataLabel);
		fourthPanel.add(new JLabel("<html>" + prodotto.getDataArrivo().toString() + "<br><br>", SwingConstants.CENTER));

		fourthPanel.add(singerGroupLabel);
		fourthPanel.add(new JLabel("<html>" + prodotto.getTitolare().toString() + "<br><br>", SwingConstants.CENTER));

		fourthPanel.add(descrlLabel);
		fourthPanel.add(new JLabel("<html>" + prodotto.getDescrizione() + "<br><br>", SwingConstants.CENTER));

		fourthPanel.add(genLabel);
		fourthPanel.add(new JLabel("<html>" + prodotto.getGenere().toString() + "<br><br>", SwingConstants.CENTER));

		JPanel fifthPanel = new JPanel(new FlowLayout());
		ArtistaGenerico[] artisti = prodotto.getPartecipanti();
		String artistiFormatted = "<html><center>Musicisti partecipanti:<br></center><ul>";
		for (ArtistaGenerico item : artisti) {
			artistiFormatted += "<li>";
			artistiFormatted += item.toString();
			artistiFormatted += "</li>";
		}
		artistiFormatted += "</ul></html>";

		fifthPanel.add(new JLabel(artistiFormatted));
		fifthPanel.setBorder(new EmptyBorder(0, 0, 0, 30));

		JPanel lastPanel = new JPanel(new GridLayout(1, 1));
		JLabel prizeLabel = new JLabel("<html><center><b>Prezzo:</b> <br><h2>" + prodotto.getPrezzo() + " €</h2>",
				SwingConstants.CENTER);
		lastPanel.add(prizeLabel);
		lastPanel.setBorder(new EmptyBorder(0, 30, 0, 30));

		getContentPane().add(firstPanel);
		getContentPane().add(secondPanel);
		getContentPane().add(fourthPanel);
		getContentPane().add(fifthPanel);
		getContentPane().add(lastPanel);

		setIconImage(new ImageIcon(this.getClass().getResource("/resources/info_icon_new.png")).getImage());

		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);

	}

}
