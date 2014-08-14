package com.freeborders.base.utils;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver.Window;

import com.freeborders.base.AbstractTestClass;
import com.freeborders.base.annotation.TestClass;
import com.freeborders.base.annotation.TestMethod;
import com.freeborders.base.api.TestReportHandler;
import com.freeborders.base.context.TestApplicationContext;
import com.freeborders.base.entity.TestImageEntity;
import com.freeborders.base.log.Logger;
import com.google.common.io.Files;

public class ScreenCaptureUtils {
	private static Rectangle rec = null;

	/**
	 * capture img by Selenium Driver
	 * 
	 * @param clazz
	 *            class name
	 * @param methodName
	 *            method name
	 * @return null if exception happened
	 */

	public static String captureByDriver(Class<? extends AbstractTestClass> clazz, String methodName) {
		String imageName = null;
		String imageUrl = null;
		try {
			Method method = clazz.getDeclaredMethod(methodName, new Class[] { TestImageEntity.class });
			String testMethodName = method.getName();
			String modelName = method.getDeclaringClass().getAnnotation(TestClass.class).modelName().toString();
			String testCaseId = method.getAnnotation(TestMethod.class).testCaseId();
			String testClassName = clazz.getAnnotation(TestClass.class).testClassName();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			TestReportHandler testReportHandler = TestApplicationContext.CTX.getTestReportHandler();
			imageName = testMethodName + " " + sdf.format(new Date()) + ".png";

			// get the url of image
			imageUrl = testReportHandler.appendImage(testCaseId, imageName, testClassName, modelName);
			// new image folder
			File dirfile = new File(imageUrl.substring(0, imageUrl.lastIndexOf("\\")));
			if (!dirfile.exists()) {
				dirfile.mkdirs();
			}
			// save the image in the image folder.
			File tempFile = ((TakesScreenshot) TestApplicationContext.CTX.getDefaultDriver())
					.getScreenshotAs(OutputType.FILE);
			File descfile = new File(imageUrl);
			try {
				if (descfile.createNewFile()) {
					Files.copy(tempFile, descfile);
				} else {
					Logger.warn("takescreenshot", "create file failure");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			Logger.info("takescreenshot", imageUrl);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.openqa.selenium.NoSuchWindowException e) {
			e.printStackTrace();
		} catch (org.openqa.selenium.UnhandledAlertException e) {
			e.printStackTrace();
		}
		return imageUrl;
	}

	/**
	 * capture by driver
	 * 
	 * @param imageEntity
	 * @return
	 */
	public static String captureByDriver(TestImageEntity imageEntity) {
		String imageName = null;
		String imageUrl = null;
		try {
			String testMethodName = imageEntity.getTestMethodName();
			String modelName = imageEntity.getModelName().toString();
			String testCaseId = imageEntity.getTestCaseId();
			String testClassName = imageEntity.getTestClassName();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			TestReportHandler testReportHandler = TestApplicationContext.CTX.getTestReportHandler();
			imageName = testMethodName + " " + sdf.format(new Date()) + ".png";

			// get the url of image
			imageUrl = testReportHandler.appendImage(testCaseId, imageName, testClassName, modelName);
			// new image folder
			File dirfile = new File(imageUrl.substring(0, imageUrl.lastIndexOf("\\")));
			if (!dirfile.exists()) {
				dirfile.mkdirs();
			}
			// save the image in the image folder.
			File tempFile = ((TakesScreenshot) imageEntity.getWebDriver()).getScreenshotAs(OutputType.FILE);
			File descfile = new File(imageUrl);
			try {
				if (descfile.createNewFile()) {
					Files.copy(tempFile, descfile);
				} else {
					Logger.warn("takescreenshot", "create file failure");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			Logger.info("takescreenshot", imageUrl);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.openqa.selenium.NoSuchWindowException e) {
			e.printStackTrace();
		} catch (org.openqa.selenium.UnhandledAlertException e) {
			e.printStackTrace();
		}
		return imageUrl;
	}

	/**
	 * capture img by AWT
	 * 
	 * @param clazz
	 *            class name
	 * @param methodName
	 *            method name
	 * @return null if exception happened
	 */
	public static String captureByAWT(Class<? extends AbstractTestClass> clazz, String methodName) {
		String imageUrl = null;
		try {
			Method method = clazz.getDeclaredMethod(methodName, new Class[] { TestImageEntity.class });
			Robot robot = new Robot();
			Rectangle rectangle = new Rectangle(1500, 900);
			BufferedImage screenShot = robot.createScreenCapture(rectangle);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			Date fileDate = new Date();
			String modelName = method.getDeclaringClass().getAnnotation(TestClass.class).modelName().toString();
			String imageName = method.getName() + "_" + sdf.format(fileDate) + ".jpg";
			TestReportHandler testReportHandler = TestApplicationContext.CTX.getTestReportHandler();
			String testCaseId = method.getAnnotation(TestMethod.class).testCaseId();
			String testClassName = clazz.getAnnotation(TestClass.class).testClassName();
			imageUrl = testReportHandler.appendImage(testCaseId, imageName, testClassName, modelName);
			// new the image folder
			File dirfile = new File(imageUrl.substring(0, imageUrl.lastIndexOf("\\")));
			if (!dirfile.exists()) {
				dirfile.mkdirs();
			}
			ImageIO.write(screenShot, "JPG", new File(imageUrl));

		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imageUrl;
	}

	/**
	 * capture image by AWT and the parameter is entity
	 * 
	 * @param imageEntity
	 * @return
	 */
	public static String captureByAWT(TestImageEntity imageEntity) {
		String imageUrl = null;
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_PRINTSCREEN);
			robot.keyRelease(KeyEvent.VK_PRINTSCREEN);
			robot.keyRelease(KeyEvent.VK_ALT);
			Rectangle rectangle = null;
			// full screen
			if (imageEntity.isFullScreen()) {
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				rectangle = new Rectangle(screenSize.width, screenSize.height);
			} else if (imageEntity.isMaxBrowserSize()) {
				// full browser size
				rectangle = rec;
			} else {
				// active screen
				Window win = imageEntity.getWebDriver().manage().window();
				org.openqa.selenium.Point p = win.getPosition();
				rectangle = new Rectangle(p.getX() + 2, p.getY() + 3, win.getSize().width - 4, win.getSize().height - 3);
			}

			BufferedImage screenShot = robot.createScreenCapture(rectangle);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			Date fileDate = new Date();
			String modelName = imageEntity.getModelName().toString();
			String imageName = imageEntity.getTestMethodName() + "_" + sdf.format(fileDate) + ".jpg";
			TestReportHandler testReportHandler = TestApplicationContext.CTX.getTestReportHandler();
			String testCaseId = imageEntity.getTestCaseId();
			String testClassName = imageEntity.getTestClassName();
			imageUrl = testReportHandler.appendImage(testCaseId, imageName, testClassName, modelName);
			// new the image folder
			File dirfile = new File(imageUrl.substring(0, imageUrl.lastIndexOf("\\")));
			if (!dirfile.exists()) {
				dirfile.mkdirs();
			}
			ImageIO.write(screenShot, "JPG", new File(imageUrl));

		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imageUrl;
	}

	public static String captureByAWT2(TestImageEntity imageEntity) {
		String imageUrl = null;
		try {
			Robot robot = new Robot();
			// full screen
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_PRINTSCREEN);
			robot.keyRelease(KeyEvent.VK_PRINTSCREEN);
			robot.keyRelease(KeyEvent.VK_ALT);
			WaitTimeoutUtils.sleep(1);
			BufferedImage screenShot = getClipboard();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			Date fileDate = new Date();
			String modelName = imageEntity.getModelName().toString();
			String imageName = imageEntity.getTestMethodName() + "_" + sdf.format(fileDate) + ".jpg";
			TestReportHandler testReportHandler = TestApplicationContext.CTX.getTestReportHandler();
			String testCaseId = imageEntity.getTestCaseId();
			String testClassName = imageEntity.getTestClassName();
			imageUrl = testReportHandler.appendImage(testCaseId, imageName, testClassName, modelName);
			// new the image folder
			File dirfile = new File(imageUrl.substring(0, imageUrl.lastIndexOf("\\")));
			if (!dirfile.exists()) {
				dirfile.mkdirs();
			}
			ImageIO.write(screenShot, "JPG", new File(imageUrl));

		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imageUrl;
	}

	public static void setMaxBrowser(int x, int y, int width, int height) {
		rec = new Rectangle(x, y, width, height);
	}

	public static BufferedImage getClipboard() {
		// Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
		//
		// try {
		// if (t != null && t.isDataFlavorSupported(DataFlavor.imageFlavor)) {
		// BufferedImage text = (BufferedImage) t.getTransferData(DataFlavor.imageFlavor);
		// return text;
		// }
		// } catch (UnsupportedFlavorException e) {
		// } catch (IOException e) {
		// }
		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();

		ImageIcon IMG=null;
		try {
			IMG = new ImageIcon((BufferedImage) clip.getData(DataFlavor.imageFlavor));
			BufferedImage bImage = getBufferedImage(IMG.getImage());
			return bImage;
		} catch (UnsupportedFlavorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static BufferedImage getBufferedImage(Image img) {
		if (img == null) {
			return null;
		}
		int w = img.getWidth(null);
		int h = img.getHeight(null);
		// draw original image to thumbnail image object and
		// scale it to the new size on-the-fly
		BufferedImage bufimg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = bufimg.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(img, 0, 0, w, h, null);
		g2.dispose();
		return bufimg;
	}
}
