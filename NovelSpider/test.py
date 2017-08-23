import page_downloader
import page_parser
import  page_outputer
from bs4 import BeautifulSoup
import re
from selenium import webdriver
import time

driver = webdriver.Chrome("C:\\Users\\asus\\Downloads\\chromedriver_win32 (1)\\chromedriver")
driver.set_script_timeout(5)
driver.set_page_load_timeout(5)
try:
    driver.get("http://www.jjwxc.net/onebook.php?novelid=3185717&chapterid=1")
except:
    print(driver.page_source)