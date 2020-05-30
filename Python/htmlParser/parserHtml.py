import sys
import re

specified_users = []
if len(sys.argv) > 1:
    with open(sys.argv[1], 'r', encoding='utf-8') as file:
        specified_users = file.read().splitlines()

content = sys.stdin.readlines()
html_table = ""
for line in content:
    table_body = re.match(r'<tbody><tr class="headerrow">.*</tr>', line)
    if table_body:
        html_table = table_body.group()
        break

pattern = r'<tr class="problemrow".*?<a.*?([0-9A-Za-z_]*?)">(.*?)(</a>.*?)' \
          r'(\d{1,}.\d\d)</td></tr>'
names_scores = re.findall(pattern, html_table)

result = ""
index = 0
while index < len(names_scores):
    (nick, name, categories, score) = names_scores[index]

    if specified_users:
        if nick not in specified_users:
            index += 1
            continue

    task_points = re.findall(r'(\d{1,}.\d\d|-)', categories)
    small_points = []
    for point in task_points:
        if point == '-':
            small_points.append('0,0')
        else:
            small_points.append(point.replace('.', ','))

    result += '"' + '", "'.join((nick, name, score)) + '", "' + \
              '", "'.join(small_points) + '"\n'

    index += 1

print(result)
