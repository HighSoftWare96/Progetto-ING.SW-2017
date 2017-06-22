package it.RGB.is.GUI;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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

		Prodotto prodotto = Catalogo.searchByID(id);

		setTitle("Dettagli prodotto");

		JPanel firstPanel = new JPanel(new GridLayout(2, 1));

		ImageIcon[] photos = prodotto.getPhotos();
		JPanel imagesPanel = new JPanel(new GridLayout(1, photos.length));

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
		fourthPanel.add(new JLabel("<html>" + prodotto.getDataArrivo().toString() + "<br>", SwingConstants.CENTER));

		fourthPanel.add(singerGroupLabel);
		fourthPanel
				.add(new JLabel("<html><center>" + prodotto.getTitolare().toString() + "<br>", SwingConstants.CENTER));

		fourthPanel.add(descrlLabel);
		fourthPanel.add(new JLabel("<html>" + prodotto.getDescrizione() + "<br>", SwingConstants.CENTER));

		fourthPanel.add(genLabel);
		fourthPanel.add(new JLabel("<html>" + prodotto.getGenere().toString() + "<br>", SwingConstants.CENTER));

		JPanel fifthPanel = new JPanel(new GridLayout(2, 1));
		ArtistaGenerico[] artisti = prodotto.getPartecipanti();
		String artistiFormatted = "<html><center>Musicisti partecipanti:<br></center><ul>";
		for (ArtistaGenerico item : artisti) {
			artistiFormatted += "<li>";
			artistiFormatted += item.toString();
			artistiFormatted += "</li>";
		}
		artistiFormatted += "</ul></html>";
		JLabel prizeLabel = new JLabel("<html><center><b>Prezzo:</b> <br><h2>" + prodotto.getPrezzo() + " €</h2>",
				SwingConstants.CENTER);
		prizeLabel.setBorder(new EmptyBorder(0, 10, 0, 0));

		fifthPanel.add(new JLabel(artistiFormatted));
		fifthPanel.add(prizeLabel);
		fifthPanel.setBorder(new EmptyBorder(10, 5, 0, 15));

		// pannello contenente tutti i sottopannelli
		JPanel mainPanel = new JPanel(new FlowLayout());
		mainPanel.add(firstPanel);
		mainPanel.add(secondPanel);
		mainPanel.add(fourthPanel);
		mainPanel.add(fifthPanel);

		// scroll pane per permettere lo scrolling
		JScrollPane mainScrollPane = new JScrollPane(mainPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		mainScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		// aggiungo lo scroll pane
		getContentPane().add(mainScrollPane);

		setIconImage(new ImageIcon(this.getClass().getResource("/resources/info_icon_new.png")).getImage());

		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);

	}

}
