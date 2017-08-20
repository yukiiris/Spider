# -*- coding: utf-8 -*-
import subprocess

def get_html(url):
    cmd = 'D:\\code\\phantomjs-2.1.1-windows\\phantomjs-2.1.1-windows\\bin\\phantomjs C:\\Users\\asus\\Documents\\GitHub\\Spider\\Engine\\downloader.js http://m.dmzj.com/search/yi.html?'
    stdout, stderr = subprocess.Popen(cmd, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE).communicate()
    print (stderr)
    print (stdout.decode("utf-8"))

if __name__ == '__main__':
    url = 'www.baidu.com'
    get_html(url)

