import sys
import os


dir_path_args = sys.argv[1:]


def check_arguments():
    if len(dir_path_args) < 1:
        print("Please pass at least 1 argument (directory path) ")
    else:
        for dir_path_name in dir_path_args:
            if not os.path.exists(dir_path_name):
                print("%s does not exist " % dir_path_name)


def load_files_into_list():
    file_list = []
    for dir_path_name in dir_path_args:
        for (dirpath, dirnames, filenames) in os.walk(dir_path_name):
            for oneNameFile in filenames:
                file_list.append(os.path.join(dirpath, oneNameFile))

    return file_list


def compare_files(file_list):
    duplicated_files = []
    while file_list:
        first_file = file_list.pop(0)
        with open(first_file, "rb") as f:
            content_of_compared_file = f.read()

        index = len(file_list) - 1
        same = True
        while index >= 0 and same:
            second_file = file_list[index]
            with open(second_file, "rb") as f:
                content_of_file_to_compare = f.read()

            # check size
            if len(content_of_compared_file) == len(content_of_file_to_compare):
                byte = 0
                while byte < len(content_of_compared_file):
                    # check content
                    if content_of_compared_file[byte] != content_of_file_to_compare[byte]:
                        same = False
                    byte = byte + 1
            else:
                same = False

            if same:
                duplicated_files.append(("Duplicated - size[b]: " +
                                         str(len(content_of_compared_file)), first_file, second_file))
                file_list.pop(index)

            index = index - 1
            same = True

    return duplicated_files


def print_duplicates(duplicate_files):
    for a_tuple in duplicate_files:  # iterates through each tuple

        for item in a_tuple:  # iterates through each tuple items
            print(item)


check_arguments()
duplicates = compare_files(load_files_into_list())
print_duplicates(duplicates)
