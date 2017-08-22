#-*-coding:utf-8-*- #编码声明，不要忘记！
from selenium import webdriver

class Downloader(object):

    def getPage(self, url):
        path = "D:\\code\\phantomjs-2.1.1-windows\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe"
        driver = webdriver.PhantomJS(path)
        page = driver.get(url)
        return driver.page_source