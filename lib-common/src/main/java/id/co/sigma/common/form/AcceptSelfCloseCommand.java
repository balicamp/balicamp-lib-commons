package id.co.sigma.common.form;

import com.google.gwt.user.client.Command;


/**
 * panel yang bisa menerima close command. kadang yang bisa mengclose diri nya sendiri adalah owner dari panel. jadinya agar bisa di close panel musti di kirimi  close command
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 **/
public interface AcceptSelfCloseCommand {
	
	
	
	/**
	 * assign close command untuk panel. jadinya panel di kasi command agar bisa menutup diri nya sendiri
	 * @param closePanelCommand command untuk menutup dirinya sendiri
	 * 
	 **/
	public void assignCloseCommand (Command closePanelCommand); 

}
