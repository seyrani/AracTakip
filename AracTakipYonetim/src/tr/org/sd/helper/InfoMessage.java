package tr.org.sd.helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class InfoMessage {

	public static void optionPaneChangeButtonText() {
		UIManager.put("OptionPane.cancelButtonText", "İptal");
		UIManager.put("OptionPane.yesButtonText", "Evet");
		UIManager.put("OptionPane.noButtonText", "Hayır");
		UIManager.put("OptionPane.okButtonText", "Tamam");

	}

	public static void ShowMsg(final String str) {

		String msg;
		optionPaneChangeButtonText();
		switch (str) {
		case "fill":
			msg = "Zorunlu alanlar boş geçilemez!";
			break;
		case "success":
			msg = "İşlem başarılı !";
			break;
		case "error":
			msg = "Hata oluştu !";
			break;
		default:
			msg = str;
		}

		JOptionPane.showMessageDialog(null, msg, "Mesaj", JOptionPane.INFORMATION_MESSAGE);

	}

	public static boolean confirm(final String str) {
		String msg;
		switch (str) {
		case "sure":
			msg = "Bu işlemi gerçekleştirmek istediğinizden emin misiniz !";
			break;
		default:
			msg = str;
			break;
		}

		final int res = JOptionPane.showConfirmDialog(null, msg, "Dikkat !", JOptionPane.YES_NO_OPTION);

		if (res == 0) {
			return true;
		} else {
			return false;
		}

	}

}
