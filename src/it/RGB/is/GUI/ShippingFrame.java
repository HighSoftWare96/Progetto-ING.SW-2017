package it.RGB.is.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import it.RGB.is.Classes.BancaUtenti;
import it.RGB.is.Classes.Cart;
import it.RGB.is.Classes.ModConsegna;
import it.RGB.is.Classes.Pagamento;

public class ShippingFrame extends JDialog {

	private int status = 0;

	private JPanel mainPanel = new JPanel(new BorderLayout());
	private JPanel firstPanel = new JPanel(new GridLayout(5, 1));
	private JPanel secondPanel = new JPanel(new GridLayout(8, 1));
	private JPanel thirdPanel = new JPanel(new GridLayout(7, 1));

	private JButton avantiBtn;
	private JButton indietroBtn;
	private JButton cancelBtn;

	private JRadioButton shipCorriereOpt;
	private JRadioButton shipPostaOpt;
	private JRadioButton shipGratisOpt;
	private ModConsegna consegnaSelezionata;

	private JRadioButton bonificoOpt;
	private JRadioButton cardOpt;
	private JRadioButton ppOpt;
	private Pagamento pagamentoSelezionato;

	private ButtonGroup payOpts = new ButtonGroup();

	private JTextField cardCode;
	private JSpinner expireMonthCode;
	private JSpinner expireYearCode;
	private JTextField cvcCode;

	private JTextField ppAccount;
	private JPasswordField ppPsw;

	private JLabel prezzoLbl = new JLabel("Totale: " + Cart.getSubTotale(null) + " €", SwingConstants.CENTER);

	private JLabel modSpedLbl = new JLabel("", SwingConstants.CENTER);
	private JLabel modSpedDtlLbl = new JLabel("", SwingConstants.CENTER);
	private JLabel modPagamLbl = new JLabel("", SwingConstants.CENTER);
	private JLabel modPagamDtlLbl = new JLabel("", SwingConstants.CENTER);

	private static final long serialVersionUID = 1L;

