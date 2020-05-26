import collections
import re


def open_file(filename):
    try:
        with open(filename, mode = "r") as input_file:
            content_with_spaces_list = [line.rstrip().upper() for line in input_file]
            return content_with_spaces_list
    except FileNotFoundError as e:
        print("File not found. Create file! ")
        print(e)
    except Exception as e:
        print("Unknown error")
        print(e)


def count_occur_of_letters(content):
    pattern = re.compile("^[a-zA-Z]+$")
    dict_container = collections.defaultdict(int)
    for letter in content:
        if pattern.match(letter):
            dict_container[letter] += 1
    return dict_container
