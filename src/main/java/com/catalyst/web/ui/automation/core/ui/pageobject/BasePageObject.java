package com.catalyst.web.ui.automation.core.ui.pageobject;

import com.catalyst.web.ui.automation.core.ui.properties.DriverProperties;
import com.catalyst.web.ui.automation.core.ui.webdriver.WebdriverCreator;
import com.catalyst.web.ui.automation.webdriverpool.WebDriverPool;
import com.catalyst.web.ui.automation.webdriverpool.WebDriverPool.Webdrivers;
import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("com.catalyst.web.ui.automation.core.ui.pageobject.BasePageObject")
public class BasePageObject<T extends WebElement> {

  @Autowired
  WebDriverPool driver;

  @Autowired
  DriverProperties driverProperties;

  @Autowired
  private WebdriverCreator webriverCreator;

  /**
   * Perform clear action for specific element. Will wait the element until visible before
   * performing the action using explicit timeout.
   *
   * @param by locator of the element.
   */
  public void clear(By by) {
    waitUntilVisible(by).clear();
  }

  /**
   * Perform click action for specific element. Will wait the element until clickable before
   * performing the action using explicit timeout.
   *
   * @param by               locator of the element.
   * @param timeoutInSeconds timeout in seconds.
   */
  public void clickOn(By by, Integer timeoutInSeconds) {
    waitUntilClickable(by, timeoutInSeconds).click();
  }

  /**
   * Perform click action for specific element. Will wait the element until clickable before
   * performing the action using explicit timeout.
   *
   * @param by locator of the element.
   */
  public void clickOn(By by) {
    clickOn(by, getExplicitTimeout());
  }

  /**
   * Reduce intermiten error while try to click element
   * that not loader properly in some page.
   *
   * @param by locator of the element.
   */
  public void clickWithTryCatch(By by) {
    try {
      clickOn(by);
    } catch (Exception e) {
      clickOn(by);
    }
  }

  /**
   * Clear all selected entries. This is only valid when the SELECT supports multiple selections.
   * Using cssSelector locator.
   *
   * @param cssSelector locator of the element.
   */
  public void deselectAll(String cssSelector) {
    new Select(find(cssSelector)).deselectAll();
  }

  /**
   * Clear all selected entries. This is only valid when the SELECT supports multiple selections.
   *
   * @param by locator of the element.
   */
  public void deselectAll(By by) {
    new Select(find(by)).deselectAll();
  }

  /**
   * Execute the javascript script.
   *
   * @param script The JavaScript to execute
   * @param args   The arguments to the script. May be empty
   * @return Object data (return value of the script).
   */
  public Object evaluateJavascript(String script, Object args) {
    JavascriptExecutor jse = ((JavascriptExecutor) getDriver());
    return jse.executeScript(script, args);
  }

  /**
   * Find the element by specific locator. Will wait until the element is present using explicit
   * timeout.
   *
   * @param by locator of the element.
   * @return {@link T} {@link WebElement} class or subclass
   */
  public T find(By by) {
    return waitUntilPresent(by);
  }

  /**
   * Find the element by cssSelector locator. Will wait until the element is present using explicit
   * timeout.
   *
   * @param cssSelector locator of the element.
   * @return {@link T} {@link WebElement} class or subclass
   */
  public T find(String cssSelector) {
    return find(By.cssSelector(cssSelector));
  }

  /**
   * Finds all of the element by specific locator. Will wait until all of the element is present
   * using explicit timeout.
   *
   * @param by locator of the element.
   * @return List of {@link WebElement} class or subclass
   */
  public List<T> findAll(By by) {
//    return getDriver().findElements(by);
    return waitUntilAllPresent(by);
  }

  /**
   * Finds all of the element by cssSelector locator. Will wait until all of the element is present
   * using explicit timeout.
   *
   * @param cssSelector locator of the element.
   * @return List of {@link WebElement} class or subclass
   */
  public List<T> findAll(String cssSelector) {
//    return findAll(By.cssSelector(cssSelector));
    return waitUntilAllPresent(By.cssSelector(cssSelector));
  }

  /**
   * Fullscreen the current window if it is not already fullscreen.
   */
  public void fullScreen() {
    getDriver().manage().window().fullscreen();
  }

