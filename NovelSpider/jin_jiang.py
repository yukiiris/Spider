import page_downloader
import page_parser
import  page_outputer
from bs4 import BeautifulSoup
import re
from selenium import webdriver
import time
from selenium.webdriver.support.ui import WebDriverWait

class JinJiang(object):

    def __init__(self, urls):
        self.path = "D:\\code\\phantomjs-2.1.1-windows\\phantomjs-2.1.1-windows\\bin\\phantomjs"
        self.phantomjs = webdriver.PhantomJS(self.path)
        self.downloader = page_downloader.Downloader()
        self.parser = page_parser.Parser()
        self.outputer = page_outputer.Outputer()
        self.urls = urls
        self.google = "C:\\Users\\asus\\Downloads\\chromedriver_win32 (1)\\chromedriver"
        self.driver = webdriver.Chrome(self.google)
        self.is_login = False
        print("初始化成功")

    def on_home(self):
        author = self.soup.find('span', attrs={'itemprop' : 'author'}).text
        name = self.soup.find('h1', attrs={'itemprop' : 'name'}).text
        print("首页信息获取完毕")
        print(name + author)
        return {'name' : name, 'author' : author}

    def on_chapter(self, url):
        if url[0].__contains__("vip"):
            url = url[0]
            vip = True
            if self.is_login is False:
                self.login()
                self.is_login = True
            page = self.get_page_by_cookie(url)
        else:
            vip = False
            self.phantomjs.get(url)
            WebDriverWait(self.phantomjs, 10).until(lambda driver: driver.find_element_by_class_name(".noveltext"))
            page = self.phantomjs.page_source
            print(page)
        soup = BeautifulSoup(page, "lxml")
        text = soup.select(".noveltext")[0]
        print("找到noveltext")
        unvip_regex = "<div style=\"clear:both;\"></div>([\s\S\D]*?)<div"
        vip_regex = "<div id=\"show\"></div>([\s\S\D]*?)<di"
        if vip is True:
            result = re.search(vip_regex, text.__str__()).group(1)
        else:
            result = re.search(unvip_regex, text.__str__()).group(1)
        print("找到result")
        result = re.sub(r"<font [\s\S\D]*?</font><br/>", "\n", result.__str__()).__str__()
        result = re.sub(r"<br/>", "\n", result).__str__()
        name = text.select("h2")[0].text
        chapter = re.search("chapterid=(.*)", url).group(1)
        result = result.strip()
        temp = "    "
        temp = temp + result
        result = temp
        print("正在获取第%s章" % chapter)
        print("%s" % result)
        return {'context' : result, 'chapter_name': name, 'chapter_id' : chapter}

    def get_urls(self):
        elements = self.soup.find_all('a', attrs={'itemprop' : 'url'})
        urls = []
        for e in elements:
            if 'href' in e.attrs:
                urls.append(e.attrs['href'])
            if 'rel' in e.attrs:
                urls.append(e.attrs['rel'])
        print("章节地址获取完毕")
        return urls

    def login(self):
        cap = webdriver.DesiredCapabilities.PHANTOMJS
        cap[
            "phantomjs.page.settings.userAgent"] = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:36.0) Gecko/20100101 Firefox/36.0 WebKit",

        cap[
            "phantomjs.page.customHeaders.User-Agent"] = 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:36.0) Gecko/20100101 Firefox/36.0 WebKit',
        path = "D:\\code\\phantomjs-2.1.1-windows\\phantomjs-2.1.1-windows\\bin\\phantomjs"
        google = "C:\\Users\\asus\\Downloads\\chromedriver_win32 (1)\\chromedriver"

        self.driver.get("http://www.jjwxc.net/login.php")
        self.driver.find_element_by_id("loginname").clear()
        self.driver.find_element_by_id("loginpassword").clear()
        self.driver.find_element_by_id("loginname").send_keys("25852469@qq.com")
        self.driver.find_element_by_id("loginpassword").send_keys("jj998877123")
        time.sleep(10)
        self.driver.find_element_by_class_name("loginSubmit").click()

    def get_page_by_cookie(self, url):
        self.driver.get(url)
        return self.driver.page_source

    def check_login(self):
        page = self.get_page_by_cookie("http://www.jjwxc.net/")
        if page == None:
            return False
        soup = BeautifulSoup(page, "lxml")
        if soup.select("#t_user_info") != None:
            return True
        return False

    def start(self):
        for url in self.urls:
            self.url = url
            self.phantomjs.get(url)
            self.page = self.phantomjs.page_source
            self.soup = BeautifulSoup(self.page, "lxml")
            print("page处理完成")
            attributes = self.on_home()
            t_urls = self.get_urls()
            print(t_urls)
            novel = []
            for t_url in t_urls:
                print("......")
                novel.append(self.on_chapter(t_url))
            self.outputer.jin_jiang_outer(attributes, novel)


JinJiang(["http://www.jjwxc.net/onebook.php?novelid=2779185", "http://www.jjwxc.net/onebook.php?novelid=3185717"]).start()