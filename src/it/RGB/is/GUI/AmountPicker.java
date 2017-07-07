package it.RGB.is.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import it.RGB.is.Classes.Prodotto;

public class AmountPicker extends JDialog {

	private static final long serialVersionUID = 1L;

	private int min;
	private int max;
	private String title;

	private int valueSelected = -1;

	JSpinner spinner;
	private JButton enterBtn = new JButton(AmountPickerListener.OK_COMMAND);
	private JButton disposeBtn = new JButton(AmountPickerListener.DISPOSE_COMMAND);
	private Prodotto prodotto;
	private JLabel priceLbl;
	private JLabel objDetail;
	private JLabel imageCD;

	public AmountPicker(JDialog parent, Prodotto prodotto, String title, int min, int max) {
		super(parent, true);
		this.prodotto = prodotto;
		this.title = title;
		this.min = min;
		this.max = max;
		setAndViewGUI();
	}

	public AmountPicker(JFrame parent, Prodotto prodotto, String title, int min, int max) {
		super(parent, true);
		this.prodotto = prodotto;
		this.title = title;
		this.min = min;
		this.max = max;
		setAndViewGUI();
	}

	private void setAndViewGUI() {
		setLayout(new BorderLayout());

		// ESC key
		setFocusable(true);
		addKeyListener(new KeyboardListener(this));

		imageCD = new JLabel(prodotto.getCover());

		JPanel northPanel = new JPanel(new GridLayout(2, 1));
		objDetail = new JLabel("<html><h3>" + prodotto.getTitolo(), SwingConstants.CENTER);
		priceLbl = new JLabel("Prezzo: " + prodotto.getPrezzoString() + " €", SwingConstants.CENTER);

		northPanel.add(objDetail);
		northPanel.add(priceLbl);

		JPanel southPanel = new JPanel(new FlowLayout());
		enterBtn.setActionCommand(AmountPickerListener.OK_COMMAND);
		enterBtn.addActionListener(new AmountPickerListener(this));

		disposeBtn.setActionCommand(AmountPickerListener.DISPOSE_COMMAND);
		disposeBtn.addActionListener(new AmountPickerListener(this));

		SpinnerModel model = new SpinnerNumberModel(1, min, max, 1);
		spinner = new JSpinner(model);
		spinner.addChangeListener(new ApChangeListener());

		southPanel.add(spinner);
		southPanel.add(enterBtn);
		southPanel.add(disposeBtn);

		northPanel.setBorder(new EmptyBorder(5, 5, 2, 15));
		imageCD.setBorder(new EmptyBorder(5, 10, 2, 10));
		add(northPanel, BorderLayout.EAST);
		add(imageCD, BorderLayout.WEST);

		// southPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(southPanel, BorderLayout.SOUTH);

		setIconImage(new ImageIcon(this.getClass().getResource("/resources/buy_icon_new.png")).getImage());
		getRootPane().setDefaultButton(enterBtn);
		setTitle(this.title + " - quantità");
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	public int getSelectedValue() {
		return this.valueSelected;
	}

	public void setValueSelected(int i) {
		this.valueSelected = i;
	}

	private class ApChangeListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			int spinnerValue = (int) spinner.getValue();
			priceLbl.setText("Prezzo: " + String.format("%.2f", (spinnerValue * prodotto.getPrezzo())) + " €");
		}
	}
}
