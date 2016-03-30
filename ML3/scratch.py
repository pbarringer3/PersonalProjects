import numpy as np


def get_records_list(file_name):
    file = open(file_name)
    records_list = list(file)
    file.close()
    for index in range(len(records_list)):
        records_list[index] = records_list[index][:-1].split(",")
    return records_list


def create_file(array, name):
    new_file = open("A3 StumbleUpon "+name+".csv",'w')
    for instance in array:
        instance_as_string = ",".join(instance)+"\n"
        new_file.write(instance_as_string)

    new_file.close()

data = get_records_list("A3 StumbleUpon Combined.csv")
np.random.shuffle(data)

split = 2853
train = data[:split]
test = data[split:]

create_file(train, "Train")
create_file(test, "Test")

