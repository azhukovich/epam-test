package com.epam.test.framework;

/**
 * Created by azhukovich on 3/26/2015.
 */

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.Assert;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;


/**
 * Canned {@link org.openqa.selenium.support.ui.ExpectedCondition}s which are generally useful within webdriver
 * tests.
 */
public class CustomExpectedConditions {

    private final static Logger log = Logger.getLogger(CustomExpectedConditions.class.getName());

    private CustomExpectedConditions() {
        // Utility class
    }

    /**
     * An expectation for checking the title of a page.
     *
     * @param title the expected title, which must be an exact match
     * @return true when the title matches, false otherwise
     */
    public static ExpectedCondition<Boolean> titleIs(final String title) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return title.equals(driver.getTitle());
            }

            @Override
            public String toString() {
                return "title to be: " + title;
            }
        };
    }

    /**
     * An expectation for checking that the title contains a case-sensitive
     * substring
     *
     * @param title the fragment of title expected
     * @return true when the title matches, false otherwise
     */
    public static ExpectedCondition<Boolean> titleContains(final String title) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                String currentTitle = driver.getTitle();
                return currentTitle == null ? false : currentTitle.contains(title);
            }

            @Override
            public String toString() {
                return "title to contain " + title;
            }
        };
    }

    /**
     * An expectation for checking that an element is present on the DOM of a
     * page. This does not necessarily mean that the element is visible.
     *
     * @param locator used to find the element
     * @return the WebElement once it is located
     */
    public static ExpectedCondition<WebElement> presenceOfElementLocated(
            final By locator) {
        return new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver driver) {
                return findElement(locator, driver);
            }

            @Override
            public String toString() {
                return "presence of element located by: " + locator;
            }
        };
    }

    /**
     * An expectation for checking that an element is present on the DOM of a page
     * and visible. Visibility means that the element is not only displayed but
     * also has a height and width that is greater than 0.
     *
     * @param locator used to find the element
     * @return the WebElement once it is located and visible
     */
    public static ExpectedCondition<WebElement> visibilityOfElementLocated(
            final By locator) {

        return new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver driver) {
                try {
                    return elementIfVisible(findElement(locator, driver));
                } catch (StaleElementReferenceException e) {
                    return null;
                }
            }

            @Override
            public String toString() {
                return "visibility of element located by " + locator;
            }
        };
    }

    /**
     * An expectation for checking that an element, known to be present on the DOM
     * of a page, is visible. Visibility means that the element is not only
     * displayed but also has a height and width that is greater than 0.
     *
     * @param element the WebElement
     * @return the (same) WebElement once it is visible
     */
    public static ExpectedCondition<WebElement> visibilityOf(
            final WebElement element) {
        return new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver driver) {
                return elementIfVisible(element);
            }

            @Override
            public String toString() {
                return "visibility of " + element;
            }
        };
    }

    /**
     * @return the given element if it is visible and has non-zero size, otherwise
     *         null.
     */
    private static WebElement elementIfVisible(WebElement element) {
        return element.isDisplayed() ? element : null;
    }

    /**
     * An expectation for checking that there is at least one element present on a
     * web page.
     *
     * @param locator used to find the element
     * @return the list of WebElements once they are located
     */
    public static ExpectedCondition<List<WebElement>> presenceOfAllElementsLocatedBy(
            final By locator) {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ExpectedCondition<List<WebElement>>() {
            public List<WebElement> apply(WebDriver driver) {

                return findElements(locator, driver);
            }

            @Override
            public String toString() {
                return "presence of any elements located by " + locator;
            }
        };
    }

    /**
     * An expectation for checking if the given text is present in the specified
     * element.
     */
    public static ExpectedCondition<Boolean> textToBePresentInElement(
            final By locator, final String text) {

        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver from) {
                try {
                    String elementText = findElement(locator, from).getText();
                    return elementText.contains(text);
                } catch (StaleElementReferenceException e) {
                    return null;
                }
            }

            @Override
            public String toString() {
                return String.format("text ('%s') to be present in element found by %s",
                        text, locator);
            }
        };
    }

    /**
     * An expectation for checking if the given text is present in the specified
     * elements value attribute.
     */
    public static ExpectedCondition<Boolean> textToBePresentInElementValue(
            final By locator, final String text) {

        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver from) {
                try {
                    String elementText = findElement(locator, from).getAttribute("value");
                    if (elementText != null) {
                        return elementText.contains(text);
                    } else {
                        return false;
                    }
                } catch (StaleElementReferenceException e) {
                    return null;
                }
            }

            @Override
            public String toString() {
                return String.format("text ('%s') to be the value of element located by %s",
                        text, locator);
            }
        };
    }

    /**
     * An expectation for checking whether the given frame is available to switch
     * to. <p> If the frame is available it switches the given driver to the
     * specified frame.
     */
    public static ExpectedCondition<WebDriver> frameToBeAvailableAndSwitchToIt(
            final String frameLocator) {
        return new ExpectedCondition<WebDriver>() {
            public WebDriver apply(WebDriver from) {
                try {
                    return from.switchTo().frame(frameLocator);
                } catch (NoSuchFrameException e) {
                    return null;
                }
            }

            @Override
            public String toString() {
                return "frame to be available: " + frameLocator;
            }
        };
    }

    /**
     * An Expectation for checking that an element is either invisible or not
     * present on the DOM.
     *
     * @param locator used to find the element
     */
    public static ExpectedCondition<Boolean> invisibilityOfElementLocated(
            final By locator) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    return !(findElement(locator, driver).isDisplayed());
                } catch (NoSuchElementException e) {
                    // Returns true because the element is not present in DOM. The
                    // try block checks if the element is present but is invisible.
                    return true;
                } catch (StaleElementReferenceException e) {
                    // Returns true because stale element reference implies that element
                    // is no longer visible.
                    return true;
                }
            }

            @Override
            public String toString() {
                return "element to no longer be visible: " + locator;
            }
        };
    }

    /**
     * An Expectation for checking an element is visible and enabled such that you
     * can click it.
     */
    public static ExpectedCondition<WebElement> elementToBeClickable(
            final By locator) {

        return new ExpectedCondition<WebElement>() {

            public ExpectedCondition<WebElement> visibilityOfElementLocated =
                    CustomExpectedConditions.visibilityOfElementLocated(locator);

            public WebElement apply(WebDriver driver) {
                WebElement element = visibilityOfElementLocated.apply(driver);
                try {
                    if (element != null && element.isEnabled()) {

                        return element;
                    } else {
                        return null;
                    }
                } catch (StaleElementReferenceException e) {
                    return null;
                }
            }

            @Override
            public String toString() {
                return "element to be clickable: " + locator;
            }
        };
    }
    /**
     * Wait until an element is no longer attached to the DOM.
     *
     * @param element The element to wait for.
     * @return false is the element is still attached to the DOM, true
     *         otherwise.
     */
    public static ExpectedCondition<Boolean> stalenessOf(
            final WebElement element) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver ignored) {
                try {
                    // Calling any method forces a staleness check
                    element.isEnabled();
                    return false;
                } catch (StaleElementReferenceException expected) {
                    return true;
                }
            }

            @Override
            public String toString() {
                return String.format("element (%s) to become stale", element);
            }
        };
    }

    /**
     * An expectation for checking if the given element is selected.
     */
    public static ExpectedCondition<Boolean> elementToBeSelected(final WebElement element) {
        return elementSelectionStateToBe(element, true);
    }

    /**
     * An expectation for checking if the given element is selected.
     */
    public static ExpectedCondition<Boolean> elementSelectionStateToBe(final WebElement element,
                                                                       final boolean selected) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver from) {
                return element.isSelected() == selected;
            }

            @Override
            public String toString() {
                return String.format("element (%s) to %sbe selected", element, (selected ? "" : "not "));
            }
        };
    }

    public static ExpectedCondition<Boolean> elementToBeSelected(final By locator) {
        return elementSelectionStateToBe(locator, true);
    }

    public static ExpectedCondition<Boolean> elementSelectionStateToBe(final By locator,
                                                                       final boolean selected) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver from) {
                try {
                    WebElement element = from.findElement(locator);
                    return element.isSelected() == selected;
                } catch (StaleElementReferenceException e) {
                    return null;
                }
            }

            @Override
            public String toString() {
                return String.format("element found by %s to %sbe selected",
                        locator, (selected ? "" : "not "));
            }
        };
    }

    public static ExpectedCondition<Alert> alertIsPresent() {
        return new ExpectedCondition<Alert>() {
            public Alert apply(WebDriver input) {
                try {
                    return input.switchTo().alert();
                } catch (NoAlertPresentException e) {
                    return null;
                }
            }

            @Override
            public String toString() {
                return "alert to be present";
            }
        };
    }

    /**
     * Looks up an element. Logs and re-throws WebDriverException if thrown. <p/>
     * Method exists to gather data for http://code.google.com/p/selenium/issues/detail?id=1800
     */
    private static WebElement findElement(By by, WebDriver driver) {
        try {
            return driver.findElement(by);
        } catch (NoSuchElementException e) {
            throw e;
        } catch (WebDriverException e) {
            log.log(Level.WARNING,
                    String.format("WebDriverException thrown by findElement(%s)", by), e);
            throw e;
        }
    }

    /**
     * @see #findElement(By, WebDriver)
     */
    private static List<WebElement> findElements(By by, WebDriver driver) {
        try {
            return driver.findElements(by);
        } catch (WebDriverException e) {
            log.log(Level.WARNING,
                    String.format("WebDriverException thrown by findElement(%s)", by), e);
            throw e;
        }
    }

