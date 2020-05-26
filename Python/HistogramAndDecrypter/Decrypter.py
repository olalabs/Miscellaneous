import Utils

FILE = "cipher.txt"


def join_lines():
    content_with_spaces_joined_lines = ' '.join(Utils.open_file(FILE))
    return content_with_spaces_joined_lines


def get_first_line():
    first_line = Utils.open_file(FILE)[0]
    return first_line


def get_lines_without_first():
    rest_lines = Utils.open_file(FILE)[1:]
    joined_rest_lines = " ".join(rest_lines)
    return joined_rest_lines


def substitute(first_line, dict_container):
    substitutions = {}
    index = 0
    for letter in sorted(dict_container, key=dict_container.get, reverse=True):
        substitutions[letter] = first_line[index]
        index += 1
    return substitutions


def decrypt(dict_container):
    substitutions = substitute(get_first_line(), dict_container)
    with open(FILE, mode="r") as input_file:
        for line in input_file:
            for oneChar in line:
                if oneChar in substitutions:
                    print(substitutions[oneChar], end="")
                else:
                    print(oneChar, end="")


content = get_lines_without_first().replace(" ", "")
dictionary = Utils.count_occur_of_letters(content)
decrypt(dictionary)
