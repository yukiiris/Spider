import page_downloader
import page_parser
import  page_outputer
from bs4 import BeautifulSoup
import time
from selenium import webdriver
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.common.keys import Keys

driver = webdriver.Chrome("C:\\Users\\asus\\Downloads\\chromedriver_win32 (1)\\chromedriver")
driver.set_script_timeout(2)
driver.set_page_load_timeout(2)
def get_chapter(url):
    isLoad = False

    while isLoad is False:
        print(1)
        try:
            print(2)
            driver.get(url)
            # self.driver.execute_script("window.stop()")
            # WebDriverWait(self.driver, 5, 0.5).until(
            #     lambda x: x.find_element_by_class_name("noveltext").is_displayed())
        except:
            try:
                driver.refresh()
            except:
                pass
        else:
            isLoad = True

for url in ['http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=1', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=2', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=3', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=4', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=5', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=6', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=7', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=8', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=9', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=10', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=11', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=12', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=13', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=14', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=15', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=16', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=17', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=18', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=19', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=20', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=21', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=22', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=23', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=24', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=25', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=26', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=27', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=28', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=29', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=30', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=31', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=32', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=33', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=34', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=35', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=36', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=37', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=38', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=39', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=40', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=41', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=42', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=43', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=44']:
    get_chapter(url)