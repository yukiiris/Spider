#-*-coding:utf-8-*- #编码声明，不要忘记！
from bs4 import BeautifulSoup
import re


class Parser(object):


    def get_url(self, regex, soup):
        result = []
        urls = soup.find_all('a', href = re.compile(regex))

        for d in urls:
            result.append(d)
        return result
