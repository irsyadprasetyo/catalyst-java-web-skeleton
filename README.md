# Framework UI Web Automation (Java)

[Requirements](#Requirements)
- [Install Java](#java)
- [Install Gradle](#gradle)
- [Install IntelliJ](#intelliJ)
- [Install Chromedriver](#chromedriver)


<a name="Requirements"></a>
# Requirements

What you need to make this project works is `Java`, `Gradle` , `IntelliJ`, `Chromedriver`

---
<a name="java"></a>
### Install Java

You can download it [here](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html) . Make sure you install `Java 1.8 or above`.

---

<a name="gradle"></a>
### Install Gradle

`Gradle` (version 5.6 or above) are easier to install if you had `homebrew` in your mac. If you never installed `homebrew` before, you could look it up [here](https://brew.sh/). To install it using `homebrew` run this command.

```
brew install gradle
```

It will install your `gradle` globally on your system.

<a name="intelliJ"></a>
### Install IntelliJ


You can follow instruction [here](https://www.jetbrains.com/help/idea/installation-guide.html)

### Install JDK (Java Development Kit)
- Open IntelliJ IDEA
- Go to Project Structure -> Project
- on SDK, click dropdown SDK
- Add SDK
   - For MacOS-arm: choose version: 11, vendor: Amazon Coretto
   - For MacOS-intel: choose version: 11, vendor: Oracle (Open JDK)
   - Make sure installation folder "~/Library/Java/JavaVirtualMachines/"

<a name="chromedriver"></a>
### Install Chromedriver

#### Linux
1. sudo apt-get install unzip
2. wget -N http://chromedriver.storage.googleapis.com/2.10/chromedriver_linux64.zip -P ~/Downloads
3. unzip ~/Downloads/chromedriver_linux64.zip -d ~/Downloads
4. chmod +x ~/Downloads/chromedriver
5. sudo mv -f ~/Downloads/chromedriver /usr/local/share/chromedriver
6. Change the directory to /usr/bin/chromedriver
   - sudo ln -s /usr/local/share/chromedriver /usr/local/bin/chromedriver
   - sudo ln -s /usr/local/share/chromedriver /usr/bin/chromedriver

#### MacOS
You can follow instruction [here](https://www.kenst.com/installing-chromedriver-on-mac-osx/#:~:text=The%20easiest%20way%20to%20install,seeing%20it%20returns%20a%20version.)
