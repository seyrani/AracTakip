package tr.org.sd.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import tr.org.sd.bll.AracBilgiManager;
import tr.org.sd.bll.AracYakitManager;
import tr.org.sd.bll.GorevEmriManager;
import tr.org.sd.bll.PlakaManager;
import tr.org.sd.bll.SoforManager;
import tr.org.sd.dal.AracBilgiModel;
import tr.org.sd.dal.AracYakitModel;
import tr.org.sd.dal.GorevEmriModel;
import tr.org.sd.dal.PlakaModel;
import tr.org.sd.dal.SoforModel;
import tr.org.sd.helper.InfoMessage;

public class MainFrame extends JFrame {

	private final JPanel contentPane;
	private final JTable tableGorevBilgi;
	private final JTextField textGorevliPersonel;
	private final JSpinner textCikisKm;
	private final JSpinner textDonusKm;
	private final JTextField textAra;
	// private final JTextArea textGidilecekYer;
	// private final JComboBox comboBoxGorevTuru;

	// JTABLE GÖREV BİLGİ GİRİŞİ BAŞLIK MODEL
	final String basliklar[] = { "No", "Görev Tarihi", "Görevli Personel", "Görev Türü", "Gidilecek Yer", "Sürücü",
			"Plaka", "Çıkış Saati", "Dönüş Saati", "Çıkış Km.", "Dönüş Km.", "Yapilan Km." };
	final DefaultTableModel gorevEmriModel = new DefaultTableModel(basliklar, 0);
	private JTable tableAracBilgi;
	private JSpinner textGelenKm;

	// JTABLE ARAÇ BİLGİ BAŞLIKLAR
	final String aracBilgiBasliklar[] = { "No", "Plaka", "Araç Türü", "Marka", "Model", "Model Yıl", "Başlama Tarihi",
			"Bitiş Tarihi", "Aktif Km.", "Şase No", "Durum" };
	final DefaultTableModel aracBilgiModel = new DefaultTableModel(aracBilgiBasliklar, 0);

	// JTABLE ARAÇ yakıt BAŞLIKLAR
	final String aracYakitBasliklar[] = { "No", "Tarih", "Plaka", "Yakıt Türü", "Miktar", "Ödeme Türü", "Belge No",
			"Aktif Km.", "Tutar" };
	final DefaultTableModel aracYakitModel = new DefaultTableModel(aracYakitBasliklar, 0);