// custom methods

    /**
     * An expectation for checking whether the given frame is available to switch
     * to. <p> If the frame is available it switches the given driver to the
     * specified frame.
     *
     *
     */
    public static ExpectedCondition<WebDriver> frameToBeAvailableAndSwitchToIt(
            final WebElement frameLocator) {
        return new ExpectedCondition<WebDriver>() {
            public WebDriver apply(WebDriver from) {
                try {
                    return from.switchTo().frame(frameLocator);
                } catch (NoSuchFrameException e) {
                    return null;
                }
            }

            @Override
            public String toString() {
                return "frame to be available: " + frameLocator;
            }
        };
    }

    /** new added
     * An expectation for checking whether the given frame is available to switch
     * to. <p> If the frame is available it switches the given driver to the
     * specified frame.
     */
    public static ExpectedCondition<WebDriver> frameToBeAvailableAndSwitchToIt(
            final int frameLocator) {
        return new ExpectedCondition<WebDriver>() {
            public WebDriver apply(WebDriver from) {
                try {
                    return from.switchTo().frame(frameLocator);
                } catch (NoSuchFrameException e) {
                    return null;
                }
            }

            @Override
            public String toString() {
                return "frame to be available: " + frameLocator;
            }
        };
    }

    /**
     * An Expectation for checking an element is visible and enabled such that you
     * can click it.
     *
     * @param element The element to wait for.
     * @throws InterruptedException
     *
     */

    public static ExpectedCondition<WebElement> popupToBeOpened(
            final WebElement element) throws InterruptedException {
        final String str = "top: 0px";
        return new ExpectedCondition<WebElement>() {

            public ExpectedCondition<WebElement> visibilityOf =
                    CustomExpectedConditions.visibilityOf(element);

            public WebElement apply(WebDriver driver) {

                WebElement element = visibilityOf.apply(driver);

                try {
                    if (element.getAttribute("style").toLowerCase().contains(str)) {
                        return element;
                    } else {
                        return null;
                    }
                } catch (StaleElementReferenceException e) {
                    return null;
                }


            }

            @Override
            public String toString() {
                return "popup to be opened: " + element;
            }
        };
    }
    public static void waitForPopupToBeOpened(
            final WebElement element, int second) throws InterruptedException {
        String str = "top: 0px";
        for ( second = 0;; second++) {
            if (second >= 10) Assert.fail("timeout");
            try { if (element.getAttribute("style").toLowerCase().contains(str)) break; } catch (Exception e) {}
            Thread.sleep(1000);
        }
    }
    public static void waitForPanelUploadToBeOpened(
            final WebElement element, int second) throws InterruptedException {
        String str = "top: 0px";
        for ( second = 0;; second++) {
            if (second >= 10) Assert.fail("timeout");
            try { if (element.getAttribute("style").toLowerCase().contains(str)) break; } catch (Exception e) {}
            Thread.sleep(1000);
        }
    }

    /**
     * An Expectation for checking an element is visible and enabled such that you
     * can click it.
     *
     * @param element The element to wait for.
     * @throws InterruptedException
     *
     */

    public static ExpectedCondition<WebElement> panelUploadToBeOpened(
            final WebElement element) throws InterruptedException {

        return new ExpectedCondition<WebElement>() {

            public ExpectedCondition<WebElement> visibilityOf =
                    CustomExpectedConditions.visibilityOf(element);

            public WebElement apply(WebDriver driver) {

                WebElement element = visibilityOf.apply(driver);

                try {
                    if (element.getAttribute("style").trim().equalsIgnoreCase("margin-left: -251.5px; margin-right: -251.5px; height: 280px;")) {
                        return element;
                    } else {
                        return null;
                    }
                } catch (StaleElementReferenceException e) {
                    return null;
                }


            }

            @Override
            public String toString() {
                return "popup to be opened: " + element;
            }
        };
    }
    /**
     * An Expectation for checking an element is visible and enabled such that you
     * can click it.
     *
     * @param element The element to wait for.
     *
     */

    public static ExpectedCondition<WebElement> elementToBeClickable(
            final WebElement element) {
        return new ExpectedCondition<WebElement>() {
            public ExpectedCondition<WebElement> visibilityOf =
                    CustomExpectedConditions.visibilityOf(element);

            public WebElement apply(WebDriver driver) {
                WebElement element = visibilityOf.apply(driver);
                try {
                    if (element != null && element.isEnabled()) {
                        return element;
                    } else {
                        return null;
                    }
                } catch (StaleElementReferenceException e) {
                    return null;
                }


            }

            @Override
            public String toString() {
                return "element to be clickable: " + element;
            }
        };
    }
    /**
     * An expectation for checking that an element is present on the DOM of a page
     * and visible. Visibility means that the element is not only displayed but
     * also has a height and width that is greater than 0.
     *
     * @param element used to find the element
     * @return the WebElement once it is located and visible
     */
    public static ExpectedCondition<WebElement> visibilityOfElement(
            final WebElement element) {
        return new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver driver) {
                try {
                    return elementIfVisible(element);
                } catch (StaleElementReferenceException e) {
                    return null;
                }
            }

            @Override
            public String toString() {
                return "visibility of element " + element;
            }
        };
    }

    /**
     * An expectation for checking if the given text is present in the specified
     * element.
     */
    public static ExpectedCondition<Boolean> textToBePresentInElement(
            final WebElement element, final String text) {

        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver from) {
                try {
                    String elementText = element.getText();
                    return elementText.contains(text);
                } catch (StaleElementReferenceException e) {
                    return null;
                }
            }

            @Override
            public String toString() {
                return String.format("text ('%s') to be present in element found by %s",
                        text, element);
            }
        };
    }
    public static ExpectedCondition<Boolean> ajaxCompleted() {

        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                return (Boolean) js.executeScript("return $.active == 0");
            }

        };
    }

    public static ExpectedCondition<Boolean> pageLoaded() {

        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                return (Boolean) js.executeScript("return document.readyState === 'complete'");
            }

        };
    }

    /**
     * An Expectation for checking that an element is either invisible or not
     * present on the DOM.
     *
     * @param element used to find the element
     */
    public static ExpectedCondition<Boolean> invisibilityOfElement(
            final WebElement element) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {

                try {
                    return !(element.isDisplayed());
                } catch (NoSuchElementException e) {
                    // Returns true because the element is not present in DOM. The
                    // try block checks if the element is present but is invisible.
                    return true;
                } catch (StaleElementReferenceException e) {
                    // Returns true because stale element reference implies that element
                    // is no longer visible.
                    return true;
                }
            }

            @Override
            public String toString() {
                return "element to no longer be visible: " + element;
            }
        };
    }
    /**
     * An expectation for checking if the given text is present in the specified
     * element.
     */
    public static ExpectedCondition<Boolean> textToBeAbsentInElement(
            final WebElement element, final String text) {

        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {

                try {
                    String elementText = element.getText();
                    return !(elementText.contains(text));
                } catch (NoSuchElementException e) {
                    // Returns true because the element is not present in DOM. The
                    // try block checks if the element is present but is invisible.
                    return true;
                } catch (StaleElementReferenceException e) {
                    // Returns true because stale element reference implies that element
                    // is no longer visible.
                    return true;
                }
            }

            @Override
            public String toString() {
                return String.format("text ('%s') absent in element  %s",
                        text, element);
            }
        };
    }

    /**
     * An expectation for checking if the given text is present in the specified
     * element.
     */
    public static ExpectedCondition<Boolean> textToBeAbsentInElement(
            final By locator, final String text) {

        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver from) {
                try {
                    String elementText = findElement(locator, from).getText();
                    return !(elementText.contains(text));
                } catch (NoSuchElementException e) {
                    // Returns true because the element is not present in DOM. The
                    // try block checks if the element is present but is invisible.
                    return true;
                } catch (StaleElementReferenceException e) {
                    // Returns true because stale element reference implies that element
                    // is no longer visible.
                    return true;
                }
            }

            @Override
            public String toString() {
                return String.format("text ('%s') absent in element found by %s",
                        text, locator);
            }
        };
    }

    public static boolean isElementPresent(WebDriver driver, By by) {
        try {
            driver.findElement(by);
            return true;  // Success!
        } catch (NoSuchElementException ignored) {
            return false;
        }
    }
    public static boolean isElementPresent(final WebElement element) {
        try {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return element.isDisplayed();  // Success!
        } catch (NoSuchElementException ignored) {
            return false;
        }
    }
    public static ExpectedCondition<WebElement> linkLogoutAppears(
            final WebElement element) throws InterruptedException {

        return new ExpectedCondition<WebElement>() {

            public ExpectedCondition<WebElement> visibilityOf =
                    CustomExpectedConditions.visibilityOf(element);

            public WebElement apply(WebDriver driver) {

                WebElement element = visibilityOf.apply(driver);

                try {
                    if (element.getText().equals("Выйти")) {
                        return element;
                    } else {
                        return null;
                    }
                } catch (StaleElementReferenceException e) {
                    return null;
                }


            }

            @Override
            public String toString() {
                return "popup to be opened: " + element;
            }
        };
    }
    public static ExpectedCondition<WebElement> linkLoginAppears(
            final WebElement element) throws InterruptedException {

        return new ExpectedCondition<WebElement>() {

            public ExpectedCondition<WebElement> visibilityOf =
                    CustomExpectedConditions.visibilityOf(element);

            public WebElement apply(WebDriver driver) {

                WebElement element = visibilityOf.apply(driver);

                try {
                    if (element.getText().equals("Войти")) {
                        return element;
                    } else {
                        return null;
                    }
                } catch (StaleElementReferenceException e) {
                    return null;
                }


            }

            @Override
            public String toString() {
                return "popup to be opened: " + element;
            }
        };
    }


}


