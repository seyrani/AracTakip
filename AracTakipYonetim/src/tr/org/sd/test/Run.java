package tr.org.sd.test;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import tr.org.sd.gui.LoginFrame;

public class Run {

	public static void main(final String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				/* Nimbus Tema */
//				try {
//					for (final LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//						if ("Nimbus".equals(info.getName())) {
//							UIManager.setLookAndFeel(info.getClassName());
//							break;
//						}
//					}
//				} catch (final Exception e) {
//					// If Nimbus is not available, you can set the GUI to another look and feel.

//				}

				/*
				 * com.jtattoo.plaf.smart.SmartLookAndFeel
				 * com.jtattoo.plaf.acryl.AcrylLookAndFeel com.jtattoo.plaf.luna.LunaLookAndFeel
				 * com.jtattoo.plaf.hifi.HiFiLookAndFeel
				 * 
				 * com.jtattoo.plaf.mcwin.McWinLookAndFeel
				 */

				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
				} catch (final ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (final InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (final IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (final UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {

						// new FrmAnaPencere();
						new LoginFrame().setVisible(true);
						// new UrunlerDAL().GetAll();
					}
				});

			}
		});

	}

}
