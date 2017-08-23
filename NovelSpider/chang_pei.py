# coding=utf-8
import page_downloader
import page_parser
import page_outputer
from bs4 import BeautifulSoup
import re
from selenium import webdriver
import time


class ChangPei(object):

    def __init__(self, urls, name, password):
        self.name = name
        self.password = password
        self.downloader = page_downloader.Downloader()
        self.parser = page_parser.Parser()
        self.outputer = page_outputer.Outputer()
        self.urls = urls
        self.path = "D:\\code\\phantomjs-2.1.1-windows\\phantomjs-2.1.1-windows\\bin\\phantomjs"
        #google = "C:\\Users\\asus\\Downloads\\chromedriver_win32 (1)\\chromedriver"
        print("初始化成功")

    def get_urls(self):
        text = self.soup.select(".pg")
        count = re.search("共 (.*?) 页", text.__str__())
        if (count == None):
            return 1
        count = count.group(1)
        print("获取到%s页" % count)
        return int(count)

    def get_context(self, url):
        page = self.get_page_by_cookie(url)
        soup = BeautifulSoup(page, "lxml")
        text = soup.select(".t_f")
        self.name = soup.select("#thread_subject")[0].text
        data = []
        for paragraph in text:
            t = paragraph.text
            print(t)
            temp = "    "
            t = temp + t
            if len(t) > 500:
                result = re.sub("本帖最后由(.*?)编辑", "", t)
                result = re.sub("以下内容需要积分高于(.*)才可浏览", "", result)
                data.append(result)
        return data

    def login(self, url):
        driver = webdriver.PhantomJS(self.path)
        driver.get(url)
        username = driver.find_element_by_name("username")
        username.clear()
        username.send_keys(self.name)
        password = driver.find_element_by_name("password")
        password.clear()
        password.send_keys(self.password)
        driver.find_element_by_css_selector("button[class='pn2 vm']").click()
        time.sleep(2)
        cookies = driver.get_cookies()
        file = open("chang_pei_cookie.txt", 'w')
        for cookie in cookies:
            file.write(str(cookie))
            file.write("!\n")
        file.close()

    def get_page_by_cookie(self, url):
        driver = webdriver.PhantomJS(self.path)
        file = open("chang_pei_cookie.txt", encoding="gbk")
        cookies_str = file.read()
        file.close()
        try:
            for cookie_str in cookies_str.split("!"):
                cookie = eval(cookie_str)
                driver.add_cookie({
                            'domain': '.allcp.net',  # note the dot at the beginning
                            'name': cookie['name'],
                            'value': cookie['value'],
                            'path': '/',
                })
        except Exception:
            print("")


        driver.get(url)
        return driver.page_source

    def check_login(self):
        page = self.get_page_by_cookie("http://allcp.net/forum.php")
        if page == None:
            return False
        soup = BeautifulSoup(page, "lxml")
        if soup.select(".vms")[0].text.__contains__("欢迎回来"):
            return True
        return False

    def start(self):
        if self.check_login() == False:
            print("cookie失效")
            self.login("http://allcp.net/forum.php")
            print("正在尝试重新登录")
            if self.check_login() == False:
                print("无法登录")
                return
        print("登录成功")

        for url in self.urls:
            self.url = url
            self.page = self.downloader.getPage(self.url)
            self.soup = BeautifulSoup(self.page, "lxml")
            t_urls = self.get_urls()
            t_url = self.url.split("page=1")
            novel = []
            for num in range(1, t_urls + 1):
                novel.append(self.get_context(t_url[0] + 'page=' + str(num) + t_url[1]))
            self.outputer.chang_pei_outer(self.name, novel)

ChangPei(["http://allcp.net/forum.php?mod=viewthread&tid=37755&page=1&auth"
          "orid=159685", "http://allcp.net/forum.php?mod=viewthread&tid=23385&page=1&authorid=122629"], "", "").start()
