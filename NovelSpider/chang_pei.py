import page_downloader
import page_parser
import  page_outputer
from bs4 import BeautifulSoup
import re

class ChangPei(object):

    def __init__(self, url):
        self.downloader = page_downloader.Downloader()
        self.parser = page_parser.Parser()
        self.outputer = page_outputer.Outputer();
        self.url = url
        self.page = self.downloader.getPage(url)
        self.soup = BeautifulSoup(self.page, "lxml")
        print("初始化成功")

    def get_urls(self):
        text = self.soup.select(".pg")
        count = re.search("共 (.*?) 页", text.__str__())
        if (count == None):
            return 1
        count = count.group(1)
        print(count)
        return int(count)

    def get_context(self, url):
        page = self.downloader.getPage(url)
        soup = BeautifulSoup(page, "lxml")
        text = soup.select(".t_f")
        self.name = soup.select("#thread_subject")[0].text
        data = []
        for paragraph in text:
            print(paragraph.text)
            if (len(paragraph.text) > 500):
                re.sub("本帖最后由(.*)编辑", "", paragraph.text)
                data.append(paragraph.text)
        return data

    def start(self):
        urls = self.get_urls()
        url = self.url.split("page=1")
        novel = []
        for num in range(1, urls + 1):
            novel.append(self.get_context(url[0] + 'page=' + str(num) + url[1]))
        self.outputer.chang_pei_outer(self.name, novel)


ChangPei("http://allcp.net/forum.php?mod=viewthread&tid=26712&page=1&authorid=156619").start()

