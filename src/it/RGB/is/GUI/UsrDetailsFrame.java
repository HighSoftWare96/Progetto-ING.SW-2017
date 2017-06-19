package it.RGB.is.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import it.RGB.is.Classes.BancaUtenti;
import it.RGB.is.Classes.Cliente;
import it.RGB.is.Classes.Vendita;

public class UsrDetailsFrame extends JDialog {

	private static final long serialVersionUID = 1L;

	public UsrDetailsFrame(JFrame frame) {
		super(frame, true);

		setTitle("Dettagli utente");

		// ESC key
		setFocusable(true);
		addKeyListener(new KeyboardListener(this));

		Cliente user = BancaUtenti.getLoggedInUser();

		JPanel nordPanel = new JPanel(new GridLayout(2, 1));
		JLabel userNameLbl = new JLabel("<html><center><h1>" + user.getUsername(), SwingConstants.CENTER);
		JLabel userImage = new JLabel(new ImageIcon(this.getClass().getResource("/resources/user_new.png")));
		nordPanel.add(userNameLbl);
		nordPanel.add(userImage);
		nordPanel.setBorder(new EmptyBorder(0, 0, 20, 0));

		JPanel southPanel = new JPanel(new GridLayout(8, 2));

		JLabel cFLabel = new JLabel("<html><b>Codice Fiscale: ", SwingConstants.RIGHT);
		JLabel nomeLabel = new JLabel("<html><b>Nome: ", SwingConstants.RIGHT);
		JLabel surnameLabel = new JLabel("<html><b>Cognome: ", SwingConstants.RIGHT);
		JLabel cityLabel = new JLabel("<html><b>Città di residenza: ", SwingConstants.RIGHT);
		JLabel telLabel = new JLabel("<html><b>Numero di telefono: ", SwingConstants.RIGHT);
		JLabel cellLabel = new JLabel("<html><b>Numero di cellulare: ", SwingConstants.RIGHT);

		southPanel.add(cFLabel);
		southPanel.add(new JLabel(user.getCF()));
		southPanel.add(nomeLabel);
		southPanel.add(new JLabel(user.getNome()));
		southPanel.add(surnameLabel);
		southPanel.add(new JLabel(user.getCognome()));
		southPanel.add(cityLabel);
		southPanel.add(new JLabel(user.getCity()));
		southPanel.add(telLabel);
		southPanel.add(new JLabel(user.getTel()));

		// IF PRESENTE
		if (!BancaUtenti.getLoggedInUser().getCell().equals("")) {
			southPanel.add(cellLabel);
			southPanel.add(new JLabel(user.getCell()));
		}

		JPanel venditePnl = new JPanel(new FlowLayout());
		// setto una preferenza per rendere il flow verticale (stimo 40px per ogni elemento...)
		venditePnl.setPreferredSize(new Dimension(500, BancaUtenti.getLoggedInUser().getVenditeCount() * 40));

		for (Vendita item : user.getVendite()) {
			JPanel currentVenditaPnl = new JPanel(new FlowLayout());

			currentVenditaPnl
					.add(new JLabel("<html><b>Costo:</b> " + String.format("%.2f", item.getPrezzoTotale()) + " €",
							SwingConstants.CENTER));
			currentVenditaPnl.add(new JLabel("<html><b>Pagamento:</b> " + item.getPagamento()), SwingConstants.CENTER);
			currentVenditaPnl.add(new JLabel("<html><b>Spedizione:</b> " + item.getConsegna()), SwingConstants.CENTER);

			String prodottiFormatted = "<html><b>Prodotti: </b>";

			for (int i = 0; i < item.getProdottiLength(); i++) {
				prodottiFormatted += item.getProdottoString(i);
				prodottiFormatted += " (" + item.getAmount()[i] + "x), <br>";
			}

			prodottiFormatted = prodottiFormatted.substring(0, prodottiFormatted.length() - 6); // tolgo
																								// ultima
																								// virgola

			currentVenditaPnl.add(new JLabel(prodottiFormatted), SwingConstants.CENTER);
			currentVenditaPnl.add(new JLabel("<html><b>IP host:</b> " + item.getIP()), SwingConstants.CENTER);
			currentVenditaPnl.add(new JLabel("<html><b>In data:</b> " + item.getDateString()), SwingConstants.CENTER);
			venditePnl.add(currentVenditaPnl);
		}

		JScrollPane venditeContainer = new JScrollPane(venditePnl);
		venditeContainer.setBorder(new TitledBorder("Acquisti effettuati:"));

		venditeContainer.setPreferredSize(new Dimension(860, 120));

		southPanel.setBorder(new EmptyBorder(10, 20, 0, 20));

		getContentPane().add(nordPanel, BorderLayout.NORTH);
		getContentPane().add(southPanel, BorderLayout.CENTER);
		getContentPane().add(venditeContainer, BorderLayout.SOUTH);

		setIconImage(new ImageIcon(this.getClass().getResource("/resources/user_icon.png")).getImage());
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
