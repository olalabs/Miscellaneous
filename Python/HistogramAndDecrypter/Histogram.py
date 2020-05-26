import re
import Utils

FILE = "cipher.txt"
PATTERN = re.compile("^[a-zA-Z]+$")


def join_lines():
    content_with_spaces_joined_lines = ' '.join(Utils.open_file(FILE))
    return content_with_spaces_joined_lines


content = join_lines().replace(" ", "")


def count_only_letters_in_all_content(content):
    only_letters_in_content = 0
    for letter in content:
        if PATTERN.match(letter):
            only_letters_in_content += 1
    return only_letters_in_content


Utils.count_occur_of_letters(content)


def print_histogram():
    only_letters_in_content = count_only_letters_in_all_content(content)
    for letter in sorted(dictContainer, key=dictContainer.get, reverse=True):
        if PATTERN.match(letter):
            number_of_char_occur = dictContainer[letter]
            percent = (number_of_char_occur * 100) / only_letters_in_content
            print('%s %6d\t %6f' % (letter, number_of_char_occur, percent))


dictContainer = Utils.count_occur_of_letters(content)
print_histogram()