	private JTextField textAracAra;
	private JTextField textSaseNo;
	private JTable tableAracYakit;
	private JTextField textYakitBelgeNo;
	private JTextField textYakitAra;

	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {

					// UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");//
					// jtattoo
					// Select the Look and Feel
					UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");

					final MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent e) {

				final int secim = JOptionPane.showConfirmDialog(null, "Programdan çıkmak istediğinizden emin misiniz?",
						"Kapat", JOptionPane.YES_NO_OPTION);
				if (secim == JOptionPane.YES_OPTION) {
					// System.exit(0);
					dispose();
				} else {
					setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
				}

			}
		});
		setTitle("Araç Görev Takip Modülü v1.0 | Developer by  Seyrani DEMİREL");
		setIconImage(Toolkit.getDefaultToolkit().getImage("images/form.png"));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 1072, 586);
		setExtendedState(Frame.MAXIMIZED_BOTH);

		final JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		final JMenu mnNewMenu = new JMenu("Menü");
		menuBar.add(mnNewMenu);

		final JMenuItem mnPlakaEkle = new JMenuItem("Plaka Ekle");
		mnPlakaEkle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				new PlakaFrame().setVisible(true);
			}
		});

		final JMenuItem mnSoforEkle = new JMenuItem("Şöför Ekle");
		mnSoforEkle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				new SoforFrame().setVisible(true);
			}
		});
		mnNewMenu.add(mnSoforEkle);
		mnNewMenu.add(mnPlakaEkle);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		final JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		final JPanel panelGorevEmri = new JPanel();
		tabbedPane.addTab("Görev Emri Bilgi Girişi", null, panelGorevEmri, null);
		panelGorevEmri.setLayout(new BorderLayout(0, 0));

		final JPanel panelGorevEmriSolMenu = new JPanel();
		panelGorevEmriSolMenu.setBorder(new TitledBorder(null, "G\u00F6rev Emri Giri\u015Fi", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panelGorevEmriSolMenu.setToolTipText("");
		panelGorevEmriSolMenu.setPreferredSize(new Dimension(310, 10));
		panelGorevEmri.add(panelGorevEmriSolMenu, BorderLayout.WEST);
		panelGorevEmriSolMenu.setLayout(null);

		final JDateChooser dateChooserGorevTarihi = new JDateChooser();
		dateChooserGorevTarihi.getCalendarButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent e) {

				//// textCikisKm.setText(gorevEmriModel.getValueAt(tableGorevBilgi.getSelectedRow(),
				// 10).toString());
				// textCikisKm.setValue(String.valueOf(tableGorevBilgi.getValueAt(0, 10)));
				textCikisKm.setValue(gorevEmriModel.getValueAt(0, 10));
				textCikisKm.setBackground(new Color(152, 251, 152));
			}
		});
		dateChooserGorevTarihi.setBounds(125, 41, 173, 20);
		panelGorevEmriSolMenu.add(dateChooserGorevTarihi);

		final JLabel lblNewLabel = new JLabel("Görev Tarihi", SwingConstants.RIGHT);
		lblNewLabel.setBounds(24, 41, 91, 14);
		panelGorevEmriSolMenu.add(lblNewLabel);

		textGorevliPersonel = new JTextField();
		textGorevliPersonel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(final KeyEvent e) {
				// büyük harfe çevir
				final int position = textGorevliPersonel.getCaretPosition();
				textGorevliPersonel.setText(textGorevliPersonel.getText().toUpperCase());
				textGorevliPersonel.setCaretPosition(position);
			}
		});
		textGorevliPersonel.setBounds(125, 67, 173, 20);
		panelGorevEmriSolMenu.add(textGorevliPersonel);
		textGorevliPersonel.setColumns(10);

		final JLabel lblNewLabel_1 = new JLabel("Görevli Personel", SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(10, 69, 105, 14);
		panelGorevEmriSolMenu.add(lblNewLabel_1);

		final JLabel lblGrevTr = new JLabel("Görev Türü", SwingConstants.RIGHT);
		lblGrevTr.setBounds(24, 98, 91, 15);
		panelGorevEmriSolMenu.add(lblGrevTr);

		final JComboBox gorevTuruBox = new JComboBox();
		gorevTuruBox.setModel(new DefaultComboBoxModel(new String[] { "--Görev Türü Seçiniz--", "EVDE SAĞLIK",
				"EVRAK TESLİM", "TOPLANTI", "MALZEME TAŞIMA", "DİĞER" }));
		gorevTuruBox.setBounds(125, 93, 173, 24);
		panelGorevEmriSolMenu.add(gorevTuruBox);

		final JLabel lblGidilecekYer = new JLabel("Gidilen Yer", SwingConstants.RIGHT);
		lblGidilecekYer.setBounds(24, 137, 91, 15);
		panelGorevEmriSolMenu.add(lblGidilecekYer);

		final JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(125, 125, 173, 67);
		panelGorevEmriSolMenu.add(scrollPane_1);

		final JTextArea textGidilecekYer = new JTextArea();
		textGidilecekYer.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(final KeyEvent e) {

				// büyük harfe çevir
				final int position = textGidilecekYer.getCaretPosition();
				textGidilecekYer.setText(textGidilecekYer.getText().toUpperCase());
				textGidilecekYer.setCaretPosition(position);

			}
		});
		textGidilecekYer.setBounds(218, 169, 139, 64);
		scrollPane_1.setViewportView(textGidilecekYer);

		final JComboBox soforBox = new JComboBox(new SoforManager().GetAll().toArray());
		soforBox.insertItemAt("--Sürücü Seçiniz--", 0);
		soforBox.setSelectedIndex(0);
		soforBox.setBounds(125, 204, 173, 24);
		panelGorevEmriSolMenu.add(soforBox);

		final JComboBox plakaBox = new JComboBox(new PlakaManager().GetAll().toArray());
		plakaBox.insertItemAt("--Plaka Seçiniz--", 0);
		plakaBox.setSelectedIndex(0);
		plakaBox.setBounds(125, 242, 173, 24);
		panelGorevEmriSolMenu.add(plakaBox);

		final JLabel lblNewLabel_2 = new JLabel("Sürücü", SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(24, 209, 91, 15);
		panelGorevEmriSolMenu.add(lblNewLabel_2);

		final JLabel lblPlaka = new JLabel("Plaka", SwingConstants.RIGHT);
		lblPlaka.setBounds(24, 247, 91, 15);
		panelGorevEmriSolMenu.add(lblPlaka);

		final JSpinner spinnerCikisSaati = new JSpinner();
		spinnerCikisSaati.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.HOUR));
		final DateEditor spinnerCikisSaatiDe = new JSpinner.DateEditor(spinnerCikisSaati, "HH:mm");
		spinnerCikisSaati.setBounds(220, 277, 78, 20);
		spinnerCikisSaati.setEditor(spinnerCikisSaatiDe);
		panelGorevEmriSolMenu.add(spinnerCikisSaati);

		final JLabel lblkdnSaati = new JLabel("Çıkış Km/Saati", SwingConstants.RIGHT);
		lblkdnSaati.setBounds(10, 280, 105, 15);
		panelGorevEmriSolMenu.add(lblkdnSaati);

		final JSpinner spinnerDonusSaati = new JSpinner();
		spinnerDonusSaati.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.HOUR));
		final DateEditor spinnerDonusSaatiDe = new JSpinner.DateEditor(spinnerDonusSaati, "HH:mm");
		spinnerDonusSaati.setBounds(220, 308, 78, 20);
		spinnerDonusSaati.setEditor(spinnerDonusSaatiDe);
		panelGorevEmriSolMenu.add(spinnerDonusSaati);

		final JLabel lblkdnSaati_1 = new JLabel("Dönüş Km/Saati", SwingConstants.RIGHT);
		lblkdnSaati_1.setBounds(10, 310, 105, 15);
		panelGorevEmriSolMenu.add(lblkdnSaati_1);

		textCikisKm = new JSpinner();
		textCikisKm.setEnabled(false);
		textCikisKm.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));

		textCikisKm.setBackground(Color.WHITE);
		textCikisKm.setBounds(125, 277, 78, 20);
		panelGorevEmriSolMenu.add(textCikisKm);

		textDonusKm = new JSpinner();
		textDonusKm.setBackground(Color.WHITE);
		textDonusKm.setBounds(125, 307, 78, 20);
		panelGorevEmriSolMenu.add(textDonusKm);

		final JButton btnEkle = new JButton("Ekle");

		btnEkle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {

				if (textGidilecekYer.getText().equals("") || (textCikisKm.getValue().equals(""))) {

					JOptionPane.showConfirmDialog(null, "Kaydedilecek veri bulunamadı!", "Araç Görev Emri Girişi",
							JOptionPane.PLAIN_MESSAGE);
					return;
				}

				final GorevEmriModel contract = new GorevEmriModel();

				final SoforModel casSoforModel = (SoforModel) soforBox.getSelectedItem();
				final PlakaModel casPlakaModel = (PlakaModel) plakaBox.getSelectedItem();
				final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");// yyyy-MM-dd
				final String date = format.format(dateChooserGorevTarihi.getDate());

				final SimpleDateFormat timeFormat = new SimpleDateFormat("HH.mm");

				final String cikisSaat = timeFormat.format(spinnerCikisSaati.getValue());
				final String donusSaat = timeFormat.format(spinnerDonusSaati.getValue());

				// contract.setMiktar(texYakitMiktar.getValue().toString());
				final int cikisKm = Integer.parseInt(textCikisKm.getValue().toString());
				final int girisKm = Integer.parseInt(textDonusKm.getValue().toString());
				final int yapilanKm = (girisKm - cikisKm);

				contract.setGorevTarihi(date);
				contract.setGorevliPersonel(textGorevliPersonel.getText());
				contract.setGorevTuru(gorevTuruBox.getSelectedItem().toString());
				contract.setGidilecekYer(textGidilecekYer.getText());
				contract.setSoforId(casSoforModel.getId());
				contract.setPlakaId(casPlakaModel.getId());
				// contract.setPlaka(plakaBox.getSelectedItem().toString());
				// contract.setCikisSaati(spinnerCikisSaati.getValue().toString());
				// contract.setDonusSaati(spinnerDonusSaati.getValue().toString());
				contract.setCikisSaati(cikisSaat);
				contract.setDonusSaati(donusSaat);
				contract.setCikisKm(Integer.parseInt(textCikisKm.getValue().toString()));
				contract.setDonusKm(Integer.parseInt(textDonusKm.getValue().toString()));
				// textCikisKm.setText(String.valueOf(tableGorevBilgi.getValueAt(0, 10)));

				contract.setYapilanKm(yapilanKm);

				new GorevEmriManager().insert(contract);
				JOptionPane.showMessageDialog(null, "Kayıt başarıyla eklendi.");
				GorevEmriListele();

				// clear
				textGorevliPersonel.setText(" ");
				textGidilecekYer.setText(" ");
				gorevTuruBox.setSelectedIndex(0);
				soforBox.setSelectedIndex(0);
				plakaBox.setSelectedIndex(0);
				textCikisKm.setValue(0);
				textDonusKm.setValue(0);

			}
		});

		btnEkle.setIcon(new ImageIcon(this.getClass().getResource("/add.png")));
		btnEkle.setBounds(42, 367, 117, 25);
		panelGorevEmriSolMenu.add(btnEkle);

		final JButton btnSil = new JButton("Sil");
		btnSil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {

				try {

					if (tableGorevBilgi.getSelectedRowCount() == 0) {

						JOptionPane.showMessageDialog(null, "Silinecek kaydı tablodan seçiniz !");
						return;
					}

					final int donenCevap = JOptionPane.showConfirmDialog(null,
							"Seçili kaydı silmek istediğinizden emin misiniz?");

					if (donenCevap != 0) {
						return;
					}
					final int id = Integer
							.parseInt(tableGorevBilgi.getValueAt(tableGorevBilgi.getSelectedRow(), 0).toString());

					if (new GorevEmriManager().delete(id)) {
						// kayıt silinemedi
						JOptionPane.showMessageDialog(null, "Kayıt silindi !");
						GorevEmriListele();
					} else {
						JOptionPane.showMessageDialog(null, "Kayıt silinemedi");
					}

				} catch (final Exception ex) {
					JOptionPane.showMessageDialog(null, "Kayıt silinemedi. Hata:" + ex.getMessage());
				}
			}
		});
		btnSil.setBounds(181, 367, 117, 25);
		panelGorevEmriSolMenu.add(btnSil);
		btnSil.setIcon(new ImageIcon(this.getClass().getResource("/delete.png")));
		// panel_2.add(textArea);

		final JPanel panelGorevEmriSagMenu = new JPanel();
		panelGorevEmri.add(panelGorevEmriSagMenu, BorderLayout.CENTER);
		panelGorevEmriSagMenu.setLayout(new BorderLayout(0, 0));

		final JPanel panelGorevEmriUstMenu = new JPanel();
		panelGorevEmriUstMenu.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelGorevEmriUstMenu.setPreferredSize(new Dimension(10, 50));
		panelGorevEmriUstMenu.setMinimumSize(new Dimension(10, 50));
		panelGorevEmriSagMenu.add(panelGorevEmriUstMenu, BorderLayout.NORTH);
		panelGorevEmriUstMenu.setLayout(null);

		final JButton btnAra = new JButton("Ara");
		btnAra.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {

				/* JTable Refresh Code */
				final int satir = gorevEmriModel.getRowCount();
				for (int i = 0; i < satir; i++) {
					gorevEmriModel.removeRow(0);

				}

				for (final GorevEmriModel gorevEmri : new GorevEmriManager().getSearch(textAra.getText())) {

					gorevEmriModel.addRow(gorevEmri.getVeriler());
					tableGorevBilgi.setModel(gorevEmriModel);
				}

			}
		});
		btnAra.setBounds(273, 6, 69, 37);
		panelGorevEmriUstMenu.add(btnAra);
		btnAra.setIcon(new ImageIcon(this.getClass().getResource("/search.png")));

		textAra = new JTextField();
		textAra.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(final KeyEvent e) {

				final int position = textAra.getCaretPosition();
				textAra.setText(textAra.getText().toUpperCase());
				textAra.setCaretPosition(position);

			}
		});
		textAra.setBounds(10, 9, 253, 31);
		panelGorevEmriUstMenu.add(textAra);
		textAra.setColumns(10);

		final JPanel panelGorevEmriTableMenu = new JPanel();
		panelGorevEmriSagMenu.add(panelGorevEmriTableMenu, BorderLayout.CENTER);
		panelGorevEmriTableMenu.setLayout(new BorderLayout(0, 0));

		final JScrollPane scrollPane = new JScrollPane();

		panelGorevEmriTableMenu.add(scrollPane, BorderLayout.CENTER);

		// JTABLE DOLDUR LİSTELE
		tableGorevBilgi = new JTable(gorevEmriModel);
		tableGorevBilgi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent e) {
				// textCikisKm.setText(gorevEmriModel.getValueAt(tableGorevBilgi.getSelectedRow(),
				// 10).toString());

			}
		});
		GorevEmriListele();
		scrollPane.setViewportView(tableGorevBilgi);

		final JPanel panelAracBilgi = new JPanel();
		tabbedPane.addTab("Araç Sözleşme Bilgi Girişi", null, panelAracBilgi, null);
		panelAracBilgi.setLayout(new BorderLayout(0, 0));

		final JPanel panelAracBilgiSolMenu = new JPanel();
		panelAracBilgiSolMenu.setBorder(new TitledBorder(null, "Ara\u00E7 \u0130\u015Flemleri", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panelAracBilgiSolMenu.setPreferredSize(new Dimension(310, 10));
		panelAracBilgi.add(panelAracBilgiSolMenu, BorderLayout.WEST);
		panelAracBilgiSolMenu.setLayout(null);

		final JLabel lblNewLabel_3 = new JLabel("Plaka", SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(34, 31, 61, 14);
		panelAracBilgiSolMenu.add(lblNewLabel_3);

		final JComboBox boxAracTuru = new JComboBox();
		boxAracTuru.setModel(new DefaultComboBoxModel(new String[] { "--SEÇİM YAPINIZ--", "BİNEK", "KAMYONET" }));
		boxAracTuru.setBounds(105, 55, 180, 22);
		panelAracBilgiSolMenu.add(boxAracTuru);

		final JLabel lblNewLabel_4 = new JLabel("Araç Türü", SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(12, 59, 81, 14);
		panelAracBilgiSolMenu.add(lblNewLabel_4);

		final JComboBox boxMarka = new JComboBox();
		boxMarka.setModel(
				new DefaultComboBoxModel(new String[] { "--SEÇİM YAPINIZ--", "FIAT", "PEUGEOT", "FORD", "" }));
		boxMarka.setBounds(105, 88, 180, 22);
		panelAracBilgiSolMenu.add(boxMarka);

		final JDateChooser dateSozlesmeBaslama = new JDateChooser();
		dateSozlesmeBaslama.setBounds(105, 187, 180, 22);
		panelAracBilgiSolMenu.add(dateSozlesmeBaslama);

		final JLabel lblNewLabel_4_1 = new JLabel("Marka", SwingConstants.RIGHT);
		lblNewLabel_4_1.setBounds(34, 92, 61, 14);
		panelAracBilgiSolMenu.add(lblNewLabel_4_1);

		final JLabel lblNewLabel_4_1_1 = new JLabel("Model", SwingConstants.RIGHT);
		lblNewLabel_4_1_1.setBounds(32, 125, 61, 14);
		panelAracBilgiSolMenu.add(lblNewLabel_4_1_1);

		final JLabel lblNewLabel_4_1_1_1 = new JLabel("Model Yılı", SwingConstants.RIGHT);
		lblNewLabel_4_1_1_1.setBounds(12, 158, 81, 14);
		panelAracBilgiSolMenu.add(lblNewLabel_4_1_1_1);

		final JLabel lblNewLabel_4_1_1_1_1 = new JLabel("Söz.Baş.Tar.", SwingConstants.RIGHT);
		lblNewLabel_4_1_1_1_1.setBounds(0, 195, 98, 14);
		panelAracBilgiSolMenu.add(lblNewLabel_4_1_1_1_1);

		final JComboBox boxModelYil = new JComboBox();
		boxModelYil.setModel(new DefaultComboBoxModel(new String[] { "--SEÇİM YAPINIZ--", "2015", "2016", "2017",
				"2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025" }));
		boxModelYil.setBounds(105, 154, 180, 22);
		panelAracBilgiSolMenu.add(boxModelYil);

		final JLabel lblNewLabel_4_1_1_1_1_1 = new JLabel("Durum", SwingConstants.RIGHT);
		lblNewLabel_4_1_1_1_1_1.setBounds(34, 326, 61, 14);
		panelAracBilgiSolMenu.add(lblNewLabel_4_1_1_1_1_1);

		textGelenKm = new JSpinner();
		textGelenKm.setBounds(105, 255, 180, 22);
		panelAracBilgiSolMenu.add(textGelenKm);

		final JLabel lblNewLabel_4_1_1_2 = new JLabel("Aktif Km.", SwingConstants.RIGHT);
		lblNewLabel_4_1_1_2.setBounds(12, 259, 81, 14);
		panelAracBilgiSolMenu.add(lblNewLabel_4_1_1_2);

		final JComboBox boxPlakaArac = new JComboBox(new PlakaManager().GetAll().toArray());
		boxPlakaArac.insertItemAt("--Plaka Seçiniz--", 0);
		boxPlakaArac.setSelectedIndex(0);
		boxPlakaArac.setBounds(105, 27, 180, 22);
		panelAracBilgiSolMenu.add(boxPlakaArac);

		final JComboBox boxAracDurum = new JComboBox();
		boxAracDurum.setModel(new DefaultComboBoxModel(new String[] { "--SEÇİM YAPINIZ--", "FAAL", "FAAL DEĞİL" }));
		boxAracDurum.setBounds(105, 322, 180, 22);
		panelAracBilgiSolMenu.add(boxAracDurum);

		final JComboBox boxModel = new JComboBox();
		boxModel.setModel(
				new DefaultComboBoxModel(new String[] { "--SEÇİM YAPINIZ--", "DOBLO", "FIORINO", "PARTNER" }));
		boxModel.setBounds(105, 121, 180, 22);
		panelAracBilgiSolMenu.add(boxModel);

		final JButton btnSilArac = new JButton("Sil");
		btnSilArac.setIcon(new ImageIcon(this.getClass().getResource("/delete.png")));
		btnSilArac.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {

				try {

					if (tableAracBilgi.getSelectedRowCount() == 0) {

						JOptionPane.showMessageDialog(null, "Silinecek kaydı tablodan seçiniz !");
						return;
					}

					final int donenCevap = JOptionPane.showConfirmDialog(null,
							"Seçili kaydı silmek istediğinizden emin misiniz?");

					if (donenCevap != 0) {
						return;
					}
					final int id = Integer
							.parseInt(tableAracBilgi.getValueAt(tableAracBilgi.getSelectedRow(), 0).toString());

					if (new AracBilgiManager().delete(id)) {
						// kayıt silinemedi
						JOptionPane.showMessageDialog(null, "Kayıt silindi !");
						AracBilgiListele();
					} else {
						JOptionPane.showMessageDialog(null, "Kayıt silinemedi");
					}

				} catch (final Exception ex) {
					InfoMessage.ShowMsg("Kayıt silinemedi. Hata:" + ex.getMessage());
					// JOptionPane.showMessageDialog(null, "Kayıt silinemedi. Hata:" +
					// ex.getMessage());
				}

			}
		});
		btnSilArac.setBounds(168, 356, 117, 25);
		panelAracBilgiSolMenu.add(btnSilArac);

		textSaseNo = new JTextField();
		textSaseNo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(final KeyEvent e) {
				// büyük harf
				final int position = textSaseNo.getCaretPosition();
				textSaseNo.setText(textSaseNo.getText().toUpperCase());
				textSaseNo.setCaretPosition(position);

			}
		});
		textSaseNo.setBounds(105, 291, 180, 20);
		panelAracBilgiSolMenu.add(textSaseNo);
		textSaseNo.setColumns(10);

		final JLabel lblNewLabel_4_1_1_2_1 = new JLabel("Şase No", SwingConstants.RIGHT);
		lblNewLabel_4_1_1_2_1.setBounds(14, 294, 81, 14);
		panelAracBilgiSolMenu.add(lblNewLabel_4_1_1_2_1);

		final JLabel lblNewLabel_4_1_1_1_1_2 = new JLabel("Söz.Bit.Tar.", SwingConstants.RIGHT);
		lblNewLabel_4_1_1_1_1_2.setBounds(-3, 229, 98, 14);
		panelAracBilgiSolMenu.add(lblNewLabel_4_1_1_1_1_2);

		final JDateChooser dateBitisTarihi = new JDateChooser();
		dateBitisTarihi.setBounds(105, 221, 180, 22);
		panelAracBilgiSolMenu.add(dateBitisTarihi);

		final JPanel panelAracBilgiSagMenu = new JPanel();
		panelAracBilgi.add(panelAracBilgiSagMenu, BorderLayout.CENTER);
		panelAracBilgiSagMenu.setLayout(new BorderLayout(0, 0));

		final JPanel panelAracBilgiUstMenu = new JPanel();
		panelAracBilgiUstMenu.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelAracBilgiUstMenu.setPreferredSize(new Dimension(10, 50));
		panelAracBilgiSagMenu.add(panelAracBilgiUstMenu, BorderLayout.NORTH);
		panelAracBilgiUstMenu.setLayout(null);

		final JButton btnEkleArac = new JButton("Ekle");
		btnEkleArac.setIcon(new ImageIcon(this.getClass().getResource("/add.png")));
		btnEkleArac.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {

//				if (textSaseNo.getText().equals("") || (boxPlakaArac.getSelectedItem().equals(""))) {
//
//					JOptionPane.showConfirmDialog(null, "Kaydedilecek veri bulunamadı!", "Araç Görev Emri Girişi",
//							JOptionPane.PLAIN_MESSAGE);
//					return;
//				}

				final AracBilgiModel contract = new AracBilgiModel();
				// final SoforModel casSoforModel = (SoforModel) soforBox.getSelectedItem();
				// final PlakaModel casPlakaModel = (PlakaModel) plakaBox.getSelectedItem();
				final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");// yyyy-MM-dd

				final String dateStart = format.format(dateSozlesmeBaslama.getDate());
				final String dateEnd = format.format(dateBitisTarihi.getDate());

				// final SimpleDateFormat timeFormat = new SimpleDateFormat("HH.mm");

				contract.setPlaka(boxPlakaArac.getSelectedItem().toString());
				contract.setAracTuru(boxAracTuru.getSelectedItem().toString());
				contract.setMarka(boxMarka.getSelectedItem().toString());
				contract.setModel(boxModel.getSelectedItem().toString());
				contract.setModelYil(boxModelYil.getSelectedItem().toString());
				contract.setBaslamaTarihi(dateStart);
				contract.setBitisTarihi(dateEnd);
				contract.setGelenKm(textGelenKm.getValue().toString());
				contract.setSaseNo(textSaseNo.getText());
				contract.setDurum(boxAracDurum.getSelectedItem().toString());

				new AracBilgiManager().insert(contract);
				JOptionPane.showMessageDialog(null, "Kayıt başarıyla eklendi.");
				AracBilgiListele();

				// clear
				boxPlakaArac.setSelectedIndex(0);
				boxAracDurum.setSelectedIndex(0);
				boxAracTuru.setSelectedIndex(0);
				boxMarka.setSelectedIndex(0);
				boxModel.setSelectedIndex(0);
				boxModelYil.setSelectedIndex(0);
				textSaseNo.setText(" ");

			}
		});
		btnEkleArac.setBounds(34, 356, 117, 25);
		panelAracBilgiSolMenu.add(btnEkleArac);

		textAracAra = new JTextField();
		textAracAra.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(final KeyEvent e) {
				// büyük harfe çevir
				final int position = textAracAra.getCaretPosition();
				textAracAra.setText(textAracAra.getText().toUpperCase());
				textAracAra.setCaretPosition(position);
			}
		});
		textAracAra.setBounds(12, 12, 253, 31);
		panelAracBilgiUstMenu.add(textAracAra);
		textAracAra.setColumns(10);

		final JButton btnAracAra = new JButton("Ara");
		btnAracAra.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {

				/* JTable Refresh Code */
				final int satir = aracBilgiModel.getRowCount();
				for (int i = 0; i < satir; i++) {
					aracBilgiModel.removeRow(0);

				}

				for (final AracBilgiModel gorevEmri : new AracBilgiManager().getSearch(textAracAra.getText())) {

					aracBilgiModel.addRow(gorevEmri.getVeriler());
					tableAracBilgi.setModel(aracBilgiModel);
				}

			}
		});
		btnAracAra.setIcon(new ImageIcon(this.getClass().getResource("/search.png")));
		btnAracAra.setBounds(276, 9, 69, 37);
		panelAracBilgiUstMenu.add(btnAracAra);

		final JScrollPane scrollPaneAracBilgiTable = new JScrollPane();
		panelAracBilgiSagMenu.add(scrollPaneAracBilgiTable, BorderLayout.CENTER);

		// JTable Araç Bilgi Listele
		tableAracBilgi = new JTable(aracBilgiModel);
		AracBilgiListele();
		// tableAracBilgi.setModel(new DefaultTableModel(new Object[][] {}, new String[]
		// { "New column", "New column" }));
		scrollPaneAracBilgiTable.setViewportView(tableAracBilgi);

		final JPanel panelAracYakit = new JPanel();
		tabbedPane.addTab("Araç Yakıt Bilgi Girişi", null, panelAracYakit, null);
		panelAracYakit.setLayout(new BorderLayout(0, 0));

		final JPanel panelAracYakitSol = new JPanel();
		panelAracYakitSol.setBorder(new TitledBorder(null, "Yak\u0131t \u0130\u015Flemleri", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panelAracYakitSol.setPreferredSize(new Dimension(310, 10));
		panelAracYakit.add(panelAracYakitSol, BorderLayout.WEST);
		panelAracYakitSol.setLayout(null);

		final JDateChooser dateYakitTarih = new JDateChooser();
		dateYakitTarih.setBounds(120, 28, 180, 22);
		panelAracYakitSol.add(dateYakitTarih);

		final JComboBox boxYakitTuru = new JComboBox();
		boxYakitTuru.setModel(new DefaultComboBoxModel(
				new String[] { "--SEÇİM YAPINIZ--", "EURO DIZEL", "KURŞUNSUZ BENZİN", "LPG" }));
		boxYakitTuru.setBounds(120, 93, 180, 22);
		panelAracYakitSol.add(boxYakitTuru);

		final JComboBox boxYakitPlaka = new JComboBox(new PlakaManager().GetAll().toArray());
		boxYakitPlaka.insertItemAt("--Plaka Seçiniz--", 0);
		boxYakitPlaka.setSelectedIndex(0);
		boxYakitPlaka.setBounds(120, 60, 180, 22);
		panelAracYakitSol.add(boxYakitPlaka);

		final JSpinner texYakitMiktar = new JSpinner();
		texYakitMiktar.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		texYakitMiktar.setBounds(120, 126, 180, 22);
		panelAracYakitSol.add(texYakitMiktar);

		final JComboBox boxYakitOdemeTuru = new JComboBox();
		boxYakitOdemeTuru.setModel(new DefaultComboBoxModel(new String[] { "--SEÇİM YAPINIZ--", "YAKIT FİŞİ" }));
		boxYakitOdemeTuru.setBounds(120, 159, 180, 22);
		panelAracYakitSol.add(boxYakitOdemeTuru);

		textYakitBelgeNo = new JTextField();
		textYakitBelgeNo.setBounds(120, 192, 180, 22);
		panelAracYakitSol.add(textYakitBelgeNo);
		textYakitBelgeNo.setColumns(10);

		final JSpinner textYakitAktifKm = new JSpinner();
		textYakitAktifKm.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		textYakitAktifKm.setBounds(120, 226, 180, 22);
		panelAracYakitSol.add(textYakitAktifKm);

		final JSpinner textYakitTutar = new JSpinner();
		textYakitTutar.setBounds(120, 259, 180, 22);
		panelAracYakitSol.add(textYakitTutar);

		final JLabel lblNewLabel_5 = new JLabel("Tarih", SwingConstants.RIGHT);
		lblNewLabel_5.setBounds(10, 36, 96, 14);
		panelAracYakitSol.add(lblNewLabel_5);

		final JLabel lblNewLabel_5_1 = new JLabel("Plaka", SwingConstants.RIGHT);
		lblNewLabel_5_1.setBounds(10, 64, 96, 14);
		panelAracYakitSol.add(lblNewLabel_5_1);

		final JLabel lblNewLabel_5_1_1 = new JLabel("Yakıt Türü", SwingConstants.RIGHT);
		lblNewLabel_5_1_1.setBounds(10, 97, 96, 14);
		panelAracYakitSol.add(lblNewLabel_5_1_1);

		final JLabel lblNewLabel_5_1_1_1 = new JLabel("Miktar", SwingConstants.RIGHT);
		lblNewLabel_5_1_1_1.setBounds(10, 130, 96, 14);
		panelAracYakitSol.add(lblNewLabel_5_1_1_1);

		final JLabel lblNewLabel_5_1_1_1_1 = new JLabel("Ödeme Türü", SwingConstants.RIGHT);
		lblNewLabel_5_1_1_1_1.setBounds(10, 163, 96, 14);
		panelAracYakitSol.add(lblNewLabel_5_1_1_1_1);

		final JLabel lblNewLabel_5_1_1_1_1_1 = new JLabel("Belge No", SwingConstants.RIGHT);
		lblNewLabel_5_1_1_1_1_1.setBounds(10, 196, 96, 14);
		panelAracYakitSol.add(lblNewLabel_5_1_1_1_1_1);

		final JLabel lblNewLabel_5_1_1_1_1_1_1 = new JLabel("Aktif Km.", SwingConstants.RIGHT);
		lblNewLabel_5_1_1_1_1_1_1.setBounds(10, 230, 96, 14);
		panelAracYakitSol.add(lblNewLabel_5_1_1_1_1_1_1);

		final JLabel lblNewLabel_5_1_1_1_1_1_1_1 = new JLabel("Tutar", SwingConstants.RIGHT);
		lblNewLabel_5_1_1_1_1_1_1_1.setBounds(10, 263, 96, 14);
		panelAracYakitSol.add(lblNewLabel_5_1_1_1_1_1_1_1);

		final JButton btnYakitSil = new JButton("Sil");
		btnYakitSil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {

				try {

					if (tableAracYakit.getSelectedRowCount() == 0) {

						JOptionPane.showMessageDialog(null, "Silinecek kaydı tablodan seçiniz !");
						return;
					}

					final int donenCevap = JOptionPane.showConfirmDialog(null,
							"Seçili kaydı silmek istediğinizden emin misiniz?");

					if (donenCevap != 0) {
						return;
					}
					final int id = Integer
							.parseInt(tableAracYakit.getValueAt(tableAracYakit.getSelectedRow(), 0).toString());

					if (new AracYakitManager().delete(id)) {
						// kayıt silinemedi
						JOptionPane.showMessageDialog(null, "Kayıt silindi !");
						AracYakitListele();
					} else {
						JOptionPane.showMessageDialog(null, "Kayıt silinemedi");
					}

				} catch (final Exception ex) {
					InfoMessage.ShowMsg("Kayıt silinemedi. Hata:" + ex.getMessage());
					// JOptionPane.showMessageDialog(null, "Kayıt silinemedi. Hata:" +
					// ex.getMessage());
				}

			}
		});
		btnYakitSil.setIcon(new ImageIcon(this.getClass().getResource("/delete.png")));
		btnYakitSil.setBounds(179, 324, 117, 25);
		panelAracYakitSol.add(btnYakitSil);

		final JPanel panelAracYakitSag = new JPanel();
		panelAracYakit.add(panelAracYakitSag, BorderLayout.CENTER);
		panelAracYakitSag.setLayout(new BorderLayout(0, 0));

		final JPanel panelAracYakitUst = new JPanel();
		panelAracYakitUst.setPreferredSize(new Dimension(10, 50));
		panelAracYakitSag.add(panelAracYakitUst, BorderLayout.NORTH);
		panelAracYakitUst.setLayout(null);

		textYakitAra = new JTextField();
		textYakitAra.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(final KeyEvent e) {

				// büyük harf
				final int position = textYakitAra.getCaretPosition();
				textYakitAra.setText(textYakitAra.getText().toUpperCase());
				textYakitAra.setCaretPosition(position);

			}
		});
		textYakitAra.setBounds(10, 9, 253, 31);
		panelAracYakitUst.add(textYakitAra);
		textYakitAra.setColumns(10);

		final JButton btnYakitAra = new JButton("Ara");
		btnYakitAra.setIcon(new ImageIcon(this.getClass().getResource("/search.png")));
		btnYakitAra.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {

				/* JTable Refresh Code */
				final int satir = aracYakitModel.getRowCount();
				for (int i = 0; i < satir; i++) {
					aracYakitModel.removeRow(0);

				}

				for (final AracYakitModel aracYakit : new AracYakitManager().getSearch(textYakitAra.getText())) {

					aracYakitModel.addRow(aracYakit.getVeriler());
					tableAracYakit.setModel(aracYakitModel);
				}

			}
		});
		btnYakitAra.setBounds(273, 6, 69, 37);
		panelAracYakitUst.add(btnYakitAra);

		final JScrollPane scrollPaneAracYakitTable = new JScrollPane();
		panelAracYakitSag.add(scrollPaneAracYakitTable, BorderLayout.CENTER);

		tableAracYakit = new JTable(aracYakitModel);
		AracYakitListele();
		scrollPaneAracYakitTable.setViewportView(tableAracYakit);

		final JButton btnYakitEkle = new JButton("Ekle");

		btnYakitEkle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				// araç yakıt ekle
