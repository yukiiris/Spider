import page_downloader
import page_parser
import  page_outputer
from bs4 import BeautifulSoup
import re
from selenium import webdriver
import time
import requests


class JinJiang(object):

    def __init__(self, url):
        self.downloader = page_downloader.Downloader()
        self.parser = page_parser.Parser()
        self.outputer = page_outputer.Outputer();
        self.url = url
        self.page = self.downloader.getPage(url)
        self.soup = BeautifulSoup(self.page, "lxml")
        print("初始化成功")

    def on_home(self):
        author = self.soup.find('span', attrs={'itemprop' : 'author'}).text
        name = self.soup.find('h1', attrs={'itemprop' : 'name'}).text
        print("首页信息获取完毕")
        print(name + author)
        return {'name' : name, 'author' : author}

    def on_chapter(self, url):
        page = self.downloader.getPage(url)
        soup = BeautifulSoup(page, "lxml")
        text = soup.select(".noveltext")[0]
        result = re.search(r"<div style=\"clear:both;\"></div>([\s\S\D]*?)<div", text.__str__()).group(1)
        result = re.sub(r"<font [\s\S\D]*?</font><br/>", "\n", result.__str__()).__str__()
        result = re.sub(r"<br/>", "\n", result).__str__()
        name = text.select("h2")[0].text
        chapter = re.search("chapterid=(.*)", url).group(1)
        result = result.strip()
        temp = "    "
        temp = temp + result
        result = temp
        print("%s" % result)
        print("正在获取第%s章" % chapter)
        return {'context' : result, 'chapter_name': name, 'chapter_id' : chapter}

    def get_urls(self):
        elements = self.soup.find_all('a', attrs={'itemprop' : 'url'})
        urls = []
        for e in elements:
            urls.append(e.attrs['href'])
        return urls

    def start(self):
        attributes = self.on_home()
        urls = self.get_urls()
        novel = []
        for url in urls:
            novel.append(self.on_chapter(url))
        self.outputer.jin_jiang_outer(attributes, novel)

def login(url):
    path = "D:\\code\\phantomjs-2.1.1-windows\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe"
    driver = webdriver.PhantomJS(path)
    driver.get(url)

    driver.find_element_by_id("loginname").clear()
    driver.find_element_by_id("loginpassword").clear()
    driver.find_element_by_id("loginname").send_keys("25852469@qq.com")
    driver.find_element_by_id("loginpassword").send_keys("jj998877123")

    driver.find_element_by_name("submit").click()

    cookies = driver.get_cookies()
    #cookies = "; ".join([item["name"] + "=" + item["value"] + "\n" for item in driver.get_cookies()])
    for cookie in cookies:
        driver.add_cookie({
            'domain': '.jjwxc.net',  # note the dot at the beginning
            'name': cookie['name'],
            'value': cookie['value'],
            'path': '/',
            'expires': None
        })
    driver.get("http://my.jjwxc.net/onebook_vip.php?novelid=3183162&chapterid=43")
    print(driver.page_source)


login("http://my.jjwxc.net/login.php")
