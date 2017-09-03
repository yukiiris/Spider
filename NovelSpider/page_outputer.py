
class Outputer(object):

    def jin_jiang_outer(self, attributes, novel):
        out = open(attributes.get('name') + ".txt", "w", encoding="utf-8")
        for d in novel:
            out.write("第%s章   " % d.get('chapter_id'))
            out.write(d.get('chapter_name'))
            out.write('\n')
            out.write(d.get('context'))
            print("正在写入第%s章" % d.get('chapter_id'))
        print("完成")
        out.close()

    def chang_pei_outer(self,name, novel):
        out = open(name + ".txt", "w", encoding="utf-8")
        for d in novel:
            for e in d:
                out.write(e)
                print("正在写入")
        print("完成")
        out.close()