	public ShippingFrame(JFrame frame) {

		super(frame, true);

		// ESC key
		setFocusable(true);
		addKeyListener(new KeyboardListener(this));

		setTitle("Spedizione");

		setLayout(new GridLayout(1, 1));

		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		indietroBtn = new JButton("< Indietro");
		indietroBtn.setVisible(false);
		indietroBtn.setActionCommand(BuyListeners.INDIETRO_COMMAND);
		indietroBtn.addActionListener(new BuyListeners(this));

		avantiBtn = new JButton("Avanti >");
		avantiBtn.setEnabled(false);
		avantiBtn.setActionCommand(BuyListeners.AVANTI_COMMAND);
		avantiBtn.addActionListener(new BuyListeners(this));

		cancelBtn = new JButton("Annulla");
		cancelBtn.setActionCommand(BuyListeners.CANCEL_COMMAND);
		cancelBtn.addActionListener(new BuyListeners(this));

		buttonsPanel.add(indietroBtn);
		buttonsPanel.add(avantiBtn);
		buttonsPanel.add(cancelBtn);

		JLabel imageShip = new JLabel(new ImageIcon(this.getClass().getResource("/resources/sped40.png")));
		JLabel titleLblShip = new JLabel("<html><center><h3>Seleziona il tipo di spedizione</h3></html>",
				SwingConstants.CENTER);
		shipCorriereOpt = new JRadioButton("Corriere 24 ore - 7.00 €");
		shipPostaOpt = new JRadioButton("Posta ordinaria - 5.00 €");
		shipGratisOpt = new JRadioButton("Corriere gratis - 0,00 €");
		shipCorriereOpt.addActionListener(new BuyCheckListeners(this));
		shipCorriereOpt.setActionCommand(BuyCheckListeners.RADIO_COMMAND);

		shipPostaOpt.addActionListener(new BuyCheckListeners(this));
		shipPostaOpt.setActionCommand(BuyCheckListeners.RADIO_COMMAND);

		shipGratisOpt.addActionListener(new BuyCheckListeners(this));
		shipGratisOpt.setActionCommand(BuyCheckListeners.RADIO_COMMAND);

		if (!BancaUtenti.getLoggedInUser().canHaveDiscounts())
			shipGratisOpt.setEnabled(false); // intanto TODO

		ButtonGroup shipOpts = new ButtonGroup();

		shipOpts.add(shipCorriereOpt);
		shipOpts.add(shipGratisOpt);
		shipOpts.add(shipPostaOpt);

		firstPanel.add(imageShip);
		firstPanel.add(titleLblShip);
		firstPanel.add(shipCorriereOpt);
		firstPanel.add(shipPostaOpt);
		firstPanel.add(shipGratisOpt);

		JLabel imagePay = new JLabel(new ImageIcon(this.getClass().getResource("/resources/payC50.png")));
		JLabel titleLblPay = new JLabel("<html><center><h3>Seleziona il tipo di pagamento</h3></html>",
				SwingConstants.CENTER);
		bonificoOpt = new JRadioButton("Tramite bonifico bancario");
		bonificoOpt.addActionListener(new BuyCheckListeners(this));
		bonificoOpt.setActionCommand(BuyCheckListeners.RADIO_BON_COMMAND);
		JLabel coordBonifico = new JLabel(
				"<html>Coordinate per il bonifico:<br> BANCA D'ITALIA<br> IBAN SDASDG535726537600000000001623",
				SwingConstants.CENTER);

		cardOpt = new JRadioButton("Tramite carta di credito");
		cardOpt.addActionListener(new BuyCheckListeners(this));
		cardOpt.setActionCommand(BuyCheckListeners.RADIO_CARD_COMMAND);

		JPanel cardOptPanel = new JPanel(new GridLayout(3, 2));

		cardCode = new JTextField();
		JLabel cardCodeLabel = new JLabel("Codice carta:");

		JPanel expireCode = new JPanel(new GridLayout(1, 1));

		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		int month = cal.get(Calendar.MONTH);
		month++;

		int year = cal.get(Calendar.YEAR);
		cal.add(Calendar.YEAR, 50);
		int yearAfter = cal.get(Calendar.YEAR);

		expireMonthCode = new JSpinner(new SpinnerNumberModel(month, 1, 12, 1));
		expireYearCode = new JSpinner(new SpinnerNumberModel(year % 100, year % 100, yearAfter % 100, 1));
		JLabel expireCodeLabel = new JLabel("Scadenza:");

		cvcCode = new JTextField();
		JLabel cvcCodeLabel = new JLabel("Codice verifica:");

		expireCode.add(expireMonthCode);
		expireCode.add(expireYearCode);

		cardOptPanel.add(cardCodeLabel);
		cardOptPanel.add(cardCode);
		cardOptPanel.add(expireCodeLabel);
		cardOptPanel.add(expireCode);
		cardOptPanel.add(cvcCodeLabel);
		cardOptPanel.add(cvcCode);
		cardOptPanel.setBorder(new EmptyBorder(0, 20, 0, 20));

		ppOpt = new JRadioButton("Tramite account PayPal");
		ppOpt.addActionListener(new BuyCheckListeners(this));
		ppOpt.setActionCommand(BuyCheckListeners.RADIO_PP_COMMAND);

		JPanel ppPanel = new JPanel(new GridLayout(2, 2));
		ppPanel.setEnabled(false);

		JLabel accountLbl = new JLabel("Nome account: ");
		ppAccount = new JTextField();

		JLabel pswLbl = new JLabel("Password: ");
		ppPsw = new JPasswordField();

		cardCode.setEnabled(false);
		expireMonthCode.setEnabled(false);
		expireYearCode.setEnabled(false);
		cvcCode.setEnabled(false);

		ppAccount.setEnabled(false);
		ppPsw.setEnabled(false);

		ppPanel.add(accountLbl);
		ppPanel.add(ppAccount);
		ppPanel.add(pswLbl);
		ppPanel.add(ppPsw);
		ppPanel.setBorder(new EmptyBorder(0, 20, 20, 20));

		payOpts.add(bonificoOpt);
		payOpts.add(cardOpt);
		payOpts.add(ppOpt);

		secondPanel.add(imagePay);
		secondPanel.add(titleLblPay);
		secondPanel.add(bonificoOpt);
		secondPanel.add(coordBonifico);
		secondPanel.add(cardOpt);
		secondPanel.add(cardOptPanel);
		secondPanel.add(ppOpt);
		secondPanel.add(ppPanel);

		JLabel imageLastInfo = new JLabel(new ImageIcon(this.getClass().getResource("/resources/check40.png")));
		JLabel titleThird = new JLabel("<html><center><h3>Riepilogo dell'ordine</h3></html>", SwingConstants.CENTER);

		thirdPanel.add(imageLastInfo);
		thirdPanel.add(titleThird);
		thirdPanel.add(modSpedLbl);
		thirdPanel.add(modSpedDtlLbl);
		thirdPanel.add(modPagamLbl);
		thirdPanel.add(modPagamDtlLbl);
		thirdPanel.add(prezzoLbl);

		mainPanel.add(firstPanel, BorderLayout.NORTH);
		mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
		mainPanel.setBorder(new EmptyBorder(10, 10, 3, 10));

		getContentPane().add(mainPanel);
		getRootPane().setDefaultButton(avantiBtn);
		setIconImage(new ImageIcon(this.getClass().getResource("/resources/cart_icon_new.png")).getImage());
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void setConsegnaSelezionata() {
		if (shipCorriereOpt.isSelected()) {
			this.consegnaSelezionata = ModConsegna.CORRIERE_24H;
			modSpedDtlLbl.setText("<html><center><i>Consegna prevista tra 2 giorni");
		} else if (shipGratisOpt.isSelected()) {
			this.consegnaSelezionata = ModConsegna.GRATIS;
			modSpedDtlLbl.setText("<html><center><i>Consegna prevista tra 5 giorni");
		} else if (shipPostaOpt.isSelected()) {
			this.consegnaSelezionata = ModConsegna.POSTA;
			modSpedDtlLbl.setText("<html><center><i>Consegna prevista tra 15 giorni");
		}
		modSpedLbl.setText("<html><b>Modalità di spedizione: " + this.consegnaSelezionata);
	}

	public ModConsegna getConsegnaSelezionata() {
		return this.consegnaSelezionata;
	}

	public void setPagamentoSelezionato() {
		if (ppOpt.isSelected()) {
			this.pagamentoSelezionato = Pagamento.PAYPAL;
			modPagamDtlLbl.setText("<html><center><i>Account PayPal selezionato:<br> " + this.ppAccount.getText());
		} else if (bonificoOpt.isSelected()) {
			this.pagamentoSelezionato = Pagamento.BONIFICO;
			modPagamDtlLbl.setText(
					"<html><center><i>Coordinate per il bonifico:<br> BANCA D'ITALIA <br>IBAN SDASDG535726537600000000001623");
		} else if (cardOpt.isSelected()) {
			this.pagamentoSelezionato = Pagamento.CARTA_CREDITO;
			if (cardCode.getText().length() > 4) {
				modPagamDtlLbl.setText("<html><i>Numero carta di credito:<br>************"
						+ this.cardCode.getText().substring(this.cardCode.getText().length() - 4));
			}
		}
		modPagamLbl.setText("<html><b>Modalità di pagamento: " + this.pagamentoSelezionato);

	}

	public Pagamento getPagamentoSelezionato() {
		return this.pagamentoSelezionato;
	}

	public void enableProceed() {
		avantiBtn.setEnabled(true);
	}

	public void disableProceed() {
		avantiBtn.setEnabled(false);
	}

	public void proceed() {
		switch (status) {
		case 0: // da spedizione a pagamento
			status = 1;

			if (BuyCheckListeners.pagamentoIsSelected())
				this.avantiBtn.setEnabled(true);
			else
				this.avantiBtn.setEnabled(false);

			setTitle("Pagamento");
			indietroBtn.setVisible(true);
			mainPanel.remove(firstPanel);
			mainPanel.add(secondPanel);
			pack();
			setLocationRelativeTo(null);
			break;
		case 1: // da pagamento a riepilogo
			status = 2;

			this.avantiBtn.setEnabled(true);

			String prezzoString = "<html><h4>Totale: "
					+ String.format("%.2f", Cart.getSubTotale(this.consegnaSelezionata)) + " €</h4>";

			// se l'utente ha lo sconto
			if (Cart.hasDiscount())
				prezzoString += "<i>(Sconto 15%)<br>";
			this.prezzoLbl.setText(prezzoString);

			setTitle("Riepilogo");
			indietroBtn.setVisible(true);
			mainPanel.remove(secondPanel);
			mainPanel.add(thirdPanel);

			avantiBtn.setText("Conferma");
			avantiBtn.setForeground(Color.decode("#0099000"));
			avantiBtn.setActionCommand(BuyListeners.FINAL_COMMAND);

			pack();
			setLocationRelativeTo(null);
			break;

		default:
			break;
		}
	}

	public void turnBack() {

		switch (status) {
		case 1: // da pagamento a spedizione
			status = 0;
			setTitle("Spedizione");
			indietroBtn.setVisible(false);
			mainPanel.remove(secondPanel);
			mainPanel.add(firstPanel);

			if (BuyCheckListeners.spedizioneIsSelected())
				this.avantiBtn.setEnabled(true);
			else
				this.avantiBtn.setEnabled(false);

			avantiBtn.setText("Avanti >");
			avantiBtn.setForeground(null);
			avantiBtn.setActionCommand(BuyListeners.AVANTI_COMMAND);

			pack();
			setLocationRelativeTo(null);
			break;
		case 2: // da riepilogo a pagamento
			status = 1;
			setTitle("Pagamento");
			indietroBtn.setVisible(true);
			mainPanel.remove(thirdPanel);
			mainPanel.add(secondPanel);

			avantiBtn.setText("Avanti >");
			avantiBtn.setForeground(null);
			avantiBtn.setActionCommand(BuyListeners.AVANTI_COMMAND);

			if (BuyCheckListeners.pagamentoIsSelected())
				this.avantiBtn.setEnabled(true);
			else
				this.avantiBtn.setEnabled(false);

			setLocationRelativeTo(null);
			pack();
			break;
		default:
			break;
		}
	}

	public void activateCardOpt() {
		cardCode.setEnabled(true);
		expireMonthCode.setEnabled(true);
		expireYearCode.setEnabled(true);
		cvcCode.setEnabled(true);

		// deseleziono gli altri
		ppAccount.setEnabled(false);
		ppPsw.setEnabled(false);
	}

	public void activatePPOpt() {
		ppAccount.setEnabled(true);
		ppPsw.setEnabled(true);

		// deselezione gli altri
		cardCode.setEnabled(false);
		expireMonthCode.setEnabled(false);
		expireYearCode.setEnabled(false);
		cvcCode.setEnabled(false);

	}

	public void noOpt() {
		ppAccount.setEnabled(false);
		ppPsw.setEnabled(false);
		cardCode.setEnabled(false);
		expireMonthCode.setEnabled(false);
		expireYearCode.setEnabled(false);
		cvcCode.setEnabled(false);
	}

	public boolean checkPaymentsData() {
		if (cardCode.getText().equals("") || cvcCode.getText().equals("")) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(this, "Riempire tutti i campi obbligatori", "Errore pagamento con Carta",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (!(cardCode.getText().matches("[0-9]+") || cvcCode.getText().matches("[0-9]+"))) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(this, "Sono presenti caratteri non numerici", "Errore pagamento con Carta",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (cardCode.getText().length() != 16) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(this, "Il numero della Carta non è valido", "Errore pagamento con Carta",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (cvcCode.getText().length() != 3) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(this, "Il CVC non è valido", "Errore pagamento con Carta",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			Date today = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(today);
			int month = c.get(Calendar.MONTH);
			month++;

			int year = c.get(Calendar.YEAR);


			// controllo che il mese non sia prima di quello corrente
			if ((((int) expireYearCode.getValue()) == (year % 100)) && (((int) expireMonthCode.getValue()) < month)) {
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(this, "Data scadenza carta di credito non valida",
						"Errore pagamento con Carta", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			return true;
		}
	}

	public boolean checkPaymentsDataPP() {
		if (ppAccount.getText().equals("") || ppPsw.getPassword().equals("")) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(this, "Riempire tutti i campi obbligatori", "Errore pagamento con PayPal",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (!ppAccount.getText().matches("^[a-zA-Z0-9._+-]+@[a-zA-Z.-]+\\.[a-zA-Z]{2,3}$")) { // regex
																										// per
																										// la
																										// mail
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(this, "Indirizzo Mail non corretto", "Errore pagamento con PayPal",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public int getStatus() {
		// TODO Auto-generated method stub
		return this.status;
	}

}
