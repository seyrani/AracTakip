package tr.org.sd.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import tr.org.sd.bll.KullaniciManager;
import tr.org.sd.bll.KullaniciYetkiManager;
import tr.org.sd.dal.KullaniciModel;
import tr.org.sd.helper.InfoMessage;

public class LoginFrame extends JFrame {

	private final JPanel contentPane;
	private final JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					final LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("images/form.png"));
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 442, 229);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		// form küçültme kapatma tuşlarını kapatır
		// setUndecorated(true);
		// getRootPane().setWindowDecorationStyle(JRootPane.NONE);

		final JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(10, 50));
		panel.setPreferredSize(new Dimension(10, 50));
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(null);

		final JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(150, 10));
		contentPane.add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(null);

		final JLabel lblLogin = new JLabel("");
		lblLogin.setIcon(new ImageIcon(this.getClass().getResource("/aracTakip.png")));
		lblLogin.setBounds(32, 12, 94, 100);
		panel_1.add(lblLogin);

		final JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);

		passwordField = new JPasswordField();
		passwordField.setBounds(110, 62, 160, 24);
		panel_2.add(passwordField);

		final JLabel lblKullancAd = new JLabel("Kullanıcı Adı");
		lblKullancAd.setBounds(12, 32, 95, 15);
		panel_2.add(lblKullancAd);

		final JLabel lblifre = new JLabel("Şifre");
		lblifre.setBounds(12, 63, 80, 17);
		panel_2.add(lblifre);

		final JComboBox emailBox = new JComboBox(new KullaniciManager().GetAll().toArray());
		emailBox.setBounds(110, 26, 160, 24);
		panel_2.add(emailBox);

		final JButton btnGiris = new JButton("Giriş");
		btnGiris.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {

				final KullaniciModel contract = (KullaniciModel) emailBox.getSelectedItem();
				final String sifre = passwordField.getText();

				if (new KullaniciYetkiManager().GetPersoneIdVeSifre(contract.getId(), sifre).getId() != 0) {
					new MainFrame().setVisible(true);
					new LoginFrame().setVisible(false);
					dispose();

				} else {
					InfoMessage.ShowMsg("Kullanıcı adı veya şifre hatalı!");
				}

			}
		});
		btnGiris.setIcon(new ImageIcon(this.getClass().getResource("/ok.png")));
		btnGiris.setBounds(122, 12, 117, 25);
		panel.add(btnGiris);

		final JButton btnIptal = new JButton("İptal");
		btnIptal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				dispose();
			}
		});
		btnIptal.setIcon(new ImageIcon(this.getClass().getResource("/cancel.png")));
		btnIptal.setBounds(261, 12, 117, 25);
		panel.add(btnIptal);

	}
}
