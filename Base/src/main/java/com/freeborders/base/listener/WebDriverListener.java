package com.freeborders.base.listener;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.internal.Base64Encoder;
import org.openqa.selenium.remote.ScreenshotException;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import com.freeborders.base.log.Logger;

/**
 * take snap picture
 * @author nelson.yang
 */
public class WebDriverListener extends AbstractWebDriverEventListener{
    @Override
    public void onException(Throwable throwable, WebDriver driver){
        Throwable cause = throwable.getCause();
         if(cause instanceof ScreenshotException){
            FileOutputStream fos = null;
            try {
                String pageTitle = driver.getTitle();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");//文件名不能用冒号
                String curentDateTime = sdf.format(new Date());
                String pngName = pageTitle + curentDateTime + ".png";
                fos = new FileOutputStream(pngName);
                byte[] screenShotPNG =   new Base64Encoder().decode(((ScreenshotException) cause).getBase64EncodedScreenshot());
                fos.write(screenShotPNG);
                fos.flush();
            } catch (IOException ex) {
                Logger.severe("onException", ex.getMessage(), cause);
            } finally {
                try {
                    if(fos != null){
                        fos.close();
                    }
                } catch (IOException ex) {
                      Logger.severe("onException", ex.getMessage(), cause);
                }
            }
         }
    }
}
