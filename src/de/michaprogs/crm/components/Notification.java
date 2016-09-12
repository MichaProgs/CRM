package de.michaprogs.crm.components;

import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class Notification {
	
	/**
	 * 
	 * @param TitleType tt - which kind of title should be shown 
	 * @param Mesage msg - which id and name/description was used
	 * @param MsgType mt - which kind of mesage should be shown
	 * @param NotificationType nt - which kind of notification should be used
	 */
	public Notification(String title, String msg, NotificationType NotificationType){
		
		TrayNotification tn = new TrayNotification();
		tn.setTitle(title);
		tn.setMessage(msg);
		tn.setNotificationType(NotificationType);
		tn.setAnimationType(AnimationType.POPUP);
		tn.showAndDismiss(new Duration(4000));;
		
	}
	
}
