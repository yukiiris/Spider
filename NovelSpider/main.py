import page_downloader
import page_parser
import page_outputer
from bs4 import BeautifulSoup


class Starter(object):

    def __init__(self):
        self.downloader = page_downloader.Downloader()
        self.parser = page_parser.Parser()
        self.outputer = page_outputer.Outputer()

    def jin_jiang(self):
        page = self.downloader.getPage("http://www.jjwxc.net/onebook.php?novelid=602476")
        soup = BeautifulSoup(page, "lxml")

        name = soup.find('h1', attrs={'itemprop' : 'name'})

        # text = soup.select(r".noveltext")[0]
        # result = re.search(r"<div style=\"clear:both;\"></div>([\s\S\D]*?)<div", text.__str__()).group(1)
        # result = re.sub(r"<font [\s\S\D]*?</font><br/>", "\n", result.__str__()).__str__()
        # result = re.sub(r"<br/>", "\n", result).__str__()
        # print("%s" % result)
        #
        # data = []
        # data.append(result)
        #self.outputer.out(data,name)

    def change_pei(self):
        page = self.downloader.getPage("http://allcp.net/forum.php?mod=viewthread&tid=36976&page=1&authorid=257740")
        soup = BeautifulSoup(page, "lxml")
        text = soup.select(".t_f")
        name = soup.select("#thread_subject")[0].text
        data = []
        for paragraph in text:
            print(paragraph.text)
            data.append(paragraph.text)
        self.outputer.out(data, name)
Starter().jin_jiang()