  /**
   * Get the {@link Alert} object. It also will do Switches to the currently active modal dialog for
   * this particular driver instance. You should call
   * {@link BasePageObject#switchToDefaultContent()} after finish performing task to alert.
   *
   * @return {@link Alert} object.
   */
  public Alert getAlert() {
    return getDriver().switchTo().alert();
  }

  /**
   * Get the element attribute value.
   *
   * @param by            locator of the element.
   * @param attributeName Attribute name that want to get.
   * @return value of the element attribute in {@link String}.
   */
  public String getAttribute(By by, String attributeName) {
    return find(by).getAttribute(attributeName);
  }

  /**
   * Get the element attribute value using cssSelector locator
   *
   * @param cssSelector   locator of the element.
   * @param attributeName Attribute name that want to get.
   * @return value of the element attribute in {@link String}.
   */
  public String getAttribute(String cssSelector, String attributeName) {
    return find(cssSelector).getAttribute(attributeName);
  }

  /**
   * Get a string representing the current URL that the browser is looking at.
   *
   * @return current URL {@link String} value.
   */
  public String getCurrentUrl() {
    return getDriver().getCurrentUrl();
  }

  /**
   * Get the WebDriver that currently used.
   *
   * @return {@link WebDriver} Object
   */
  public WebDriver getDriver() {
    for (int counter = 0;
         this.driver.getDrivers().getWebDriver() == null && counter < driverProperties.getWeb()
                 .getMaxDriverInitRetry(); counter++) {
      Webdrivers drivers = this.driver.getDrivers();
      drivers.setWebDriver(webriverCreator.create());
      this.driver.setDrivers(drivers);
    }

    return this.driver.getDrivers().getWebDriver();
  }

  /**
   * Set the WebDriver that currently used.
   *
   * @param driver {@link WebDriver} driver
   */
  @Deprecated
  public void setDriver(WebDriver driver) {
    Webdrivers drivers = new Webdrivers();
    drivers.setWebDriver(driver);
    this.driver.setDrivers(drivers);
  }

  /**
   * Get the explicit timeout.
   *
   * @return explicit timeout in seconds.
   */
  public Integer getExplicitTimeout() {
    return driverProperties.getWeb().getExplicitWait();
  }

  /**
   * Get the element text value using specified locator.
   *
   * @param by locator of the element.
   * @return element text value in {@link String}
   */
  public String getText(By by) {
    return find(by).getText();
  }

  /**
   * Get the element text value using cssSelector locator.
   *
   * @param cssSelector locator of the element.
   * @return element text value in {@link String}
   */
  public String getText(String cssSelector) {
    return find(cssSelector).getText();
  }

  /**
   * Get the title of the current page.
   *
   * @return {@link String} title.
   */
  public String getTitle() {
    return getDriver().getTitle();
  }

  /**
   * Get {@link TouchActions} object.
   *
   * @return {@link TouchActions} object.
   */
  public TouchActions getTouchActions() {
    return new TouchActions(getDriver());
  }

  /**
   * Get List of WebDriver that already started.
   *
   * @return {@link WebDriverPool} Object
   */
  public WebDriverPool getWebdriverPool() {
    return this.driver;
  }

  public Set<String> getWindowHandles() {
    return getDriver().getWindowHandles();
  }

  /**
   * Check whether the element is displayed using specified locator.
   *
   * @param by locator of the element.
   * @return true if the element is displayed. false if the element isn't displayed.
   */
  public Boolean isDisplayed(By by) {
    return find(by).isDisplayed();
  }

  /**
   * Check whether the element is displayed using cssSelector locator.
   *
   * @param cssSelector locator of the element.
   * @return true if the element is displayed. false if the element isn't displayed.
   */
  public Boolean isDisplayed(String cssSelector) {
    return find(cssSelector).isDisplayed();
  }

  /**
   * Check whether the element is available for actions (click, type, etc) using specified locator.
   *
   * @param by locator of the element.
   * @return true if the element is enabled. false if the element is disabled.
   */
  public Boolean isEnabled(By by) {
    return find(by).isEnabled();
  }

  /**
   * Check whether the element is available for actions (click, type, etc) using cssSelector
   * locator.
   *
   * @param cssSelector locator of the element.
   * @return true if the element is enabled. false if the element is disabled.
   */
  public Boolean isEnabled(String cssSelector) {
    return find(cssSelector).isEnabled();
  }

  /**
   * Maximizes the current window if it is not already maximized.
   */
  public void maximize() {
    getDriver().manage().window().maximize();
  }

