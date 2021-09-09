package tr.org.sd.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import tr.org.sd.bll.SoforManager;
import tr.org.sd.dal.SoforModel;
import tr.org.sd.helper.InfoMessage;

public class SoforFrame extends JFrame {

	private final JPanel contentPane;
	private final JPanel panel;
	private final JTextField textSofor;

	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					final SoforFrame frame = new SoforFrame();
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
	public SoforFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("images/form.png"));
		setTitle("Şöför Ekle");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 331, 368);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Sofor Ekle", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setPreferredSize(new Dimension(10, 100));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(null);

		final JLabel lblNewLabel = new JLabel("Şöför");
		lblNewLabel.setBounds(30, 27, 46, 14);
		panel.add(lblNewLabel);

		textSofor = new JTextField();
		textSofor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(final KeyEvent e) {

				// büyük harfe çevir
				final int position = textSofor.getCaretPosition();
				textSofor.setText(textSofor.getText().toUpperCase());
				textSofor.setCaretPosition(position);

			}
		});
		textSofor.setBounds(86, 24, 199, 20);
		panel.add(textSofor);
		textSofor.setColumns(10);

		// kayıt listele
		final JList list = new JList(new SoforManager().getList().toArray());
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(list, BorderLayout.CENTER);

		final JButton btnEkle = new JButton("Ekle");
		btnEkle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {

				final SoforModel contract = new SoforModel();
				contract.setAdSoyad(textSofor.getText());

				new SoforManager().insert(contract);
				// JOptionPane.showMessageDialog(textSofor, contract.getAdSoyad() + " " + " adlı
				// kişi kayıt edildi");
				InfoMessage.ShowMsg("success");// helper
				list.setListData(new SoforManager().getList().toArray());

				textSofor.setText("");

			}
		});
		btnEkle.setIcon(new ImageIcon(this.getClass().getResource("/add.png")));
		btnEkle.setBounds(82, 55, 89, 23);
		panel.add(btnEkle);

		final JButton btnSil = new JButton("Sil");
		btnSil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {

				final int selectedIndex = list.getSelectedIndex();
				if (selectedIndex != -1) {
					final SoforModel contract = (SoforModel) list.getSelectedValue();
					new SoforManager().delete(contract);
					JOptionPane.showMessageDialog(textSofor, contract.getAdSoyad() + " " + " adlı kayıt silindi.");
					list.setListData(new SoforManager().getList().toArray());
				} else {
					InfoMessage.ShowMsg("Silinecek kaydı listeden seçiniz!");

				}

			}
		});
		btnSil.setIcon(new ImageIcon(this.getClass().getResource("/delete.png")));
		btnSil.setBounds(196, 55, 89, 23);
		panel.add(btnSil);

	}

}
