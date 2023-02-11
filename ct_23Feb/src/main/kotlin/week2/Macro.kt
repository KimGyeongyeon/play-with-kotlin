package week2

import Solution
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.chrome.ChromeDriver

class Macro : Solution {
    private val DEVIEW_URL = "https://deview.kr/2023"
    private val driver = ChromeDriver().also { it.get(DEVIEW_URL) }
    override fun start() {
        // 가장 중요한 참가신청 버튼을 지정할수가 없다.. 계획을 철회하자
    }

    fun login() {
        //val loginElement = driver.findElement(By.ByXPath, "//div[@class='leftLoginBox']/iframe[@title='login']")
        /* 2. 로그인 페이지 접속 */
        val loginBtn = driver.findElement(By.xpath("//a[@class='btn_login']"))
        loginBtn.sendKeys(Keys.ENTER)

        /* 3. 네이버 로그인 */
        val naverLoginBtn = driver.findElement(By.xpath("//a[@class='btn circle-active']"))
        naverLoginBtn.sendKeys(Keys.ENTER)

        driver.windowHandles.forEach { println(it) }
        val loginPopUp = driver.windowHandles.last()
        driver.switchTo().window(loginPopUp)

        val idInput = driver.findElement(By.xpath("//div[@class='icon_cell']"))
            .also { it.sendKeys("kgy556") }

        val pwInput = driver.findElement(By.xpath("//input[@name='pw']"))
            .also { it.sendKeys("naver556^^") }

        val submitBtn = driver.findElement(By.id("log.login"))
            .also { it.sendKeys(Keys.ENTER) }

        // println(loginBtn.toString())
    }

    fun applyTo() {
        driver.findElement(By.xpath("//"))
    }
}