package lk.ci.int_cn_system.UI.SimQuotation;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.declarative.Design;

/** 
 * !! DO NOT EDIT THIS FILE !!
 * 
 * This class is generated by Vaadin Designer and will be overwritten.
 * 
 * Please make a subclass with logic and additional interfaces as needed,
 * e.g class LoginView extends LoginDesign implements View { }
 */
@DesignRoot
@AutoGenerated
@SuppressWarnings("serial")
public class BusinessReport extends AbsoluteLayout {
	protected DateField enddate;
	protected DateField startdate;
	protected ComboBox reportitem_combobox;
	protected Button excel_btn;

	public BusinessReport() {
		Design.read(this);
	}
}
