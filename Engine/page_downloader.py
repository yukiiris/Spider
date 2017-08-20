#-*-coding:utf-8-*- #编码声明，不要忘记！
import urllib.request
from selenium import webdriver

def getPage(url):
    path = "D:\\code\\phantomjs-2.1.1-windows\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe"
    driver = webdriver.PhantomJS(path)
    page = driver.get(url)
    return page

html = getPage("https://baidu.com")

print (html.decode("utf-8"))