#-*-coding:utf-8-*- #编码声明，不要忘记！
from bs4 import BeautifulSoup
import re


class Parser(object):

    def get_data(self, regex, soup):
        result = []
        data = soup.find_all(re.compile(regex))

        for d in data:
            result.append(d)
        return result;

    def get_url(self, regex, soup):
        result = []
        data = soup.find_all(re.compile(regex))

        for d in data:
            result.append(d)
        return result;
