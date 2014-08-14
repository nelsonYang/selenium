package com.freeborders.base.utils.administration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.freeborders.base.entity.TestImageEntity;
import com.freeborders.base.entity.administration.OptionData;
import com.freeborders.base.enumeration.AdministrationPage;
import com.freeborders.base.utils.RadioSelector;
import com.freeborders.base.utils.Selector;
import com.freeborders.base.utils.SubMenuSelector;
import com.freeborders.base.utils.TextInputUtils;

public class OptionsUtils {
    public static void editOptions(WebDriver webDriver, OptionData data) {
    	SubMenuSelector.administrationSubMenu(AdministrationPage.OPTIONS);
    	TextInputUtils.editText(webDriver, By.name("OptionsForm.photocopyRate"), data.getPhotocopyRate());
    	TextInputUtils.editText(webDriver, By.name("OptionsForm.maxTravelTime"), data.getMaxTravelTime());
    	TextInputUtils.editText(webDriver, By.name("OptionsForm.maxBillingHoursPerDay"), data.getMaxBillingHoursPerDay());
    	Selector.select(webDriver, By.name("OptionsForm.authorizerId"), data.getAuthorizerName());
    	Selector.select(webDriver, By.name("OptionsForm.NotifiedUserId"), data.getNotifiedUserName());
    	TextInputUtils.editText(webDriver, By.name("OptionsForm.bankruptcySpecialProject"), data.getBankruptcySpecialProject());
    	RadioSelector.selectCheckbox(webDriver, By.name("freezeProcess"), data.getReadOnly());
    	TextInputUtils.editText(webDriver, By.name("OptionsForm.systemMessage"), data.getSystemMessage());
    	webDriver.findElement(By.name("Save")).click();
    }
}