  /**
   * Open the browser tab then search the inpputed url.
   *
   * @param path String url path
   */
  public void openAt(String path) {
    this.getDriver().get(path);
  }

  /**
   * Get the {@link Select} object using specified locator.
   *
   * @param by locator of the element.
   * @return {@link Select} object.
   */
  public Select select(By by) {
    return new Select(find(by));
  }

  /**
   * Get the {@link Select} object using cssSelector locator.
   *
   * @param cssSelector locator of the element.
   * @return {@link Select} object.
   */
  public Select select(String cssSelector) {
    return new Select(find(cssSelector));
  }

  /**
   * Select the option at the given index. This is done by examining the "index" attribute of an
   * element, and not merely by counting.
   *
   * @param cssSelector locator of the element.
   * @param index       index of the data. start from 0.
   */
  public void selectByIndex(String cssSelector, Integer index) {
    new Select(find(cssSelector)).selectByIndex(index);
  }

  /**
   * Select the option at the given index. This is done by examining the "index" attribute of an
   * element, and not merely by counting.
   *
   * @param by    locator of the element.
   * @param index index of the data. start from 0.
   */
  public void selectByIndex(By by, Integer index) {
    new Select(find(by)).selectByIndex(index);
  }

  /**
   * Select all options that have a value matching the argument. That is, when given "foo" this
   * would select an option like:
   * <p>
   * &lt;option value="foo"&gt;Bar&lt;/option&gt;
   *
   * @param cssSelector locator of the element.
   * @param value       The value to match against
   */
  public void selectByValue(String cssSelector, String value) {
    new Select(find(cssSelector)).selectByValue(value);
  }

  /**
   * Select all options that have a value matching the argument. That is, when given "foo" this
   * would select an option like:
   * <p>
   * &lt;option value="foo"&gt;Bar&lt;/option&gt;
   *
   * @param by    locator of the element.
   * @param value The value to match against
   */
  public void selectByValue(By by, String value) {
    new Select(find(by)).selectByValue(value);
  }

  /**
   * Select all options that display text matching the argument. That is, when given "Bar" this
   * would select an option like:
   * <p>
   * &lt;option value="foo"&gt;Bar&lt;/option&gt;
   *
   * @param by   locator of the element.
   * @param text The text to match against.
   */
  public void selectByVisibleText(By by, String text) {

    new Select(find(by)).selectByVisibleText(text);
  }

  /**
   * Select all options that display text matching the argument. That is, when given "Bar" this
   * would select an option like:
   * <p>
   * &lt;option value="foo"&gt;Bar&lt;/option&gt;
   *
   * @param cssSelector locator of the element.
   * @param text        The text to match against.
   */
  public void selectByVisibleText(String cssSelector, String text) {
    new Select(find(cssSelector)).selectByVisibleText(text);
  }

  /**
   * Set the default explicit timeout. You can also set the default explicit timeout in the
   * properties file. Please refer to this <a
   * href="https://mokapos.atlassian.net/wiki/spaces/MT/pages/43254156/Configuration+Documentation">documentation</a>
   *
   * @param seconds Timeout in seconds.
   */
  public void setDefaultExplicitTimeout(Integer seconds) {
    driverProperties.getWeb().setExplicitWait(seconds);
  }

  /**
   * Set the default implict timeout. You can also set the default implicit timeout in the
   * properties file. Please refer to this <a
   * href="https://mokapos.atlassian.net/wiki/spaces/MT/pages/43254156/Configuration+Documentation">documentation</a>
   *
   * @param seconds Timeout in seconds.
   */
  public void setDefaultImplicitTimeout(Integer seconds) {
    driverProperties.getWeb().setImplicitWait(seconds);
    getDriver().manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
  }

  /**
   * Sets the amount of time to wait for a page load to complete before throwing an error. If the
   * timeout is negative, page loads can be indefinite.
   *
   * @param seconds Timeout in seconds.
   */
  public void setPageLoadTimeout(Integer seconds) {
    getDriver().manage().timeouts().pageLoadTimeout(seconds, TimeUnit.SECONDS);
  }

  /**
   * Set the size of the current window. This will change the outer window dimension, not just the
   * view port, synonymous to window.resizeTo() in JS.
   *
   * @param width  width of the window.
   * @param height height of the window.
   */
  public void setScreenSize(Integer width, Integer height) {
    Dimension d = new Dimension(width, height);
    getDriver().manage().window().setSize(d);
  }

