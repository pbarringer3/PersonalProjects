def get_records_list(file_name):
    file = open(file_name)
    records_list = list(file)
    file.close()
    for index in range(len(records_list)):
        records_list[index] = records_list[index][:-1].split(",")
    return records_list

old_file = get_records_list('A3 StumbleUpon.csv')

new_file = open("A3 StumbleUpon.csv",'w')
for instance in old_file:
    if '?' not in instance:
        instance_as_string = ",".join(instance)+"\n"
        new_file.write(instance_as_string)

new_file.close()