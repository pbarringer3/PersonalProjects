import numpy as np
from sklearn import metrics
from sklearn.cluster import KMeans
from sklearn.mixture import GMM
from openpyxl import load_workbook
from sklearn.preprocessing import scale


def import_data():
    data = dict()
    data["mushrooms"] = get_data('Mushrooms')
    data["stumble_upon"] = get_data('StumbleUpon')
    return data


def get_data(dataset_name):
    parts = ["train", "test"]
    train_test_data = dict()

    for part in parts:
        file = open("A3 "+dataset_name+" "+part+".csv")
        file_data = np.loadtxt(file, delimiter=",")

        instances = file_data[:, :-1]
        labels = file_data[:, -1]

        train_test_data[part+"_instances"] = instances
        train_test_data[part+"_labels"] = labels

    return train_test_data


# def get_accuracy(estimator, data):
#     estimator.fit(data["instances"])
#     return metrics.accuracy_score(data["labels"], estimator.labels_)


def run_kmeans(data, results_sheet):
    run_kmeans_on(data, results_sheet, "mushrooms", 'B', 'C')
    run_kmeans_on(data, results_sheet, "stumble_upon", 'K', 'L')

    scale_data(data)

    run_kmeans_on(data, results_sheet, "mushrooms", 'D', 'E')
    run_kmeans_on(data, results_sheet, "stumble_upon", 'M', 'N')


def run_kmeans_on(data, results_sheet, set_name, train_results_column, test_results_column):
    for i in range(4, 104):
        row = str(i)
        fit_estimator = create_and_fit_estimator(data, set_name)
        # get and store train accuracy
        train_accuracy = metrics.accuracy_score(data[set_name]["train_labels"], fit_estimator.labels_)
        results_sheet[train_results_column+row] = train_accuracy
        # get and store test accuracy
        test_predictions = fit_estimator.predict(data[set_name]["test_instances"])
        test_accuracy = metrics.accuracy_score(data[set_name]["test_labels"], test_predictions)
        results_sheet[test_results_column+row] = test_accuracy


def create_and_fit_estimator(data, set_name):
    estimator = KMeans(n_clusters=2)
    estimator.fit(data[set_name]["train_instances"])
    return estimator


def scale_data(data):
    datasets = ["mushrooms", "stumble_upon"]
    parts = ["train_instances", "test_instances"]

    for dataset in datasets:
        for part in parts:
            data[dataset][part] = scale(data[dataset][part])


def run_em(data, results_sheet):
    return

data = import_data()
results_file = load_workbook("results.xlsx")
results_sheet = results_file["Pure Clustering"]
run_kmeans(data, results_sheet)
run_em(data, results_sheet)

results_file.save("results.xlsx")