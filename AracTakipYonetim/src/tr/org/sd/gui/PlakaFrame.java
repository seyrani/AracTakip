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

import tr.org.sd.bll.PlakaManager;
import tr.org.sd.dal.PlakaModel;
import tr.org.sd.helper.InfoMessage;

public class PlakaFrame extends JFrame {

	private final JPanel contentPane;
	private final JPanel panel;
	private final JTextField textPlaka;

	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					final PlakaFrame frame = new PlakaFrame();
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
	public PlakaFrame() {

		setIconImage(Toolkit.getDefaultToolkit().getImage("images/form.png"));
		setTitle("Plaka Ekle");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 331, 368);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Plaka Ekle", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setPreferredSize(new Dimension(10, 100));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(null);
		setLocationRelativeTo(null);

		final JLabel lblNewLabel = new JLabel("Plaka");
		lblNewLabel.setBounds(30, 27, 46, 14);
		panel.add(lblNewLabel);

		textPlaka = new JTextField();
		textPlaka.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(final KeyEvent e) {
				// büyük harfe çevir
				final int position = textPlaka.getCaretPosition();
				textPlaka.setText(textPlaka.getText().toUpperCase());
				textPlaka.setCaretPosition(position);
			}
		});
		textPlaka.setBounds(86, 24, 199, 20);
		panel.add(textPlaka);
		textPlaka.setColumns(10);

		// kayıt listele
		final JList list = new JList(new PlakaManager().getList().toArray());
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(list, BorderLayout.CENTER);

		final JButton btnEkle = new JButton("Ekle");
		btnEkle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {

				final PlakaModel contract = new PlakaModel();
				contract.setPlaka(textPlaka.getText());

				new PlakaManager().insert(contract);
				JOptionPane.showMessageDialog(textPlaka, contract.getPlaka() + " " + " plaka kayıt edildi");
				list.setListData(new PlakaManager().getList().toArray());

				textPlaka.setText("");
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
					final PlakaModel contract = (PlakaModel) list.getSelectedValue();
					new PlakaManager().delete(contract);
					JOptionPane.showMessageDialog(textPlaka, contract.getPlaka() + " " + " adlı kayıt silindi.");
					list.setListData(new PlakaManager().getList().toArray());
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
