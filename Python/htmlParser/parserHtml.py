import sys
import re


class HtmlParser():
    def __init__(self):
        self.specified_users = []
        self.html_table = ""

    @staticmethod
    def read_specified_users():
        if len(sys.argv) > 1:
            with open(sys.argv[1], 'r', encoding='utf-8') as file:
                specified_users = file.read().splitlines()
                return specified_users

    @staticmethod
    def read_html_table():
        content = sys.stdin.readlines()
        for line in content:
            table_body = re.match(r'<tbody><tr class="headerrow">.*</tr>', line)
            if table_body:
                html_table = table_body.group()
                return html_table

    # groups: 1. nick, 2. name, 3. not extracted tasks points, 4. score
    @staticmethod
    def load_info_in_groups(html_table):
        pattern = r'<tr class="problemrow".*?<a.*?([0-9A-Za-z_]*?)">(.*?)(</a>.*?)' \
                  r'(\d{1,}.\d\d)</td></tr>'
        names_scores = re.findall(pattern, html_table)
        return names_scores

    @staticmethod
    def show_info(names_scores, specified_users):
        result = ""
        index = 0
        while index < len(names_scores):
            (nick, name, categories, score) = names_scores[index]

            if specified_users:
                if nick not in specified_users:
                    index += 1
                    continue

            # extract tasks points
            task_points = re.findall(r'(\d{1,}.\d\d|-)', categories)
            small_points = []
            for point in task_points:
                if point == '-':
                    small_points.append('0,0')
                else:
                    small_points.append(point.replace('.', ','))

            result += '"' + '", "'.join((nick, name, score)) + '", "' + \
                      '", "'.join(small_points) + '" \n'

            index += 1

        sys.stdout.write(result + '\n')


parser = HtmlParser()
parser.read_specified_users()
table = parser.read_html_table()
parser.load_info_in_groups(table)
parser.show_info(parser.load_info_in_groups(table), parser.read_specified_users())
