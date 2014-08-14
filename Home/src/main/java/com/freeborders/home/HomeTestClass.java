package com.freeborders.home;

import com.freeborders.base.AbstractTestClass;
import com.freeborders.base.annotation.TestClass;
import com.freeborders.base.annotation.TestMethod;
import com.freeborders.base.context.TestApplicationContext;
import com.freeborders.base.enumeration.BrowserType;
import com.freeborders.base.enumeration.ModelNameEnum;
import com.freeborders.base.exception.NoDataException;
import com.freeborders.base.utils.TableElementFinder;
import com.freeborders.base.utils.WaitElementPresent;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author nelson.yang
 */
@TestClass(testClassName = "home",
modelName = ModelNameEnum.HOME)
public class HomeTestClass extends AbstractTestClass {

    private static final Map<String, String> map = new HashMap<String, String>(12, 1);

    @Override
    public void beforeClass() {
        super.beforeClass();
        map.put("New Staff", "ABACUS - New Staff Approval");
        map.put("Rate Changes", "ABACUS - Approve Rate Change");
        map.put("General Message", "");
        map.put("Settled Cases", "ABACUS - Case Data Information ");

    }

    @TestMethod(testMethodName = "testViewMessage",
    testMethodNum = 1,
    modelName = ModelNameEnum.HOME,
    desc = "view a messge")
    public void testViewDetail() {
        WebDriver ieDriver = TestApplicationContext.CTX.getWebDriver(BrowserType.IE);
        WaitElementPresent.waitElement(ieDriver, 50, By.id("messageTab"));
        //find table
        WebElement trElement =  TableElementFinder.findFirstRequireElementTR(By.id("messageTab"), 3,null);
        WebElement textTdElement = trElement.findElement(By.xpath("./td[4]"));
        WebElement tdElement = trElement.findElement(By.xpath("./td[3]"));
        WebElement aElement = tdElement.findElement(By.tagName("a"));
        if(textTdElement != null){
            if (map.containsKey(textTdElement.getText())) {
               aElement.click();
               this.assertEqual(map.get(textTdElement.getText()), ieDriver.getTitle().trim());
            }
        }else{
            throw new NoDataException("td element doesnot exist");
        }
    }

    @TestMethod(testMethodName = "testDeleteMessage",
    testMethodNum = 1,
    modelName = ModelNameEnum.HOME,
    desc = "delete a messge")
    public void testDeleteMessage() {
        WebDriver ieDriver = TestApplicationContext.CTX.getWebDriver(BrowserType.IE);
        //find table
        WaitElementPresent.waitElement(ieDriver, 50, By.id("messageTab"));
        WebElement trElement = TableElementFinder.findFirstRequireElementTR(By.id("messageTab"), map);
        WebElement inputElement = trElement.findElement(By.name("cbxValue"));
        inputElement.click();
        ieDriver.findElement(By.id("Delete")).click();
        ieDriver.switchTo().alert().accept();
        WaitElementPresent.waitElement(ieDriver, 10, By.id("errlabel"));
        this.assertEqual("The Message has been successfully deleted.", ieDriver.findElement(By.id("errlabel")).getText().trim());

    }

    @TestMethod(testMethodName = "testDeleteSystemMessage",
    testMethodNum = 1,
    modelName = ModelNameEnum.HOME,
    desc = "delete a system messge")
    public void testDeleteSystemMessage() {
        WebDriver ieDriver = TestApplicationContext.CTX.getWebDriver(BrowserType.IE);
        //find table
        WaitElementPresent.waitElement(ieDriver, 50, By.id("messageTab"));
        WebElement trElement = TableElementFinder.findFirstRequireElementTR(By.id("messageTab"), "System Message");
        WebElement inputElement = trElement.findElement(By.id("cbxValue"));
        inputElement.click();
        ieDriver.findElement(By.id("Delete")).click();
        this.assertEqual("You can not delete the System Message!", ieDriver.findElement(By.id("errlabel")).getText().trim());
    }
}
