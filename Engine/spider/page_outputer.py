
class Outputer(object):

    def out(self, data):
        out = open("test.txt", "w", encoding="utf-8")
        for d in data:
            out.write(data)
        out.close();