//				if (textSaseNo.getText().equals("") || (boxPlakaArac.getSelectedItem().equals(""))) {
				//
//										JOptionPane.showConfirmDialog(null, "Kaydedilecek veri bulunamadı!", "Araç Görev Emri Girişi",
//												JOptionPane.PLAIN_MESSAGE);
//										return;
//									}

				final AracYakitModel contract = new AracYakitModel();

				final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");// yyyy-MM-dd

				final String date = format.format(dateYakitTarih.getDate());

				contract.setTarih(date);
				contract.setPlaka(boxYakitPlaka.getSelectedItem().toString());
				contract.setYakitTuru(boxYakitTuru.getSelectedItem().toString());
				// contract.setCikisKm(Integer.parseInt(textCikisKm.getText()));
				contract.setMiktar(texYakitMiktar.getValue().toString());
				contract.setOdemeTuru(boxYakitOdemeTuru.getSelectedItem().toString());
				contract.setBelgeNo(textYakitBelgeNo.getText());
				contract.setAktifKm(textYakitAktifKm.getValue().toString());
				contract.setTutar(Double.parseDouble(textYakitTutar.getValue().toString()));

				new AracYakitManager().insert(contract);
				JOptionPane.showMessageDialog(null, "Kayıt başarıyla eklendi.");
				AracYakitListele();

				// clear Text
				boxYakitPlaka.setSelectedIndex(0);
				boxYakitOdemeTuru.setSelectedIndex(0);
				boxYakitTuru.setSelectedIndex(0);
				texYakitMiktar.setValue(0);
				textYakitAktifKm.setValue(0);
				textYakitTutar.setValue(0);
				textYakitBelgeNo.setText(" ");

			}
		});

		btnYakitEkle.setBounds(52, 324, 117, 25);
		btnYakitEkle.setIcon(new ImageIcon(this.getClass().getResource("/add.png")));
		panelAracYakitSol.add(btnYakitEkle);

		// ComboGorevTuruDoldur();
		// GorevEmriListele();

	}

	// jTABLE GörevEmri Listele
	private void GorevEmriListele() {

		/* JTable Refresh Code */
		final int satir = gorevEmriModel.getRowCount();
		for (int i = 0; i < satir; i++) {
			gorevEmriModel.removeRow(0);

		}

		// jtable gorev emri listele
		for (final GorevEmriModel gorevEmri : new GorevEmriManager().getList()) {
			gorevEmriModel.addRow(gorevEmri.getVeriler());
		}

	}

	// jTABLE AraçBilgi Listele
	private void AracBilgiListele() {

		/* JTable Refresh Code */
		final int satir = aracBilgiModel.getRowCount();
		for (int i = 0; i < satir; i++) {
			aracBilgiModel.removeRow(0);

		}

		// jtable araç bilgi listele
		for (final AracBilgiModel aracBilgi : new AracBilgiManager().getList()) {
			aracBilgiModel.addRow(aracBilgi.getVeriler());
		}

	}

	// jTABLE AraçYakit Listele
	private void AracYakitListele() {

		/* JTable Refresh Code */
		final int satir = aracYakitModel.getRowCount();
		for (int i = 0; i < satir; i++) {
			aracYakitModel.removeRow(0);

		}

		// jtable araç bilgi listele
		for (final AracYakitModel aracYakit : new AracYakitManager().getList()) {
			aracYakitModel.addRow(aracYakit.getVeriler());
		}

	}

	private void TextFieldClear() {
		textGorevliPersonel.setText(" ");
		textCikisKm.setValue(" ");
		textDonusKm.setValue(" ");
		// textGidilecekYer.setText("");

	}
}
