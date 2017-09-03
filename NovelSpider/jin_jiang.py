import page_downloader
import page_parser
import  page_outputer
from bs4 import BeautifulSoup
import re
from selenium import webdriver


class JinJiang(object):

    def __init__(self, urls):
        self.path = "D:\\code\\phantomjs-2.1.1-windows\\phantomjs-2.1.1-windows\\bin\\phantomjs"
        self.opera = "D:\\code\\geckodriver.exe"
        self.phantomjs = webdriver.PhantomJS(self.path)
        self.downloader = page_downloader.Downloader()
        self.parser = page_parser.Parser()
        self.outputer = page_outputer.Outputer()
        self.urls = urls
        self.google = "C:\\Users\\asus\\Downloads\\chromedriver_win32 (1)\\chromedriver"
        self.driver = webdriver.Firefox(executable_path=self.opera)
        self.login()
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
        else:
            vip = False
            print(url)

        self.get_chapter(url)
        page = self.driver.page_source
        soup = BeautifulSoup(page, "lxml")
        text = soup.select(".noveltext")[0]
        unvip_regex = "<div style=\"clear:both;\"></div>([\s\S\D]*?)<div"
        vip_regex = "<div id=\"show\"></div>([\s\S\D]*?)<di"
        if vip is True:
            result = re.search(vip_regex, text.__str__()).group(1)
        else:
            result = re.search(unvip_regex, text.__str__()).group(1)
        result = re.sub(r"<font [\s\S\D]*?</font><br/>", "\n", result.__str__()).__str__()
        result = re.sub(r"<br/>", "\n", result).__str__()
        result = re.sub("@无限好文，尽在晋江文学城", "", result).__str__()
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
                if e.attrs['rel'][0].__contains__("http://my.jjwxc.net/backend/"):
                    continue
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

        self.get("http://www.jjwxc.net/login.php")
        self.driver.find_element_by_id("loginname").clear()
        self.driver.find_element_by_id("loginpassword").clear()
        self.driver.find_element_by_id("loginname").send_keys("")
        self.driver.find_element_by_id("loginpassword").send_keys("")
        # if self.driver.find_element_by_id("login_auth_num") is not None:
        #     time.sleep(10)
        # else:
        #     time.sleep(1)
        self.driver.find_element_by_class_name("loginSubmit").click()

    def get_page_by_cookie(self, url):
        self.get(url)
        return self.driver.page_source

    def check_login(self):
        page = self.get_page_by_cookie("http://www.jjwxc.net/")
        if page == None:
            return False
        soup = BeautifulSoup(page, "lxml")
        if soup.select("#t_user_info") != None:
            return True
        return False

    def get(self, url):
        isLoad = False
        while isLoad is False:
            try:
                self.driver.get(url)
            except:
                pass
            else:
                isLoad = True

    def get_chapter(self, url):
        isLoad = False
        while isLoad is False:
            try:
                self.driver.get(url)
            except:
                try:
                    if self.driver.find_element_by_id("favorite_3") is not None:
                        isLoad = True
                except:
                    pass
            else:
                isLoad = True



    def start(self):
        self.driver.set_page_load_timeout(8)
        self.driver.set_script_timeout(8)
        for url in self.urls:
            self.url = url
            self.get(url)
            self.page = self.driver.page_source
            self.soup = BeautifulSoup(self.page, "lxml")
            print("page处理完成")
            attributes = self.on_home()
            t_urls = self.get_urls()
            print(t_urls)
            novel = []
            self.driver.set_page_load_timeout(3)
            self.driver.set_script_timeout(3)
            for t_url in t_urls:
                novel.append(self.on_chapter(t_url))
            self.outputer.jin_jiang_outer(attributes, novel)
        self.driver.quit()


# for url in ['http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=1', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=2', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=3', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=4', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=5', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=6', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=7', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=8', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=9', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=10', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=11', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=12', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=13', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=14', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=15', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=16', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=17', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=18', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=19', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=20', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=21', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=22', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=23', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=24', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=25', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=26', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=27', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=28', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=29', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=30', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=31', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=32', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=33', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=34', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=35', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=36', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=37', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=38', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=39', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=40', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=41', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=42', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=43', 'http://www.jjwxc.net/onebook.php?novelid=2696632&chapterid=44']:
#     get_chapter(url)
JinJiang(["http://www.jjwxc.net/onebook.php?novelid=3047399"]).start()