  /**
   * Sets the amount of time to wait for an asynchronous script to finish execution before throwing
   * an error. If the timeout is negative, then the script will be allowed to run indefinitely.
   *
   * @param seconds Timeout in seconds.
   */
  public void setScriptTimeout(Integer seconds) {
    getDriver().manage().timeouts().setScriptTimeout(seconds, TimeUnit.SECONDS);
  }

  /**
   * Switch the focus of future commands for this driver to the window with the given name/handle.
   *
   * @param name The name of the window or the handle as returned by
   *             {@link WebDriver#getWindowHandle()}
   */
  public void switchTo(String name) {
    getDriver().switchTo().window(name);
  }

  /**
   * Selects either the first frame on the page, or the main document when a page contains iframes.
   */
  public void switchToDefaultContent() {
    getDriver().switchTo().defaultContent();
  }

  /**
   * Perform type action for specific element. Will wait the element until visible before performing
   * the action using explicit timeout.
   *
   * @param by   locator of the element.
   * @param text text that want to be input.
   */
  public void typeOn(By by, String text) {
    T element = waitUntilVisible(by);
    element.clear();
    element.sendKeys(text);
  }

  /**
   * Perform wait for specified time in seconds.
   *
   * @param seconds wait duration in seconds.
   */
  public void waitABit(Integer seconds) {
    try {
      Thread.sleep(Duration.ofSeconds(seconds).toMillis());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Perform wait for specified time unit.
   *
   * @param waitTime wait value duration.
   * @param unit     time unit.
   */
  public void waitABit(Integer waitTime, TemporalUnit unit) {
    try {
      Thread.sleep(Duration.of(waitTime, unit).toMillis());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Repeatedly applies this instance's input value to the given function until one of the following
   * occurs:
   * <ol>
   * <li>the function returns neither null nor false</li>
   * <li>the function throws an unignored exception</li>
   * <li>the timeout expires</li>
   * <li>the current thread is interrupted</li>
   * </ol>
   *
   * @param conditions parameter to pass to the {@link ExpectedCondition}. Should be
   *                   ExpectedCondition that have generic class list of {@link WebElement}
   * @param timeout    timeout in seconds.
   * @return List of {@link WebElement} Object or the subclass.
   * @throws TimeoutException If the timeout expires.
   */
  public List<T> waitAllUntil(ExpectedCondition<List<WebElement>> conditions, Integer timeout) {
    WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
    return (List<T>) wait.until(conditions);
  }

  /**
   * Repeatedly applies this instance's input value to the given function until one of the following
   * occurs:
   * <ol>
   * <li>the function returns neither null nor false</li>
   * <li>the function throws an unignored exception</li>
   * <li>the timeout expires</li>
   * <li>the current thread is interrupted</li>
   * </ol>
   * Using explicit timeout.
   *
   * @param conditions parameter to pass to the {@link ExpectedCondition}. Should be
   *                   ExpectedCondition that have generic class list of {@link WebElement}
   * @return List of {@link WebElement} Object or the subclass.
   * @throws TimeoutException If the timeout expires.
   */
  public List<T> waitAllUntil(ExpectedCondition<List<WebElement>> conditions) {
    return waitAllUntil(conditions, getExplicitTimeout());
  }

  /**
   * Repeatedly applies this instance's input value to the given function until one of the following
   * occurs:
   * <ol>
   * <li>the function returns neither null nor false</li>
   * <li>the function throws an unignored exception</li>
   * <li>the timeout expires</li>
   * <li>the current thread is interrupted</li>
   * </ol>
   *
   * @param conditions parameter to pass to the {@link ExpectedCondition}. Should be
   *                   ExpectedCondition that have generic class {@link WebElement}
   * @param timeout    timeout in seconds.
   * @return {@link WebElement} Object or the subclass.
   * @throws TimeoutException If the timeout expires.
   */
  public T waitUntil(ExpectedCondition<WebElement> conditions, Integer timeout) {
    WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
    return (T) wait.until(conditions);
  }

  /**
   * Repeatedly applies this instance's input value to the given function until one of the following
   * occurs:
   * <ol>
   * <li>the function returns neither null nor false</li>
   * <li>the function throws an unignored exception</li>
   * <li>the timeout expires</li>
   * <li>the current thread is interrupted</li>
   * </ol>
   * Using explicit timeout.
   *
   * @param conditions parameter to pass to the {@link ExpectedCondition}. Should be
   *                   ExpectedCondition that have generic class {@link WebElement}
   * @return {@link WebElement} Object or the subclass.
   * @throws TimeoutException If the timeout expires.
   */
  public T waitUntil(ExpectedCondition<WebElement> conditions) {
    return waitUntil(conditions, getExplicitTimeout());
  }

  /**
   * Wait all of the element with same locator present. Using explicit timeout.
   *
   * @param by locator of the element.
   * @return List of {@link WebElement} class or subclass.
   */
  public List<T> waitUntilAllPresent(By by) {
    return waitAllUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
  }

  /**
   * Wait all of the element with same locator present.
   *
   * @param by               locator of the element.
   * @param timeoutInSeconds Timeout in seconds.
   * @return List of {@link WebElement} class or subclass.
   */
  public List<T> waitUntilAllPresent(By by, Integer timeoutInSeconds) {
    return waitAllUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(by), timeoutInSeconds);
  }

  /**
   * Wait the element until it clickable. Using explicit timeout.
   *
   * @param by locator of the element.
   * @return {@link T} {@link WebElement} class or subclass.
   */
  public T waitUntilClickable(By by) {
    return waitUntil(ExpectedConditions.elementToBeClickable(by), getExplicitTimeout());
  }

  /**
   * Wait the element until it clickable.
   *
   * @param by      locator of the element.
   * @param timeout timeout in seconds.
   * @return {@link T} {@link WebElement} class or subclass.
   */
  public T waitUntilClickable(By by, Integer timeout) {
    return waitUntil(ExpectedConditions.elementToBeClickable(by), timeout);
  }

  /**
   * Wait the element until disappear from screen.
   *
   * @param by      locator of the element.
   * @param timeout Timeout in seconds.
   * @return {@link Boolean} true if the element is already disappear or false if the element still
   * visible.
   */
  public Boolean waitUntilInvisible(By by, Integer timeout) {
    WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
    return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
  }

  /**
   * Wait the element until disappear from screen. Using explicit timeout.
   *
   * @param by locator of the element.
   * @return {@link Boolean} true if the element is already disappear or false if the element still
   * visible.
   */
  public Boolean waitUntilInvisible(By by) {
    return waitUntilInvisible(by, getExplicitTimeout());
  }

  /**
   * Wait the element until it present. Using explicit timeout.
   *
   * @param by locator of the element.
   * @return {@link T} {@link WebElement} class or subclass.
   */
  public T waitUntilPresent(By by) {
    return waitUntil(ExpectedConditions.presenceOfElementLocated(by), getExplicitTimeout());
  }

  /**
   * Wait the element until it present.
   *
   * @param by               locator of the element.
   * @param timeoutInSeconds timeout in seconds.
   * @return {@link T} {@link WebElement} class or subclass.
   */
  public T waitUntilPresent(By by, Integer timeoutInSeconds) {
    return waitUntil(ExpectedConditions.presenceOfElementLocated(by), timeoutInSeconds);
  }

  /**
   * Wait the element until it visible. Using explicit timeout.
   *
   * @param by locator of the element.
   * @return {@link T} {@link WebElement} class or subclass.
   */
  public T waitUntilVisible(By by) {
    return waitUntil(ExpectedConditions.visibilityOfElementLocated(by), getExplicitTimeout());
  }

  /**
   * Wait the element until it visible.
   *
   * @param by      locator of the element.
   * @param timeout timeout in seconds.
   * @return {@link T} {@link WebElement} class or subclass.
   */
  public T waitUntilVisible(By by, Integer timeout) {
    return waitUntil(ExpectedConditions.visibilityOfElementLocated(by), timeout);
  }

  /**
   * Get the {@link Actions} builder class for performing complex user gestures.
   *
   * @return {@link Actions} builder class.
   */
  public Actions withActions() { return new Actions(getDriver()); }

  /**
   * Hover to element page
   * @param By locator of the element
   */
  public void hoverTo(By by) {
    WebElement ele = getDriver().findElement(by);
    withActions().moveToElement(ele).perform();
  }

  public boolean isloadComplete() {
    return new WebDriverWait(getDriver(), 15).until((ExpectedCondition<Boolean>) wd ->
            ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
  }
}
